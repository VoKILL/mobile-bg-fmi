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
    public void notify(Ad ad) {
        // Например: изпращаме имейл със заглавие и информация за обявата
        String message = "New ad matched your criteria: " + ad;
        emailNotifier.sendEmail(email, title, message);
    }
}
