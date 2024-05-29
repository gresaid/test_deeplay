package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Animal;
import model.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RuleProcessorServiceTest {
  private static RuleProcessorService ruleProcessorService;

  @BeforeEach
  public void setUp() {
    ruleProcessorService = new RuleProcessorService() {};
  }

  @Test
  void readRules() {

    RuleProcessorService ruleProcessorServiceSpy = Mockito.spy(ruleProcessorService);

    List<Rule> rules = ruleProcessorServiceSpy.readRules("rules.txt");

    assertNotNull(rules);
    assertEquals(3, rules.size());
    assertEquals("Травоядные", rules.get(0).getName());
    assertEquals("animal['type'] == \"ТРАВОЯДНОЕ\"", rules.get(0).getCondition());
    assertEquals("Маленькие травоядные или плотоядные", rules.get(1).getName());
    assertEquals(
        "animal['type'] == \"ТРАВОЯДНОЕ\" || animal['type'] == \"ПЛОТОЯДНОЕ\" && animal['height'] == \"МАЛЕНЬКОЕ\"",
        rules.get(1).getCondition());
    assertEquals("Всеядные, но не высокие", rules.get(2).getName());
    assertEquals(
        "animal['type'] == \"ВСЕЯДНОЕ\" && animal['height'] != \"ВЫСОКОЕ\"",
        rules.get(2).getCondition());
  }

  @Test
  public void testApplyRules() {
    List<Animal> animals =
        Arrays.asList(
            createAnimal("ЛЕГКОЕ", "МАЛЕНЬКОЕ", "ВСЕЯДНОЕ"),
            createAnimal("ТЯЖЕЛОЕ", "МАЛЕНЬКОЕ", "ТРАВОЯДНОЕ"),
            createAnimal("ТЯЖЕЛОЕ", "НЕВЫСОКОЕ", "ТРАВОЯДНОЕ"),
            createAnimal("СРЕДНЕЕ", "ВЫСОКОЕ", "ПЛОТОЯДНОЕ"));

    List<Rule> rules =
        Arrays.asList(
            new Rule("Травоядные", "animal['type'] == \"ТРАВОЯДНОЕ\""),
            new Rule(
                "Маленькие травоядные или плотоядные",
                "animal['type'] == \"ТРАВОЯДНОЕ\" || animal['type'] == \"ПЛОТОЯДНОЕ\" && animal['height'] == \"МАЛЕНЬКОЕ\""),
            new Rule(
                "Всеядные, но не высокие",
                "animal['type'] == \"ВСЕЯДНОЕ\" && animal['height'] != \"ВЫСОКОЕ\""));

    Map<String, Integer> expectedResults = new HashMap<>();
    expectedResults.put("Травоядные", 2);
    expectedResults.put("Маленькие травоядные или плотоядные", 2);
    expectedResults.put("Всеядные, но не высокие", 1);

    Map<String, Integer> actualResults = ruleProcessorService.applyRules(animals, rules);

    assertEquals(expectedResults, actualResults);
  }

  private Animal createAnimal(String weight, String height, String type) {
    Animal animal = mock(Animal.class);
    when(animal.getWeight()).thenReturn(weight);
    when(animal.getHeight()).thenReturn(height);
    when(animal.getType()).thenReturn(type);
    return animal;
  }
}
