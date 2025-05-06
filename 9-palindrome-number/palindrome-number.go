func isPalindrome(x int) bool {
    if x < 0 {
        return false;
    }

    temp, reverse := x, 0;
    for temp != 0 {
        reverse = reverse * 10 + temp % 10;
        temp /= 10;
    }

    if reverse == x {
        return true;
    }

    return false;
}