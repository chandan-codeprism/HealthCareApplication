package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.raghu.entity.Doctor;

public interface DoctorRepositery extends JpaRepository<Doctor, Long> {

}
