// refer: https://www.youtube.com/watch?v=mLfjzJsN8us&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=3

import java.util.Arrays;

class Recursion_Fibonacci {
    // tc: O(2^N)
    // sc: O(N) - recursive call stack
    public static void main(String[] args) {
        int n = 7;
        System.out.println(solve(n));
    }

    private static int solve(int n) {
        if (n <= 1) return n;
        return solve(n - 1) + solve(n - 2);
    }
}

class Memoization_Fibonacci {
    // tc: O(N)
    // sc: O(N) + O(N)
    public static void main(String[] args) {
        int n = 7;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println(solve(n, dp));
    }

    private static int solve(int n, int[] dp) {
        if (n <= 1) return n;
        if (dp[n] != -1) return dp[n];
        return dp[n] = solve(n - 1, dp) + solve(n - 2, dp);
    }
}

class Tabulation_Fibonacci {
    // tc: O(N)
    // sc: O(N)
    public static void main(String[] args) {
        int n = 7;
        int[] dp = new int[n + 1];
        System.out.println(solve(n, dp));
    }

    private static int solve(int n, int[] dp) {
        // base
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[n];
    }
}

class Tabulation_Fibonacci_SpaceOptimized {
    // tc: O(N)
    // sc: O(1)
    // this is space optimization
    public static void main(String[] args) {
        int n = 7;
        System.out.println(solve(n));
    }

    private static int solve(int n) {

        // edge case
        if(n <= 1) return n;

        int prev2i = 0, previ = 1;
        for (int i = 2; i <= n; i++) {
            int curi = previ + prev2i;
            prev2i = previ;
            previ = curi;
        }

        return previ;
    }
}
