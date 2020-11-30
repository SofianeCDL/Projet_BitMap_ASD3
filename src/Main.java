import javax.swing.text.AbstractWriter;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws Exception {

        ImagePNG i = new ImagePNG("pngs/128-gnu.png");
        QuadTree t = new QuadTree(i);
       //System.out.println(t.toString());

        //System.out.println(t.numberNodes(t));



        //t.compressDelta(75, t);
        System.out.println(t.numberNodes(t));
        ArrayList<savePhiLeaf> h = new ArrayList<>();
        t.compressPhi(t, 250);
        System.out.println(t.numberNodes(t));

        //System.out.println(t.toString());

        t.saveTXT("SaveTXT/test.txt");

        t.savePNG("SavePNG/test1.png");





    }
}