package lexer

import (
	"fmt"
	"lab4/internal/utils"
	"log"
	"regexp"
)

type ILexer interface {
	Tokenize(expression string) []string
}

type Lexer struct {
	terminalSymbols map[string][][]string
}

func New(filePath string) *Lexer {

	var terminalSymbols map[string][][]string

	err := utils.ReadJsonFile(filePath, &terminalSymbols)
	if err != nil {
		log.Fatal(err)
	}

	return &Lexer{terminalSymbols: terminalSymbols}
}

func (l *Lexer) Tokenize(expression string) ([]string, error) {

	words := regexp.MustCompile(`[ ]+`).Split(expression, -1)

	result := make([]string, 0, len(words))

	for _, word := range words {

		token := ""

		for _, data := range l.terminalSymbols["symbols"] {
			re := regexp.MustCompile(data[1])
			if re.MatchString(word) {
				token = data[0]
				break
			}
		}

		for _, data := range l.terminalSymbols["reserved"] {
			re := regexp.MustCompile(data[1])
			if re.MatchString(word) {
				token = data[0]
				break
			}
		}

		if token == "" {
			return nil, fmt.Errorf("unexpected character: %s", word)
		}

		result = append(result, token)
	}

	return result, nil
}
