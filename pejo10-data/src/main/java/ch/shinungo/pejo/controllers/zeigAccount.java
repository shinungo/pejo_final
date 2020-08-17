package ch.shinungo.pejo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;

@Controller
@Data
public class zeigAccount {

	@GetMapping(path = { "zeigAccount", "sites/zeigAccount" })
	public String zeigAccountHtml() {
		return "sites/zeigAccount";
	}
}
