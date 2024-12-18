package notifications;

import ads.Ad;
import notifications.adapters.Notifier;
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

    public void notify(String message) {
        notifier.notify(message);
    }
}
