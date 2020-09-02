package com.want.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.want.vo.Attence;

@Mapper
public interface AttenceMapper {
	
    //检查是否存在同一笔考勤信息
    boolean checkAttenceExist(@Param("id")String id);
    
	// 写入考勤资料@Param("attence")
	void insertAttence(Attence attence);
	
	// 修改考勤资料
	void updateAttence(Attence attence);
	
	
}
