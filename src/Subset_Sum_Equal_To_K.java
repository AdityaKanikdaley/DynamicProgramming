import java.util.Arrays;

// refer: https://www.youtube.com/watch?v=fWX9xDmIzRI&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY

class Recursion_Subset_Sum_Equal_To_K {
    // tc: 2^N
    // sc: 2^N
    public static void main(String[] args) {
        int n = 4;
        int[] arr = {1, 2, 3, 4};
        int target = 4;
        System.out.println(solve(n - 1, arr, target));
    }

    private static boolean solve(int index, int[] arr, int target) {

        //base
        if (target == 0) return true; //already achieved target
        if (index == 0)
            return (target == arr[0]); //if there is only 1 element, arr[0], then we can take that element only if it = target

        boolean notTake = solve(index - 1, arr, target);
        boolean take = false;
        if (target >= arr[index]) //take only if target >= current element
            take = solve(index - 1, arr, target - arr[index]);

        return notTake || take; //here took OR as we want to see (if their is a possibility of getting target)
    }
}

class Memoization_Subset_Sum_Equal_To_K {
    // tc: O(N*target)
    // sc: O(N*target) + O(N)
    public static void main(String[] args) {
        int n = 4;
        int[] arr = {1, 2, 3, 4};
        int target = 4;
        int[][] dp = new int[n + 1][target + 1];
        for (int[] a : dp)
            Arrays.fill(a, -1);

        System.out.println(solve(n - 1, arr, target, dp));
    }

    private static boolean solve(int index, int[] arr, int target, int[][] dp) {

        //base
        if (target == 0) return true; //already achieved target
        if (index == 0)
            return (target == arr[0]); //if there is only 1 element, arr[0], then we can take that element only if it = target
        if (dp[index][target] != -1) return dp[index][target] != 0;

        boolean notTake = solve(index - 1, arr, target, dp);
        boolean take = false;
        if (target >= arr[index]) //take only if target >= current element
            take = solve(index - 1, arr, target - arr[index], dp);

        dp[index][target] = (notTake || take) ? 1 : 0;
        return notTake || take; //here took OR as we want to see (if their is a possibility of getting target)
    }
}

class Tabulation_Subset_Sum_Equal_To_K {
    // tc: O(N*target)
    // sc: O(N*target)
    public static void main(String[] args) {
        int n = 4;
        int[] arr = {1, 2, 3, 4};
        int target = 4;
        boolean[][] dp = new boolean[n + 1][target + 1];

        System.out.println(solve(n - 1, arr, target, dp));
    }

    private static boolean solve(int n, int[] arr, int k, boolean[][] dp) {

        for (int i = 0; i < n; i++) dp[i][0] = true; // base case for -> if(target == 0) return true;
        dp[0][arr[0]] = true; // base case for -> if(index == 0) return (target == arr[0]);

        for (int index = 1; index < n; index++) {
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[index - 1][target];
                boolean take = false;
                if (target >= arr[index]) //take only if target >= current element
                    take = dp[index - 1][target - arr[index]];

                dp[index][target] = notTake || take;
            }
        }

        return dp[n - 1][k];
    }
}

class Tabulation_Subset_Sum_Equal_To_K_SpaceOptimized {
    // tc: O(N*target)
    // sc: O(target)
    public static void main(String[] args) {
        int n = 5;
        int[] arr = {1, 2, 3, 4};
        int target = 4;
        boolean[][] dp = new boolean[n + 1][target + 1];

        System.out.println(solve(n - 1, arr, target, dp));
    }

    private static boolean solve(int n, int[] arr, int k, boolean[][] dp) {

        boolean[] prev = new boolean[k + 1];
        boolean[] cur = new boolean[k + 1];

        prev[0] = cur[0] = true; // base case for -> if(target == 0) return true;
        prev[arr[0]] = true; // base case for -> if(index == 0) return (target == arr[0]);

        for (int index = 1; index < n; index++) {
            for (int target = 1; target <= k; target++) {
                boolean notTake = prev[target];
                boolean take = false;
                if (target >= arr[index]) //take only if target >= current element
                    take = prev[target - arr[index]];

                cur[target] = notTake || take;
            }
            prev = cur;
        }

        return prev[k];
    }
}