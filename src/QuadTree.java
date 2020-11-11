import java.awt.*;

public class QuadTree {

    private QuadTree northWest, northEast, southEast, southWest;
    private Color color;
    private boolean isLeaf;


    public QuadTree(ImagePNG image) {
        this.northWest = null;
        this.northEast = null;

        this.southEast = null;
        this.southWest = null;

        this.isLeaf = false;

        this.color = null;

        this.creatQuadTree(image, 0, 0, image.width());

    }

    public QuadTree(ImagePNG image, int x, int y, int sizeImage) {
        this.northWest = null;
        this.northEast = null;

        this.southEast = null;
        this.southWest = null;

        this.isLeaf = false;

        this.color = null;

        this.creatQuadTree(image, x, y, sizeImage);

    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public Color getColor() {
        return color;
    }

    public void creatQuadTree(ImagePNG image, int x, int y, int sizeImage) {

        if (sizeImage == 1) {
            this.color = image.getPixel(x, y);
            this.isLeaf = true;
        } else {
            int newSizeImage = sizeImage / 2;

            int newXNW = 0;
            int newYNW = 0;

            int newXNE = x + newSizeImage;
            int newYNE = 0;

            int newXSE = x + newSizeImage;
            int newYSE = y + newSizeImage;

            int newXSW = 0;
            int newYSW = y + newSizeImage;

            this.northWest = new QuadTree(image, newXNW, newYNW, newSizeImage);
            this.northEast = new QuadTree(image, newXNE, newYNE, newSizeImage);
            this.southEast = new QuadTree(image, newXSE, newYSE, newSizeImage);
            this.southWest = new QuadTree(image, newXSW, newYSW, newSizeImage);

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
}
