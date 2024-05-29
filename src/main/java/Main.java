import View.ResultPrinter;
import handler.AnimalDataHandler;
import model.Animal;
import model.Rule;
import service.RuleProcessorService;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("<animal_file> <rules_file>");
            System.exit(1);
        }

        String animalFile = args[0];
        String rulesFile = args[1];

        AnimalDataHandler animalDataHandler = new AnimalDataHandler();
        RuleProcessorService ruleProcessorService = new RuleProcessorService();
        ResultPrinter resultPrinter = new ResultPrinter();

        List<Animal> animalList = animalDataHandler.readAnimalData(animalFile);
        List<Rule> rules = ruleProcessorService.readRules(rulesFile);
        Map<String, Integer> result = ruleProcessorService.applyRules(animalList, rules);
        resultPrinter.printResults(result);
    }
}
