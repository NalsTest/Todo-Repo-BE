# Todo-App

##Environment
- Java 8
- MySQL 5.7
- SpringBoot 2.6.6
##Note
- The status field has 3 values ​​[0,1,2] corresponding to [Planning, Doing, Complete]
- The delete function does not delete directly in the database, instead uses a flag to delete.
##List API
**Add**
```
POST /api/todo/

body
{
    "workName": "Review bài tập",
    "startingDate": "2022-04-27",
    "endingDate": "2022-04-27"
}
```
**Edit**
```
PATCH /api/todo/{id}/edit

body
{
    "id": 1,
    "workName": "Review bài tập",
    "startingDate": "2022-04-27",
    "endingDate": "2022-04-27",
    "status": 2
}
```
**Delete**
```
PATCH /api/todo/{id}/delete
```
**Get by id**
```
GET /api/todo/{id}
```
**Get all work**
```
GET /api/todo
```
**Filter**
```
GET /api/todo?workName=
GET /api/todo?status=
GET /api/todo?status=
```