import java.io.IOException;
import java.util.ArrayList;

public class Menu {
    private ArrayList<String> listMenus;

    public Menu() {
        this.listMenus = new ArrayList<>();

    }

    public void noInteractiveProgramme(String[] args) throws IOException {
        createDeltaFile(Integer.parseInt(args[1]), args[0], args[0]);
        createPhiFile(Integer.parseInt(args[2]), args[0], args[0]);


    }

    //---------------------------------------- CREATE FILE
    ///TODO BLOQUER DELTA ENTRE 0 ET 254 !!!!!!!!!!!!!!!!!!!!!!!!!
    private void createDeltaFile(int delta, String name, String imagePath) throws IOException {
        QuadTree deltaTree = new QuadTree(imagePath);
        deltaTree.compressDelta(delta);

        deltaTree.saveTXT("SaveTXT/" + name + "-delta" + delta + ".txt");
        deltaTree.savePNG("SavePNG/" + name + "-delta" + delta + ".png");

        System.out.print("\n Comparaison fichiers Delta : ");
        displayEQM(deltaTree);


    }

    ///TODO PHI > 0 !!!!!!!!!!!!!!!!!!!!!!!!!
    private void createPhiFile(int phi, String name, String imagePath) throws IOException {
        QuadTree phiTree = new QuadTree(imagePath);
        phiTree.compressPhi(phi);

        phiTree.saveTXT("SaveTXT/" + name + "-phi" + phi + ".txt");
        phiTree.savePNG("SavePNG/" + name + "-phi" + phi + ".png");

        System.out.print("\n Comparaison fichiers Phi : ");
        displayEQM(phiTree);

    }

    public void displayEQM(QuadTree tree) throws IOException {
        System.out.println("\nECART QUATRADIQUE MOYEN : " + tree.EQM() + "%");
    }

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
