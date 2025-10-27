func numberOfBeams(bank []string) int {
    var prev, ans int
    prev = helper(bank[0])

    for i := 1; i < len(bank); i++ {
        cur := helper(bank[i])
        if cur > 0 {
            ans += (prev * cur)
            prev = cur
        }
    }
    
    return ans
}

func helper(s string) int {
    c := 0
    for _, v := range s {
        if v == '1' {
            c++
        }
    }
    return c
}