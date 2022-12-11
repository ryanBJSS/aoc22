import java.math.BigInteger;

public class WorryTest {

  private final int size;
  private final int trueMonkey;
  private final int falseMonkey;

  public WorryTest(int testDivisible, int trueMonkey, int falseMonkey) {
    this.size = testDivisible;
    this.trueMonkey = trueMonkey;
    this.falseMonkey = falseMonkey;
  }

  public int doTest(BigInteger input) {
    if(input.mod(BigInteger.valueOf(size)).equals(BigInteger.valueOf(0))) {
      return trueMonkey;
    } else {
      return falseMonkey;
    }
  }

  public int getSize() {
    return size;
  }
}
