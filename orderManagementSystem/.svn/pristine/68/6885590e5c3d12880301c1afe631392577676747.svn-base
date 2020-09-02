package com.want.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.want.dto.MonitorDto;

@Mapper
public interface MonitorMapper {
   	String FUNC_STATUS_FAILED = "0";      // 失败
	String FUNC_STATUS_SUCCESSED = "1";   // 成功
	String FUNC_STATUS_RUNNING = "2";     // 执行中
    //检查是否存在同一信息
    boolean checkMonitorExist(@Param("sid")String sid);
    
	// 写入资料
	void insertMonitor(MonitorDto Monitor);
	
	// 修改资料
	void updateMonitor(MonitorDto Monitor);
	
	
}
