package in.nareshit.raghu.service;

import java.util.List;
import java.util.Map;

import in.nareshit.raghu.entity.Doctor;

public interface IDoctorService {
	
	public long savaDoctor(Doctor doc);
	public List<Doctor> getAllDoctors();
	public void removeDoctor(Long id); 
	public Doctor getOneDoctor(Long id);
	public void updateDoctor(Doctor doc);
	
	public boolean isFirstNameExist(String firstName);
	public boolean isLastNameExist(String lastName);
	public boolean isEmailExist(String email);
	public boolean isAddressExist(String address);
	public boolean isMobileExist(String mobile);
	public boolean isNoteExist(String note);
	
	public boolean isFirstNameExistForEdit(String firstName,Long id);
	public boolean isLastNameExistForEdit(String lastName,Long id);
	public boolean isEmailExistForEdit(String email,Long id);
	public boolean isAddressExistForEdit(String address,Long id);
	public boolean isMobileExistForEdit(String mobile,Long id);
	public boolean isNoteExistForEdit(String note,Long id);
	
	
	public Map<Long,String> getDoctorIdAndNames();
}
