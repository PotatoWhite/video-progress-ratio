package me.potato;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) {
    int totalDuration = 10;
    // duration seconds
    List<View> views = new ArrayList<>();
    views.add(new View(0, 4));
    views.add(new View(8, 9));


    System.out.println(getProgressRatio(totalDuration, views) / totalDuration * 100);
  }


  private static double getProgressRatio(double totalDuration, List<View> views) {
    double result = views.stream()
            .map(view -> BigInteger.TWO.pow(view.end - view.start + 1).subtract(BigInteger.ONE)
                    .shiftLeft(view.start))
            .reduce(BigInteger.ZERO, BigInteger::or).bitCount()  ;

    return result  ;
  }

  static class View {
    private final int start;
    private final int end;

    public View(int start, int end) {
      if (start > end) {
        this.start = end;
        this.end   = start;
      } else {
        this.start = start;
        this.end   = end;
      }
    }

    @Override
    public String toString() {
      return "View{" +
              "start=" + start +
              ", end=" + end +
              '}';
    }
  }
}
;