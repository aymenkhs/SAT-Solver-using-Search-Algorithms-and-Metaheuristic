package part_2.algorithms;

import java.util.Comparator;

public class IndividualComparator  implements Comparator<Individual> {

    @Override
    public int compare(Individual o1, Individual o2) {
        return o2.getScore() - o1.getScore();
    }
}
