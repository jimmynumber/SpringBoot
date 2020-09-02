package com.want.wantworld.service;

import com.want.wantworld.dto.IwwLoginDto;
import com.want.wantworld.vo.WResult;

public interface IwwLoginService {
	
	public WResult<?> login(IwwLoginDto iwwLoginVo);
	

}
