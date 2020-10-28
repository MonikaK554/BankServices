package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    public static boolean peselValidator(long peselGiven) {

        String peselGivenToString = Long.toString(peselGiven);

        int year = Integer.parseInt(peselGivenToString.substring(0, 2));
        int month = Integer.parseInt(peselGivenToString.substring(2, 4));
        int day = Integer.parseInt(peselGivenToString.substring(4, 6));

        System.out.println("Rok urodzenia " + year);
        System.out.println("Miesiąc " + month);
        System.out.println("Dzień " + day);

        if (peselGivenToString.length() == 11 && year > 10 && month > 0 && month < 13) {

            if ((day > 0 && day < 32) &&
                    (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 ||
                            month == 12)) {
                System.out.println("Przypadek 1");

            } else if ((day > 0 && day < 31) &&
                    (month == 4 || month == 6 || month == 9 ||
                            month == 11)) {
                System.out.println("Przypadek 2");

            } else if (month == 2 && day > 0 && day < 30) {
                System.out.println("Przypadek 3");

                if (day < 29) {

                    System.out.println("Przypadek 3a");

                } else if (day == 29 && ((year + 1900) % 4 == 0 && (year + 1900) % 100 != 0 || (year + 1900) % 400 == 0)) {
                    System.out.println("Przypadek 3b");

                } else {
                    return false;
                }

            } else {
                System.out.println("Przypadek 4");
                return false;
            }

        } else {
            System.out.println("Przypadek 5 ");
            return false;
        }

        int sum = 1 * Integer.parseInt(peselGivenToString.substring(0, 1)) +
                3 * Integer.parseInt(peselGivenToString.substring(1, 2)) +
                7 * Integer.parseInt(peselGivenToString.substring(2, 3)) +
                9 * Integer.parseInt(peselGivenToString.substring(3, 4)) +
                1 * Integer.parseInt(peselGivenToString.substring(4, 5)) +
                3 * Integer.parseInt(peselGivenToString.substring(5, 6)) +
                7 * Integer.parseInt(peselGivenToString.substring(6, 7)) +
                9 * Integer.parseInt(peselGivenToString.substring(7, 8)) +
                1 * Integer.parseInt(peselGivenToString.substring(8, 9)) +
                3 * Integer.parseInt(peselGivenToString.substring(9, 10));
        sum %= 10;
        sum = 10 - sum;
        sum %= 10;

        System.out.println("Suma kontrolna :" + sum);

        if (sum == Integer.parseInt(peselGivenToString.substring(10))) {
            System.out.println("Walidacja nr PESEL przebiegła pomyślnie.");
            return true;
        } else {
            return false;
        }
    }

    public static boolean nameAndSurnameValidator(String nameGiven, String surnameGiven) {

        if (nameGiven != null && surnameGiven != null) {

            Pattern nameAndSurnamePattern = Pattern.compile("[A-Za-z ]*");

            Matcher nameMatcher = nameAndSurnamePattern.matcher(nameGiven);
            Matcher surnameMatcher = nameAndSurnamePattern.matcher(surnameGiven);

            if (nameMatcher.matches() && surnameMatcher.matches()) {
                System.out.println("Walidacja imienia i nazwiska przebiegła pomyślnie.");
                return true;

            } else {
                return false;
            }

        } else {
            return false;
        }
    }
}
