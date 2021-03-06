package in.nareshit.raghu.service.impl;

import java.util.Collections;
import java.util.Optional;

import in.nareshit.raghu.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.entity.User;
import in.nareshit.raghu.service.IUserService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repo;

	@Override
	public Long saveUser(User user) {
		// read generated password
		String pwd = user.getPassword();
		// encode password
		String encPwd = passwordEncoder.encode(pwd);
		// set back to object
		user.setPassword(encPwd);
		return repo.save(user).getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {

		return repo.findByUsername(username);
	}

	@Override
	@Transactional
	public void updateUserPwd(String pwd, Long userId) {
		String encPwd=passwordEncoder.encode(pwd);
		repo.updateUserPwd(encPwd,userId);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt = findByUsername(username);
		if (!opt.isPresent())
			throw new UsernameNotFoundException(username);
		else {

			User user = opt.get();

			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));

		}
	}

}
