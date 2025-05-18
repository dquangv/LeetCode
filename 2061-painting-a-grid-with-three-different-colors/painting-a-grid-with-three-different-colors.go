const MOD = 1_000_000_007

func colorTheGrid(m int, n int) int {
    validStates := generateValidStates(m);
    transitions := buildTransitions(validStates, m);

    dp := make([]int, len(validStates));
    for i := range dp {
        dp[i] = 1;
    }

    for col := 1; col < n; col++ {
        newDp := make([]int, len(validStates));
        for i := range validStates {
            for _, prev := range transitions[i] {
                newDp[i] = (newDp[i] + dp[prev]) % MOD;
            }
        }
        dp = newDp;
    }

    result := 0;
    for _, count := range dp {
        result = (result + count) % MOD;
    }

    return result;
}

func generateValidStates(m int) [][]int {
    total := 1;
    for i := 0; i < m; i++ {
        total *= 3;
    }

    var validStates [][]int;
    for mask := 0; mask < total; mask++ {
        colors := decode(mask, m);
        if isValid(colors) {
            validStates = append(validStates, colors);
        }
    }

    return validStates;
}

func buildTransitions(validStates [][]int, m int) map[int][]int {
    transitions := make(map[int][]int);
    for i, a := range validStates {
        for j, b := range validStates {
            if canTransition(a, b) {
                transitions[i] = append(transitions[i], j);
            }
        }
    }

    return transitions;
}

func isValid(colors []int) bool {
    for i := 1; i < len(colors); i++ {
        if colors[i] == colors[i-1] {
            return false;
        }
    }

    return true;
}

func canTransition(a, b []int) bool {
    for i := 0; i < len(a); i++ {
        if a[i] == b[i] {
            return false;
        }
    }

    return true;
}

func decode(mask, m int) []int {
    colors := make([]int, m);
    for i := 0; i < m; i++ {
        colors[i] = mask % 3;
        mask /= 3;
    }
    
    return colors;
}