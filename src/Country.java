import java.util.Iterator;
import java.util.Map;

public class Country {

    private final int ROBOTS_NUMBER = 10;
    private static volatile boolean stop;
    private final String countryName;
    private int robotsNumber;
    private Map<RobotParts, Integer> factory;

    public Country(String countryName, Map<RobotParts, Integer> factory) {
        this.countryName = countryName;
        this.factory = factory;
    }

    public void createRobots() {
        Robot robot = new Robot();
        try {
            while (!stop) {
                synchronized (factory) {
                    Iterator<RobotParts> partsIterator = robot.getRobotParts().iterator();
                    while (partsIterator.hasNext()) {
                        RobotParts part = partsIterator.next();
                        Integer partsNumber = factory.get(part);
                        if (partsNumber != null && partsNumber > 0) {
                            partsIterator.remove();
                            factory.put(part, partsNumber - 1);
                        }
                    }
                    if (robot.getRobotParts().isEmpty()) {
                        robotsNumber++;
                        if (robotsNumber == ROBOTS_NUMBER) {
                            System.out.println(countryName + " win!");
                            stop = true;
                            factory.notifyAll();
                            break;
                        } else {
                            robot = new Robot();
                        }
                    }
                    System.out.println(countryName + " has " + robotsNumber + " robots, needs more robots " + robot);
                    factory.wait();
                }
            }
        } catch (InterruptedException ignored) {
        }
    }

}
