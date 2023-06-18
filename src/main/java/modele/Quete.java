package modele;

import java.util.*;
import java.util.TreeMap;

import static java.lang.Math.abs;

/**
 * Cette classe crée un objet de Type Quete en récupérant les lignes des fichiers scenarios
 */
public class Quete {
    private int numero;
    private int [] position;
    private int [] preconditions;
    private int duree;
    private int experience;
    private String intitule;

    private TreeMap<Float, ArrayList<Quete>> voisinage; // Des quêtes qui peuvent succéder à celle-ci.


    public Quete(String ligne) {
        // On découpe la ligne pour la traiter
        Scanner scanner = new Scanner(ligne).useDelimiter("\\|");






        while (scanner.hasNext()){
            this.numero = scanner.nextInt();


            /* ----- POSITION ----- */
            String pos = scanner.next(); // Création d'un scanner
            pos = pos.replace("(", ""); // On remplace les caractères non voulus
            pos = pos.replace(")", "");
            pos = pos.replace(" ", "");
            Scanner scanPos = new Scanner(pos).useDelimiter(","); // On ajoute un délimiteur


            position = new int [2];
            position[0] = Integer.parseInt(scanPos.next()); // On insère nos valeurs dans la liste
            position[1] = Integer.parseInt(scanPos.next());




            /* ----- PRECONDITIONNEMENT ----- */
            // On récupère le tuple au format str qui nous intéresse
            String precond = scanner.next();

            // On retire les caractères qui vont déranger
            precond = precond.replace("(", "");
            precond = precond.replace(")", "");
            precond = precond.replace(" ", "");

            // On délimite ce morceau de texte
            Scanner scanPrecondition = new Scanner(precond).useDelimiter(",");
            preconditions = new int [4];
            int i = 0;

            // On parcourt scanPrecondition pour récupérer ses valeurs et ainsi les mettre au propre dans le tableau.
            while (scanPrecondition.hasNext()){
                String extrait = scanPrecondition.next();
                if (! extrait.equals("")){
                    preconditions[i] = Integer.parseInt(extrait);
                }
                i++;
            }

            this.duree = scanner.nextInt();
            this.experience = scanner.nextInt();
            this.intitule = scanner.next();
        }

        voisinage = new TreeMap<>(); // Sans valeur sauf si on les insères à l'aide de la méthode voisins.
    }

    public String toString(){

        String strPosition = "["+ this.position[0] + ", " + this.position[1] + "]";

        // On convertit la liste des préconditions en String pour l'afficher
        String strPreconditions = "[";
        for(int i = 0; i < preconditions.length && preconditions[i]!=0; i++)
        {
            if (i!=0){
                strPreconditions = strPreconditions + ", ";
            }
            strPreconditions = strPreconditions + preconditions[i];
        }
        strPreconditions = strPreconditions + "]";
        return "[ NO: " + this.numero + ", POS: " + strPosition + ", PRECOND: " + strPreconditions + ", TPS: " + duree + ", XP: " + experience + ", NOM: " + intitule + "]";
    }

    /**
     * Principe :
     * Dictionnaire des voisins de this en fonction de leur coût (coût en clés)
     * Ce coût correspond au ratio entre le nombre d'expérience sur le critère demandé par l'utilisateur.
     * <p>
     * Plus celui est grand, plus il est optimal d'y aller sur le rapport expérience rapporté sur le critère demandé (parcours par ex).
     * Entrée :
     *
     * @param parScenar    [SCENARIO] : Le scénario étudié
     * @param parChoixAlgo [CHOIXUTILISATEUR] : le choix de l'utilisateur, afin de savoir avec quel critère on doit calculer
     *                     Sortie :
     * @return dicoVoisins     [TREEMAP : FLOAT > QUETE] : Dictionnaire des coûts avec les quêtes associés.
     */
    public TreeMap<Float, ArrayList<Quete>> voisins(Scenario parScenar, ChoixUtilisateur parChoixAlgo){
        TreeMap<Float, ArrayList<Quete>> dicoVoisins = new TreeMap<>(Collections.reverseOrder());

        for (Quete unVoisin : parScenar.getProvQuetes()){
            if (unVoisin != this){
                float ratio = this.ratioXPCritere(unVoisin,parChoixAlgo);
                if (dicoVoisins.get(ratio) == null){
                    ArrayList<Quete> voisins = new ArrayList<>();
                    voisins.add(unVoisin);
                    dicoVoisins.put(ratio, voisins);
                }
                else {
                    dicoVoisins.get(ratio).add(unVoisin);
                }

            }
        }

        voisinage = dicoVoisins;
        return dicoVoisins;
    }

    /**
     * Principe :
     *      Calcule la distance (en terme de déplacement) entre cette quête (this) et la quête en paramètre.
     * Entrée :
     *      @param parQuete [QUETE] : La quête à comparer
     * Sortie :
     *      @return distance [INT] : Distance totale entre les deux quêtes
     */
    public int distanceDeplacement(Quete parQuete){
        int diffX = abs(this.position[0] - parQuete.position[0]); // Calcul la distance en x (nombre de déplacement)
        int diffY = abs(this.position[1] - parQuete.position[1]); // Calcul la distance en y (nb déplacement)

        return diffX + diffY;
    }

    /**
     * Principe :
     *      Calcule le ratio entre l'expérience de parQuete et un critère demandé par l'utilisateur
     *      ATTENTION :
     *          choixAlgo correspond à un objet ChoixUtilisateur créer un algorithme à partir du choix de l'utilisateur.
     *          Cette méthode prend qu'un seul critère à la fois.
     *          Si l'utilisateur en a pris plusieurs, il faut que cet algo réadapte la recherche pour cette méthode
     * Entrée :
     *      parQuete               [QUETE] : La quête à comparer
     *      choixAlgo   [CHOIXUTILISATEUR] : le choix de l'utilisateur adapté pour la méthode.
     * Sortie :
     *      distance               [FLOAT] : le ratio de parQuete
     */
    public float ratioXPCritere(Quete parQuete, ChoixUtilisateur choixAlgo){
        try{

            if (this == parQuete) {
                throw new Exception();
            }
        }
        catch (Exception e){
            System.out.println("ERREUR >> la quête en paramètre est invalide [ratioXPCritere]");
            System.out.println("          La quête en paramètre est la même quête que l'objet.");
            System.exit(-1);
        }

        float critere = 1;



        if (choixAlgo.getObjectifDuree() != 0){
            // critère de durée
            critere = parQuete.duree;
        }
        if (choixAlgo.getObjectifDeplacement() != 0){
            // Critère de déplacement
            int diffX = abs(this.position[0] - parQuete.position[0]); // Calcul la distance en x (nombre de déplacement)
            int diffY = abs(this.position[1] - parQuete.position[1]); // Calcul la distance en y (nb déplacement)

            critere = diffX + diffY;
        }

        float ratio = parQuete.experience / critere;

        return ratio;
    }

    /**
     * Principe :
     *      Calcule le temps entre cette quête (this) et la quête en paramètre.
     *      On comprend le nombre d'unité de temps concernant le trajet en plus de ceux des quêtes.
     * Entrée :
     *      parQuete    [QUETE] : La quête à comparer
     * Sortie :
     *      temps         [INT] : Temps calculé entre les deux quêtes
     */
    public int tempsDeplacemenEtFaireQuetes(Quete parQuete){

        // Temps de déplacement + temps de faire les quêtes
        int temps = this.distanceDeplacement(parQuete) + (this.duree + parQuete.getDuree());

        return temps;
    }


    /**
     * Principe :
     *      Dit si c'est possible ou non de faire la quête en fonction de l'avancé du joueur.
     * Entrée :
     *      parAvancerJoueur [AvancerJoueur] : Objet contenant là où en est le joueur
     * Sortie :
     *      true → c'est possible de faire la quête
     *      false → ce n'est pas possible
     */
    public Boolean possibleDeFaireQuete(AvancerJoueur parAvancerJoueur){
        /*
        Remarque :
        Sur les 4 cases : (c0 ou c1) ET (c2 ou c3)
         */

        if (parAvancerJoueur.getListNoQuetes().contains(this.numero)){
            // Si la quête a déjà été faite, on ne peut pas la refaire.
            return false;
        }

        else if (this.numero == 0 && this.experience > parAvancerJoueur.getXp()){
            //C'est la quête finale et le joueur n'a pas assez d'expérience pour la faire.
            return false;
        }

        else if (this.preconditions[0] == 0){
            // Cas où il n'y a pas de précondition pour faire la quête
            return true;
        }

        else if (! (parAvancerJoueur.getListNoQuetes().contains(this.preconditions[0]) || parAvancerJoueur.getListNoQuetes().contains(this.preconditions[1]))){
            // Si le premier critère n'est pas valide (aucune quête n'a été faite)
            return false;
        }

        else if (this.preconditions[2] == 0){
            // Cas où le premier critère est valide, et qu'il n'y a pas de deuxième critère
            return true;
        }

        else if (! (parAvancerJoueur.getListNoQuetes().contains(this.preconditions[2]) || parAvancerJoueur.getListNoQuetes().contains(this.preconditions[3]))){
            // Si le deuxième critère n'est pas valide (aucune quête n'a été faite), même si le premier critère est valide
            return false;
        }

        else{
            // Sinon, les deux critères sont valides
            return true;
        }

    }


    public int getNumero(){ return numero; }
    public int[] getPosition(){ return position; }
    public int[] getPreconditions(){ return preconditions; }
    public int getDuree(){ return duree; }
    public int getExperience(){ return experience; }
    public String getIntitule(){ return intitule; }

    public TreeMap<Float, ArrayList<Quete>> getVoisinage(){return voisinage;}
}
