package data;

import java.time.LocalDateTime;

/**
 * Main character. Is stored in the collection.
 */
public class Movie implements Comparable<Movie>{
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long oscarsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private long totalBoxOffice; //Значение поля должно быть больше 0
    private MovieGenre genre; //Поле может быть null
    private MpaaRating mpaaRating; //Поле может быть null
    private Person screenwriter;

    public Movie(Long id, String name, Coordinates coordinates, LocalDateTime creationDate, long oscarsCount,
                 long totalBoxOffice, MovieGenre genre, MpaaRating mpaaRating, Person screenwriter) {
        this.id = id;
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
    public long GetTotalBoxOffice(){
        return totalBoxOffice;
    }

    /**
     * @return Genre of the movie.
     */
    public MovieGenre GetGenre(){
        return genre;
    }
    /**
     * @return Mpaa rating of the movie.
     */
    public MpaaRating GetMpaaRating(){
        return mpaaRating;
    }

    public Person GetScreenwriter(){
        return screenwriter;
    }
    @Override
    public int compareTo(Movie movieObj) {
        return id.compareTo(movieObj.GetId());
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
        return name.hashCode() + coordinates.hashCode() + (int) oscarsCount + (int) totalBoxOffice + genre.hashCode() +
                mpaaRating.hashCode() + screenwriter.hashCode();

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



