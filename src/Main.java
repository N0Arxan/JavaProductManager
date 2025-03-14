import java.util.Scanner;

/**
 * 14/03/2025
 * @author Arshan_Hz <p><p/>
 * Refer to <a href="https://github.com/N0Arxan/JavaProductManager">GitHub</a>
 */
public class Main {

    /**
     * Método genérico para imprimir un menú.
     * @param header Título del menú.
     * @param options Opciones del menú.
     */
    public static void printMenu(String header, String[] options) {
        System.out.println("\n" + header);
        for (String option : options) {
            System.out.println(option);
        }
    }

    /**
     * Lee la opción ingresada por el usuario desde el teclado.
     * @param input Objeto Scanner para leer la entrada del usuario.
     * @return Un entero que representa la opción ingresada.
     */
    public static int readOption(Scanner input) {
        System.out.print("Opció: ");
        return input.nextInt();
    }

    /**
     * Recorre el array de stocks para encontrar la posición del valor sentinela (-1).
     * @param productStock Array de enteros que representa el stock de cada producto.
     * @return El índice donde se encontró el valor sentinela (posición del último producto + 1).
     */
    public static int findLastProduct(int[] productStock) {
        for (int i = 0; i < productStock.length; i++) {
            if (productStock[i] == -1) {
                return i;
            }
        }
        return 99;
    }

    /**
     * Verifica si la entrada del usuario es 0, lo que indica que la operación fue cancelada.
     * @param number Número ingresado por el usuario.
     * @return true si el número es 0, false en caso contrario.
     */
    public static boolean isCanceled(int number) {
        return number == 0;
    }

    /**
     * Controla la funcionalidad del menú principal.
     * Permanece en el bucle hasta que el usuario ingrese el número 9.
     */
    public static void mainMenu(Scanner input, String[] names, int[] stocks){
        int option = 0;
        while (option != 9) {
            printMenu("Menú principal", new String[] {
                " 1. Gestió de productes",
                " 2. Gestió de compres i vendes",
                " 9. Acabar"
            });
            option = readOption(input);
            option = mainMenuHandler(option, input, names, stocks);
        }
    }

    /**
     * Maneja la opción ingresada en el menú principal y dirige al usuario al submenú correspondiente.
     * @return La opción actualizada basada en la entrada del usuario.
     */
    public static int mainMenuHandler(int option, Scanner input, String[] names, int[] stocks) {
        switch (option) {
            case 1:
                gestionProMenu(input, names, stocks);
                return 0;
            case 2:
                gestionVentaMenu(input, names, stocks);
                return 0;
            case 9:
                return 9;
            default:
                System.out.println("\nOpció no vàlida");
                return 0;
        }
    }

    /**
     * Controla el menú de gestión de productos.
     * Permanece en el bucle hasta que el usuario ingrese el número 9, volviendo al menú principal.
     */
    public static void gestionProMenu(Scanner input, String[] names, int[] stocks) {
        int option = 0;
        while (option != 9) {
            printMenu("Menú de gestió de productes", new String[] {
                " 1. Afegir",
                " 2. Eliminar",
                " 3. Llistar productes",
                " 9. Tornar"
            });
            option = readOption(input);
            option = gestionProMenuHandler(option, names, stocks, input);
        }
    }

    /**
     * Maneja la opción ingresada en el menú de gestión de productos.
     * @return La opción actualizada basada en la entrada del usuario.
     */
    public static int gestionProMenuHandler(int option, String[] names, int[] stocks, Scanner input) {
        switch (option) {
            case 1:
                addProduct(names, stocks, input);
                return 0;
            case 2:
                removeProduct(names, stocks, input);
                return 0;
            case 3:
                printProNames(names);
                return 0;
            case 9:
                return 9;
            default:
                System.out.println("\nOpció no vàlida");
                return 0;
        }
    }

    /**
     * Agrega un nuevo producto a la lista.
     * Se consulta la posición del último producto (valor sentinela -1), se inserta el nuevo producto
     * en esa posición y se inicializa su stock a 0. Luego, se desplaza el valor sentinela a la siguiente posición.
     */
    public static void addProduct(String[] productNames, int[] productStock, Scanner input) {
        input.nextLine(); // Consumir la línea sobrante del menú anterior
        System.out.print("Introdueix el nom del producte: ");
        String name = input.nextLine();

        int i = findLastProduct(productStock);
        productNames[i] = name;
        productStock[i] = 0;
        productStock[i + 1] = -1;
        System.out.println("\nProducte " + name + " afegit amb èxit");
    }

    /**
     * Elimina un producto de la lista basado en la posición ingresada por el usuario
     * (se resta 1 para obtener el índice correcto). Verifica que la posición sea válida y que la operación
     * no haya sido cancelada. Luego, desplaza los elementos restantes y elimina el producto.
     */
    public static void removeProduct(String[] productNames, int[] productStock, Scanner input) {
        System.out.print("Introdueix la posició del producte (per cancel·lar -> 0): ");
        int pos = input.nextInt() - 1;
        if (isCanceled(pos + 1)) {
            System.out.println("\nOperació cancel·lada");
            return;
        }
        int lastIndex = findLastProduct(productStock);
        if (pos >= lastIndex) {
            System.out.println("\nEl producte no existeix");
            return;
        }

        String productName = productNames[pos]; // Guardar el valor antes de borrar

        for (int i = pos; i < lastIndex; i++) {
            productNames[i] = productNames[i + 1];
            productStock[i] = productStock[i + 1];
        }
        productNames[lastIndex] = null;
        productStock[lastIndex] = 0;

        System.out.println("\nProducte " + productName + " esborrat amb èxit");
    }

    /**
     * Imprime la lista de nombres de productos. Si no hay productos, muestra "Inventari buit".
     */
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

    /**
     * Controla el menú de gestión de compres i vendes.
     * Permanece en el bucle hasta que el usuario ingrese el número 9, volviendo al menú principal.
     */
    public static void gestionVentaMenu(Scanner input, String[] names, int[] stocks) {
        int option = 0;
        while (option != 9) {
            printMenu("Menú de gestió de compres i vendes", new String[] {
                " 1. Compra",
                " 2. Venda",
                " 3. Llistar estocs",
                " 9. Tornar"
            });
            option = readOption(input);
            option = gestionVentaMenuHandler(option, names, stocks, input);
        }
    }

    /**
     * Maneja la opción ingresada en el menú de gestión de compres i vendes.
     * @return La opción actualizada basada en la entrada del usuario.
     */
    public static int gestionVentaMenuHandler(int option, String[] names, int[] stocks, Scanner input) {
        switch (option) {
            case 1:
                addStock(names, stocks, input);
                return 0;
            case 2:
                removeStock(names, stocks, input);
                return 0;
            case 3:
                printProStockes(stocks);
                return 0;
            case 9:
                return 9;
            default:
                System.out.println("\nOpció no vàlida");
                return 0;
        }
    }

    /**
     * Permite incrementar el stock de un producto (compra).
     */
    public static void addStock(String[] productNames, int[] productStock, Scanner input) {
        System.out.print("Introdueix la posició del producte (per cancel·lar -> 0): ");
        int pos = input.nextInt() - 1;
        if (isCanceled(pos + 1)) {
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

    /**
     * Permite disminuir el stock de un producto (venta).
     */
    public static void removeStock(String[] productNames, int[] productStock, Scanner input) {
        System.out.print("Introdueix la posició del producte (per cancel·lar -> 0): ");
        int pos = input.nextInt() - 1;
        if (isCanceled(pos + 1)) {
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

    /**
     * Imprime la cantidad de productos (stock) hasta encontrar el valor sentinela (-1).
     * Si no hay productos, muestra "Inventari buit".
     */
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

    /**
     * Método principal que inicializa las variables y arranca el programa.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] names = new String[100];  // Lista de nombres de productos (máximo 99)
        int[] stocks = new int[100];         // Lista de stocks de productos (máximo 99)
        stocks[0] = -1; // Valor sentinela para indicar el final de la lista de productos

        mainMenu(input, names, stocks);
    }
}
