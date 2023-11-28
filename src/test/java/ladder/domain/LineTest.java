package ladder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LineTest {

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    @DisplayName("참여자 만큼 라인 포인트 생성")
    public void createLinePointTest(int countOfPerson) throws Exception {
        Line line = new Line(countOfPerson);
        int size = line.readOnlyPoints().size();
        assertThat(size).isEqualTo(countOfPerson);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    @DisplayName("참여자 2명 미만 일시 에러")
    public void personTwoUnderException(int countOfPersion) throws Exception {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Line(countOfPersion);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    @DisplayName("마지막은 포인트는 false 로 끝나야한다.")
    public void islastPointFalseTest(int countOfPerson) throws Exception {
        Line line = new Line(countOfPerson);
        List<Boolean> points = line.readOnlyPoints();
        assertThat(points.get(points.size() - 1)).isFalse();
    }

    @Test
    @DisplayName("모든 포인트 False 확인")
    public void allPointFalseTest() throws Exception {
        Line line = new Line(5, () -> false);
        boolean result = line.readOnlyPoints().stream()
                .noneMatch(p -> p);
        assertThat(result).isTrue();
    }
}