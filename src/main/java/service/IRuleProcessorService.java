package service;

import java.util.List;
import java.util.Map;
import model.Animal;
import model.Rule;

public interface IRuleProcessorService {
  List<Rule> readRules(String filepath);

  Map<String, Integer> applyRules(List<Animal> animalData, List<Rule> rules);
}
