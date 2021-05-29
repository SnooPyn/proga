package com.company;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Main character. Is stored in the collection.
 */
public class Movie implements Comparable<Movie> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private static Long idIndicator = 1L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long oscarsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private long totalBoxOffice; //Значение поля должно быть больше 0
    private MovieGenre genre; //Поле может быть null
    private MpaaRating mpaaRating; //Поле может быть null
    private Person screenwriter;

    public Movie(String name, Coordinates coordinates, LocalDateTime creationDate, long oscarsCount,
                 long totalBoxOffice, MovieGenre genre, MpaaRating mpaaRating, Person screenwriter) {
        this.id = GetIdIndicator();
        SetIdIndicator();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.totalBoxOffice = totalBoxOffice;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.screenwriter = screenwriter;
    }

    /**
     * @return ID of the movie.
     */
    public Long GetId() {
        return id;
    }

    public long GetIdIndicator(){
        return idIndicator;
    }
    public void SetIdIndicator(){
        idIndicator++;
    }
    public void DecreaseId(){
        idIndicator--;
    }
    /**
     * @return Name of the movie.
     */
    public String GetName() {
        return name;
    }

    /**
     * @return Coordinates of the movie.
     */
    public Coordinates GetCoordinates() {
        return coordinates;
    }

    /**
     * @return Creation date of the movie.
     */
    public LocalDateTime GetCreationDate() {
        return creationDate;
    }

    /**
     * @return Oscars count of the movie.
     */
    public long GetOscarsCount() {
        return oscarsCount;
    }

    /**
     * @return Total box office of the movie.
     */
    public long GetTotalBoxOffice() {
        return totalBoxOffice;
    }

    /**
     * @return Genre of the movie.
     */
    public MovieGenre GetGenre() {
        return genre;
    }

    /**
     * @return Mpaa rating of the movie.
     */
    public MpaaRating GetMpaaRating() {
        return mpaaRating;
    }

    public Person GetScreenwriter() {
        return screenwriter;
    }

    @Override
    public int compareTo(Movie movieObj) {
//        int result = Long.compare(oscarsCount, movieObj.oscarsCount);
        int result = 0;
        if (result == 0) {
            result = mpaaRating.CompareTo(movieObj.mpaaRating);
        }
        if (result == 0) result = Long.compare(totalBoxOffice, movieObj.totalBoxOffice);
        return result;
    }

    @Override
    public String toString() {
        String info = "";
        info += "Film №" + id;
        info += " (added " + creationDate.toLocalDate() + " " + creationDate.toLocalTime() + ")";
        info += "\n Name: " + name;
        info += "\n Coordinates: " + coordinates;
        info += "\n Oscars count: " + oscarsCount;
        info += "\n Total box office: " + totalBoxOffice;
        info += "\n Genre: " + genre;
        info += "\n Mpaa rating: " + mpaaRating;
        info += "\n Screenwriter: " + screenwriter;
        return info;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, oscarsCount, totalBoxOffice, genre,
                mpaaRating, screenwriter);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Movie) {
            Movie movieObj = (Movie) obj;
            return name.equals(movieObj.GetName()) && coordinates.equals(movieObj.GetCoordinates()) &&
                    (oscarsCount == movieObj.GetOscarsCount()) && (totalBoxOffice == movieObj.GetTotalBoxOffice()) &&
                    (genre == movieObj.GetGenre()) && (mpaaRating == movieObj.GetMpaaRating()) &&
                    screenwriter.equals(movieObj.GetScreenwriter());
        }
        return false;
    }


}


