package ch.shinungo.pejo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AccountController {

	// Nachtrag: vom 29.07.: Beweis für MOTZ:
//	@RequestMapping(path = { "/showAccount", "sites/showAccount" })

	@GetMapping(path = { "/showAccount", "sites/showAccount" })
	public String showAccountHtml() {

		log.debug("Account-Controller direkt angesteuert");

		return "sites/showAccount";
	}
}