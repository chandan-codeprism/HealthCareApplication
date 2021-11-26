package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.constants.UserRoles;
import in.nareshit.raghu.entity.Doctor;
import in.nareshit.raghu.entity.User;
import in.nareshit.raghu.exception.DoctorNotFoundException;
import in.nareshit.raghu.repo.DoctorRepositery;
import in.nareshit.raghu.service.IDoctorService;
import in.nareshit.raghu.service.IUserService;
import in.nareshit.raghu.util.MyCollectionsUtil;
import in.nareshit.raghu.util.MyMailUtil;
import in.nareshit.raghu.util.UserUtil;

@Service
public class DoctorSeviceImpl implements IDoctorService {

	@Autowired
	private DoctorRepositery repo;
	@Autowired
	private IUserService userservice;
	@Autowired
	private UserUtil util;
	@Autowired
	private MyMailUtil mailUtil;

	@Override
	public long savaDoctor(Doctor doc) {

		Long id = repo.save(doc).getId();
		if (id != null) {
			String pwd = util.genPwd();
			User user = new User();
			user.setDisplayName(doc.getFirstName() + " " + doc.getLastName());
			user.setUsername(doc.getEmail());
			user.setPassword(pwd);
			user.setRole(UserRoles.DOCTOR.name());
			Long genId = userservice.saveUser(user);
			if (genId != null)

				new Thread(new Runnable() {

					public void run() {
						String text = "Your username is " + doc.getEmail() + ", password is " + pwd;
						mailUtil.send(doc.getEmail(), "DOCTOR ADDED", text);

					}
				}).start();
		}
		return id;
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

		return repo.findById(id).orElseThrow(() -> new DoctorNotFoundException(id + ", not exist"));
	}

	@Override
	public void updateDoctor(Doctor doc) {
		if (repo.existsById(doc.getId()))
			repo.save(doc);
		else
			throw new DoctorNotFoundException(doc.getId() + ", not exist");

	}

	@Override
	public boolean isFirstNameExist(String firstName) {

		return repo.getFirstNameCount(firstName) > 0;
	}

	@Override
	public boolean isLastNameExist(String lastName) {

		return repo.getLastNameCount(lastName) > 0;
	}

	@Override
	public boolean isEmailExist(String email) {

		return repo.getEmailCount(email) > 0;
	}

	@Override
	public boolean isAddressExist(String address) {

		return repo.getAddressCount(address) > 0;
	}

	@Override
	public boolean isMobileExist(String mobile) {

		return repo.getMobileCount(mobile) > 0;
	}

	@Override
	public boolean isNoteExist(String note) {

		return repo.getNoteCount(note) > 0;
	}

	@Override
	public boolean isFirstNameExistForEdit(String firstName, Long id) {

		return repo.getFirstNameCountForEdit(firstName, id) > 0;
	}

	@Override
	public boolean isLastNameExistForEdit(String lastName, Long id) {

		return repo.getLastNameCountForEdit(lastName, id) > 0;
	}

	@Override
	public boolean isEmailExistForEdit(String email, Long id) {

		return repo.getEmailCountForEdit(email, id) > 0;
	}

	@Override
	public boolean isAddressExistForEdit(String address, Long id) {

		return repo.getAddressCountForEdit(address, id) > 0;
	}

	@Override
	public boolean isMobileExistForEdit(String mobile, Long id) {

		return repo.getMobileCountForEdit(mobile, id) > 0;
	}

	@Override
	public boolean isNoteExistForEdit(String note, Long id) {

		return repo.getNoteCountForEdit(note, id) > 0;
	}

	@Override
	public Map<Long, String> getDoctorIdAndNames() {
		List<Object[]> list = repo.getDoctorIdAndNames();
		return MyCollectionsUtil.convertToMapIndex(list);
	}

}
