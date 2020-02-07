# Playlist API 정의서
## 1.개요
본 문서는 playlist 기능을 연동하기 위한 API 연동 정의서입니다.
### 1.1 연동방식
연동방식은 실시간으로 처리되며 데이터를 처리 및 가공합니다.
1. protocal : http or https
2. content-type : application/json
3. charset : UTF-8
### 1.2 유형
API를 연동하기 위한 호출 방식 유형은 Restful이며 다음과 같다.
1. URL : {PROTOCAL}://{DOMAIN}:{PORT}/{URI}?{PARAMETER}
    - PROTOCAL : http or https
    - DOMAIN : 사전 정의된 domain
    - PORT : 사전 정의된 port
    - URI : 정의된 접근 가능한 리소스
    - PARAMETER : 정의된 파라미터 (restful에서는 거의 사용 안함)
2. Request body : JSON
3. Response body : JSON
                                           
## 2.API
### 2.1 공통 영역
#### 2.1.1 응답 구조
API 호출에 대한 응답 Json구조는 다음과 같습니다.
1. Json 구조
~~~
{
    "meta" : {
        "path" : "/api/playList/1",
        "status" : 200,
        "message" : "OK",
        "timestamp" : "2020-02-07T13:58:46.732",
        "errorMap" : null
    },
    "body" : {}
}
~~~
2. 설명 

| name | type | desc |
| --- | --- | --- |
| meta | object |  해당 API에 대한 공통 메타 데이터 정보  |
| - path | string |  호출 URI  |
| - status | int | 응답 결과 (http 응답 코드)   |
| - message | string | 결과에 대한 응답이며 성공시 http 응답코드 메시지, 실패시 실패에 대한 메시지가 정의됨.   |
| - timestamp | string | 응답완료 시간 (pattern : yyyy-MM-dd'T'HH:mm:ss.SSS) |
| - errorMap | object | 오류 발생시 각 발생한 오류 데이터에 대한 정보 |
| body | object | 성공시 반환되는 응답 Json 데이터. |

### 2.2 playlist 목록 조회
생성된 playlist 목록을 조회하는 API입니다.
1. Request URI : [GET] /playList/{id}

| name | type | value |
| --- | --- | --- |
| id | long | {Required} 목록 요청 사용자 ID |
2. Response Status : 200 OK
3. Response Body : 

| name | type | value |
| --- | --- | --- |
| playListId | long |  play list ID |
| playListName | string |  play list 이름 |
~~~
{
    "meta": {
        "path": "/playList/1",
        "status": 200,
        "message": "OK",
        "timestamp": "2020-02-07T14:38:13.492"
    },
    "body": [
        {
            "playListId": 1,
            "playListName": "My Album - 4"
        }
    ]
}
~~~
### 2.3 playlist 등록된 곡 조회
선택한 playlist에 등록된 곡을 조회하는 API입니다.
1. Request URI : [GET] /playList/{id}/{playListId}

| name | type | value |
| --- | --- | --- |
| id | long | {Required} 목록 요청 사용자 ID |
| playListId | long | {Required} 선택한 playlist ID |
2. Response Status : 200 OK
3. Response Body : List형

| name | type | value |
| --- | --- | --- |
| songId | long |  곡 ID |
| title | string |  곡 이름 |
| playListId | long | 곡이 등록된 playlist ID |
| length | int |  곡 길이 |
| track | int |  앨범 트랙 번호 |
| createdDate | string | 곡 등록일 (yyyy-MM-dd'T'HH:mm:ss.SSS) (실제 곡 원본 등록일) |
| modifiedDate | string |  곡 수정일 (yyyy-MM-dd'T'HH:mm:ss.SSS) (실제 곡 원본 수정일) |
~~~
{
    "meta": {
        "path": "/playList/1/1",
        "status": 200,
        "message": "OK",
        "timestamp": "2020-02-07T14:42:55.311"
    },
    "body": [
        {
            "songId": 45,
            "title": "Rubber Soul (1965.12) song-1",
            "length": 166,
            "track": 1,
            "createdDate": "2020-02-06T13:49:04.000",
            "modifiedDate": "2020-02-06T13:49:04.000",
            "playListId": 1
        },
        {
            "songId": 46,
            "title": "Rubber Soul (1965.12) song-2",
            "length": 113,
            "track": 2,
            "createdDate": "2020-02-06T13:49:04.000",
            "modifiedDate": "2020-02-06T13:49:04.000",
            "playListId": 1
        },
        {
            "songId": 47,
            "title": "Rubber Soul (1965.12) song-3",
            "length": 377,
            "track": 3,
            "createdDate": "2020-02-06T13:49:04.000",
            "modifiedDate": "2020-02-06T13:49:04.000",
            "playListId": 1
        }
    ]
}
~~~
### 2.4 playlist 등록
playlist에 곡을 등록하기 위한 API입니다.
1. Request URI : [POST] /playList
2. Request Header : 

| name | type | value |
| --- | --- | --- |
| content-type | string |  application/json, charset=UTF8  |
3. Request Body : 

| name | type | desc |
| --- | --- | --- |
| id | long | {Required}  사용자 ID |
| playListName | string | {Optional} play list 이름, 신규 생성시 필수. 기존 play list에 곡 추가시 이름을 지정하면 play list 이름이 수정됨.  |
| playListId | long | {Optional} 곡을 추가할 기존 생성된 playList ID, null이면 신규생성.  |
| albumId | long | {Optional} 선택한 앨범 ID  |
| songs | list<long> | {Optional} 선택한 곡 ID  |
~~~
{
    "id" : 1,
    "playListName" : "My Album",
    "playListId" : 1,
    "albumId" : 5,
    "songs" : [1, 2, 3, 4]
}
~~~
4. Response status : 201 Created
5. Response Body : 
~~~
{
    "meta": {
        "path": "/playList",
        "status": 201,
        "message": "CREATED",
        "timestamp": "2020-02-07T14:35:18.459"
    }
}
~~~
### 2.5 playlist 삭제
1. Request URI : [DELETE] /playList
2. Request Header : 

| name | type | value |
| --- | --- | --- |
| content-type | string |  application/json, charset=UTF8  |
3. Request Body : 

| name | type | desc |
| --- | --- | --- |
| id | long | {Required}  사용자 ID |
| playListId | long | {Required} 삭제를 원하는 playList ID, playList와 등록된 모든 곡을 삭제함 |
~~~
{
    "id" : 1,
    "playListId" : 1
}
~~~
4. Response Status : 200 OK
5. Response Body : 
~~~
{
    "meta": {
        "path": "/playList",
        "status": 200,
        "message": "OK",
        "timestamp": "2020-02-07T14:55:42.727"
    }
}
~~~
### 2.6 playlist 곡 삭제
1. Request URI : [DELETE] /playList/song
2. Request Header : 

| name | type | value |
| --- | --- | --- |
| content-type | string |  application/json, charset=UTF8  |
3. Request Body : 

| name | type | desc |
| --- | --- | --- |
| id | long | {Required}  사용자 ID |
| playListId | long | {Required} 삭제를 원하는 playList ID, playList와 등록된 모든 곡을 삭제함 |
| songIds | list | {Optional} 삭제를 원하는 playList 의 곡 ID, 없으면 모든 곡 삭제.|
~~~
{
    "id" : 1,
    "playListId" : 1,
    "songIds" : [1, 3, 5]
}
~~~
4. Response Status : 200 OK
5. Response Body : 
~~~
{
    "meta": {
        "path": "/playList",
        "status": 200,
        "message": "OK",
        "timestamp": "2020-02-07T14:55:42.727"
    }
}
~~~
## 3.ERROR
서비스중 내부적으로 발생하는 모든 오류 응답은 Http Status 오류코드를 발생시키며 응답 Body의 meta정보에 오류에 대한 정보를 담습니다.
### 3.1 오류 응답 status
1. http status : 200 OK 혹은 201 Created를 제외한 모든 코드.
2. Response Body :
~~~
{
    "meta": {
        "path": "/api/playList",
        "status": 400,
        "message": "[id] 사용자 ID는 필수입니다.\n[playListName] 이름은 필수입니다.\n",
        "timestamp": "2020-02-07T15:04:28.144",
        "errorMap": {
            "playListName": [
                "이름은 필수입니다."
            ],
            "id": [
                "사용자 ID는 필수입니다."
            ]
        }
    }
}
~~~

- message : 발생한 오류에 대한 간략한 메시지를 표시합니다.
- errorMap : 속성값에 대한 Validation오류 발생시 생성되는 정보이며 다음과 같은 구조입니다.
~~~
    {
        "fieldName" : ["error message1", "error message2"],
        "fieldName2" : ["error message1", "error message2"],
        ...
    }
~~~

   - fieldName : 요청 파라미터의 파라미터 명
   - error message : 해당 파라미터의 검증오류 메시지. 