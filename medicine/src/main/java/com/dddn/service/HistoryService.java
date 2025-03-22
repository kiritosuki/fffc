package com.dddn.service;

import com.dddn.pojo.History;

import java.util.List;

public interface HistoryService {
    List<History> getHistoryList(Integer id);
}
