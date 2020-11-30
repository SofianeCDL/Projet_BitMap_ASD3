import java.util.Comparator;

public class QuadTreeComparator implements Comparator<QuadTree> {

    @Override
    public int compare(QuadTree o1, QuadTree o2) {
        if (o1.verification() && o2.verification()) {
            int delta1 = o1.maxColorimetricDifference();
            int delta2 = o2.maxColorimetricDifference();

            if (delta1 < delta1) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
