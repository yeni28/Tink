import json
import os


if __name__ == "__main__":
    start = 899479  # 시작 id
    end = 899479    # 종료 id (포함)
    for id in range(start, end + 1):
        file_path = f'./RawData/Ravelry{id}.json'
        if os.path.isfile(file_path):

            with open(file_path, 'r') as file:
                data = json.load(file).get('pattern')
            if not data.get('downloadable'):
                os.remove(file_path)
                continue
            languages = data.get('languages')
            for language in languages:
                if language.get('code') == 'en' or language.get('code') == 'kr':
                    break
            else:
                os.remove(file_path)
    print('삭제 완료 ㅎㅎ')
