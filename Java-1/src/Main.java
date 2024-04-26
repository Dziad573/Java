import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię ");
        String imie = scanner.nextLine();
        System.out.println("Podaj nazwisko ");
        String nazwisko = scanner.nextLine();
        System.out.println("Podaj wiek ");
        int wiek = scanner.nextInt();

        System.out.println("Imię: " + imie + "\nNazwisko: " + nazwisko + "\nWiek: " + wiek);
        */

        /*
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj a: ");
        float a = scan.nextFloat();
        System.out.println("Podaj b: ");
        float b = scan.nextFloat();
        float dod = a + b;
        float min = a - b;
        float mno = a * b;
        System.out.println(
                "Wynik dodawania: " + dod +
                "\nWynik odejmowania: " + min +
                "\nWynik mnożenia: " + mno +
                "\nWynik dzielenia: " + (a / b)
        );
        */

        // ########## ZAOKRĄGLANIE ##########
        /*
        double a = 1.44;
        double b = 43.1;
        double c = a*b;
        double d = a*b;
        System.out.println(Math.round(d));

        String roundedNumber = String.format("%.0f",c);
        System.out.println(roundedNumber);
        */

        String[] tab = {"ga", "ge", "baab", "fraw"};

        for(int i = 0; i< tab.length; i++){
            tab[i] = tab[i] + "faew" + (i + 1);
            System.out.println(tab[i]);
        }



    }
}