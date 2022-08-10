// refer: https://www.youtube.com/watch?v=GrMBfJNk_NY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=6

import java.util.Arrays;

class Recursion_MaximumSumOfNonAdjacentElements {
    // tc: O(2^N)
    // sc: O(N)
    public static void main(String[] args) {
        int[] nums = {2, 1, 4, 9};
        System.out.println(solve(nums.length - 1, nums));
    }

    private static int solve(int index, int[] nums) {

        // base
        if (index == 0) return nums[index];
        if (index < 0) return 0;

        int pick = nums[index] + solve(index - 2, nums);
        int notPick = 0 + solve(index - 1, nums);

        return Math.max(pick, notPick);
    }
}

class Memoization_MaximumSumOfNonAdjacentElements {
    // tc: O(N)
    // sc: O(N) + O(N)
    public static void main(String[] args) {
        int[] nums = {2, 1, 4, 9};
        int n = nums.length;

        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        System.out.println(solve(n - 1, nums, dp));
        System.out.println(Arrays.toString(dp));
    }

    private static int solve(int index, int[] nums, int[] dp) {

        // base
        if (index == 0) return nums[index];
        if (index < 0) return 0;

        if (dp[index] != -1) return dp[index];

        int pick = nums[index] + solve(index - 2, nums, dp);
        int notPick = 0 + solve(index - 1, nums, dp);

        return dp[index] = Math.max(pick, notPick);
    }
}

class Tabulation_MaximumSumOfNonAdjacentElements {
    // tc: O(N)
    // sc: O(N)
    public static void main(String[] args) {
        int[] nums = {2, 1, 4, 9};
        int n = nums.length;

        int[] dp = new int[n];
        System.out.println(solve(nums, dp));
        System.out.println(Arrays.toString(dp));
    }

    private static int solve(int[] nums, int[] dp) {

        // base
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int pick = nums[i];
            if (i > 1) pick += dp[i - 2];
            int notPick = 0 + dp[i - 1];

            dp[i] = Math.max(pick, notPick);
        }
        return dp[nums.length - 1];
    }
}

class Tabulation_MaximumSumOfNonAdjacentElements_SpaceOptimized {
    // tc: O(N)
    // sc: O(1)
    // this is space optimization
    public static void main(String[] args) {
        int[] nums = {2, 1, 4, 9};
        System.out.println(solve(nums));
    }

    private static int solve(int[] nums) {

        // base
        int previ = nums[0];
        int prev2i = 0;

        for (int i = 1; i < nums.length; i++) {
            int pick = nums[i];
            if (i > 1)
                pick += prev2i;
            int notPick = 0 + previ;

            int curi = Math.max(pick, notPick);

            prev2i = previ;
            previ = curi;
        }

        return previ;
    }
}