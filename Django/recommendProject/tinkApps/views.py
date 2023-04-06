from django.shortcuts import render

from sklearn.metrics.pairwise import cosine_similarity
import pandas as pd
import numpy as np
from django.http import JsonResponse
from django.views.decorators.http import require_POST
import json


  
@require_POST
def recommendByYarn(request):
    
    req_data = json.loads(request.body.decode('utf-8'))
    
    patterns = []
    user_input = {}

    for i, data in enumerate(req_data):
        patternId = data['patternId']
        gauge = data['gauge']
        gauge_divisor = data['gaugeDivisor']
        row_gauge = data['rowGauge']
        yardage = data['yardage']
        yardage_max = data['yardageMax']
        pattern = {
            'patternId':patternId,
            'gauge': gauge,
            'gauge_divisor': gauge_divisor,
            'row_gauge': row_gauge,
            'yardage': yardage,
            'yardage_max': yardage_max
        }
        if i == len(req_data) - 1:
            user_input = pattern
        else:
            patterns.append(pattern)


    item_df = pd.DataFrame(patterns, columns=["patternId", "gauge", "gauge_divisor", "row_gauge", "yardage", "yardage_max"])
    item_df.set_index('patternId', inplace=True)
    item_df = item_df.fillna(0) # 결측치 0으로 세팅
    
    # 사용자 데이터
    usr_item = pd.DataFrame([user_input], columns=["patternId", "gauge", "gauge_divisor", "row_gauge", "yardage", "yardage_max"])
    usr_item.set_index('patternId', inplace=True)
    
    #print(usr_item)

    cosine_similarities = cosine_similarity(usr_item, item_df)

    # 결과 데이터 프레임 생성 및 유사도를 기준으로 내림차순 정렬
    result = pd.DataFrame({'similarity': cosine_similarities[:, 0], 'data': item_df.values.tolist()}, index=item_df.index)

    result = result[result['similarity'] > 0.5] # 유사도 0.5 이상인 데이터만 선택함

    result = result.sort_values(by='similarity', ascending=False).head(30)



    # 결과 출력
    print(len(result))

    recommendPatternId = result.index.tolist()

    return JsonResponse({'recommendPatternId': recommendPatternId})
     
