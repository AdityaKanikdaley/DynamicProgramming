// refer: https://takeuforward.org/data-structure/dynamic-programming-introduction/
// this problem is just like fibonacci, but slightly base values changed

/*
 base cases: 1) if we have n=0 -> we can't jump so 1 way.
             2) if we have n=1 -> we have only 1 way ie, 0->1, so 1 way.
             this is why we write here -> dp[0]=1, dp[1]=1 & a=1, b=1
*/

class Tabulation_ClimbingStairs {
    // tc: O(N)
    // sc: O(N)
    public static void main(String[] args) {
        int n = 3;
        System.out.println(solve(n));
    }

    private static int solve(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}

class Tabulation_ClimbingStairs_SpaceOptimized {
    // tc: O(N)
    // sc: O(1)
    // this is space optimization
    public static void main(String[] args) {
        int n = 3;
        System.out.println(solve(n));
    }

    private static int solve(int n) {
        int prev2i = 1, previ = 1;
        for (int i = 2; i <= n; i++) {
            int curi = previ + prev2i;
            prev2i = previ;
            previ = curi;
        }

        return previ;
    }
}