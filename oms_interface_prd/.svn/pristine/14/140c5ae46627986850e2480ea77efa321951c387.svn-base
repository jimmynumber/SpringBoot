package com.want.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.want.mapper188.ProdStructMapper;
import com.want.response.ResponseEntity;
import com.want.service.IProdStructWebService;
import com.want.util.ExceptionUtil;
import com.want.vo.ProdStruct;

@Service
//@Transactional
public class ProdStructServiceImpl implements IProdStructWebService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProdStructServiceImpl.class);
	
	@Autowired
    private ProdStructMapper mapper;
	
	private SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
	
	
	@SuppressWarnings("finally")
	@Override
	public ResponseEntity syncProdStruct(List<ProdStruct> prodStructList) {
		ResponseEntity result=new ResponseEntity();
		String beginDate=dateFormat.format(new Date());
		LOGGER.info("BEGINDATE:"+dateFormat.format(new Date()));
		StringBuilder sb=new StringBuilder();
		StringBuilder reasonSb=new StringBuilder("异常原因：\n");
		try {
			int totalNum=prodStructList.size();
			int insertNum=0;
			for (ProdStruct prodStruct : prodStructList) {
				try {
					mapper.mergeProdStruct(prodStruct);
					insertNum++;
				} catch (Exception e) {
					sb.append(prodStruct.getId()+",");
					reasonSb.append(prodStruct+" : "+ExceptionUtil.errorTrackSpace(e)+"\n");
				}
			}
			if (totalNum == insertNum) {
				result.setType("S");
				String msg="成功接收%s笔资料";
				msg=String.format(msg,totalNum);
				result.setMessage(msg);
				// 删除前一天数据
				mapper.deleteProdStruct();
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
			LOGGER.info("beginDate:"+beginDate+",enddate:"+dateFormat.format(new Date()));
			return result;
		}
	}
}
