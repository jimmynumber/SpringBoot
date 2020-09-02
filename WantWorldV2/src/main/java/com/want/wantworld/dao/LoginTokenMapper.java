package com.want.wantworld.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.want.wantworld.entity.LoginToken;

@Mapper
public interface LoginTokenMapper {
	int deleteByPrimaryKey(Short sid);

	int insertSelective(LoginToken record);

	List<LoginToken> selectByParam(LoginToken record);

	int updateByPrimaryKeySelective(LoginToken record);
	
	LoginToken selectOneByParam(LoginToken record);
}