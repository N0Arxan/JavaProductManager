import java.util.Scanner;

public class Main {
    /**
     * Los siguientes métodos se encargan de imprimir los menús de la aplicación (parte visual):
     * - Menú principal
     * - Menú de gestión de productos
     * - Menú de gestión de compras y ventas
     */
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

    /**
     * Lee la opción ingresada por el usuario desde el teclado en cada menú.
     * @param input Objeto Scanner para leer la entrada del usuario.
     * @return Un entero que representa la opción ingresada.
     */
    public static int readOption(Scanner input) {
        System.out.print("Opció: ");
        return input.nextInt();
    }

    /**
     * Recorre la lista de stocks para encontrar el valor -1, que actúa como sentinela.
     * @param productStock Array de enteros que representa los stocks de los productos.
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
     * Verifica si el número proporcionado es 0, lo que indica que la operación fue cancelada.
     * @param number Número ingresado por el usuario.
     * @return true si el número es 0, false en caso contrario.
     */
    public static boolean isCanceled(int number) {
        return number == 0;
    }

    /**
     * Controla la funcionalidad del menú principal. El programa permanecerá en este bucle
     * hasta que el usuario ingrese el número 9. La entrada del usuario se maneja mediante un handler.
     */
    public static void mainMenu(Scanner input, String[] names, int[] stocks){
        int option = 0;
        while (option != 9) {
            printMenuPrincipal();
            option = readOption(input);
            option = mainMenuHandler(option, input, names, stocks);
        }
    }
    /**
     * Maneja la opción ingresada en el menú principal. Si el usuario selecciona una opción válida,
     * se dirige al menú correspondiente. Al retornar, actualiza la variable 'option' para continuar o salir del bucle.
     * @param option Opción seleccionada por el usuario.
     * @param input Objeto Scanner para leer la entrada del usuario.
     * @param names Array de nombres de productos.
     * @param Stockes Array de stocks de productos.
     * @return La opción actualizada basada en la entrada del usuario.
     */
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

    /**
     * Controla el menú de gestión de productos. El programa permanecerá en este bucle
     * hasta que el usuario ingrese el número 9, momento en el cual se regresa al menú principal.
     * La entrada del usuario se maneja mediante un handler.
     */
    public static void gestionProMenu(Scanner input, String[] names, int[] Stockes) {
        int option = 0;
        while (option != 9) {
            printMenuGestioPro();
            option = readOption(input);
            option = gestionProMenuHandler(option, names, Stockes, input);
        }
    }

    /**
     * Maneja la opción ingresada en el menú de gestión de productos. Si el usuario selecciona una opción válida,
     * se dirige a la funcionalidad correspondiente. Si la entrada es inválida o el usuario ingresa 9,
     * se actualiza la variable 'option' para determinar si se continúa o se sale del bucle.
     * @param option Opción seleccionada por el usuario.
     * @param names Array de nombres de productos.
     * @param Stockes Array de stocks de productos.
     * @param input Objeto Scanner para leer la entrada del usuario.
     * @return La opción actualizada basada en la entrada del usuario.
     */
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
                // Volver al menú anterior
                return 9;
            default:
                // Mantener el bucle
                System.out.println("\nOpció no vàlida");
                return 0;
        }
    }

    /**
     * Agrega un nuevo producto a la lista.
     * Se consulta la posición del último producto (identificada por el valor -1, sentinela),
     * se inserta el nuevo producto en esa posición y se inicializa su stock a 0.
     * Finalmente, se mueve la sentinela a la siguiente posición para futuras inserciones.
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
     * (se resta 1 para obtener el índice correcto).
     * Verifica que el índice sea válido y que la operación no haya sido cancelada.
     *
     * <p>Posteriormente, a partir del índice indicado, cada elemento se desplaza una posición hacia la izquierda,
     * y se elimina el último elemento de ambos arrays.</p>
     *
     * <p>Ejemplo: para eliminar el producto en la posición 1 de la lista:</p>
     * <p>[1,2,3,4,-1,0,0,0,0]  -- valor inicial</p>
     * <p>Primer paso: [1,3,4,-1,-1,0,0,0,0]</p>
     * <p>Segundo paso (eliminar el último): [1,3,4,-1,0,0,0,0,0]</p>
     *
     */
    public static void removeProduct(String[] productNames, int[] productStock, Scanner input) {
        System.out.print("Introdueix la posició del producte (per cancel·lar -> 0): ");
        int pos = input.nextInt() - 1;
        if (isCanceled(pos+1)) {
            System.out.println("\nOperació cancel·lada");
            return;
        }
        int lastIndex = findLastProduct(productStock);
        if (pos >= lastIndex) {
            System.out.println("\nEl producte no existeix");
            return;
        }

        String productName = productNames[pos]; // Guardar el valor antes de borrar para imprimir después

        for (int i = pos; i < lastIndex; i++) {
            productNames[i] = productNames[i + 1];
            productStock[i] = productStock[i + 1];
        }
        productNames[lastIndex] = null;
        productStock[lastIndex] = 0;

        System.out.println("\nProducte " + productName + " esborrat amb èxit");
    }

    /**
     * Imprime en pantalla la lista de nombres de productos hasta encontrar un elemento nulo,
     * lo que indica el final de la lista. Si no se encuentra ningún producto, muestra "Inventari buit".
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
     * Controla el menú de gestión de compres i vendes. El programa permanecerá en este bucle
     * hasta que el usuario ingrese el número 9, momento en el cual se regresa al menú principal.
     * La entrada del usuario se maneja mediante un handler.
     */
    public static void gestionVentaMenu(Scanner input, String[] names, int[] Stockes) {
        int option = 0;
        while (option != 9) {
            printMenuGestioComp();
            option = readOption(input);
            option = gestionVentaMenuHandler(option, names, Stockes, input);
        }
    }

    /**
     * Maneja la opción ingresada en el menú de gestión de compres i vendes. Si el usuario selecciona una opción válida,
     * se dirige a la funcionalidad correspondiente. Si la entrada es inválida o el usuario ingresa 9,
     * se actualiza la variable 'option' para determinar si se continúa o se sale del bucle.
     * @param option Opción seleccionada por el usuario.
     * @param names Array de nombres de productos.
     * @param Stockes Array de stocks de productos.
     * @param input Objeto Scanner para leer la entrada del usuario.
     * @return La opción actualizada basada en la entrada del usuario.
     */
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

    /**
     * Permite comprar productos, es decir, incrementar la cantidad en stock de un producto.
     * Se solicita la posición del producto (se resta 1 para obtener el índice correcto), se verifica que el producto exista
     * y que la operación no haya sido cancelada.
     *
     * <p>Se suma la cantidad especificada al stock actual del producto en el array correspondiente.</p>
     *
     */
    public static void addStock(String[] productNames, int[] productStock, Scanner input) {
        System.out.print("Introdueix la posició del producte (per cancel·lar -> 0): ");
        int pos = input.nextInt() - 1;
        if (isCanceled(pos+1)) {
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
     * Permite vender productos, es decir, restar una cantidad del stock de un producto.
     * Se solicita la posición del producto (se resta 1 para obtener el índice correcto), y se verifica que el producto exista,
     * que su stock sea mayor que 0 y que la operación no haya sido cancelada. Además, se comprueba que la cantidad a vender
     * no exceda el stock disponible.
     *
     * <p>Si es correcto hasta aqui, se resta la cantidad especificada del stock actual del producto en el array correspondiente.</p>
     *
     */
    public static void removeStock(String[] productNames, int[] productStock, Scanner input) {
        System.out.print("Introdueix la posició del producte (per cancel·lar -> 0): ");
        int pos = input.nextInt() - 1;
        if (isCanceled(pos+1)) {
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
     * Imprime en pantalla la cantidad de productos (stock) para cada producto hasta encontrar la sentinela (-1).
     * Si no hay productos registrados, muestra "Inventari buit".
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
        // Inicializar
        Scanner input = new Scanner(System.in);
        String[] names = new String[100]; // Lista de nombres de productos (máximo 99)
        int[] stocks = new int[100]; // Lista de stocks de productos (máximo 99)
        stocks[0] = -1; // Valor sentinela para indicar el final de la lista de productos

        mainMenu(input, names, stocks); // Iniciar el programa ejecutando el bucle del menú principal
    }
}
