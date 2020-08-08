package ch.shinungo.pejo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import ch.shinungo.pejo.model.Booked;
import ch.shinungo.pejo.model.ConsentRequest;
import ch.shinungo.pejo.model.ConsentResponse;
import ch.shinungo.pejo.model.Transaction;
import ch.shinungo.pejo.model.TransactionResponse;
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
		model.addAttribute("bookingStatus", respEntity.getBody().getLinks().getTransactions());

		return "sites/consentIdConfirmer";
	}

	@GetMapping({ "getAccounts" })
	public String getAccounts(@RequestParam(value = "consentId", required = true) String consentId, Model model)
			throws JsonProcessingException {

		Account accountDetails;

		HttpHeaders headers = prepareHeaders();
		headers.set("Consent-ID", consentId);

		HttpEntity<String> entityReq = new HttpEntity<String>(headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<AccountResponse> respEntity = template.exchange(ACCOUNTS_URL, HttpMethod.GET, entityReq,
				AccountResponse.class);

		// Um den User im HTML auszugeben:

		// LISTE unD HASH-MAP Gem MAURI 5.8.
		// List<String> noteListMap = null;
		// List<Account> accounts = null;
		Map<String, List<Balance>> noteListMap = new HashMap<>();

		for (Account currentAccount : respEntity.getBody().getAccounts()) {

			accountDetails = getAccountDetails(currentAccount, consentId);

			log.debug("account Details: accountDetails" + accountDetails);
			log.debug("account Details: Currency   " + accountDetails.getCurrency());
			log.debug("account Details: CashA.Type " + accountDetails.getCashAccountType());
			log.debug("account Details: IBAN       " + accountDetails.getIban());
			log.debug("account Details: Name       " + accountDetails.getName());

			List<Balance> getbalancesFromAccount = getbalancesFromAccount(accountDetails, consentId);
			currentAccount.setBalances(getbalancesFromAccount);

			noteListMap.put(accountDetails.getIban(), getbalancesFromAccount);

			log.debug("hier die noteListe" + noteListMap);

			log.debug("get Balances 1 Balancens - CurrentAccount  " + currentAccount.getBalances());

			List<Booked> getBookedTransactions = getTransactions(accountDetails, consentId);
			currentAccount.setTransactions(getBookedTransactions);

			log.debug("getBookedTransactions A " + getBookedTransactions);
			log.debug("getBookedTransactions B " + currentAccount.getBalances());

			// noteListMap.put("stand", currentAccount.getBalances());
			// noteListMap.get(currentAccount);
		}

//		for (Account currentAccount : respEntity.getBody().getAccounts()) {
//
//			log.debug(currentAccount.getResourceId());
//			accountDetails = getAccountDetails(currentAccount, consentId);
//			List<Balance> getbalancesFromAccount = getbalancesFromAccount(accountDetails, consentId);
//			List<Booked> getBookedTransactions = getTransactions(accountDetails, consentId);
//			currentAccount.setBalances(getbalancesFromAccount);
//			currentAccount.setTransactions(getBookedTransactions);
//		}

		model.addAttribute("testListe", respEntity.getBody().getAccounts());
		model.addAttribute("accounts", respEntity.getBody().getAccounts());
		model.addAttribute("balances", respEntity.getBody().getAdditionalProperties());

		log.debug("die Accounts die ich haben kann: " + respEntity.getBody().getAccounts());

		/*
		 * Wieso kann ich Werte nicht aus den get AdditionalPropeties holen.
		 */

		log.debug("FRAGE-MOTZ" + respEntity.getBody().getAdditionalProperties());
//		model.addAttribute("SaldiListe", noteListMap.toString());

		// model.addAttribute("CurrentUser", respEntity.ge ;

		return "sites/showAccounts";

	}

	private List<Booked> getTransactions(Account accountDetails, String consentId) {

		String bookingStatus = "booked";
		String dateFrom = "1970-01-01";

		String transactionsUrl = accountDetails.getLinks().getTransactions().getHref() + "?bookingStatus="
				+ bookingStatus + "&dateFrom=" + dateFrom;
		;

		HttpHeaders headers = prepareHeaders();
		headers.set("Consent-ID", consentId);

		log.debug("BIS HIER MIT DEBUGGER OK");

		HttpEntity<String> entityReq = new HttpEntity<String>(headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<TransactionResponse> respEntity = template.exchange(transactionsUrl, HttpMethod.GET, entityReq,
				TransactionResponse.class);

		return respEntity.getBody().getTransactions().getBooked();

	}

	private List<Balance> getbalancesFromAccount(Account accountDetails, String consentId) {

		String balancesUrl = accountDetails.getLinks().getBalances().getHref();

		HttpHeaders headers = prepareHeaders();
		headers.set("Consent-ID", consentId);

		HttpEntity<String> entityReq = new HttpEntity<String>(headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<BalancesResponse> respEntity = template.exchange(balancesUrl, HttpMethod.GET, entityReq,
				BalancesResponse.class);

		// log.debug("getBalancesFromAccoumt IN Methode" +
		// respEntity.getBody().getBalances());

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

//		log.debug("getAccountDetails: IN Methode  " + respEntity.getBody().getAccount().getResourceId());

		return respEntity.getBody().getAccount();

	}

	private HttpHeaders prepareHeaders() {
		HttpHeaders headers = new HttpHeaders();

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