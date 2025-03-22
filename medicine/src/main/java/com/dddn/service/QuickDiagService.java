package com.dddn.service;

import com.dddn.pojo.QuickResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface QuickDiagService {
    QuickResult quickDiag(MultipartFile file) throws Exception;
}
