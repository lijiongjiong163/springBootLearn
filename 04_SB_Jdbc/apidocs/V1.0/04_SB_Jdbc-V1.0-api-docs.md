> This documentation is generated by [JApiDocs](https://japidocs.agilestudio.cn/).
---
# articleController
## 新增用户

**

**请求URL**

/article `POST` 

**请求参数**

```json
    {
  "id": "int //用户id",
  "title": "string //书名",
  "content": "string //目录",
  "author": "string //作者",
  "createTime": "date //日期",
  "readers": [
    {
      "name": "string //读者名称",
      "age": "int //读者年龄"
    }
  ]
}
```
**返回结果**

```json
{
  "isok": "boolean",
  "code": "int //200、400、500",
  "message": "string",
  "data": {}
}
```
## 修改用户

**

**请求URL**

/article `PUT` 

**请求参数**

```json
    {
  "id": "int //用户id",
  "title": "string //书名",
  "content": "string //目录",
  "author": "string //作者",
  "createTime": "date //日期",
  "readers": [
    {
      "name": "string //读者名称",
      "age": "int //读者年龄"
    }
  ]
}
```
**返回结果**

```json
{
  "isok": "boolean",
  "code": "int //200、400、500",
  "message": "string",
  "data": {}
}
```
## 删除用户

**

**请求URL**

/article/{id} `DELETE` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
id|int|是|
**返回结果**

```json
{
  "isok": "boolean",
  "code": "int //200、400、500",
  "message": "string",
  "data": {}
}
```
## 查询用户

**

**请求URL**

/article/{id} `GET` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
id|int|是|
**返回结果**

```json
{
  "isok": "boolean",
  "code": "int //200、400、500",
  "message": "string",
  "data": {}
}
```
## findAll

**

**请求URL**

/articles `GET` 

**返回结果**

```json
{
  "isok": "boolean",
  "code": "int //200、400、500",
  "message": "string",
  "data": {}
}
```
# 学习js时写的测试类
## test

**

**请求URL**

/test/{id} `GET` 

**返回结果**

```json
string{}
```
## test2

**

**请求URL**

/test2/{id} `GET` 

**返回结果**

```json
string{}
```
