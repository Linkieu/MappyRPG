/**
 * La classe Algorithmes représente les différents algorithmes permettant de trouver les itinéraires souhaités par
 * l'utilisateur.
 */

package modele;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Algorithmes {

    /**
     * Principe :
     *      Méthode principale de recherche.
     *      En fonction de la demande de l'utilisateur, elle divise la recherche et récupère ainsi les résultats issus de ses méthodes soeurs.
     *      Cette méthode renvois les itinéraires en commun.
     *
     * Entrée :
     *      @param     scenar              [SCENARIO] : Le fichier scénario avec lequel on va trouver les solutions
     *      @param     choixUser   [CHOIXUTILISATEUR] : Ce que veux l'utilisateur comme résultat
     *      @param     debogage             [BOOLEAN] : renvoi le résultat d'un unique critère. True : actif (par défaut : false)
     *
     * Sortie :
     *      @return    listItinerairesFinaux      [ARRAYLIST > ITINERAIRE] : Set des différents itinéraires trouvés.
     *                                                                  Ils sont triés par score.
     */
    public static ArrayList<Itineraire> rechercheItineraire(Scenario scenar, ChoixUtilisateur choixUser, boolean debogage){
        // Les itinéraires qui correspond à la recherche de l'utilisateur
        ArrayList<Itineraire> listItinerairesFinaux = new ArrayList<>();
        int minXP = scenar.getProvQuetes().get(scenar.getProvNoQuetes().indexOf(0)).getExperience();



        // On récupère des objets AvancerJoueur comportant toutes les possibilités de quête obligatoire à faire.
        HashSet<AvancerJoueur> chCritiquesAvantTri = scenar.cheminsCritiques();
        // On va trier les objets, chCritiques contiendra la liste de chemin critique correspondant à la recherche de l'utilisateur
        HashSet<AvancerJoueur> chCritiques = new HashSet<>();

        // On trie les chemin critiques ne pouvant jamais correspondre à la demande de l'utilisateur
        // ChoixUtilisateur choixMaxPourTrier = new ChoixUtilisateur(-1, -1, -1, -1, choixUser.getMinTempsAccorde(), choixUser.getMaxTempsAccorde(), choixUser.getMinDeplacementAccorde(), choixUser.getMaxDeplacementAccorde(), choixUser.getMinNbrQueteSouhaitees(), choixUser.getMaxNbrQueteSouhaitees(), 50);
        // On retire tous les chemins critiques dépassant les valeurs maximales de l'utilisateur
        ChoixUtilisateur choixMaxPourTrier = new ChoixUtilisateur(-1, -1, -1, 0, choixUser.getMaxTempsAccorde(), 0, choixUser.getMaxDeplacementAccorde(), 0, choixUser.getMaxNbrQueteSouhaitees(), 0, -1, 0, 50);
        for (AvancerJoueur unAJ : chCritiquesAvantTri){
            if (unAJ.verifAttenteUser(choixMaxPourTrier)){
                chCritiques.add(unAJ); // L'objet correspond à sa recherche
            }
        }

        // A suppr probablement
        if (chCritiques.size() == 0) {return listItinerairesFinaux;} // Dans le cas où la recherche est trop restrictive.




        // Si le scénario est trop grand, on propose un plan b et c
        if (choixUser.getQueteLaPlusProche() == false && scenar.getProvQuetes().size() > 15 && (choixUser.getObjectifQuete() == 2 || choixUser.getObjectifDuree() == 2 || choixUser.getObjectifDeplacement() == 2)){
            if (choixUser.getTypeDeSolution() == 1){
                // On refuse de chercher, car aucun algo peut trouver une solution (sauf le récursif).
                return listItinerairesFinaux;
            }

            listItinerairesFinaux = insereDesQuetesplanC(scenar, chCritiques, choixUser, minXP);

            ArrayList<Itineraire> glouton = trouveItineraireGloutonPire(scenar, choixUser, minXP);

            if (! listItinerairesFinaux.contains(glouton.get(0))){ listItinerairesFinaux.addAll(glouton); }

            int tailleMax;
            if (listItinerairesFinaux.size() <= choixUser.getNbrSolutionsMax()){ tailleMax = listItinerairesFinaux.size(); }
            else { tailleMax = choixUser.getNbrSolutionsMax(); }

            listItinerairesFinaux.sort(Comparator.reverseOrder());

            return new ArrayList<Itineraire>(listItinerairesFinaux.subList(0, tailleMax));
        }
        else if (choixUser.getQueteLaPlusProche() == false && scenar.getProvQuetes().size() >= 20 && (choixUser.getTypeDeSolution() != 1)){
            listItinerairesFinaux = insereDesQuetesplanC(scenar, chCritiques, choixUser, minXP);

            ArrayList<Itineraire> glouton = trouveItineraireGloutonPire(scenar, choixUser, minXP);

            if (! listItinerairesFinaux.contains(glouton.get(0))){ listItinerairesFinaux.addAll(glouton); }

            int tailleMax;
            if (listItinerairesFinaux.size() <= choixUser.getNbrSolutionsMax()){ tailleMax = listItinerairesFinaux.size(); }
            else { tailleMax = choixUser.getNbrSolutionsMax(); }

            listItinerairesFinaux.sort(Comparator.reverseOrder());

            return new ArrayList<Itineraire>(listItinerairesFinaux.subList(0, tailleMax));
        }
        else if (choixUser.getQueteLaPlusProche() == false && scenar.getProvQuetes().size() >= 15 && choixUser.getNbrSolutionsMax() > 10){
            // On limite le nombre de solution à chercher à 10 au grand maximum.
            choixUser = new ChoixUtilisateur(choixUser.getObjectifDuree(), choixUser.getObjectifQuete(), choixUser.getObjectifDeplacement(), 0, choixUser.getMaxTempsAccorde(), choixUser.getMinDeplacementAccorde(), choixUser.getMaxDeplacementAccorde(), choixUser.getMinNbrQueteSouhaitees(), choixUser.getMaxNbrQueteSouhaitees(), choixUser.getMinXP(), choixUser.getMinXP(), choixUser.getTypeDeSolution(), 10);

        }



        // On va rechercher les itinéraires en fonction des critères demandés
        ArrayList<Itineraire> itineraireGlouton = new ArrayList<>();
        ArrayList<Itineraire> itineraireParDeplacement = new ArrayList<>();
        ArrayList<Itineraire> itineraireParDuree = new ArrayList<>();
        ArrayList<Itineraire> itineraireParNbQuetes = new ArrayList<>();

        ArrayList<Itineraire> itineraireTrouves = new ArrayList<>();
        if (choixUser.getQueteLaPlusProche()){
            itineraireGlouton = trouveItineraireGlouton(scenar, choixUser, minXP);

            return itineraireGlouton;
        }

        if (choixUser.getObjectifDeplacement() == 1 || choixUser.getObjectifDeplacement() == 2) {
            itineraireParDeplacement = trouveItineraireDeplacement(scenar, chCritiques, choixUser, minXP);

            if (debogage){ return itineraireParDeplacement; }
        }

        if (choixUser.getObjectifDuree() == 1 || choixUser.getObjectifDuree() == 2) {
            itineraireParDuree = trouveItineraireTemps(scenar, chCritiques, choixUser, minXP);

            if (debogage){ return itineraireParDuree; }
        }

        if (choixUser.getObjectifQuete() == 1 || choixUser.getObjectifQuete() == 2) {
            itineraireParNbQuetes = trouveItineraireNbQuetes(scenar, chCritiques, choixUser, minXP);

            if (debogage){ return itineraireParNbQuetes; }
        }


        // On va chercher quel est la liste avec le moins d'éléments.
        ArrayList<Itineraire> minlist = itineraireParDuree;
        String critereMinList = "duree";

        if ((minlist.size() == 0 || minlist.size() > itineraireParDeplacement.size()) && choixUser.getObjectifDeplacement() > 0 ){
            // Si sur le critère de durée nous n'avions aucun résultat ou plus que sur le critère de déplacement,
            // alors on prend la liste concernant le critère de déplacement à la place.
            minlist = itineraireParDeplacement;
            critereMinList = "deplacement";
        }
        if ((minlist.size() == 0 || minlist.size() > itineraireParNbQuetes.size()) && choixUser.getObjectifQuete() > 0){
            // Si nous n'avons aucun résultat ou plus que sur le critère de nombre de quêtes,
            // alors on prend la liste concernant le critère du nombre de quêtes à la place.
            minlist = itineraireParNbQuetes;
            critereMinList = "nbquetes";
        }



        for (Itineraire iti : minlist){
            iti.reDefinirScore(choixUser);

            if (critereMinList == "duree"){
                // Soit il correspond au critère par déplacement, soit ce critère est désactivé
                boolean cestOK = itineraireParDeplacement.contains(iti) || (! (choixUser.getObjectifDeplacement() == 1 || choixUser.getObjectifDeplacement() == 2));

                if (cestOK == true && (itineraireParNbQuetes.contains(iti) || (! (choixUser.getObjectifQuete() == 1 || choixUser.getObjectifQuete() == 2)))){
                    // Si c'est ok pour le précédent critère et qu'il répond à ce critère aussi (ou ce critère est désactivé)
                    listItinerairesFinaux.add(iti);
                }
            }

            else if (critereMinList == "deplacement"){
                // Soit il correspond au critère par durée, soit ce critère est désactivé
                boolean cestOK = itineraireParDuree.contains(iti) || (! (choixUser.getObjectifDuree() == 1 || choixUser.getObjectifDuree() == 2));

                if (cestOK == true && (itineraireParNbQuetes.contains(iti) || (! (choixUser.getObjectifQuete() == 1 || choixUser.getObjectifQuete() == 2)))){
                    // Si c'est ok pour le précédent critère et qu'il répond à ce critère aussi (ou ce critère est désactivé)
                    listItinerairesFinaux.add(iti);
                }
            }

            else if (critereMinList == "nbquetes"){
                // Soit il correspond au critère par durée, soit ce critère est désactivé
                boolean cestOK = itineraireParDuree.contains(iti) || (! (choixUser.getObjectifDuree() == 1 || choixUser.getObjectifDuree() == 2));


                if (cestOK == true && (itineraireParDeplacement.contains(iti) || (! (choixUser.getObjectifDeplacement() == 1 || choixUser.getObjectifDeplacement() == 2)))){
                    // Si c'est ok pour le précédent critère et qu'il répond à ce critère aussi (ou ce critère est désactivé)
                    listItinerairesFinaux.add(iti);
                }
            }

        }

        listItinerairesFinaux.sort(Comparator.naturalOrder());


        if (listItinerairesFinaux.size() < choixUser.getNbrSolutionsMax()){
            return new ArrayList<Itineraire>(listItinerairesFinaux.subList(0,listItinerairesFinaux.size()));
        }
        return new ArrayList<Itineraire>(listItinerairesFinaux.subList(0,choixUser.getNbrSolutionsMax()));

    }

    public static ArrayList<Itineraire> rechercheItineraire(Scenario scenar, ChoixUtilisateur choixUser){
        return rechercheItineraire(scenar, choixUser, false);
    }

    public static ArrayList<Itineraire> insereDesQuetesplanC (Scenario scenar, HashSet<AvancerJoueur> chCritiques,ChoixUtilisateur choixUser, int minXP){
        ArrayList<Itineraire> itinerairesTrouves = new ArrayList<>();

        if (chCritiques.size() == 0){
            // On ne peut pas chercher
            return itinerairesTrouves;
        }

        // Si nous voulons les pires résultats, mais que le scénario prossède trop de quête (et donc l'algo prendrais 747857 siècle à trouver une solution).
        ArrayList<AvancerJoueur> resultat = new ArrayList<>();


        for (AvancerJoueur unChCriOriginal : chCritiques){
            AvancerJoueur unChCri = new AvancerJoueur(unChCriOriginal);



            int maxRecherche = unChCri.getListQuetes().size() - 1;
            if (choixUser.getTypeDeSolution() == 1 || choixUser.getTypeDeSolution() == 2){
                // si get c'est 1, alors on s'arrête avant l'avant dernier, sinon, on s'arrête avant le dernier si c'est 2.
                maxRecherche = unChCri.getListQuetes().size() - 3 + choixUser.getTypeDeSolution();
            }


            while (unChCri.getListQuetes().size() < scenar.getProvQuetes().size()){
                unChCri.chercheEtAjouteQueteEntre(scenar, choixUser, maxRecherche);
                maxRecherche ++;
            }



            resultat.add(unChCri);
        }

        for (AvancerJoueur aj : resultat){
            aj.reDefinirScore(choixUser);
            Itineraire newIti = new Itineraire(aj, aj.getScore());
            if(! itinerairesTrouves.contains(newIti)){  itinerairesTrouves.add(newIti); }
        }

        return itinerairesTrouves;
    }





    /**
     * Principe :
     *      Trouve les itinéraires les plus adaptés pour l'utilisateur sur le critère de déplacement.
     *      Il classe les itinéraires à l'aide d'un score.
     *      Plus le score est bas, moins il y a de déplacements.
     *
     * Entrée :
     *      @param     scenar                      [SCENARIO] : Le fichier scénario avec lequel on va trouver les solutions
     *      @param     chCritiques  [HASHSET > AVANCERJOUEUR] : Les chemins critiques, la base de notre recherche
     *      @param     parChoixUser           [CHOIXUTILISATEUR] : Ce que veux l'utilisateur comme résultat
     *      @param     minXP                            [INT] : Le minimum d'expérience que chaque parcours doit avoir
     *
     * Sortie :
     *      @return    itinerairesTrouves      [ARRAYLIST > ITINERAIRE] : Liste des différents itinéraires trouvés.
     */
    public static ArrayList<Itineraire> trouveItineraireDeplacement(Scenario scenar, HashSet<AvancerJoueur> chCritiques,ChoixUtilisateur parChoixUser, int minXP){
        ArrayList<Itineraire> itinerairesTrouves = new ArrayList<>();

        // On modifie l'objet pour être certain qu'on traite seulement et uniquement le cas du déplacement.
        ChoixUtilisateur choixUser = new ChoixUtilisateur(-1, -1, parChoixUser.getObjectifDeplacement(), parChoixUser.getMinTempsAccorde(), parChoixUser.getMaxTempsAccorde(), parChoixUser.getMinDeplacementAccorde(), parChoixUser.getMaxDeplacementAccorde(), parChoixUser.getMinNbrQueteSouhaitees(), parChoixUser.getMaxNbrQueteSouhaitees(), parChoixUser.getMinXP(), parChoixUser.getMaxXP(), parChoixUser.getTypeDeSolution(), parChoixUser.getNbrSolutionsMax());

        // On cherche le meilleur AvancerJoueur en terme de déplacement, concernant le maximum de déplacement.
        // Cela peut être le meilleur en terme de déplacement comme le pire, en fonction du choix de l'utilisateur
        // C'est donc notre "modèle"
        AvancerJoueur modeleChCritique = null;

        // Dans le cas où nous avons toujours pas trouvé de modèle, on gardera le chemin invalide avec le moins/plus de
        // déplacement en compensation (moins si on cherche les chemins optimal, plus sinon).

        // Cela dit, pour faire mieux que le modèle, il faut déjà que celui-ci fasse parti de l'intervalle de valeurs données par l'utilisateur.
        // Si on a retiré les chemins critiques qui dépassent les max, on a vérifié celles qui n'ont pas atteint les min. Donc on vérifie maintenant.
        ChoixUtilisateur choixMinPourTrier = new ChoixUtilisateur(-1, -1, -1, choixUser.getMinTempsAccorde(), -1, choixUser.getMinDeplacementAccorde(), -1, choixUser.getMinNbrQueteSouhaitees(), -1, 0, -1, 0, 50);

        for (AvancerJoueur unChCritique : chCritiques) {
            if (modeleChCritique == null && unChCritique.getXp() >= minXP) {
                modeleChCritique = unChCritique;
            }

            else if (/*choixUser.getObjectifDeplacement() == 1 &&*/ modeleChCritique != null) {
                // Si on cherche un trajet avec le moins d'itinéraire possible tout en respectant les contraintes de l'utilisateur
                if (unChCritique.getDeplacements() < modeleChCritique.getDeplacements() && unChCritique.getXp() >= minXP && unChCritique.verifAttenteUser(choixMinPourTrier)) {
                    modeleChCritique = unChCritique;
                }
            }
        }

        if (modeleChCritique == null || choixUser.getTypeDeSolution() == 2){
            // Dans l'algorithme, quand le modèle ne possède pas de quêtes, il est considéré comme null.
            modeleChCritique = new AvancerJoueur();
        }

        modeleChCritique.reDefinirScore(choixUser);

        AvancerJoueur newChemin = new AvancerJoueur();
        ArrayList<AvancerJoueur> solution = new ArrayList<>();

        if (modeleChCritique.getListNoQuetes().size() > 0 && modeleChCritique.verifAttenteUser(choixUser, true, scenar)){ solution.add(modeleChCritique);}



        if (choixUser.getTypeDeSolution() == 2){ modeleChCritique = new AvancerJoueur(); }

        newChemin.termineParcours(scenar, choixUser, modeleChCritique, minXP, solution);

        if (choixUser.getObjectifDeplacement() == 1){
            solution.sort(Comparator.naturalOrder());
        }
        else if (choixUser.getObjectifDeplacement() == 2){
            solution.sort(Comparator.reverseOrder());
        }

        for (AvancerJoueur futurItineraire : solution){
            futurItineraire.reDefinirScore(parChoixUser);
            Itineraire itineraire = new Itineraire(futurItineraire, futurItineraire.getScore());
            itinerairesTrouves.add(itineraire);

        }

        return itinerairesTrouves;
    }


    /**
     * Principe :
     *      Génère un itinéraire où le joueur fait la quête la plus proche à chaque instant.
     *      Il peut faire dès que possible la quête final ou attendre un certain nombre de quête avant de la faire.

     *
     * Entrée :
     *      @param     scenar                      [SCENARIO] : Le fichier scénario avec lequel on va trouver les solutions
     *      @param     parChoixUser           [CHOIXUTILISATEUR] : Ce que veux l'utilisateur comme résultat
     *      @param     minXP                            [INT] : Le minimum d'expérience que chaque parcours doit avoir
     *
     * Sortie :
     *      @return    itinerairesTrouves      [ARRAYLIST > ITINERAIRE] : Liste des différents itinéraires trouvés.
     */
    public static ArrayList<Itineraire> trouveItineraireGlouton(Scenario scenar, ChoixUtilisateur parChoixUser, int minXP) {
        ArrayList<Itineraire> itinerairesTrouves = new ArrayList<>();
        AvancerJoueur avancerJoueur = new AvancerJoueur();

        //(! avancerJoueur.getListNoQuetes().contains(0))

        boolean possibleDeFaireFinale = false;
        while (! possibleDeFaireFinale) {

            boolean minQueteOK = (parChoixUser.getMinNbrQueteSouhaitees() != -1 && avancerJoueur.getListNoQuetes().size() >= parChoixUser.getMinNbrQueteSouhaitees() - 1); // true quand le minimum de quête demandé est atteint (hors finale)
            boolean exhaustif = (parChoixUser.getTypeDeSolution() == 2 && avancerJoueur.getListNoQuetes().size() == scenar.getProvQuetes().size() - 1); // true quand toute les quêtes sauf la finale ont été faite.
            boolean autre = parChoixUser.getTypeDeSolution() != 2 && parChoixUser.getMinNbrQueteSouhaitees() == -1;

            if (autre || minQueteOK || exhaustif) {
                // Cas où il y a pas de contrainte sur le nombre de quête minimale
                Object[] laQueteProche = avancerJoueur.quetePlusProcheDuJoueur(scenar);
                possibleDeFaireFinale = (((Quete) laQueteProche[0]).getNumero() == 0 && (minQueteOK || exhaustif || autre));
                avancerJoueur.ajouteQuete((Quete) laQueteProche[0], (Integer) laQueteProche[1]);
            }
            else{
                Object[] laQueteProche = avancerJoueur.quetePlusProcheDuJoueur(scenar, false);
                possibleDeFaireFinale = (((Quete) laQueteProche[0]).getNumero() == 0 && (minQueteOK || exhaustif || autre));
                avancerJoueur.ajouteQuete((Quete) laQueteProche[0], (Integer) laQueteProche[1]);
            }
        }

        avancerJoueur.reDefinirScore(parChoixUser);
        Itineraire itineraire = new Itineraire(avancerJoueur, avancerJoueur.getScore());
        itinerairesTrouves.add(itineraire);
        return itinerairesTrouves;
    }

    /**
     * Principe :
     *      Génère un itinéraire où le joueur fait la pire quête à chaque instant.
     *      Il peut faire dès que possible la quête final ou attendre un certain nombre de quête avant de la faire.
     *
     *      AVIS AUX CORRECTEURS :
     *          Cette méthode a été construite à la dernière minute, elle n'incorpore pas toute les fonctions du logiciel,
     *          et il est possible que son fonctionnement soit un peu tordu...
     *          Mais il a été créer dans l'optique de proposer au moins 1 résultat tandis que les autres groupes en propose 0...
     *
     * Entrée :
     *      @param     scenar                      [SCENARIO] : Le fichier scénario avec lequel on va trouver les solutions
     *      @param     parChoixUser           [CHOIXUTILISATEUR] : Ce que veux l'utilisateur comme résultat
     *
     * Sortie :
     *      @return    itinerairesTrouves      [ARRAYLIST > ITINERAIRE] : Liste des différents itinéraires trouvés.
     */
    public static ArrayList<Itineraire> trouveItineraireGloutonPire(Scenario scenar, ChoixUtilisateur parChoixUser, int minXP) {
        ArrayList<Itineraire> itinerairesTrouves = new ArrayList<>();

        if (parChoixUser.getTypeDeSolution() == 1){
            return itinerairesTrouves;
        }

        AvancerJoueur avancerJoueur = new AvancerJoueur();

        boolean autre = false; // Cas où aucun boolean ne marche, pour éviter que ça plante et que ça fournisse quand même un résultat

        boolean possibleDeFaireFinale = false;
        while (! possibleDeFaireFinale) {

            boolean minQueteOK = (parChoixUser.getMinNbrQueteSouhaitees() != -1 && avancerJoueur.getListNoQuetes().size() >= parChoixUser.getMinNbrQueteSouhaitees() - 1); // true quand le minimum de quête demandé est atteint (hors finale)
            boolean minDeplacementOK = (parChoixUser.getMinDeplacementAccorde() != -1 && avancerJoueur.getDeplacements() >= parChoixUser.getMinDeplacementAccorde());
            boolean minDureeOK = (parChoixUser.getMinTempsAccorde() != -1 && avancerJoueur.getTemps() >= parChoixUser.getMinTempsAccorde());
            boolean minXPOK = (avancerJoueur.getXp() >= minXP && parChoixUser.getMinXP() != -1 && avancerJoueur.getXp() >= parChoixUser.getMinXP());
            boolean exhaustif = (parChoixUser.getTypeDeSolution() == 2 && avancerJoueur.getListNoQuetes().size() == scenar.getProvQuetes().size() - 1); // true quand toute les quêtes sauf la finale ont été faite.


            if (minQueteOK || minDeplacementOK || minDureeOK || minXPOK || exhaustif || autre) {
                // Cas où il y a pas de contrainte sur le nombre de quête minimale
                Object[] laQueteProche = avancerJoueur.cherchePireQuete(scenar, parChoixUser, true);
                possibleDeFaireFinale = (((Quete) laQueteProche[0]).getNumero() == 0 && (minQueteOK || minDeplacementOK || minDureeOK || minXPOK || exhaustif || autre));
                avancerJoueur.ajouteQuete((Quete) laQueteProche[0], (Integer) laQueteProche[1]);
            }
            else{
                Object[] laQueteProche = avancerJoueur.cherchePireQuete(scenar, parChoixUser , false);
                if (laQueteProche[0] != null) {
                    possibleDeFaireFinale = (avancerJoueur.getXp() >= minXP && ((Quete) laQueteProche[0]).getNumero() == 0 && (minQueteOK || minDeplacementOK || minDureeOK || minXPOK || exhaustif || autre));
                    avancerJoueur.ajouteQuete((Quete) laQueteProche[0], (Integer) laQueteProche[1]);
                }
                else {
                    autre = true;
                }
            }
        }

        avancerJoueur.reDefinirScore(parChoixUser);
        Itineraire itineraire = new Itineraire(avancerJoueur, avancerJoueur.getScore());
        itinerairesTrouves.add(itineraire);
        return itinerairesTrouves;
    }



    /**
     * Principe :
     *      Trouve les itinéraires les plus adaptés pour l'utilisateur sur le critère de temps.
     *      Il classe les itinéraires à l'aide d'un score.
     *      Plus le score est bas, moins le parcours demande de temps.
     *
     * Entrée :
     *      @param     scenar                      [SCENARIO] : Le fichier scénario avec lequel on va trouver les solutions
     *      @param     chCritiques  [HASHSET > AVANCERJOUEUR] : Les chemins critiques, la base de notre recherche
     *      @param     parChoixUser           [CHOIXUTILISATEUR] : Ce que veux l'utilisateur comme résultat
     *      @param     minXP                            [INT] : Le minimum d'expérience que chaque parcours doit avoir
     *
     * Sortie :
     *      @return    itinerairesTrouves      [ARRAYLIST > ITINERAIRE] : Liste des différents itinéraires trouvés.
     */
    public static ArrayList<Itineraire> trouveItineraireTemps(Scenario scenar, HashSet<AvancerJoueur> chCritiques,ChoixUtilisateur parChoixUser, int minXP){
        ArrayList<Itineraire> itinerairesTrouves = new ArrayList<>();

        // On modifie l'objet pour être certain qu'on traite seulement et uniquement le cas du déplacement.
        ChoixUtilisateur choixUser = new ChoixUtilisateur(parChoixUser.getObjectifDuree(), -1, -1, parChoixUser.getMinTempsAccorde(), parChoixUser.getMaxTempsAccorde(), parChoixUser.getMinDeplacementAccorde(), parChoixUser.getMaxDeplacementAccorde(), parChoixUser.getMinNbrQueteSouhaitees(), parChoixUser.getMaxNbrQueteSouhaitees(), parChoixUser.getMinXP(), parChoixUser.getMaxXP(), parChoixUser.getTypeDeSolution(), parChoixUser.getNbrSolutionsMax());

        // On cherche le meilleur AvancerJoueur en terme de déplacement, concernant le maximum de déplacement.
        // Cela peut être le meilleur en terme de déplacement comme le pire, en fonction du choix de l'utilisateur
        // C'est donc notre "modèle"
        AvancerJoueur modeleChCritique = null;

        // Cela dit, pour faire mieux que le modèle, il faut déjà que celui-ci fasse parti de l'intervalle de valeurs données par l'utilisateur.
        // Si on a retiré les chemins critiques qui dépassent les max, on a vérifié celles qui n'ont pas atteint les min. Donc on vérifie maintenant.
        ChoixUtilisateur choixMinPourTrier = new ChoixUtilisateur(-1, -1, -1, choixUser.getMinTempsAccorde(), -1, choixUser.getMinDeplacementAccorde(), -1, choixUser.getMinNbrQueteSouhaitees(), -1, 0, -1, 0, 50);

        for (AvancerJoueur unChCritique : chCritiques) {
            if (modeleChCritique == null && unChCritique.getXp() >= minXP) {
                modeleChCritique = unChCritique;
            }

            else if (/*choixUser.getObjectifDeplacement() == 1 &&*/ modeleChCritique != null) {
                // Si on cherche un trajet avec le moins d'itinéraire possible tout en respectant les contraintes de l'utilisateur
                if (unChCritique.getTemps() < modeleChCritique.getTemps() && unChCritique.getXp() >= minXP && unChCritique.verifAttenteUser(choixMinPourTrier)) {
                    modeleChCritique = unChCritique;
                }
            }

        }

        if (modeleChCritique == null || choixUser.getTypeDeSolution() == 2){
            // Dans l'algorithme, quand le modèle ne possède pas de quêtes, il est considéré comme null.
            modeleChCritique = new AvancerJoueur();
        }

        modeleChCritique.reDefinirScore(choixUser);

        AvancerJoueur newChemin = new AvancerJoueur();
        ArrayList<AvancerJoueur> solution = new ArrayList<>();

        if (modeleChCritique.getListNoQuetes().size() > 0 && modeleChCritique.verifAttenteUser(choixUser, true, scenar)){ solution.add(modeleChCritique);}



        if (choixUser.getTypeDeSolution() == 2){ modeleChCritique = new AvancerJoueur(); }

        newChemin.termineParcours(scenar, choixUser, modeleChCritique, minXP, solution);

        if (choixUser.getObjectifDuree() == 1){
            solution.sort(Comparator.naturalOrder());
        }
        else if (choixUser.getObjectifDuree() == 2){
            solution.sort(Comparator.reverseOrder());
        }

        for (AvancerJoueur futurItineraire : solution){
            futurItineraire.reDefinirScore(parChoixUser);
            Itineraire itineraire = new Itineraire(futurItineraire, futurItineraire.getScore());
            itinerairesTrouves.add(itineraire);

        }

        return itinerairesTrouves;
    }

    /**
     * Principe :
     *      Trouve les itinéraires les plus adaptés pour l'utilisateur sur le critère du nombre de quêtes.
     *      Il classe les itinéraires à l'aide d'un score.
     *      Plus le score est bas, moins il y a de quêtes.
     *
     * Entrée :
     *      @param     scenar                      [SCENARIO] : Le fichier scénario avec lequel on va trouver les solutions
     *      @param     chCritiques  [HASHSET > AVANCERJOUEUR] : Les chemins critiques, la base de notre recherche
     *      @param     parChoixUser           [CHOIXUTILISATEUR] : Ce que veux l'utilisateur comme résultat
     *      @param     minXP                            [INT] : Le minimum d'expérience que chaque parcours doit avoir
     *
     * Sortie :
     *      @return    itinerairesTrouves      [ARRAYLIST > ITINERAIRE] : Liste des différents itinéraires trouvés.
     */
    public static ArrayList<Itineraire> trouveItineraireNbQuetes(Scenario scenar, HashSet<AvancerJoueur> chCritiques,ChoixUtilisateur parChoixUser, int minXP){
        ArrayList<Itineraire> itinerairesTrouves = new ArrayList<>();

        // On modifie l'objet pour être certain qu'on traite seulement et uniquement le cas du déplacement.
        ChoixUtilisateur choixUser = new ChoixUtilisateur(-1, parChoixUser.getObjectifQuete(), -1, parChoixUser.getMinTempsAccorde(), parChoixUser.getMaxTempsAccorde(), parChoixUser.getMinDeplacementAccorde(), parChoixUser.getMaxDeplacementAccorde(), parChoixUser.getMinNbrQueteSouhaitees(), parChoixUser.getMaxNbrQueteSouhaitees(), parChoixUser.getMinXP(), parChoixUser.getMaxXP(), parChoixUser.getTypeDeSolution(), parChoixUser.getNbrSolutionsMax());

        // On cherche le meilleur AvancerJoueur en terme de déplacement, concernant le maximum de déplacement.
        // Cela peut être le meilleur en terme de déplacement comme le pire, en fonction du choix de l'utilisateur
        // C'est donc notre "modèle"
        AvancerJoueur modeleChCritique = null;


        // Cela dit, pour faire mieux que le modèle, il faut déjà que celui-ci fasse parti de l'intervalle de valeurs données par l'utilisateur.
        // Si on a retiré les chemins critiques qui dépassent les max, on a vérifié celles qui n'ont pas atteint les min. Donc on vérifie maintenant.
        ChoixUtilisateur choixMinPourTrier = new ChoixUtilisateur(-1, -1, -1, choixUser.getMinTempsAccorde(), -1, choixUser.getMinDeplacementAccorde(), -1, choixUser.getMinNbrQueteSouhaitees(), -1, 0, -1, 0, 50);

        for (AvancerJoueur unChCritique : chCritiques) {
            if (modeleChCritique == null && unChCritique.getXp() >= minXP) {
                modeleChCritique = unChCritique;
            }

            else if (/*choixUser.getObjectifDeplacement() == 1 &&*/ modeleChCritique != null) {
                // Si on cherche un trajet avec le moins d'itinéraire possible tout en respectant les contraintes de l'utilisateur
                if (unChCritique.getListNoQuetes().size() < modeleChCritique.getListNoQuetes().size() && unChCritique.getXp() >= minXP && unChCritique.verifAttenteUser(choixMinPourTrier)) {
                    modeleChCritique = unChCritique;
                }
            }

        }

        if (modeleChCritique == null || choixUser.getTypeDeSolution() == 2){
            // Dans l'algorithme, quand le modèle ne possède pas de quêtes, il est considéré comme null.
            modeleChCritique = new AvancerJoueur();
        }

        modeleChCritique.reDefinirScore(choixUser);

        AvancerJoueur newChemin = new AvancerJoueur();
        ArrayList<AvancerJoueur> solution = new ArrayList<>();

        if (modeleChCritique.getListNoQuetes().size() > 0 && modeleChCritique.verifAttenteUser(choixUser, true, scenar)){ solution.add(modeleChCritique);}



        if (choixUser.getTypeDeSolution() == 2){ modeleChCritique = new AvancerJoueur(); }

        newChemin.termineParcours(scenar, choixUser, modeleChCritique, minXP, solution);

        if (choixUser.getObjectifQuete() == 1){
            solution.sort(Comparator.naturalOrder());
        }
        else if (choixUser.getObjectifQuete() == 2){
            solution.sort(Comparator.reverseOrder());
        }

        for (AvancerJoueur futurItineraire : solution){
            futurItineraire.reDefinirScore(parChoixUser);
            Itineraire itineraire = new Itineraire(futurItineraire, futurItineraire.getScore());
            itinerairesTrouves.add(itineraire);

        }

        return itinerairesTrouves;
    }

}

