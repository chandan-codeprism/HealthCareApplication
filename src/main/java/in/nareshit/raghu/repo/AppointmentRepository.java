package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.raghu.entity.Appointment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT aptm.date, aptm.noOfSlots, aptm.fee FROM Appointment aptm INNER JOIN aptm.doctor as doctor WHERE doctor.id=:docId")
    public List<Object[]> getAppointmentsByDoctor(Long docId);
}