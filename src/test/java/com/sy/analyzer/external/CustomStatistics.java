package com.sy.analyzer.external;

import com.sy.analyzer.Analysis;

/* Create a custom statistics class */
public class CustomStatistics implements Analysis {
    private int value;

    public CustomStatistics(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
