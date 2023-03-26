# ./Dockerfile 
FROM python:3

WORKDIR /usr/src/app

## Install packages
COPY requirements.txt ./
RUN pip install -r requirements.txt

## 현재 로컬 경로에 존재하는 모든 파일을 위의 workdir에 복사
COPY . .

## Run the application on the port 8080
EXPOSE 8000

CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]