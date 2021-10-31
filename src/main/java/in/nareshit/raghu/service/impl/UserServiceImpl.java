package in.nareshit.raghu.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.entity.User;
import in.nareshit.raghu.repo.UserRepositery;
import in.nareshit.raghu.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepositery repo;

	@Override
	public Long saveUser(User user) {

		return repo.save(user).getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {

		return repo.findByUsername(username);
	}

}
