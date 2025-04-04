/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func lcaDeepestLeaves(root *TreeNode) *TreeNode {
    if root == nil {
		return nil;
	}

	type Pair struct {
		Node *TreeNode;
		Parent *TreeNode;
	}

	queue := list.New();
	queue.PushBack(Pair{root, nil});

    parentMap := make(map[int]int);
    nodeMap := make(map[int]*TreeNode);

    var levels [][]int;

	for queue.Len() > 0 {
		size := queue.Len();
		level := make([]int, 0, size);

		for i := 0; i < size; i++ {
			front := queue.Remove(queue.Front()).(Pair);
			node := front.Node;
			parent := front.Parent;

			val := node.Val;
			parentVal := -1;
			if parent != nil {
				parentVal = parent.Val;
			}

			level = append(level, val);
			parentMap[val] = parentVal;
			nodeMap[val] = node;

			if node.Left != nil {
				queue.PushBack(Pair{node.Left, node});
			}

			if node.Right != nil {
				queue.PushBack(Pair{node.Right, node});
			}
		}

		levels = append(levels, level);
	}

    lastLevel := levels[len(levels)-1];
	if len(lastLevel) == 1 {
		return nodeMap[lastLevel[0]];
	}

	s, t := lastLevel[0], lastLevel[len(lastLevel)-1];

	for s != t {
		s = parentMap[s];
		t = parentMap[t];
	}

	return nodeMap[s];
}