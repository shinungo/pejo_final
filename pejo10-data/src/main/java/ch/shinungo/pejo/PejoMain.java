package ch.shinungo.pejo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PejoMain {
//	private static final String CONSENT_URL = "https://api.dev.openbankingproject.ch/v1/consents";
//	private static final Logger log = LoggerFactory.getLogger(PejoMain.class);

	// STARTMETHODE:
	public static void main(String[] args) {
		SpringApplication.run(PejoMain.class, args);
	}

//EVERY
	/*
	 * Metheode getContent sendet Anmeldedaten an Sandbox um ConsentID ("SessionID")
	 * zu erhalten. - Läuft nun im Consent Id-Controller 01.07.2020
	 */
//
//	// @Bean
//	public void getConsentCredential() throws JsonProcessingException {
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("X-Request-ID", "99391c7e-ad88-49ec-a2ad-99ddcb1f7721");
//		headers.set("PSU-ID", "buha1"); //
//		headers.set("psu-ip-address", "192.168.0.2"); // IPv4: 192.168.1.5 // Standardgateway 192.168.1.1
//		headers.set("tpp-redirect-uri", "url-class-java-examples");
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		ObjectMapper mapper = new ObjectMapper();
//
//		// Object to JSON in String
//		String jsonInString = mapper.writeValueAsString(getConsentRequest());
//		log.debug("JETZT KOMMT DER JSON-STRING");
//		log.debug(jsonInString);
//
//		// "Verheiratung" HttpEntität + RestTemplates als entityReq --> via Post an
//		// ConsentURL
//		HttpEntity<String> entityReq = new HttpEntity<String>(jsonInString, headers);
//		RestTemplate template = new RestTemplate();
//		ResponseEntity<ConsentResponse> respEntity = template // HIer Zugriff angefragt.
//				.exchange(CONSENT_URL, HttpMethod.POST, entityReq, ConsentResponse.class);
//		log.debug("JETZT KOMMT DER BODY MIT DER CONSENT-ID");
//		log.debug(respEntity.getBody().getConsentId());
//
//		// Link (==> body/Links/scaRedirect/href) in Variable "sessionConfirmerURL"
//	}
//
//	// DER HIER UNTEN IST GUT UND WICHTIG!!!
//	private ConsentRequest getConsentRequest() {
//
//		ConsentRequest cr = new ConsentRequest();
//		cr.setAccess(new Access());
//		cr.setRecurringIndicator(false);
//		cr.setValidUntil("2020-12-31");
//		cr.setFrequencyPerDay(4);
//		cr.setCombinedServiceIndicator(false);
//
//		Account a = new Account();
//		a.setIban("CH2810051000000000054");
//		cr.getAccess().setAccounts(new ArrayList<Account>());
//		cr.getAccess().getAccounts().add(a);
//
//		Balance b = new Balance();
//		b.setIban("CH2810051000000000054");
//		cr.getAccess().setBalances(new ArrayList<Balance>());
//		cr.getAccess().getBalances().add(b);
//
//		Transaction tra = new Transaction();
//		tra.setIban("CH2810051000000000054");
//		cr.getAccess().setTransactions(new ArrayList<Transaction>());
//		cr.getAccess().getTransactions().add(tra);
//		return cr;
//	}
}