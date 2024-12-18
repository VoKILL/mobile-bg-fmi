package search;


import java.util.List;

public class NOfMFilter<T> implements Filter<T> {
    private final int n;
    private final List<Filter<T>> filters;

    public NOfMFilter(int n, List<Filter<T>> filters) {
        if (n <= 0 || filters == null || n > filters.size()) {
            throw new IllegalArgumentException("Invalid N or filters list.");
        }
        this.n = n;
        this.filters = filters;
    }

    @Override
    public boolean matches(T item) {
        int matches = 0;
        for (Filter<T> filter : filters) {
            if (filter.matches(item)) {
                matches++;
            }
            if (matches >= n) {
                return true;
            }
        }
        return false;
    }
}