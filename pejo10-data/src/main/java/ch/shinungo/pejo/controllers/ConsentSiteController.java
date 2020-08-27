package ch.shinungo.pejo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ConsentSiteController {

	public ConsentIdController consentIdController;

	@GetMapping({ "/consentIdConfirmer", "sites/consentIdConfirmer", "/tt" })
	public String showConsentIdConfirmerHtml() {
		log.debug("ConsentIdConfirmer direkt angesteuert");
		return "sites/consentIdConfirmer";
	}
	//Hier kleiner TESXT - 13.46 Uhr
}
