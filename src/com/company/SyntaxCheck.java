package com.company;

import java.util.List;
import java.util.Stack;

public class SyntaxCheck {
    List<Row> grammer;
    List<String> in;
    String _currLexem;
    Integer _currentTableIndex;
    Integer _currentWordIndex;
    Stack<Integer> stackIndex = new Stack<>();


    public SyntaxCheck(List<Row> grammer, List<String> in) {
        this.grammer = grammer;
        this.in = in;
    }

    public boolean Run()
    {
        _currentTableIndex = 0;
        _currentWordIndex = 0;
        _currLexem = in.get(_currentWordIndex);
        return CheckWords();
    }

    private boolean CheckRow()
    {
        return grammer.get(_currentTableIndex).directionSet.contains(_currLexem) || grammer.get(_currentTableIndex).directionSet.size() == 0;
    }

    private boolean CheckWords()
    {
        if (CheckRow())  // проверяем можно ли обрабатывать строку в таблице
        {
            ShiftIfEnabled();
            PushToStackIfEnabled();

            if (grammer.get(_currentTableIndex).dirNum != 0 )  // переходим по dirNum
            {
                _currentTableIndex = grammer.get(_currentTableIndex).dirNum;
                return CheckWords();
            }

            if ( grammer.get(_currentTableIndex).dirNum == 0 && stackIndex.size() > 0 )  // переходим по стеку, если нельзя по dirNum
            {
                _currentTableIndex = stackIndex.lastElement();
                stackIndex.remove(stackIndex.size() - 1);
                return CheckWords();
            }

            if (grammer.get(_currentTableIndex).errorTransition != -1 )  // переходим по Error, если возможно и нельзя обработать строку
            {
                _currentTableIndex = grammer.get(_currentTableIndex).errorTransition;
                return CheckWords();
            }
            return stackIndex.size() == 0 && grammer.get(_currentTableIndex).isEnd == 1;

        }

        return false;
    }


    private void ShiftIfEnabled()
    {
        if (grammer.get(_currentTableIndex).shift == -1)
        {
            return;
        }

        _currentWordIndex++;
        if (_currentWordIndex > in.size() - 1)
        {
            _currLexem = null;
            return;
        }

        _currLexem = in.get(_currentWordIndex);
    }

    private void PushToStackIfEnabled()
    {
        if (grammer.get(_currentTableIndex).stack == -1)
        {
            return;
        }
        stackIndex.push( _currentTableIndex + 1 );
    }
}