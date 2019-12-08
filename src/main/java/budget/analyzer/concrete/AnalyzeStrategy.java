package budget.analyzer.concrete;

import budget.models.Purchase;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public abstract class AnalyzeStrategy implements Strategable {
    protected List<Purchase> filteredList = new LinkedList<>();

    protected double countSum(List<Purchase> list){
        return list.stream()
                .map(x -> x.getPrice())
                .collect(Collectors.summingDouble(Double::doubleValue));
    }
}
