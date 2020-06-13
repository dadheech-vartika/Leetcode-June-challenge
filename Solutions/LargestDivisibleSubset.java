/*
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:

Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

Input: [1,2,3]
Output: [1,2] (of course, [1,3] will also be ok)
Example 2:

Input: [1,2,4,8]
Output: [1,2,4,8]
*/


//Solution:


class Solution(object):
    def largestDivisibleSubset(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        n = len(nums)
        if n<=1:
            return nums
        nums.sort()
        dp = [(0,0)] * n
        dp[0] = (1,0)
        manIndex, maxVal = 0,1
        for i in range(1,n):
            dp[i] = max((dp[j][0] + 1, j) for j in range(i + 1) if nums[i] % nums[j] is 0)
            if dp[i] > maxVal:
                maxIndex, maxVal = i, dp[i]
        i, lds = maxIndex, [nums[maxIndex]]
        while i!= dp[i][1]:
            i = dp[i][1]
            lds.append(nums[i])
        return lds
