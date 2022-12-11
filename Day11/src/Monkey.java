import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Monkey {

  private List<BigInteger> items;
  private final Operation operation;
  private final WorryTest test;
  private int numberOfInspects = 0;

  public Monkey(List<BigInteger> items, Operation operation, WorryTest test) {
    this.items = items;
    this.operation = operation;
    this.test = test;
  }

  public List<MonkeyPass> doRound(int factor) {
    var passes =  items.stream().map(item -> {
      numberOfInspects++;
      var worryLevel = item;
      worryLevel = operation.doOp(worryLevel);
//      worryLevel = worryLevel.divide(BigInteger.valueOf(3));

      var passMonkey = test.doTest(worryLevel);

      return new MonkeyPass(worryLevel.mod(BigInteger.valueOf(factor)), passMonkey);
    }).toList();
    this.items = new ArrayList<>();
    return passes;
  }

  public int getFactor() {
    return test.getSize();
  }

  public void give(BigInteger item) {
    items.add(item);
  }

  public BigInteger getNumberOfInspects() {
    return BigInteger.valueOf(numberOfInspects);
  }

}
