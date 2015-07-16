package org;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Phone {

    public static void main(String[] args) {
        System.out.println("[+] Interviews questions");
        String s1 = "alula";
        String reversed = ReverseString(s1);
        System.out.println("[+] String " + s1 + " reversed : " + reversed);
        System.out.println("[+] Fibonacci of 10 : " + Fibonacci(10));
        System.out.println("[+] 12 x 12 Multiplication Number :");
        PrintMultiplication();
        System.out.println("[+] Sum file ints.txt : " + SumIntFile("ints.txt"));
        System.out.println("[+] Print odd number until 61 :");
        PrintOdd(61);
    }

    public static void PrintOdd(int n) {
        for (int i = 1; i <= n; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    public static int SumIntFile(String filename) {
        try {
            Stream<String> lines = Files.lines(Paths.get("src/" + filename));
            return lines.map(Integer::parseInt).reduce(0, (a, b) -> a + b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void PrintMultiplication() {
        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j <= 12; j++) {
                System.out.print(i * j + " ");
            }
            System.out.println("");
        }
    }

    public static String ReverseString(String s) {
        char arr[] = s.toCharArray();
        for (int i = 0; i < (s.length() / 2); i++) {
            char tmp = arr[i];
            arr[i] = arr[s.length() - i - 1];
            arr[s.length() - i - 1] = tmp;
        }
        return new String(arr);
    }

    public static int Fibonacci(int n) {
        return n <= 1 ? n : Fibonacci(n - 1) + Fibonacci(n - 2);
    }
}
