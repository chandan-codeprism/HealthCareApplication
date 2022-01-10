package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.raghu.entity.Appointment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author:RAGHU SIR
 * Generated F/w:SHWR-Framework
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT aptm.date, aptm.noOfSlots, aptm.fee, aptm.id FROM Appointment aptm INNER JOIN aptm.doctor as doctor WHERE doctor.id=:docId AND aptm.noOfSlots>0")
    public List<Object[]> getAppointmentsByDoctor(Long docId);

    @Query("SELECT aptm.date, aptm.noOfSlots, aptm.fee, aptm.details FROM Appointment aptm INNER JOIN aptm.doctor as doctor WHERE doctor.email=:userName AND aptm.noOfSlots>0")
    public List<Object[]> getAppointmentsByDoctorEmail(String userName);

    @Modifying
    @Query("UPDATE Appointment SET noOfSlots = noOfSlots + :count WHERE id =:id")
    void updateSlotCountForAppointments(Long id, int count);
}