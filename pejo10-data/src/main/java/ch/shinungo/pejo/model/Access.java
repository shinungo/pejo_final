
package ch.shinungo.pejo.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "accounts",
    "balances",
    "transactions",
    "availableAccounts",
    "availableAccountsWithBalance",
    "allPsd2"
})
public class Access implements Serializable
{

    @JsonProperty("accounts")
    private List<Account> accounts = null;
    @JsonProperty("balances")
    private List<Balance> balances = null;
    @JsonProperty("transactions")
    private List<Transaction> transactions = null;
    @JsonProperty("availableAccounts")
    private String availableAccounts;
    @JsonProperty("availableAccountsWithBalance")
    private String availableAccountsWithBalance;
    @JsonProperty("allPsd2")
    private String allPsd2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2441515307631912712L;

    @JsonProperty("accounts")
    public List<Account> getAccounts() {
        return accounts;
    }

    @JsonProperty("accounts")
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @JsonProperty("balances")
    public List<Balance> getBalances() {
        return balances;
    }

    @JsonProperty("balances")
    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    @JsonProperty("transactions")
    public List<Transaction> getTransactions() {
        return transactions;
    }

    @JsonProperty("transactions")
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @JsonProperty("availableAccounts")
    public String getAvailableAccounts() {
        return availableAccounts;
    }

    @JsonProperty("availableAccounts")
    public void setAvailableAccounts(String availableAccounts) {
        this.availableAccounts = availableAccounts;
    }

    @JsonProperty("availableAccountsWithBalance")
    public String getAvailableAccountsWithBalance() {
        return availableAccountsWithBalance;
    }

    @JsonProperty("availableAccountsWithBalance")
    public void setAvailableAccountsWithBalance(String availableAccountsWithBalance) {
        this.availableAccountsWithBalance = availableAccountsWithBalance;
    }

    @JsonProperty("allPsd2")
    public String getAllPsd2() {
        return allPsd2;
    }

    @JsonProperty("allPsd2")
    public void setAllPsd2(String allPsd2) {
        this.allPsd2 = allPsd2;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
