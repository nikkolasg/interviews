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

    public static void main(String[] args) {
        System.out.println("[+] String chapters JAVA");
        String str = "arrayize";
        String remove = "ai";
        System.out.println("[+] remove string " + remove + " from " + str + " => " + remove(str,remove));
        str = "A tree is, red";
        System.out.println("[+] Reverse words of '" + str + "' => '" + reverseWord(str) + "'");
    }
}
