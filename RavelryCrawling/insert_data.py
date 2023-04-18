import pymysql
import json


def getDbInfo():
    conn = pymysql.connect(
        host = "127.0.0.1", 	
        port=3306,
        user = "ssafy", 		
        password = "ssafy",
        database = "test",
        charset = 'utf8'
    )
    return conn


def insertData():
    db = getDbInfo()
    mycursor = db.cursor()
with open('C:\Users\multicampus\Desktop\0327RavelryCrawling\merged_needle_file.json', encoding='utf-8') as json_file:
    json_data = open("C:\Users\multicampus\Desktop\0327RavelryCrawling\merged_needle_file.json").read()
    json_obj = json.loads(json_data)

    try:
        for item in json_obj:
            print(item.get(0))
            id = item.get(0)
    
    except Exception as e:
        print("json 데이터 삽입 실패",e)
        db.close()

    
if __name__ == "__main__":
    insertData()