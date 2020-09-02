package com.want.wantworld.dao;

import com.want.wantworld.entity.BaseSystemLink;
import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseSystemLinkDao {
    int deleteByPrimaryKey(BigDecimal sid);

    int insert(BaseSystemLink record);

    int insertSelective(BaseSystemLink record);

    BaseSystemLink selectByPrimaryKey(BigDecimal sid);

    int updateByPrimaryKeySelective(BaseSystemLink record);

    int updateByPrimaryKey(BaseSystemLink record);
    
    List<BaseSystemLink> getSystemLinkList();
}