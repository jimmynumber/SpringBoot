package com.want.wantworld.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.want.wantworld.dao.BaseDataDao;
import com.want.wantworld.dao.LoginTokenMapper;
import com.want.wantworld.dto.BaseDataDto;
import com.want.wantworld.entity.BaseData;
import com.want.wantworld.entity.LoginToken;
import com.want.wantworld.service.BaseDataService;
import com.want.wantworld.utils.VersionUtils;
import com.want.wantworld.utils.WResultUtil;
import com.want.wantworld.vo.BaseVersion;
import com.want.wantworld.vo.WResult;

@Service
public class BaseDataServiceImpl implements BaseDataService{
	
	@Autowired
	private BaseDataDao baseDataDao;
	
	@Autowired
	private LoginTokenMapper loginTokenMapper;
	
	private static final String ANDROID_VERSION = "ANDROID_VERSION";//安卓正式版本
	
	private static final String IOS_VERSION = "IOS_VERSION";//苹果正式版本
	
	private static final String BETA_ANDROID_VERSION = "ANDROID_BETA_VERSION";//安卓测试版本
	
	private static final String BETA_IOS_VERSION = "IOS_BETA_VERSION";//苹果测试版本
	
	private static final String ANDROID = "ANDROID";//设备类型--ANDROID
	
	private static final String IOS = "IOS";//设备类型--IOS

	@Override
	public WResult<?> getBaseData(BaseDataDto baseDataDto) {
		BaseVersion baseVersion = new BaseVersion();
		BaseData baseData = null;
		String queryParam = ANDROID_VERSION;
		if(null == baseDataDto.getToken() || baseDataDto.getToken().equals("")){//没有用户ID查询最新服务端版本号
			if(ANDROID.equals(baseDataDto.getOsType())){//查询安卓版本号
				queryParam = ANDROID_VERSION;
			}else{//查询IOS版本号
				queryParam = IOS_VERSION;
			}
			//baseData = baseDataDao.selectBaseDateByAttribute(queryParam);
		}else{//有用户ID查询用户是否有测试版本
			LoginToken loginToken = new LoginToken();
			loginToken.setToken(baseDataDto.getToken());
			loginToken = (LoginToken) loginTokenMapper.selectOneByParam(loginToken);
			if(loginToken == null) {
				if(ANDROID.equals(baseDataDto.getOsType())){//查询安卓版本号
					queryParam = ANDROID_VERSION;
				}else{//查询IOS版本号
					queryParam = IOS_VERSION;
				}
			}else {
				int isBeta = loginToken.getIsBeta();
				if(isBeta == 1){//有试运行版本
					if(ANDROID.equals(baseDataDto.getOsType())){//查询安卓版本号
						queryParam = BETA_ANDROID_VERSION;
					}else{//查询IOS版本号
						queryParam = BETA_IOS_VERSION;
					}
				}else{//
					if(ANDROID.equals(baseDataDto.getOsType())){//查询安卓版本号
						queryParam = ANDROID_VERSION;
					}else{//查询IOS版本号
						queryParam = IOS_VERSION;
					}
				}
			}
		}
		baseData = baseDataDao.selectBaseDateByAttribute(queryParam);//查询基础数据
		String serverVersion = baseData.getAttributeValue();//服务端版本号
		int result = 0;
		try {
			//比较服务端版本号和客户端版本号
			result = VersionUtils.compare(baseDataDto.getVersion(), serverVersion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result<0){//需要更新
			baseVersion.setUpdate(true);
			List<BaseData> baseDateList = baseDataDao.selectBaseDateListByAttribute(queryParam);
			baseVersion.setNewVersion(serverVersion);
			baseVersion.setApkUrl(baseDateList.get(1).getAttributeValue());
			baseVersion.setUpdateDescription(baseDateList.get(2).getAttributeValue());
		}else{
			baseVersion.setUpdate(false);
		}
		return WResultUtil.ok(baseVersion);
		
	}

}
