from django.db import models

# Create your models here.(db관련된 파일)

from django.db import models


# 사진 테이블
class Thumbnail(models.Model):
    imageId = models.IntegerField(primary_key=True, db_column='image_id')
    mainImg = models.CharField(max_length=255, db_column='main_img')
    thumbImg = models.CharField(max_length=255, db_column='thumb_img')


# 회원 테이블
class Member(models.Model):
    memberId = models.IntegerField(primary_key=True, db_column='member_id')
    email = models.CharField(max_length=50)
    birth = models.DateField()
    nickname = models.CharField(max_length=15)
    role = models.CharField(max_length=10)
    createdAt = models.DateTimeField(auto_now_add=True, db_column='created_at')
    updatedAt = models.DateTimeField(auto_now=True, db_column='updated_at')
    status = models.IntegerField(max_length=2)
    imageId = models.OneToOneField(Thumbnail, db_column='image_id')


# 도안 카테고리 테이블
class Category(models.Model):
    categoryId = models.IntegerField(primary_key=True, db_column='category_id')
    categoryName = models.CharField(max_length=50)
    depth = models.IntegerField()
    parentCategory = models.ForeignKey('self', db_column='parent_id')


# 도안 테이블
class Pattern(models.Model):
    patternId = models.IntegerField(primary_key=True, db_column='pattern_id')
    memberId = models.ForeignKey(
        Member, primary_key=True, db_column='member_id')
    name = models.CharField(max_length=50)
    createdAt = models.DateTimeField(auto_now_add=True, db_column='created_at')
    updatedAt = models.DateTimeField(auto_now=True, db_column='updated_at')
    difficultySum = models.IntegerField()
    difficultyCnt = models.IntegerField()
    downloadUrl = models.CharField(max_length=150, db_column='download_url')
    gauge = models.FloatField()
    gaugeDivisor = models.IntegerField(db_column='gauge_divisor')
    gaugePattern = models.CharField(max_length=50, db_column='gauge_pattern')
    rowGauge = models.FloatField(db_column='row_gauge')
    yardage = models.FloatField()
    yardageMax = models.IntegerField(db_column='yardage_max')
    sizesAvailable = models.CharField(
        max_length=20, db_column='sizes_available')
    notesHtml = models.CharField(max_length=255, db_column='notes_html')
    yarnWeightDescription = models.CharField(
        max_length=25, db_column='yarn_weight_description')
    yardage_description = models.CharField(
        max_length=100, db_column='yardage_description')
    imageId = models.OneToOneField(Thumbnail, db_column='image_id')
    categoryId = models.ForeignKey(Category, db_column='category_id')
    needle = models.ManyToManyField('Yarn')
    yarn = models.ManyToManyField('Needle')


# 바늘 테이블
class Needle(models.Model):
    needleId = models.IntegerField(primary_key=True, db_column='kneedle_id')
    us = models.IntegerField()
    metric = models.FloatField()
    usSteel = models.CharField(max_length=255, db_column='us_steel')
    crochet = models.BooleanField()
    knitting = models.BooleanField()
    hook = models.CharField(max_length=10)
    name = models.CharField(max_length=30)
    prettyMetric = models.CharField(max_length=10, db_column='pretty_metric')


# 실 테이블
class Yarn(models.Model):
    yarnId = models.IntegerField(primary_key=True, db_column='yarn_id')
    yarnName = models.CharField(max_length=40, db_column='yarn_name')
    primaryPackId = models.CharField(
        max_length=45, db_column='primary_pack_id')
    projectId = models.CharField(max_length=45, db_column='project_id')
    skeins = models.CharField(max_length=45, db_column='skeins')
    stashId = models.CharField(max_length=45, db_column='stash_id')
    totalGrams = models.CharField(max_length=45, db_column='total_grams')
    totalMeters = models.CharField(max_length=45, db_column='total_meters')
    totalOunces = models.CharField(max_length=45, db_column='total_ounces')
    totalYards = models.CharField(max_length=45, db_column='total_yards')
    yarn_id = models.CharField(max_length=45, db_column='yarn_id')
    yarnName = models.CharField(max_length=45, db_column='yarn_name')
    yarnWeight = models.CharField(max_length=45, db_column='yarn_weight')

    yarnCrochetGauge = models.CharField(
        max_length=45, db_column='yarn_weight_crochet_gauge')
    yarnWeightKnitGauge = models.CharField(
        max_length=45, db_column='yarn_weight_knit_gauge')
    yarnWeightMaxGauge = models.CharField(
        max_length=45, db_column='yarn_weight_max_gauge')
    yarnWeightMinGauge = models.CharField(
        max_length=45, db_column='yarn_weight_min_gauge')
    yarnWeightName = models.CharField(
        max_length=45, db_column='yarn_weight_name')
    yarnWeightPly = models.CharField(
        max_length=45, db_column='yarn_weight_ply')
    yarnWeightWpi = models.CharField(
        max_length=45, db_column='yarn_weight_wpi')

    colorway = models.CharField(max_length=45, db_column='colorway')
    shopName = models.CharField(max_length=45, db_column='shop_name')

    yarnPermalink = models.CharField(max_length=45, db_column='yarn_permalink')
    yarnYarnCompanyName = models.CharField(
        max_length=45, db_column='yarn_yarn_company_name')
    yarnYarnCompanyId = models.CharField(
        max_length=45, db_column='yarn_yarn_company_id')

    quantityDescription = models.CharField(
        max_length=45, db_column='quantity_description')
    personalName = models.CharField(max_length=45, db_column='personal_name')
    dyeLot = models.CharField(max_length=45, db_column='dye_lot')
    colorFamilyId = models.CharField(
        max_length=45, db_column='color_family_id')

    gramsPerSkein = models.FloatField(db_column='grams_per_skein')
    yardsPerSkein = models.FloatField(db_column='yards_per_skein')
    metersPerSkein = models.FloatField(db_column='meters_per_skein')
    ouncesPerSkein = models.FloatField(db_column='ounces_per_skein')
    preferMetricWeight = models.BooleanField(db_column='prefer_metric_weight')
    preferMetricLength = models.BooleanField(db_column='prefer_metric_length')

    shopId = models.CharField(max_length=45, db_column='shop_id')
    threadSize = models.CharField(max_length=45, db_column='thread_size')
