import java.util.ArrayList;
import java.util.List;

public class Forest {

  private final List<List<Tree>> trees = new ArrayList<>();

  public void addTreeLine(List<Tree> treeLine) {
    this.trees.add(treeLine);
  }

  public int countVisible() {
    int count = 0;
    for (int i = 0; i < trees.size(); i++) {
      for (int j = 0; j < trees.get(i).size(); j++) {

        if ((i - 1 < 0) || (j - 1 < 0)) {
          count++;
        } else if ((i + 1 >= trees.size()) || (j + 1 >= trees.get(i).size())) {
          count++;
        } else {

          var top = new ArrayList<Boolean>();
          var bottom = new ArrayList<Boolean>();
          var left = new ArrayList<Boolean>();
          var right = new ArrayList<Boolean>();
          var myHeight = trees.get(i).get(j).getHeight();

          for (int k = i - 1; k >= 0; k--) {
            var above = trees.get(k).get(j).getHeight();
            top.add(above < myHeight);
          }

          for (int k = i + 1; k < trees.get(i + 1).size(); k++) {
            var above = trees.get(k).get(j).getHeight();
            bottom.add(above < myHeight);
          }

          for (int k = j - 1; k >= 0; k--) {
            var above = trees.get(i).get(k).getHeight();
            left.add(above < myHeight);
          }

          for (int k = j + 1; k < trees.get(i).size(); k++) {
            var above = trees.get(i).get(k).getHeight();
            right.add(above < myHeight);
          }

          if (top.stream().allMatch(m -> m) || bottom.stream().allMatch(m -> m) || left.stream()
              .allMatch(m -> m) || right.stream().allMatch(m -> m)) {
            count++;
          }
        }
      }
    }
    return count;
  }

  public int countViewing() {
    int max = 0;
    for (int i = 0; i < trees.size(); i++) {
      for (int j = 0; j < trees.get(i).size(); j++) {

        var top = 0;
        var bottom = 0;
        var left = 0;
        var right = 0;
        var myHeight = trees.get(i).get(j).getHeight();

        if (i - 1 >= 0) {
          for (int k = i - 1; k >= 0; k--) {
            var above = trees.get(k).get(j).getHeight();
            if (above < myHeight) {
              top++;
            } else {
              top ++;
              break;
            }
          }
        }

        if (i + 1 < trees.size()) {

          for (int k = i + 1; k < trees.get(i + 1).size(); k++) {
            var above = trees.get(k).get(j).getHeight();
            if (above < myHeight) {
              bottom++;
            }else {
              bottom ++;
              break;
            }
          }
        }

        if (j - 1 >= 0) {

          for (int k = j - 1; k >= 0; k--) {
            var above = trees.get(i).get(k).getHeight();
            if (above < myHeight) {
              left++;
            }else {
              left ++;
              break;
            }
          }
        }

        if (j + 1 < trees.get(i).size()) {
          for (int k = j + 1; k < trees.get(i).size(); k++) {
            var above = trees.get(i).get(k).getHeight();
            if (above < myHeight) {
              right++;
            }else {
              right ++;
              break;
            }
          }
        }

        var currentScore = top * bottom * left * right;

        if(currentScore > max) {
          max = currentScore;
        }
      }
    }
    return max;
  }

}
