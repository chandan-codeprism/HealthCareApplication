package in.nareshit.raghu.service;

import java.util.List;
import java.util.Map;

import in.nareshit.raghu.entity.Doctor;

public interface IDoctorService {
	
	long savaDoctor(Doctor doc);
	List<Doctor> getAllDoctors();
	void removeDoctor(Long id);
	Doctor getOneDoctor(Long id);
	void updateDoctor(Doctor doc);
	
	boolean isFirstNameExist(String firstName);
	boolean isLastNameExist(String lastName);
	boolean isEmailExist(String email);
	boolean isAddressExist(String address);
	boolean isMobileExist(String mobile);
	boolean isNoteExist(String note);
	
	boolean isFirstNameExistForEdit(String firstName, Long id);
	boolean isLastNameExistForEdit(String lastName, Long id);
	boolean isEmailExistForEdit(String email, Long id);
	boolean isAddressExistForEdit(String address, Long id);
	boolean isMobileExistForEdit(String mobile, Long id);
	boolean isNoteExistForEdit(String note, Long id);
	
	
	Map<Long,String> getDoctorIdAndNames();
	public List<Doctor> findDoctorBySpecName(Long specId);
}
