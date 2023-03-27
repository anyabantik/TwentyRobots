import java.util.Map;
import java.util.Random;

public class Factory {

    private static final Random RANDOM = new Random();
    private final int WAIT_TIME = 100;
    private final Map<RobotParts, Integer> robotPartsStorage;

    public Factory(Map<RobotParts, Integer> robotPartsStorage) {
        this.robotPartsStorage = robotPartsStorage;
    }

    public void produceParts() {
        try {
            while (true) {
                synchronized (robotPartsStorage) {
                    RobotParts robotPart = generateModel();
                    Integer numberOfParts = robotPartsStorage.computeIfAbsent(robotPart, parts -> 0);
                    robotPartsStorage.put(robotPart, ++numberOfParts);
                    System.out.println("Parts in storage " + robotPartsStorage);
                    robotPartsStorage.notify();
                    robotPartsStorage.wait(WAIT_TIME);
                }
            }
        } catch (InterruptedException ignored) {
        }
    }

    private static RobotParts generateModel() {
        return RobotParts.values()[RANDOM.nextInt(RobotParts.values().length)];
    }
}
