import requests
import base64
import json


def patternRequest(username, password, id):
    header_string = username + ":" + password
    auth_header_bytes = base64.urlsafe_b64encode(header_string.encode("utf-8"))
    auth_header = str(auth_header_bytes, "utf-8")
    headers = {"Authorization": "Basic " + auth_header}

    url = f"https://api.ravelry.com/patterns/{id}.json"

    response = requests.get(url, headers=headers)
    status_code = response.status_code
    # is_free = True
    if status_code == 200:
        data = response.json()
        pattern = data.get('pattern')

        if pattern.get('free') and pattern.get('downloadable'):
            for language in pattern.get('languages'):
                if language.get('code') == 'en' or language.get('code') == 'kr':
                    with open(f'./RawData/Ravelry{id}.json', 'w') as f:
                        json.dump(data, f, ensure_ascii=True, indent=4)
                    break

            return pattern
        # else:
        #     is_free = False

    elif status_code == 403:
        print(f'※ api키가 유효하지 않습니다. ({status_code})')
    # elif status_code == 404:
    #     print(f'※ {id}번 패턴은 존재하지 않습니다. ({status_code})')
    # elif not is_free:
    #     print(f'※ 유료 도안입니다. ({status_code})')
    elif status_code != 200 and status_code != 404:
        print(f'※ status code를 확인해주세요. ({status_code})')

    return False


def patternParse(pattern, id):

    with open("./ParsedData/data.json", "r") as st_json:
        data = json.load(st_json)

    with open(f'./ParsedData/data.json', 'w') as f:
        parsedPattern = dict()
        parsedPattern['id'] = id
        parsedPattern['created_at'] = pattern.get('created_at')
        data.get('patterns').append(parsedPattern)
        json.dump(data, f, ensure_ascii=True, indent=4)


if __name__ == "__main__":
    start = 408403  # 시작 id
    end = 410000    # 종료 id (포함)
    api_username = "da8a38ce8cc728b937faf2c6f3e27155"           # api 키 사용자이름
    api_password = "1NpDoTTnaB-8bhYYndwnyRnvVF55mES_l3JNPJu8"                     # api 키 비밀번호
    for id in range(start, end + 1):
        pattern = patternRequest(api_username, api_password, id)
        # if pattern:
        #     patternParse(pattern, id)
    print('데이터 수집을 완료했습니다.')
