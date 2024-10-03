package com.be.stock.service;

import com.be.stock.domain.StockVO;
import com.be.stock.mapper.StockMapper;
import com.be.stock.repository.StockRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    private final OkHttpClient client = new OkHttpClient();

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.StockUrl}")
    private String StockUrl;

    @Value("${api.StockKey}")
    private String StockKey;

    int count = 0;

    // 전체 주식 데이터를 조회
    public List<StockVO> getAllStocks() {
        return stockRepository.findAll();
    }

    // stockCode 또는 stockName으로 검색하는 서비스
    public List<StockVO> searchStock(String searchTerm) {
        return stockRepository.searchByCodeOrName(searchTerm);
    }

    // 주식시세 fetch
    public void fetchStock() {
        int pageNo = 1; // 초기 페이지 번호
        int numOfRows = 1000; // 한 페이지에 가져올 데이터 수
        boolean hasNextPage = true; // 다음 페이지가 있는지 여부

        try {
            while (hasNextPage) {
                // API 호출 URL
                String url = StockUrl + "?serviceKey=" + StockKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&resultType=xml";
                System.out.println("API 호출 URL: " + url);

                // OkHttp 요청 구성
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .addHeader("Cookie", "SCOUTER=x3qrmbd76uuavd")
                        .build();

                // OkHttp 요청 실행
                Response response = client.newCall(request).execute();
                String responseBody = response.body().string();

                // 응답이 XML 형식일 경우 XML을 JSON으로 변환
                JSONObject json = XML.toJSONObject(responseBody);
                System.out.println("XML 변환된 JSON: " + json);

                if (responseBody == null) {
                    throw new RuntimeException("API 응답이 null입니다.");
                }

                if (json.has("response")) {
                    JSONObject responseObject = json.getJSONObject("response");
                    JSONObject body = responseObject.getJSONObject("body");
                    JSONArray items = body.getJSONObject("items").getJSONArray("item");
                    int totalCount = body.getInt("totalCount"); // 전체 데이터 개수
                    int totalPages = (int) Math.ceil((double) totalCount / numOfRows); // 전체 페이지 수 계산

                    List<StockVO> stockList = new ArrayList<>();

                    // 각 item 파싱 후 StockVO 객체로 변환하여 리스트에 추가
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject item = items.getJSONObject(i);
                        StockVO vo = new StockVO();

                        vo.setClpr(item.optInt("clpr", 0));
                        vo.setHipr(item.optInt("hipr", 0));
                        vo.setStockCode(item.optString("srtnCd")); // stockCode는 srtnCd로 설정
                        if (item.optString("srtnCd").equals("900110")) {
                            count++;
                        }
                        vo.setStockName(item.optString("itmsNm"));  // stockName은 itmsNm으로 설정
                        vo.setMrktCtg(item.optString("mrktCtg"));
                        vo.setDailyPrice(item.optBigDecimal("dailyPrice", BigDecimal.ZERO));  // dailyPrice 필드
                        vo.setField(item.optInt("field", 0));  // field 필드
                        vo.setVs(item.optInt("vs", 0));
                        vo.setFltRt(item.optBigDecimal("fltRt", BigDecimal.ZERO));
                        vo.setMkp(item.optInt("mkp", 0));
                        vo.setLopr(item.optInt("lopr", 0));
                        vo.setTrqu(item.optBigInteger("trqu", BigInteger.ZERO));
                        vo.setTrPrc(item.optBigInteger("trPrc", BigInteger.ZERO));
                        vo.setIstgStCnt(item.optBigInteger("lstgStCnt", BigInteger.ZERO));
                        vo.setMrktTotAmt(item.optBigInteger("mrktTotAmt", BigInteger.ZERO));

                        stockList.add(vo);  // 리스트에 추가
                    }

                    // DB에 저장
                    for (StockVO stockVO : stockList) {
                        stockRepository.insert(stockVO);
                    }

                    System.out.println(count);
                    System.out.println("페이지 " + pageNo + "의 주식 데이터가 성공적으로 저장되었습니다.");

                    // 페이지를 다 읽었는지 확인
                    if (pageNo >= totalPages) {
                        hasNextPage = false; // 모든 페이지를 가져왔으므로 반복 종료
                    } else {
                        pageNo++; // 다음 페이지로 이동
                    }
                } else {
                    System.out.println("API 응답에 'response' 키가 없습니다.");
                    hasNextPage = false; // 오류 발생 시 반복 중지
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("API 호출 중 오류 발생: " + e.getMessage());
        }
    }

    // 포트폴리오 구성 시 주식 수익률 계산에 필요한 데이터 json 객체로 반환
    public JSONObject getStockData(String stockCode) {
        LocalDate now = LocalDate.now();
        LocalDate past = now.minusYears(1);

        int pageNo = 1; // 초기 페이지 번호
        int numOfRows = 1000; // 한 페이지에 가져올 데이터 수
        boolean hasNextPage = true; // 다음 페이지가 있는지 여부
        String beginDate = "" + past.getYear() + past.getMonthValue() + past.getDayOfMonth();
        String endDate = "" + now.getYear() + now.getMonthValue() + now.getDayOfMonth();


        JSONObject json = null;
        try {
            while (hasNextPage) {
                // API 호출 URL
                String url = StockUrl + "?serviceKey=" + StockKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&beginBasDt=" + beginDate + "&endBasDt=" + endDate + "&likeSrtnCd=" + stockCode + "&resultType=xml";
                System.out.println("API 호출 URL: " + url);

                // OkHttp 요청 구성
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .addHeader("Cookie", "SCOUTER=x3qrmbd76uuavd")
                        .build();

                // OkHttp 요청 실행
                Response response = client.newCall(request).execute();
                String responseBody = response.body().string();

                // 응답이 XML 형식일 경우 XML을 JSON으로 변환
                json = XML.toJSONObject(responseBody);

                if (responseBody == null) {
                    throw new RuntimeException("API 응답이 null입니다.");
                }

                if (json.has("response")) {
                    JSONObject responseObject = json.getJSONObject("response");
                    JSONObject body = responseObject.getJSONObject("body");
                    JSONArray items = body.getJSONObject("items").getJSONArray("item");

                    JSONObject resultJson = new JSONObject();
                    JSONArray prices = new JSONArray();
                    for (int i = items.length() - 1; i >= 0; i--) {
                        JSONObject item = items.getJSONObject(i);
                        int price = item.optInt("clpr");
                        prices.put(price);
                    }

                    // 결과 JSON에 추가
                    resultJson.put(stockCode, prices);
                    System.out.println(resultJson);

                    int totalCount = body.getInt("totalCount"); // 전체 데이터 개수
                    int totalPages = (int) Math.ceil((double) totalCount / numOfRows); // 전체 페이지 수 계산
                    if (pageNo >= totalPages) {
                        hasNextPage = false; // 모든 페이지를 가져왔으므로 반복 종료
                    } else {
                        pageNo++; // 다음 페이지로 이동
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("API 호출 중 오류 발생: " + e.getMessage());
        }

        System.out.println(json);
        return json;
    }
}
