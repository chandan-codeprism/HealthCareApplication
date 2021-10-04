package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.entity.Doctor;

public interface DoctorRepositery extends JpaRepository<Doctor, Long> {
	
	@Query("SELECT COUNT(firstName) FROM Doctor WHERE firstName=:firstName")
	Integer getFirstNameCount(String firstName);
	
	@Query("SELECT COUNT(lastName) FROM Doctor WHERE lastName=:lastName")
	Integer getLastNameCount(String lastName);

	@Query("SELECT COUNT(email) FROM Doctor WHERE email=:email")
	Integer getEmailCount(String email);
	
	@Query("SELECT COUNT(address) FROM Doctor WHERE address=:address")
	Integer getAddressCount(String address);
	
	@Query("SELECT COUNT(mobile) FROM Doctor WHERE mobile=:mobile")
	Integer getMobileCount(String mobile);
	
	@Query("SELECT COUNT(note) FROM Doctor WHERE note=:note")
	Integer getNoteCount(String note);
}
