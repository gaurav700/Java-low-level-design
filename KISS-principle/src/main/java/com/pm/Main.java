package com.pm;


//TIP Problem: A developer built an overengineered string formatting system. There is a FormatterRegistry that stores formatters, a FormatterFactory that creates them, and a FormatterChain that applies them in sequence. All of this just to format a user's display name (trim whitespace and capitalize the first letter).
//Your task: replace the entire system with a single formatDisplayName method.
//<br>
//Requirements:
//Accept a raw name string (e.g., " john doe ")<br>
//Trim leading and trailing whitespace<br>
//Capitalize the first letter of the result<br>
//Return the formatted string (e.g., "John doe")<br>
class DisplayNameFormatter{
    public  String formatDisplayName(String name){
        return name.trim().substring(0,1).toUpperCase() + name.trim().substring(1);
    }
}

public class Main {
    public static void main(String[] args) {
        DisplayNameFormatter formatter = new DisplayNameFormatter();
        System.out.println(formatter.formatDisplayName("  john doe  "));
        System.out.println(formatter.formatDisplayName("ALICE"));
        System.out.println(formatter.formatDisplayName("  bob  "));
    }
}