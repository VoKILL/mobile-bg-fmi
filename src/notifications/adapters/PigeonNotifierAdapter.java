package notifications.adapters;

import ads.Ad;
import notifications.external.PigeonNotifier;

public class PigeonNotifierAdapter implements Notifier {
    private final PigeonNotifier pigeonNotifier;
    private final String address;
    private final Integer pigeonNumber;

    public PigeonNotifierAdapter(String address, Integer pigeonNumber) {
        this.pigeonNotifier = new PigeonNotifier();
        this.address = address;
        this.pigeonNumber = pigeonNumber;
    }

    @Override
    public void notify(Ad ad) {
        String message = "New ad matched your criteria: " + ad;
        pigeonNotifier.sendPigeon(address, pigeonNumber, message);
    }
}
