package me.potato;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) {
    int totalDuration = 60 * 60 * 60; // 60 시간 짜리 쌉무식 동영상
    // duration seconds
    List<View> views = new ArrayList<>();
    views.add(new View(0, 100));
    System.out.println(getProgressRatio(totalDuration, views));

    views.add(new View(0, 100));
    views.add(new View(0, 100));
    System.out.println(getProgressRatio(totalDuration, views));

    IntStream.range(100, 200).forEach((aNum) -> views.add(new View(aNum, 200)));
    System.out.println(getProgressRatio(totalDuration, views));

    IntStream.range(0, 100).forEach((aNum) -> views.add(new View(0, 200)));
    System.out.println(getProgressRatio(totalDuration, views));

    IntStream.range(100, 200).forEach((aNum) -> views.add(new View(aNum, 200)));
    System.out.println(getProgressRatio(totalDuration, views));

    IntStream.range(0, totalDuration / 2).forEach((aNum) -> views.add(new View(0, aNum)));
    System.out.println(getProgressRatio(totalDuration, views));

  }

  private static double getProgressRatio(double totalDuration, List<View> views) {
    // 남자는 올림이다.
    return Math.ceil(views.stream()
            .map(view -> BigInteger.TWO.pow(view.end - view.start).subtract(BigInteger.ONE).shiftLeft(view.start))
            .reduce(BigInteger.ZERO, (view1, view2) -> view1.or(view2)).bitCount() / totalDuration * 100);
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
  }
}
;