package com.want.wantworld.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.want.wantworld.entity.BasePicture;

@Mapper
public interface BasePictureMapper {
    int deleteByPrimaryKey(String sid);

    int insert(BasePicture record);

    int insertSelective(BasePicture record);

    BasePicture selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(BasePicture record);

    int updateByPrimaryKey(BasePicture record);
    
    List<BasePicture> getAllPicture();
    
    
}