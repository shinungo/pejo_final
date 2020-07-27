package ch.shinungo.pejo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ConsentIdConfirmerController {

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

	@PostMapping({ "postConsent" })
	public String postConsent() {
		log.debug("post Consent Pressed");

		// wir müssen einen Consent erstellen.

		return "sites/consentIdConfirmer";
	}

	/*
	 * GET - ACCOUNTS: (AUS SICHT THYMELEAFE MUSS ES EIN POST SEIN.
	 * 
	 * X-Request-ID Consent-ID
	 * 
	 */

	@PostMapping({ "getAccounts" })
	public String getAccounts() {
		log.debug("get Accounts Pressed");

		return "sites/consentIdConfirmer";
	}

}
