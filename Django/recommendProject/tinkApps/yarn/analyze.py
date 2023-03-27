from sklearn.metrics.pairwise import cosine_similarity
import pandas as pd
import numpy as np
import json

# 코사인 유사도를 구함
def cosine_sim(yarn_matrix, user_data):
    yarn_matrix = cosine_similarity(yarn_matrix, user_data)
    return yarn_matrix


# json에서 임시로 데이터 읽어옴
def getData():
    pattern = []            # 도안 테이블

    with open("./merged_file.json", encoding="utf-8") as f:
        data = json.loads(f.read())

    for d in data:
                    # 도안 패턴 
            # 도안 패턴 
            pattern.append(
                    [
                        d["pattern"]["id"],
                        d["pattern"]["gauge"],
                        d["pattern"]["gauge_divisor"],
                        d["pattern"]["row_gauge"],
                        d["pattern"]["yardage"],
                        d["pattern"]["yardage_max"],
                        #d["pattern"]["yarn_weight_description"],
                    ]
                )
    return pattern

def recommend(patterns, userInput):
     item_df = pd.DataFrame(patterns, columns=["id", "gauge", "gauge_divisor", "row_gauge", "yardage", "yardage_max"])
     item_df = item_df.fillna(0) # 결측지 0으로 세팅
     
     cosine_matrix = cosine_sim(item_df.transpose(), userInput.transpose())
     print(cosine_matrix)
          

if __name__ == "__main__":
     #사용자 입력으로 가정
     userInput = {
          "id":[100],
          "gauge":[30.0], "gauge_divisor":[2], "row_gauge":[10.0], "yardage":[350], "yardage_max":[400]
     }
     usr_item = pd.DataFrame(userInput)
     patternList = getData()

     recommend(patternList, usr_item)
     