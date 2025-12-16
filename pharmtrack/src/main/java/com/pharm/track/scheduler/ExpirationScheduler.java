package com.pharm.track.scheduler;

import com.pharm.track.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpirationScheduler {

    private final MedicineService medicineService;

    /** 매일 00시 유통기한 체크 */
    @Scheduled(cron = "0 0 0 * * *")
    public void checkExpiration() {
        medicineService.updateExpiringStatus();
        medicineService.updateExpiredStatus();
    }
}
