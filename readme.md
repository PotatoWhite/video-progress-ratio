
# Video 시청 기록을 통해 얼마나 봤는지 확인하는 로직

- 실제 본 시청 구간정보를 다음과 같이 저장을한다.
```java
    Watches.add(new Watch(0, 4));
    Watches.add(new Watch(8, 9));
```


- 각 구간을을 합친다.
```java
private static double getProgressRate(double totalDuration, List<Watch> Watches) {
    double result = Watches.stream()
            .map(Watch -> BigInteger.TWO.pow(Watch.end - Watch.start + 1).subtract(BigInteger.ONE)
                    .shiftLeft(Watch.start))
            .reduce(BigInteger.ZERO, BigInteger::or).bitCount()  ;

    return result  ;
  }
```