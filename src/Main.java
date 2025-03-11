import java.util.Scanner;

public class Main {

    public static void printMenuPrincipal() {
        System.out.println("\nMenú principal");
        System.out.println(" 1. Gestió de productes");
        System.out.println(" 2. Gestió de compres i vendes");
        System.out.println(" 9. Acabar");
    }

    public static void printMenuGestioPro() {
        System.out.println("\nMenú de gestió de productes");
        System.out.println(" 1. Afegir");
        System.out.println(" 2. Eliminar");
        System.out.println(" 3. Llistar productes");
        System.out.println(" 9. Tornar");
    }

    public static void printMenuGestioComp() {
        System.out.println("\nMenú de gestió de compres i vendes");
        System.out.println(" 1. Compra");
        System.out.println(" 2. Venda");
        System.out.println(" 3. Llistar estocs");
        System.out.println(" 9. Tornar");
    }

    public static int readOption(Scanner input) {
        System.out.print("Opció: ");
        return input.nextInt();
    }

    public static int findLastProduct(int[] productStock) {
        for (int i = 0; i < productStock.length; i++) {
            if (productStock[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    public static int mainMenuHandler(int option, Scanner input, String[] names, int[] Stockes) {
        switch (option) {
            case 1:
                gestionProMenu(input, names, Stockes);
                return 0;
            case 2:
                gestionVentaMenu(input, names, Stockes);
                return 0;
            case 9:
                return 9;
            default:
                System.out.println("\nOpció no vàlida");
                return 0;
        }
    }

    public static void gestionProMenu(Scanner input, String[] names, int[] Stockes) {
        int option = 0;
        while (option != 9) {
            printMenuGestioPro();
            option = readOption(input);
            option = gestionProMenuHandler(option, names, Stockes, input);
        }
    }

    public static int gestionProMenuHandler(int option, String[] names, int[] Stockes, Scanner input) {
        switch (option) {
            case 1:
                addProduct(names, Stockes, input);
                return 0;
            case 2:
                removeProduct(names, Stockes, input);
                return 0;
            case 3:
                printProNames(names);
                return 0;
            case 9:
                // volver
                return 9;
            default:
                // seguir el bucle
                System.out.println("\nOpció no vàlida");
                return 0;
        }
    }

    public static void addProduct(String[] productNames, int[] productStock, Scanner input) {
        // consumir la línia sobrant del menú anterior
        input.nextLine();
        System.out.print("Introdueix el nom del producte: ");
        String name = input.nextLine();

        int i = findLastProduct(productStock);
        productNames[i] = name;
        productStock[i] = 0;
        productStock[i + 1] = -1;
        System.out.println("\nProducte " + name + " afegit amb èxit");
    }

    public static void removeProduct(String[] productNames, int[] productStock, Scanner input) {
        System.out.print("Introdueix la posició del producte (per cancel·lar -> 0): ");
        int pos = input.nextInt() - 1;
        if (pos == -1) {
            System.out.println("\nOperació cancel·lada");
            return;
        }
        int lastIndex = findLastProduct(productStock);
        if (pos >= lastIndex) {
            System.out.println("\nEl producte no existeix");
            return;
        }
        String productName = productNames[pos]; // guardar el valor antes de borrar para imprimir

        for (int i = pos; i < lastIndex; i++) {
            productNames[i] = productNames[i + 1];
            productStock[i] = productStock[i + 1];
        }
        productNames[lastIndex] = null;
        productStock[lastIndex] = 0;

        System.out.println("\nProducte " + productName + " esborrat amb èxit");
    }

    public static void printProNames(String[] productNames) {
        int contador = 0;
        System.out.println();
        for (int i = 0; i < productNames.length; i++) {
            if (productNames[i] != null) {
                System.out.println("  " + (i + 1) + ". " + productNames[i]);
                contador++;
            } else {
                if (contador == 0) {
                    System.out.println("\nInventari buit");
                }
                return;
            }
        }
    }

    public static void gestionVentaMenu(Scanner input, String[] names, int[] Stockes) {
        int option = 0;
        while (option != 9) {
            printMenuGestioComp();
            option = readOption(input);
            option = gestionVentaMenuHandler(option, names, Stockes, input);
        }
    }

    public static int gestionVentaMenuHandler(int option, String[] names, int[] Stockes, Scanner input) {
        switch (option) {
            case 1:
                addStock(names, Stockes, input);
                return 0;
            case 2:
                removeStock(names, Stockes, input);
                return 0;
            case 3:
                printProStockes(Stockes);
                return 0;
            case 9:
                return 9;
            default:
                System.out.println("\nOpció no vàlida");
                return 0;
        }
    }

    public static void addStock(String[] productNames, int[] productStock, Scanner input) {
        System.out.print("Introdueix la posició del producte (per cancel·lar -> 0): ");
        int pos = input.nextInt() - 1;
        if (pos == -1) {
            System.out.println("\nOperació cancel·lada");
            return;
        }
        if (productNames[pos] == null) {
            System.out.println("\nEl producte no existeix");
            return;
        }
        System.out.print("Introdueix la quantitat: ");
        int cant = input.nextInt();
        productStock[pos] += cant;
        System.out.println("\nComprat " + cant + " de " + productNames[pos] + " amb èxit");
    }

    public static void removeStock(String[] productNames, int[] productStock, Scanner input) {
        System.out.print("Introdueix la posició del producte (per cancel·lar -> 0): ");
        int pos = input.nextInt() - 1;
        if (pos == -1) {
            System.out.println("\nOperació cancel·lada");
            return;
        }
        if (productNames[pos] == null || productStock[pos] <= 0) {
            System.out.println("\nEl producte no existeix");
            return;
        }
        System.out.print("Introdueix la quantitat: ");
        int cant = input.nextInt();

        if (productStock[pos] - cant < 0) {
            System.out.println("\nEstocs insuficients");
            return;
        }
        productStock[pos] -= cant;
        System.out.println("\nVenut " + cant + " de " + productNames[pos] + " amb èxit");
    }

    public static void printProStockes(int[] productStock) {
        int contador = 0;
        System.out.println();
        for (int i = 0; i < productStock.length; i++) {
            if (productStock[i] != -1) {
                System.out.println("   " + (i + 1) + ". " + productStock[i]);
                contador++;
            } else {
                if (contador == 0) {
                    System.out.println("\nInventari buit");
                }
                return;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] names = new String[100];
        int[] stocks = new int[100];
        stocks[0] = -1; // valor sentinella

        int option = 0;
        while (option != 9) {
            printMenuPrincipal();
            option = readOption(input);
            option = mainMenuHandler(option, input, names, stocks);
        }
        input.close();
    }
}
