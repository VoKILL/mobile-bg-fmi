package notifications.adapters;

import ads.Ad;
import notifications.external.SmsNotifier;

public class SmsNotifierAdapter implements Notifier {
    private final SmsNotifier smsNotifier;
    private final String phoneNumber;

    public SmsNotifierAdapter(String phoneNumber) {
        this.smsNotifier = new SmsNotifier();
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void notify(String message) {
        smsNotifier.sendSms(phoneNumber, message);
    }
}
