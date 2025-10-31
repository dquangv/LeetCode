class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int[] arr = new int[2];
        int k = 0;

        for (int i = 0; i < nums.length; i++) {
            int c = 0;
            for (int j = i; j < nums.length; j++) 
                if (nums[i] == nums[j]) 
                    c++;
                
            
            if (c == 2) {
                arr[k] = nums[i];
                k++;
            }

            if (k == 2)
                break;
        }
        
        return arr;
    }
}