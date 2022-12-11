import java.math.BigInteger;

public class Operation {

  private final BigInteger size;
  private final String operator;

  public Operation(BigInteger size, String operator) {
    this.size = size;
    this.operator = operator;
  }

  public BigInteger doOp(BigInteger old) {
    if (operator.equals("+")) {
      return old.add(size);
    } else {
      if(size.equals(BigInteger.valueOf(-1))) {
        return old.multiply(old);
      } else {
        return old.multiply(size);
      }
    }
  }
}

