package com.want.wantworld.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.want.wantworld.entity.BaseData;

@Mapper
public interface BaseDataDao {
    int deleteByPrimaryKey(String attributeKey);

    int insert(BaseData record);

    int insertSelective(BaseData record);

    BaseData selectByPrimaryKey(String attributeKey);

    int updateByPrimaryKeySelective(BaseData record);

    int updateByPrimaryKey(BaseData record);
    
    BaseData selectBaseDateByAttribute(String attributeId);
    
    List<BaseData> selectBaseDateListByAttribute(String attributeId);
}