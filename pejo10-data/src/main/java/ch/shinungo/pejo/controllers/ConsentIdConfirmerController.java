package ch.shinungo.pejo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

import ch.shinungo.pejo.form.UserForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ConsentIdConfirmerController {

	public ConsentIdController consentIdController;

	@GetMapping({ "/consentIdConfirmer", "sites/consentIdConfirmer", "/tt" })
	public String showConsentIdConfirmerHtml() {
		log.debug("ConsentIdConfirmer direkt angesteuert");
		return "sites/consentIdConfirmer";
	}

	/*
	 * Was soll hier passieren? ConentIdConfirmer Html ist aufgerufen. 1.
	 * https://api.dev.openbankingproject.ch/docs/swagger-ui.html 2. Unten:
	 * "Post: Create Consent"
	 * 
	 * a: X-Request-ID b:
	 * 
	 * 
	 */

	/*
	 * GET - ACCOUNTS: (AUS SICHT THYMELEAFE MUSS ES EIN POST SEIN.
	 * 
	 * X-Request-ID Consent-ID
	 * 
	 */

	@PostMapping({ "justAbutton" })
	public String getAccounts(@ModelAttribute UserForm form, Model model) throws JsonProcessingException {

		log.debug("justAbutton Pressed");
		return "sites/consentIdConfirmer";

		// x-request und consent it

	}

}
