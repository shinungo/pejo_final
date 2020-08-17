package ch.shinungo.pejo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

//	@PostMapping({ "justAbutton" })
//	public String getAccounts(@ModelAttribute UserForm form, Model model) throws JsonProcessingException {
//
//		log.debug("justAbutton Pressed");
//		return "sites/consentIdConfirmer";
//
//		// x-request und consent it
//
//	}

}
