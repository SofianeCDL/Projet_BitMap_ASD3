import java.io.IOException;
import java.util.Comparator;
import java.util.TreeSet;

public class MainSimple {

    public static void main(String[] args) throws IOException {

        //Menu m = new Menu();

        //m.noInteractiveProgramme(args);

        // instructions

        long dep = System.currentTimeMillis();


        QuadTree t = new QuadTree("1024-cube");
        //QuadTree t2 = new QuadTree("1024-cube");
        //QuadTree t3 = new QuadTree("1024-cube");

        /*
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
        //System.out.println(cpt.length());*/

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


    }
}
