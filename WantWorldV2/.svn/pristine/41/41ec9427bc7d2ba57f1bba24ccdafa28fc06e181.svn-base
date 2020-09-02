package com.want.wantworld.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.want.wantworld.entity.FunctionGroup;
import com.want.wantworld.vo.Menu;
import com.want.wantworld.vo.MenuGroup;
@Mapper
public interface FunctionGroupDao {
    int deleteByPrimaryKey(String funcGroupId);

    int insert(FunctionGroup record);

    int insertSelective(FunctionGroup record);

    FunctionGroup selectByPrimaryKey(String funcGroupId);

    int updateByPrimaryKeySelective(FunctionGroup record);

    int updateByPrimaryKey(FunctionGroup record);
    
    List<MenuGroup> selectMenuGroupList(String empId);
}