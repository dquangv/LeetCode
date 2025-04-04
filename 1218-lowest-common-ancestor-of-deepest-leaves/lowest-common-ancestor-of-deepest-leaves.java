/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) return null;
        
        Queue<TreeNode[]> queue = new LinkedList<>();
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        Map<Integer, Integer> parentMap = new HashMap<>();

        queue.add(new TreeNode[] { root, null });
        List<List<Integer>> levels = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode[] pair = queue.poll();
                TreeNode node = pair[0], parent = pair[1];
                
                int x = node.val;
                int y = (parent == null) ? -1 : parent.val;
                
                level.add(x);
                parentMap.put(x, y);
                nodeMap.put(x, node);
                
                if (node.left != null) queue.add(new TreeNode[] { node.left, node });
                if (node.right != null) queue.add(new TreeNode[] { node.right, node });
            }

            levels.add(level);
        }

        List<Integer> lastLevel = levels.get(levels.size() - 1);
        if (lastLevel.size() == 1) return nodeMap.get(lastLevel.get(0));

        int s = lastLevel.get(0), t = lastLevel.get(lastLevel.size() - 1);
        while (s != t) {
            s = parentMap.get(s);
            t = parentMap.get(t);
        }

        return nodeMap.get(s);
    }
}