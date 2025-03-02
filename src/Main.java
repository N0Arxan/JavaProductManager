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
        System.out.println(" 3. Salir");
        System.out.println(" 9. Tornar");
    }

    public static void printMenuGestioComp() {
        System.out.println("\nMenú de Gestió de compres i vendes");
        System.out.println(" 1. Compra");
        System.out.println(" 2. Venda");
        System.out.println(" 3. Llistar estocs");
        System.out.println(" 9. Tornar");
    }





    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        printMenuPrincipal();
        int opcion = input.nextInt();
        while (opcion != 9) {
            switch (opcion) {
                case 1:
                    printMenuGestioPro();
                    opcion = 9;
                    break;
                case 2:
                    printMenuGestioComp();
                    opcion = 9;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    opcion = input.nextInt();
                    break;
            }

        }

    }
}