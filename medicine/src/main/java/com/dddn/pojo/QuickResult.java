package com.dddn.pojo;

import java.time.LocalDateTime;

public class QuickResult {
    private String img;     //图片url
    private String diag;    //诊断关键字
    private String resInfo;
    private LocalDateTime createTime;

    public QuickResult(String img, String diag, String resInfo, LocalDateTime createTime) {
        this.img = img;
        this.diag = diag;
        this.resInfo = resInfo;
        this.createTime = createTime;
    }

    public QuickResult() {
    }

    @Override
    public String toString() {
        return "QuickResult{" +
                "img='" + img + '\'' +
                ", diag='" + diag + '\'' +
                ", resInfo='" + resInfo + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDiag() {
        return diag;
    }

    public void setDiag(String diag) {
        this.diag = diag;
    }

    public String getResInfo() {
        return resInfo;
    }

    public void setResInfo(String resInfo) {
        this.resInfo = resInfo;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
