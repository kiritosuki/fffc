package com.dddn.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.dddn.pojo.AliyunOSSProperty;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class AliyunOSSOperator {

    private final AliyunOSSProperty aliyunOSSProperty;

    public AliyunOSSOperator(@Qualifier("aliyunOSSProperty") AliyunOSSProperty aliyunOSSProperty){
        this.aliyunOSSProperty = aliyunOSSProperty;
    }

    public void deleteFile(List<String> fileList) throws Exception {
        //配置域名 地区 bucket
        String endpoint = aliyunOSSProperty.getEndpoint();
        String bucketName = aliyunOSSProperty.getBucketName();
        String region = aliyunOSSProperty.getRegion();
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();

        try {
            // 删除文件。
            // 需要删除的多个文件完整路径。文件完整路径中不能包含Bucket名称。
            List<String> keys = new ArrayList<>();
            fileList.forEach(s -> {
                String key = s.replace(endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/", "");
                keys.add(key);
            });

            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys).withEncodingType("url"));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            for(String obj : deletedObjects) {
                String deleteObj =  URLDecoder.decode(obj, StandardCharsets.UTF_8);
                System.out.println(deleteObj);
            }
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            ossClient.shutdown();
        }
    }
}
