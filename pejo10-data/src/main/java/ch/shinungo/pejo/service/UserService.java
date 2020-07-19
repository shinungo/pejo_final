package ch.shinungo.pejo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ch.shinungo.pejo.repository.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserService {

	/*
	 * Aufgabe: Künftig: Holt Daten aus DB - und hält diese Bereit. AKTUELL: Im
	 * Moment wird nur ein User simuliert, DB-Anbindung später. 01.07.2020
	 */

	private List<User> users = new ArrayList<User>();

	// HIer kann ich dann die Mehrere Uer machen...

	public UserService() {
		User u = new User();
		u.setName("buha1");
		u.getIbans().add("CH2810051000000000054");
		u.getIbans().add("CH8710051000000000059");
		u.getIbans().add("CH6010051000000000060");
		users.add(u);

		User u1 = new User();
		u1.setName("stephan");
		u1.getIbans().add("CH9810051000000000055");
		u1.getIbans().add("CH4410051000000000057");

		users.add(u1);

	}

	// USER SUCHEN mit USER NAME
	public User findUserByName(String name) {

		for (User user : users) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}

	public List<User> getAllUsers() {
		return users;
	}

}
