package ch.shinungo.pejo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AccountController {

	@GetMapping(path = { "/showAccounts", "sites/showAccounts" })
	public String showAccountHtml() {
		// log.debug("Account-Controller direkt angesteuert");
		return "sites/showAccounts";
	}
}