class Solution {
    public String countAndSay(int n) {
        if (n == 1)
            return "1";

        String pre = countAndSay(n - 1);
        StringBuilder cur = new StringBuilder();
        int count = 1;

        for (int j = 1; j < pre.length(); j++) {
            if (pre.charAt(j) == pre.charAt(j - 1)) {
                count++;
            } else {
                cur.append(count).append(pre.charAt(j - 1));
                count = 1;
            }
        }

        cur.append(count).append(pre.charAt(pre.length() - 1));
        pre = cur.toString();

        return pre;
    }
}