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
                Arguments.of("2\n" +
                        "4 4\n" +
                        "10 1 100 10\n" +
                        "1 2\n" +
                        "1 3\n" +
                        "2 4\n" +
                        "3 4\n" +
                        "4\n" +
                        "8 8\n" +
                        "10 20 1 5 8 7 1 43\n" +
                        "1 2\n" +
                        "1 3\n" +
                        "2 4\n" +
                        "2 5\n" +
                        "3 6\n" +
                        "5 7\n" +
                        "6 7\n" +
                        "7 8\n" +
                        "7",
                        "120\n" + "39"),
                Arguments.of("5\n" +
                        "3 2\n" +
                        "1 2 3\n" +
                        "3 2\n" +
                        "2 1\n" +
                        "1\n" +
                        "4 3\n" +
                        "5 5 5 5\n" +
                        "1 2\n" +
                        "1 3\n" +
                        "2 3\n" +
                        "4\n" +
                        "5 10\n" +
                        "100000 99999 99997 99994 99990\n" +
                        "4 5\n" +
                        "3 5\n" +
                        "3 4\n" +
                        "2 5\n" +
                        "2 4\n" +
                        "2 3\n" +
                        "1 5\n" +
                        "1 4\n" +
                        "1 3\n" +
                        "1 2\n" +
                        "4\n" +
                        "4 3\n" +
                        "1 1 1 1\n" +
                        "1 2\n" +
                        "3 2\n" +
                        "1 4\n" +
                        "4\n" +
                        "7 8\n" +
                        "0 0 0 0 0 0 0\n" +
                        "1 2\n" +
                        "1 3\n" +
                        "2 4\n" +
                        "3 4\n" +
                        "4 5\n" +
                        "4 6\n" +
                        "5 7\n" +
                        "6 7\n" +
                        "7", "6\n" +
                        "5\n" +
                        "399990\n" +
                        "2\n" +
                        "0")
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
