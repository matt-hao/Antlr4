import model.*;
import model.Number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionProcessor {
    private List<Expression> list;
    private Map<String, Integer> values;

    public ExpressionProcessor(List<Expression> list) {
        this.list = list;
        this.values = new HashMap<>();
    }

    public List<String> getEvaluationResults() {
        List<String> evaluations = new ArrayList<>();
        for (Expression e : list) {
            if (e instanceof VariableDeclaration) {
                VariableDeclaration variableDeclaration = (VariableDeclaration) e;
                values.put(variableDeclaration.id, variableDeclaration.value);
            } else {
                evaluations.add(e.toString() + " is " + getEvalResults(e));
            }
        }
        return evaluations;
    }

    private int getEvalResults(Expression e) {
        int res;

        if (e instanceof Number) {
            res = ((Number) e).getNum();
        } else if (e instanceof Variable) {
            res = values.get(((Variable) e).getId());
        } else if (e instanceof Addition) {
            Addition addition = (Addition) e;
            res = getEvalResults(addition.getLeft()) + getEvalResults(addition.getRight());
        } else {
            Multiplication multiplication = (Multiplication) e;
            res = getEvalResults(multiplication.getLeft()) * getEvalResults(multiplication.getRight());
        }
        return res;
    }
}
