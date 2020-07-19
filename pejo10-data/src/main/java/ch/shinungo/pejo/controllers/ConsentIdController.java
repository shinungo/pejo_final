package ch.shinungo.pejo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.shinungo.pejo.form.UserForm;
import ch.shinungo.pejo.model.Access;
import ch.shinungo.pejo.model.Account;
import ch.shinungo.pejo.model.Balance;
import ch.shinungo.pejo.model.ConsentRequest;
import ch.shinungo.pejo.model.ConsentResponse;
import ch.shinungo.pejo.model.Transaction;
import ch.shinungo.pejo.repository.User;
import ch.shinungo.pejo.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ConsentIdController {

	private static final String CONSENT_URL = "https://api.dev.openbankingproject.ch/v1/consents";

	@Autowired
	private UserService userService;

	// Methode mit Annotation.. Freitags Hier ist Consent ID-Pfad. 10.07. //
	// LASSEN!!
	@GetMapping("getConsentId")

	public String showUserSelector(Model model) {

		model.addAttribute("userForm", new UserForm());

		model.addAttribute("users", userService.getAllUsers());
		return "sites/userSelector";
	}

//	@RequestMapping(value = "/getConsentId")
	@PostMapping("getConsentId")
	public String getConsentId(@ModelAttribute UserForm form, Model model) throws JsonProcessingException {

		User user = userService.findUserByName(form.getName());
		HttpHeaders headers = prepareHeaders(user);

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(getConsentRequest(user.getIbans()));

		HttpEntity<String> entityReq = new HttpEntity<String>(jsonInString, headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<ConsentResponse> respEntity = template // HIer Zugriff angefragt.
				.exchange(CONSENT_URL, HttpMethod.POST, entityReq, ConsentResponse.class);

		log.debug("ConsentID " + respEntity.getBody().getConsentId());

		model.addAttribute("confirmationBanklink", respEntity.getBody().getLinks().getScaRedirect().getHref());
		model.addAttribute("ConsentID", respEntity.getBody().getConsentId());

		return "sites/consentIdConfirmer";
	}

	private HttpHeaders prepareHeaders(User user) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Request-ID", "99391c7e-ad88-49ec-a2ad-99ddcb1f7721");
		headers.set("PSU-ID", user.getName()); //
		headers.set("psu-ip-address", "192.168.0.2"); // IPv4: 192.168.1.5 // Standardgateway 192.168.1.1
		headers.set("tpp-redirect-uri", "url-class-java-examples");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	private ConsentRequest getConsentRequest(List<String> ibans) {

		ConsentRequest cr = new ConsentRequest();
		cr.setAccess(new Access());
		cr.setRecurringIndicator(false);
		cr.setValidUntil("2020-12-31");
		cr.setFrequencyPerDay(4);
		cr.setCombinedServiceIndicator(false);
		cr.getAccess().setAccounts(new ArrayList<Account>());
		cr.getAccess().setBalances(new ArrayList<Balance>());
		cr.getAccess().setTransactions(new ArrayList<Transaction>());

		for (String iban : ibans) {
			Account a = new Account();
			a.setIban(iban);
			cr.getAccess().getAccounts().add(a);

			Balance b = new Balance();
			b.setIban(iban);
			cr.getAccess().getBalances().add(b);

			Transaction tra = new Transaction();
			tra.setIban(iban);
			cr.getAccess().getTransactions().add(tra);

		}

		return cr;
	}
}