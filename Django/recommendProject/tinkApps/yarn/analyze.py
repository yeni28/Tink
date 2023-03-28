from sklearn.metrics.pairwise import cosine_similarity
import pandas as pd
import numpy as np
import json
import re

# json에서 임시로 데이터 읽어옴
def getData():
    pattern = []            # 도안 테이블

    with open("./merged_file.json", encoding="utf-8") as f:
        data = json.loads(f.read())

    for d in data:
            # 도안 패턴 
            pattern.append(
                    [
                        d["pattern"]["id"],
                        d["pattern"]["gauge"],
                        d["pattern"]["gauge_divisor"],
                        d["pattern"]["row_gauge"],
                        d["pattern"]["yardage"],
                        d["pattern"]["yardage_max"],
                        d["pattern"]["yarn_weight_description"],
                    ]
                )
    return pattern

def getId(patternList):
    patternId = []
   
    for d in patternList:        
        patternId.append(d[0])  

    return patternId   

def getColumn(patternList):
    patternData = []
    splitYarnDescrption = []
   
    for d in patternList:
        wpi = ''
        yarn_description = ''
        splitYarnDescrption = d[6]

        # 너무 작은 실들은 wpi를 0으로 사용함
        if(splitYarnDescrption.find('Thread') >= 0 or splitYarnDescrption.find('Cobweb') >= 0 or splitYarnDescrption.find('Lace') >= 0 or splitYarnDescrption.find('Light Fingering') >= 0): 
            wpi = 0
        else:
            checkNum = False
            # 숫자만 추출함
            for char in splitYarnDescrption:
                if char.isdigit():
                    checkNum = True
                    wpi = wpi + char
                
            if checkNum == False:
                print(splitYarnDescrption)
                if(splitYarnDescrption.find('Fingering')):
                    wpi = '14'
                elif(splitYarnDescrption.find('Sport')):
                    wpi = '12'
                elif(splitYarnDescrption.find('DK')):
                    wpi = '11'
                elif(splitYarnDescrption.find('Worsted')):
                    wpi = '9'
                elif(splitYarnDescrption.find('Aran')):
                    wpi = '8'
                elif(splitYarnDescrption.find('Bulky')):
                    wpi = '7'
                elif(splitYarnDescrption.find('Super Bulky')):
                    wpi = '5'
                elif(splitYarnDescrption.find('Jumbo')):
                    wpi = '2'

        print(splitYarnDescrption)
        print(wpi)
            
            # 만약 숫자가 없는 경우 단위 이름 추출해서 숫자로 치환
            
            #wpi = splitYarnDescrption.split(splitYarnDescrption.find("(")+1:splitYarnDescrption.find(")"))
            

        patternData.append(
             [
                d[1],
                d[2],
                d[3],
                d[4],
                d[5],
                yarn_description,
                wpi
             ]
        )  
    

    return patternData   

def recommend(patterns, row):
    item_df = pd.DataFrame(patterns, index=row, columns=["gauge", "gauge_divisor", "row_gauge", "yardage", "yardage_max" ,"yarn_weight_description", "wpi"])
    item_df = item_df.fillna(0) # 결측치 0으로 세팅
    
    # 사용자 데이터(임시로 지정함)
    usr_item = pd.DataFrame({
         "gauge":[22], "gauge_divisor":[3], "row_gauge":[24], "yardage":[1000], "yardage_max":[1400], "yarn_weight_description":["Fingering"], "wpi":[14]
    })

    cosine_similarities = cosine_similarity(item_df, usr_item)

    # 결과 데이터 프레임 생성 및 유사도를 기준으로 내림차순 정렬
    result = pd.DataFrame({'similarity': cosine_similarities[:, 0], 'data': item_df.values.tolist()}, index=item_df.index)
    result = result.sort_values(by='similarity', ascending=False)

    # 결과 출력
    print(result)

    # 각 데이터 확인
    # for index, row in result.iterrows():
    #     print("Data with index {}: {}".format(index, row['data']))

    # # 각 행 벡터와 사용자 입력 벡터 간의 코사인 유사도 계산
    # cosine_similarities = np.dot(item_df.to_numpy(), usr_item.to_numpy().T) / (np.linalg.norm(item_df, axis=1) * np.linalg.norm(usr_item))

    # 가장 유사한 데이터 추출
    # most_similar_index = np.argmax(cosine_similarities, axis=0)
    # most_similar_id = item_df.index[most_similar_index]
    # most_similar_data = item_df.loc[most_similar_id]
          

if __name__ == "__main__":
     patternList = getData()
     
     patternId = getId(patternList)
     patternData = getColumn(patternList)

     recommend(patternData, patternId)
     