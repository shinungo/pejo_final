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

	/*
	 * Aufgabe: Künftig: Holt Daten aus DB - und hält diese Bereit. AKTUELL: Im
	 * Moment wird nur ein User simuliert, DB-Anbindung später. 01.07.2020
	 */

	private List<User> users = new ArrayList<User>();

	@Autowired
	private UserRepository userRepo;

	// HIer kann ich dann die Mehrere Uer machen...

//	public UserService() {
//		User u = new User();
//		u.setName("buha1");
//		// u.getIbans().add("CH2810051000000000054");
//		// u.getIbans().add("CH8710051000000000059");
//		// u.getIbans().add("CH6010051000000000060");
//		users.add(u);
//
//		User u1 = new User();
//		u1.setName("stephan");
//		// u1.getIbans().add("CH9810051000000000055");
//		// u1.getIbans().add("CH4410051000000000057");
//		users.add(u1);
//
//		User u2 = new User();
//		u2.setName("buha2");
////		u2.getIbans().add("CH2710051000000000072");
//		users.add(u2);
//
//		User u3 = new User();
//		u3.setName("buha3");
////		u3.getIbans().add("CH6510051000000000067");
////		u3.getIbans().add("CH3810051000000000068");
////		u3.getIbans().add("CH1110051000000000069");
////		u3.getIbans().add("CH8110051000000000070");
//		users.add(u3);
//
//		User u4 = new User();
//		u4.setName("buha4");
////		u4.getIbans().add("CH5410051000000000071");
//		users.add(u4);
//
//	}

	// USER SUCHEN mit USER NAME
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
