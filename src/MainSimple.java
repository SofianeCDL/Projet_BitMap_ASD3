import java.io.IOException;
import java.util.Comparator;
import java.util.TreeSet;

public class MainSimple {

    public static void main(String[] args) throws IOException {

       // QuadTree t = new QuadTree("pngs/32-tux.png");
        QuadTree t2 = new QuadTree("32-tux");
        //QuadTree t3 = new QuadTree("pngs/2_3.png");

        /*System.out.println("t1 : " + t.toString() + " | t2 : " + t2.toString() + " | t3 : " + t3.toString());

        Comparator<QuadTree> comparator = new QuadTreeComparator();
        TreeSet<QuadTree> test = new TreeSet<>(comparator);


        test.add(t2);
        test.add(t3);

        System.out.println("t1 : " + t.maxColorimetricDifference() + " | t2 : " + t2.maxColorimetricDifference() + " | t3 : " + t3.maxColorimetricDifference());

        System.out.println(test.toString());

        test.remove(t3);

        System.out.println(test.toString());

        test.add(t);

        System.out.println(test.toString());*/


        //System.out.println(t.toString());

        System.out.println(t2.numberLeafs(t2));
        t2.compressDelta(255);
        System.out.println(t2.numberLeafs(t2));


        t2.savePNG("SavePNG/test2.png");

        Main.displayEQM(t2);

    }
}
