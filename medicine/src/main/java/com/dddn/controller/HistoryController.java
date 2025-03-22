package com.dddn.controller;

import com.dddn.pojo.History;
import com.dddn.pojo.Result;
import com.dddn.service.HistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {
    private final HistoryService historyService;
    private final static Logger log = LoggerFactory.getLogger(HistoryController.class);

    public HistoryController(@Qualifier("historyServiceImpl") HistoryService historyService) {
        this.historyService = historyService;
    }

    //根据病人id查询历史记录
    @GetMapping("/patients/history")
    public Result getHistoryList(@RequestParam("id") Integer id){
        log.info("要查询历史记录的病人id: {}", id);
        List<History> historyList = historyService.getHistoryList(id);
        log.info("病人历史记录: {}", historyList);
        return Result.success(historyList);
    }
}
