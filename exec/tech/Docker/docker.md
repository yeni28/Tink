# docker

## Docker Internals

- docker는 리눅스 컨네이너부터 시작된 기술
- 내부 기술에 대해 가볍게라도

## LXC ( Linux Containers )

- 단일 컴퓨팅 시스템에 설치된 리눅스 운영체제 상에서, 다른 영역과 완전히 분리된 별도의 리눅스 시스템을 운영할 수 있는  리눅스 커널 기술
- 

```
초기 docker는 LXC 기술을 기반으로 구현되었으나, 최근에는 별도 컨테이너 기술을 구현하여 사용하고 있음
```

## Docker 명령어

### docker 명령어 [ 기본 ]

- docker ps : 실행 중인 컨테이너 상태 확인
    
    ```bash
    docker ps
    ```
    
- docker rm : 컨테이너를 삭제
    
    ```bash
    docker rm [컨테이너명]
    ```
    
- docker rmi : 이미지를 삭제
    
    ```bash
    docker rmi [이미지명]
    ```
    
- docker image : 이미지 관련 명령을 할 때 사용
    
    [ 하위 명령 ]
    
    - ls : 로컬(docker process) 에 존재하는 이미지 리스트
    
    ```bash
    docker image ls
    ```
    
    [ 옵션 ]
    
    - q : ID만 나타내고 싶을 때
        
        ```bash
        docker image ls -q
        ```
        
    - rm : 이미지 삭제 [ 이미지명 or 이미지ID ]
    
    ```bash
    docker image rm [이미지명]
    ```
    
- docker container : 컨테이너 관련 명령을 할 때 사용
    
    [ 하위 명령 ]
    
    - create : 컨테이너 생성할 때
    
    ```bash
    docker container create [이미지명]
    ```
    
    - run : 컨테이너를 생성 & 실행
    
    ```bash
    docker container run [옵션] [이미지명]
    ```
    
    - start : 컨테이너 실행
    
    ```bash
    docker container start [컨테이너명]
    ```
    
    - stop : 컨테이너 실행종료
    
    ```bash
    docker container stop [컨테이너명]
    ```
    
    - pause : 컨테이너 잠시 멈춤 설정
    - unpause : 컨테이너 잠시 멈춤 해제
    - rm : 컨테이너 삭제
    
    ```bash
    docker container rm [컨테이너명]
    ```
    
- docker run : 컨테이너 생성 & 실행 [ 만약 이미지가 없다면 pull 까지 ]
    
    [ 옵션 ]
    
    - d : 백그라운드로 실행할 수 있도록 처리
    - p : 포트번호 지정
    
    ```bash
    docker run -p [host_port]:[container_port] [이미지명]
    ```
    
    ```bash
    NAPT ( Network Address Port Translation )
    ```
    
    - v : 볼륨 지정
    
    ```bash
    docker run -v [host_경로]:[container_경로] [이미지명]
    ```
    
    - i : 컨테이너의 표준 입력 스트림을 연결
    - t : 컨테이너에게 가상 터미널을 할당 ( `i` 옵션과 같이 사용함 )
        
        ```
        TTY
        teletypewriter의 약자
        리눅스(유닉스 계열)에서는 콘솔 또는 터미널을 의미
        ```
        
    - --name : 컨테이너의 이름을 지정
        
        ```bash
        docker run --name [명칭] [이미지명]
        ```
        
    - --net : 컨테이너 네트워크 지정
    - --rm : 종료와 함께 컨테이너 삭제
    - --link :
- docker build : 이미지 생성
    
    [ 옵션 ]
    
    - t : 이미지의 이름을 지정할 때, 태그를 지정해도 되고 안해도된다.
    
    ```bash
    docker build -t [이미지명] .
    ```
    
    - f : 이미지를 생성할 dockerfile 이름을 명시할 경우 사용
    
    ```bash
    docker build -t [이미지명] -f [Dockerfile명] .
    ```
    
    - --pull :

### docker 명령어 [ 세부 사항 ]

- docker network : 도커 네트워크 관련 명령을 할 때
    
    [ 하위 명령 ]
    
    - create : 네트워크 생성하기
    
    ```bash
    docker network create [네트워크명]
    ```
    
- docker volume : 도커 볼륨 관련 명령
    
    [하위 명령 ]
    
    - create : 볼륨 생성하기
    
    ```bash
    docker volume create [볼륨명]
    ```
    
- docker exec : 실행 중인 컨테이너에 내부로 들어갈 때 많이 사용한다..

### docker 명령어 [ 허브 관련 ]

- docker login : 도커허브 로그인할 때 사용
- docker logout : 도커허브 로그아웃할 때 사용
- docker pull : 도커허브에 있는 이미지를 불러올 때
    
    ```bash
    docker pull [이미지명]
    ```
    
    ```
    
    이미지명만 작성할 경우에는 해당 offical 공식이미지를 가져온다.
    	- lasted를 가옴 즉, 마지막으로 push된 버젼을 가져옵니다.
    	- 만약, 특정 버젼에 이미지를 가져오고 싶을 경우에는 [이미지명]:[태그] 형태로 작성하면 된다.
    
    특정 사용자가 올린 이미지를 가져오고 싶을 때는 [사용자명]/[이미지명] 으로 불러오면 된다.
    ```
    
- docker push : 도커허브에 이미지를 올릴 때
- docker search : 도커허브에 존재하는 이미지 검색
    
    ```bash
    docker search [이미지명]
    ```
    
    ```bash
    docker search [이미지명]:[태그명]
    ```
    

### docker 명령어 [ image 조사하기 ]

- docker history :
- docker inspect : 이미지의 상세설정을 보여줌
- docker logs : 컨테이너의 로그를 확인
- docker commit : 컨테이너의 변경사항을 새로운 이미지로 만들 때
- docker cp: 호스트PC에 있는 특정파일을 docker로 이동하거나 docker_container 파일을 호스트PC로 이동 할 때

## Dockerfile

- docker 이미지를 작성할 수 있는 기능
- dockerfile 문법으로 이미지 생성을 위한 스크립트를 작성할 수 있고
- 이를 기반으로 이미지를 생성

## Dockerfile 기본 문법

- dockerfile 은 텍스트 파일 형식이므로, 각자 사용하는 어떤 에디터로든 작성할 수 있음
- 주석 ( # )

```docker
# 안녕하세요. docker 주석입니다.
```

### Dockerfile 명령[기본]

- FROM
    - 베이스 이미지 지정 명령
    - 반드시 Dockerfile 에 작성해야 하는 명령
- LABEL
    - 작성한 이미지의 작성자 or 설명을 남길 때 많이 사용
- CMD
    - 방법#1 ( 파이썬의 배열형태로 선언 )
        
        ```docker
        CMD ["/bin/sh","-c","echo","hello"]
        ```
        
    - 방법#2 ( ENTRYPOINT 와 같이 사용하는 방식 )
        
        ```docker
        CMD ["param1","param2", ... ]
        ```
        
    - 방법#3 ( 쉘 명령 형태로 선언 )
        
        ```docker
        CMD <command> <param1> <param2> ...
        ```
        
    
    ⚠️ CMD는 Dockerfile 안에 하나만 존재해야한다.!!
    
- ENTRYPOINT
    - 
- RUN
    - 
- EXPOSE
    - Docker 컨테이너의 특정 포트를 외부에 오픈하는 설정
- ENV
    - 컨테이너 내의 환경변수를 설정
- WORKDIR
    - 컨테이너에 특정 파일경로에 들어가서 작업하고 싶을 때 사용
- COPY

### 

## docker compose 명령

- docker-compose up
- docker-compose down

## docker 명령어 모음

모든 컨테이너 삭제

```bash
docker container stop $(docker container ls -a -q)
docker rm $(docker container ls -a -q)
```

모든 이미지 삭제

```bash
docker rmi $(docker image ls -a -q)
```

사용하지 않는 이미지 삭제

```bash
docker image prune
```