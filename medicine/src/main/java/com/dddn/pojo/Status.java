package com.dddn.pojo;

public class Status {
    private Integer id;
    private Integer ill;
    private Integer patientId;
    private String info;
    private Integer position;   //1左眼 2右眼

    public Status(Integer id, Integer ill, Integer patientId, String info, Integer position) {
        this.id = id;
        this.ill = ill;
        this.patientId = patientId;
        this.info = info;
        this.position = position;
    }

    public Status() {
    }

    public Status(Integer ill, Integer patientId, Integer position) {
        this.ill = ill;
        this.patientId = patientId;
        this.position = position;
    }

    public Status(Integer ill, Integer patientId, String info, Integer position) {
        this.ill = ill;
        this.patientId = patientId;
        this.info = info;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", ill=" + ill +
                ", patientId=" + patientId +
                ", info='" + info + '\'' +
                ", position=" + position +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIll() {
        return ill;
    }

    public void setIll(Integer ill) {
        this.ill = ill;
    }

    public Integer getPatient_id() {
        return patientId;
    }

    public void setPatient_id(Integer patientId) {
        this.patientId = patientId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
