import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        ImagePNG i = new ImagePNG("pngs/16.png");
        QuadTree t = new QuadTree(i);
        System.out.println(t.toString());

        //System.out.println(t.numberNodes(t));



        t.compressDelta(75, t);

        System.out.println(t.toString());

        t.saveTXT("SaveTXT/test.txt");

        t.savePNG("SavePNG/test1.png");








    }
}
