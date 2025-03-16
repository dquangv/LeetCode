func repairCars(ranks []int, cars int) int64 {
    sort.Ints(ranks);
    left := int64(1);
    right := int64(ranks[0]) * int64(cars) * int64 (cars);
    result := right;

    for left <= right {
        mid := left + (right - left) / 2;
        
        if canRepairAll(ranks, cars, mid) {
            result = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return result;
}

func canRepairAll(ranks []int, cars int, time int64) bool {
    totalCars := int64(0);

    for _, rank:= range ranks {
        carsPerMechanic := int64(math.Sqrt(float64(time) / float64(rank)));
        totalCars += carsPerMechanic;

        if totalCars >= int64(cars) {
            return true;
        }
    }

    return totalCars >= int64(cars);
}