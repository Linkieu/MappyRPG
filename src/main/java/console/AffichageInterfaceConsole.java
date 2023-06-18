package console;

import modele.ChoixUtilisateur;

import java.util.Scanner;

public class AffichageInterfaceConsole {

    /**
     * Principe :
     *      Affiche les possibilités à l'utilisateur et demande à l'utilisateur ses choix.
     *
     *      Renvoie un objet de type CHOIXUTILISATEUR comportant les différentes réponses.
     *      Les réponses sont représentées par un int.
     *
     *      Note
     *      Tâche N
     *
     *  Entrée :
     *      Aucune
     *
     * @return ChoixUtilisateur : Objet de type CHOIXUTILISATEUR
     *
     */
    public static ChoixUtilisateur interfaceChoixUtilisateur() {
        // Déclaration des objectifs visés
        String quetePlusProche = "";
        String objectifDuree = "";
        String objectifQuete = "";
        String objectifDeplacement = "";
        String minNbrQueteSouhaitees = "";
        String maxNbrQueteSouhaitees = "";
        String minDeplacementAccorde = "";
        String maxDeplacementAccorde = "";
        String minTempsAccorde = "";
        String maxTempsAccorde = "";

        // Création afin de récupérer la saisie de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Boucles pour forcer l'utilisateur à répondre la bonne chose
        // quetePlusProche
        while (!quetePlusProche.equals("oui") && !quetePlusProche.equals("non")){
            System.out.println("Voulez-vous la quete la plus proche a chaque instant ? (oui/non)\n ");
            quetePlusProche = scanner.next();
        }
        if (quetePlusProche.equals("oui"))
            return new ChoixUtilisateur(true, 1);

        // objectifDuree
        while (!objectifDuree.equals("non") && !objectifDuree.equals("1") && !objectifDuree.equals("2")) {
            System.out.println("Prenez vous en compte la duree ? (non, 1 pour resultats optimises, 2 resultats non optimises)\n ");
            objectifDuree = scanner.next();
        }
        if (objectifDuree.equals("non")) {
            objectifDuree = "0";
            minTempsAccorde = "-1";
            maxTempsAccorde = "-1";
        }

        // objectifQuete
        while (!objectifQuete.equals("non") && !objectifQuete.equals("1") && !objectifQuete.equals("2")) {
            System.out.println("Prenez vous en compte le nombre de quetes a realiser ? (non, 1 pour resultats optimises, 2 resultats non optimises)\n ");
            objectifQuete = scanner.next();
        }
        if (objectifQuete.equals("non")){
            objectifQuete = "0";
            minNbrQueteSouhaitees = "-1";
            maxNbrQueteSouhaitees = "-1";
        }


        // objectifDeplacement
        while (!objectifDeplacement.equals("non") && !objectifDeplacement.equals("1") && !objectifDeplacement.equals("2")) {
            System.out.println("Prenez vous en compte le deplacement ? (non, 1 pour resultats optimises, 2 resultats non optimises)\n ");
            objectifDeplacement = scanner.next();
        }
        if (objectifDeplacement.equals("non")){
            objectifDeplacement = "0";
            minDeplacementAccorde = "-1";
            maxDeplacementAccorde = "-1";
        }


        // minNbrQueteSouhaitees
        if (!objectifQuete.equals("0")){ // Si c'est différent de 0 on pose la question
            boolean saisieValideNbrQuete = false; // Définition d'un booléen afin de gérer la sortie
            while (!saisieValideNbrQuete) {
                System.out.println("Combien de quetes minimum prenez-vous en compte ? (entier positif)\n");
                minNbrQueteSouhaitees = scanner.next();

                try {
                    int nombre = Integer.parseInt(minNbrQueteSouhaitees);
                    if (nombre >= 0) {
                        saisieValideNbrQuete = true; // La saisie est valide, on sort de la boucle
                        minNbrQueteSouhaitees = String.valueOf(nombre);
                    }
                    else {
                        System.out.println("Veuillez entrer un entier positif");
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un entier positif");
                }
            }
            // maxNbrQueteSouhaitees

            boolean saisieValideMaxNbrQuete = false; // Définition d'un booléen afin de gérer la sortie

            while (!saisieValideMaxNbrQuete) {
                System.out.println("Combien de quetes maximum prenez-vous en compte ? (entier positif)\n");
                maxNbrQueteSouhaitees = scanner.next();

                try {
                    int nombre = Integer.parseInt(maxNbrQueteSouhaitees);
                    int valeurMin = Integer.parseInt(minNbrQueteSouhaitees);
                    if (nombre >= valeurMin){
                        saisieValideMaxNbrQuete = true;
                        maxNbrQueteSouhaitees = String.valueOf(nombre);
                    }
                    else {
                        System.out.println("Veuillez entrer un entier positif et supérieur au minimum");
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un entier positif");
                }
            }
        }

        // minDeplacementAccorde
        if (!objectifDeplacement.equals("0")) { // Si c'est différent de 0 on pose la question
            boolean saisieValideDep = false; // Définition d'un booléen afin de gérer la sortie
            while (!saisieValideDep) {
                System.out.println("Combien de déplacement minimum prenez-vous en compte ? (entier positif)\n");
                minDeplacementAccorde = scanner.next();

                try {
                    int nombre = Integer.parseInt(minDeplacementAccorde);
                    if (nombre >= 0) {
                        saisieValideDep = true; // La saisie est valide, on sort de la boucle
                        minDeplacementAccorde = String.valueOf(nombre);
                    } else {
                        System.out.println("Veuillez entrer un entier positif");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un entier positif");
                }
            }
            // maxDeplacementAccorde

            boolean saisieValideMaxNbrQuete = false; // Définition d'un booléen afin de gérer la sortie
            while (!saisieValideMaxNbrQuete) {
                System.out.println("Combien de déplacement maximum prenez-vous en compte ? (entier positif)\n");
                maxDeplacementAccorde = scanner.next();

                try {
                    int nombre = Integer.parseInt(maxDeplacementAccorde);
                    int valeurMin = Integer.parseInt(minDeplacementAccorde);
                    if (nombre >= valeurMin) {
                        saisieValideMaxNbrQuete = true; // La saisie est valide, on sort de la boucle
                        maxDeplacementAccorde = String.valueOf(nombre);
                    } else {
                        System.out.println("Veuillez entrer un entier positif et supérieur au minimum");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un entier positif");
                }
            }
        }

        // minTempsAccorde
        if (!objectifDuree.equals("0")) { // Si c'est différent de 0 on pose la question
            boolean saisieValidetemps = false; // Définition d'un booléen afin de gérer la sortie
            while (!saisieValidetemps) {
                System.out.println("Combien de temps minimum prenez-vous en compte ? (entier positif)\n");
                minTempsAccorde = scanner.next();

                try {
                    int nombre = Integer.parseInt(minTempsAccorde);
                    if (nombre >= 0) {
                        saisieValidetemps = true; // La saisie est valide, on sort de la boucle
                        minTempsAccorde = String.valueOf(nombre);
                    } else {
                        System.out.println("Veuillez entrer un entier positif");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un entier positif");
                }
            }
            // maxDeplacementAccorde
            boolean saisieValideMaxNbrQuete = false; // Définition d'un booléen afin de gérer la sortie
            while (!saisieValideMaxNbrQuete) {
                System.out.println("Combien de temps maximum prenez-vous en compte ? (entier positif)\n");
                maxTempsAccorde = scanner.next();

                try {
                    int nombre = Integer.parseInt(maxTempsAccorde);
                    int valeurMin = Integer.parseInt(minTempsAccorde);
                    if (nombre >= valeurMin) {
                        saisieValideMaxNbrQuete = true; // La saisie est valide, on sort de la boucle
                        maxTempsAccorde = String.valueOf(nombre);
                    } else {
                        System.out.println("Veuillez entrer un entier positif et supérieur au minimum");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un entier positif");
                }
            }
        }

        // System.out.println(objectifDuree + objectifQuete + objectifDeplacement + minNbrQueteSouhaitees + maxNbrQueteSouhaitees + minDeplacementAccorde + maxDeplacementAccorde + minTempsAccorde + maxTempsAccorde);
        return new ChoixUtilisateur(Integer.parseInt(objectifDuree), Integer.parseInt(objectifQuete), Integer.parseInt(objectifDeplacement), Integer.parseInt(minNbrQueteSouhaitees), Integer.parseInt(maxNbrQueteSouhaitees), Integer.parseInt(minDeplacementAccorde), Integer.parseInt(maxDeplacementAccorde), Integer.parseInt(minTempsAccorde), Integer.parseInt(maxTempsAccorde));
    }
}