package com.company;

public enum MpaaRating {
    G(1),
    PG(2),
    PG_13(3),
    R(4),
    NC_17(5);

    int priority;

    MpaaRating(int priority) {
        this.priority = priority;
    }

    public int CompareTo(MpaaRating Enum) {
        return Integer.compare(this.priority, Enum.priority);
    }

    public static final String ratingList() {
        String nameList = "";
        for (MpaaRating rating : values()) {
            nameList += rating.name() + ", ";
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}


