/**
 * Created by sdebici on 18/05/2017.
 * Classe représentant l'algo génétique
 * Singleton car besoin d'une seule instance de cette classe
 *
 */
public class GeneticAlgo {

    private static final double RATEMUTATION = 0.3;
    private static final int SELECTION = 7;
    private static int keeXIndividus;

    private static GeneticAlgo INSTANCE = null;

    private GeneticAlgo(){
    }

    private static synchronized GeneticAlgo getINSTANCE(){
        if (INSTANCE == null){
            INSTANCE = new GeneticAlgo();
        }
        return INSTANCE;
    }

    /**
     * Création d'une nouvelle population avec croisement aléatoire et mutation aléatoire
     * --> Choix de deux parents choisis de manière "aléatoire"
     * --> Croisement de ces deux parents
     * --> Ajout de l'individu dans la nouvelle population
     * --> Tentative de mutation sur chaque individu de la nouvelle génération
     * @param population : population n-1 qui sert pour la génération de la prochaine population
     * @return nouvelle population avec croisements et mutations
     */
    public static Population evolvePopulation(Population population) {

        // booléen à false pour pas initialiser la nouvelle population, nous allons la remplir au fir et à mesure des croisements
        Population newPopulation = new Population(population.populationSize(), false);

        //On garde un dixième des meilleurs chromosomes sans les croiser, ni les muter
        keeXIndividus = population.populationSize() / 10;

        for (int i = 0 ; i < keeXIndividus ; i++){

            Individu oneGoodIndividu = population.getBestIndividu();
            newPopulation.addIndividu(i, oneGoodIndividu);


        }

        for (int i = keeXIndividus; i < newPopulation.populationSize(); i++) {
            Individu goodParent1 = randomSelectIndividu(population);
            Individu goodParent2 = randomSelectIndividu(population);

            Individu child = crossover(goodParent1, goodParent2);

            newPopulation.addIndividu(i, child);
        }



        for (int i = keeXIndividus; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTour(i));
        }
        return newPopulation;
    }

    /**
     * Méthode qui permet de sélectionner un certain nombre d'individu dans notre population initiale pour sélectionner le meilleur d'eutre eux
     * --> Selection aléatoire de x (SELECTION) individus
     * --> Renvoi le meilleur de ces x individus
     * @param population : Population mère
     * @return
     */
    private static Individu randomSelectIndividu(Population population) {

        Population tournament = new Population(SELECTION, false);

        for (int i = 0; i < SELECTION; i++) {
            int random = (int) (Math.random() * population.populationSize());
            tournament.addIndividu(i, population.getTour(random));
        }
        Individu bestIndividu = tournament.getBestIndividu();
        return bestIndividu;
    }

    /**
     * Méthode de croisement d'individus :
     * --> Garder les 5 meilleurs individus et les injecter sans les croiser
     * On prend deux nombres au hasard compris dans la taille de l'individu
     * --> Ces deux nombres représentes la fourchette de ville que nous allons garder dans notre nouvel individu
     * @param goodParent1 : Premier individu parent
     * @param goodParent2 : Second individu parent
     * @return
     */
    public static Individu crossover(Individu goodParent1, Individu goodParent2) {

        Individu child = new Individu();
        int end = -1;

        int start = (int) (Math.random() * goodParent1.individuSize());
        do {
            end = (int) (Math.random() * goodParent1.individuSize());
        }while(end < start);

        // Récupération des gènes sélectionnés avec le random
        for (int i = 0; i < child.individuSize(); i++) {
            if (i > start && i < end) {
                child.setCity(i, goodParent1.getCity(i));
            }
        }

        // Remplissage des autres gène avec le parents 2
        for (int i = 0; i < goodParent2.individuSize(); i++) {
            if (!child.containsCity(goodParent2.getCity(i))) {
                for (int x = 0; x < child.individuSize(); x++) {
                    if (child.getCity(x) == null) {
                        child.setCity(x, goodParent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    /**
     * Méthode de mutation d'un individu
     * --> 0 <= Math.random() <= 1
     * --> Ce nombre nous dis si on fait une mutation sur l'indidu par rapport à un seuil
     * On applique cette méthode à chaque individu de la population
     * @param individu
     */
    private static void mutate(Individu individu) {
        for (int i = 0; i < individu.individuSize(); i++) {
            if (Math.random() < RATEMUTATION) {

                //choix de l'autre gène à muter
                int random = (int) (individu.individuSize() * Math.random());

                City city1 = individu.getCity(i);
                City city2 = individu.getCity(random);

                individu.setCity(random, city1);
                individu.setCity(i, city2);
                break;
            }
        }
    }


}
