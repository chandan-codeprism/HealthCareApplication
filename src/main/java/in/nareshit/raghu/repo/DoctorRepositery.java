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
	
	
	@Query("SELECT COUNT(firstName) FROM Doctor WHERE firstName=:firstName AND id!=:id")
	Integer getFirstNameCountForEdit(String firstName, Long id);
	
	@Query("SELECT COUNT(lastName) FROM Doctor WHERE lastName=:lastName AND id!=:id")
	Integer getLastNameCountForEdit(String lastName, Long id);
	
	@Query("SELECT COUNT(email) FROM Doctor WHERE email=:email AND id!=:id")
	Integer getEmailCountForEdit(String email, Long id);
	
	@Query("SELECT COUNT(address) FROM Doctor WHERE address=:address AND id!=:id")
	Integer getAddressCountForEdit(String address, Long id);
	
	@Query("SELECT COUNT(mobile) FROM Doctor WHERE mobile=:mobile AND id!=:id")
	Integer getMobileCountForEdit(String mobile, Long id);
	
	@Query("SELECT COUNT(note) FROM Doctor WHERE note=:note AND id!=:id")
	Integer getNoteCountForEdit(String note, Long id);
}
