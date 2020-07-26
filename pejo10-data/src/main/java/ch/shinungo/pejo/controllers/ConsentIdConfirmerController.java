package ch.shinungo.pejo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ConsentIdConfirmerController {

	@GetMapping({"/consentIdConfirmer", "sites/consentIdConfirmer", "/tt"})
	public String showConsentIdConfirmerHtml() {
		log.debug("ConsentIdConfirmer direkt angesteuert");
		return "sites/consentIdConfirmer";
	}


	/*
	 * Was soll hier passieren? 
	 * ConentIdConfirmer Html ist aufgerufen.
	 * 
	 */
	
	@PostMapping({"postConsent"})
	public String postConsent() {
		log.debug("post Consent Pressed");
		
		// wir müssen einen Consent erstellen. 
		
		return "sites/consentIdConfirmer";
	}
	

}
