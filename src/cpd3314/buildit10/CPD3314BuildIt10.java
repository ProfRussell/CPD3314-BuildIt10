/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpd3314.buildit10;

import java.util.Scanner;

/**
 *
 * @author c0587637
 */
public class CPD3314BuildIt10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        doBuildIt1();
        doBuildIt2();
        doBuildIt3();
    }

    /**
     * Using the Character class methods to verify a Canadian postal code
     */
    public static void doBuildIt1() {
        // Get the User Input
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter a Postal Code:");
        String input = kb.nextLine();

        // Perform the Actual Checking
        if (Character.isLetter(input.charAt(0))
                && Character.isDigit(input.charAt(1))
                && Character.isLetter(input.charAt(2))
                && Character.isSpaceChar(input.charAt(3))
                && Character.isDigit(input.charAt(4))
                && Character.isLetter(input.charAt(5))
                && Character.isDigit(input.charAt(6))) {
            System.out.println("Valid Postal Code: " + input);
        } else {
            System.out.println(input + " is not a valid Postal Code");
        }

        // BONUS Here's how it's really done:
        if (input.matches("[a-zA-Z][0-9][a-zA-Z] ?[0-9][a-zA-Z][0-9]")) {
            System.out.println("Valid Postal Code: " + input);
        }
    }

    /**
     * Verifies and Parses some XML into Data
     */
    public static void doBuildIt2() {
        // The original data
        String[] xml = {"<person>",
            "    <name>Bob</name>",
            "    <age>42</age>",
            "</person>"
        };

        // Build a trimmed single string
        String xmlTrimmed = "";
        for (String s : xml) {
            xmlTrimmed += s.trim();
        }

        // Find the location of the name and age tags
        int nameStart = xmlTrimmed.indexOf("<name>");
        int nameEnd = xmlTrimmed.indexOf("</name>");
        int ageStart = xmlTrimmed.indexOf("<age>");
        int ageEnd = xmlTrimmed.indexOf("</age>");

        // Determine if the person, name and age tags are correct
        boolean isPerson = xmlTrimmed.startsWith("<person>")
                && xmlTrimmed.endsWith("</person>")
                && nameStart < nameEnd
                && ageStart < ageEnd
                && nameEnd < ageStart;

        // Grab out the values -- Note the offset to move past the text
        String name = xmlTrimmed.substring(nameStart + 6, nameEnd);
        String age = xmlTrimmed.substring(ageStart + 5, ageEnd);

        // Output
        System.out.println("Is this a Person? " + isPerson);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);

        // And again, using Regular Expressions:
        boolean isPersonRegex = xmlTrimmed.matches("^<person><name>[A-Za-z]+</name><age>[0-9]+</age></person>$");
        String[] values = xmlTrimmed.split("</?\\w+>");
        String nameRegex = values[2];
        String ageRegex = values[4];

        System.out.println("Is this a Person? " + isPersonRegex);
        System.out.println("Name: " + nameRegex);
        System.out.println("Age: " + ageRegex);
    }

    /**
     * Builds some Input and Counts its Words
     */
    public static void doBuildIt3() {
        // Build the Initial Tools
        StringBuilder str = new StringBuilder();
        Scanner kb = new Scanner(System.in);
        String input;
        boolean cantExit;

        // Give the User Instructions
        System.out.println("Enter some text. Enter --- as a single line to exit.");

        // Loop Until ---
        do {
            input = kb.nextLine();
            cantExit = !input.equals("---");
            if (cantExit) {
                // Trip the input to remove extra spaces
                str.append(input.trim());
                // Make sure the first word of the next line is separate
                str.append(" ");
            }
        } while (cantExit);

        // Split the String by Spaces
        String[] words = str.toString().split(" ");

        // Fire Out the Word Count
        System.out.println("Word Count: " + words.length);        
    }
}
