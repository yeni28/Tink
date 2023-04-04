package com.ssafy.tink.service;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CrawlingService {


	private static String sevyUrl = "https://www.sevy.co.kr/";
	String sevyViewUrl = "";

	public static void getCrawling() throws IOException {

		for(int page = 1; page <= 8; page++){
			String listUrl = "/goods/goods_list.php?cateCd=";
			Connection connection = Jsoup.connect(sevyUrl + listUrl + page);

			try{
				Document document = connection.get();
				Elements imageUrlElement = document.select(".item_photo_box > a");

				for(Element element : imageUrlElement){
					String linkUrl = element.attr("href");
					System.out.println(linkUrl);
					String innerUrl = sevyUrl + linkUrl;

					Connection innerConnection = Jsoup.connect(innerUrl);
					Document innerDocument = innerConnection.get();

					Element priceElement = innerDocument.selectFirst(".item_delivery > dd > p > strong");
					System.out.println(priceElement);

					Elements detailInfoElements = innerDocument.select(".item_detail_list dd dl dt");

					Element ingredientElement;//성분
					Element lengthElement;//길이
					Element originElement;//원산지
					String urlElement = innerUrl;//사이트 경로
					Element companyElement;//제조사
					Element needleElement;//추천 바늘
					Element gramElement;//단위 중량
					Element imgElement;//이미지 url

					for(Element getElement : detailInfoElements){
						String textElement = getElement.text();

						if(textElement.equals("단위 중량,길이")){
							Element next = getElement.nextElementSibling();
						}else if(textElement.equals("성분")){
							ingredientElement = getElement.nextElementSibling();
						}else if(textElement.equals("원산지")){
							originElement = getElement.nextElementSibling();
						}else if(textElement.equals("제조사")){
							companyElement = getElement.nextElementSibling();
						}else if(textElement.equals("추천바늘")){
							needleElement = getElement.nextElementSibling();
						}
					}

				}

			}catch (IOException e){
				throw new IOException();
			}

		}
	}


}
