package com.dddn.pojo;

public class PastStatus {
    private Integer id;
    private Integer ill;
    private Integer historyId;
    private String info;
    private Integer position;

    public PastStatus(Integer id, Integer ill, Integer historyId, String info, Integer position) {
        this.id = id;
        this.ill = ill;
        this.historyId = historyId;
        this.info = info;
        this.position = position;
    }

    public PastStatus(Integer ill, Integer historyId, String info, Integer position) {
        this.ill = ill;
        this.historyId = historyId;
        this.info = info;
        this.position = position;
    }

    public PastStatus(Integer ill, Integer historyId, Integer position) {
        this.ill = ill;
        this.historyId = historyId;
        this.position = position;
    }

    public PastStatus() {
    }

    @Override
    public String toString() {
        return "PastStatus{" +
                "id=" + id +
                ", ill=" + ill +
                ", historyId=" + historyId +
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

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
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
