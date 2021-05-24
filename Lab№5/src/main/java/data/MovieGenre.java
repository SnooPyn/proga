package data;

public enum MovieGenre {
    ACTION,
    COMEDY,
    ADVENTURE,
    HORROR,
    SCIENCE_FICTION;

    public static String genreList() {
        String nameList = "";
        for (MovieGenre genre : values()) {
            nameList += genre.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}


