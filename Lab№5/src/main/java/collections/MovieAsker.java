package collections;

import java.util.NoSuchElementException;
import java.util.Scanner;

import data.*;

import exceptions.*;
import run.Application;


/**
 * Asks a user a movie's value.
 */
public class MovieAsker {
    private final double MAX_Y = 623;
    private final long MIN_TOTALBOXOFFICE = 0;
    private final long MIN_OSCARSCOUNT = 0;
    private final double MIN_WEIGHT = 0;
    private final int MIN_HEIGHT = 0;

    private Scanner userScanner;
    private boolean fileMode;

    public MovieAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    /**
     * Sets a scanner to scan user input.
     *
     * @param userScanner Scanner to set.
     */
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * @return Scanner, which uses for user input.
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Sets movie asker mode to 'File Mode'.
     */
    public void setFileMode() {
        fileMode = true;
    }

    /**
     * Sets movie asker mode to 'User Mode'.
     */
    public void setUserMode() {
        fileMode = false;
    }

    /**
     * Asks a user the movie's name.
     *
     * @return Movie's name.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.println("Enter name:");
                Console.print(Application.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Name not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("The name cannot be empty!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Asks a user the movie's X coordinate.
     *
     * @return Movie's X coordinate.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Float askX() throws IncorrectInputInScriptException {
        String strX;
        Float x;
        while (true) {
            try {
                Console.println("Enter X coordinate:");
                Console.print(Application.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Float.parseFloat(strX);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("X coordinate not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("X coordinate must be represented by a number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return x;
    }

    /**
     * Asks a user the movie's Y coordinate.
     *
     * @return Movie's Y coordinate.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public double askY() throws IncorrectInputInScriptException {
        String strY;
        double y;
        while (true) {
            try {
                Console.println("Enter Y coordinate  < " + (MAX_Y + 1) + ":");
                Console.print(Application.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Double.parseDouble(strY);
                if (y > MAX_Y) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Y coordinate is not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Y coordinate cannot exceed " + MAX_Y + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Y coordinate must be represented by a number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return y;
    }

    /**
     * Asks a user the movie's coordinates.
     *
     * @return Movie's coordinates.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        Float x;
        double y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    /**
     * Asks a user the movie's totalBoxOffice.
     * @return Movie's totalBoxOffice.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public long askBoxOffice() throws IncorrectInputInScriptException {
        String strBoxOffice;
        long totalBoxOffice;
        while (true) {
            try {
                Console.println("Enter box office:");
                Console.print(Application.PS2);
                strBoxOffice = userScanner.nextLine().trim();
                if (fileMode) Console.println(strBoxOffice);
                totalBoxOffice = Long.parseLong(strBoxOffice);
                if (totalBoxOffice < MIN_TOTALBOXOFFICE) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Box office not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Box office must be greater than zero!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Box office should be represented by a number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return totalBoxOffice;
    }
    /**
     * Asks a user the movie's genre.
     * @return Movie's genre.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public MovieGenre askGenre() throws IncorrectInputInScriptException {
        String strGenre;
        MovieGenre genre;
        while (true) {
            try {
                Console.println("List of genres - " + MovieGenre.genreList());
                Console.println("Enter genre:");
                Console.print(Application.PS2);
                strGenre = userScanner.nextLine().trim();
                if (fileMode) Console.println(strGenre);
                genre = MovieGenre.valueOf(strGenre.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Genre not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("The genre is not in the list!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return genre;
    }
    /**
     * Asks a user the movie's mpaa rating.
     * @return Movie's mpaa rating.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public MpaaRating askMpaaRating() throws IncorrectInputInScriptException {
        String strMpaaRating;
        MpaaRating mpaaRating;
        while (true) {
            try {
                Console.println("List of Mpaa ratings - " + MpaaRating.ratingList());
                Console.println("Enter rating:");
                Console.print(Application.PS2);
                strMpaaRating = userScanner.nextLine().trim();
                if (fileMode) Console.println(strMpaaRating);
                mpaaRating = MpaaRating.valueOf(strMpaaRating.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Rating not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("The rating is not in the list!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return mpaaRating;
    }
    /**
     * Asks a user the movie's oscars count.
     * @return Movie's oscars count.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public long askOscarsCount() throws IncorrectInputInScriptException {
        String strOscarsCount;
        long oscarsCount;
        while (true) {
            try {
                Console.println("Enter oscars count:");
                Console.print(Application.PS2);
                strOscarsCount = userScanner.nextLine().trim();
                if (fileMode) Console.println(strOscarsCount);
                oscarsCount = Long.parseLong(strOscarsCount);
                if (oscarsCount < MIN_OSCARSCOUNT) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Oscars count not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Oscars count must be greater than zero!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Oscars count should be represented by a number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return oscarsCount;
    }
    /**
     * Asks a user the movie person's name.
     * @return Person's name.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public String askPersonName() throws IncorrectInputInScriptException {
        String personName;
        while (true) {
            try {
                Console.println("Enter the name of the screenwriter:");
                Console.print(Application.PS2);
                personName = userScanner.nextLine().trim();
                if (fileMode) Console.println(personName);
                if (personName.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Name of the screenwriter not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Name of the screenwriter cannot be empty!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return personName;
    }
    /**
     * Asks a user the movie person's height.
     * @return height.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public int askPersonHeight() throws IncorrectInputInScriptException {
        String strPersonHeight;
        int height;
        while (true) {
            try {
                Console.println("Enter height:");
                Console.print(Application.PS2);
                strPersonHeight = userScanner.nextLine().trim();
                if (fileMode) Console.println(strPersonHeight);
                height = (int) Double.parseDouble(strPersonHeight);
                if (height < MIN_HEIGHT) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Height not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Height must be greater than zero!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Height cannot be empty!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return height;
    }
    /**
     * Asks a user the movie person's weight.
     * @return weight.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public double askPersonWeight() throws IncorrectInputInScriptException {
        String strPersonWeight;
        double weight;
        while (true) {
            try {
                Console.println("Enter weight:");
                Console.print(Application.PS2);
                strPersonWeight = userScanner.nextLine().trim();
                if (fileMode) Console.println(strPersonWeight);
                weight = Double.parseDouble(strPersonWeight);
                if (weight < MIN_WEIGHT) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Weight not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Weight must be greater than zero!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Weight cannot be empty!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return weight;
    }
    /**
     * Asks a user the movie's person.
     * @return Movie's person.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Person askPerson() throws IncorrectInputInScriptException {
        String name;
        int height;
        double weight;
        name = askPersonName();
        height = askPersonHeight();
        weight = askPersonWeight();
        return new Person(name, height, weight);
    }
    /**
     * Asks a user a question.
     * @return Answer (true/false).
     * @param question A question.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(Application.PS2);
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Response not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("The answer must be represented by signs '+' or '-'!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return (answer.equals("+")) ? true : false;
    }

    @Override
    public String toString() {
        return "MovieAsker (auxiliary class for user requests)";
    }
}


