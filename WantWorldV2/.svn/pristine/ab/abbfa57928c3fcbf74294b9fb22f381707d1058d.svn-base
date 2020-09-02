package com.want.wantworld.dao;

import com.want.wantworld.entity.AccessToken;
import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccessTokenDao {
    int deleteByPrimaryKey(BigDecimal sid);

    int insert(AccessToken record);

    int insertSelective(AccessToken record);
    
    int insertSelectiveSeq(AccessToken record);

    AccessToken selectByPrimaryKey(BigDecimal sid);

    int updateByPrimaryKeySelective(AccessToken record);

    int updateByPrimaryKey(AccessToken record);
    
    AccessToken selectAccessToken();
}