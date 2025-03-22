package com.dddn.mapper;

import com.dddn.pojo.History;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HistoryMapper {

    List<History> getHistoryList(Integer patientId);

    List<Integer> getLeftStatusIllList(Integer id);

    List<Integer> getRightStatusIllList(Integer id);

    String getLeftIllInfo(Integer id);

    String getRightIllInfo(Integer id);
}
