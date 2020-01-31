package checkers;
import java.util.Scanner;
import java.util.function.*;
import java.io.*;
public class IntScanner {
    public static int[] scanInts(Scanner scan, int number, Function<int[], Boolean> isValid, String message, PrintStream out) {
        int[] ints = new int[number];
        out.println("Insert " + number + " integer numbers for " + message);
        //boolean passed = false;
        outer: while (true) {
            int index = 0;
            for (int i = 0; i < number; i++) {
                if (scan.hasNextInt()) {
                    ints[index++] = scan.nextInt();
                } else {
                    for (; i < number; i++) {
                        scan.next();
                    }
                    out.println("Incorrect input. Please insert "
                           + number + " integer numbers");
                    continue outer;
                }
            }
            if (isValid.apply(ints)) {
                return ints;
            }
            out.println("Incorrect parameters. Try again:");
            
        }
        // return ints;
    }

    public static int[] scanInts(Scanner scan, int number, String message, PrintStream out) {
        return scanInts(scan, number, (ints) -> true, message, out);
    }
}