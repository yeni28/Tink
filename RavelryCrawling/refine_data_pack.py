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

    with open("./files/0328/merged_file_v2.json", encoding="utf-8") as f:
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
            p = d["pattern"]["pattern_categories"][0]

            # 실모음 packs
            packsIdList = []
            for p in d["pattern"]["packs"]:
                if p["yarn_weight"] != None:
                    if p["yarn"] == None:
                        yarn_name = ""
                        yarn_company_name = ""
                        yarn_company_id = ""
                    else:
                        yarn_name = p["yarn"]["name"]
                        yarn_company_name = p["yarn"]["yarn_company_name"]
                        yarn_company_id = p["yarn"]["yarn_company_id"]

                    packs.append(
                        [
                            p["id"],
                            p["primary_pack_id"],
                            p["project_id"],
                            p["skeins"],
                            p["stash_id"],
                            p["total_grams"],
                            p["total_meters"],
                            p["total_ounces"],
                            p["total_yards"],
                            p["yarn_id"],
                            p["yarn_weight"]["crochet_gauge"],
                            p["yarn_weight"]["id"],
                            p["yarn_weight"]["knit_gauge"],
                            p["yarn_weight"]["max_gauge"],
                            p["yarn_weight"]["min_gauge"],
                            p["yarn_weight"]["name"],
                            p["yarn_weight"]["ply"],
                            p["yarn_weight"]["wpi"],
                            p["colorway"],
                            p["shop_name"],
                            yarn_name,
                            yarn_company_name,
                            yarn_company_id,
                            p["quantity_description"],
                            p["personal_name"],
                            p["dye_lot"],
                            p["color_family_id"],
                            p["grams_per_skein"],
                            p["yards_per_skein"],
                            p["meters_per_skein"],
                            p["ounces_per_skein"],
                            p["prefer_metric_weight"],
                            p["prefer_metric_length"],
                            p["shop_id"],
                            p["thread_size"],
                            d["pattern"]["id"],
                        ]
                    )
                    packsIdList.append(p["id"])

        with open("./merged/merged_packs_file.json",'w', encoding="utf-8") as outfile:
            json.dump(packs, outfile, ensure_ascii=False, indent="\t")

if __name__ == "__main__":
    # combineFile()
    refrainData()
    print('완료.')
 
