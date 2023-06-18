/**
 * La classe Itineraire représente des itinéraires sous la forme d'une ArrayList, du nombre d'expériences, de temps et de déplacements ainsi qu'un score.
 *
 * Un objet Itinéraire comporte un trajet finalisé.
 * C'est à dire un trajet mis au propre, avec une quête de début et une quête de fin le tout ordonnancés dans un ordre logique.
*/

package modele;

import java.util.ArrayList;

/**
 * Cette classe sert à trouver les bons itinéraires afin de récupérer des trajets valides
 */
public class Itineraire implements Comparable<Itineraire> {
    private ArrayList<Quete> lesQuetes;
    private ArrayList<Integer> lesNoQuetes;
    private int temps;
    private int deplacements;
    private int xp;
    private int score;


    /**
     * Principe :
     *      Finalise un trajet. Il ne pourra plus être modifié par la suite.
     * Entrée :
     *      @param avancerJoueur [AVANCERJOUEUR] : Objet contenant le trajet que nous allons finaliser
     *      @param parScore                [INT] : Score de l'itinéraire dans sa catégorie
     */
    public Itineraire(AvancerJoueur avancerJoueur, int parScore){
        try{

            if (avancerJoueur.getListNoQuetes().size() < 1) {
                Exception e = new Exception("Le trajet ne possède pas de quête.");
                throw e;
            }

            if (avancerJoueur.getQuete(avancerJoueur.getListNoQuetes().size() - 1).getNumero() != 0) {
                Exception e = new Exception("""
                Le trajet ne termine pas par la quête finale.
                                     Peut-être que le joueur ne le fait pas ?
                """);
                throw e;
            }

            if (avancerJoueur.getXp() < avancerJoueur.getQuete(avancerJoueur.getListNoQuetes().size() - 1).getExperience()) {
                Exception e = new Exception("Le joueur n'a pas assez d'expérience pour faire la quête finale. " + avancerJoueur.getXp() + " " + avancerJoueur.getQuete(avancerJoueur.getListNoQuetes().size() - 1).getExperience());
                throw e;
            }

            if (! Itineraire.verifPossible(avancerJoueur)) {
                Exception e = new Exception("""
                Les préconditions d'une ou plusieurs quêtes n'ont pas été validés.
                """);
                throw e;
            }

            if (! Itineraire.verifPossible(avancerJoueur)) {
                Exception e = new Exception("Une ou plusieurs quêtes ne peuvent pas être fait car certaines quêtes n'ont as été faite.");
                throw e;
            }
        }
        catch (Exception e){
            System.out.println("ERREUR >> Impossible de finaliser ce chemin.");
            System.out.println(e);
            System.exit(-1);
        }


        lesQuetes = (ArrayList<Quete>) avancerJoueur.getListQuetes().clone();
        lesNoQuetes = (ArrayList<Integer>) avancerJoueur.getListNoQuetes().clone();

        temps = Itineraire.verifTemps(avancerJoueur);
        deplacements = Itineraire.verifDeplacements(avancerJoueur);
        xp = avancerJoueur.getXp();
        score = parScore;

    }

    /**
     * Principe :
     *      Recalcule le temps de l'itinéraire, pour être sur que l'itinéraire ne comporte aucune erreur.
     * Entrée :
     *      @param avancerJ      [AVANCERJOUEUR] : Objet qu'on veut finaliser
     * Sortie :
     *      @return vraiTemps              [INT] : Temps recalculé
     */
    public static int verifTemps(AvancerJoueur avancerJ){

        int vraiTemps = 0;

        // Avant de faire la première quête, le joueur est à la case de départ, soit en position 0,0
        Quete quetePrecedente = new Quete("-1|(0, 0)|()|0|0|depart joueur CE NEST PAS UNE QUETE");

        for (Quete uneQuete : avancerJ.getListQuetes()){
            vraiTemps += uneQuete.distanceDeplacement(quetePrecedente);
            vraiTemps += uneQuete.getDuree();

            quetePrecedente = uneQuete;
        }



        return vraiTemps;
    }

    /**
     * Principe :
     *      Recalcule la distance total de déplacement de l'itinéraire.
     * Entrée :
     *      @param avancerJ             [AVANCERJOUEUR] : Objet qu'on veut finaliser
     * Sortie :
     *      @return vraiDeplacements              [INT] : Distance total recalculé
     */
    public static int verifDeplacements(AvancerJoueur avancerJ){

        int vraiDeplacements = 0;

        // Avant de faire la première quête, le joueur est à la case de départ, soit en position 0,0
        Quete quetePrecedente = new Quete("-1|(0, 0)|()|0|0|depart joueur CE NEST PAS UNE QUETE");

        for (Quete uneQuete : avancerJ.getListQuetes()){
            vraiDeplacements += uneQuete.distanceDeplacement(quetePrecedente);

            quetePrecedente = uneQuete;
        }



        return vraiDeplacements;
    }

    /**
     * Principe :
     *      Vérifie si le trajet est faisable en respectant les préconditions et l'expérience pour la quête finale.
     * Entrée :
     *      @param parAvancerJoueur [AVANCERJOUEUR] : Objet qu'on veut finaliser
     * Sortie :
     *      @return faisable              [BOOLEAN] : True si le trajet est faisable, False sinon
     */
    public static boolean verifPossible(AvancerJoueur parAvancerJoueur){
        // avancer permet de refaire le parcours petit à petit
        AvancerJoueur avancer = new AvancerJoueur();

        // On regarde si c'est faisable dans l'ordre du début du trajet à sa fin, de quête par quête
        for (Quete uneQuete : parAvancerJoueur.getListQuetes()){
            if (! uneQuete.possibleDeFaireQuete(avancer)){
                // S'il n'est pas possible de faire à ce moment là cette quête, le trajet n'est pas faisable.
                // Raisons: problèmes avec les précondition OU pas assez d'expérience à la fin
                return false;
            }

            avancer.ajouteQuete(uneQuete, 1);
        }
        return true;
    }

    /**
     * Principe :
     *      Vérifie si le trajet est faisable en respectant les préconditions. NE REGARDE PAS LA QUÊTE FINALE !
     * Entrée :
     *      @param parAvancerJoueur [AVANCERJOUEUR] : Objet qu'on veut finaliser
     * Sortie :
     *      @return faisable              [BOOLEAN] : True si le trajet est faisable, False sinon
     */
    public static boolean verifPossibleHorsFinale(AvancerJoueur parAvancerJoueur){
        // avancer permet de refaire le parcours petit à petit
        AvancerJoueur avancer = new AvancerJoueur();

        // On regarde si c'est faisable dans l'ordre du début du trajet à sa fin, de quête par quête
        for (int i=0; i < parAvancerJoueur.getListNoQuetes().size() - 1; i++){
            Quete uneQuete = parAvancerJoueur.getListQuetes().get(i);
            if (! uneQuete.possibleDeFaireQuete(avancer)){
                // S'il n'est pas possible de faire à ce moment là cette quête, le trajet n'est pas faisable.
                // Raisons: problèmes avec les précondition OU pas assez d'expérience à la fin
                return false;
            }

            avancer.ajouteQuete(uneQuete, 1);
        }
        return true;
    }

    public void reDefinirScore(ChoixUtilisateur choixUser){
        score = 0;
        if (choixUser.getObjectifDeplacement() == 1 || choixUser.getObjectifDeplacement() == 2){ score += deplacements; }
        if (choixUser.getObjectifDuree() == 1 || choixUser.getObjectifDuree() == 2){ score += temps; }

        if (choixUser.getObjectifQuete() == 1){ score -= lesQuetes.size(); }
        else if (choixUser.getObjectifQuete() == 2){ score += lesQuetes.size(); }
    }

    public String toString(){
        String str = "[ITINERAIRE - " + score +"]: les quêtes : " + lesNoQuetes + " /// total : " + lesQuetes.size() + " /// Temps : " + temps + " /// Déplacements : " + deplacements + " /// Expériences : " + xp + "\n";
        return str;
    }

    public int getScore(){
        return score;
    }

    public ArrayList<Integer> getLesNoQuetes(){
        return (ArrayList<Integer>) lesNoQuetes.clone();
    }

    public ArrayList<Quete>  getLesQuetes() {
        return (ArrayList<Quete>) lesQuetes.clone();
    }
    public int getTemps(){
        return temps;
    }

    public int getDeplacements(){
        return deplacements;
    }

    public int getXP(){
        return xp;
    }

    public int getNbQuetes(){
        return lesQuetes.size();
    }

    public int compareTo(Itineraire parItineraire) {
        if (this.score < parItineraire.score) {
            return -1;
        } else if (this.score > parItineraire.score) {
            return 1;
        } else {

            if ((this.deplacements + this.temps) < (parItineraire.deplacements + parItineraire.temps)){
                return -1;
            }
            else if ((this.deplacements + this.temps) > (parItineraire.deplacements + parItineraire.temps)){
                return 1;
            }
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        // True si les caractéristiques de this et de l'objet Itineraire en paramètre sont les mêmes, false sinon

        Itineraire parIti = (Itineraire) obj;

        if (this.xp == parIti.xp && this.lesNoQuetes.equals(parIti.lesNoQuetes) && this.deplacements == parIti.deplacements && this.temps == parIti.temps) {
            return true;
        } else {
            return false;
        }
    }

}
