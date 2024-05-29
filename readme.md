# Тестовое задание Deeplay

## Файлы

- `animals.txt`: Файл с данными о животных.
- `rules.txt`: Файл с правилами для подсчета.

## Формат данных

### animals.txt

Файл с данными о животных должен содержать строки со свойствами животных, разделенными запятыми.

```
ЛЕГКОЕ,МАЛЕНЬКОЕ,ВСЕЯДНОЕ
ТЯЖЕЛОЕ,МАЛЕНЬКОЕ,ТРАВОЯДНОЕ
ТЯЖЕЛОЕ,НЕВЫСОКОЕ,ТРАВОЯДНОЕ
```

### rules.txt

Файл с правилами должен содержать строки в формате "имя правила: условие".

```
Травоядные: animal['type'] == 'ТРАВОЯДНОЕ'
Маленькие травоядные или плотоядные: animal['type'] == 'ТРАВОЯДНОЕ' || animal['type'] == 'ПЛОТОЯДНОЕ' && animal['height'] == 'МАЛЕНЬКОЕ'
Всеядные, но не высокие: animal['type'] == 'ВСЕЯДНОЕ' && animal['height'] != 'ВЫСОКОЕ'
```

## Использование

1. Поместите файлы `animals.txt` и `rules.txt` в корневую директорию проекта.
2. При запуске в качестве аргументов программа должна принимать два файла "<animal_file> <rules_file>" в таком формате.
   