import java.awt.*;
import java.io.*;
import java.util.*;

public class QuadTree {

    private QuadTree northWest;
    private QuadTree northEast;
    private QuadTree southEast;
    private QuadTree southWest;

    private final QuadTree father;

    private Color color;
    private boolean leaf;

    private final String imagePath;
    private final String compressImagePath;

    //Constructor 1
    public QuadTree(String imagePath) throws IOException {
        this.northWest          = null;
        this.northEast          = null;
        this.southEast          = null;
        this.southWest          = null;

        this.father             = null;

        this.leaf               = false;

        this.color              = null;

        this.imagePath          = imagePath;

        ImagePNG image = Main.loadImagePNG(imagePath);

        this.compressImagePath  = imagePath;

        this.createQuadTree(image, imagePath, 0, 0, image.width());

    }

    //Constructor 2
    private QuadTree(ImagePNG image, String imagePath, int x, int y, int sizeImage, QuadTree father) {
        this.northWest          = null;
        this.northEast          = null;
        this.southEast          = null;
        this.southWest          = null;

        this.father             = father;

        this.leaf               = false;

        this.color              = null;

        this.imagePath          = imagePath;
        this.compressImagePath  = imagePath;

        this.createQuadTree(image, imagePath, x, y, sizeImage);

    }

    /** @role : Create the complete tree of the image.
     *  @param image image in 2^N format, with N = number of pixels per side.
     *  @param x position x of pixel.
     *  @param y position y of pixel.
     *  @param sizeImage number pixel per side.
     */
    private void createQuadTree(ImagePNG image, String imagePath, int x, int y, int sizeImage) {

        if (sizeImage == 1) {
            this.color = image.getPixel(x, y);
            this.leaf = true; //We arrived to a leaf, so we stop the function

        } else {
            int newSizeImage = sizeImage / 2; //Calculation of new size of childrens (North West, North East, South East and South West).

            int newXNE = x + newSizeImage;  //Coordinate X of cutting North East.

            int newXSE = x + newSizeImage;  //Coordinate X of cutting South East.
            int newYSE = y + newSizeImage;  //Coordinate Y of cutting South East.

            int newYSW = y + newSizeImage;  //Coordinate Y of cutting South West.

            this.northWest = new QuadTree(image, imagePath, x, y, newSizeImage, this); //Recursive of cutting North West.
            this.northEast = new QuadTree(image, imagePath, newXNE, y, newSizeImage, this); //Recursive of cutting North East.
            this.southEast = new QuadTree(image, imagePath, newXSE, newYSE, newSizeImage, this); //Recursive of cutting South East.
            this.southWest = new QuadTree(image, imagePath, x, newYSW, newSizeImage, this); //Recursive of cutting South West.


            if (this.northWest.isLeaf() && this.northEast.isLeaf() && this.southEast.isLeaf() && this.southWest.isLeaf()) { //lossless compression.
                if (this.northWest.getColor().equals(this.northEast.getColor()) &&
                    this.northEast.getColor().equals(this.southEast.getColor()) &&
                    this.southEast.getColor().equals(this.southWest.getColor()) &&
                    this.southWest.getColor().equals(this.northWest.getColor())) {

                    this.color = this.northWest.getColor();

                    this.northWest = null;
                    this.northEast = null;

                    this.southEast = null;
                    this.southWest = null;

                    this.leaf = true;
                }
            }
        }
    }

    // ----------------------------------------------- GETTERS -----------------------------------------------

    public Color getColor() {
        return color;
    }

    public QuadTree getNorthWest() {
        return this.northWest;
    }

    public QuadTree getNorthEast() {
        return this.northEast;
    }

    public QuadTree getSouthEast() {
        return this.southEast;
    }

    public QuadTree getSouthWest() {
        return this.southWest;
    }

    public QuadTree getFather() {
        return father;
    }

    public boolean isLeaf() {
        return leaf;
    }

    // ----------------------------------------------- SETTERS -----------------------------------------------

    public void setColor(Color color) {
        this.color = color;
    }

    public void setNorthWest(QuadTree northWest) {
        this.northWest = northWest;
    }

    public void setNorthEast(QuadTree northEast) {
        this.northEast = northEast;
    }

    public void setSouthEast(QuadTree southEast) {
        this.southEast = southEast;
    }

    public void setSouthWest(QuadTree southWest) {
        this.southWest = southWest;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    // ----------------------------------------------- VERIFICATION -----------------------------------------------

    /**
     *
     * @return
     */
    public boolean verificationBound() {
        return !this.isLeaf() && this.getNorthEast().isLeaf() && this.getNorthWest().isLeaf() && this.getSouthWest().isLeaf() && this.getSouthEast().isLeaf();
    }

    // ----------------------------------------------- TO STRING -----------------------------------------------

    /** @role : Display tree in docs format.
     *  @return display in format String.
     */
    public String toString() {
        String display = "(";

        if (this.leaf) {
            return ImagePNG.colorToHex(this.color);
        } else {
            display += "" + this.northWest.toString() + " ";
            display += "" + this.northEast.toString() + " ";
            display += "" + this.southEast.toString() + " ";
            display += "" + this.southWest.toString();

            return display + ")";
        }
    }

    // ----------------------------------------------- COLORIMETRIC DIFFERENCE -----------------------------------------------

    /** @role : Average color between north east, north west, south west and south east.
     *  @return average color.
     */
    public Color colorimetricAverage() {
        int Rm = (this.northWest.getColor().getRed()   + this.northEast.getColor().getRed()   + this.southEast.getColor().getRed()   + this.southWest.getColor().getRed()) / 4; //Average red color between north east, north west, south west and south east.
        int Gm = (this.northWest.getColor().getGreen() + this.northEast.getColor().getGreen() + this.southEast.getColor().getGreen() + this.southWest.getColor().getGreen()) / 4; //Average green color between north east, north west, south west and south east.
        int Bm = (this.northWest.getColor().getBlue()  + this.northEast.getColor().getBlue()  + this.southEast.getColor().getBlue()  + this.southWest.getColor().getBlue()) / 4; //Average blue color between north east, north west, south west and south east.

        return new Color(Rm, Gm, Bm);

    }

    /** @role : Calculate the colorimetric difference.
     *  @param average average color.
     *  @return calcule.
     */
    private int colorimetricDifference(Color average) {
        return (int) Math.sqrt(((this.color.getRed() - average.getRed()) * (this.color.getRed() - average.getRed()) +
                (this.color.getGreen() - average.getGreen()) * (this.color.getGreen() - average.getGreen()) +
                (this.color.getBlue() - average.getBlue()) * (this.color.getBlue() - average.getBlue())) / 3.0);
    }


    /** @role : choose the maximum direction between the 4 directions.
     *  @return //choose the maximum direction between south and north.
     */
    public int maxColorimetricDifference() {
        Color average = this.colorimetricAverage();
        int maxNorth = Math.max(this.northWest.colorimetricDifference(average), this.northEast.colorimetricDifference(average)); //choose the maximum direction between north east and north west.
        int maxSouth = Math.max( this.southWest.colorimetricDifference(average), this.southEast.colorimetricDifference(average)); //choose the maximum direction between south east and south west.

        return Math.max(maxNorth, maxSouth);
    }

    /**
     *
     * @param delta
     * @param tree
     */
    private void maxColorimetricDifference(int delta, QuadTree tree) {
        int colorimetricDifference = tree.maxColorimetricDifference();

        if (colorimetricDifference <= delta) {
            Color newColor =  tree.colorimetricAverage();

            tree.setColor(newColor);

            tree.setNorthWest(null);//All of sons becomes null
            tree.setNorthEast(null);
            tree.setSouthWest(null);
            tree.setSouthEast(null);


            tree.setLeaf(true);
        }
    }

    // ----------------------------------------------- COMPRESS DELTA -----------------------------------------------

    public void compressDelta(int delta) {
        compressDelta(delta, this);
    }
    /** @role :
     *  @param delta
     *  @param tree
     */
    private void compressDelta(int delta, QuadTree tree) {

        if (!tree.isLeaf()) {
            if (tree.verificationBound()) { //If all of sons are leaf
                maxColorimetricDifference(delta, tree);
            } else {
                compressDelta(delta, tree.getNorthWest());
                compressDelta(delta, tree.getNorthEast());
                compressDelta(delta, tree.getSouthEast());
                compressDelta(delta, tree.getSouthWest());

                if (tree.verificationBound()) { //If all of sons are leaf
                    maxColorimetricDifference(delta, tree);
                }
           }
        }
    }


    // ----------------------------------------------- COMPRESS PHI -----------------------------------------------

    /**
     *
     * @param phi
     */
    public void compressPhi(int phi) {
        compressPhi(this, phi);
    }

    /**
     * Compress the number of leaves until reaching phi.
     * @param tree Shaft to compress
     * @param phi leaf limit to be reached.
     */
    private void compressPhi(QuadTree tree, int phi) {
        int numberLeaf = tree.numberLeafs(tree); //Calculate numbers leaf in quadtree.

        Comparator<QuadTree> comparator = new QuadTreeComparator();
        TreeSet<QuadTree> listLeaf = new TreeSet<>(comparator);

        this.compressPhiTri(tree, listLeaf); //fill listLeaf with leafs.

        while (phi < numberLeaf) {

            QuadTree saveTree = listLeaf.first();
            listLeaf.remove(saveTree);

            crushLeaf(saveTree);//and crush (see crush fonction).

            if (saveTree.getFather() != null && saveTree.getFather().verificationBound()) {
                listLeaf.add(saveTree.getFather()); //Add in new listLeaf if the father has as a result of overwriting 4 sons.
            }

            numberLeaf -= 3;
        }
    }


    /**
     * Adds all the leaves of a tree to a list.
     * @param tree Quadtree
     * @param list list of leafs.
     */
    private void compressPhiTri( QuadTree tree, TreeSet<QuadTree> list) {
        if (tree != null) {
            if (tree.verificationBound()) {
                list.add(tree);
            } else {
                tree.compressPhiTri(tree.getNorthWest(), list);
                tree.compressPhiTri(tree.getNorthEast(), list);
                tree.compressPhiTri(tree.getSouthEast(), list);
                tree.compressPhiTri(tree.getSouthWest(), list);
            }
        }
    }

    // ----------------------------------------------- LEAFS OPERATION -----------------------------------------------

    /** @role : turns a father of 4 leaves into leaves.
     * @param tree Father of 4 leaves.
     */
    private void crushLeaf(QuadTree tree) {
        Color newColor =  tree.colorimetricAverage();

        tree.setColor(newColor);

        tree.setNorthWest(null);//All of sons becomes null
        tree.setNorthEast(null);
        tree.setSouthWest(null);
        tree.setSouthEast(null);


        tree.setLeaf(true);

    }


        /** @role : This function count the number of leaves in the tree
         * @param tree
         * @return
         */
    public int numberLeafs(QuadTree tree){
        if(tree != null) {
            if (tree.isLeaf()) {
                return 1;
            } else {
                return (numberLeafs(tree.getNorthWest()) + numberLeafs(tree.getNorthEast()) + numberLeafs(tree.getSouthWest()) + numberLeafs(tree.getSouthEast()));
            }
        }

        return 0;
    }

    // ----------------------------------------------- SAVE PNG -----------------------------------------------


    /**
     *
     * @param filename
     * @throws IOException
     */
    public void savePNG(String filename) throws IOException {
        ImagePNG image = Main.loadImagePNG(this.imagePath);

        this.compressionPNG(image, this, 0, 0, image.width());

        image.save(filename);
    }

    /** @role : Recursively cycle through the tree and overwrite identical pixel packets with the help of crushPixelPNG.
     *
     * @param image image to compress to PNG.
     * @param arbre Tree to parcoured.
     * @param x position X in image.
     * @param y position Y in image.
     * @param sizeImage number of pixels on one side of the image.
     */
    private void compressionPNG(ImagePNG image, QuadTree arbre, int x, int y, int sizeImage) {
        if (arbre.isLeaf()) {
            crushPixelPNG(image, x, y, x, y, sizeImage, arbre.getColor());
        } else {
            int newSizeImage = sizeImage / 2; //Calculation of new size of childrens (North West, North East, South East and South West).

            int newXNE = x + newSizeImage;  //Coordinate X of cutting North East.

            int newXSE = x + newSizeImage;  //Coordinate X of cutting South East.
            int newYSE = y + newSizeImage;  //Coordinate Y of cutting South East.

            int newYSW = y + newSizeImage;  //Coordinate Y of cutting South West.

            compressionPNG(image, arbre.getNorthWest(), x, y, newSizeImage);
            compressionPNG(image, arbre.getNorthEast(), newXNE, y, newSizeImage);
            compressionPNG(image, arbre.getSouthEast(), newXSE, newYSE, newSizeImage);
            compressionPNG(image, arbre.getSouthWest(), x, newYSW, newSizeImage);
        }
    }

    /** @role : overwrite pixels of identical packet.
     *
     * @param image
     * @param x0
     * @param y0
     * @param x
     * @param y
     * @param sizeImage
     * @param rgb
     */
    private void crushPixelPNG(ImagePNG image, int x0, int y0,  int x, int y, int sizeImage, Color rgb) {

        for (int i = x ; i < x + sizeImage ; ++i) {
            for (int j = y ; j < y + sizeImage ; j++) {
                image.setPixel(i, j, rgb);
            }
        }
    }

    // ----------------------------------------------- SAVE TXT -----------------------------------------------

    public void saveTXT(String location) {
        String quadTreeTXT = this.toString();

        try {
            BufferedWriter write = new BufferedWriter(new FileWriter(location));

            write.write(quadTreeTXT);

            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------- EQM -----------------------------------------------

    public double EQM() throws IOException {
        ImagePNG imageOrigine = Main.loadImagePNG(this.imagePath);
        ImagePNG imageCompress = Main.loadImagePNG(this.compressImagePath);

        return ImagePNG.computeEQM(imageOrigine,imageCompress);
    }
}
