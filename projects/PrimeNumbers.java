//almanaque ; code
import java.util.Scanner;

public class Solution {
    //Write isPrime here
    
        public static boolean isPrime(int n) {
            if (n==1) return false;
            if (n == 2) return true;
            //loop and modulo
            for (int i=2; i < n; i++) {
                if (n % i == 0) return false;
            }
            return true;
        }
            
        //Write countPrimes here
        public static int countPrimes (int n) {
            int count = 0;
            for (int i=1; i < n; i++) {
                if (isPrime(i)) count += 1;
            }
            return count;
        }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduzca un número entero: ");
        int n = scanner.nextInt();
        scanner.close();
        System.out.println("" + n + " is prime? " + isPrime(n));
        System.out.println("Primes count from 1 to " + n + " is " + countPrimes(n));
    }
}
