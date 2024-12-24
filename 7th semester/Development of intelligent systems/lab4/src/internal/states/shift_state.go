package states

import (
	"fmt"
	r "lab4/internal/services/repo"
	"lab4/internal/utils"
	"math"
)

type ShiftState struct {
}

func NewShiftState() *ShiftState {
	return &ShiftState{}
}

func (s *ShiftState) Next(
	repo r.AnalyzerRepo,
	actionToState map[int]IState,
	tokens *[]string,
	currentAction *int,
) (state IState, err error) {

	defer func() {
		if r := recover(); r != nil {
			err = fmt.Errorf("shift error: %v", r)
			state = NewErrorState()
		}
	}()

	repo.GetTokensStack().Push((*tokens)[0])

	err = utils.RemoveElementFromSlice(0, tokens)
	if err != nil {
		return nil, fmt.Errorf(err.Error())
	}

	repo.GetStatesStack().Push(int(math.Abs(float64(*currentAction))))

	*currentAction = repo.GetCurrentActionIDByActionIDAndToken(
		repo.GetStatesStack().Peek().(int),
		(*tokens)[0],
	)
	nextState := actionToState[utils.Sign(*currentAction)]

	return nextState, nil
}
