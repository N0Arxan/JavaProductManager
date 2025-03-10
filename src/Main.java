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
                printMenuGestioComp();
                return 0;
            case 9:
                return 9;
            default:
                System.out.println("Opcion no valida");
                return 0;
        }
    }

    public static void gestionProMenu(Scanner input, String[] names, int[] Stockes) {
        int option = 0;
        while (option != 9) {
            printMenuGestioPro();
            option = readOption(input);
            gestionProMenuHandler(option, names, Stockes);
        }
    }

    public static int gestionProMenuHandler(int option, String[] names, int[] Stockes) {
        switch (option) {
            case 1:
                addProduct(names, Stockes, new Scanner(System.in));
                return 0;
            case 2:
                removeProduct(names, Stockes, new Scanner(System.in));
                return 0;
            case 3:
                printProNames(names);
                return 0;
            case 9:
                //volver
                return 9;
            default:
                //seguir el bucle
                System.out.println("Opcion no valida");
                return 0;
        }
    }

    public static void addProduct(String[] productNames, int[] productStock, Scanner input) {
        System.out.println("Ingrese el nombre del producto: (para cancelar: 0)");
        String name = input.nextLine();
        int i = findLastProduct(productStock);
        productNames[i] = name;
        productStock[i] = 0;
        productStock[i + 1] = -1;
    }

    public static void removeProduct(String[] productNames, int[] productStock, Scanner input) {
        System.out.println("ingresa el posicion de producto: (para cancelar: 0)");
        int pos = input.nextInt() - 1;
        int lastIndex = findLastProduct(productStock);
        if (pos >= lastIndex) {
            System.out.println("Fuck you");
            return;
        }
        for (int i = pos; i < lastIndex; i++) {
            productNames[i] = productNames[i + 1];
            productStock[i] = productStock[i + 1];
        }
        productNames[lastIndex] = null;
        productStock[lastIndex] = 0;
    }

    public static void printProNames(String[] productNames) {
        for (int i = 0; i < productNames.length; i++) {
            if (productNames[i] != null) {
                System.out.println(i + 1 + ". " + productNames[i]);
            } else {
                return;
            }
        }
    }


    public static void gestionVentaMenu(Scanner input) {
        int option = 0;
        while (option != 9) {
            printMenuGestioComp();
            option = readOption(input);
            option = gestionVentaMenuHandler(option);
        }
    }

    public static int gestionVentaMenuHandler(int option) {
        switch (option) {
            case 1:
                //compra
            case 2:
                //venta
            case 3:
                //llistar
            case 9:
                return 9;
            default:
                System.out.println("Opcion no valida");
                return 0;
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] names = new String[100];
        int[] stocks = new int[100];
        stocks[0] = -1;
        int numProducts = 0;
        int option = 0;
        while (option != 9) {
            printMenuPrincipal();
            option = readOption(input);
            option = mainMenuHandler(option, input, names, stocks);
        }
    }
}