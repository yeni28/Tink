# node 이미지 사용
FROM node:16.19.0 as build-stage
WORKDIR /var/jenkins_home/workspace/tink-pipeline/front
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build


#nginx 이미지를 사용합니다.
FROM nginx:stable-alpine as production-stage

#빌드했던 파일들을 nginx의 서비스 폴더로 복사함
COPY --from=build-stage /var/jenkins_home/workspace/tink-pipeline/front/dist /usr/share/nginx/html

#파일 삭제
RUN rm /etc/nginx/conf.d/default.conf

<<<<<<< HEAD
# host pc 의 nginx.conf 를 아래 경로에 복사
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
=======
# 파일 복사
COPY --from=build-stage /var/jenkins_home/workspace/tink-pipeline/front/nginx.conf /etc/nginx/conf.d/default.conf
>>>>>>> 4b7288ee17d6cf7af104e64cec1883db19d1b96f

# 포트 오픈
EXPOSE 80

# container 실행 시 자동으로 실행할 command. nginx 시작함
CMD ["nginx", "-g", "daemon off;"]

