import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SolutionTest {

    private static Solution solution = new Solution();

    @DisplayName("测试开始")
    @Test
    public void main() {
        System.out.println("test start");
    }

    @DisplayName("数组测试")
    @Test
    public void arrayTest(){
        int[] input = new int[]{1,4,0};
        int res = solution.maxProfit(input);
        System.out.println(res);
    }
}