package com.want.wantworld.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.want.wantworld.dao.AccessTokenDao;
import com.want.wantworld.entity.AccessToken;
import com.want.wantworld.service.ICryptoService;
import com.want.wantworld.service.ILoginService;
import com.want.wantworld.service.IwwLoginService;
import com.want.wantworld.utils.WResultUtil;
import com.want.wantworld.dto.IwwLoginDto;
import com.want.wantworld.vo.IwwReturnVo;
import com.want.wantworld.vo.LoginVo;
import com.want.wantworld.vo.WResult;


@Service
public class IwwLoginServiceImpl implements IwwLoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(IwwLoginServiceImpl.class);
	
	@Autowired
	private AccessTokenDao accessTokenDao;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private ICryptoService cryptoService;
	
	@Value("${iwwapi.getAccessToken}")
	private String getAccessToken;

	@Value("${iwwapi.getUserId}")
	private String getUserIdUrl;
	
	@Value("${iwwapi.getUserInfo}")
	private String getUserInfoUrl;
	
	@Value("${iwwapi.corpid}")
	private String corpid;
	
	@Value("${iwwapi.corpsecret}")
	private String corpsecret;
	
	@Override
	public WResult<?> login(IwwLoginDto iwwLoginDto) {
		String code = iwwLoginDto.getCode();
		logger.info("企业微信登录开始解密code:",code);
		code = cryptoService.getDecryptText(code);
		//获取access_token
		AccessToken accessToken = accessTokenDao.selectAccessToken();
		Date currentDate = new Date(System.currentTimeMillis());
		AccessToken accessTokenParam = new AccessToken();
		//有token并且未超时
		if(null != accessToken && accessToken.getEndDate().compareTo(currentDate) > 0){
			accessTokenParam = accessToken;
		}else{//发接口获取token
			String url = String.format(getAccessToken,corpid,corpsecret);
			RestTemplate restTemplate = new RestTemplate();
			IwwReturnVo iwwReturnVo = new IwwReturnVo();
			iwwReturnVo = restTemplate.getForObject(url, IwwReturnVo.class);
			int errCode = iwwReturnVo.getErrcode();
			String errMsg = iwwReturnVo.getErrmsg();
			logger.info("企业微信登录获取token.getAccessToken errMsg"+errMsg);
			if(errCode == 0) {
				if(null != accessToken) {//更新token信息
					accessTokenParam.setSid(accessToken.getSid());
					accessTokenParam.setCreateDate(currentDate);
					accessTokenParam.setEndDate(getAfterDate(currentDate,iwwReturnVo.getExpires_in()));
					accessTokenParam.setToken(iwwReturnVo.getAccess_token());
					accessTokenDao.updateByPrimaryKeySelective(accessTokenParam);
				}else {
					accessTokenParam.setCreateDate(currentDate);//新增token信息
					accessTokenParam.setEndDate(getAfterDate(currentDate,iwwReturnVo.getExpires_in()));
					accessTokenParam.setToken(iwwReturnVo.getAccess_token());
					accessTokenDao.insertSelectiveSeq(accessTokenParam);
				}
			}else {//获取token异常
				return WResultUtil.error(iwwReturnVo.getErrcode(),iwwReturnVo.getErrmsg());
			}
			
		}
		String content = iwwGetUser(getUserIdUrl,accessTokenParam.getToken(),code);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap = new Gson().fromJson(content, paramMap.getClass());
		IwwReturnVo iwwReturnVo = new IwwReturnVo();
		iwwReturnVo = new Gson().fromJson(content, IwwReturnVo.class);
		int errCode = iwwReturnVo.getErrcode();
		String errMsg = iwwReturnVo.getErrmsg();
	
		if(errCode == 0 && errMsg.equals("ok")) {
			String userId = String.valueOf(paramMap.get("UserId"));
			String url = String.format(getUserInfoUrl,accessTokenParam.getToken(),userId);
			IwwReturnVo infoIwwReturnVo = new IwwReturnVo();
			RestTemplate restTemplate = new RestTemplate();
			infoIwwReturnVo = restTemplate.getForObject(url, IwwReturnVo.class);
			
			String userContent = iwwGetUserInfo(getUserInfoUrl,accessTokenParam.getToken(),userId);
			errCode = infoIwwReturnVo.getErrcode();
			errMsg = infoIwwReturnVo.getErrmsg();
			if(errCode == 0 && errMsg.equals("ok")) {
				String userName = infoIwwReturnVo.getName();
				LoginVo loginVo = new LoginVo();
				loginVo.setUserId(userId);
				loginVo.setDeviceid(iwwLoginDto.getDeviceid());
				loginVo.setOsType(iwwLoginDto.getOsType());
				loginVo.setBaseBand(iwwLoginDto.getBaseBand());
				Map<String,String> loginMap = new HashMap<String, String>();
				loginMap.put("userName",userName);
			 	
				Map<String, String> resultMap = loginService.loginToken(loginVo,loginMap);
				resultMap.put("userId", cryptoService.getEncryptText(userId));
				return WResultUtil.ok(resultMap);
				
			}else {
				return WResultUtil.error(errCode,errMsg);
			}
			
		}else {
			return WResultUtil.error(errCode,errMsg);
		}
		
	}
	
	/**
	 * 通过接口获取企业微信用户
	 * 
	 * @param token password
	 * @return String
	 */
	public String iwwGetUser(String reqUrl, String token, String code) {
		reqUrl = String.format(reqUrl, token, code);
		logger.info("------------reqUrl"+ reqUrl+"----------");

		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(300 * 1000).setConnectTimeout(300 * 1000)
				.build();
		HttpGet get = new HttpGet(reqUrl);

		get.setConfig(requestConfig);
		get.setHeader("Content-Type", "application/json;charset=utf-8");
		HttpResponse response;
		String content = "";
		try {
			// 调用接口
			response = httpClient.execute(get);
			// 获取返回值
			content = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.info(content);
		return content;
	}
	
	/**
	 * 通过接口获取企业微信用户信息
	 * 
	 * @param token password
	 * @return String
	 */
	public String iwwGetUserInfo(String reqUrl, String token, String userId) {
		reqUrl = String.format(reqUrl, token, userId);
		logger.info("------------reqUrl"+ reqUrl+"----------");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(300 * 1000).setConnectTimeout(300 * 1000)
				.build();
		HttpGet get = new HttpGet(reqUrl);

		get.setConfig(requestConfig);
		get.setHeader("Content-Type", "application/json;charset=utf-8");
		HttpResponse response;
		String content = "";
		try {
			// 调用接口
			response = httpClient.execute(get);
			// 获取返回值
			content = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.info(content);
		return content;
	}
	
	private Date getAfterDate(Date date,Long time) {
		return new Date(date.getTime() + time * 1000);
	}

}
