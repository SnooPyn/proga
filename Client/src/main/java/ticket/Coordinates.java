package ticket;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Long x; //Максимальное значение поля: 687, Поле не может быть null
    private Double y; //Максимальное значение поля: 153, Поле не может быть null

    public Coordinates(Long x, Double y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates(){

    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}

