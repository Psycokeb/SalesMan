import java.util.ArrayList;

/**
 * Created by sdebici on 18/05/2017.
 * Classe utilisé pour la création du premier individu et des méthodes utiles pour les mutations
 */
public class IndividuManager {

    // Liste de nos villes
    private static ArrayList destinationCities = new ArrayList<City>();

    // Ajout d'une ville dans notre individu
    public static void addCity(City city) {
        destinationCities.add(city);
    }

    // Get la ville à un index de notre liste de ville
    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }

    // Nombre de villes que contient notre individu
    public static int numberOfCities(){
        return destinationCities.size();
    }
}
