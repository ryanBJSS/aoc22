import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) throws URISyntaxException, IOException {
    Path path = Paths.get(Objects.requireNonNull(Main.class.getClassLoader()
        .getResource("input.txt")).toURI());


    var inputLines = Files.lines(path).toList();

    var monkeyLine = Arrays.stream(String.join("\n", inputLines).split("\\n\n")).toList();

    var monkeys = new ArrayList<>(monkeyLine.stream().map(line -> {

      var list = Arrays.stream(line.split("\\n")).map(String::trim).toList();
      return MonkeyParser.parse(list);
    }).toList());

    int factor = monkeys.stream().map(Monkey::getFactor).reduce(1, (memo, monkey) -> memo * monkey);

    IntStream.rangeClosed(1, 10000).boxed().toList().forEach( out -> {
          for (var monkey: monkeys) {
            var passes = monkey.doRound(factor);
            passes.forEach(pass -> monkeys.get(pass.getToMonkey()).give(pass.getItem()));
          }
        });

    monkeys.sort(Comparator.comparing(Monkey::getNumberOfInspects));

    System.out.println(monkeys.get(monkeys.size() - 1).getNumberOfInspects().multiply( monkeys.get(monkeys.size() - 2).getNumberOfInspects()));
  }
}