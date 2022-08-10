// refer: https://www.youtube.com/watch?v=EgG3jsGoPvQ&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=4

import java.util.Arrays;

class Recursion_FrogJump {
    // tc: O(2^N)
    // sc: O(N)
    public static void main(String[] args) {
        int[] heights = {30, 10, 60, 10, 60, 50};
        int n = heights.length;
        System.out.println(solve(n - 1, heights));
    }

    private static int solve(int index, int[] heights) {

        //base
        if (index == 0) return 0; //frog cannot jump if it has only 1 stair

        int left = solve(index - 1, heights) + Math.abs(heights[index] - heights[index - 1]);

        int right = Integer.MAX_VALUE;
        if (index > 1)
            right = solve(index - 2, heights) + Math.abs(heights[index] - heights[index - 2]);

        return Math.min(left, right);
    }
}

class Memoization_FrogJump {
    // tc: O(N)
    // sc: O(N) + O(N)
    public static void main(String[] args) {
        int[] heights = {30, 10, 60, 10, 60, 50};
        int n = heights.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println(solve(n - 1, heights, dp));
    }

    private static int solve(int index, int[] heights, int[] dp) {

        //base
        if (index == 0) return 0; //frog cannot jump if it has only 1 stair

        if (dp[index] != -1) return dp[index];
        int left = solve(index - 1, heights, dp) + Math.abs(heights[index] - heights[index - 1]);

        int right = Integer.MAX_VALUE;
        if (index > 1)
            right = solve(index - 2, heights, dp) + Math.abs(heights[index] - heights[index - 2]);

        return dp[index] = Math.min(left, right);
    }
}

class Tabulation_FrogJump {
    // tc: O(N)
    // sc: O(N)
    public static void main(String[] args) {
        int[] heights = {30, 10, 60, 10, 60, 50};
        int n = heights.length;
        int[] dp = new int[n];
        System.out.println(solve(n, heights, dp));
    }

    private static int solve(int n, int[] heights, int[] dp) {

        // base
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int firstStep = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            int secondStep = Integer.MAX_VALUE;
            if (i > 1)
                secondStep = dp[i - 2] + Math.abs(heights[i] = heights[i - 2]);

            dp[i] = Math.min(firstStep, secondStep);
        }

        return dp[n - 1];
    }
}

class Tabulation_FrogJump_SpaceOptimized {
    // tc: O(N)
    // sc: O(1)
    public static void main(String[] args) {
        int[] heights = {30, 10, 60, 10, 60, 50};
        int n = heights.length;
        System.out.println(solve(n, heights));
    }

    private static int solve(int n, int[] heights) {

        int previ = 0, prev2i = 0;
        for (int i = 1; i < n; i++) {
            int firstStep = previ + Math.abs(heights[i] - heights[i - 1]);
            int secondStep = Integer.MAX_VALUE;
            if (i > 1)
                secondStep = prev2i + Math.abs(heights[i] = heights[i - 2]);

            int curi = Math.min(firstStep, secondStep);
            prev2i = previ;
            previ = curi;
        }

        return previ;
    }
}