import json
import glob

Outerwear = ['Coat / Jacket', 'Cardigan']                                   # 31
Top = ['Tops','Vest','Shrug / Bolero','Sweater']                            # 912
Bottom = ['Pants','Shorts','Leggings']                                      # 316
Homewear = ['Intimate Apparel','Soakers','Robe','Sleepwear']                # 328
Clothing_other = ['Onesies','Swimwear','Other']                             # 322
Headwear = ['Hat','Other Headwear']                                         # 411
Accessories_other = ['Belt','Feet / Legs','Hands','Neck / Torso','Other']    # 368
Bedding = ['Blanket','Pillow']                                               # 450
Kitchen = ['Cleaning','Coaster','Containers','Potholder','Table Setting']    # 481
Figure = ['Ball','Blocks']                                                   # 537
Costume_Puppet = ['Costume','Puppet']                                        # 543 Costume / Puppet

# json파일들 하나의 파일로 합치기
def combineFile():
    data = []

    for f in glob.glob("./RawData_test/*.json"):
        with open(f, encoding="utf-8") as infile:
            data.append(json.load(infile))

    with open("./merged_file.json",'w', encoding="utf-8") as outfile:
        json.dump(data, outfile, ensure_ascii=False, indent="\t")

# 카테고리 재분류
def classification(name,id):
    if name in Outerwear:
        return "Outerwear", 31
    elif name in Top:
        return "Top", 912
    elif name in Bottom:
        return "Bottom", 316
    elif name in Homewear:
        return "Homewear", 328
    elif name in Clothing_other:
        return "Other Clothing", 322
    elif name in Headwear:
        return "Headwear", 411
    elif name in Accessories_other:
        return "Other Accessories", 368
    elif name in Bedding:
        return "Bedding", 450
    elif name in Kitchen:
        return "Kitchen", 481
    elif name in Figure:
        return "Figure", 537
    elif name in Costume_Puppet:
        return "Costume / Puppet", 543
    else:
        return name, id 

# diff 계산
def calcDiff(avg, cnt):
    if avg==0 or cnt==0:
        return 0
    return float(avg*cnt)

# 데이터 정제
def refrainData():

    pattern = []            # 도안 테이블
    packs = []              # 실모음
    needle = []             # 바늘 
    catergory = []          # 카테고리
    keyword = []            # 키워드 
    member = []             # 멤버 

    patternNeedle = []      # 패턴-바늘 조인
    patternKeyword = []     # 패턴-키워드 조인
    patternThumbnail = []   # 패턴-썸네일 조인 

    with open("./key05.json", encoding="utf-8") as f:
        data = json.loads(f.read())

    # 속성 (키워드) 중복 제거 
    baby_list = ['baby', 'preemie', 'newborn', 'toddler']  #id 4 
    attribute_list = [
        'adult', 'teen', 'child','baby', 'preemie', 'newborn', 'toddler',
        'fitted','oversized-fit','petite fit','plus-fit',
        'female','male','unisex',
        'Intarsia','stranded','stripes',
        'bottom-up','top-down','short-rows','worked-flat','seamed','worked-in-the-round','seamless',
        'collar','boat-neck','crew-neck','scoop-neck','v-neck','square-neck','mock-turtle','turtle-neck',
        '3/4-length','long','short','sleeveless',
        'chart','written-pattern','video-tutorial',
        ]
    keyword.append( [4,"baby",] ) 
    added_keywordList = [] # 추가된 속성

    # 카테고리 재분류
    added_cateList = [] # 추가된 카테고리
    catergory_list = [
        'Categories',
        'Clothing',             # 대분류01
        'Coat / Jacket',        # Outerwear  (new)
        'Top',                  # Top(new)
        'Bottom',               # Bottom (new)
        'Dress',
        'Skirt',
        'Homewear',             # Homewear (new)
        'Other Clothing',       # Other(new)
        
        'Accessories',           # 대분류02
        'Headwear',              # Headwear  (new)
        'Jewelry',
        'Bag',
        'Other Accessories',     # Other(new)

        'Home',                  # 대분류03
        'Bedding',               # Bedding (new)
        'Bookmark',
        'Kitchen',               # Kitchen  (new)
        'Cozy',
        'Decorative',
        'Rug',
        'Other',

        'Toys and Hobbies',       # 대분류04
        'Figure',                 # Figure  (new)
        'Costume / Puppet',       # Costume / Puppet (new)
        'Doll Clothes',
        'Food',
        'Animal',
        'Doll', 
        'Other',

        'Pet',                    # 대분류05
        'Accessory',
        'Bedding', 
        'Clothing',
        'Toys',
        
        'Components',              # 대분류06
        'Afghan block',
        'Applique / Embellishment',
        'Chart',
        'Edging', 
        'Stitch pattern',
        'Tutorial',
        ]
    # 재분류 
    catergory.append([ 301,"Categories",2,301,])

    for d in data:
        # 도안 완성본 사진 있는 경우만 
        if len(d["pattern"]["photos"]) > 0 and d["pattern"]["download_location"] != None:

            # 카테고리
            # p = d["pattern"]["pattern_categories"][0]
            # depth = 0
            # specificCategory = ""
            # found = 0   # specificCategory 찾은 경우 1

            # while len(p) == 4 and p["name"] not in added_cateList:
            #     # 재분류 
            #     extends = p["parent"]
            #     curName, curId  = classification(p["name"], p["id"])
            #     parName, parId  = classification(extends["name"], extends["id"])
                
            #     if curName in catergory_list:
            #         if found == 0:
            #             specificCategory = curId
            #             found = 1
            #     else:
            #         p = p["parent"]
            #         continue
                
            #     if parName not in catergory_list:
            #         p = p["parent"]
            #         continue
                
            #     if curName not in added_cateList:
            #         catergory.append(
            #                 [
            #                     curId,            #카테고리 id
            #                     curName,          #카테고리name
            #                     depth,            #depth
            #                     parId,            #extends
            #                 ]
            #             )
            #     depth += 1
            #     added_cateList.append(curName)
            #     p = p["parent"]

            # 도안 썸네일 이미지
            # photoIdList = []
            # for p in d["pattern"]["photos"]:
            #     photoIdList.append(p["id"])

            #     patternThumbnail.append(
            #         [
            #             p["medium2_url"],
            #             p["thumbnail_url"],
            #             p["id"],
            #         ]
            #     )

            # 실모음 packs
            # packsIdList = []
            # for p in d["pattern"]["packs"]:
            #     if p["yarn_weight"] != None:
            #         if p["yarn"] == None:
            #             yarn_name = ""
            #             yarn_company_name = ""
            #             yarn_company_id = ""
            #         else:
            #             yarn_name = p["yarn"]["name"]
            #             yarn_company_name = p["yarn"]["yarn_company_name"]
            #             yarn_company_id = p["yarn"]["yarn_company_id"]

            #         packs.append(
            #             [
            #                 p["id"],
            #                 p["primary_pack_id"],
            #                 p["project_id"],
            #                 p["skeins"],
            #                 p["stash_id"],
            #                 p["total_grams"],
            #                 p["total_meters"],
            #                 p["total_ounces"],
            #                 p["total_yards"],
            #                 p["yarn_id"],
            #                 p["yarn_weight"]["crochet_gauge"],
            #                 p["yarn_weight"]["id"],
            #                 p["yarn_weight"]["knit_gauge"],
            #                 p["yarn_weight"]["max_gauge"],
            #                 p["yarn_weight"]["min_gauge"],
            #                 p["yarn_weight"]["name"],
            #                 p["yarn_weight"]["ply"],
            #                 p["yarn_weight"]["wpi"],
            #                 p["colorway"],
            #                 p["shop_name"],
            #                 yarn_name,
            #                 yarn_company_name,
            #                 yarn_company_id,
            #                 p["quantity_description"],
            #                 p["personal_name"],
            #                 p["dye_lot"],
            #                 p["color_family_id"],
            #                 p["grams_per_skein"],
            #                 p["yards_per_skein"],
            #                 p["meters_per_skein"],
            #                 p["ounces_per_skein"],
            #                 p["prefer_metric_weight"],
            #                 p["prefer_metric_length"],
            #                 p["shop_id"],
            #                 p["thread_size"],
            #             ]
            #         )
            #         packsIdList.append(p["id"])

            # 도안 패턴 
            # pattern.append(
            #         [
            #             d["pattern"]["id"],
            #             d["pattern"]["name"],
            #             d["pattern"]["created_at"],
            #             "",
            #             # d["pattern"]["difficulty_average"]*d["pattern"]["difficulty_count"],    # sum
            #             calcDiff(d["pattern"]["difficulty_average"], d["pattern"]["difficulty_count"]),
            #             d["pattern"]["difficulty_count"],                                       # cnt
            #             d["pattern"]["difficulty_average"],                                     # avg
            #             d["pattern"]["download_location"]["url"],
            #             d["pattern"]["gauge"],
            #             d["pattern"]["gauge_divisor"],
            #             d["pattern"]["gauge_pattern"],
            #             d["pattern"]["row_gauge"],
            #             d["pattern"]["yardage"],
            #             d["pattern"]["yardage_max"],
            #             d["pattern"]["sizes_available"],
            #             d["pattern"]["notes_html"],
            #             d["pattern"]["yarn_weight_description"],
            #             d["pattern"]["yardage_description"],
            #             d["pattern"]["craft"]["id"],
            #             d["pattern"]["craft"]["name"],
            #             d["pattern"]["craft"]["permalink"],
            #             d["pattern"]["pattern_author"]["id"],
            #             specificCategory,
            #             photoIdList,
            #             packsIdList,                            # pack id 모음 
            #         ]
            #     )
            
            # # 바늘 needle
            # for p in d["pattern"]["pattern_needle_sizes"]:

            #     needle.append(
            #         [
            #             p["id"],
            #             p["us"],
            #             p["metric"],
            #             p["us_steel"],
            #             p["crochet"],
            #             p["knitting"],
            #             p["hook"],
            #             p["name"],
            #             p["pretty_metric"],
            #         ]
            #     )

            #     patternNeedle.append(
            #         [
            #             p["id"],
            #             d["pattern"]["id"],
            #         ]
            #     )

            # 키워드
            for p in d["pattern"]["pattern_attributes"]:
                # if p['permalink'] in attribute_list and p['permalink'] not in added_keywordList:
                if p['permalink'] in attribute_list:
                    if p["permalink"] in baby_list:

                        # 패턴-키워드 조인
                        patternKeyword.append(
                            [
                                4,                                # 키워드id
                                d["pattern"]["id"],               # 패턴id
                            ]
                        )
                        # added_keywordList.append("baby")
                    else:
                        # keyword.append(
                        #     [
                        #         p["id"],
                        #         p["permalink"],
                        #     ]
                        # )

                        # 패턴-키워드 조인
                        patternKeyword.append(
                            [
                                p["id"],                                # 키워드id
                                d["pattern"]["id"],                     # 패턴id
                            ]
                        )
                        # added_keywordList.append(p["permalink"])

            # 멤버
            # p = d["pattern"]["pattern_author"]
            # member.append(
            #     [
            #         p["id"],                                
            #         p["name"],
            #         "20030303",
            #         p["name"],
            #         "user",
            #         "now()",
            #         "",
            #         1,
            #         "",
            #         "ravelry",
            #     ]
            # )

        # with open("./merged_pattern_file.json",'w', encoding="utf-8") as outfile:
            # json.dump(pattern, outfile, ensure_ascii=False, indent="\t")
        
        # with open("./merged_packs_file.json",'w', encoding="utf-8") as outfile:
        #     json.dump(packs, outfile, ensure_ascii=False, indent="\t")

        # with open("./merged_needle_file.json",'w', encoding="utf-8") as outfile:
        #     json.dump(needle, outfile, ensure_ascii=False, indent="\t")

        # with open("./merged_patternNeedle_file.json",'w', encoding="utf-8") as outfile:
        #     json.dump(patternNeedle, outfile, ensure_ascii=False, indent="\t")

        # with open("./merged_category_file.json",'w', encoding="utf-8") as outfile:
        #     json.dump(catergory, outfile, ensure_ascii=False, indent="\t")

        # with open("./merged_keyword_file.json",'w', encoding="utf-8") as outfile:
        #     json.dump(keyword, outfile, ensure_ascii=False, indent="\t")

        with open("./merged_patternKeyword_file05.json",'w', encoding="utf-8") as outfile:
            json.dump(patternKeyword, outfile, ensure_ascii=False, indent="\t")

        # with open("./merged_member_file.json",'w', encoding="utf-8") as outfile:
        #     json.dump(member, outfile, ensure_ascii=False, indent="\t")
        
        # with open("./merged_patternThumbnail_file.json",'w', encoding="utf-8") as outfile:
        #     json.dump(patternThumbnail, outfile, ensure_ascii=False, indent="\t")

if __name__ == "__main__":
    # combineFile()

    refrainData()
    print('완료.')
 
