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
    public ResponseEntity<?> create(@RequestBody SaleDto saleDto) {

        // ⭐ React에서 로그인 후 userId를 보내줘야 함
        if (saleDto.getUserId() == null) {
            return ResponseEntity.badRequest().body("❌ userId 누락됨 — 로그인 정보가 필요합니다.");
        }

        Long saleId = saleService.createSale(saleDto);
        return ResponseEntity.ok(saleId);   // { "saleId": 3 } 이런식으로 응답됨
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
