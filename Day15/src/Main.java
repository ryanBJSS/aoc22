import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) throws URISyntaxException, IOException {
   Path path = Paths.get(Objects.requireNonNull(Main.class.getClassLoader()
        .getResource("input.txt")).toURI());


    var inputLines = Files.lines(path).toList();

    var allNodes = new ArrayList<Node>();
    inputLines.forEach(line -> allNodes.add(new InputParser(line).parse()));
    System.out.println(allNodes);

    var allOut = new HashSet<Node>();
    allNodes.stream().forEach( node -> allOut.addAll(node.ruleSimple(2000000)));

    var beacons = allNodes.stream().map(Node::getClosestBeacon).toList();

    var filtered = allOut.stream().filter( node -> !beacons.contains(new Node(node.getX(), node.getY()))).toList();
    System.out.println(filtered.size());
//    System.out.println(filtered);

  }
}