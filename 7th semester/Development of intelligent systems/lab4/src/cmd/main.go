package main

import (
	"lab4/internal/lexer"
	"lab4/internal/parser"
	"lab4/internal/services/repo"
	"log"
	"strings"
)

const test = `var x = 10 ;
var result = func ( id ) ;
True and False ;
Function ( id ) { return id + id ; } ;
x < 5 and y > 10 ;
x = 5 and y > 10 ;
x = 10 ;
var x = 10`

func main() {

	lexer := lexer.New("./data/terminal_symbos.json")

	analyzerRepo, err := repo.New(
		"./data/action_table.json",
		"./data/goto_table.json",
		"./data/grammar.txt",
	)
	if err != nil {
		log.Fatal(err)
	}

	parser := parser.New(analyzerRepo)

	lines := strings.Split(test, "\n")

	for _, line := range lines {
		tokens, err := lexer.Tokenize(line)
		if err != nil {
			log.Printf("line: %s TOKENIZE ERROR", line)
			continue
		}
		tokens = append(tokens, "$end")

		err = parser.Parse(tokens[:])
		if err != nil {
			log.Printf("line: %s CANCELED", line)
		} else {
			log.Printf("line: %s ACCEPTED", line)
		}
	}
}
