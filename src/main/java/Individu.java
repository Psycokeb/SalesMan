import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by sdebici on 18/05/2017.
 * Classe représentant un individu / chromosome
 */
public class Individu {

    /**
     * Liste de ville de l'individu
     */
    private ArrayList individu = new ArrayList<City>();
    private int distance = 0;

    /**
     * Constructeur pour un individu vide (pour préparer la mutation)
     */
    public Individu(){
        for (int i = 0; i < IndividuManager.numberOfCities(); i++) {
            individu.add(null);
        }
    }

    /**
     * Méthode de génération d'individu aléatoires
     * On injecte les villes de départs dans notre individu
     * Puis on mélange la liste pour une nouvel individu
     */
    public void generateRandomIndividu() {

        for (int i = 0; i < IndividuManager.numberOfCities(); i++) {
            setCity(i, IndividuManager.getCity(i));
        }
        Collections.shuffle(individu);
    }

    /**
     * Calcul de la distance total du parcour d'un individu
     * @return la distance totale d'un parcour
     */
    public int getDistance(){
        // Pour économiser du temps de traitement, on calcul la distance que si elle n'a pas été déjà calculée
        if (distance == 0) {
            for (int i=0; i < individuSize(); i++) {

                City city1 = getCity(i);
                City city2;
                if(i+1 < individuSize()){
                    city2 = getCity(i+1);
                }
                else{
                    city2 = getCity(0);
                }
                distance += city1.getDistance(city2);
            }
        }
        return distance;
    }

    /**
     * Méthode utile pour le croisement. Check si la ville existe dans un individu
     * @param city
     * @return boolean si la ville existe dans un individu
     */
    public boolean containsCity(City city){
        return individu.contains(city);
    }

    /**
     * Ovverride de toString pour un question de lisibilité lors de l'affichage de notre solution
     * @return
     */
    @Override
    public String toString() {
        String output = "| ";
        for (int i = 0; i < individuSize(); i++) {
            if (i == individuSize() - 1)
                output += getCity(i).getName()+" | ";
            else
                output += getCity(i).getName()+" --> ";

        }
        return output;
    }

    public City getCity(int individuPosition) {
        return (City) individu.get(individuPosition);
    }


    public void setCity(int individuPosition, City city) {
        individu.set(individuPosition, city);
    }

    public int individuSize() {
        return individu.size();
    }
}
