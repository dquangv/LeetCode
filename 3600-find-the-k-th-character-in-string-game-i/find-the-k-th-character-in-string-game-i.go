func kthCharacter(k int) byte {
    // Trả về ký tự thứ k dựa trên số bit 1 trong (k-1)
	return byte('a' + bits.OnesCount(uint(k-1)))
}