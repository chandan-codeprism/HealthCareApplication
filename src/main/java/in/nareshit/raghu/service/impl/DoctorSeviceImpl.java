package in.nareshit.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.entity.Doctor;
import in.nareshit.raghu.exception.DoctorNotFoundException;
import in.nareshit.raghu.repo.DoctorRepositery;
import in.nareshit.raghu.service.IDoctorService;

@Service
public class DoctorSeviceImpl implements IDoctorService {
	
	@Autowired
	private DoctorRepositery repo;

	@Override
	public long savaDoctor(Doctor doc) {
		
		return repo.save(doc).getId();
	}

	@Override
	public List<Doctor> getAllDoctors() {

		return repo.findAll();
	}

	@Override
	public void removeDoctor(Long id) {
		
		repo.delete(getOneDoctor(id));

	}

	@Override
	public Doctor getOneDoctor(Long id) {
		
		return repo.findById(id).orElseThrow(
				()->new DoctorNotFoundException(id+", not exist")
				);
	}

	@Override
	public void updateDoctor(Doctor doc) {
		if(repo.existsById(doc.getId()))
		repo.save(doc);
		else throw new DoctorNotFoundException(doc.getId()+", not exist");

	}

}
