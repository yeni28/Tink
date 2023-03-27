
FROM node:16.19.0 as build-stage
WORKDIR /var/jenkins_home/workspace/tink-pipeline/front/
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

#nginx 이미지를 사용합니다. 뒤에 tag가 없으면 latest 를 사용.
FROM nginx as production-stage

COPY --from=build-stage /var/jenkins_home/workspace/tink-pipeline/front/build /usr/share/nginx/html

RUN rm /etc/nginx/conf.d/default.conf

COPY --from=build-stage /var/jenkins_home/workspace/tink-pipeline/front/nginx.conf /etc/nginx/conf.d/

# 포트 오픈
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
 