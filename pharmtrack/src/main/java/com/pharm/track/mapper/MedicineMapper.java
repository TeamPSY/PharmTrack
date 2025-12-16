package com.pharm.track.mapper;

import com.pharm.track.model.Medicine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MedicineMapper {

    /** 🔹전체 조회 */
    List<Medicine> findAll();

    /** 🔹유통기한 임박 조회 */
    List<Medicine> findExpiringSoon();

    /** 🔹유통기한 지남 조회 */
    List<Medicine> findExpired();

    /** 🔹단건 조회 */
    Medicine findById(Long id);

    /** 🔹상태 변경 (NORMAL / LOW / ALERT 등) */
    void updateStatus(@Param("id") Long id, @Param("status") String status);

    /** 🔹등록 */
    void insert(Medicine medicine);

    /** 🔹수정 */
    void update(Medicine medicine);

    /** 🔹삭제 */
    void delete(Long id);
}
