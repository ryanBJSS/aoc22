import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

  private static int totalX = 0;
  private static List<List<String>> crt = List.of(new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(),
      new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());

  public static void main(String[] args) throws URISyntaxException, IOException {
    Path path = Paths.get(Objects.requireNonNull(Main.class.getClassLoader()
        .getResource("input.txt")).toURI());
    var inputLines = Files.lines(path).map(String::trim).toList();


    IntStream.rangeClosed(0, 5).boxed().toList().forEach( out -> {
      IntStream.rangeClosed(0, 39).boxed().toList().forEach( in -> {
        crt.get(out).add(".");
      });
    });

    var x = 1;

    var cycle = 1;

    for (var line : inputLines) {
      if (line.equals("noop")) {
        checkCycle(cycle, x);
        cycle++;
      } else {
        checkCycle(cycle, x);
        cycle++;
        var toAdd = Integer.parseInt(line.split(" ")[1]);
        x += toAdd;
        checkCycle(cycle, x);
        cycle++;

      }
    }
    printCrt();
    System.out.println(crt.get(0).size());
    System.out.println(totalX);
  }


  public static void checkCycle(int cycleNumber, int x) {
    var moo = (cycleNumber -1) / 40;
    var myIndex = (cycleNumber - (moo * 40)) - 1;

    var index = x - 1;
    var spriteIsAt = List.of(index -1, index, index + 1);


    if(spriteIsAt.contains(myIndex)) {
      crt.get(moo).remove(myIndex);
      crt.get(moo).add(myIndex, "#");
    }

    if (cycleNumber == 20 || cycleNumber == 60 || cycleNumber == 100 || cycleNumber == 140
        || cycleNumber == 180 || cycleNumber == 220) {
      totalX += x * cycleNumber;
    }

  }

  public static void printCrt() {
    crt.forEach(System.out::println);
  }
}