package rules

import (
	"bufio"
	"log"
	"os"
	"strings"
)

type Rule struct {
	Name     string
	Subrules []string
}

func ReadRules(filePath string) ([]Rule, error) {
	file, err := os.Open(filePath)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	var grammar []Rule

	grammar = make([]Rule, 0, 35)

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		line := scanner.Text()

		if len(line) == 0 {
			continue
		}

		parts := strings.Split(line, "->")
		if len(parts) != 2 {
			continue
		}

		rule := strings.TrimSpace(parts[0])
		definition := strings.TrimSpace(parts[1])

		subrules := strings.Split(definition, " ")
		for i := range subrules {
			subrules[i] = strings.TrimSpace(subrules[i])
		}

		grammar = append(grammar, Rule{
			Name:     rule,
			Subrules: subrules,
		})
	}

	if err := scanner.Err(); err != nil {
		return nil, err
	}

	return grammar, nil
}
