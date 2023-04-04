package com.ssafy.tink.service;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ssafy.tink.db.entity.YarnSale;
import com.ssafy.tink.db.repository.YarnSaleRepository;

@Service
public class CrawlingService {

	@Autowired
	private YarnSaleRepository yarnSaleRepository;

	private final String sevyUrl = "https://www.sevy.co.kr/";

	public void run() {

		for(int page = 1; page <= 8; page++){
			String listUrl = null;
			if(page == 1){
				listUrl = sevyUrl + "/goods/goods_list.php?cateCd=001";
			}else{
				listUrl = sevyUrl + "/goods/goods_list.php?page=" + page + "&cateCd=001";
			}

			Connection connection = Jsoup.connect(listUrl);

			try{
				Document document = connection.get();
				Elements imageUrlElement = document.select(".item_photo_box > a");

				for(Element element : imageUrlElement){
					String linkUrl = element.attr("href");
					String goodsNo = linkUrl.split("goodsNo=")[1].split("&")[0];//상품 번호 얻어옴
					String innerUrl = sevyUrl + "goods/goods_view.php?goodsNo=" + goodsNo;

					Connection innerConnection = Jsoup.connect(innerUrl);
					Document innerDocument = innerConnection.get();

					Element priceElement = innerDocument.selectFirst(".item_price > dd > strong > strong");



					Elements imgElements = innerDocument.select(".img_photo_big > img");

					int price = priceElement == null ? 0 : Integer.parseInt(priceElement.text().replaceAll(",", ""));

					String ingredient = null;//성분
					String length = null;//길이
					String origin = null;//원산지
					String url = innerUrl;//사이트 경로
					String company = null;//제조사
					String needle = null;//추천 바늘
					String gram = null;//단위 중량
					String imgUrl = imgElements.attr("src");//이미지 url


					Elements detailInfoElements = innerDocument.select(".item_tit_detail_cont > .item_detail_list dl dt");

					for(Element getElement : detailInfoElements){
						String textElement = getElement.text();

						if(textElement.equals("단위 중량,길이")){
							Element next = getElement.nextElementSibling();
							System.out.println(next.text());
							String[] parse = next.text().split(",");

							if (parse.length >= 2) {
								gram = parse[0].trim();
								length = parse[1].trim();
							}
						}else if(textElement.equals("성분")){
							ingredient = getElement.nextElementSibling().text();
						}else if(textElement.equals("원산지")){
							origin = getElement.nextElementSibling().text();
						}else if(textElement.equals("제조사")){
							company = getElement.nextElementSibling().text();
						}else if(textElement.equals("추천바늘")){
							needle = getElement.nextElementSibling().text();
						}
					}

					if(gram == null || price == 0) continue;//데이터로 사용안함

					YarnSale yarnSale = YarnSale.builder()
						.len(length)
						.gram(gram)
						.company(company)
						.origin(origin)
						.url(url)
						.img(imgUrl)
						.ingredient(ingredient)
						.price(price)
						.needle(needle)
						.build();

					yarnSaleRepository.save(yarnSale);

				}

			}catch (IOException e){
				e.printStackTrace();

			}

		}
	}


}
