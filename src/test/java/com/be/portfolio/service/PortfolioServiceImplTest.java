package com.be.portfolio.service;

import com.be.config.RootConfig;
import com.be.portfolio.domain.PortfolioVO;
import com.be.portfolio.dto.req.PortfolioItemReqDto;
import com.be.portfolio.dto.req.PortfolioReqDto;
import com.be.portfolio.dto.res.PortfolioItemResDto;
import com.be.portfolio.dto.res.PortfolioPortionDto;
import com.be.portfolio.dto.res.PortfolioResDto;
import com.be.portfolio.mapper.PortfolioMapper;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        RootConfig.class,
})
@Log4j
public class PortfolioServiceImplTest {

    @Autowired
    PortfolioMapper portfolioMapper;



    @Test
    public void testGetPortfolio() {
        PortfolioResDto resDto = PortfolioResDto.of(portfolioMapper.getPortfolio(1));
        System.out.println(Optional.ofNullable(resDto)
                .orElseThrow(NoSuchElementException::new));
    }

    @Test
    void getPortfolioItems() {
        System.out.println(portfolioMapper.getPortfolioItemList(1)
                .stream().map(PortfolioItemResDto::of).toList());
    }

    @Test
    void createPortfolio() {
        PortfolioReqDto reqDto = new PortfolioReqDto();
        PortfolioItemReqDto pReqDto = new PortfolioItemReqDto();
        List<PortfolioItemReqDto> itemList = new ArrayList<>();

        Date now = new Date();
        reqDto.setCreationDate(now);
        reqDto.setPortfolioName("asdf");
        reqDto.setMemberNum(1);
        reqDto.setRiskLevel(21);
        reqDto.setExpectedReturn(123);
        reqDto.setTotal(10000);

        pReqDto.setStockCode("a12");
        pReqDto.setAmount(5);
        pReqDto.setExpectedReturn(5.0);
        itemList.add(pReqDto);

        pReqDto = new PortfolioItemReqDto();
        pReqDto.setProductId(12);
        pReqDto.setAmount(50000);
        pReqDto.setExpectedReturn(7.5);
        itemList.add(pReqDto);

        reqDto.setPortfolioItems(itemList);

        reqDto.setPortfolioId(portfolioMapper.insertPortfolio(reqDto.toVo()));

        for(PortfolioItemReqDto portfolioItem : itemList) {
            portfolioItem.setPortfolioId(reqDto.getPortfolioId());
            portfolioMapper.insertPortfolioItem(portfolioItem.toVo());
        }
    }

    @Test
    void updatePortfolio() {
        PortfolioVO vo = new PortfolioVO();
        vo.setPortfolioId(1);
        vo.setTotal(120);
        vo.setExpectedReturn(101);
        vo.setRiskLevel(13);

        System.out.println(portfolioMapper.updatePortfolio(vo));
    }

    @Test
    void updateAllPortfolio() {
    }

    @Test
    void deletePortfolio() {
        System.out.println(portfolioMapper.deletePortfolio(2));
    }

    @Test
    void calculatePortfolio() {

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("005930", new int[]{79600,77000,76600,76600,76500,74700,73600,73200,73100,73900,72600,71000,71700,74700,75100,75200,74000});

            //            String strJson = "{\"005930\":[79600,77000,76600,76600,76500,74700,73600,73200,73100,73900,72600,71000,71700,74700,75100,75200,74000]}";

            ProcessBuilder processBuilder = new ProcessBuilder("python", "src/main/java/com/be/portfolio/service/test.py", jsonObject.toString());
            Process process = processBuilder.start();

            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 파이썬 플라스크 서버 연결 + 계산 + 반환
            // portfolio의 expectedReturn, riskLevel, portfolioItem의 expectedReturn 반환

            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void calculatePortion() {
        PortfolioPortionDto dto = new PortfolioPortionDto();
        List<PortfolioItemResDto> portfolioList = portfolioMapper.getPortfolioItemList(1)
                .stream().map(PortfolioItemResDto::of).toList();


        for(PortfolioItemResDto item : portfolioList) {
            System.out.println(item);
            switch(item.getProductType() == null ? "stock" : item.getProductType()) {
                case "S" -> dto.setTotalSaving(dto.getTotalSaving() + item.getAmount());
                case "F" -> dto.setTotalFund(dto.getTotalFund() + item.getAmount());
                case "B" -> dto.setTotalBond(dto.getTotalBond() + item.getAmount());
                case "stock" -> dto.setTotalStock(dto.getTotalStock() + (item.getAmount() * item.getDailyPrice()));
            }
        }

        System.out.println(dto);
    }
}