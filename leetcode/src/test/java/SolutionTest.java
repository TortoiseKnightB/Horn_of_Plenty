import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SolutionTest {

    private static Solution solution = new Solution();

    @DisplayName("测试开始")
    @Test
    public void main() {
        System.out.println("test start");
        solution.hello();
    }
}