import java.io.IOException;

public class MainSimple {

    public static void main(String[] args) throws IOException {
        ImagePNG i = new ImagePNG("pngs/32-tux.png"); //CHARGE UN IMAGE PNG

        QuadTree t = new QuadTree(i);

        t.compressDelta(255, t);

        t.savePNG("SavePNG/test1.png");

        System.out.println(Main.displayEQM(t));

    }
}
