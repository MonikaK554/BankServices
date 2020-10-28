package application;

import java.util.*;

public class Client {

    private String name;
    private String surname;
    private long pesel;
    private long id;
    private List<Account> listOfClientAccounts;

    public Client(ClientBuilder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.pesel = builder.pesel;
        this.id = builder.id;
        this.listOfClientAccounts = builder.listOfClientAccounts;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public long getPesel() {
        return pesel;
    }

    public long getId() {
        return id;
    }

    public List<Account> getListOfClientAccounts() {
        return listOfClientAccounts;
    }


    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pesel=" + pesel +
                ", id=" + id +
                ", listOfAccounts=" + "\n" + listOfClientAccounts +
                '}';
    }


    public static class ClientBuilder {

        private String name;
        private String surname;
        private long pesel;
        private long id;
        private List<Account> listOfClientAccounts;

        public ClientBuilder(String name, String surname, long pesel) {
            this.name = name;
            this.surname = surname;
            this.pesel = pesel;
            id = Bank.createId();
        }

        public ClientBuilder addNewListOfAccounts(AccountType[] typeOfAccount) {

            System.out.println("Podaj saldo początkowe dla konta " + Arrays.toString(typeOfAccount));
            Scanner scanner = new Scanner(System.in);
            double balance = scanner.nextDouble();
            Account account = Account.createAccount(typeOfAccount[0], balance);

            listOfClientAccounts.add(account);
            Bank.allAccounts.add(account);
            this.listOfClientAccounts = listOfClientAccounts;
            return this;
        }


        public Client build() { // metoda budująca. Teraz trzeba stworzyc konstruktor w klasie glownej oparty na ClientBuilderze
            return new Client(this);
        }

    }

    public Client addOtherAccountToList(String accountType, double balance) {
        if (this.getListOfClientAccounts() == null) {
            listOfClientAccounts = new ArrayList<>();

        }

        Account account1;
        if (accountType.equalsIgnoreCase(AccountType.STUDENT.name())) {
            account1 = Account.createAccount(AccountType.STUDENT, balance);

            listOfClientAccounts.add(account1);

            Bank.allAccounts.add(account1);
            System.out.println("Operacja dodania nowego konta przebiegła pomyślnie.");
            return this;
        } else if (accountType.equalsIgnoreCase(AccountType.PRO.name())) {
            account1 = Account.createAccount(AccountType.PRO, balance);

            listOfClientAccounts.add(account1);

            Bank.allAccounts.add(account1);
            System.out.println("Operacja dodania nowego konta przebiegła pomyślnie.");
            return this;
        } else if (accountType.equalsIgnoreCase(AccountType.STANDARD.name())) {
            account1 = Account.createAccount(AccountType.STANDARD, balance);
            listOfClientAccounts.add(account1);

            Bank.allAccounts.add(account1);
            System.out.println("Operacja dodania nowego konta przebiegła pomyślnie.");
            return this;
        } else {
            return null;
        }
    }

    public static Client createClient(String name, String surname, long pesel, AccountType... accountType) {

        if (accountType[0] == AccountType.NONE) {
            Client client = new Client.ClientBuilder(name, surname, pesel).build();

            Bank.allClients.add(client);
            return client;
        } else {
            Client client = new Client.ClientBuilder(name, surname, pesel).addNewListOfAccounts(accountType).build();

            Bank.allClients.add(client);
            return client;
        }
    }

    public Client cashAdd() {

        System.out.println("Podaj typ konta  na które chcesz wpłacić pieniądze: ");
        this.getListOfClientAccounts().stream()
                .map(account -> account.getAccountType() + " " + account.getAccountNumber() + "Saldo: " + account.getBalance())
                .forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        String choosenAccountType = scanner.nextLine();

        if (this.getListOfClientAccounts().stream()
                .filter(account -> account.getAccountType().name().equalsIgnoreCase(choosenAccountType))
                .toArray().length > 0) {

            System.out.println("Podaj kwotę do wpłaty:");
            double impact = scanner.nextDouble();

            this.getListOfClientAccounts().stream()
                    .filter(account -> account.getAccountType().name().equalsIgnoreCase(choosenAccountType))
                    .forEach(account -> account.setBalance(account.getBalance() + impact));

            this.getListOfClientAccounts().stream()
                    .filter(account -> account.getAccountType().name().equalsIgnoreCase(choosenAccountType))
                    .forEach(account -> System.out.println("Nowe saldo to: " + account.getBalance()));

        } else {
            System.out.println("Wybrano niepoprawne konto");
        }
        return this;
    }

    public Client withdrawal() {

        System.out.println("Podaj typ konta, z ktorego chcesz wypłacić pieniądze:  ");

        this.getListOfClientAccounts().stream()
                .map(account -> account.getAccountType() + " " + account.getAccountNumber() + "Saldo: " + account.getBalance())
                .forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        String choosenAccountType = scanner.nextLine();

        if (this.getListOfClientAccounts().stream()
                .filter(account -> account.getAccountType().name().equalsIgnoreCase(choosenAccountType))
                .toArray().length > 0) {

            System.out.println("Podaj kwotę do wypłaty (pamiętaj, że musi mieścić się ona w zakresie dostępnych śrdoków na koncie)");
            double impact = scanner.nextDouble();

            Optional<Account> choosenAccount = this.getListOfClientAccounts().stream()
                    .filter(account -> account.getAccountType().name().equalsIgnoreCase(choosenAccountType))
                    .findFirst();

            if (choosenAccount.get().getBalance() > impact) {
                choosenAccount.get().setBalance(choosenAccount.get().getBalance() - impact);

            } else {
                System.out.println("Brak wystarczających środków na koncie");
            }

            this.getListOfClientAccounts().stream()
                    .filter(account -> account.getAccountType().name().equalsIgnoreCase(choosenAccountType))
                    .forEach(account -> System.out.println("Nowe saldo to: " + account.getBalance()));

        } else {
            System.out.println("Wybrano niepoprawne konto");
        }
        return this;
    }


}