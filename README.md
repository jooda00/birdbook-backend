# birdbook-backend
###### 📘 독서 관리 백엔드 프로그램

### 📚 개요
Bird Book은 읽고 싶은 책을 검색하고, 개인적으로 소장하며, 자신의 감상을 기록하고 공유할 수 있는 웹 서비스 입니다.

Bird Book을 통해 새로운 책과 독서 경험을 발견해보세요 😊
### 📚 Skills
| 분류             | 기술                                                     |
|----------------|--------------------------------------------------------|
| **언어 및 프레임워크** | Java 17, Spring Boot 3.3.3                             |
| **DataBase**   | PostgreSQL                                             |
| **CI**      | Github Actions                                         |
| **라이브러리**      | Spring Data JPA, Lombok, Kakao Login, Naver Search API |
| **API 문서화**    | Swagger                                                |
| **Query**      | GraphQL                                                |
 **container**      | Docker, Docker compose                                 |

### 📚 테크스펙
<details>
<summary>Kakao 로그인</summary>

### 요약
`Kakao Rest API`를 사용하여 사용자 `로그인`을 구현합니다.

### 단계
1. 사용자가 kakao 계정을 통해 로그인을 하면 `인가 코드`를 발급합니다.
   > https://kauth.kakao.com/oauth/authorize?response_type=code&client_id={client_id}&redirect_uri={redirect_uri} 에 접속
2. 발급받은 `인가 코드`를 통해 `access token`을 요청합니다.
   > /login/oauth2/code/kakao로 접속
3. `access token`을 통해 사용자 정보를 가져온 후
4. 사용자 정보로 `jwt`를 생성합니다.
3. `jwt`를 사용하여 사용자 인가를 진행합니다.


**✅ `Bearer 인증방싟`을 사용하여 해당 jwt 소유자에게 아래의 api 실행 권한을 부여합니다.**
```json
Authorization: Bearer <token>
```
</details>
<details>
<summary>Naver API를 통한 책 검색</summary>

### 요약
`Naver Search API`를 통해 책을 `검색`하는 기능을 구현합니다.

### API 응답 형식
```json
/api/books/springboot(keyword가 들어감) 형식으로 요청
{
  "items": [
    {
      "title": "스프링 부트 3.0 (프로덕션급 애플리케이션 개발 간소화)",
      "author": "그렉 턴키스트",
      "isbn": "9791161758633"
    },
    {
      "title": "실전 스프링 부트 (기본 개념부터 실무 베스트 프랙티스, 그리고 GraalVM, GraphQL, R소켓 등 최신 기술까지)",
      "author": "솜나트 무시브",
      "isbn": "9791192987354"
    },
    {
      "title": "스프링 부트 2.0 (마이크로서비스와 리액티브 프로그래밍)",
      "author": "그렉 턴키스트",
      "isbn": "9791161752624"
    }
    // 10개씩 반환
  ]
}
```
</details>
<details>
<summary>원하는 책 저장</summary>

### 요약
검색 후 소장하고 싶은 책을 DB에 `저장`하는 기능을 구현합니다.

### API 응답 형식
###### graphql을 사용하여 api를 구현합니다.
```graphql
type Mutation {
    saveBook(input: BookReq!): Book!
}
```
응답 형식은 아래 [GraphQL UI](#-graphql-ui)에서 확인하실 수 있습니다.
</details>
<details>
<summary>저장한 책에 좋아요 누르기</summary>

### 요약
마음에 드는 책에 `좋아요`를 누르는 기능을 구현합니다.

### API 응답 형식
###### graphql을 사용하여 api를 구현합니다.
```graphql
type Mutation {
   saveBookLike(input: BookLikeReq!): BookLike!
}
```
응답 형식은 아래 [GraphQL UI](#-graphql-ui)에서 확인하실 수 있습니다.

</details>
<details>
<summary>저장한 책에 관한 노트 작성</summary>

### 요약
감상을 남기고 싶은 책에 관해 `노트를 작성`하는 기능을 구현합니다.

### API 응답 형식
###### graphql을 사용하여 api를 구현합니다.
```graphql
type Mutation {
   saveNote(input: NoteReq!): Note!
}
```
응답 형식은 아래 [GraphQL UI](#-graphql-ui)에서 확인하실 수 있습니다.

</details>
<details>
<summary>작성한 노트 목록 조회</summary>

### 요약
노트 목록을 `최신순`으로 조회하는 기능을 구현합니다.

### API 응답 형식
###### graphql을 사용하여 api를 구현합니다.
```graphql
type Query {
   getNotes: [NoteRes!]!
}
```
응답 형식은 아래 [GraphQL UI](#-graphql-ui)에서 확인하실 수 있습니다.

</details>
<details>
<summary>작성한 노트 삭제</summary>

### 요약
삭제하고 싶은 노트를 `삭제하는` 기능을 구현합니다.

### API 응답 형식
graphql을 사용하여 api를 구현합니다.
```graphql
type Mutation {
   deleteNote(input: NoteDeleteReq!): Int!
}
```
응답 형식은 아래 [GraphQL UI](#-graphql-ui)에서 확인하실 수 있습니다.

</details>

### 📚 ERD
<img src="images/img_8.png" width=500 height=300 />

### 📚 Swagger UI
![img_7.png](images/img_7.png)

### 📚 GraphQL UI
<details>
<summary>책 저장</summary>

![img_1.png](images/img_1.png)

</details>


<details>
<summary>책 삭제</summary>

![img_6.png](images/img_6.png)

</details>

<details>
<summary>노트 작성</summary>

![img_2.png](images/img_2.png)

</details>

<details>
<summary>노트 목록 조회</summary>

![img_5.png](images/img_5.png)

</details>

<details>
<summary>좋아요</summary>

![img_4.png](images/img_4.png)

</details>

### 📚 실행 방법
###### 로컬 pc에 docker가 설치되어 실행되고 있음을 가정합니다.
1. 프로젝트를 clone 받습니다.
    ```shell
    git clone https://github.com/jooda00/birdbook-backend.git
    ```
2. terminal에서 밑의 명령어를 수행하여 빌드합니다.
    ```shell
    ./gradlew clean build
    ```
3. 빌드 후, docker 디렉토리로 이동하여 docker-compose.yml 파일을 실행합니다.
    ```shell
    cd docker # docker 패키지로 이동
    ```
    ```dockerfile
    docker compose up -d --build
    ```