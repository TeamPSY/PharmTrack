package com.pharm.track.controller;

import com.pharm.track.dtos.MedicineLotDto;
import com.pharm.track.model.MedicineLot;
import com.pharm.track.service.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lots")
@RequiredArgsConstructor
public class LotController {

    private final LotService lotService;

    // 약품별 로트 조회
    @GetMapping("/{medicineId}")
    public List<MedicineLot> getLots(@PathVariable Long medicineId) {
        return lotService.getLots(medicineId);
    }

    // 로트 등록
    @PostMapping
    public void addLot(@RequestBody MedicineLotDto dto) {
        lotService.addLot(dto);
    }

    // FIFO 재고 차감
    @PostMapping("/decrease/{medicineId}")
    public void decreaseLot(
            @PathVariable Long medicineId,
            @RequestParam int amount
    ) {
        lotService.decreaseStock(medicineId, amount);
    }
}
