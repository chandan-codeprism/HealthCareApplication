package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.entity.Specialization;
import in.nareshit.raghu.exception.SpecializationNotFoundException;
import in.nareshit.raghu.repo.SpecializationRepositery;
import in.nareshit.raghu.service.ISpecializationService;

@Service
public class SpecializationServiceImpl implements ISpecializationService {
	
	@Autowired
	private SpecializationRepositery repo;

	@Override
	public long savaSpecialization(Specialization spec) {
		
		return repo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecialization() {
		
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		
		//repo.deleteById(id);
		repo.delete(getOneSpecialization(id));

	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		
		Optional<Specialization> optional=repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new SpecializationNotFoundException(id+ " Not Found");
		}
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		repo.save(spec);

	}

	@Override
	public boolean isspecCodeExist(String specCode) {
		/*
		 * Integer count=repo.getSpecCodeCount(specCode); boolean exist=count>0 ? true :
		 * false; return exist;
		 */
		return repo.getSpecCodeCount(specCode)>0;
	}

	@Override
	public boolean isspecNameExist(String specName) {
		Integer count=repo.getSpecNameCount(specName);
		boolean exist=count>0 ? true : false;
		return exist;
	}

	@Override
	public boolean isspecNoteExist(String specNote) {
		
		return repo.getSpecNoteCount(specNote)>0;
	}

}
