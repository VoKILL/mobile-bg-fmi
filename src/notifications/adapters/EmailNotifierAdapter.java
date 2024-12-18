package notifications.adapters;

import ads.Ad;
import notifications.external.EmailNotifier;

public class EmailNotifierAdapter implements Notifier {
    private final EmailNotifier emailNotifier;
    private final String email;
    private final String title;

    public EmailNotifierAdapter(String email, String title) {
        this.emailNotifier = new EmailNotifier();
        this.email = email;
        this.title = title;
    }

    @Override
    public void notify(String message) {
        emailNotifier.sendEmail(email, title, message);
    }
}
