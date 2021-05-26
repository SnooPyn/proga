package com.company;

public enum MpaaRating {
    G,
    PG,
    PG_13,
    R,
    NC_17;

    public int CompareTo(MpaaRating Enum){
    int firstEnum = 0;
    int secondEnum = 0;
    if (Enum.equals(MpaaRating.G)) firstEnum = 1;
    if (Enum.equals(MpaaRating.PG)) firstEnum = 2;
    if (Enum.equals(MpaaRating.PG_13)) firstEnum = 3;
    if (Enum.equals(MpaaRating.R)) firstEnum = 4;
    if (Enum.equals(MpaaRating.NC_17)) firstEnum = 5;
    if (MpaaRating.super.equals(MpaaRating.G)) secondEnum = 1;
    if (MpaaRating.super.equals(MpaaRating.PG)) secondEnum = 2;
    if (MpaaRating.super.equals(MpaaRating.PG_13)) secondEnum = 3;
    if (MpaaRating.super.equals(MpaaRating.R)) secondEnum = 4;
    if (MpaaRating.super.equals(MpaaRating.NC_17)) secondEnum = 5;
    return Integer.compare(firstEnum, secondEnum);
    }
    public static String ratingList() {
        String nameList = "";
        for (MpaaRating rating : values()) {
            nameList += rating.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}


