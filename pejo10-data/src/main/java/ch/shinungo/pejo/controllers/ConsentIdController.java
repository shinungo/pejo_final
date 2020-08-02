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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.shinungo.pejo.form.UserForm;
import ch.shinungo.pejo.model.Access;
import ch.shinungo.pejo.model.Account;
import ch.shinungo.pejo.model.AccountDetailResponse;
import ch.shinungo.pejo.model.AccountResponse;
import ch.shinungo.pejo.model.Balance;
import ch.shinungo.pejo.model.BalancesResponse;
import ch.shinungo.pejo.model.ConsentRequest;
import ch.shinungo.pejo.model.ConsentResponse;
import ch.shinungo.pejo.model.Transaction;
import ch.shinungo.pejo.model.Transactions;
import ch.shinungo.pejo.repository.User;
import ch.shinungo.pejo.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ConsentIdController {

	private static final String CONSENT_URL = "https://api.dev.openbankingproject.ch/v1/consents";
	private static final String ACCOUNTS_URL = "https://api.dev.openbankingproject.ch/v1/accounts";

	@Autowired
	private UserService userService;

	@GetMapping({ "getConsentId", "/", "/start", "/home" })

	public String showUserSelector(Model model) {
		model.addAttribute("userForm", new UserForm());
		model.addAttribute("users", userService.getAllUsers());
		return "sites/userSelector";
	}

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

		// NEU PER 01.08.2020:

		// @ MABOLL AM SONNTAG MAL AUSNAHMSWEISE MIT DEM Chäfferli und BreakPoints
		// Setzen.

		model.addAttribute("bookingStatus", respEntity.getBody().getLinks().getTransactions());

		return "sites/consentIdConfirmer";
	}

	@GetMapping({ "getAccounts" })
	public String getAccounts(@RequestParam(value = "consentId", required = true) String consentId,
			String bookingStatus) throws JsonProcessingException {

		Account accountDetails;
		// HIER KEIN BOOKING STATUAS

		HttpHeaders headers = prepareHeaders();
		headers.set("Consent-ID", consentId);

		HttpEntity<String> entityReq = new HttpEntity<String>(headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<AccountResponse> respEntity = template.exchange(ACCOUNTS_URL, HttpMethod.GET, entityReq,
				AccountResponse.class);

		for (Account a : respEntity.getBody().getAccounts()) {
			log.debug(a.getResourceId());
			accountDetails = getAccountDetails(a, consentId);
			List<Balance> getbalancesFromAccount = getbalancesFromAccount(accountDetails, consentId);

			List<Transactions> getAllTransactions = getTransactions(accountDetails, consentId, bookingStatus);
		}

		log.debug("Account ID: " + respEntity.getBody().getAccounts().get(1).getResourceId());
		log.debug("Response: " + respEntity.getBody().toString());
		return "sites/consentIdConfirmer";

	}

	/*
	 * Hier kommen noch die Transactions hin Nächster Termin: Sonntag, 14.00
	 * 
	 * 
	 * BookingStatus musst du wie Consent-ID, X-Request-ID oder account-id mit
	 * geben. schau mal wie du das da gemacht hast und mach es gleich.
	 * 
	 * Nimm "both" so musst du nicht eine zusätzliche Enum machen etc.. einfach
	 * hardcoded eintragen, so wie das bei X-Request-ID gemacht wurde.
	 * 
	 */

	private List<Transactions> getTransactions(Account accountDetails, String consentId, String bookingStatus) {

		String transactionsUrl = accountDetails.getLinks().getTransactions().getHref();

		HttpHeaders headers = prepareHeaders();
		headers.set("Consent-ID", consentId);

		log.debug("BIS HIER MIT DEBUGGER OK");

		HttpEntity<String> entityReq = new HttpEntity<String>(headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<Transactions> respEntity = template.exchange(transactionsUrl, HttpMethod.GET, entityReq,
				Transactions.class);

		// return respEntity.getBody()

		log.debug("Hier sind wir");

		/*
		 * Transactions lässt sich nicht retournieren.
		 */

		return null;
	}

	private List<Balance> getbalancesFromAccount(Account accountDetails, String consentId) {

		String balancesUrl = accountDetails.getLinks().getBalances().getHref();

		HttpHeaders headers = prepareHeaders();
		headers.set("Consent-ID", consentId);

		HttpEntity<String> entityReq = new HttpEntity<String>(headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<BalancesResponse> respEntity = template.exchange(balancesUrl, HttpMethod.GET, entityReq,
				BalancesResponse.class);

		return respEntity.getBody().getBalances();
	}

	private Account getAccountDetails(Account a, String consentId) {
		String accountDetailUrl = ACCOUNTS_URL + "/" + a.getResourceId();

		HttpHeaders headers = prepareHeaders();
		headers.set("Consent-ID", consentId);

		HttpEntity<String> entityReq = new HttpEntity<String>(headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<AccountDetailResponse> respEntity = template.exchange(accountDetailUrl, HttpMethod.GET,
				entityReq, AccountDetailResponse.class);

		log.debug("getAccountDetails:  " + respEntity.getBody().getAccount().getResourceId());

		return respEntity.getBody().getAccount();

	}
// BOOKING STATUS "both" 01.08.2020 

	private HttpHeaders prepareHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("bookingStatus", "both");
		headers.set("X-Request-ID", "99391c7e-ad88-49ec-a2ad-99ddcb1f7721");
		headers.set("psu-ip-address", "192.168.0.2"); // IPv4: 192.168.1.5 // Standardgateway 192.168.1.1
		headers.set("tpp-redirect-uri", "url-class-java-examples");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	private HttpHeaders prepareHeaders(User user) {
		HttpHeaders headers = prepareHeaders();
		headers.set("PSU-ID", user.getName()); //
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