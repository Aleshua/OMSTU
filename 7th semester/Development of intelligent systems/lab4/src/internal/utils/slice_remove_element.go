package utils

import "fmt"

func RemoveElementFromSlice[T any](index int, slice *[]T) error {

	if index >= len(*slice) || index < 0 {
		return fmt.Errorf("index out of range")
	}

	*slice = append((*slice)[:index], (*slice)[index+1:]...)
	return nil
}
