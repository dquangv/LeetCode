func findAllRecipes(recipes []string, ingredients [][]string, supplies []string) []string {
    graph := make(map[string][]string);
    inDegree := make(map[string]int);

    for _, recipe := range recipes {
        inDegree[recipe] = 0;
    }

    for i, recipe := range recipes {
        for _, ing := range ingredients[i] {
            graph[ing] = append(graph[ing], recipe);
            inDegree[recipe]++;
        }
    }

    queue := make([]string, 0);
    available := make(map[string]bool);

    for _, supply := range supplies {
        queue = append(queue, supply);
        available[supply] = true;
    }

    result := make([]string, 0);

    for len(queue) > 0 {
        current := queue[0];
        queue = queue[1:];

        if _, exists := inDegree[current]; exists {
            result = append(result, current);
        }

        for _, dependentRecipe := range graph[current] {
            inDegree[dependentRecipe]--;

            if inDegree[dependentRecipe] == 0 {
                queue = append(queue, dependentRecipe);
            }
        }
    }

    return result;
}