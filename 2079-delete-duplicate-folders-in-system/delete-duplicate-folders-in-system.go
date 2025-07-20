type Trie struct {
	children map[string]*Trie
}

func NewTrie() *Trie {
	return &Trie{children: make(map[string]*Trie)}
}

func insertPath(root *Trie, path []string) {
	node := root
	for _, p := range path {
		if _, exists := node.children[p]; !exists {
			node.children[p] = NewTrie()
		}
		node = node.children[p]
	}
}

var (
	seen     map[string]*Trie
	toDelete map[*Trie]bool
)

func deduplicate(node *Trie) string {
	if len(node.children) == 0 {
		return ""
	}

	var parts []string
	keys := make([]string, 0, len(node.children))
	for k := range node.children {
		keys = append(keys, k)
	}
	sort.Strings(keys)

	for _, k := range keys {
		parts = append(parts, k+deduplicate(node.children[k]))
	}
	path := "(" + strings.Join(parts, "") + ")"

	if prev, ok := seen[path]; ok {
		toDelete[prev] = true
		toDelete[node] = true
	} else {
		seen[path] = node
	}
	return path
}

func collectPaths(node *Trie, path []string, result *[][]string) {
	for name, child := range node.children {
		if toDelete[child] {
			continue
		}
		newPath := append(path, name)
		*result = append(*result, append([]string(nil), newPath...))
		collectPaths(child, newPath, result)
	}
}

func deleteDuplicateFolder(paths [][]string) [][]string {
	root := NewTrie()
	for _, p := range paths {
		insertPath(root, p)
	}

	seen = make(map[string]*Trie)
	toDelete = make(map[*Trie]bool)

	deduplicate(root)

	var result [][]string
	collectPaths(root, nil, &result)
	return result
}