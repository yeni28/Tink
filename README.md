
# 목차

## 0. 팀원 소개

![팀원소개](./exec/img/20230407_025916.png)

## 1. 서비스 소개



나이를 불문하고 넓은 연령대에게 사랑을 받은 뜨개질을 초심자도 쉽게 접근할 수 있도록 3D자료를 제공하고 다양한 뜨개질 도안을 추천하여 많은 사람들이 뜨개질에 접근성을 낮추기 위한 빅데이터 추천 서비스

### 타깃

- 뜨개질을 시작하는 입문반
- 다양한 도안을 추천 받고 싶은 취미반
- 모여서 뜨개질을 하고 싶은 사람들

### 주요기능

1. 컨텐츠를 기반으로 도안 추천
2. 실기반 도안 추천
3. 사용자 기반 도안 추천
4. 입문자를 위한 뜨개질 기본 기술 3D강의
5. 뜨개질 커뮤니티 기능
    5.1 자랑글 게시판
    5.2 소모임 게시판
    5.3 질문 게시판
6. 주간 베스트 기능
7. 도안 좋아요, 사용자 좋아요.

---

## 2. 개발환경

- 서버 도메인

```jsx
j8c201.p.ssafy.io
```

| 소프트웨어 | version | 상세 |
| --- | --- | --- |
| Java SE | JDK8 |  |
| Swagger | Springfox |  |
| Gradle | - |  |
| Spring boot | 2.7.x |  |
| Spring cloud | 2021.0.x aka Jubilee | https://spring.io/projects/spring-cloud |
| JPA | - |  |
| OpenFeign |  |  |
| database [mysql] | 8.0.31 |  |
| Redis | 6.x | 용도 : session 클러스터링 |
| Python |  |  |
| Django | 4.1.7 |  |
| React | 18.2.0 |  |

## 3. 빌드 방법


### gitignore

application-key.yml

![Untitled](./exec/img/20230407_022000.png)

### git 컨벤션

| 커밋유형 | 의미 |
| --- | --- |
| feat | 새로운 기능 추가 |
| fix | 버그 수정 |
| docs | 문서 수정 |
| style | 코드 formatting, 세미콜론 누락, 코드 자체의 변경이 없는 경우 |
| refactor | 코드 리팩토링 |
| test | 테스트 코드, 리팩토링 테스트 코드 추가 |
| chore | 패키지 매니저 수정, 그 외 기타 수정 ex) .gitignore |
| design | CSS 등 사용자 UI 디자인 변경 |
| comment | 필요한 주석 추가 및 변경 |
| rename | 파일 또는 폴더 명을 수정하거나 옮기는 작업만인 경우 |
| remove | 파일을 삭제하는 작업만 수행한 경우 |
| !BREAKING CHANGE | 커다란 API 변경의 경우 |
| !HOTFIX | 급하게 치명적인 버그를 고쳐야 하는 경우 |

## 4. 외부 기술
[BE]
- Spring: [security][securitylink]

[securitylink]: ./exec/tech/SPRING/Spring%20Security.md

- Spring: [jwt][jwtlink]

[jwtlink]: ./exec/tech/JWT/spring_jwt.md

- container: [docker][dockerlink]

[dockerlink]: ./exec/tech/Docker/docker.md

- CI/CD: [jenkink][jenkinslink]

[jenkinslink]: ./exec/tech/Jenkins/jenkins%2094b7ac670a1d47ae9201e58604d7ab1d.md

[FE]
- 3D tutorial : Three.js(https://threejs.org/)
- Gsap(https://greensock.com/gsap/)
- 색 조합 추천 : extract colors(https://extract-colors.namide.com/)
- simpler color(https://github.com/arnelenero/simpler-color#readme)
- 커뮤니티 : React Quill(https://github.com/zenoamaro/react-quill)
- 상태관리 : Recoil (https://recoiljs.org/)
## &#128187; 5. 사용자 인터페이스
---
### 1) 메인페이지
![image](https://github.com/yeni28/Tink/assets/79498424/e1417636-0d53-4b02-9687-84a22ae59c3c)
![메인-min](https://github.com/yeni28/Tink/assets/79498424/bbbc9d49-0477-4a49-89e9-f51d5a125cf2)


### 2) 추천페이지
- 추천 페이지 메인
![특화추천](https://github.com/yeni28/Tink/assets/79498424/b80b1fde-5c81-4c51-aaaf-ff3b26c68029)

- 키워드 기반 패턴 추천
![image](https://github.com/yeni28/Tink/assets/79498424/02a00633-1677-477f-a962-0b1ceaba410e)

- 실 정보 기반 패턴 추천
![image](https://github.com/yeni28/Tink/assets/79498424/5866faf9-cffd-43b2-afaf-5ee004afb0c1)

- 이미지 기반 색상 조합 추천
 ![image](https://github.com/yeni28/Tink/assets/79498424/b55a32e5-3b8d-46fa-a5d4-27c9056fece5)
![image](https://github.com/yeni28/Tink/assets/79498424/129938ee-b8ad-439d-8633-32c1309cd15d)
![image](https://github.com/yeni28/Tink/assets/79498424/f89d52b9-84b6-4d01-9185-305e67a4340a)

- 추천 결과 페이지
![image](https://github.com/yeni28/Tink/assets/79498424/eab12a0e-5fa3-42e7-b351-53f1508edb0d)


### 3) 튜토리얼 페이지
- 튜토리얼 페이지 메인
-![튜토리얼-min](https://github.com/yeni28/Tink/assets/79498424/445224bf-b6c8-46a8-b164-a0451a7ad38d)

- 스크롤 애니메이션을 이용한 뜨개질 설명
![튜토1-min](https://github.com/yeni28/Tink/assets/79498424/c88ceb6b-b069-45d4-ac1e-9fadaca1f4c4)

- three.js를 이용해 상호작용을 통한 뜨개질 단계 360도 확인
![튜로2-min](https://github.com/yeni28/Tink/assets/79498424/1cab2a09-3cf0-4b8a-a67e-f788e4373457)

### 4) 커뮤니티 페이지

- 도안 자랑글 작성 페이지
![image](https://github.com/yeni28/Tink/assets/79498424/be9cfcac-55fa-43a9-952f-f5e85cec8212)

- 도안 자랑글 디테일 페이지
![image](https://github.com/yeni28/Tink/assets/79498424/db41d4fc-d1c5-46ce-9898-05111a79ecda)
![image](https://github.com/yeni28/Tink/assets/79498424/fd5a1a80-a5ec-404f-bbbc-bded99ca49a1)
![image](https://github.com/yeni28/Tink/assets/79498424/787ac37b-940c-4157-b6c7-e6e307342bc0)

- 도안 리스트 페이지
![image](https://github.com/yeni28/Tink/assets/79498424/681ef69c-5afe-4c34-86c4-abc8066951c7)


- 소모임/ 질문 작성 페이지
 ![image](https://github.com/yeni28/Tink/assets/79498424/4dcc76e0-5b72-4c0f-a1af-2156f21eb3a2)

- 소모임/질문 디테일 페이지
![image](https://github.com/yeni28/Tink/assets/79498424/de727fe0-40a4-4a5d-a115-2f201924248a)

- 리스트 페이지
![image](https://github.com/yeni28/Tink/assets/79498424/64e0cf2b-d54e-403d-97e4-cb24b2aab5e4)

