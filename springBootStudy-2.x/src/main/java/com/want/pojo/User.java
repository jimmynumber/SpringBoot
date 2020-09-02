package com.want.pojo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description="用户实体")
public class User {

    @ApiModelProperty("用户编号")
    private Long id;
    
    @ApiModelProperty("用户姓名")
    @NotNull
    @Size(min = 2, max = 5)
    private String name;
    
    
    @ApiModelProperty("用户年龄")
    @NotNull
    @Max(100)
    @Min(10)
    private Integer age;

 
    
    
    
    
}