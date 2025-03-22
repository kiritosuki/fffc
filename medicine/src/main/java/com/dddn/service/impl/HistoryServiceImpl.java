package com.dddn.service.impl;

import com.dddn.mapper.HistoryMapper;
import com.dddn.pojo.History;
import com.dddn.service.HistoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryMapper historyMapper;

    public HistoryServiceImpl(@Qualifier("historyMapper") HistoryMapper historyMapper) {
        this.historyMapper = historyMapper;
    }

    //根据病人id查询历史记录列表
    @Override
    public List<History> getHistoryList(Integer id) {
        Integer patientId = id;
        //查询历史记录列表
        List<History> historyList = historyMapper.getHistoryList(patientId);
        if(historyList == null || historyList.isEmpty()){
            return historyList;
        }
        //查询每条历史记录中的数字疾病代号 并赋值相对应的疾病字符串list
        historyList.forEach(history -> {
            //查询疾病编号
            List<Integer> leftStatusIllList = historyMapper.getLeftStatusIllList(history.getId());
            List<Integer> rightStatusIllList = historyMapper.getRightStatusIllList(history.getId());
            history.setLeftStatusIllList(leftStatusIllList);
            history.setRightStatusIllList(rightStatusIllList);
            //查询异常信息
            String leftIllInfo = historyMapper.getLeftIllInfo(history.getId());
            String rightIllInfo = historyMapper.getRightIllInfo(history.getId());
            history.setLeftIllInfo(leftIllInfo);
            history.setRightIllInfo(rightIllInfo);
            //新建一个所有疾病名称的列表
            List<String> statusNameList = List.of("正常", "糖尿病", "青光眼", "白内障", "AMD", "高血压", "近似", "其他异常");
            //通过疾病编号得到疾病名称的list
            List<String> leftStatusStrList = new ArrayList<>();
            for(Integer i : leftStatusIllList){
                leftStatusStrList.add(statusNameList.get(i-1));
            }
            List<String> rightStatusStrList = new ArrayList<>();
            for(Integer j : rightStatusIllList){
                rightStatusStrList.add(statusNameList.get(j-1));
            }
            history.setLeftStatusStrList(leftStatusStrList);
            history.setRightStatusStrList(rightStatusStrList);
        });
        return historyList;
    }
}
