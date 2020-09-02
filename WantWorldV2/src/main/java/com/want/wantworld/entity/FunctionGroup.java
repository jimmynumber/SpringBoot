package com.want.wantworld.entity;

import java.util.Date;

public class FunctionGroup {
    private String funcGroupId;

    private String funcGroupName;

    private String status;

    private String creator;

    private Date createDate;

    public String getFuncGroupId() {
        return funcGroupId;
    }

    public void setFuncGroupId(String funcGroupId) {
        this.funcGroupId = funcGroupId == null ? null : funcGroupId.trim();
    }

    public String getFuncGroupName() {
        return funcGroupName;
    }

    public void setFuncGroupName(String funcGroupName) {
        this.funcGroupName = funcGroupName == null ? null : funcGroupName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}