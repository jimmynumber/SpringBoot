package com.want.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.want.dto.OTReturn;
import com.want.mapper.VisitTractingMapper;
import com.want.po.Paging;
import com.want.service.IStoreVisitTargetWebService;
import com.want.vo.StoreVisitTarget;

@Service
@Transactional
public class StoreVisitTargetWebServiceImpl implements IStoreVisitTargetWebService {
	
	@Autowired
    private VisitTractingMapper mapper;
	
	@Override
	public OTReturn syncStoreVisitTarget(List<StoreVisitTarget> storeVisitTarget,Paging page) {
		OTReturn otReturn=new OTReturn();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		System.out.println("BEGINDATE:"+dateFormat.format(date));
		try {
			if(storeVisitTarget!=null&&storeVisitTarget.size()>0) {
				if(page.getCurrent()==1) {
					mapper.deleteStoreVisitTarget(storeVisitTarget.get(0).getDataTime());
				}
				int count = (int) Math.floor(storeVisitTarget.size()/200)+1;
				
				for(int i = 0;i < count;i++) {
					int begin=i*200;
					int end=i*200+200;
					List<StoreVisitTarget> storeVisitTargets = new ArrayList<StoreVisitTarget>();
					for(int j = begin;j < end && j < storeVisitTarget.size();j++) {
						storeVisitTargets.add(storeVisitTarget.get(j));
					}
					if(storeVisitTargets != null && storeVisitTargets.size() > 0) {
						mapper.insertStoreVisitTarget(storeVisitTargets);
					}
				}
				otReturn.setTYPE("S");
				otReturn.setMESSAGE("接收成功");
			}else {
				otReturn.setTYPE("E");
				otReturn.setMESSAGE("参数为空");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			otReturn.setTYPE("E");
			otReturn.setMESSAGE("同步失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		Date date2 = new Date();
		System.out.println("ENDDATE:"+dateFormat.format(date2));
		return otReturn;
	}

}
