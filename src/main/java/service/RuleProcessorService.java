package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Animal;
import model.Rule;

public class RuleProcessorService implements IRuleProcessorService {

  @Override
  public List<Rule> readRules(String filePath) {
    List<Rule> rules = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(":");
        if (parts.length == 2) {
          String ruleName = parts[0].trim();
          String condition = parts[1].trim();
          rules.add(new Rule(ruleName, condition));
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return rules;
  }

  @Override
  public Map<String, Integer> applyRules(List<Animal> animalData, List<Rule> rules) {
    Map<String, Integer> results = new HashMap<>();
    for (Rule rule : rules) {
      results.put(rule.getName(), 0);
    }
    for (Animal animal : animalData) {
      for (Rule rule : rules) {
        if (evaluateRule(animal, rule.getCondition())) {
          results.put(rule.getName(), results.get(rule.getName()) + 1);
        }
      }
    }
    return results;
  }

  private boolean evaluateRule(Animal animal, String condition) {
    condition =
        condition
            .replace("animal['weight']", "\"" + animal.getWeight() + "\"")
            .replace("animal['height']", "\"" + animal.getHeight() + "\"")
            .replace("animal['type']", "\"" + animal.getType() + "\"");

    return evaluateCondition(condition);
  }

  private boolean evaluateCondition(String condition) {
    condition = condition.replace(" ", "");

    if (condition.contains("||")) {
      String[] parts = condition.split("\\|\\|");
      for (String part : parts) {
        if (evaluateCondition(part)) {
          return true;
        }
      }
      return false;
    } else if (condition.contains("&&")) {
      String[] parts = condition.split("&&");
      for (String part : parts) {
        if (!evaluateCondition(part)) {
          return false;
        }
      }
      return true;
    } else {
      if (condition.contains("==")) {
        String[] parts = condition.split("==");
        if (parts.length == 2) {
          return parts[0].equals(parts[1]);
        }
      } else if (condition.contains("!=")) {
        String[] parts = condition.split("!=");
        if (parts.length == 2) {
          return !parts[0].equals(parts[1]);
        }
      }
    }
    throw new IllegalArgumentException("Invalid condition: " + condition);
  }
}
