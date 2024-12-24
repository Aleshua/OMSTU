package parser

import (
	"fmt"
	r "lab4/internal/services/repo"
	"lab4/internal/states"
	"lab4/internal/utils"
)

type IParser interface {
	Parse(tokens []string) error
}

type Parser struct {
	repo          r.AnalyzerRepo
	actionToState map[int]states.IState
}

func New(repo r.AnalyzerRepo) *Parser {
	shiftState := states.NewShiftState()
	reduceState := states.NewReduceState()
	finalState := states.NewFinalState()

	actionToState := map[int]states.IState{
		1:  shiftState,
		0:  finalState,
		-1: reduceState,
	}

	return &Parser{
		repo:          repo,
		actionToState: actionToState,
	}
}

func (p *Parser) Parse(tokens []string) error {

	currentAction := p.repo.GetCurrentActionIDByActionIDAndToken(0, tokens[0])
	currentState := p.actionToState[utils.Sign(currentAction)]

	p.repo.ClearStatesStack()
	p.repo.ClearTokenStack()

	p.repo.GetStatesStack().Push(0)
	p.repo.GetTokensStack().Push("")

	var err error

	for {
		currentState, err = currentState.Next(
			p.repo,
			p.actionToState,
			&tokens,
			&currentAction,
		)
		if err != nil {
			return fmt.Errorf(err.Error())
		}
		if currentState == nil {
			return nil
		}
	}
}
