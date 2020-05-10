package com.company;

import java.util.HashSet;

public class Row {
    public HashSet<String> directionSet = new HashSet<>();
    public Integer shift = -1;
    public Integer dirNum = 0;
    public Integer stack = -1;
    public Integer errorTransition = -1;
    public Integer isEnd = -1;
}