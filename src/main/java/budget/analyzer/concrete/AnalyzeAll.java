package budget.analyzer.concrete;

import budget.models.Purchase;

import java.util.Comparator;
import java.util.List;

public class AnalyzeAll extends AnalyzeStrategy {
    @Override
    public void execute(List<Purchase> list) {

        list.sort(Comparator.comparing(Purchase::getPrice).reversed());

        list.stream()
                .map(x -> x.toString())
                .forEach(s -> System.out.println(s));


        System.out.printf("Total sum: $%.2f\n\n", countSum(list));
    }

}
