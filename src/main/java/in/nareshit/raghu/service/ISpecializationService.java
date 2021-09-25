package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.entity.Specialization;

public interface ISpecializationService {
	
	public long savaSpecialization(Specialization spec);
	public List<Specialization> getAllSpecialization();
	public void removeSpecialization(Long id);
	public Specialization getOneSpecialization(Long id);
	public void updateSpecialization(Specialization spec);
	
	public boolean isspecCodeExist(String specCode);
	public boolean isspecNameExist(String specName);
	public boolean isspecNoteExist(String specNote);
	
	public boolean isSpecCodeExistForEdit(String specCode, Long id);
	public boolean isSpecNameExistForEdit(String specName, Long id);
	public boolean isSpecNoteExistForEdit(String specNote, Long id);

}
