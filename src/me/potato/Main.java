package me.potato;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    int totalDuration = 10;
    // duration seconds
    List<Watch> Watches = new ArrayList<>();
    Watches.add(new Watch(0, 4));
    Watches.add(new Watch(8, 9));


    System.out.println(getProgressRate(totalDuration, Watches) / totalDuration * 100);
  }


  private static double getProgressRate(double totalDuration, List<Watch> Watches) {
    double result = Watches.stream()
            .map(Watch -> BigInteger.TWO.pow(Watch.end - Watch.start + 1).subtract(BigInteger.ONE)
                    .shiftLeft(Watch.start))
            .reduce(BigInteger.ZERO, BigInteger::or).bitCount()  ;

    return result  ;
  }

  static class Watch {
    private final int start;
    private final int end;

    public Watch(int start, int end) {
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