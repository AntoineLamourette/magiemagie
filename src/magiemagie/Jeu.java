/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiemagie;

import com.sun.glass.ui.SystemClipboard;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Formation
 */
public class Jeu {

    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private Joueur joueurEnCours;

    public Joueur getJoueurEnCours() {
        return joueurEnCours;
    }

    public void setJoueurEnCours(Joueur joueurEnCours) {
        this.joueurEnCours = joueurEnCours;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(ArrayList<Joueur> Joueurs) {
        this.joueurs = joueurs;
    }

    public void afficherMenuPrincipal() {

        //Saisie clavier
        boolean quitter = false;//importance de la positon sinon pas de renouvellement
        while (quitter == false) {
            //Afficher menu

            System.out.println("Menu ");
            System.out.println("-------");
            System.out.println("1. Nouveau joueur");
            System.out.println("2. Demarrer partie");
            System.out.println("3. Liste des joueurs");
            System.out.println("4. Quitter");

            String scanner = new Scanner(System.in).nextLine();
            switch (scanner) {
                case "1":
                    afficherMenuNouveauJoueur();
                    break;
                case "2":
                    demarrerPartie();
                    break;
                case "3":
                    System.out.println(joueurs);
                    break;
                case "4":
                    quitter = true;
                    break;
                default:
                    System.out.println("Erreur");
                    break;

            }

        }
    }

    public void afficherMenuNouveauJoueur() {
        //1.saisie du nom
        System.out.print("Saisir le nom du joueur:");
        Scanner s = new Scanner(System.in);
        String nomJoueur = s.nextLine();

        //2.crée un joueur et lui set son nom
        Joueur joueur = new Joueur();
        joueur.setNom(nomJoueur);

        //3.ajouts ds liste joueurs
        joueurs.add(joueur);

    }

    public void demarrerPartie() {
        //Distribuer 7 cartes par joueur
        for (Joueur joueur : joueurs) {
            for (int i = 0; i < 7; i++) {

                Carte carte = new Carte();
                //Distribue 7 cartes pour le joueur actuel
                Random random = new Random();
                int nb = random.nextInt(5);
                switch (nb) {
                    case 0:
                        carte.setType(Carte.TypeCarte.MANDRAGORE);
                        break;
                    case 1:
                        carte.setType(Carte.TypeCarte.AILE_DE_CHAUVE_SOURIS);
                        break;
                    case 2:
                        carte.setType(Carte.TypeCarte.LAPIS_LAZULI);
                        break;
                    case 3:
                        carte.setType(Carte.TypeCarte.BAVE_DE_CRAPAUD);
                        break;
                    case 4:
                        carte.setType(Carte.TypeCarte.CORNE_DE_LICORNE);
                        break;
                    default:
                        break;
                }

                joueur.getCartes().add(carte);//avec le ToString dans la class joueur
            }
        }
        //2.Donne la main au 1er joueur
        joueurEnCours = joueurs.get(0);

        //3 .Affiche menu action  
        menuAction();
        //4.Passer la main
    }

    public void menuAction() {
        //1.affiche menu
        boolean termine = false;
        do {
            System.out.println("A vous de jouer:" + joueurEnCours.getNom());
            System.out.println("Vos cartes:" + joueurEnCours.getCartes());
            System.out.println("1.lancer sort");
            System.out.println("2.passer son tour");
            System.out.print("Votre choix:");

            Scanner choix = new Scanner(System.in);// String choix1 = new Scanner (System.in).nextline();
            String choix2 = choix.nextLine();
            //2.lance sort ou passe tour

            switch (choix2) {

                case "1":
                    lancerSort();
                    termine = true;
                    break;
                case "2":
                    passerSonTour();
                    termine = true;
                    break;
                default:
                    System.out.println("essaie encore");
                    break;
            }
        } while (termine == false);
    }

    public void lancerSort() {
//1.Afffiche les sorts possibles pour joueurEnCours
        boolean termine = false;
        do {
            Carte carteCorneLicorne = new Carte();
            Carte carteBaveCrapaud = new Carte();
            Carte carteMandragore = new Carte();
            Carte carteAilesCSS = new Carte();
            Carte carteLapisLazu = new Carte();

            carteCorneLicorne.setType(Carte.TypeCarte.CORNE_DE_LICORNE);
            carteBaveCrapaud.setType(Carte.TypeCarte.BAVE_DE_CRAPAUD);
            carteMandragore.setType(Carte.TypeCarte.MANDRAGORE);
            carteAilesCSS.setType(Carte.TypeCarte.AILE_DE_CHAUVE_SOURIS);
            carteLapisLazu.setType(Carte.TypeCarte.LAPIS_LAZULI);

            if (joueurEnCours.getCartes().contains(carteCorneLicorne)
                    && joueurEnCours.getCartes().contains(carteBaveCrapaud)) {
                System.out.println("1 Invisibilite");
            }

            if (joueurEnCours.getCartes().contains(carteCorneLicorne)
                    && joueurEnCours.getCartes().contains(carteMandragore)) {
                System.out.println("2 Philtre d'amour");
            }

            if (joueurEnCours.getCartes().contains(carteBaveCrapaud)
                    && joueurEnCours.getCartes().contains(carteLapisLazu)) {
                System.out.println("3 Hypnose");
            }

            if (joueurEnCours.getCartes().contains(carteLapisLazu)
                    && joueurEnCours.getCartes().contains(carteAilesCSS)) {
                System.out.println("4 Divination");
            }

            if (joueurEnCours.getCartes().contains(carteMandragore)
                    && joueurEnCours.getCartes().contains(carteAilesCSS)) {
                System.out.println("5 Sommeil profond");
            }

            //2.Saisie sort à lancer  
            Scanner choix = new Scanner(System.in);
            String choix1 = choix.nextLine();

            switch (choix1) {
                case "1":
                    sortInvisibilite();
                    termine = true;

                    break;

                case "2":
                    sortPhiltreAmour();
                    termine = true;

                    break;

                case "3":
                    sortHypnose();
                    termine = true;
                    break;
                    case "4":
                    sortDivination();
                    termine = true;
                    break;
                case "5":
                    sortSommeilProfond();
                    termine = true;
                    break;
                default:
                    System.out.println("Ce sort n'existe pas");
                    return;
            }
        } while (termine == false);   
        
     }           
                    
    public Carte carteAuHasard(){
        
        Carte.TypeCarte[] tabTypeCarte = Carte.TypeCarte.values();
        int indiceAleat = new Random().nextInt(tabTypeCarte.length);
        Carte carte = new Carte();
        carte.setType( tabTypeCarte[indiceAleat] );
        
        return carte;
    }
                    

            
    

//3   .Lancement du sort
//4.Supprime les 2 cartes ayant permis de lancer le sort
    public void passerSonTour() {
        Scanner scan = new Scanner(System.in);
       
        joueurEnCours.getCartes().add(carteAuHasard());
     }   
        
    public void passerlaMain(){
        
        
        Carte.TypeCarte[] n = Carte.TypeCarte.values();
        
        joueurEnCours = joueurs.get(0);
    }
    

    private void sortInvisibilite() {

        // Itere sur ts joueurs
        for (int i = 0; i < joueurs.size(); i++) {

            Joueur joueurCible = joueurs.get(i);
            
            // 1. Si le joueur possède au - 1 carte ALORS
            if (!joueurCible.getCartes().isEmpty()) {
                // 1. Get sa carte 0
                Carte carteAVoler = joueurCible.getCartes().get(0);
                
                // 2 Se la add à nous
                joueurEnCours.getCartes().add( carteAVoler );
                // 3 La remove à lui
                joueurCible.getCartes().remove(carteAVoler);
            }
        }

    }

    private void sortPhiltreAmour() {
//        Scanner scan = new Scanner(System.in);
//        joueurCible = j
//        for (int i = 0; i < joueurs.size();){
//            Joueur joueurCible = joueurs.get(i);
//            
//            if (!joueurCible.getCartes().isEmpty()){
//                
//                Carte carteAVoler = joueurCible.getCartes().get(i);
//                
//                joueurEnCours.getCartes().add(carteAVoler);
//                
//                joueurCible.getCartes().remove(carteAVoler);
            }
        
    

    private void sortHypnose() {

    }

    private void sortDivination() {

    }

    private void sortSommeilProfond() {
        passerSonTour();
        passerSonTour();
    }

}
