func check(tops []int, bottoms []int, val int) int {
   topRes, bottomRes := 0, 0;

	for i := 0; i < len(tops); i++ {
		if tops[i] != val && bottoms[i] != val {
			return -1;
		} else if tops[i] != val {
			topRes++;
		} else if bottoms[i] != val {
			bottomRes++;
		}
	}

	if topRes < bottomRes {
		return topRes;
	}

	return bottomRes;
}

func minDominoRotations(tops []int, bottoms []int) int {
	result := check(tops, bottoms, tops[0]);

	if result != -1 || tops[0] == bottoms[0] {
		return result;
	}

	return check(tops, bottoms, bottoms[0]);
}