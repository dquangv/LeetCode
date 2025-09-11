class Solution {
    public String sortVowels(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList(
                'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        // Step 1: Extract vowels
        List<Character> vowelList = new ArrayList<>();
        char[] arr = s.toCharArray();
        for (char ch : arr)
            if (vowels.contains(ch))
                vowelList.add(ch);

        // Step 2: Sort vowels by ASCII
        Collections.sort(vowelList);

        // Step 3: Replace vowels with sorted ones
        int idx = 0;
        for (int i = 0; i < arr.length; i++)
            if (vowels.contains(arr[i]))
                arr[i] = vowelList.get(idx++);

        // Step 4: Return result
        return new String(arr);
    }
}