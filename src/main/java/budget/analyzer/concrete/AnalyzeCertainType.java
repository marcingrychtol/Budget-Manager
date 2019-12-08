package budget.analyzer.concrete;

import budget.models.Purchase;
import budget.models.PurchaseCategory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AnalyzeCertainType extends AnalyzeStrategy {

    public PurchaseCategory category;

    public AnalyzeCertainType(PurchaseCategory category) {
        this.category = category;
    }

    @Override
    public void execute(List<Purchase> list) {
        this.filteredList = list.stream()
                .filter(p -> p.getCategory().equals(this.category))
                .collect(Collectors.toList());

        filteredList.sort(Comparator.comparing(Purchase::getPrice).reversed());

        filteredList.stream()
                .forEach(System.out::println);

        if (filteredList.isEmpty()){
            System.out.println("Purchase list is empty!\n");
            return;
        }

        System.out.printf("Total sum: $%.2f\n",countSum(filteredList));
        System.out.println();

    }
}
