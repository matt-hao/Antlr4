package model;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private List<Expression> expressions;

    public Program() {
        this.expressions = new ArrayList<>();
    }

    public void addExpression(Expression e){
        expressions.add(e);
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
