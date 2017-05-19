/**
 * Created by sdebici on 18/05/2017.
 */
class Population {

    /**
     * Tableau représentant nos individu
     * Choix d'un array pour simplicité d'utilisation au niveau des insert pour le croisement de nos individu
     */
    Individu[] individus;

    /**
     * Constructeur d'une population avec deux paramètres
     * @param populationSize : taille de la population à créer
     * @param firstPop : Lors de la première création de population, on l'initialise avec un booléen à true
     */
    public Population(int populationSize, boolean firstPop) {
        individus = new Individu[populationSize];

        if (firstPop) {
            for (int i = 0; i < populationSize(); i++) {
                Individu newIndividu = new Individu();
                newIndividu.generateRandomIndividu();
                addIndividu(i, newIndividu);
            }
        }
    }

    /**
     * Methode qui renvoit l'individu de la population avec la plus petite distance
     * @return Individu
     */
    public Individu getBestIndividu() {
        Individu bestIndividu = individus[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (getTour(i).getDistance() < bestIndividu.getDistance()) {
                bestIndividu = getTour(i);
            }
        }
        return bestIndividu;
    }

    public void addIndividu(int index, Individu individu) {
        individus[index] = individu;
    }

    public Individu getTour(int index) {
        return individus[index];
    }

    public int populationSize() {
        return individus.length;
    }
}
