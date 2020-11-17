import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ImagePNG i = new ImagePNG("pngs/4.png");
        QuadTree t = new QuadTree(i);
        System.out.println(t.toString());



    }
}
