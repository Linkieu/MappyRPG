package modele;

import java.util.*;

/**
 * Cette classe permet de suivre la progression du joueur.
 * Chaque objet représente un état du joueur durant la partie. C'est-à-dire le nombre de quêtes qu'il a effectuées,
 * le nombre d'expériences qu'il possède, mais également son nombre de déplacements ou encore son temps de jeu.
 */
public class AvancerJoueur implements Comparable<AvancerJoueur> {

    private int xp; // Contiendra l'expérience qu'a le joueur à un moment X
    private ArrayList<Quete> listQuetes; // Les objets quêtes qu'a fait le joueur à un moment X
    private ArrayList<Integer> listNoQuetes; // Pour nous simplifier la tâche, les numéros de ces tâches

    private int temps; // Temps de la partie jusqu'à présent
    private int deplacements; // Nombre de déplacements total jusqu'à présent
    private int progression; // progression du joueur (nombre de quêtes ajouté depuis le début)

    private int score;

    private int solutionEfficace; // 0 = None, 1 = True, 2 = False

    public AvancerJoueur(){
        xp = 0;
        listQuetes = new ArrayList<Quete>();
        listNoQuetes = new ArrayList<Integer>();
        temps = 0;
        deplacements = 0;
        progression = 0;
        score = 0;
        solutionEfficace = 0;
    }

    public AvancerJoueur(AvancerJoueur avancerJoueurACopier){
        // On créer this appartir d'un autre objet AvancerJoueur (on le clone).
        xp = avancerJoueurACopier.xp;
        listQuetes = (ArrayList<Quete>) avancerJoueurACopier.listQuetes.clone();
        listNoQuetes = (ArrayList<Integer>) avancerJoueurACopier.listNoQuetes.clone();
        temps = avancerJoueurACopier.temps;
        deplacements = avancerJoueurACopier.deplacements;
        progression = avancerJoueurACopier.progression;
        score = avancerJoueurACopier.score;
        solutionEfficace = avancerJoueurACopier.solutionEfficace;
    }


    /**
     * Principe :
     *      Remplace les valeurs de this par ceux de l'objet AvancerJoueur en paramètre.
     * Entrée :
     *      @param parAvancer   [AVANCERJOUEUR] : Objet à importer
     */
    public void importerAJ(AvancerJoueur parAvancer){
        xp = parAvancer.xp;
        listQuetes = parAvancer.listQuetes;
        listNoQuetes = parAvancer.listNoQuetes;
        temps = parAvancer.temps;
        deplacements = parAvancer.deplacements;
        progression = parAvancer.progression;
        score = parAvancer.score;
        solutionEfficace = parAvancer.solutionEfficace;
    }

    /**
     * Principe :
     *      On ajoute une quête dans la liste des quêtes faites par le joueur
     * Entrée :
     *      parQuete               [QUETE] : Quête que vient de faire le joueur
     *      parDeplacements          [INT] : Nombre de cases parcourues par le joueur avant d'atteindre cette quête
     *      choixUser   [CHOIXUTILISATEUR] (optionnel) : Volonté de l'utilisateur
     */
    public void ajouteQuete(Quete parQuete, int parDeplacements, ChoixUtilisateur choixUser){
        try{

            if (listNoQuetes.contains(parQuete.getNumero()) == true) {
                Exception e = new Exception();
                throw e;
            }
        }
        catch (Exception e){
            System.out.println("ERREUR >> Erreur d'ajout de la quête dans l'avancement du joueur");
            System.out.println("          Le joueur a déjà fait cette quête.");
            System.exit(-1);
        }

        try{

            if (parDeplacements < 1) {
                Exception e = new Exception();
                throw e;
            }
        }
        catch (Exception e){
            System.out.println("ERREUR >> Erreur d'ajout de la quête dans l'avancement du joueur");
            System.out.println("          La valeur du nombre de déplacements est inférieur à 1 !");
            System.out.println("          -> Les quêtes ne peuvent pas se superposer");
            System.exit(-1);
        }

        listQuetes.add(parQuete);
        listNoQuetes.add(parQuete.getNumero());

        // le déplacement vers une case = 1 unité de temps... Donc parDéplacements correspond aussi au temps de parcours.
        temps = temps + parDeplacements + parQuete.getDuree(); // On ajoute le temps de déplacement + le temps pour faire la quête
        deplacements = deplacements + parDeplacements; // On ajoute le temps de déplacement du joueur pour accéder à cette quête

        if (choixUser != null) {
            if (choixUser.getObjectifDeplacement() > 0) {
                score += parDeplacements;
            }
            else if (choixUser.getObjectifDuree() > 0){
                score += parQuete.getDuree() + parDeplacements;
            }
            else if (choixUser.getObjectifQuete() > 0){
                score = listQuetes.size();
            }
            else{
                System.out.println(choixUser.getObjectifQuete());
                System.out.println("[DEBOGAGE] : ATTENTION CETTE METHODE N'IMPLEMENTE PAS ENTIEREMENT CHOIX USER");
            }
        }

        if (parQuete.getNumero() != 0){
            // Si ce n'est pas la quête final. La quête final ne fait pas gagner d'expérience.
            xp = xp + parQuete.getExperience();
        }


        progression += 1;
    }

    public void ajouteQuete(Quete parQuete, int parDeplacements){
        ajouteQuete(parQuete, parDeplacements, null);
    }

    /**
     * Principe :
     *      On retire la dernière quête d'AvancerJoueur.
     * Entrée :
     *      @param      choixUser [CHOIXUTILISATEUR] : Le souhait de l'utilisateur
     * Retour :
     *      @return                  true [BOOLEAN] : la dernière quête a été supprimé avec succès
     *      @return                 false [BOOLEAN] : this ne comporte aucune quête.
     */
    public boolean retireDerniereQuete(ChoixUtilisateur choixUser){
        if (this.listQuetes.size() < 1){
            return false;
        }
        else if (this.listQuetes.size() == 1){
            // Il restait plus qu'une quête
            xp = 0;
            temps = 0;
            deplacements = 0;
            progression = 0;
            score = 0;
        }
        else {
            Quete derniereQuete = listQuetes.get(listQuetes.size() - 1);
            // Il en reste plusieurs

            if (derniereQuete.getNumero() != 0){
                xp -= derniereQuete.getExperience();
            }
            temps -= derniereQuete.distanceDeplacement(listQuetes.get(listQuetes.size() - 2));
            temps -= derniereQuete.getDuree();
            deplacements -= derniereQuete.distanceDeplacement(listQuetes.get(listQuetes.size() - 2));
            progression -= 1;

            if (choixUser != null) {
                if (choixUser.getObjectifDeplacement() != 0) {
                    score -= derniereQuete.distanceDeplacement(listQuetes.get(listQuetes.size() - 2));
            }
            else{
                System.out.println("[DEBOGAGE] : ATTENTION CETTE METHODE N'IMPLEMENTE PAS ENTIEREMENT CHOIX USER");
            }
        }

        }

        // On supprime le dernier élément de la liste
        listQuetes.remove(listQuetes.size() - 1);
        listNoQuetes.remove(listNoQuetes.size() - 1);
        return true;
    }

    /**
     *
     */
    public void ajouteQueteEntre(Quete parQuete, int parIndexPlacerCaAvant, ChoixUtilisateur choixUser){
        try{

            if (listNoQuetes.contains(parQuete.getNumero()) == true) {
                Exception e = new Exception();
                throw e;
            }
        }
        catch (Exception e){
            System.out.println(this);
            System.out.println(parQuete);
            System.out.println("ERREUR >> Erreur d'ajout de la quête dans l'avancement du joueur");
            System.out.println("          Le joueur a déjà fait cette quête.");
            System.exit(-1);
        }

        try{

            if (this.listQuetes.contains(parQuete)) {
                Exception e = new Exception();
                throw e;
            }
        }
        catch (Exception e){
            System.out.println("ERREUR >> Erreur d'ajout de la quête dans l'avancement du joueur");
            System.out.println("          L'objet AvancerJoueur possède déjà la quête.");
            System.exit(-1);
        }

        try{

            if (! (parIndexPlacerCaAvant >= 0 && parIndexPlacerCaAvant < this.listQuetes.size())) {
                Exception e = new Exception();
                throw e;
            }
        }
        catch (Exception e){
            System.out.println("ERREUR >> Erreur d'ajout de la quête dans l'avancement du joueur");
            System.out.println("          L'index demandé n'est pas valide");
            System.out.println("          -> Inférieur à 0 ou dépasse la liste des quêtes.");
            System.exit(-1);
        }


        listQuetes.add(parIndexPlacerCaAvant, parQuete);
        listNoQuetes.add(parIndexPlacerCaAvant, parQuete.getNumero());

        temps += Itineraire.verifTemps(this);
        deplacements += Itineraire.verifDeplacements(this);
        progression += 1;

        if (choixUser != null) {
            this.reDefinirScore(choixUser);
        }

        if (parQuete.getNumero() != 0){
            xp += parQuete.getExperience();
        }
    }


    /**
     * Principe :
     *      Dans le cas où this ne possède pas de score, ou qu'on souhaite changer le critère de celui-ci.
     * Entrée :
     *      @param choixUser        [CHOIXUTILISATEUR] : le souhait de l'utilisateur
     */
    public void reDefinirScore(ChoixUtilisateur choixUser){
        if (choixUser.getObjectifDeplacement() == 1 || choixUser.getObjectifDeplacement() == 2){ score = deplacements; }
        if (choixUser.getObjectifDuree() == 1 || choixUser.getObjectifDuree() == 2){ score = temps; }
        if (choixUser.getObjectifQuete() == 1 || choixUser.getObjectifQuete() == 2){ score = listQuetes.size(); }
    }


    /**
     * Principe :
     *      Algorithme récursif.
     *      Si this n'est pas terminé, on va chercher de le compléter :
     *              - En fonction du choix de l'utilisateur.
     *              - En cherchant à faire mieux que le modèle
     *              - En cherchant aussi à être au dessus du minimum d'expérience.
     *
     *      Si on s'aperçoit qu'on fait pire que le modèle, on cherche un nouveau chemin dès que possible.
     *      Si la quête finale ne peut pas être fait, on fait de même.
     * Entrée :
     *      @param parScenar    [SCENARIO] : Scénario étudié
     *      @param choixUser    [CHOIXUTILISATEUR] : Choix de l'utilisateur
     */
    public void termineParcours(Scenario parScenar, ChoixUtilisateur choixUser, AvancerJoueur modeleAJ, int minXP, ArrayList<AvancerJoueur> listeParcoursFinit) {
        int tailleAJ = listQuetes.size();

        // On cherche un itinéraire avec le moins de déplacement que possible
        boolean deplacementOptimal = choixUser.getObjectifDeplacement() == 1 && (deplacements < modeleAJ.deplacements);
        boolean deplacementPire = choixUser.getObjectifDeplacement() == 2;

        boolean tempsOptimal = choixUser.getObjectifDuree() == 1 && (temps < modeleAJ.temps);
        boolean tempsPire = choixUser.getObjectifDuree() == 2;

        boolean nbQuetesOptimal = choixUser.getObjectifQuete() == 1 && (listQuetes.size() < modeleAJ.listQuetes.size());
        boolean nbQuetesPire = choixUser.getObjectifQuete() == 2;

        if (tailleAJ > 0) {
            if (listNoQuetes.get(tailleAJ - 1) == 0) {
                if (xp >= minXP && this.verifAttenteUser(choixUser, true, parScenar)) {
                    // On a terminé le parcours, alors on l'ajoute comme un parcours terminé et utilisable.
                    listeParcoursFinit.add(this);

                    if (modeleAJ.listNoQuetes.size() == 0){
                        modeleAJ.importerAJ(this);
                    }
                    else if (deplacementOptimal || tempsOptimal || nbQuetesOptimal){
                        // On redéfinie this comme modèle car il fait mieux que le modèle.
                        modeleAJ.importerAJ(this);
                    }
                    if (listeParcoursFinit.size() > choixUser.getNbrSolutionsMax()){
                        if (choixUser.getObjectifDeplacement() == 1 || choixUser.getObjectifDuree() == 1 || choixUser.getObjectifQuete() == 1){
                            listeParcoursFinit.sort(Comparator.naturalOrder());
                        }
                        else if (choixUser.getObjectifDeplacement() == 2 || choixUser.getObjectifDuree() == 2 || choixUser.getObjectifQuete() == 2){
                            listeParcoursFinit.sort(Comparator.reverseOrder());
                        }

                        listeParcoursFinit.remove(listeParcoursFinit.size() - 1);
                    }
                }
                return;
            }
        }
        if (modeleAJ.listNoQuetes.size() == 0 || ( this.verifAttenteUser(choixUser, false) && (deplacementOptimal || deplacementPire || tempsOptimal || tempsPire || nbQuetesOptimal || nbQuetesPire))) {
            // Si on a pas terminé le parcours, et qu'on peut toujours faire mieux, alors on continu

            if (parScenar.getQueteFinale().possibleDeFaireQuete(this)){
                if (solutionEfficace == 0){
                    setSolutionEfficace(1);
                }
                else{
                    setSolutionEfficace(2);
                }
            }

            Quete positionJoueur;
            if (tailleAJ > 0) {
                positionJoueur = listQuetes.get(tailleAJ - 1);
            }
            else{
                positionJoueur = new Quete("-1|(0, 0)|()|0|0|depart joueur CE NEST PAS UNE QUETE");
            }

            TreeMap<Float, ArrayList<Quete>> dicoQuetes = positionJoueur.voisins(parScenar, choixUser);

            for (Float coutQuetes : dicoQuetes.keySet()) {
                ArrayList<Quete> lesQuetes = dicoQuetes.get(coutQuetes);
                for (Quete uneQuete : lesQuetes) {
                    if ((!this.listQuetes.contains(uneQuete)) && uneQuete.possibleDeFaireQuete(this)) {
                        AvancerJoueur newAJ = new AvancerJoueur(this);
                        newAJ.ajouteQuete(uneQuete, uneQuete.distanceDeplacement(positionJoueur), choixUser);
                        newAJ.termineParcours(parScenar, choixUser, modeleAJ, minXP, listeParcoursFinit);
                    }
                }
            }
        }
    }



    /**
     * Principe :
     *      Indique la quête la plus proche du joueur en fonction de sa dernière effectué.
     * Entrée :
     *      @param parScenario          [SCENARIO] : Le scénario comportant toutes les quêtes du jeu.
     *      @param avecFinale            [BOOLEAN] : si on évite ou non dès que possible la quête final (false, on évite. True par défaut)
     * Sortie :
     *      @return Object[]                : Liste qui comporte l'objet Quête queteTrouvee et distanceQuetePlusProche de type int.
     *                                        -> Ce n'est sûrement pas la meilleur solution. Elle peut être confuse.
     *                                           Mais c'est la plus simple, et la plus efficace selon nous.
     *                                           Nous préférons renvoyer une liste plutôt qu'un objet regroupant les deux par exemple.
     *
     *      Avec...
     *      @return queteTrouvee            [QUETE] : La quête la plus proche du joueur, trouvée.
     *      @return queteTrouvee             [NULL] : Dans le cas où aucune quête n'a été trouvée.
     *
     *      @return distanceQuetePlusProche   [INT] : Distance qui sépare la quête du joueur
     *      @return distanceQuetePlusProche    = -1 : Cas où aucune quête n'a été trouvée !
     */
    public Object[] quetePlusProcheDuJoueur(Scenario parScenario, boolean avecFinale){
        Quete quetePlusProche = null;
        int distanceQuetePlusProche = -1;

        Quete positionJoueur; // L'endroit où est actuellement le joueur

        if (this.listQuetes.size() > 0){
            // Cas où le joueur a fait au moins une quête

            // On récupère la dernière quête faite par le joueur
            positionJoueur = this.listQuetes.get(listQuetes.size() - 1);
        }
        else {
            // Cas où le joueur n'a fait aucune quête, et qu'on ne connait pas sa position.
            // Alors il est au tout début du jeu en position x=0 et y=0.

            /* ATTENTION :  Nous créons une quête qui n'est pas une quête du jeu !
                            Elle représente la position initial du joueur au début du jeu.
                            Utiliser la classe quête est le choix le plus facile et rapide à faire.

              Cette fausse quête, numéro -1 est en position x=0 y=0, position de départ du joueur.
            */
            positionJoueur = new Quete("-1|(0, 0)|()|0|0|depart joueur CE NEST PAS UNE QUETE");

        }

        // Maintenant, nous savons où se trouve le joueur via l'objet positionJoueur de type Quête.

        for (Quete uneQuete : parScenario.getProvQuetes()){
            // On parcour les quêtes du scénario

            if (uneQuete.possibleDeFaireQuete(this) && (! ((! avecFinale) && ((uneQuete.getNumero() == 0))))){
                if (quetePlusProche != null) {
                    // Nous allons voir si la quête sélectionné n'est pas plus proche du joueur.

                    if (distanceQuetePlusProche > uneQuete.distanceDeplacement(positionJoueur)) {
                        // Si la quête sélectionné est plus proche du joueur
                        quetePlusProche = uneQuete;
                        distanceQuetePlusProche = uneQuete.distanceDeplacement(positionJoueur);
                    }
                }
                else {
                    // Cas où on doit prendre la première quête disponible pour les comparaisons plus tard.

                    quetePlusProche = uneQuete;
                    distanceQuetePlusProche = uneQuete.distanceDeplacement(positionJoueur);
                }

            }
        }



        return new Object[]{quetePlusProche, distanceQuetePlusProche};

    }

    public Object[] quetePlusProcheDuJoueur(Scenario parScenario){
        return quetePlusProcheDuJoueur(parScenario, true);
    }

    /**
     * Principe :
     *      Indique la quête la pire quête en fonction de sa dernière effectué.
     * Entrée :
     *      @param parScenario          [SCENARIO] : Le scénario comportant toutes les quêtes du jeu.
     *      @param avecFinale            [BOOLEAN] : Si on évite ou non dès que possible la quête final (false, on évite. True par défaut)
     *      @param choixUser    [CHOIXUTILISATEUR] : Les choix de l'utilisateur
     * Sortie :
     *      @return Object[]                : Liste qui comporte l'objet Quête queteTrouvee et distanceQuetePlusProche de type int.
     *                                        -> Ce n'est sûrement pas la meilleur solution. Elle peut être confuse.
     *                                           Mais c'est la plus simple, et la plus efficace selon nous.
     *                                           Nous préférons renvoyer une liste plutôt qu'un objet regroupant les deux par exemple.
     *
     *      Avec...
     *      @return queteTrouvee            [QUETE] : La pire quête trouvée trouvée.
     *      @return queteTrouvee             [NULL] : Dans le cas où aucune quête n'a été trouvée.
     *
     *      @return distanceQuetePlusProche   [INT] : Distance qui sépare la quête du joueur
     *      @return distanceQuetePlusProche    = -1 : Cas où aucune quête n'a été trouvée !
     */
    public Object[] cherchePireQuete(Scenario parScenario, ChoixUtilisateur choixUser, boolean avecFinale){
        Quete quetePlusProche = null;
        int ratioPireQuete = -1;

        Quete positionJoueur; // L'endroit où est actuellement le joueur

        if (this.listQuetes.size() > 0){
            // Cas où le joueur a fait au moins une quête

            // On récupère la dernière quête faite par le joueur
            positionJoueur = this.listQuetes.get(listQuetes.size() - 1);
        }
        else {
            // Cas où le joueur n'a fait aucune quête, et qu'on ne connait pas sa position.
            // Alors il est au tout début du jeu en position x=0 et y=0.

            /* ATTENTION :  Nous créons une quête qui n'est pas une quête du jeu !
                            Elle représente la position initial du joueur au début du jeu.
                            Utiliser la classe quête est le choix le plus facile et rapide à faire.

              Cette fausse quête, numéro -1 est en position x=0 y=0, position de départ du joueur.
            */
            positionJoueur = new Quete("-1|(0, 0)|()|0|0|depart joueur CE NEST PAS UNE QUETE");

        }

        // Maintenant, nous savons où se trouve le joueur via l'objet positionJoueur de type Quête.

        for (Quete uneQuete : parScenario.getProvQuetes()){
            // On parcour les quêtes du scénario

            if (uneQuete.possibleDeFaireQuete(this) && (! ((! avecFinale) && ((uneQuete.getNumero() == 0))))){
                // True si : possible de faire quête et  que ce n'est pas la final.
                // Aussi, si on fait avec final, c'est forcément True.
                if (quetePlusProche != null) {
                    // Nous allons voir si la quête sélectionné n'est pas plus proche du joueur.

                    if (ratioPireQuete > uneQuete.ratioXPCritere(positionJoueur, choixUser)) {
                        // Si la quête sélectionné est pire
                        quetePlusProche = uneQuete;
                        ratioPireQuete = uneQuete.distanceDeplacement(positionJoueur);
                    }
                }
                else {
                    // Cas où on doit prendre la première quête disponible pour les comparaisons plus tard.

                    quetePlusProche = uneQuete;
                    ratioPireQuete = uneQuete.distanceDeplacement(positionJoueur);
                }

            }
        }



        return new Object[]{quetePlusProche, ratioPireQuete};

    }


    /**
     * Principe :
     *      Fusionne deux objets AvancerJoueur.
     *      On ajoute les quêtes de parAvancer dans this, et on essaye de calculer la distance par rapport
     *      à la dernière quête ajouté dans this.
     *
     * @param : AvancerJoueur parAvancer
     *      parAvancer [AVANCERJOUEUR] : Objet AvancerJoueur qui contient les quêtes qu'on va transférer dans this.
     */
    public void fusion(AvancerJoueur parAvancer){

        for (Quete uneQuete : parAvancer.listQuetes){
            // On parcourt les quêtes de parAvancer pour voir si on peut les transférer dans this

            if (listNoQuetes.contains(uneQuete.getNumero()) == false){
                // This ne contient pas déjà la quête, alors on l'ajoute.

                if (this.listQuetes.size() == 0){
                    // Si this n'a pas de quête, alors le joueur est en position x=0, y=0

                    /* ATTENTION :  Nous créons une quête qui n'est pas une quête du jeu !
                                    Elle représente la position initial du joueur au début du jeu.
                                    Utiliser la classe quête est le choix le plus facile et rapide à faire.

                                    Cette fausse quête, numéro -1 est en position x=0 y=0, position de départ du joueur.
                    */

                    Quete positionJoueur = new Quete("-1|(0, 0)|()|0|0|depart joueur CE NEST PAS UNE QUETE");

                    // On calcul la distance parcouru entre le joueur et la quête, et on ajoute la quête
                    int distanceJoueurUneQuete = positionJoueur.distanceDeplacement(uneQuete);
                    this.ajouteQuete(uneQuete, distanceJoueurUneQuete);
                }

                else {
                    // On calcul la distance parcouru entre le joueur et la quête, et on ajoute la quête
                    Quete positionJoueur = this.listQuetes.get(listQuetes.size() - 1); // dernière quête ajoutée
                    int distanceJoueurUneQuete = positionJoueur.distanceDeplacement(uneQuete);
                    this.ajouteQuete(uneQuete, distanceJoueurUneQuete);
                }

            }
        }
    }

    /**
     * Principe :
     *      Vérifie si this correspond aux attentes de l'utilisateur
     * Entrée :
     *      @param choixUser [CHOIXUTILISATEUR] : Ce que veux l'utilisateur comme résultat
     *      @param minimum            [BOOLEAN] : Si on prend en compte les if correspondant aux minumum (par défaut : true)
     * Sortie :
     *      @return true → this correspond aux attentes de l'utilisateur
     *      @return false → this ne correspond pas aux attentes de l'utilisateur
     */
    public boolean verifAttenteUser(ChoixUtilisateur choixUser, boolean minimum, Scenario scenar){
        if (this.temps < choixUser.getMinTempsAccorde() && (choixUser.getMinTempsAccorde() != -1 && minimum)) { return false;}
        if (this.temps > choixUser.getMaxTempsAccorde() && choixUser.getMaxTempsAccorde() != -1) { return false;}
        if (this.deplacements < choixUser.getMinDeplacementAccorde() && (choixUser.getMinDeplacementAccorde() != -1 && minimum)) { return false;}
        if (this.deplacements > choixUser.getMaxDeplacementAccorde() && choixUser.getMaxDeplacementAccorde() != -1) { return false;}
        if (this.listQuetes.size() < choixUser.getMinNbrQueteSouhaitees() && (choixUser.getMinNbrQueteSouhaitees() != -1 && minimum)) { return false;}
        if (this.listQuetes.size() > choixUser.getMaxNbrQueteSouhaitees() && choixUser.getMaxNbrQueteSouhaitees() != -1) { return false;}
        if (this.xp < choixUser.getMinXP() && (choixUser.getMinXP() != -1 && minimum)) { return false;}
        if (this.xp > choixUser.getMaxXP() && choixUser.getMaxXP() != -1) { return false;}

        // Cas à part pour le minimum. Mais pour pouvoir générer une solution, min devra être sur false. Donc on en profite.
        if (this.solutionEfficace != 1 && (choixUser.getTypeDeSolution() == 1 && minimum)){ return false;}

        if (scenar != null && scenar.getProvQuetes().size() != this.listQuetes.size() && choixUser.getTypeDeSolution() == 2){ return false;}
        return true;
    }
    public boolean verifAttenteUser(ChoixUtilisateur chhoixUser, boolean minimum){ return verifAttenteUser(chhoixUser, minimum, null);}
    public boolean verifAttenteUser(ChoixUtilisateur chhoixUser){ return verifAttenteUser(chhoixUser, true, null);}


    public void chercheEtAjouteQueteEntre(Scenario scenar, ChoixUtilisateur choixUser, int maxRecherche){
        Quete positionJoueur = new Quete("-1|(0, 0)|()|0|0|depart joueur CE NEST PAS UNE QUETE");
        Quete queteModele = null;
        Float ratioModele = Float.valueOf(-1);
        int posModele = -1;


        AvancerJoueur avancerPetitAPetit = new AvancerJoueur();

        for (int i = -1; i < maxRecherche; i++){
            // On parcours de prétrajet de this de la position initial du joueur à avant la quête finale (ou avant l'avant dernière, si on cherche une solution efficace)


            if (i > 0) {
                avancerPetitAPetit.ajouteQuete(positionJoueur, positionJoueur.distanceDeplacement(avancerPetitAPetit.listQuetes.get(avancerPetitAPetit.listQuetes.size() - 1)));
            }
            else if (i == 0) {
                avancerPetitAPetit.ajouteQuete(positionJoueur, positionJoueur.getPosition()[0] + positionJoueur.getPosition()[1]);
            }

            TreeMap<Float, ArrayList<Quete>> voisinage = positionJoueur.voisins(scenar, choixUser);
            NavigableSet<Float> lesRatios;
            if (choixUser.getObjectifDuree() == 2 || choixUser.getObjectifQuete() == 2 || choixUser.getObjectifDeplacement() == 2) { lesRatios = voisinage.descendingKeySet(); }
            else { lesRatios = (NavigableSet<Float>) voisinage.keySet(); }



            for (Float ratio : lesRatios){
                // On parcours les voisins par ratio

                Quete meilleurVoisin = null;

                for (Quete unVoisin : voisinage.get(ratio)){

                    if (unVoisin.possibleDeFaireQuete(avancerPetitAPetit) && (! this.listQuetes.contains(unVoisin))){



                        if (meilleurVoisin == null) {
                            meilleurVoisin = unVoisin;
                        }

                        boolean boolMeilleurDur = meilleurVoisin.getDuree() > unVoisin.getDuree();
                        boolean boolPireDur = meilleurVoisin.getDuree() < unVoisin.getDuree();
                        boolean boolPireDep = meilleurVoisin.distanceDeplacement(positionJoueur) < unVoisin.distanceDeplacement(positionJoueur);
                        boolean boolMeilleurDep = meilleurVoisin.distanceDeplacement(positionJoueur) > unVoisin.distanceDeplacement(positionJoueur);

                        if ((choixUser.getObjectifDuree() == 1) && boolMeilleurDur) {
                            meilleurVoisin = unVoisin;
                        } else if ((choixUser.getObjectifDuree() == 2) && boolPireDur) {
                            meilleurVoisin = unVoisin;
                        } else if ((choixUser.getObjectifDeplacement() == 2 || choixUser.getObjectifQuete() == 2) && boolPireDep) {
                            meilleurVoisin = unVoisin;
                        } else if ((choixUser.getObjectifDuree() == 1) && boolMeilleurDep){
                            // Meilleur déplacement
                            meilleurVoisin = unVoisin;
                        }
                    }

                }

                Float ratioAvecRatioQueteApres = (float) -1; // Il sera recalculé par la suite pour la comparaison

                boolean boolQueteProche = (ratioAvecRatioQueteApres < ratioModele || ratioModele == -1) && (choixUser.getObjectifDeplacement() == 1 || choixUser.getObjectifQuete() == 1);
                boolean boolQueteLoin = (ratioAvecRatioQueteApres > ratioModele || ratioModele == -1) && (choixUser.getObjectifDeplacement() == 2 || choixUser.getObjectifQuete() == 2);

                boolean boolQueteCourte = (ratioAvecRatioQueteApres < ratioModele || ratioModele == -1) && choixUser.getObjectifDuree() == 1;
                boolean boolQueteLongue = (ratioAvecRatioQueteApres > ratioModele || ratioModele == -1) && choixUser.getObjectifDuree() == 2;


                if (meilleurVoisin != null) {
                    ratioAvecRatioQueteApres = ratio + meilleurVoisin.ratioXPCritere(this.listQuetes.get(i + 1), choixUser);

                    if (boolQueteProche || boolQueteLoin || boolQueteCourte || boolQueteLongue){
                        queteModele = meilleurVoisin;
                        ratioModele = ratioAvecRatioQueteApres;
                        posModele = i;

                    }
                }

            }

            positionJoueur = this.listQuetes.get(i + 1);
        }



        if (! this.listQuetes.contains(queteModele) && queteModele != null) {
            this.ajouteQueteEntre(queteModele, posModele + 1, choixUser);

        }
        this.temps = Itineraire.verifTemps(this);
        this.deplacements = Itineraire.verifDeplacements(this);

    }



    public int compareTo(AvancerJoueur parAvancer){
        if (this.score < parAvancer.score){
            return -1;
        }

        else if (this.score > parAvancer.score){
            return 1;
        }

        else{
            return 0;
        }
    }

    public int getXp(){return xp;}
    public Quete getQuete(int idQuete){
        // On récupère une quête à partir de sa position dans la liste

        Quete laQuete = null;
        try{
            laQuete = listQuetes.get(idQuete);
        } catch (Exception e) {
            System.out.println("ERREUR >> L'identifiant de la quête n'est pas valide.");
            System.out.println("          Id min autorisé: 0, id max autorisé: " + (listQuetes.size() - 1) + ", votre id: " + idQuete);
            System.exit(-1);
        }

        return laQuete;
    }

        @Override
        public boolean equals(Object obj){
        // True si les caractéristiques de this et de l'objet AvancerJoueur en paramètre sont les mêmes, false sinon

        AvancerJoueur parAJ = (AvancerJoueur) obj;

        if (this.xp == parAJ.xp && this.listNoQuetes.equals(parAJ.listNoQuetes) && this.deplacements == parAJ.deplacements && this.temps == parAJ.temps){
            return true;
        }
        else {
            return false;
        }


    }

    @Override
    public int hashCode(){
        // Obligatoire pour que le HashSet fonctionne (accompagne equals)
        int hash = this.xp + this.deplacements + this.temps + this.listNoQuetes.size() + this.progression;
        return hash;
    }

    private void setSolutionEfficace(int parValeur){
        if (parValeur == 1 || parValeur == 2 || parValeur == 0){
            solutionEfficace = parValeur;
        }
    }

    public ArrayList<Integer> getListNoQuetes(){return listNoQuetes;}

    public ArrayList<Quete> getListQuetes() { return listQuetes; }

    public int getTemps(){return temps;}
    public int getDeplacements(){return deplacements;}

    public int getProgression(){return progression;}

    public boolean getEstEfficace(){return solutionEfficace==1;}

    public int getScore(){return score;}

    public String toString(){
        String str = "[AJ]: les quêtes : " + listNoQuetes + " /// total : " + listQuetes.size() + " /// Temps : " + temps + " /// Déplacements : " + deplacements + " /// Expériences : " + xp + " /// Faisable: " + Itineraire.verifPossible(this) + " /// Efficace : " + (solutionEfficace==1) + " --------- " + listQuetes + "\n";
        return str;
    }

}
