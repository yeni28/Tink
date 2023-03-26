from sklearn.metrics.pairwise import cosine_similarity
import pandas as pd
import numpy as np


# 코사인 유사도를 구함
def cosine_sim(yarn_matrix):
    yarn_matrix = cosine_similarity(yarn_matrix, yarn_matrix)
    return yarn_matrix


# 유사도가 가장 높은 리스트를 가져옴
def getRecommendList():
    pass

