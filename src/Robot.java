import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Robot {

    private final List<RobotParts> robotParts = new ArrayList<>();

    public Robot() {
        robotParts.addAll(Arrays.asList(RobotParts.values()));
    }

    public List<RobotParts> getRobotParts() {
        return robotParts;
    }

    @Override
    public String toString() {
        return  robotParts.toString();
    }
}
