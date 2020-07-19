package ch.shinungo.pejo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WelcomeController {
	@RequestMapping(path = { "/start", "/" })
	public String showStart() {
		return "sites/userSelector";
	}
}
