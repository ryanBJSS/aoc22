import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonkeyParser {

  public static Monkey parse(List<String> rawMonkey) {

    var itemLine = rawMonkey.get(1);
    var operationLine = rawMonkey.get(2);
    var testDivisibleLine = rawMonkey.get(3);
    var testTrueLine = rawMonkey.get(4);
    var testFalseLine = rawMonkey.get(5);

    Pattern digitRegex = Pattern.compile("\\d+");

    Matcher findItemMatcher = digitRegex.matcher(itemLine);
    List<BigInteger> items = new ArrayList<>();
    while (findItemMatcher.find()) {
      items.add(BigInteger.valueOf(Integer.parseInt(findItemMatcher.group())));
    }

    Matcher findOperationMatcher = digitRegex.matcher(operationLine);
    var foundOp = findOperationMatcher.find();
    BigInteger operationSize;
    if(foundOp) {
      operationSize = BigInteger.valueOf(Integer.parseInt(findOperationMatcher.group()));
    } else {
      operationSize = BigInteger.valueOf(-1);
    }

    var operationOperator = operationLine.contains("+") ? "+" : "*";
    var operation = new Operation(operationSize, operationOperator);

    Matcher findDivisibleLine = digitRegex.matcher(testDivisibleLine);
    findDivisibleLine.find();
    var divisibleSize  = Integer.parseInt(findDivisibleLine.group());

    Matcher findTestTrueLine = digitRegex.matcher(testTrueLine);
    findTestTrueLine.find();
    var testTrue  = Integer.parseInt(findTestTrueLine.group());

    Matcher findTestFalseLine = digitRegex.matcher(testFalseLine);
    findTestFalseLine.find();
    var testFalse  = Integer.parseInt(findTestFalseLine.group());

    var test = new WorryTest(divisibleSize, testTrue, testFalse);

    return new Monkey(items, operation, test);


  }
}
