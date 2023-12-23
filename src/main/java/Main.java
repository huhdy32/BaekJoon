import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int testCount = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();
        Collection<int[]> stack = new Stack<>();
        for (int i = 0; i < testCount; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            answer.append(getAlpha(input[0], input[1]));
            if (i != testCount -1 ) {
                answer.append("\n");
            }
        }
        System.out.print(answer);
    }

    private static long getAlpha(int a, int b) {
        int n = b - a;
        if (n == 1) return 1;
        if (n == 2) return 2;

        long root = (long) Math.sqrt(n);
        if (root * root != n) root++;
        if (n > (root * root + (root - 1) * (root - 1)) / 2) {
            return 2 * root - 1;
        }
        return 2 * root - 2;

    }
}


