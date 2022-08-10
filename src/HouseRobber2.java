// this is similar to Maximum Sum of Non-Adjacent Elements question which was done previously
// the only difference is that the elements are in circle, so, 1st element and last element are adjacent.
// so we can't take 1st element if we take last element and vice versa.
// so we 1st run the code for -> all elements except last.
// then 2nd run the code for -> all elements except first.
// then return the max of these 2 callings.

// refer: https://takeuforward.org/data-structure/dynamic-programming-house-robber-dp-6/

import java.util.ArrayList;
import java.util.List;

// this code is of Maximum Sum of Non-Adjacent Elements space-optimization
class HouseRobber2 {
    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 2, 6};
        System.out.println(solve(nums));
    }

    private static int solve(int[] nums) {
        int n = nums.length;

        if (n == 1) return nums[0];

        List<Integer> temp1 = new ArrayList<>();
        List<Integer> temp2 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i != 0) temp1.add(nums[i]);
            if (i != n - 1) temp2.add(nums[i]);
        }

        return Math.max(helper(temp1), helper(temp2));
    }

    private static int helper(List<Integer> nums) {

        // base
        int previ = nums.get(0);
        int prev2i = 0;

        for (int i = 1; i < nums.size(); i++) {
            int pick = nums.get(i);
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
