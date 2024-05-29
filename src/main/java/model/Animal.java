package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Animal {
  private final String weight;
  private final String height;
  private final String type;
}
