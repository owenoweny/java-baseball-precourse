package domains;

import exceptions.BaseballException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumbersTest {

    @Nested
    @DisplayName("객체 생성 테스트")
    class BeanCreationTest {
        @Test
        @DisplayName("유효한 입력일 경우, 유효한 객체를 반환한다.")
        void creation() {
            Numbers numbers = Numbers.of(List.of(1, 2, 3));
            assertThat(numbers)
                    .isNotNull();
        }

        @Test
        @DisplayName("배열의 크기가 유효하지 않은 경우 예외를 발생시킨다.")
        void wrongSize() {
            assertThatThrownBy(() -> Numbers.of(List.of(1, 2, 3, 4)))
                    .isInstanceOf(BaseballException.class)
                    .hasMessage("세 개의 숫자여야합니다.");
        }

        @Test
        @DisplayName("중복이 존재하는 경우 예외를 발생시킨다.")
        void hasDuplication() {
            assertThatThrownBy(() -> Numbers.of(List.of(1, 1, 3)))
                    .isInstanceOf(BaseballException.class)
                    .hasMessage("중복은 허용되지 않습니다.");
        }

        @Test
        @DisplayName("중복이 존재하는 경우 예외를 발생시킨다.")
        void wrongRange() {
            assertThatThrownBy(() -> Numbers.of(List.of(12, 0, 3)))
                    .isInstanceOf(BaseballException.class)
                    .hasMessage("1에서 9 사이의 숫자여야합니다.");
        }
    }

    @ParameterizedTest
    @MethodSource("provideNumbersAndScores")
    @DisplayName("점수 계산 테스트")
    void scoreCalculation(Numbers targetNumbers, Score expectedScore) {
        Numbers answers = Numbers.of(List.of(1, 2, 3));
        assertThat(answers.scoreOf(targetNumbers))
                .isEqualTo(expectedScore);
    }

    private static Stream<Arguments> provideNumbersAndScores() {
        return Stream.of(
                Arguments.of(Numbers.of(List.of(1, 2, 3)), new Score(3, 0)),
                Arguments.of(Numbers.of(List.of(4, 5, 6)), new Score(0, 0)),
                Arguments.of(Numbers.of(List.of(1, 3, 2)), new Score(1, 2)),
                Arguments.of(Numbers.of(List.of(2, 3, 1)), new Score(0, 3))
        );
    }
}