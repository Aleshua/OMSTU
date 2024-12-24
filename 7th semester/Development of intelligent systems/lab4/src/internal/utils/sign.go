package utils

func Sign(x int) int {

	if x < 0 {
		return -1
	}
	if x > 0 {
		return 1
	}
	return 0

	// return (x >> 31) - (-x >> 31)
}
