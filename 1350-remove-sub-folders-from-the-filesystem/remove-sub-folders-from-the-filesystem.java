class Solution {
    public List<String> removeSubfolders(String[] folder) {
        // Sort the folders lexicographically so parent folders come before their subfolders
        Arrays.sort(folder);

        // Initialize result list with the first folder
        List<String> result = new ArrayList<>();
        result.add(folder[0]);

        // Iterate through remaining folders starting from index 1
        for (int i = 1; i < folder.length; i++) {
            // Get the last added folder path and add a trailing slash
            String lastFolder = result.get(result.size() - 1) + "/";

            // Check if current folder starts with lastFolder
            // If it doesn't start with lastFolder, then it's not a subfolder
            if (!folder[i].startsWith(lastFolder))
                result.add(folder[i]);
        }

        return result;
    }
}