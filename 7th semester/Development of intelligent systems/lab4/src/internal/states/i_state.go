package states

import (
	r "lab4/internal/services/repo"
)

type IState interface {
	Next(
		repo r.AnalyzerRepo,
		actionToState map[int]IState,
		tokens *[]string,
		currentAction *int,
	) (IState, error)
}
