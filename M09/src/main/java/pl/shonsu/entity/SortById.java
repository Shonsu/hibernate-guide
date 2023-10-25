package pl.shonsu.entity;

import java.util.Comparator;

public class SortById implements Comparator<Review> {

    @Override
    public int compare(Review o1, Review o2) {
        return o2.getId().compareTo(o1.getId());
    }
}
