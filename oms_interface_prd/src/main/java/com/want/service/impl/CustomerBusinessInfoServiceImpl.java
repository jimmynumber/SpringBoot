package com.want.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.want.mapper187.CustomerBusinessInfoMapper;
import com.want.response.ResponseEntity;
import com.want.service.ICustomerBusinessInfoWebService;
import com.want.util.ExceptionUtil;
import com.want.vo.CustomerBusinessInfo;

@Service
//@Transactional
public class CustomerBusinessInfoServiceImpl implements ICustomerBusinessInfoWebService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProdStructServiceImpl.class);
	
	@Autowired
    private CustomerBusinessInfoMapper mapper;
	
	private SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
	
	@Override
	public ResponseEntity syncCustomerBusinessInfo(List<CustomerBusinessInfo> customerBusinessInfoList) {
		ResponseEntity result=new ResponseEntity();
		LOGGER.info("BEGINDATE:"+dateFormat.format(new Date()));
		try {
			/*******************/
			StringBuilder sb=new StringBuilder();
			StringBuilder reasonSb=new StringBuilder("异常原因：\n");
			int totalNum=customerBusinessInfoList.size();
			int insertNum=0;
			for (CustomerBusinessInfo item : customerBusinessInfoList) {
				try {
					mapper.mergeCustomerBusinessInfo(item);
					insertNum++;
				} catch (Exception e) {
					sb.append(item.getTenantUserid()+",");
					reasonSb.append(item+" : "+ExceptionUtil.errorTrackSpace(e)+"\n");
				}
			}
			if (totalNum == insertNum) {
				result.setType("S");
				String msg="成功接收%s笔资料";
				msg=String.format(msg,totalNum);
				result.setMessage(msg);
			}else {
				result.setType("E");
				String msg="接收%s笔考勤资料,失败%d笔";
				msg=String.format(msg,insertNum,(totalNum-insertNum) );
				result.setMessage(msg);
				result.setEid(sb.toString().substring(0, sb.toString().length()-1));
				LOGGER.error("monitor execute error >>> "+ reasonSb);
			}
			/*******************/
		} catch (Exception e) {
			LOGGER.error("monitor execute error >>> "+ ExceptionUtil.errorTrackSpace(e));
			result.setType("E");
			result.setMessage("同步失败");
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}finally {
			LOGGER.info("ENDDATE:"+dateFormat.format(new Date()));
		}
		
		return result;
	}
   

}
