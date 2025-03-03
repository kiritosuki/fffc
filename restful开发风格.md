RESTful 风格的 API 设计规范主要基于 **HTTP 方法（GET、POST、PUT、DELETE）**，结合 **资源路径** 进行增删改查（CRUD）操作。以下是标准的 RESTful **增删改查请求路径**设计方案：

------

## **1. RESTful API 设计原则**

- **资源路径（URI）应该是名词，不使用动词**
- **HTTP 方法决定操作类型**
- **路径层级清晰，尽量简洁**
- **使用 HTTP 状态码表示请求结果**

------

## **2. 增删改查（CRUD）API 设计**

| 操作                     | HTTP 方法 | 路径示例              | 描述                           |
| ------------------------ | --------- | --------------------- | ------------------------------ |
| **查询所有资源**         | `GET`     | `/students`           | 获取所有学生信息               |
| **查询单个资源**         | `GET`     | `/students/{id}`      | 获取 ID 为 `{id}` 的学生信息   |
| **新增资源**             | `POST`    | `/students`           | 新增一个学生                   |
| **修改资源**（整体更新） | `PUT`     | `/students/{id}`      | 更新 ID 为 `{id}` 的学生信息   |
| **修改资源**（部分更新） | `PATCH`   | `/students/{id}`      | 仅更新 ID 为 `{id}` 的部分信息 |
| **删除单个资源**         | `DELETE`  | `/students/{id}`      | 删除 ID 为 `{id}` 的学生       |
| **批量删除资源**         | `DELETE`  | `/students?ids=1,2,3` | 删除多个学生                   |

------

## **3. 详细示例**

### **（1）查询所有学生**

**请求方式：**

```
http

GET /students
```

**示例响应（JSON）：**

```
json

[
    {"id": 1, "name": "张三", "age": 20},
    {"id": 2, "name": "李四", "age": 21}
]
```

------

### **（2）查询某个学生**

**请求方式：**

```
http

GET /students/1
```

**示例响应（JSON）：**

```
json

{"id": 1, "name": "张三", "age": 20}
```

------

### **（3）添加新学生**

**请求方式：**

```
http

POST /students
Content-Type: application/json
```

**请求体（JSON）：**

```
json

{
    "name": "王五",
    "age": 22
}
```

**示例响应：**

```
json

{"id": 3, "name": "王五", "age": 22}
```

------

### **（4）更新学生信息（PUT）**

**请求方式：**

```
h

PUT /students/1
Content-Type: application/json
```

**请求体（JSON）：**

```
json

{
    "name": "张三",
    "age": 23
}
```

**示例响应：**

```
json

{"id": 1, "name": "张三", "age": 23}
```

🚀 **注意**：`PUT` 适用于 **整体更新**，请求体应包含所有字段。

------

### **（5）部分更新学生信息（PATCH）**

**请求方式：**

```
http

PATCH /students/1
Content-Type: application/json
```

**请求体（JSON）：**

```
json

{
    "age": 24
}
```

**示例响应：**

```
json

{"id": 1, "name": "张三", "age": 24}
```

🚀 **注意**：`PATCH` 适用于 **局部更新**，只需提交要修改的字段。

------

### **（6）删除单个学生**

**请求方式：**

```
http

DELETE /students/1
```

**示例响应：**

```
json

{"message": "删除成功"}
```

------

### **（7）批量删除学生**

**请求方式：**

```
h

DELETE /students?ids=1,2,3
```

**示例响应：**

```
json

{"message": "删除成功"}
```

✅ **说明**：

- `DELETE` 方法 **不支持请求体**，所以批量删除时用 **查询参数（`?ids=1,2,3`）**。
- 也可以使用 `POST /students/delete`，但 **不符合 RESTful 规范**。

------

## **4. RESTful API 设计小贴士**

### **✅ 推荐做法**

1. 资源路径用名词，不要用动词
   - ❌ `/getStudentInfo`
   - ✅ `/students/{id}`
2. 批量操作用查询参数
   - ❌ `/students/deleteMany`
   - ✅ `/students?ids=1,2,3`
3. 正确使用 HTTP 方法
   - `GET` 用于查询
   - `POST` 用于新增
   - `PUT` 用于整体更新
   - `PATCH` 用于部分更新
   - `DELETE` 用于删除

### **❌ 不推荐的 URL**

| 错误设计               | 正确设计                         |
| ---------------------- | -------------------------------- |
| `/getStudentById?id=1` | `/students/1`                    |
| `/deleteStudent?id=1`  | `/students/1` (`DELETE`)         |
| `/updateStudent`       | `/students/1` (`PUT` or `PATCH`) |

------

## **5. 总结**

- **增（POST）** → `/students`
- 查（GET）
  - 查询全部：`/students`
  - 查询单个：`/students/{id}`
- **改（PUT / PATCH）** → `/students/{id}`
- 删（DELETE）
  - 删除单个：`/students/{id}`
  - 批量删除：`/students?ids=1,2,3`

这样设计的 API **简洁、清晰、符合 RESTful 规范**，便于维护和使用。