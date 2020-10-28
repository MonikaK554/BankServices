package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class Bank {

    static final String name = "MBank";

    public static List<String> accountNumberUniqueList = new ArrayList<>();
    public static List<Integer> clientUniqueIdList = new ArrayList<>();

    public static List<Account> allAccounts = new ArrayList<>();
    public static List<Client> allClients = new ArrayList<>();


    public static long createId() {
        Random random = new Random();
        int[] ids = new int[4];
        String id = "";
        for (int i = 0; i < ids.length; i++) {
            ids[i] = random.nextInt(10);
            id = id + ids[i];

        }

        int checkedId = Integer.parseInt(id);

        if (!clientUniqueIdList.contains(checkedId)) {
            clientUniqueIdList.add(checkedId);
            return checkedId;
        } else {
            System.out.println("Błędny numer id.");
            return 0;
        }

    }

    public static void showAllClients() {
        allClients.stream()
                .map(client -> client.getSurname() + " " + client.getName() + client.getListOfClientAccounts())
                .sorted()
                .forEach(System.out::println);
    }

    public static void showAllAccounts() {
        allAccounts.stream().forEach(System.out::println);
    }

    public static List<Client> deleteClientIfHasNoAccounts() {
        List<Client> onlyClientsWithAccounts = allClients.stream()
                .filter(client -> client.getListOfClientAccounts() != null)
                .collect(Collectors.toList());

        System.out.println("Tylko klienci z otwartymi rachunkami");
        allClients = onlyClientsWithAccounts;
        return onlyClientsWithAccounts;
    }

    public static List<Client> deleteAccountIfBalanceIsZero() {

        List<Client> clientsWithBalanceMoreThanZero = allClients.stream()
                .filter(client -> client.getListOfClientAccounts().removeIf(account -> account.getBalance() == 0))
                .collect(Collectors.toList());

        return clientsWithBalanceMoreThanZero;
    }

    @Override
    public String toString() {
        return "Bank{" +
                Bank.name;
    }
}
