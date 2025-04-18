func countAndSay(n int) string {
    if n == 1 {
        return "1";
    }

    pre := countAndSay(n - 1);
    var cur strings.Builder;
    count := 1;

    for i := 1; i < len(pre); i++ {
        if pre[i] == pre[i - 1] {
            count++;
        } else {
            cur.WriteString(strconv.Itoa(count));
            cur.WriteByte(pre[i - 1]);
            count = 1;
        }
    }

    cur.WriteString(strconv.Itoa(count));
    cur.WriteByte(pre[len(pre) - 1]);

    return cur.String();
}