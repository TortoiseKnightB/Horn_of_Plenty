
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int diff = 0;
        // 至少要2天才能赚钱
        if (prices.length <= 1) {
            return 0;
        }
        // 后一天比前一天价格高则赚钱，所以只要计算每天股票价格的差值，保留正差值即可
        for (int index = 1; index < prices.length; index++) {
            diff = prices[index] - prices[index - 1];
            profit += diff > 0 ? diff : 0;
        }
        return profit;
    }
}

