package budget.analyzer;

import budget.analyzer.concrete.AnalyzeAll;
import budget.analyzer.concrete.Strategable;
import budget.models.Purchase;

import java.util.List;

public class Analyzer {

    Strategable analyzeStrategy = new AnalyzeAll();

    public void setAnalyzeStrategy(Strategable analyzeStrategy) {
        this.analyzeStrategy = analyzeStrategy;
    }

    public void showAnalysis(String title, List<Purchase> list){
        System.out.printf("%s: \n",title);
        analyzeStrategy.execute(list);
    }
}
