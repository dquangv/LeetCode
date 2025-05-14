const MOD int64 = 1e9 + 7

func lengthAfterTransformations(s string, t int, nums []int) int {
	// Bước 1: vector tần suất ký tự
	freq := make([]int64, 26);
	for _, c := range s {
		freq[c-'a']++;
	}

	// Bước 2: xây ma trận chuyển tiếp 26x26
	trans := makeMatrix();
	for i := 0; i < 26; i++ {
		steps := nums[i];
		for j := 1; j <= steps; j++ {
			to := (i + j) % 26;
			trans[i][to] = (trans[i][to] + 1) % MOD;
		}
	}

	// Bước 3: lũy thừa ma trận trans^t
	transT := matrixPower(trans, t);

	// Bước 4: nhân vector freq với ma trận kết quả
	resultFreq := make([]int64, 26);
	for i := 0; i < 26; i++ {
		for j := 0; j < 26; j++ {
			resultFreq[j] = (resultFreq[j] + freq[i]*transT[i][j]) % MOD;
		}
	}

	// Bước 5: tổng kết quả
	var total int64 = 0;
	for i := 0; i < 26; i++ {
		total = (total + resultFreq[i]) % MOD;
	}

	return int(total);
}

// Tạo ma trận 26x26 rỗng
func makeMatrix() [][]int64 {
	mat := make([][]int64, 26);
	for i := range mat {
		mat[i] = make([]int64, 26);
	}

	return mat;
}

// Nhân 2 ma trận 26x26
func multiply(A, B [][]int64) [][]int64 {
	C := makeMatrix();
	for i := 0; i < 26; i++ {
		for k := 0; k < 26; k++ {
			if A[i][k] == 0 {
				continue;
			}

			for j := 0; j < 26; j++ {
				C[i][j] = (C[i][j] + A[i][k]*B[k][j]) % MOD;
			}
		}
	}

	return C;
}

// Lũy thừa ma trận A^power
func matrixPower(base [][]int64, power int) [][]int64 {
	res := makeMatrix();
	// Ma trận đơn vị
	for i := 0; i < 26; i++ {
		res[i][i] = 1;
	}

	for power > 0 {
		if power%2 == 1 {
			res = multiply(res, base);
		}

		base = multiply(base, base);
		power /= 2;
	}

	return res;
}
