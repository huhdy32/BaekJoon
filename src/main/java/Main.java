import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        // 테스트 케이스 갯수
        int testCount = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < testCount; testCase++) {
            // 건물 갯수, 관계 갯수
            int[] temp = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int relationSize = temp[1];
            int[] buildingCosts = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            ArrayList<int[]> relations = new ArrayList<>();
            // 관계 읽기
            for (int i = 0; i < relationSize; i++) {
                relations.add(Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .map(o -> o - 1)
                        .toArray());
            }
            // 목표 건물
            int target = Integer.parseInt(br.readLine()) - 1;
            answer.append(getCost(buildingCosts, relations, target));
            if (testCase != testCount -1 ) {
                answer.append("\n");
            }
        }
        System.out.print(answer);
    }

    private static int getCost(int[] buildingCosts, ArrayList<int[]> relations, int target) {
        int[] topology = new int[buildingCosts.length];
        int[] requireTime = new int[buildingCosts.length];
        for (int i = 0; i < relations.size(); i++) {
            int[] temp = relations.get(i);
            topology[temp[1]]++;
        }
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < topology.length; i++) {
            if (topology[i] == 0) {
                topology[i] = -1;
                q.add(new int[]{i, 0});
            }
        }
        while (q.size() != 0) {
            int[] curr = q.poll();
            if (curr[0] == target) {
                return curr[1] + buildingCosts[curr[0]]; // 너가 지금까지 쓴 시간
            }
            for (int i = 0; i < relations.size(); i++) {
                int left = relations.get(i)[0];
                int right = relations.get(i)[1];
                if (left == curr[0]) {
                    topology[right]--;
                    requireTime[right] = Math.max(requireTime[right], curr[1] + buildingCosts[left]);
                }
                if (topology[right] == 0) {
                    topology[right] = -1;
                    q.add(new int[]{right, requireTime[right]});
                }
            }
            relations.removeIf(relation -> relation[0] == curr[0]);
        }
        return 0;
    }
}


