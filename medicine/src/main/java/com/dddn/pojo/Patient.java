package com.dddn.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Patient {
    private Integer id;
    private String name;
    private Integer age;
    private Integer gender;     //1男 2女
    private String phone;
    private String idCard;
    private Integer doctorId;
    private String leftImg;
    private String rightImg;
    private String leftDiag;
    private String rightDiag;
    private String resInfo;
    private LocalDate diagTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String allergy;
    private String complaint;
    private Integer visit;    //1初诊 2复诊
    private String presHistory;
    private String pastHistory;
    private String posFeature;
    private String negFeature;

    //数据库中没有的字段
    private String doctorName;
    private List<Integer> leftStatusIllList;
    private List<Integer> rightStatusIllList;
    private String leftIllInfo;
    private String rightIllInfo;
    private List<History> historyList;

    public Patient(Integer id, String name, Integer age, Integer gender, String phone, String idCard, Integer doctorId, String leftImg, String rightImg, String leftDiag, String rightDiag, String resInfo, LocalDate diagTime, LocalDateTime createTime, LocalDateTime updateTime, String allergy, String complaint, Integer visit, String presHistory, String pastHistory, String posFeature, String negFeature, String doctorName, List<Integer> leftStatusIllList, List<Integer> rightStatusIllList, String leftIllInfo, String rightIllInfo, List<History> historyList) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.idCard = idCard;
        this.doctorId = doctorId;
        this.leftImg = leftImg;
        this.rightImg = rightImg;
        this.leftDiag = leftDiag;
        this.rightDiag = rightDiag;
        this.resInfo = resInfo;
        this.diagTime = diagTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.allergy = allergy;
        this.complaint = complaint;
        this.visit = visit;
        this.presHistory = presHistory;
        this.pastHistory = pastHistory;
        this.posFeature = posFeature;
        this.negFeature = negFeature;
        this.doctorName = doctorName;
        this.leftStatusIllList = leftStatusIllList;
        this.rightStatusIllList = rightStatusIllList;
        this.leftIllInfo = leftIllInfo;
        this.rightIllInfo = rightIllInfo;
        this.historyList = historyList;
    }

    public Patient() {
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", doctorId=" + doctorId +
                ", leftImg='" + leftImg + '\'' +
                ", rightImg='" + rightImg + '\'' +
                ", leftDiag='" + leftDiag + '\'' +
                ", rightDiag='" + rightDiag + '\'' +
                ", resInfo='" + resInfo + '\'' +
                ", diagTime=" + diagTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", allergy='" + allergy + '\'' +
                ", complaint='" + complaint + '\'' +
                ", visit=" + visit +
                ", presHistory='" + presHistory + '\'' +
                ", pastHistory='" + pastHistory + '\'' +
                ", posFeature='" + posFeature + '\'' +
                ", negFeature='" + negFeature + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", leftStatusIllList=" + leftStatusIllList +
                ", rightStatusIllList=" + rightStatusIllList +
                ", leftIllInfo='" + leftIllInfo + '\'' +
                ", rightIllInfo='" + rightIllInfo + '\'' +
                ", historyList=" + historyList +
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
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

    public String getResInfo() {
        return resInfo;
    }

    public void setResInfo(String resInfo) {
        this.resInfo = resInfo;
    }

    public LocalDate getDiagTime() {
        return diagTime;
    }

    public void setDiagTime(LocalDate diagTime) {
        this.diagTime = diagTime;
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

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public String getPresHistory() {
        return presHistory;
    }

    public void setPresHistory(String presHistory) {
        this.presHistory = presHistory;
    }

    public String getPastHistory() {
        return pastHistory;
    }

    public void setPastHistory(String pastHistory) {
        this.pastHistory = pastHistory;
    }

    public String getPosFeature() {
        return posFeature;
    }

    public void setPosFeature(String posFeature) {
        this.posFeature = posFeature;
    }

    public String getNegFeature() {
        return negFeature;
    }

    public void setNegFeature(String negFeature) {
        this.negFeature = negFeature;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public List<Integer> getLeftStatusIllList() {
        return leftStatusIllList;
    }

    public void setLeftStatusIllList(List<Integer> leftStatusIllList) {
        this.leftStatusIllList = leftStatusIllList;
    }

    public List<Integer> getRightStatusIllList() {
        return rightStatusIllList;
    }

    public void setRightStatusIllList(List<Integer> rightStatusIllList) {
        this.rightStatusIllList = rightStatusIllList;
    }

    public String getLeftIllInfo() {
        return leftIllInfo;
    }

    public void setLeftIllInfo(String leftIllInfo) {
        this.leftIllInfo = leftIllInfo;
    }

    public String getRightIllInfo() {
        return rightIllInfo;
    }

    public void setRightIllInfo(String rightIllInfo) {
        this.rightIllInfo = rightIllInfo;
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }
}
