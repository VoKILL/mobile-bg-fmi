package notification;

import ads.Ad;
import search.Filter;

public class NotificationRule {
    private final Filter<Ad> filter;
    private final Notifier notifier;

    public NotificationRule(Filter<Ad> filter, Notifier notifier) {
        this.filter = filter;
        this.notifier = notifier;
    }

    public boolean matches(Ad ad) {
        return filter.matches(ad);
    }

    public void notify(Ad ad) {
        notifier.notify(ad);
    }
}
