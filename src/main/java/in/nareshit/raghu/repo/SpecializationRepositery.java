package in.nareshit.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.entity.Specialization;

public interface SpecializationRepositery extends JpaRepository<Specialization, Long> {
	
	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode")
	Integer getSpecCodeCount(String specCode);
	
	@Query("SELECT COUNT(specName) FROM Specialization WHERE specName=:specName")
	Integer getSpecNameCount(String specName);
	
	@Query("SELECT COUNT(specNote) FROM Specialization WHERE specNote=:specNote")
	Integer getSpecNoteCount(String specNote);
	
	
	
	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode AND id!=:id")
	Integer getSpecCodeCountForEdit(String specCode, Long id);
	
	@Query("SELECT COUNT(specName) FROM Specialization WHERE specName=:specName AND id!=:id")
	Integer getSpecNameCountForEdit(String specName, Long id);
	
	@Query("SELECT COUNT(specNote) FROM Specialization WHERE specNote=:specNote AND id!=:id")
	Integer getSpecNoteCountForEdit(String specNote, Long id);
	
	
	
	@Query("SELECT id, specName FROM Specialization")
	List<Object[]> getSpecIdAndName();

	

	

}
