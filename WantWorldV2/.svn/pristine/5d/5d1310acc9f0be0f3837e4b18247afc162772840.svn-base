package com.want.wantworld.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

import com.want.wantworld.entity.FunctionInfo;
import com.want.wantworld.vo.Menu;
import com.want.wantworld.vo.MenuGroup;

@Mapper
public interface FunctionInfoDao {
    int deleteByPrimaryKey(String sid);

    int insert(FunctionInfo record);

    int insertSelective(FunctionInfo record);

    FunctionInfo selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(FunctionInfo record);

    int updateByPrimaryKey(FunctionInfo record);
    
    List<Menu> selectMentListByEmpId(String empId);
}