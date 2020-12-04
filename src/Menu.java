import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    //TODO faire chargement delta save txt et phi
    // TODO signtures fonction commentaring
    //TODO afficher abrbre et les 3 menus bien
    //TODO expliquer eqm dans le meu, faire saut a la ligne pour que ce soit propre

    public static final String RED   = "\033[0;31m"; //Color red
    public static final String GREEN = "\033[0;32m"; //Color green
    public static final String RESET = "\033[0m";  //Reset color

    private final ArrayList<String> listMenu;
    private boolean compressMenu, saveMenu, comparator, isCompress;
    private QuadTree tree;

    //Constructor
    public Menu() {

        this.compressMenu = false;
        this.saveMenu     = false;
        this.isCompress   = false;
        this.comparator   = false;

        this.listMenu     = new ArrayList<>();

        this.tree         = null;

    }

    //---------------------------------------- INTERACTIVE MODE

    /** @role
     *
     * @throws IOException
     */
    public void startProgramme() throws IOException {

        createMenu();
        if (!this.compressMenu && !this.saveMenu) {
            try {
                System.out.println("Bonjour, bienvenue dans notre outil de compression d'image !\n" +
                        "→ Nous vous invitons à charger une image (indiquez le nom du fichier ou chemin d'accès du fichier) en mémoire pour acceder au menu.\nNous vous souhaitons une bonne découverte !\n");

                Scanner scan = new Scanner(System.in);//we scan the user's keyboard input
                String imagePath = scan.next();

                //TODO préciser dans le readMe soit indiquer chemin (mettre exemple) soit juste le nom de l'image si elle se trouve dans pngs
                this.tree = new QuadTree(imagePath);
                this.compressMenu = true;
                startProgramme();
            } catch (IOException e) {
                System.out.println(RED + "/!\\ ERREUR : Le nom du fichier est incorrect ! " + RESET);
                programmeError();
                throw e;
            }
        } else {
            displayMenu();
            choiceOption();
        }
    }

    /** @role   This function enables
     *
     *  @throws IOException
     */
    private void programmeError() throws IOException {
        try {
            System.out.println("\n→ Nous vous invitons à re-donner le nom de votre image pour acceder au menu.");
            Scanner scan = new Scanner(System.in);
            String imagePath = scan.next();
            this.tree = new QuadTree(imagePath);

        } catch (IOException e) {
            System.out.println(RED + "/!\\ ERREUR : Le nom du fichier est incorrect ! " + RESET);
            programmeError();
            throw e;
        }
            this.compressMenu = true;
            startProgramme();
    }


    /** @role
     *
     *  @throws IOException
     */
    private void createMenu() throws IOException {

        if (this.compressMenu && !this.saveMenu && !this.comparator) {
            listMenu.add("\n************************************* MENU *************************************\n\n");
            listMenu.add("\t 0. Quitter le programme.\n");
            listMenu.add("\t 1. Recharger une image PNG en mémoire dans un quadtree.\n");
            listMenu.add("\t 2. Appliquer une compression Delta pour un ∆ donné.\n");
            listMenu.add("\t 3. Appliquer une compression Phi pour un Φ donné.\n");
            listMenu.add("\n\t A. Afficher à l'écran l'arbre.\n");

        } else if (this.compressMenu && this.saveMenu && !this.comparator && this.listMenu.size() < 8 ) {
            listMenu.remove(5);
            listMenu.add("\t 4. Sauvegarder le quadtree dans un fichier PNG.\n");
            listMenu.add("\t 5. Sauvegarder la représentation textuelle du quadtree dans un fichier TXT.\n");
            listMenu.add("\n\t A. Afficher à l'écran l'arbre.\n");

        } else if (this.compressMenu && this.saveMenu && this.comparator && this.listMenu.size() < 9) {
            listMenu.remove(7);
            listMenu.add("\t 6. Donner les mesures comparative de deux fichiers images PNG.\n\n");
            listMenu.add("\t A. Afficher à l'écran l'arbre.\n");
        }
    }

    /** @role
     *
     * @param
     */
    private void displayMenu() {
        for (String option : this.listMenu) {
            System.out.print(option);
        }
    }




    /** @role
     *
     * @throws IOException
     */
    private void choiceOption() throws IOException {
        System.out.println("\n→ A présent, choissisez quelle option vous intérresse.");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        switch (choice) {
            case "0":
                exit();
                break;
            case "1":
                this.compressMenu = false;
                this.saveMenu = false;
                startProgramme();
                break;
            case "2":
                this.saveMenu = true;
                compressDelta();
                break;
            case "3":
                this.saveMenu = true;
                compressPhi();
                break;
            case "4":
                savePNG();
                this.comparator = true;
                break;
            case "5":
                saveTXT();
                break;
            case "6":
                displayEQM(this.tree);
                break;
            case "A":
                System.out.println("\nArbre : \n");
                System.out.println(this.tree.toString());
                break;
            default:
                System.out.println(RED + "/!\\ ERREUR : Entrée invalide, veuillez refaire votre choix." + RESET);
                choiceOption();
        }
        startProgramme();
    }


    /** @role
     *
     */
    private void exit() {
        System.out.println(GREEN + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
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
    public void compressDelta() throws InputMismatchException, IOException {
        Scanner scan = new Scanner(System.in);
        int delta;

        System.out.println("Choissisez votre delta (un entier entre 0 et 255) pour la compression : ");

        try {
            delta = scan.nextInt();
            while (delta < 0 || delta > 255) {
                System.out.println(RED + "/!\\ ERREUR : Entrée invalide, veuillez re-donner un delta." + RESET);
                delta = scan.nextInt();
            }

            this.tree.compressDelta(delta);//We applied compression

        } catch (InputMismatchException eDelta) {
            System.out.println(RED + "/!\\ ERREUR : Il faut un entier entre 0 et 255 ! " + RESET);
            compressDelta();
            throw eDelta;
        }
        startProgramme();
    }


    /** @role
     *
     */
    public void compressPhi() throws InputMismatchException, IOException {
        Scanner scan = new Scanner(System.in);
        int phi;

        System.out.println("Choissisez votre phi (un entier strictement supérieur à 0) pour la compression : ");

        try {
            phi = scan.nextInt();
            while (phi < 0 || phi > 255) {
                System.out.println(RED + "/!\\ ERREUR : Entrée invalide, veuillez re-donner un phi." + RESET);
                phi = scan.nextInt();
            }

            this.tree.compressPhi(phi);//We applied compression

        } catch (InputMismatchException ePhi) {
            System.out.println(RED + "/!\\ ERREUR : Il faut un entier strictement supérieur à 0 ! " + RESET);
            compressPhi();
            throw ePhi;
        }
        startProgramme();
    }


    /** @role
     *
     *  @throws IOException
     */
    private void savePNG() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choissisez votre nom de fichier PNG ou le chemin ou vous voulez enregistrer : ");
        String namePNG = scan.next();
        this.tree.savePNG(namePNG);
    }

    /** @role
     *
     *  @throws IOException
     */
    private void saveTXT() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choissisez votre nom de fichier TXT : ");
        String nameTXT = scan.next();
        this.tree.saveTXT("SaveTXT/" + nameTXT + ".txt");
        startProgramme();
    }



    //---------------------------------------- NON INTERACTIVE MODE
    /** @role
     * @param args
     * @throws IOException
     *
     */
    public void noInteractiveProgramme(String[] args) throws IOException {
        createDeltaFile(Integer.parseInt(args[1]), args[0], args[0]);
        createPhiFile(Integer.parseInt(args[2]), args[0], args[0]);
    }

    //---------------------------------------- CREATE FILE

    /** @role
     *  @param delta
     *  @param name
     *  @param imagePath
     *  @throws IOException
     */
    private void createDeltaFile(int delta, String name, String imagePath) throws IOException {
        QuadTree deltaTree = new QuadTree(imagePath);
        deltaTree.compressDelta(delta);

        deltaTree.saveTXT("SaveTXT/" + name + "-delta" + delta + ".txt");
        deltaTree.savePNG("SavePNG/" + name + "-delta" + delta + ".png");

        System.out.print("\n Comparaison fichiers Delta : ");
        displayEQM(deltaTree);
    }

    /**
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
     * @param tree
     * @throws IOException
     * @role :
     */
    public void displayEQM(QuadTree tree) throws IOException {
        tree.EQM();
    }

    /**
     * @param imagePath
     * @return
     * @throws IOException
     * @role :
     */
    public ImagePNG loadImagePNG(String imagePath) throws IOException {
        if (imagePath.contains("/")) {
            return new ImagePNG(imagePath);
        } else if (imagePath.contains(".png")) {
            return new ImagePNG("pngs/" + imagePath);
        } else {
            return new ImagePNG("pngs/" + imagePath + ".png");
        }
    }


}//END
