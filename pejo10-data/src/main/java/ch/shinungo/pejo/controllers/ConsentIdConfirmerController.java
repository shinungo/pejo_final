package ch.shinungo.pejo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ConsentIdConfirmerController {

	// Zusätzlich für Direktansteuerung vom Nav. Braucht es dies 04.07.20?

	@RequestMapping(path = { "/consentIdConfirmer", "sites/consentIdConfirmer" })
	public String showConsentIdConfirmerHtml() {
		log.debug("ConsentIdConfirmer direkt angesteuert");
		return "sites/consentIdConfirmer";
	}
	
	
	

	/*
	 * Was soll hier passieren? 
	 * ConentIdConfirmer Html ist aufgerufen.
	 * 
	 * 
	 */

}
