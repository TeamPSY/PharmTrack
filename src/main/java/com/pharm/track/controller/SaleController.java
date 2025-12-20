package com.pharm.track.controller;

import com.pharm.track.dtos.SaleDetailDto;
import com.pharm.track.dtos.SaleDto;
import com.pharm.track.service.SaleService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    /** 🔹 판매 생성 */
    @PostMapping
    public ResponseEntity<?> createSale(
            @RequestBody SaleDto saleDto,
            HttpSession session
    ) {
        Long userId = (Long) session.getAttribute("user");

        if (userId == null) {
            return ResponseEntity
                    .status(401)
                    .body("❌ userId 누락됨 — 로그인 정보가 필요합니다.");
        }

        saleDto.setUserId(userId);
        Long saleId = saleService.createSale(saleDto);
        return ResponseEntity.ok(saleId);
    }


    /** 🔹 판매 상세 조회 */
    @GetMapping("/{saleId:\\d+}")
    public ResponseEntity<SaleDetailDto> getDetail(@PathVariable Long saleId) {
        SaleDetailDto detail = saleService.getSaleDetail(saleId);
        if (detail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detail);
    }

    /** 🔹 전체 판매 목록 조회 */
    @GetMapping
    public ResponseEntity<List<SaleDetailDto>> list() {
        return ResponseEntity.ok(saleService.getSaleList());
    }
}
