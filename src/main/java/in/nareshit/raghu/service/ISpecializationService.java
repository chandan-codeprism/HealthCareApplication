package in.nareshit.raghu.service;

import java.util.List;
import java.util.Map;

import in.nareshit.raghu.entity.Specialization;

public interface ISpecializationService {
	
	long savaSpecialization(Specialization spec);
	List<Specialization> getAllSpecialization();
	void removeSpecialization(Long id);
	Specialization getOneSpecialization(Long id);
	void updateSpecialization(Specialization spec);
	
	boolean isspecCodeExist(String specCode);
	boolean isspecNameExist(String specName);
	boolean isspecNoteExist(String specNote);
	
	boolean isSpecCodeExistForEdit(String specCode, Long id);
	boolean isSpecNameExistForEdit(String specName, Long id);
	boolean isSpecNoteExistForEdit(String specNote, Long id);
	
	Map<Long,String> getSpecIdAndName();

}
