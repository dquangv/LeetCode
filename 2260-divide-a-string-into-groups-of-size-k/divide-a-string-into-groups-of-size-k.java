class Solution {
    public String[] divideString(String s, int k, char fill) {
        int size = (s.length() + k - 1) / k;
        String[] result = new String[size];

        for (int i = 0, idx = 0; i < s.length(); i += k, idx++) {
            int end = Math.min(i + k, s.length());
            String group = s.substring(i, end);

            if (group.length() < k) {
                StringBuilder sb = new StringBuilder(group);
                while (sb.length() < k)
                    sb.append(fill);
                group = sb.toString();
            }

            result[idx] = group;
        }

        return result;
    }
}