# 城市品质提升文档 小程序/H5

## 基本信息

- 接口前缀：`/city/api`
- 返回格式：JSON（统一使用 `AjaxResult`）
- 鉴权机制：部分接口需要 Token
- 路径为com/ruoyi/city/controller下的LoginController、 MiniReportsController、 MiniTypesController、 OssUploadController

---

## 登录接口

### POST `/city/api/login`

> 小程序用户登录，返回 token 和用户信息

#### 请求参数（JSON）

```json
{
  "username": "string",
  "password": "string"
}
```

#### 返回示例

```json
{
  "msg": "操作成功",
  "code": 200,
  "data": {
    "msg": "操作成功",
    "code": 200,
    "data": {
      "userInfo": {
        "role": "field",
        "phone": "13333333333",
        "userId": 1,
        "username": "test0"
      },
      "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MCIsImxvZ2luX3VzZXJfa2V5IjoiMWVlZDRmMTMtZTUwYy00YmViLThkZWUtMjliMjNmZThjNzgxIn0.NtEhUscx_tu1wHWWMP5FEXdaSUYKYNRlJxYDjXdrC-SZ5T9Uco7hdPPhl8IkcMgm8rAhCO2Yu-AZfzDgD4QazQ"
    }
  }
}
```

---

## 工单接口

### POST `/city/api/submit`

> 提交工单

#### 请求参数（JSON）

```json
{
  "title": "井盖缺失或位移",
  "description": "",
  "address": "华南理工大学(五山校区)",
  "latitude": 23.155361,
  "longitude": 113.345014,
  "typeId": 1,
  "reportImagesList": [
    {
      "imageUrl": "https://ccc0001.oss-cn-guangzhou.aliyuncs.com/23dd7eb0-520b-43e3-b000-275515e2569c.jpeg"
    }
  ],
  "userId": 1
}
```

---

### GET `/city/api/list`

> 查询当前用户提交的工单列表  
> 支持分页和筛选参数

---

### PUT `/city/api/assign`

> 分配工单处理人 目前只用在后台操作接口

#### 请求参数（JSON）

```json
{
  "createBy": null,
  "createTime": "2025-04-22 14:47:26",
  "updateBy": null,
  "updateTime": "2025-05-09 15:01:30",
  "remark": null,
  "id": 1,
  "userId": 1,
  "typeId": 1,
  "title": "公共设施损坏",
  "description": "公共设施损坏",
  "address": "广东省广州市天河区",
  "latitude": 23.12463,
  "longitude": 113.36199,
  "status": "processing",
  "assignedBy": null,
  "assignedTo": "road_quality_group",
  "assignedTime": null,
  "reportImagesList": [
    {
      "createBy": null,
      "createTime": "2025-05-09 15:01:30",
      "updateBy": null,
      "updateTime": null,
      "remark": null,
      "id": 145,
      "reportId": 1,
      "imageUrl": "https://pic.arkread.com/cover/ebook/f/556906114.1740465550.jpg!cover_default.jpg"
    }
  ],
  "imageUrls": [
    "https://pic.arkread.com/cover/ebook/f/556906114.1740465550.jpg!cover_default.jpg"
  ]
}
```

---

## 工单类型接口

### GET `/city/api/types/list`

> 获取工单类型列表

### GET `/city/api/types/sublist`

> 获取工单子类型列表

---

## 文件上传接口（阿里云 OSS）

### POST `/city/api/oss/upload`

> 上传图片，返回 URL

#### 请求参数（multipart/form-data）

- 参数名：`file`

#### 返回示例

```json
{
  "msg": "上传成功",
  "code": 200,
  "data": {
    "url": "https://ccc0001.oss-cn-guangzhou.aliyuncs.com/23dd7eb0-520b-43e3-b000-275515e2569c.jpeg"
  }
}
```

---
