package budget.models;

import java.text.DecimalFormat;

public class Purchase implements Comparable {
    private PurchaseCategory category;
    private String title;
    private Double price;
    DecimalFormat dec = new DecimalFormat("#0.00");

    public PurchaseCategory getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    @Override
    public String toString() {
        return title + " $" + dec.format(price);
    }

    public Purchase(PurchaseCategory category, String title, Double price) {
        this.category = category;
        this.title = title;
        this.price = price;
    }

    public String toSaveString() {
        return category.getValue() +
                ";" + title +
                ";" + price;
    }


    @Override
    public int compareTo( Object o) {
        Purchase other = (Purchase)o;
        return this.title.compareTo(other.title);
    }
}


