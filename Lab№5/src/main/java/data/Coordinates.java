package data;

/**
 * X-Y coordinates.
 */

public class Coordinates {
    private Float x; //Поле не может быть null
    private double y; //Максимальное значение поля: 623

    public Coordinates(float x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * @return X-coordinate.
     */

    public Float getX(){
        return x;
    }
    /**
     * @return Y-coordinate.
     */
    public double getY(){
        return y;
    }
    public String toString(){
        return "X:" + x + "Y:" + y;
    }
    public int hashCode(){
        return  x.hashCode() + (int) y;
    }
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinates) {
            Coordinates coordinatesObj = (Coordinates) obj;
            return (y == coordinatesObj.getX()) && x.equals(coordinatesObj.getY());
        }
        return false;
    }
}

