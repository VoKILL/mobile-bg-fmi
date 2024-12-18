package search;

import java.util.List;

public class OrFilter<T> implements Filter<T> {
    private final List<Filter<T>> filters;

    public OrFilter(List<Filter<T>> filters) {
        this.filters = filters;
    }

    @Override
    public boolean matches(T item) {
        for (Filter<T> filter : filters) {
            if (filter.matches(item)) {
                return true;
            }
        }
        return false;
    }
}