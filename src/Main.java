import jdk.jfr.Name;

import java.util.Scanner;

public class Main {

    public static void printMenuPrincipal() {
        System.out.println("\nMenuPrincipal");
        System.out.println(" 1. Gestió de productes");
        System.out.println(" 2. Gestió de compres i vendes");
        System.out.println(" 9. Acabar");
    }

    public static void printMenuGestioPro() {
        System.out.println("\nMenú de Gestió de productes");
        System.out.println(" 1. Afegir");
        System.out.println(" 2. Eliminar");
        System.out.println(" 3. Llistar productes");
        System.out.println(" 9. Tornar");
    }

    public static void printMenuGestioComp() {
        System.out.println("\nMenú de Gestió de compres i vendes");
        System.out.println(" 1. Compra");
        System.out.println(" 2. Venda");
        System.out.println(" 3. Llistar estocs");
        System.out.println(" 9. Tornar");
    }


    public static int readOption(Scanner input) {
        return input.nextInt();
    }

    public static int mainMenuHandler(int option , Scanner input ,String[] names , int[] Stockes , int numProduct) {
        switch (option) {
            case 1:
                gestionProMenu(input);
                return 0;
            case 2:
                printMenuGestioComp();
                return 0;
            case 9:
                return 9;
            default:
                System.out.println("Opcion no valida");
                return 0;
        }
    }

    public static void gestionProMenu(Scanner input) {
        int option = 0 ;
        while (option != 9) {
            printMenuGestioPro();
            option = readOption(input);
            //gestionMenuHandler(option);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] names = new String[100];
        int[] stocks = new int[100];
        int numProducts = 0;
        int option = 0;
        while (option != 9) {
            printMenuPrincipal();
            option = readOption(input);
            numProducts = mainMenuHandler(option , input , names , stocks , numProducts);
        }
    }
}