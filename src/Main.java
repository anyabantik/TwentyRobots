import java.util.HashMap;
import java.util.Map;

public class Main {

    private static volatile Map<RobotParts, Integer> storage = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {

        Factory factory = new Factory(storage);
        Country countryOne = new Country("Springfield", storage);
        Country countryTwo = new Country("SouthPark", storage);
        Country countryThree = new Country("Quahog", storage);
        Thread producer = new Thread(factory::produceParts);
        Thread country1 = new Thread(countryOne::createRobots);
        Thread country2 = new Thread(countryTwo::createRobots);
        Thread country3 = new Thread(countryThree::createRobots);
        producer.setDaemon(true);
        producer.start();
        country1.start();
        country2.start();
        country3.start();
        country1.join();
        country2.join();
        country3.join();
    }
}