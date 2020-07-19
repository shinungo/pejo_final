package ch.shinungo.pejo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // This means that this class is a Controller
public class AccountController {

	@RequestMapping(path = { "/showAccount", "sites/showAccount" })
	public String showAccountHtml() {

		log.debug("Account-Controller direkt angesteuert");

		return "sites/showAccount";
	}
}