package in.nareshit.raghu.repo;

import in.nareshit.raghu.entity.SlotRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRequestRepository extends JpaRepository<SlotRequest,Long> {

    @Modifying
    @Query("UPDATE SlotRequest SET status=:status WHERE id=:id")
    void updateSlotRequestStatus(Long id,String status);

    @Modifying
    @Query("SELECT sr FROM SlotRequest sr INNER JOIN sr.patient as patient WHERE patient.email=:patientMail")
    List<SlotRequest> getAllPatientSlots(String patientMail);
}
