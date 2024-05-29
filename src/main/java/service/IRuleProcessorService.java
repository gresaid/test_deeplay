package service;

import model.Animal;
import model.Rule;

import java.util.List;
import java.util.Map;

public interface IRuleProcessorService {
    List<Rule> readRules(String filepath);

    Map<String, Integer> applyRules(List<Animal> animalData, List<Rule> rules);
}
