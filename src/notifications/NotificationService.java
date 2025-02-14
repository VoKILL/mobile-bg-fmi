package notifications;

import java.util.ArrayList;
import java.util.List;
import ads.Ad;

public class NotificationService {
    private final List<NotificationRule> rules = new ArrayList<>();

    public void addRule(NotificationRule rule) {
        rules.add(rule);
    }

    public void notifyAll(Ad ad) {
        for (NotificationRule rule : rules) {
            if (rule.matches(ad)) {
                String message = "New ad matched your criteria: " + ad;
                rule.notify(message);
            }
        }
    }
}
