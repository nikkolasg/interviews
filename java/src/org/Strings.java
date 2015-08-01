package org;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by nikko on 7/28/15.
 */
public class Strings {

    public static String remove(String str,String remove) {
        char[] arr_str = str.toCharArray();
        boolean[] arr_remove = new boolean[256];
        for(int i = 0; i < arr_remove.length;i++) arr_remove[i] = false;
        for(int i = 0; i < remove.length();i++) arr_remove[remove.charAt(i)] = true;
        int j = 0;
        for(int i = 0; i < str.length();i++) {
            int c = arr_str[i];
            if(!arr_remove[c])  {
                arr_str[j++] = arr_str[i];
            }
        }
        char[] new_str = new char[j];
        System.arraycopy(arr_str,0,new_str,0,j);
        return new String(new_str);
    }

    public static String reverseWord(String str) {
        char [] arr_str = str.toCharArray();
        char [] arr2_str = new char[str.length()];
        int begin = 0,end = 0;
        int rbegin = arr_str.length;
        while (end < arr_str.length) {
            if (arr_str[end] != ' ') {
                end++;
                rbegin--;
            } else {
                System.arraycopy(arr_str,begin,arr2_str,rbegin,end-begin);
                end++; // next word directly, pass over the space
                begin = end;
                if (rbegin > 0)
                    arr2_str[--rbegin] = ' ';
            }
        }
        if (begin != end) {
            System.arraycopy(arr_str,begin,arr2_str,rbegin,end-begin);
        }
        return  new String(arr2_str);
    }


    public static int Str2Int(String str){
        int n = 0;
        char [] arr = str.toCharArray();
        boolean neg = arr[0] == '-' ? true : false;
        for (int i = str.length()-1,j = 0; i > 0; --i,j++) {

            char c = arr[i];
            int ci = (int) (c - '0');
            n += ci * Math.pow(10,j);
        }
        return neg ? -n : n;
    }


    public static String Int2Str(int n) {
        StringBuffer buf = new StringBuffer();
        boolean neg = n < 0  ? true : false;
        if (neg) n = -n;
        do {
            char c = int2char(n % 10);
            buf.append(c);
            n /= 10;
        } while(n>10);
        if (neg) buf.append("-");
        return buf.reverse().toString();
    }
    public static char int2char(int n) {
        switch(n) {
            case 0:
                return '0';
            case 1:
                return '1';
            case 2:
                return '2';
            case 3:
                return '3';
            case 4:
                return '4';
            case 5:
                return '5';
            case 6:
                return '6';
            case 7:
                return '7';
            case 8:
                return '8';
            case 9:
                return '9';
            default:
                return '0';
        }
    }
    public static void main(String[] args) {
        System.out.println("[+] String chapters JAVA");
        String str = "arrayize";
        String remove = "ai";
        System.out.println("[+] remove string " + remove + " from " + str + " => " + remove(str,remove));
        str = "A tree is, red";
        System.out.println("[+] Reverse words of '" + str + "' => '" + reverseWord(str) + "'");
        int n1 = 124;
        System.out.println("[+] Int 2 String : (int) "+ n1 + " ==> " + Int2Str(n1));
        int n2 = -154;
        int n3 = 0;
        System.out.println("[+] Int 2 String : (int) " + n2 + " ==> " +Int2Str(n2));
        System.out.println("[+] Int 2 String : (int) " + n3 + " ==> " +Int2Str(n3));
        String s1 = "124";
        String s2 = "0";
        String s3 = "-256";
        System.out.println("[+] String 2 int : (str) " + s1 + " ==> " + Str2Int(s1));
        System.out.println("[+] String 2 int : (str) " + s2 + " ==> " + Str2Int(s2));
        System.out.println("[+] String 2 int : (str) " + s3 + " ==> " + Str2Int(s3));

    }
}
