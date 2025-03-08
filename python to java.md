# 医学影像疾病预测接口文档

## 疾病预测接口
### 基本信息
请求路径: `/predict`  
请求方式: POST  
接口描述: 根据医学影像和患者信息预测8种眼底疾病（0-7编号）  

### 请求参数

#### 请求体
- 参数格式：json
- 参数说明：

| 字段名    | 类型   | 是否必须 |  示例值 | 备注     |
|----------|--------|---------|--------|----------|
| image    | string | 是 |"https://web-framework.oss-cn-hangzhou.aliyuncs.com/2022-09-03-07-37-38222.jpg"|图片URL|
| age      | int | 是 | 35 | 0 ≤ age ≤ 120 |
| sex      | int | 是 | 1 | 0: 女性<br>1: 男性 |
| symptoms | string | 是 | "视力模糊3个月，伴飞蚊症" | 10-500个字符 |

**请求示例**:
```json
{
  "image": "/medical_images/2023/patient_123.jpg",
  "age": 45,
  "sex": 1,
  "symptoms": "左眼视力下降伴闪光感两周"
}
```

### 响应数据
- 参数格式： json
- 参数说明：

| 字段名 | 类型 | 是否必须 | 示例值 | 备注 |
|--------|------|----------|--------|----------|
| code | string | 是 |1 |响应是否成功|
| msg | int | 是 | "success" |相关信息|
| data | Object  | 是    |   | 返回的数据 |
| --\|--illness | Integer | 是 | 1 | 返回疾病种类 |
| --\|--diagnosis | String | 否 |abab | 疾病诊断 |


**请求示例**：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "illness": 1,
    "diagnosis": ""
  }
}
```

