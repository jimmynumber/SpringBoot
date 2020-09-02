package com.want.wantworld.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.want.wantworld.dao.BaseSystemLinkDao;
import com.want.wantworld.entity.BaseSystemLink;
import com.want.wantworld.service.BaseSystemLinkService;
import com.want.wantworld.utils.WResultUtil;
import com.want.wantworld.vo.WResult;

@Service
public class BaseSystemLinkServiceImpl implements BaseSystemLinkService {

	@Autowired
	private BaseSystemLinkDao baseSystemLinkDao;
	
	@Override
	public WResult<?> getSystemLinkList() {
		return WResultUtil.ok(baseSystemLinkDao.getSystemLinkList());
	}

}
