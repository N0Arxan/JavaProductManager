import java.util.Scanner;

public class TEst {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] numbers = {1, 2, 3, 4, 5, -1, 0, 0, 0};
        String[] productNames = {"mewo1" , "mewo2" , "mewo3",  "mewo4" , "mewo5" ,null,null,null,null};
        removeProduct(productNames, numbers, input);
        printArray(numbers);
        printArray(productNames);
    }

    public static void printArray(int[] numbers) {
        for (int number : numbers) {
            System.out.print(number + "\t");
        }
    }
    public static void printArray(String[] productNames) {
        for (String productName : productNames) {
            System.out.print(productName + " ");
        }
    }

    public static void removeProduct(String[] productNames, int[] productStock, Scanner input) {
        System.out.println("ingresa el posicion de producto: (para cancelar: 0)");

        int pos = input.nextInt() -1;
        int lastIndex = 5;
        if (pos >= lastIndex) {
            return;
        }
        for (int i = pos; i < lastIndex; i++) {
            productNames[i] = productNames[i + 1];
            productStock[i] = productStock[i + 1];
        }
        productNames[lastIndex] = null;
        productStock[lastIndex] = 0;
    }
}
