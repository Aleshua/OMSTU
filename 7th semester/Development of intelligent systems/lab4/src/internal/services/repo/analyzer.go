package repo

import (
	"fmt"
	r "lab4/internal/rules"
	"lab4/internal/utils"
	"math"

	"github.com/golang-collections/collections/stack"
)

type AnalyzerRepo interface {
	GetNextActionIDByActionIDAndToken(actionID int, token string) int
	GetCurrentActionIDByActionIDAndToken(actionID int, token string) int
	GetRuleByActionID(index int) *r.Rule

	GetStatesStack() *stack.Stack
	GetTokensStack() *stack.Stack
	ClearStatesStack()
	ClearTokenStack()
}

type analyzerRepo struct {
	actionTable map[int]map[string]*int
	gotoTable   map[int]map[string]*int
	rules       []r.Rule
	statesStack *stack.Stack
	tokensStack *stack.Stack
}

func New(actionTablePath, gotoTablePath, rulesPath string) (*analyzerRepo, error) {

	var (
		actionTable map[int]map[string]*int
		gotoTable   map[int]map[string]*int
	)

	err := utils.ReadJsonFile(actionTablePath, &actionTable)
	if err != nil {
		return nil, fmt.Errorf("actionTable read error: %s", err.Error())
	}

	err = utils.ReadJsonFile(gotoTablePath, &gotoTable)
	if err != nil {
		return nil, fmt.Errorf("gotoTable read error: %s", err.Error())
	}

	rules, err := r.ReadRules(rulesPath)
	if err != nil {
		return nil, fmt.Errorf("rules read error: %s", err.Error())
	}

	statesStack := stack.New()
	tokensStack := stack.New()

	return &analyzerRepo{
		actionTable: actionTable,
		gotoTable:   gotoTable,
		rules:       rules,
		statesStack: statesStack,
		tokensStack: tokensStack,
	}, nil
}

func (a *analyzerRepo) GetNextActionIDByActionIDAndToken(actionID int, token string) int {
	return *a.gotoTable[actionID][token]
}

func (a *analyzerRepo) GetCurrentActionIDByActionIDAndToken(actionID int, token string) int {
	return *a.actionTable[actionID][token]
}

func (a *analyzerRepo) GetRuleByActionID(index int) *r.Rule {
	return &a.rules[int(math.Abs(float64(index)))]
}

func (a *analyzerRepo) GetStatesStack() *stack.Stack {
	return a.statesStack
}
func (a *analyzerRepo) GetTokensStack() *stack.Stack {
	return a.tokensStack
}

func (a *analyzerRepo) ClearStatesStack() {
	for a.statesStack.Pop() != nil {
	}
}

func (a *analyzerRepo) ClearTokenStack() {
	for a.tokensStack.Pop() != nil {
	}
}
