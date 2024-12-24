package utils

import (
	"encoding/json"
	"log"
	"os"
)

func ReadJsonFile(filePath string, result interface{}) error {
	data, err := os.ReadFile(filePath)
	if err != nil {
		return err
	}

	err = json.Unmarshal(data, &result)
	if err != nil {
		log.Fatal(err)
	}

	return nil
}
