class Solution {
public:
    int maximumCount(vector<int>& nums) {
        return max(lower_bound(nums.begin(), nums.end(), 0) - nums.begin(),
                   nums.end() - lower_bound(nums.begin(), nums.end(), 1));
    }
};