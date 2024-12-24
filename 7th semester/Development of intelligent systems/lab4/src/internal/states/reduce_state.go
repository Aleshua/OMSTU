package states

import (
	"fmt"
	r "lab4/internal/services/repo"
	"lab4/internal/utils"
)

type ReduceState struct {
}

func NewReduceState() *ReduceState {
	return &ReduceState{}
}

func (s *ReduceState) Next(
	repo r.AnalyzerRepo,
	actionToState map[int]IState,
	tokens *[]string,
	currentAction *int,
) (state IState, err error) {

	defer func() {
		if r := recover(); r != nil {
			err = fmt.Errorf("reduce error: %v", r)
			state = NewErrorState()
		}
	}()

	rule := repo.GetRuleByActionID(*currentAction)

	for range rule.Subrules {
		repo.GetStatesStack().Pop()
		repo.GetTokensStack().Pop()
	}
	repo.GetTokensStack().Push(rule.Name)

	repo.GetStatesStack().Push(
		repo.GetNextActionIDByActionIDAndToken(
			repo.GetStatesStack().Peek().(int),
			repo.GetTokensStack().Peek().(string),
		),
	)

	*currentAction = repo.GetCurrentActionIDByActionIDAndToken(
		repo.GetStatesStack().Peek().(int),
		(*tokens)[0],
	)
	nextState := actionToState[utils.Sign(*currentAction)]

	return nextState, nil
}
