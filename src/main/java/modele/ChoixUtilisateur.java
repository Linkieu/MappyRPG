package modele;

/**
 * Cette classe créer des objets choixUtilisateur pour permettre de customiser la recherche
 */
public class ChoixUtilisateur {
    private boolean queteLaPlusProche;
    private int objectifDuree;
    private int objectifQuete;
    private int objectifDeplacement;
    private int minNbrQueteSouhaitees;
    private int maxNbrQueteSouhaitees;
    private int minTempsAccorde;
    private int maxTempsAccorde;
    private int minDeplacementAccorde;
    private int maxDeplacementAccorde;
    private int nbrSolutionsMax;

    private int typeDeSolution; // 1 = solutions efficaces, 2 = solutions exhaustives, 0 = peu importe

    private int minXP;
    private int maxXP;



    public ChoixUtilisateur(boolean parLaPlusProche,  int parTypeSoluce) {
        //le reste sur : False / 0 (on n'en prend pas en compte)
        queteLaPlusProche = parLaPlusProche;

        objectifDuree = 0;

        objectifQuete = 0;
        objectifDeplacement = 0;

        // -1 signifie qu'on n'en prend pas compte

        minNbrQueteSouhaitees = -1;
        maxNbrQueteSouhaitees = -1;
        minDeplacementAccorde = -1;
        maxDeplacementAccorde = -1;
        minTempsAccorde = -1;
        maxTempsAccorde = -1;
        minXP = -1;
        maxXP = -1;

        typeDeSolution = parTypeSoluce;

        // on veut les pires résultats

        // meilleurPireChemin = false;

        nbrSolutionsMax = 1;
    }

    public ChoixUtilisateur (int parObjectifDuree){
        // parObjectifDuree doit être un int

        queteLaPlusProche = false;

        objectifDuree = parObjectifDuree;

        objectifQuete = 0;
        objectifDeplacement = 0;

        // -1 signifie qu'on n'en prend pas compte

        minNbrQueteSouhaitees = -1;
        maxNbrQueteSouhaitees = -1;
        minDeplacementAccorde = -1;
        maxDeplacementAccorde = -1;
        minTempsAccorde = -1;
        maxTempsAccorde = -1;
        minXP = -1;
        maxXP = -1;

        typeDeSolution = 0;

        // on veut les pires résultats

        // meilleurPireChemin = false;

        nbrSolutionsMax = 10;
    }

    public ChoixUtilisateur (int parObjectifDuree, int parObjectifQuete){
        // les 2 paramètres doivent être des int
        queteLaPlusProche = false;

        objectifDuree = parObjectifDuree;
        objectifQuete = parObjectifQuete;

        objectifDeplacement = 0;

        // -1 signifie qu'on n'en prend pas compte

        minNbrQueteSouhaitees = -1;
        maxNbrQueteSouhaitees = -1;
        minDeplacementAccorde = -1;
        maxDeplacementAccorde = -1;
        minTempsAccorde = -1;
        maxTempsAccorde = -1;
        minXP = -1;
        maxXP = -1;

        typeDeSolution = 0;

        // on veut les pires résultats

        // meilleurPireChemin = false;

        nbrSolutionsMax = 10;
    }

    public ChoixUtilisateur (int parObjectifDuree, int parObjectifQuete, int parObjectifDeplacement){
        // les 3 paramètres doivent être des int
        queteLaPlusProche = false;

        objectifDuree = parObjectifDuree;
        objectifQuete = parObjectifQuete;
        objectifDeplacement = parObjectifDeplacement;

        // -1 signifie qu'on n'en prend pas compte

        minNbrQueteSouhaitees = -1;
        maxNbrQueteSouhaitees = -1;
        minDeplacementAccorde = -1;
        maxDeplacementAccorde = -1;
        minTempsAccorde = -1;
        maxTempsAccorde = -1;
        minXP = -1;
        maxXP = -1;

        typeDeSolution = 0;

        // on veut les pires résultats

        // meilleurPireChemin = false;

        nbrSolutionsMax = 10;
    }


    public ChoixUtilisateur(int parObjectifDuree, int parObjectifQuete, int parObjectifDeplacement, int parMinTempsAccorde, int parMaxTempsAccorde, int parMinDeplacementAccorde, int parMaxDeplacementAccorde, int parMinNbrQueteSouhaitees, int parMaxNbrQueteSouhaitees){
        // les 3 premiers paramètres doivent être des int
        // parOrdreImportanceCritere doit être un int (de 0 à 7 compris (0 = aucun, 7 = tout))
        // parMeilleurPireChemin doit être un booléen

        queteLaPlusProche = false;

        objectifDuree = parObjectifDuree;
        objectifQuete = parObjectifQuete;
        objectifDeplacement = parObjectifDeplacement;



        minNbrQueteSouhaitees = parMinNbrQueteSouhaitees;
        maxNbrQueteSouhaitees = parMaxNbrQueteSouhaitees;
        minDeplacementAccorde = parMinDeplacementAccorde;
        maxDeplacementAccorde = parMaxDeplacementAccorde;
        minTempsAccorde = parMinTempsAccorde;
        maxTempsAccorde = parMaxTempsAccorde;
        minXP = -1;
        maxXP = -1;

        typeDeSolution = 0;

        // on veut les pires résultats

        // meilleurPireChemin = parMeilleurPireChemin;

        nbrSolutionsMax = 10;
    }

    public ChoixUtilisateur(int parObjectifDuree, int parObjectifQuete, int parObjectifDeplacement, int parMinTempsAccorde, int parMaxTempsAccorde, int parMinDeplacementAccorde, int parMaxDeplacementAccorde, int parMinNbrQueteSouhaitees, int parMaxNbrQueteSouhaitees, int parMinXP, int parMaxXP, int parTypeSoluce, int parNbrSolutionsMax){
        // les 3 premiers paramètres doivent être des int
        // parOrdreImportanceCritere doit être un int (de 0 à 7 compris (0 = aucun, 7 = tout))
        // parMeilleurPireChemin doit être un booléen

        queteLaPlusProche = false;

        objectifDuree = parObjectifDuree;
        objectifQuete = parObjectifQuete;
        objectifDeplacement = parObjectifDeplacement;



        minNbrQueteSouhaitees = parMinNbrQueteSouhaitees;
        maxNbrQueteSouhaitees = parMaxNbrQueteSouhaitees;
        minDeplacementAccorde = parMinDeplacementAccorde;
        maxDeplacementAccorde = parMaxDeplacementAccorde;
        minTempsAccorde = parMinTempsAccorde;
        maxTempsAccorde = parMaxTempsAccorde;
        minXP = parMinXP;
        maxXP = parMaxXP;

        typeDeSolution = parTypeSoluce;

        // on veut les meilleurs résultats

        // meilleurPireChemin = parMeilleurPireChemin;
        nbrSolutionsMax = parNbrSolutionsMax;
    }


    public boolean getQueteLaPlusProche(){ return queteLaPlusProche; }
    public int getObjectifDuree(){ return objectifDuree; }
    public int getObjectifQuete(){ return objectifQuete; }
    public int getObjectifDeplacement(){ return objectifDeplacement; }

    public int getMinNbrQueteSouhaitees() { return minNbrQueteSouhaitees; }
    public int getMaxNbrQueteSouhaitees() { return maxNbrQueteSouhaitees; }
    public int getMinDeplacementAccorde(){ return minDeplacementAccorde; }
    public int getMaxDeplacementAccorde(){ return maxDeplacementAccorde; }
    public int getMinTempsAccorde(){ return minTempsAccorde; }
    public int getMaxTempsAccorde(){ return maxTempsAccorde; }
    public int getNbrSolutionsMax() { return nbrSolutionsMax;}

    public int getTypeDeSolution() { return typeDeSolution;}

    public int getMinXP(){ return minXP;}
    public int getMaxXP(){ return maxXP;}


}
