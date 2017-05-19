/**
 * Created by sdebici on 18/05/2017.
 */
public class SalesMan {

    public static void main(String[] args) {

        final int POPULATION = 50;
        final int NOMBRE_GENERATION = 100;

        // Création de nos villes avec des noms bidons
        City city = new City(1000, 800, "Bayonne");
        IndividuManager.addCity(city);
        City city2 = new City(2100 , 1000, "Rodez");
        IndividuManager.addCity(city2);
        City city3 = new City(1600, 2300, "Tours");
        IndividuManager.addCity(city3);
        City city4 = new City(2100, 2900, "Paris");
        IndividuManager.addCity(city4);
        City city5 = new City(1900, 3700, "Boulogne");
        IndividuManager.addCity(city5);
        City city6 = new City(3500, 2600, "Colmar");
        IndividuManager.addCity(city6);
        City city7 = new City(2100, 1700, "Clermont Ferrand");
        IndividuManager.addCity(city7);
        City city8 = new City(3500, 700, "Marseille");
        IndividuManager.addCity(city8);
        City city9 = new City(1000, 2300, "Nantes");
        IndividuManager.addCity(city9);
        City city10 = new City(1200, 1200, "Bordeaux");
        IndividuManager.addCity(city10);

        // Création de la première population et affichage de la meilleure distance sans évolution
        Population pop = new Population(POPULATION, true);
        System.out.println("Meilleure distance de la première génération " + pop.getBestIndividu().getDistance());

        //Evolution des populations
        for (int i = 0; i < NOMBRE_GENERATION; i++) {
            pop = GeneticAlgo.evolvePopulation(pop);
            System.out.print(" ");
            System.out.println("Meilleure distande de la génération " + (i + 1) + " : " + pop.getBestIndividu().getDistance());
            System.out.println(pop.getBestIndividu());
        }

        // Print final results
        System.out.println("Distance finale : " + pop.getBestIndividu().getDistance());
        System.out.println("Meilleur trajet : ");
        System.out.println(pop.getBestIndividu());
    }
}
