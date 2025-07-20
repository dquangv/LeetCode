class Solution {
    // Node class represents a folder and its children (subfolders)
    static class Node {
        String name; // Folder name
        TreeMap<String, Node> children; // Sorted map of child folders
        String signature; // Encoded structure of subfolders (used for duplicate detection)

        public Node(String name) {
            this.name = name;
            this.children = new TreeMap<>();
        }
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Node root = new Node(""); // Root node of the folder tree

        // Step 1: Build the folder tree from the input paths
        for (List<String> path : paths) {
            Node curr = root;
            for (String folder : path) {
                curr.children.putIfAbsent(folder, new Node(folder));
                curr = curr.children.get(folder);
            }
        }

        // Step 2: Traverse tree and build signatures to detect duplicates
        Map<String, Integer> countMap = new HashMap<>(); // Map from signature -> count of occurrences
        dfs(root, countMap); // Populates node.signature and fills countMap

        // Step 3: Traverse tree again and build result while skipping duplicates
        List<List<String>> result = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();
        for (Node child : root.children.values())
            dfs2(child, currentPath, result, countMap);
        
        return result;
    }

    // DFS 1: Compute signature for each node (used to identify duplicates)
    private void dfs(Node node, Map<String, Integer> countMap) {
        if (node.children.isEmpty()) {
            node.signature = ""; // Leaf folder has empty signature
            return;
        }

        // Build signature from children's signatures recursively
        StringBuilder sb = new StringBuilder();
        for (Node child : node.children.values()) {
            dfs(child, countMap); // Recursive DFS to get child signatures
            sb.append(child.name).append('(').append(child.signature).append(')');
        }

        node.signature = sb.toString(); // Unique string representing subtree structure
        countMap.put(node.signature, countMap.getOrDefault(node.signature, 0) + 1); // Count occurrence
    }

    // DFS 2: Traverse and collect valid folders that are not duplicates
    private void dfs2(Node node, List<String> currentPath, List<List<String>> result, Map<String, Integer> countMap) {
        // Skip nodes with duplicate subtree structures (and their subfolders)
        if (!node.children.isEmpty() && countMap.getOrDefault(node.signature, 0) >= 2)
            return;

        // Include current node in path
        currentPath.add(node.name);
        result.add(new ArrayList<>(currentPath)); // Add a copy to result

        // Traverse children recursively
        for (Node child : node.children.values()) 
            dfs2(child, currentPath, result, countMap);

        // Backtrack to explore other paths
        currentPath.remove(currentPath.size() - 1);
    }
}