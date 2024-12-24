package states

import (
	"fmt"
	r "lab4/internal/services/repo"
)

type ErrorState struct {
}

func NewErrorState() *ErrorState {
	return &ErrorState{}
}

func (s *ErrorState) Next(
	repo r.AnalyzerRepo,
	actionToState map[int]IState,
	tokens *[]string,
	currentAction *int,
) (IState, error) {
	return nil, fmt.Errorf("parsing error")
}
