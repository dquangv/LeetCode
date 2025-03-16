func repairCars(ranks []int, cars int) int64 {
	cars64 := int64(cars);

	left := int64(0);
	right := int64(ranks[0]) * cars64 * cars64;

	for left < right {
		mid := left + (right - left) / 2;
		totalCars := int64(0);

		for _, r := range ranks {
			n := int64(math.Sqrt(float64(mid) / float64(r)));
			totalCars += n;
		}

		if totalCars >= cars64 {
			right = mid;
		} else {
			left = mid + 1;
		}
	}

	return left;
}