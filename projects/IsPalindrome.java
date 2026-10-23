//almanaque ; code
import java.util.Scanner;

public class Solution {
    
    public static boolean isPalindrome(int n) {
        if (n < 0) return false;
        
        int original = n;
        int invertido = 0;
        int numTemporal = n;
        
        while (numTemporal != 0) {
            int digito = numTemporal % 10;
            invertido = (invertido * 10) + digito;
            numTemporal /= 10;
        }
        
        return original == invertido;
    }

    public static int countPalindromes(int n) {
        int count = 0;
        for (int i = 0; i <= n; i++) {
            if (isPalindrome(i)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            System.out.println(isPalindrome(n));
            System.out.println(countPalindromes(n));
        }
        scanner.close();
    }
}
