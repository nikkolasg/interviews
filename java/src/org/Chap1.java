package org;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikko on 7/16/15.
 */
public class Chap1 {

    public static void main(String[] args) {
        System.out.println("[+] Chapter 1 questions ...");
        UniqueCharsTest();
        RotationBy90Test();
        StringPermutationTest();
        ReplaceSpaceTest();
    }

    public static void StringPermutationTest() {
        String s1 = "hello";
        String s2 = "loehl";
        System.out.println("[+] String permutation " + s1 + " vs " + s2 + " : " + IsStringPermutation(s1, s2));
        s2 = "loehl2";
        System.out.println("[+] String permutation " + s1 + " vs " + s2 + " : " + IsStringPermutation(s1, s2));
    }

    public static void ReplaceSpaceTest() {
        char[] s1 = { 'j','o','n',' ','i','s',' ','c','o','n',' ',' ',' ',' '};
        int strLength = 10;
        System.out.print("[+] ReplaceSpaceTest with " + new String(s1) + " => " );
        ReplaceSpace(s1, strLength);
        System.out.print(new String(s1) + "\n");
    }
    public static void ReplaceSpace(char[] array, int strLength) {
        int n = array.length;
        int j = array.length - 1;
        for (int i = strLength - 1; i >= 0; i--) {
            if (array[i] == ' ') {
                array[j] = '0';
                array[j-1] = '2';
                array[j-2] = '%';
                j -= 3;
            } else {
                array[j] = array[i];
                j--;
            }
        }
    }

    public static boolean IsStringPermutation(String s1, String s2) {

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            int count = map.getOrDefault(s1.charAt(i), 0);
            map.put(s1.charAt(i), ++count);
        }
        for (int i = 0; i < s2.length(); i++) {
            int count = map.getOrDefault(s2.charAt(i), 0);
            map.put(s2.charAt(i), --count);
        }
        for (Map.Entry<Character, Integer> ent : map.entrySet()) {
            if (ent.getValue() != 0) return false;
        }
        return true;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    public static void RotationBy90Test() {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int[][] rotated = RotationBy90(matrix);
        System.out.println("[+] Matrix original ");
        printMatrix(matrix);
        System.out.println("[+] ==> Matrix rotated : ");
        printMatrix(rotated);
        int[][] matrix2 = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        System.out.println("[+] Matrix original : ");
        printMatrix(matrix2);
        System.out.println("[+] Matrix rotated INPLACE: ");
        RotationBy90Inplace(matrix2);
        printMatrix(matrix2);


    }

    public static void RotationBy90Inplace(int[][] matrix) {
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int offset = first; offset < last; offset++) {
                int topleft = matrix[first + offset][first];
                int botleft = matrix[last][first + offset];
                int botright = matrix[last - offset][last];
                int topright = matrix[first][last - offset];
                matrix[first + offset][first] = topright;
                matrix[last][first + offset] = topleft;
                matrix[last - offset][last] = botleft;
                matrix[first][last - offset] = botright;
            }
        }
    }

    public static int[][] RotationBy90(int[][] matrix) {
        int[][] rotated = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            rotated[i] = new int[matrix.length];
        }

        // take row i
        for (int i = 0; i < matrix.length; i++) {
            // take column j
            for (int j = 0; j < matrix.length; j++) {
                // put it in row length - 1 - i
                rotated[matrix.length - 1 - j][i] = matrix[i][j];
            }
        }
        return rotated;
    }

    public static void UniqueCharsTest() {
        String p1 = "chars";
        String p2 = "charas";
        System.out.println("[+] Unique Chars in " + p1 + " : " + UniqueChars(p1));
        System.out.println("[+] Unique Chars in " + p2 + " : " + UniqueChars(p2));
        System.out.println("[+] FAST Unique Chars in " + p2 + " : " + UniqueCharsFast(p2));

    }

    public static boolean UniqueCharsFast(String s) {
        if (s.length() > 256) return false;

        boolean[] chars = new boolean[256];
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (chars[c])
                return false;
            chars[c] = true;
        }
        return true;
    }

    public static boolean UniqueChars(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) return false;
        }
        return true;
    }

}
