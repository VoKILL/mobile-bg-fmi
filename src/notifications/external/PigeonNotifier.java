package notifications.external;

public class PigeonNotifier {
    public void sendPigeon(String address, Integer pigeonNumber, String message) {
        System.out.println("Sending pigeon number " + pigeonNumber + " to " + address + " with message: " + message);
    }
}