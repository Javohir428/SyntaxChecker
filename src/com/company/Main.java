package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Row> rows = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("./src/com/company/output.txt"));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                Row row = new Row();
                scanner.next();
                String dirSetString = scanner.next();
                row.directionSet = new HashSet<String>( Arrays.asList( dirSetString.split(",", -1) ) );
                row.shift = Integer.parseInt(scanner.next());
                row.dirNum = Integer.parseInt(scanner.next());
                row.stack = Integer.parseInt(scanner.next());
                row.errorTransition = Integer.parseInt(scanner.next());
                row.isEnd = Integer.parseInt(scanner.next());
                rows.add(row);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }


        List<String> ins = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("./src/com/company/input.txt"));
            while (scanner.hasNext()) {
                ins.add(scanner.next());
            }
            ins.add("#");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        SyntaxCheck syntaxCheck = new SyntaxCheck(rows, ins);
        if (syntaxCheck.Run())
        {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }
    }
}
