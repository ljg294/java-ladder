package nextstep.ladder.src;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ui {
  private static final String SPACE = "     ";

  private final String bridge;
  private final String notBridge;

  public Ui(String bridge, String notBridge) {
    this.bridge = bridge;
    this.notBridge = notBridge;
  }

  public Ui(int size) {
    this(
      IntStream.range(0, size)
        .boxed()
        .map(i -> "-")
        .collect(Collectors.joining("")),
      IntStream.range(0, size)
        .boxed()
        .map(i -> " ")
        .collect(Collectors.joining(""))
    );
  }

  // public int minSize(int size) {
  //   if(size < 3) {
  //   }
  // }

  public String bridgeUi(boolean isBridge) {
    if(isBridge) {
      return bridge;
    }
    return notBridge;
  }

  public String floorUi(Floor floor) {
    String str = floor.floor()
      .stream()
      .map(bridge -> bridgeUi(bridge.isBridge()))
      .collect(Collectors.joining("|"));
    return "|" + str + "|";
  }

  public String ladderUi(Ladder ladder) {
    return ladder.ladder()
      .stream()
      .map(floor -> SPACE + floorUi(floor))
      .collect(Collectors.joining("\n"));
  }
}
