package com.be.finance.controller;

import com.be.finance.domain.FundProductVO;
import com.be.finance.service.FundProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/fund-products")  // API 엔드포인트
@CrossOrigin(origins = "http://localhost:5173")
public class FundProductController {

    private final String UPLOAD_DIR = "C:/upload";

    @Autowired
    private FundProductService fundProductService;

    // 파일 업로드 및 펀드 DB 저장 API
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "파일을 선택해주세요.");
            return "파일이 없습니다. 파일을 선택해 주세요.";
        }

        try {
            // 파일 저장 경로
            Path filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());

            // 파일 저장
            Files.write(filePath, file.getBytes());

            fundProductService.importFundProduct(filePath.toString());

            return "파일 업로드 및 DB 저장 성공!";
        } catch (IOException e) {
            e.printStackTrace();
            return "파일 업로드 실패: " + e.getMessage();
        }
    }

    // 펀드 리스트 조회 API
    @GetMapping("/list")
    public List<FundProductVO> getFundProductsList() {
        return fundProductService.getFundProductsList();  // 펀드 상품 데이터를 가져와서 반환
    }

    // 검색어 기반 펀드 상품 검색
    @GetMapping("/search")
    public ResponseEntity<List<FundProductVO>> searchFundProducts(@RequestParam String keyword) {
        List<FundProductVO> results = fundProductService.searchFundProducts(keyword);
        return ResponseEntity.ok(results);
    }
}