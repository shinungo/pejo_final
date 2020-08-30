package ch.shinungo.pejo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.shinungo.pejo.service.UserForm;
import ch.shinungo.pejo.service.UserService;
import lombok.Data;

@Controller
@Data
public class UserSelectorController {

	@Autowired
	private UserService userService;

	@GetMapping({ "getConsentId", "/", "/start", "/home", "userSelector" })

	public String showUserSelector(Model model) {
		model.addAttribute("userForm", new UserForm());
		model.addAttribute("users", userService.getAllUsers());
		return "sites/userSelector";
	}

}
