package handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Animal;

public class AnimalDataHandler {
  public List<Animal> readAnimalData(String filePath) {
    List<Animal> animalList = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] properties = line.split(",");
        Animal animal = new Animal(properties[0], properties[1], properties[2]);
        animalList.add(animal);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return animalList;
  }
}
