import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class QuadTree {

    private QuadTree northWest, northEast, southEast, southWest;
    private Color color;
    private boolean isLeaf;

    private ImagePNG image;

    //Constructor 1
    public QuadTree(ImagePNG image) {
        this.northWest = null;
        this.northEast = null;
        this.southEast = null;
        this.southWest = null;

        this.isLeaf = false;

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

        this.isLeaf = false;

        this.color = null;

        this.createQuadTree(image, x, y, sizeImage);

    }

    //Methods
    public boolean isLeaf() {
        return isLeaf;
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
            this.isLeaf = true; //We arrived to a leaf, so we stop the function

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


            if (this.northWest.isLeaf && this.northEast.isLeaf && this.southEast.isLeaf && this.southWest.isLeaf) {
                if (this.northWest.getColor().equals(this.northEast.getColor()) &&
                    this.northEast.getColor().equals(this.southEast.getColor()) &&
                    this.southEast.getColor().equals(this.southWest.getColor()) &&
                    this.southWest.getColor().equals(this.northWest.getColor())) {

                    this.color = this.northWest.getColor();

                    this.northWest = null;
                    this.northEast = null;

                    this.southEast = null;
                    this.southWest = null;

                    this.isLeaf = true;
                }
            }
        }
    }


    /**
     * @role :
     * @return
     */
    public String toString() {
        String display = " ";

        if (this.isLeaf) {
            return ImagePNG.colorToHex(this.color);
        } else {
            display += "(" + this.northWest.toString() + ") ";
            display += "(" + this.northEast.toString() + ") ";
            display += "(" + this.southEast.toString() + ") ";
            display += "(" + this.southWest.toString() + ") ";

            return display + " ";
        }
    }

    public void savePNG(String filename) throws IOException {
        ImagePNG imageClone = this.image.clone();

        imageClone.save(filename);
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
