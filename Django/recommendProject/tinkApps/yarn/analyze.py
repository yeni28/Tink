from sklearn.metrics.pairwise import cosine_similarity
import pandas as pd
import numpy as np
from django.http import JsonResponse
from django.views.decorators.http import require_POST
import json


  
@require_POST
def recommendYarnByUser(request):

    req_data = json.loads(request.body)
    patterns = req_data['patterns']
    user_input = req_data['user_input']

    item_df = pd.DataFrame(patterns, columns=["gauge", "gauge_divisor", "row_gauge", "yardage", "yardage_max" , "wpi"])
    item_df.set_index('pattern_id', inplace=True)
    item_df = item_df.fillna(0) # 결측치 0으로 세팅
    
    # 사용자 데이터(임시로 지정함)
    usr_item = pd.DataFrame(user_input, columns=["gauge", "gauge_divisor", "row_gauge", "yardage", "yardage_max" , "wpi"])

    cosine_similarities = cosine_similarity(item_df, usr_item)

    # 결과 데이터 프레임 생성 및 유사도를 기준으로 내림차순 정렬
    result = pd.DataFrame({'similarity': cosine_similarities[:, 0], 'data': item_df.values.tolist()}, index=item_df.index)
    result = result.sort_values(by='similarity', ascending=False)

    # 결과 출력
    print(result)

    recommendPatternId = result.index.tolist()

    return JsonResponse({'recommendPatternId': recommendPatternId})


if __name__ == "__main__":
    pass     

     