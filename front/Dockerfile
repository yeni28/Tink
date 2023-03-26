FROM node:latest as builder
WORKDIR /app
COPY ./package.json ./
RUN npm install
COPY . .
RUN npm run build


# nginx 이미지를 받는다. 실행 이미지
FROM nginx
RUN mkdir /app
# builder에서 빌드한 바이너리를 실행할 이미지로 전달해주기 위해 copy --from 옵션을 사용하여 실행 이미지로 전달한다
COPY --from=builder /app/dist /app
RUN hostname -i
COPY ./nginx.conf /etc/nginx/nginx.conf
RUN service nginx restart

