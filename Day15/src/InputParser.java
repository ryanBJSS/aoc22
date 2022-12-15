import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

  private static final Pattern pattern = Pattern.compile("-?\\d+");
  private Matcher scannerMatcher;
  private Matcher beaconMatcher;


  public InputParser(String input) {
    var parts = input.split(":");
    scannerMatcher = pattern.matcher(parts[0]);
    beaconMatcher = pattern.matcher(parts[1]);
  }

  public Node parse() {
    var beacon = new Node(beaconCoord(), beaconCoord());
    return new Node( scannerCoord(), scannerCoord(), beacon);
  }

  private int scannerCoord() {
    scannerMatcher.find();
    return Integer.parseInt(scannerMatcher.group());
  }

  private int beaconCoord() {
    beaconMatcher.find();
    return Integer.parseInt(beaconMatcher.group());
  }

}
