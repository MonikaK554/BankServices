package database.entity;

import application.AccountType;
import application.Client;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Accounts_data")
public class AccountData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "account_Id")
    private Integer accountId;

    @NotNull
    @Column(name = "account_type")
    @Enumerated(value = EnumType.STRING)
    private  AccountType accountType;

    @Column(name = "account_number")
    private String accountNumber;

    private BigDecimal balance;


    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private ClientData clientData;

    public AccountData () {

    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public ClientData getClientData() {
        return clientData;
    }

    public void setClientData(ClientData clientData) {
        this.clientData = clientData;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "accountId=" + accountId +
                ", accountType=" + accountType +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", clientData=" + clientData +
                '}';
    }
}
