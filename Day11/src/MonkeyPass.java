import java.math.BigInteger;

public class MonkeyPass {
  private final BigInteger item;
  private final int toMonkey;

  public MonkeyPass(BigInteger item, int toMonkey) {
    this.item = item;
    this.toMonkey = toMonkey;
  }

  public BigInteger getItem() {
    return item;
  }

  public int getToMonkey() {
    return toMonkey;
  }
}
