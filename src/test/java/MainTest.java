import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.io.*;
import java.util.stream.Stream;

@DisplayName("백준 문제 테스트")
public class MainTest {

    static final InputStream ORIGINAL_INPUT_STREAM = System.in;
    static final PrintStream ORIGINAL_OUTPUT_STREAM = System.out;
    ByteArrayOutputStream byteArrayOutputStream;

    @ParameterizedTest
    @MethodSource("Supply_Test_Source")
    @DisplayName("테스트 케이스 수행")
    void Test_Main(final String data, final String result) throws IOException {
        initInputStream(data);
        Main.main(new String[]{});
        Assertions.assertEquals(byteArrayOutputStream.toString(), result);
    }

    // 테스트 데이터, 정답
    static Stream<Arguments> Supply_Test_Source() {
        return Stream.of(
                Arguments.of("3\n" +
                        "0 3\n" +
                        "1 5\n" +
                        "45 50",
                        "3\n" +
                        "3\n" +
                        "4")
        );
    }

    @AfterEach
    public void restoreInputStream() {
        System.setIn(ORIGINAL_INPUT_STREAM);
        System.setOut(ORIGINAL_OUTPUT_STREAM);
    }

    void initInputStream(final String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @BeforeEach
    void setOutputStream() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }
}
