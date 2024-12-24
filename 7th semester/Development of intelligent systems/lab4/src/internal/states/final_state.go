package states

import r "lab4/internal/services/repo"

type FinalState struct {
}

func NewFinalState() *FinalState {
	return &FinalState{}
}

func (s *FinalState) Next(
	repo r.AnalyzerRepo,
	actionToState map[int]IState,
	tokens *[]string,
	currentAction *int,
) (IState, error) {

	return nil, nil
}
