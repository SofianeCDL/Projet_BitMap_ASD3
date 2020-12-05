import java.io.Console;
import java.io.IOException;
import java.util.Comparator;
import java.util.TreeSet;

public class MainSimple {

    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE


    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE}

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws Exception {

        Menu m = new Menu();
        m.programm(args);





        /*QuadTree t = new QuadTree("1024-cube");
        long dep = System.currentTimeMillis();
        t.compressPhi(100);
        System.out.println((System.currentTimeMillis() - dep) / 1000.0);
        t.savePNG("SavePNG/test.png");
        t.EQM();
        System.out.println((System.currentTimeMillis() - dep) / 1000.0);*/
        //t.savePNG("SavePNG/test.png");



        /*//m.noInteractiveProgramme(args);

        // instructions

        long dep = System.currentTimeMillis();


        QuadTree t = new QuadTree("1024-cube");
        //QuadTree t2 = new QuadTree("1024-cube");
        //QuadTree t3 = new QuadTree("1024-cube");


        System.out.println("t1 : " + t.toString() + " | t2 : " + t2.toString() + " | t3 : " + t3.toString());

        Comparator<QuadTree> comparator = new QuadTreeComparator();
        TreeSet<QuadTree> test = new TreeSet<>(comparator);


        test.add(t2);
        test.add(t3);

        System.out.println("t1 : " + t.maxColorimetricDifference() + " | t2 : " + t2.maxColorimetricDifference() + " | t3 : " + t3.maxColorimetricDifference());

        System.out.println(test.toString());

        test.remove(t3);

        System.out.println(test.toString());

        test.add(t);

        System.out.println(test.toString());


        //System.out.println(t.toString());


        String cpt = t2.toString();
        //System.out.println(cpt.length());

        //System.out.println(t2.numberLeafs(t2));
        t.compressPhi(4);
        //t2.compressPhi(191);
        //t3.compressPhi(192);
        //System.out.println(t2.numberLeafs(t2));


        t.savePNG("SavePNG/test.png");
        //t2.savePNG("SavePNG/test2.png");
        //t3.savePNG("SavePNG/test3.png");
        //t2.saveTXT("SaveTXT/test.txt");

        //Main.displayEQM(t2);

        System.out.println((System.currentTimeMillis() - dep) / 1000.0);

        System.out.print( BLACK + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( BLACK_BOLD + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( BLACK_UNDERLINED + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( BLACK_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( BLACK_BOLD_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( ANSI_BLACK + "\nMerci, au revoir et à la prochaîne !\n" + RESET);

        System.out.print( RED + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( RED_BOLD + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( RED_UNDERLINED + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( RED_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( RED_BOLD_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( ANSI_RED + "\nMerci, au revoir et à la prochaîne !\n" + RESET);

        System.out.print( GREEN + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( GREEN_BOLD + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( GREEN_UNDERLINED + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( GREEN_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( GREEN_BOLD_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( ANSI_GREEN + "\nMerci, au revoir et à la prochaîne !\n" + RESET);

        System.out.print( YELLOW + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( YELLOW_BOLD + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( YELLOW_UNDERLINED + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( YELLOW_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( YELLOW_BOLD_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( ANSI_YELLOW + "\nMerci, au revoir et à la prochaîne !\n" + RESET);

        System.out.print( BLUE + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( BLUE_BOLD + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( BLUE_UNDERLINED + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( BLUE_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( BLUE_BOLD_BRIGHT+ "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( ANSI_BLUE+ "\nMerci, au revoir et à la prochaîne !\n" + RESET);

        System.out.print( PURPLE + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( PURPLE_BOLD + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( PURPLE_UNDERLINED+ "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( PURPLE_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( PURPLE_BOLD_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( ANSI_PURPLE + "\nMerci, au revoir et à la prochaîne !\n" + RESET);

        System.out.print( CYAN + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( CYAN_BOLD + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( CYAN_UNDERLINED + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( CYAN_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( CYAN_BOLD_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( ANSI_CYAN + "\nMerci, au revoir et à la prochaîne !\n" + RESET);

        System.out.print( WHITE + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( WHITE_BOLD + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( WHITE_UNDERLINED + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( WHITE_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( WHITE_BOLD_BRIGHT + "\nMerci, au revoir et à la prochaîne !\n" + RESET);
        System.out.print( ANSI_WHITE + "\nMerci, au revoir et à la prochaîne !\n" + RESET);*/


    }


}
