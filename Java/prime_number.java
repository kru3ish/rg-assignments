package Java;

import java.util.Scanner;

public class prime_number {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();

        if (isPrime(num)) {
            System.out.println(num + " is a Prime Number");
        } else {
            System.out.println(num + " is Not a Prime Number");
        }

        scanner.close();
    }

    // Method to check if a number is prime
    static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
