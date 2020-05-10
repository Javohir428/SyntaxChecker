package com.company;

import java.util.List;
import java.util.Stack;

public class SyntaxCheck {
    List<Row> grammer;
    List<String> in;
    Integer pointer = 4;
    Integer grammerPointer = 0;
    Stack<Integer> stack = new Stack<>();

    public SyntaxCheck(List<Row> grammer, List<String> in) {
        this.grammer = grammer;
        this.in = in;
    }

    public String run() {
        while (true) {
            if (grammer.get(pointer).directionSet.contains(in.get(grammerPointer))) {
                Row currentRow = grammer.get(pointer);

                if (currentRow.isEnd == 1) {
                    return "OK";
                }

                if (currentRow.shift == 1) {
                    grammerPointer++;
                }
                if (currentRow.stack == 1) {
                    stack.push(pointer + 1);
                }

                if (currentRow.dirNum == 0) {
                    pointer = stack.pop();
                } else {
                    pointer = currentRow.dirNum;
                }
            } else if (grammer.get(pointer).errorTransition != -1) {
                pointer = grammer.get(pointer).errorTransition;
            } else {
                return "Error";
            }
        }
    }
}