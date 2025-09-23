func compareVersion(version1 string, version2 string) int {
    var i, j, rev1, rev2 int

    for i < len(version1) || j < len(version2) {
        rev1, i = nextRevision(version1, i)
        rev2, j = nextRevision(version2, j)
        if rev1 < rev2 {
            return -1
        } else if rev1 > rev2 {
            return 1
        }
    }
    return 0
}

func nextRevision(version string, idx int) (int, int) {
    rev := 0
    for idx < len(version) && version[idx] != '.' {
        rev = rev*10 + int(version[idx] - '0')
        idx++
    }
    return rev, idx + 1
}