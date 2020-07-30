package ch.shinungo.pejo.model;

import lombok.Data;

@Data
public class Iban {

	public static String IBAN = null;

	public static String getIBAN() {
		return IBAN;
	}

	public static void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

}
