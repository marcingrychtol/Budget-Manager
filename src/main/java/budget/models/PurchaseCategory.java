package budget.models;

import java.util.HashMap;
import java.util.Map;

public enum PurchaseCategory {
    FOOD(1, "Food"),
    CLOTHES(2, "Clothes"),
    ENTERTAINMENT(3, "Entertainment"),
    OTHER(4, "Other");

    private String name;
    private int value;

    private static Map map = new HashMap();

    static {
        for (PurchaseCategory category : PurchaseCategory.values()) {
            map.put(category.value, category);
        }
    }

    public static PurchaseCategory getCategoryByNumber(int category) {
        return (PurchaseCategory) map.get(category);
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    PurchaseCategory(int value, String name) {
        this.value = value;
        this.name = name;
    }
}