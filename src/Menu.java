import sun.misc.Cleaner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private final ArrayList<String> listMenu;

    private boolean compressMenu, saveMenu;

    private QuadTree tree;

    /**
     *
     */
    public Menu() {

        this.compressMenu = false;
        this.saveMenu = false;

        this.listMenu = new ArrayList<>();

        this.tree = null;

    }

    /**
     *
     * @throws IOException
     */
    public void startProgramme() throws IOException {

        createMenu();
        if (!this.compressMenu && !this.saveMenu) {
            try {
                System.out.println("Bienvenue dans notre outil de compression d'image !\n" +
                        "→ Nous vous invitons à charger une image en mémoire pour acceder au menu. Nous vous souhaitons une bonne découverte ! ");
                Scanner scan = new Scanner(System.in);
                String imagePath = scan.next();

                //TODO préciser dans le readMe soit indiquer chemin (mettre exemple) soit juste le nom de l'image si elle se trouve dans pngs


                this.tree = new QuadTree(imagePath);

                System.out.println("lllllllllllll");
                this.compressMenu = true;
                startProgramme();
            } catch(Exception e ) {
                System.out.println("Le nom du fichier est incorrect ! ");
                throw e;
            } finally {
                programmeError();
            }
        } else {
            displayMenu();
            choiceOption();
        }
    }

    /**
     *
     * @throws IOException
     */
    private void programmeError() throws IOException {
        try {
            System.out.println("→ Nous vous invitons à recharger votre en mémoire pour acceder au menu. Nous vous souhaitons une bonne découverte ! ");
            Scanner scan = new Scanner(System.in);
            String imagePath = scan.next();
            this.tree = new QuadTree(imagePath);
        } catch(Exception e ) {
            System.out.println("Le nom du fichier est incorrect ! ");
            programmeError();
            throw e;
        } finally {
            this.compressMenu = true;
            startProgramme();
        }


    }


    //TODO voir si on eut sans appuyer sur la touche entrer selectionner une option !!

    /**
     *
     * @throws IOException
     */
    private void choiceOption() throws IOException {
        System.out.println("→ A présent, choissisez quelle option vous intérresse.");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();// TODO verifier qu'on entre bien un int

        switch(choice){
            case 0:
                exit();
                break;
            case 1:
                this.compressMenu = false;
                this.saveMenu = false;
                startProgramme();
                break;
            case 2:
                this.saveMenu = true;
                compressDelta();
                break;
            case 3:
                this.saveMenu = true;
                compressPhi();
                break;
            case 4:
                savePNG();
                break;
            case 5:
                saveTXT();
                break;
            case 6:
                displayEQM(this.tree);
                break;
            default:
                System.out.println("ERREUR : Entrée invalide, veuillez refaire votre choix.");
                choiceOption();
        }
        startProgramme();
    }

    /**
     *
     */
    private void exit() {
        System.out.println( "\033[1;32m" + "\nMerci, au revoir et à la prochaîne !\n" + "\u001B[0m");
        System.exit(0);

    }

    /*public static void loadImage(boolean b) throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Veuillez entrer le nom ou le chemin de votre fichier à sauvegarder.");
        ///TODO EXPCETION VERIF BON NOM DE FICHIER +
        ///TODO VOIR SI ON APPELLE CETTE FONCTION DANS LE MODE NN INTERACTIF

        try {
            String fileName = scan.next();

            tree = new QuadTree(fileName);

            System.out.println("\nAFFICHAGE ARBRE DE LA PHOTO : " + fileName + "\n" );
            b = true;
            System.out.println(tree.toString());

        }
        catch(Exception e ){
            System.out.println("Le nom du fichier est incorrect ! ");

            throw e;
        } finally {
            if(b == false ){
                loadImage(b);
            }
        }
    }*/

    /**
     *
     */
    public void compressDelta() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choissisez votre delta pour la compression : ");
        int delta = scan.nextInt();
        this.tree.compressDelta(delta);
    }

    /**
     *
     */
    public void compressPhi() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choissisez votre phi pour la compression : ");
        int phi = scan.nextInt();
        this.tree.compressPhi(phi);
    }

    /**
     *
     * @throws IOException
     */
    private void savePNG() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choissisez votre nom de fichier PNG : ");
        String namePNG = scan.next();
        this.tree.savePNG("SavePNG/" + namePNG + ".png");
    }

    /**
     *
     * @throws IOException
     */
    private void saveTXT() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choissisez votre nom de fichier TXT : ");
        String nameTXT = scan.next();
        this.tree.saveTXT("SaveTXT/" + nameTXT + ".txt");
    }

    /**
     *
     * @throws IOException
     */
    private void createMenu() throws IOException {

        if (this.compressMenu && !this.saveMenu) {
            listMenu.add("\n************************************* MENU *************************************\n\n");
            listMenu.add("\t 0. Quitter le programme.\n");
            listMenu.add("\t 1. Recharger une image PNG en mémoire dans un quadtree.\n");
            listMenu.add("\t 2. Appliquer une compression Delta pour un ∆ donné.\n");
            listMenu.add("\t 3. Appliquer une compression Phi pour un Φ donné.\n");
        } else if (this.compressMenu && this.saveMenu && this.listMenu.size() < 7) {
            listMenu.add("\t 4. Sauvegarder le quadtree dans un fichier PNG.\n");
            listMenu.add("\t 5. Sauvegarder la représentation textuelle du quadtree dans un fichier TXT.\n");
            listMenu.add("\t 6. Donner les mesures comparative de deux fichiers images PNG.\n\n");
        }
    }

    /**
     *
     */
    private void displayMenu() {
        for (String option : this.listMenu) {
            System.out.print(option);
        }
    }


    /**
     *
     * @param args
     * @throws IOException
     */
    public void noInteractiveProgramme(String[] args) throws IOException {
        createDeltaFile(Integer.parseInt(args[1]), args[0], args[0]);
        createPhiFile(Integer.parseInt(args[2]), args[0], args[0]);


    }

    //---------------------------------------- CREATE FILE
    ///TODO BLOQUER DELTA ENTRE 0 ET 254 !!!!!!!!!!!!!!!!!!!!!!!!!

    /**
     *
     * @param delta
     * @param name
     * @param imagePath
     * @throws IOException
     */
    private void createDeltaFile(int delta, String name, String imagePath) throws IOException {
        QuadTree deltaTree = new QuadTree(imagePath);
        deltaTree.compressDelta(delta);

        deltaTree.saveTXT("SaveTXT/" + name + "-delta" + delta + ".txt");
        deltaTree.savePNG("SavePNG/" + name + "-delta" + delta + ".png");

        System.out.print("\n Comparaison fichiers Delta : ");
        displayEQM(deltaTree);


    }

    ///TODO PHI > 0 !!!!!!!!!!!!!!!!!!!!!!!!!

    /**
     *
     * @param phi
     * @param name
     * @param imagePath
     * @throws IOException
     */
    private void createPhiFile(int phi, String name, String imagePath) throws IOException {
        QuadTree phiTree = new QuadTree(imagePath);
        phiTree.compressPhi(phi);

        phiTree.saveTXT("SaveTXT/" + name + "-phi" + phi + ".txt");
        phiTree.savePNG("SavePNG/" + name + "-phi" + phi + ".png");

        System.out.print("\n Comparaison fichiers Phi : ");
        displayEQM(phiTree);

    }

    /**
     *
     * @param tree
     * @throws IOException
     */
    public void displayEQM(QuadTree tree) throws IOException {
        tree.EQM();
    }

    /**
     *
     * @param imagePath
     * @return
     * @throws IOException
     */
    public static ImagePNG loadImagePNG(String imagePath) throws IOException {
        if (imagePath.contains("/")) {
            return new ImagePNG(imagePath);
        } else if (imagePath.contains(".png")){
            return new ImagePNG("pngs/" + imagePath);
        } else {
            return new ImagePNG("pngs/" + imagePath + ".png");
        }
    }
}
