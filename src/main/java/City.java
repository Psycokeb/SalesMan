/**
 * Created by sdebici on 18/05/2017.
 * Classe représentant une ville avec des coordonnées et un nom
 */
public class City {

    private int x;
    private int y;
    private String name;

    public City(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public City(City city){
        this.x = city.getX();
        this.y = city.getY();
        this.name = city.name;

    }

    public String getName() {
        return name;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    /**
     * Calcul de la distance avec une autre ville.
     */
    public double getDistance(City city){
        int x = Math.abs(getX() - city.getX());
        int y = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( Math.pow(x, 2) + Math.pow(y, 2));

        return distance;
    }

    @Override
    public String toString(){
        return getX()+ ", " +getY();
    }
}
