package ch.shinungo.pejo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.shinungo.pejo.repository.User;
import ch.shinungo.pejo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserService {

	private List<User> users = new ArrayList<User>();

	@Autowired
	private UserRepository userRepo;

	public User findUserByName(String name) {
		return userRepo.findByName(name);
	}

	public List<User> getAllUsers() {
		Iterable<User> itUser = userRepo.findAll();
		List<User> userList = new ArrayList<User>();
		for (User user : itUser) {
			userList.add(user);
		}
		return userList;
	}
}
