package application;

import java.util.Random;

public class Account {

    private AccountType accountType;
    private double balance;
    private String accountNumber = "";

    public Account(AccountType accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
        accountNumber = createUniqueAccountNumber();
    }

    public String createUniqueAccountNumber() {
        for (int i = 0; i < 27; i++) {
            Random random = new Random();
            int digit = random.nextInt(10);

            accountNumber = accountNumber + digit;
        }

        if (!Bank.accountNumberUniqueList.contains(accountNumber)) {
            Bank.accountNumberUniqueList.add(accountNumber);
            return accountNumber;
        } else {
            System.out.println("Błędny numer konta");
            return this.createUniqueAccountNumber();
        }

    }

    public static Account createAccount(AccountType type, double balance) { // metoda do tworzenia rachunku zamiast slowka new
        return new Account(type, balance);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
