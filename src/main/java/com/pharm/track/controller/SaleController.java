package com.pharm.track.controller;

import com.pharm.track.dtos.SaleDetailDto;
import com.pharm.track.dtos.SaleDto;
import com.pharm.track.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    /** 🔹판매 생성 */
    @PostMapping
    public ResponseEntity<?> createSale(@RequestBody SaleDto saleDto) {

        // ⭐ userId는 반드시 필요 → 없으면 400 + 메시지 반환
        if (saleDto.getUserId() == null) {
            return ResponseEntity
                    .badRequest()
                    .body("❌ userId 누락됨 — 로그인 정보가 필요합니다.");
        }

        // ⭐ createSale 내부에서 이미 재고 차감이 실행됨
        Long saleId = saleService.createSale(saleDto);

        // 최종 응답: saleId 반환
        return ResponseEntity.ok(saleId);
    }

    /** 🔹판매 상세 조회 */
    @GetMapping("/{saleId}")
    public ResponseEntity<SaleDetailDto> getDetail(@PathVariable Long saleId) {
        SaleDetailDto detail = saleService.getSaleDetail(saleId);
        if (detail == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(detail);
    }

    /** 🔹전체 판매 목록 조회 */
    @GetMapping
    public ResponseEntity<List<SaleDetailDto>> list() {
        return ResponseEntity.ok(saleService.getSaleList());
    }
}
