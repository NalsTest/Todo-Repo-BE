# Todo-App

## Environment
- Java 8
- MySQL 5.7
- SpringBoot 2.6.6
## Note
- The status field has 3 values ​​[0,1,2] corresponding to [Planning, Doing, Complete]
- The delete function does not delete directly in the database, instead uses a flag to delete.
- Database: folder DB
## List API
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
GET /api/todo?endingDate=
GET /api/todo?starttingDate=

Example

GET /api/todo?workName=review
GET /api/todo?status=0
GET /api/todo?endingDate=2022-04-20
GET /api/todo?starttingDate=2022-04-22
```
Multi fields
```
GET /api/todo?workName=&status=
GET /api/todo?workName=&status=&startDate=&endingDate=
GET /api/todo?status=&startDate=&endingDate=

Example

GET /api/todo?workName=quiz&status=0
GET /api/todo?workName=xem&status=0&startDate=2022-04-19&endingDate=2022-04-19
GET /api/todo?status=0&startDate=2022-04-19&endingDate=2022-04-19
```
Beetwen date
```
GET /api/todo/startingDate?from=&to=
GET /api/todo/endingDate?from=&to=

Example

GET /api/todo/startingDate?from=2022-04-20&to=2022-04-30
GET /api/todo/endingDate?from=2022-04-20&to=2022-04-30
```
**Paging**
Use page and optionally size to paginate returned data.
```
GET /api/todo/startingDate?page=&size=

Example

GET /api/todo/startingDate?page=0&size=10
```
**Sort**
```
GET /api/todo/startingDate?sort=work_name,DESC
GET /api/todo/startingDate?sort=work_name,DESC&sort=ending_date,ASC
```
*Example*
```
GET /api/todo?startingDate=2022-04-28&endingDate=2022-04-28&status=1&workName=&page=0&size=10&sort=work_name,ASC
```