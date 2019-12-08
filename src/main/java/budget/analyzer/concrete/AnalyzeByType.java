package budget.analyzer.concrete;

import budget.models.Purchase;
import budget.models.PurchaseCategory;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class AnalyzeByType extends AnalyzeStrategy {
    Map<PurchaseCategory, Double> categoryMap = new LinkedHashMap<>();

    @Override
    public void execute(List<Purchase> list) {

        for (PurchaseCategory purchaseCategory :
                PurchaseCategory.values()) {

            Double categorySum = list.stream()
                    .filter(p -> p.getCategory().equals(purchaseCategory))
                    .map(Purchase::getPrice)
                    .mapToDouble(Double::doubleValue)
                    .sum();

            categoryMap.put(purchaseCategory, categorySum);
        }

        categoryMap = categoryMap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        for (PurchaseCategory purchaseCategory :
                categoryMap.keySet()) {
            System.out.printf("%s - $%.2f\n",purchaseCategory.getName(),categoryMap.get(purchaseCategory));
        }


        System.out.printf("Total sum: $%.2f\n\n", categoryMap
                .values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum() );

    }
}
