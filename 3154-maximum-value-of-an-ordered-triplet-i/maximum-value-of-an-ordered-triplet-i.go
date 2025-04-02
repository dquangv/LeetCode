func maximumTripletValue(nums []int) int64 {
    var result int64 = 0;
    imax, dmax := 0, 0;

    for _, num := range nums {
        if int64(dmax) * int64(num) > result {
            result = int64(dmax) * int64(num);
        }

        if imax - num > dmax {
            dmax = imax - num;
        }

        if num > imax {
            imax = num;
        }
    }

    return result;
}