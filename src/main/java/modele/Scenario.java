package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Cette classe crée des Scenarios
 */
public class Scenario{
    private ArrayList<Quete> provQuetes;
    private ArrayList<Integer> provNoQuetes;

    private Quete queteFinale; // Au met de côté la quête finale pour éviter d'aller la chercher laborieusement quand on en a besoin.

    public Scenario(){
        provQuetes = new ArrayList<>();
        provNoQuetes = new ArrayList<>();
    }

    public void ajout(Quete quete) {

        provQuetes.add(quete);
        provNoQuetes.add(quete.getNumero());

        if(quete.getNumero() == 0){
            queteFinale = quete;
        }
    }

    /**
     * Principe :
     *      Sous méthode de cheminsCritiques.
     *      A partir de deux HashSet contenant des objets AvancerJoueur, on fusionne un AvancerJoueur de HashSet 1
     *      avec un AvancerJoueur de HashSet 2.
     *
     *      Exemple :
     *          AJ = AvancerJoueur, A/B = HashSet A ou B, 1,2,3... = numéro de l'objet AvancerJoueur
     *
     *          hsA = [AJA1, AJA2, AJA3, AJA4]; hsB = [AJB1, AJB2, AJB3]
     *
     *          Cela donne AJA1 + AJB1, AJA1 + AJB2, [...], AJA4 + AJB2, AJA4 + AJB3
     *
     * @param :
     *      hsA                  [HASHSET > AVANCERJOUEUR : Objet HashSet A
     *      hsB                  [HASHSET > AVANCERJOUEUR : Objet HashSet B
     *
     * @return :
     *      resFusion          [HASHESET > AVANCERJOUEUR] : Les différents AvancerJoueur issus de A et B
     */
    public static HashSet<AvancerJoueur> fusion_pour_cheminsCritiques(HashSet<AvancerJoueur> hsA, HashSet<AvancerJoueur> hsB){
        HashSet<AvancerJoueur> resFusion = new HashSet<>();

        for (AvancerJoueur ajA : hsA){
            for (AvancerJoueur ajB : hsB){

                // On clone ajA car la fusion modifie l'objet. Sauf qu'on ne veut pas modifier ajA, on va donc modifier une copie.
                AvancerJoueur copieAJA = new AvancerJoueur(ajA);

                copieAJA.fusion(ajB); // On fusionne
                resFusion.add(copieAJA); // On ajoute le résultat
            }
        }

        if (hsA.isEmpty()){
            // Si le premier HashSet est vide, alors on retourne juste le deuxième.
            return hsB;
        }
        else if (hsB.isEmpty()){
            // et inversement
            return hsA;
        }

        return resFusion;
    }

    /**
     * Principe :
     * 	    Détermine les chemins critiques du scénario.
     * 	    C'est-à-dire les quêtes à faire obligatoirement afin de terminer le jeu et leur ordre.
     *
     * 	    Il peut y avoir plusieurs chemins critiques en fonction de la complexité du scénario.
     *
     * 	    ATTENTION : On ne prend pas en compte l'expérience qui peut manquer !
     * 		    Il est tout à fait possible qu'il faudra ajouter des quêtes aux parcours
     * 		    afin d'avoir assez de d'expérience pour pouvoir faire certaines quêtes
     * 		    critiques. Il est donc important de le prendre en compte pour les méthode
     * 		    utilisant celle-ci.
     *
     * 	    ---
     * 	    Un chemin critique sera représenté par un objet AvancerJoueur.
     * 	    Le premier élément est la première Quête à faire, et le dernier la dernière (donc le boss).
     *
     * 	    L'ensemble des chemins critiques seront stockés eux même dans une liste.
     *
     *      ---
     *      Remarque :
     *          Algorithme récursif.
     *
     *          1) Appel de chaque Quete Précond avec cette méthode, on stocke chaque appel dans des HashSet<AvancerJoueur> distinctes
     *          OU
     *          1) Créer un objet AvancerJoueur et le mettre dans lesCheminsCritiques
     *
     *          2) Ellaboration des couples
     *
     *          3) Attaque !
     *              -> Si ET = Fusion (double boucle : lCC1 * lCC2)
     *              -> Si OU = On ajoute lCC1 dans lCC2
     *
     *          4) S'ajouter dans tous les AvancerJoueur
     *
     *          5) Renvoyer lCC
     *
     *
     *
     * 	    Note : Tâche F du WBS
     *
     * @param :
     *
     *      laQueteDuChemin                 [QUETE] : une quête présent dans le parcours et dont nous devons l'étudier
     *
     *
     * 	    Remarque :  Ce paramètre est réservé uniquement à la version interne de la méthode.
     * 	                C'est une version réservé et uniquement utilisable par le programme en lui même.
     * 	                En effet, l'utilisateur ne connait pas les quêtes du chemin, de plus
     * 	                l'algorithme commence toujours par la quête finale.
     * 	                En bref, il n'y a aucune raison que l'utilisateur manipule les paramètres...
     *
     * @return :
     * 	    lesCheminsCritiques		[ARRAYLIST > AVANCERJOUEUR] : Liste comportant les chemins
     * 								                              critiques.
     */
    // Version interne de la méthode (réservé au programme)
    private HashSet<AvancerJoueur> cheminsCritiques(Quete laQueteDuChemin) {

        // On instancie l'ArrayList que nous allons renvoyer
        HashSet<AvancerJoueur> lesCheminsCritiques = new HashSet<AvancerJoueur>();

        ArrayList<HashSet<AvancerJoueur>> resAppelQuetesPrecond;


        if (laQueteDuChemin.getPreconditions()[0] != 0) {
            // Etape 1 : Appel de chaque Quete Précond avec cette méthode, on stocke chaque appel dans des HashSet<AvancerJoueur> distinctes

            resAppelQuetesPrecond = new ArrayList<HashSet<AvancerJoueur>>(4);

            for (int i = 0; i < 4; i++) {
                if (laQueteDuChemin.getPreconditions()[i] != 0) {

                    // On récupère l'index de la précondition (quête) sélectionnée, et on récupère la quête
                    int indexQueteSelect = provNoQuetes.indexOf(laQueteDuChemin.getPreconditions()[i]);
                    Quete queteSelect = provQuetes.get(indexQueteSelect);

                    // On stock sont résultat dans resAppelQuetesPrecond
                    resAppelQuetesPrecond.add(cheminsCritiques(queteSelect));
                }
                else {
                    resAppelQuetesPrecond.add(null);
                }

            }
        }

        // OU

        else {
            // 1) Créer un objet AvancerJoueur et le mettre dans lesCheminsCritiques (cas d'une feuille de l'arbre)

            // On créer un objet AvancerJoueur
            AvancerJoueur avancerJ = new AvancerJoueur();

            // ATTENTION :  Nous créons une quête qui n'est pas une quête du jeu !
            Quete positionJoueur = new Quete("-1|(0, 0)|()|0|0|depart joueur CE NEST PAS UNE QUETE");

            // On calcul la distance que le joueur doit parcourir, et on l'ajoute à son avancé
            int distanceJoueurQuete = laQueteDuChemin.distanceDeplacement(positionJoueur);
            avancerJ.ajouteQuete(laQueteDuChemin, distanceJoueurQuete);

            lesCheminsCritiques.add(avancerJ);

            return lesCheminsCritiques;
        }

        // 2) Attaque !
        //      -> Si ET = Fusion (double boucle : lCC1 * lCC2)
        //      -> Si OU = On ajoute lCC1 dans lCC2

        // Format du tableau resAppelQuetesPrecond : [A, B, C, D]. Les combinaisons possibles sont : AC, AD, BC, BD.

        if (resAppelQuetesPrecond.get(0) != null) {
            // S'il existe une précondition A

            if (resAppelQuetesPrecond.get(2) != null) {
                // On regarde s'il existe comme choix de faire les préconditions A et C pour faire la quête laQueteDuChemin
                HashSet<AvancerJoueur> AC = fusion_pour_cheminsCritiques((HashSet<AvancerJoueur>) resAppelQuetesPrecond.get(0), (HashSet<AvancerJoueur>) resAppelQuetesPrecond.get(2));
                lesCheminsCritiques.addAll(AC);

            }
            if (resAppelQuetesPrecond.get(3) != null) {
                // On regarde s'il existe comme choix de faire les préconditions A et D pour faire la quête laQueteDuChemin
                HashSet<AvancerJoueur> AD = fusion_pour_cheminsCritiques((HashSet<AvancerJoueur>) resAppelQuetesPrecond.get(0), (HashSet<AvancerJoueur>) resAppelQuetesPrecond.get(3));
                lesCheminsCritiques.addAll(AD);

            }
            if (resAppelQuetesPrecond.get(2) == null && resAppelQuetesPrecond.get(3) == null){
                // Cas où il n'existe pas de préconditions C et D, dans ce cas, on ajoute A seul
                lesCheminsCritiques.addAll((Collection<? extends AvancerJoueur>) resAppelQuetesPrecond.get(0));

            }

        }

        if (resAppelQuetesPrecond.get(1) != null) {
            // S'il existe une précondition B

            if (resAppelQuetesPrecond.get(2) != null) {
                // On regarde s'il existe comme choix de faire les préconditions B et C pour faire la quête laQueteDuChemin
                HashSet<AvancerJoueur> BC = fusion_pour_cheminsCritiques((HashSet<AvancerJoueur>) resAppelQuetesPrecond.get(1), (HashSet<AvancerJoueur>) resAppelQuetesPrecond.get(2));
                lesCheminsCritiques.addAll(BC);
            }
            if (resAppelQuetesPrecond.get(3) != null) {
                // On regarde s'il existe comme choix de faire les préconditions B et D pour faire la quête laQueteDuChemin
                HashSet<AvancerJoueur> BD = fusion_pour_cheminsCritiques((HashSet<AvancerJoueur>) resAppelQuetesPrecond.get(1), (HashSet<AvancerJoueur>) resAppelQuetesPrecond.get(3));
                lesCheminsCritiques.addAll(BD);
            }
            if (resAppelQuetesPrecond.get(2) == null && resAppelQuetesPrecond.get(3) == null){
                // Cas où il n'existe pas de préconditions C et D, dans ce cas, on ajoute B seul
                lesCheminsCritiques.addAll((Collection<? extends AvancerJoueur>) resAppelQuetesPrecond.get(1));
            }

        }

        // 3) S'ajouter dans tous les AvancerJoueur

        for (AvancerJoueur avancerJ : lesCheminsCritiques){
            int tailleLesCheminsCritiques = avancerJ.getListQuetes().size();
            int distance = laQueteDuChemin.distanceDeplacement(avancerJ.getQuete(tailleLesCheminsCritiques - 1));

            avancerJ.ajouteQuete(laQueteDuChemin, distance);
        }

        return lesCheminsCritiques;
    }

    // Version utilisable de la méthode (utilisable par le développeur)
    public HashSet<AvancerJoueur> cheminsCritiques(){

        HashSet<AvancerJoueur> lesCheminsCritiques;

        // On récupère la quête final, la racine de notre algorithme récursif.
        int indexQueteFinale = provNoQuetes.indexOf(0);
        Quete queteFinale = provQuetes.get(indexQueteFinale);

        lesCheminsCritiques = cheminsCritiques(queteFinale);

        // On renvoi la liste qui a été modifié par l'algorithme (et qui contient au moins 1 chemin critique, sauf problème du scénario)
        return lesCheminsCritiques;

    }


    public String toString(){
        return provQuetes.size() + " " + provQuetes.toString();
    }

    /**
     * Principe :
     *      Récupère une quête du scénario
     * Entrée :
     *      Id de la quête demandé
     * Sortie :
     *      la quête demandé
     */
    public Quete get(int idQuete) {
        Quete laQuete = null;

        try {
            laQuete = provQuetes.get(idQuete);
        } catch (Exception e) {
            System.out.println("ERREUR >> L'identifiant de la quête n'est pas valide.");
            System.out.println("          Id min autorisé: 0, id max autorisé: " + (provQuetes.size() - 1) + ", votre id: " + idQuete);
            System.exit(-1);
        }

        return laQuete;
    }

    public ArrayList<Quete> getProvQuetes(){ return (ArrayList<Quete>) provQuetes.clone();}

    public ArrayList<Quete> getProvNoQuetes(){ return (ArrayList<Quete>) provNoQuetes.clone();}

    public Quete getQueteFinale(){ return queteFinale;}
}
