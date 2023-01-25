package ru.andrey.bubbleSort;

public class Name implements Comparable<Name> {

    String name;

    public Name (String name) {
        this.name = name;
    }


    @Override
    public int compareTo(Name o) {
        if (this.name.compareTo(o.name) < 0) return -1;
        if (this.name.compareTo(o.name) > 0) return 1;
        return 0;
    }
}
