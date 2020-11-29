import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class QuadTree {

    private QuadTree northWest, northEast, southEast, southWest;
    private Color color;
    private boolean leaf;

    private ImagePNG image;

    //Constructor 1
    public QuadTree(ImagePNG image) {
        this.northWest = null;
        this.northEast = null;
        this.southEast = null;
        this.southWest = null;

        this.leaf = false;

        this.color = null;

        this.image = image;

        this.createQuadTree(image, 0, 0, image.width());

    }

    //Constructor 2
    private QuadTree(ImagePNG image, int x, int y, int sizeImage) {
        this.northWest = null;
        this.northEast = null;
        this.southEast = null;
        this.southWest = null;

        this.leaf = false;

        this.color = null;

        this.createQuadTree(image, x, y, sizeImage);

    }

    //Methods
    public boolean isLeaf() {
        return leaf;
    }
    //Accessors
    public Color getColor() {
        return color;
    }

    /**
     *
     * @param image
     * @param x
     * @param y
     * @param sizeImage
     */
    private void createQuadTree(ImagePNG image, int x, int y, int sizeImage) {

        if (sizeImage == 1) {
            this.color = image.getPixel(x, y);
            this.leaf = true; //We arrived to a leaf, so we stop the function

        } else {
            int newSizeImage = sizeImage / 2; //Calculation of new size of childrens (North West, North East, South East and South West).

            int newXNW = x;                 //Coordinate X of cutting North West.
            int newYNW = y;                 //Coordinate Y of cutting North West.

            int newXNE = x + newSizeImage;  //Coordinate X of cutting North East.
            int newYNE = y;                 //Coordinate Y of cutting North East.

            int newXSE = x + newSizeImage;  //Coordinate X of cutting South East.
            int newYSE = y + newSizeImage;  //Coordinate Y of cutting South East.

            int newXSW = x;                 //Coordinate X of cutting South West.
            int newYSW = y + newSizeImage;  //Coordinate Y of cutting South West.

            this.northWest = new QuadTree(image, newXNW, newYNW, newSizeImage); //Recursive of cutting North West.
            this.northEast = new QuadTree(image, newXNE, newYNE, newSizeImage); //Recursive of cutting North East.
            this.southEast = new QuadTree(image, newXSE, newYSE, newSizeImage); //Recursive of cutting South East.
            this.southWest = new QuadTree(image, newXSW, newYSW, newSizeImage); //Recursive of cutting South West.


            if (this.northWest.isLeaf() && this.northEast.isLeaf() && this.southEast.isLeaf() && this.southWest.isLeaf()) {
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

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @role :
     * @return
     */
    public String toString() {
        String display = " ";

        if (this.leaf) {
            return ImagePNG.colorToHex(this.color);
        } else {
            display += "(" + this.northWest.toString() + ") ";
            display += "(" + this.northEast.toString() + ") ";
            display += "(" + this.southEast.toString() + ") ";
            display += "(" + this.southWest.toString() + ") ";

            return display + " ";
        }
    }

    /**
     * Average color between north east, north west, south west and south east.
     * @return average color.
     */
    public Color colorimetricAverage() {
        int Rm = this.northEast.getColor().getRed() + this.northWest.getColor().getRed() + this.southWest.getColor().getRed() + this.southEast.getColor().getRed(); //Average red color between north east, north west, south west and south east.
        int Gm = this.northEast.getColor().getGreen() + this.northWest.getColor().getGreen() + this.southWest.getColor().getGreen() + this.southEast.getColor().getGreen(); //Average green color between north east, north west, south west and south east.
        int Bm = this.northEast.getColor().getBlue() + this.northWest.getColor().getBlue() + this.southWest.getColor().getBlue() + this.southEast.getColor().getBlue(); //Average blue color between north east, north west, south west and south east.

        return new Color(Rm, Gm, Bm);

    }

    /**
     * calculate the colorimetric difference.
     * @param average average color.
     * @return calcule.
     */
    public int colorimetricDifference(Color average) {
        return (int) Math.sqrt((this.color.getRed() - average.getRed()) * (this.color.getRed() - average.getRed()) +
                (this.color.getGreen() - average.getGreen()) * (this.color.getGreen() - average.getGreen()) +
                (this.color.getBlue() - average.getBlue()) * (this.color.getBlue() - average.getBlue()));
    }

    /**
     * choose the maximum direction between the 4 directions.
     * @return //choose the maximum direction between south and north.
     */
    public int maxColorimetricDifference() {
        Color average = this.colorimetricAverage();
        int maxNorth = Math.max(this.northEast.colorimetricDifference(average), this.northWest.colorimetricDifference(average)); //choose the maximum direction between north east and north west.
        int maxSouth = Math.max(this.southEast.colorimetricDifference(average), this.southWest.colorimetricDifference(average)); //choose the maximum direction between south east and south west.

        return Math.max(maxNorth, maxSouth);
    }

    /**
     *
     * @param delta
     * @param tree
     */
    public void compressDelta(int delta, QuadTree tree) {

        if (tree.isLeaf()) {
            return;
        } else {
            if (tree.getNorthEast().isLeaf() && tree.getNorthWest().isLeaf() && tree.getSouthWest().isLeaf() && tree.getSouthEast().isLeaf()) {
                int colorimetricDifference = tree.maxColorimetricDifference();

                if (colorimetricDifference <= delta) {
                    this.color = new Color(colorimetricDifference);

                    this.northEast = null;
                    this.northWest = null;
                    this.southWest = null;
                    this.southEast = null;

                    this.leaf = leaf;
                }
            } else {

            }
        }

    }

    public void savePNG(String filename) throws IOException {
        ImagePNG imageClone = this.image.clone();

        compressionPNG(imageClone, this, 0, 0, image.width());

        imageClone.save(filename);
    }

    public void compressionPNG(ImagePNG image, QuadTree arbre, int x, int y, int sizeImage) {
        if (arbre.isLeaf()) {
            System.out.println("X = " + x + " / Y = " + y + " compression block" );
            compressionBlockPNG(image, x, y, sizeImage, arbre.getColor());
        } else {
            int newSizeImage = sizeImage / 2; //Calculation of new size of childrens (North West, North East, South East and South West).

            int newXNE = x + newSizeImage;  //Coordinate X of cutting North East.

            int newXSE = x + newSizeImage;  //Coordinate X of cutting South East.
            int newYSE = y + newSizeImage;  //Coordinate Y of cutting South East.

            int newYSW = y + newSizeImage;  //Coordinate Y of cutting South West.

            compressionPNG(image, this.northWest, x, y, newSizeImage);
            compressionPNG(image, this.northEast, newXNE, y, newSizeImage);
            compressionPNG(image, this.southEast, newXSE, newYSE, newSizeImage);
            compressionPNG(image, this.southWest, x, newYSW, newSizeImage);
        }
    }

    public void compressionBlockPNG(ImagePNG image, int x, int y, int sizeImage, Color rgb) {

        if (x == x + sizeImage) {
            //image.setPixel(x, y, new Color(0,0,0));
            //compressionBlockPNG(image, x, y + 1, sizeImage, maxXY, rgb);
        } else if (y == y + sizeImage) {
            //image.setPixel(x, y, new Color(0,0,0));
            //compressionBlockPNG(image, x + 1, y, sizeImage, maxXY, rgb);
        } else {
            System.out.println("X = " + x + " / Y = " + y + " block" );

            image.setPixel(x, y, new Color(0, 0, 0));

            compressionBlockPNG(image, x + 1, y, sizeImage, rgb);
            compressionBlockPNG(image, x, y + 1, sizeImage, rgb);
        }
    }



    public void saveTXT(String location) throws IOException {
        String quadTreeTXT = this.toString();

        try {
            BufferedWriter write = new BufferedWriter(new FileWriter(location));

            write.write(quadTreeTXT);

            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
