import java.util.Arrays;

// refer: https://www.youtube.com/watch?v=7win3dcgo3k&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY

// this question is similar as Subset_Sum_Equal_To_K
// difference is that if the totalSum of elements is odd then they can't have equal partitions, otherwise they can
// this is memoization solution if Subset_Sum_Equal_To_K

class Memoization_Partition_Equal_Subset_Sum {
    // tc: O(N*target) + O(N)
    // sc: O(N*target) + O(N)
    public static void main(String[] args) {
        int n = 6;
        int[] arr = {2, 2, 1, 1, 1, 1, 1, 3, 3}; // int[] arr = {3, 1, 1, 2, 2, 1};

        int totalSum = 0;
        for (int i : arr) totalSum += i;

        if (totalSum % 2 != 0) {
            System.out.println(false); // Can't create partitions
            return;
        }

        int target = totalSum / 2;

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