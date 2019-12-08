package budget.analyzer.concrete;

import budget.models.Purchase;

import java.util.List;

public interface Strategable {
    void execute(List<Purchase> list);
}
