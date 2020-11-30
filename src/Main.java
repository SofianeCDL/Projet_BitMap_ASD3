import java.io.IOException;
import java.util.Scanner;

public class Main {

    //---------------------------------------- TEXT DISPLAY
    private static String welcome() throws IOException {
        String str = ("Bienvenue dans notre outil de compression d'image !\n"
                + "→ Nous vous invitons à presser une touche du clavier pour acceder au menu. Nous vous souhaitons une bonne découverte ! ");

        System.out.println(str + "\n\n");
        return displayMenu();
    }


    private static String displayMenu(){
        Scanner scan = new Scanner(System.in);
        String str = scan.next();

        return ( "************************************* MENU *************************************\n\n"
                + "\t 1. Charger une image PNG en mémoire dans un quadtree.\n"
                + "\t 2. Appliquer une compression Delta pour un ∆ donné.\n"
                + "\t 3. Appliquer une compression Phi pour un Φ donné.\n"
                + "\t 4. Sauvegarder le quadtree dans un fichier PNG.\n"
                + "\t 5. Sauvegarder la représentation textuelle du quadtree dans un fichier TXT.\n"
                + "\t 6. Donner les mesures comparative de deux fichiers images PNG.\n\n"
                + "\t → A présent, choissisez quel option vous intérresse.");
    }

    //---------------------------------------- Interactive mode
    private static void choiceOption(){

        switch
    }
    //------------------------------------------------------------ MAIN
    public static void main(String[] args) throws Exception {

        //Variable
        QuadTree tree, deltaTree, PhiTree;
        boolean b = false;

        //Begin
        if ( args.length == 0 ) { //If we are in interactive mode
            System.out.println(welcome());
            ///// CHOICE faire fonction
            loadImage(b);

        } else if ( args.length == 3 ) { //If we are in noninteractive mode
            //TODO EXECUTION NON-MACHIN CHOSE

            //Charge l'image en png avec fonction
            //Création des fichier delta et phi avec les bo nom
            //Creation fichier txt

            //Affichage comparatif entre les deux !! faire fonction

        } else {
            throw new Exception("Le nombre d'argument n'est pas le bon ! ");
        }


        boolean b = false;
        //loadImage(b);
       /* ImagePNG i = new ImagePNG("pngs/1024-cube.png"); //CHARGE UN IMAGE PNG
        QuadTree t = new QuadTree(i);
        //System.out.println(t.toString());

        //System.out.println(t.numberNodes(t));



        //t.compressDelta(75, t);
        System.out.println(t.numberNodes(t));
       // t.compressPhi(200000, t, t.numberNodes(t));
        System.out.println(t.numberNodes(t));

        //System.out.println(t.toString());

        t.saveTXT("SaveTXT/test.txt");

        t.savePNG("SavePNG/test1.png");*/

    }


    public static void loadImage(boolean b) throws IOException {
        Scanner scan = new Scanner(System.in);


        System.out.println("Veuillez entrer le nom du fichier à tester : ");///TODO EXPCETION
        try {
            String str = scan.next();

            String begin = "pngs/";
            String end = ".png";


            ImagePNG i = new ImagePNG(begin+str+end); //CHARGE UN IMAGE PNG

            QuadTree t = new QuadTree(i);
            System.out.println("\nAFFICHAGE ARBRE DE LA PHOTO : " + str );
            b = true;
            System.out.println(t.toString());

        }
        catch(Exception e ){
            System.out.println("Le nom du fichier est incorrect ! ");

            throw e;
        } finally {
            if(b == false ){
                loadImage(b);
            }


        }



    }
}
