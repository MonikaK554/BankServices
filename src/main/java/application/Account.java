package application;

import java.util.Random;

public class Account {

    public static String createUniqueAccountNumber() {
        String accountNumber = "";
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
            return createUniqueAccountNumber();
        }
    }
}