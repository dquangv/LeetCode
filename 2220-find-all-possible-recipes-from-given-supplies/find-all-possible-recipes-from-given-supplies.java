class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        for (String recipe : recipes) {
            inDegree.put(recipe, 0);
        }
        
        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            List<String> ingredientList = ingredients.get(i);

            for (String ingredient : ingredientList) {
                graph.computeIfAbsent(ingredient, x -> new ArrayList<>()).add(recipe);
                inDegree.put(recipe, inDegree.get(recipe) + 1);
            } 
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> available = new HashSet<>(Arrays.asList(supplies));

        for (String supply : supplies) {
            queue.offer(supply);
        }

        List<String> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (inDegree.containsKey(current)) {
                result.add(current);
            }

            if (graph.containsKey(current)) {
                for (String dependentRecipe: graph.get(current)) {
                    inDegree.put(dependentRecipe, inDegree.get(dependentRecipe) - 1);

                    if (inDegree.get(dependentRecipe) == 0) {
                        queue.offer(dependentRecipe);
                    }
                }
            }
        }

        return result;
    }
}