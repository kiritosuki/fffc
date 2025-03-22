package com.dddn.pojo;

import java.time.LocalDate;

public class PatientQueryParam {
    private String name;
    private String gender;
    private LocalDate begin;
    private LocalDate end;
    private String phone;
    private String idCard;
    private Integer page = 1;
    private Integer pageSize = 10;

    public PatientQueryParam(String name, String gender, LocalDate begin, LocalDate end, String phone, String idCard, Integer page, Integer pageSize) {
        this.name = name;
        this.gender = gender;
        this.begin = begin;
        this.end = end;
        this.phone = phone;
        this.idCard = idCard;
        this.page = page;
        this.pageSize = pageSize;
    }

    public PatientQueryParam() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PatientQueryParam{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
