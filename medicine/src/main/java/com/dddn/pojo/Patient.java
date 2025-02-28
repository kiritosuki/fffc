package com.dddn.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Patient {
    private Integer id;
    private String name;
    private Integer age;
    private Integer gender;    //1男  0女
    private String phone;
    private String leftImg;
    private String rightImg;
    private String leftDiag;
    private String rightDiag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDate diagTime;
    private String resInfo;

    //数据库中没有的字段
    private String statusName;
    private List<String> queryStatusName;

    public Patient() {
    }

    public Patient(Integer id, String name,
                   Integer age, Integer gender,
                   String phone, String leftImg,
                   String rightImg, String leftDiag,
                   String rightDiag, LocalDateTime createTime,
                   LocalDateTime updateTime, LocalDate diagTime,
                   String resInfo, String statusName,
                   List<String> queryStatusName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.leftImg = leftImg;
        this.rightImg = rightImg;
        this.leftDiag = leftDiag;
        this.rightDiag = rightDiag;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.diagTime = diagTime;
        this.resInfo = resInfo;
        this.statusName = statusName;
        this.queryStatusName = queryStatusName;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", leftImg='" + leftImg + '\'' +
                ", rightImg='" + rightImg + '\'' +
                ", leftDiag='" + leftDiag + '\'' +
                ", rightDiag='" + rightDiag + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", diagTime=" + diagTime +
                ", resInfo='" + resInfo + '\'' +
                ", statusName='" + statusName + '\'' +
                ", queryStatusName=" + queryStatusName +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLeftImg() {
        return leftImg;
    }

    public void setLeftImg(String leftImg) {
        this.leftImg = leftImg;
    }

    public String getRightImg() {
        return rightImg;
    }

    public void setRightImg(String rightImg) {
        this.rightImg = rightImg;
    }

    public String getLeftDiag() {
        return leftDiag;
    }

    public void setLeftDiag(String leftDiag) {
        this.leftDiag = leftDiag;
    }

    public String getRightDiag() {
        return rightDiag;
    }

    public void setRightDiag(String rightDiag) {
        this.rightDiag = rightDiag;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDate getDiagTime() {
        return diagTime;
    }

    public void setDiagTime(LocalDate diagTime) {
        this.diagTime = diagTime;
    }

    public String getResInfo() {
        return resInfo;
    }

    public void setResInfo(String resInfo) {
        this.resInfo = resInfo;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<String> getQueryStatusName() {
        return queryStatusName;
    }

    public void setQueryStatusName(List<String> queryStatusName) {
        this.queryStatusName = queryStatusName;
    }
}
