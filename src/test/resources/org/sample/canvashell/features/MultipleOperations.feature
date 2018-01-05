Feature: Multiple operations

  Background:
    Given Reset shell

  Scenario: Complex drawing
    When Enter command 'C 20 4'
    And Enter command 'L 1 2 6 2'
    And Enter command 'L 6 3 6 4'
    And Enter command 'R 14 1 18 3'
    And Enter command 'B 10 3 o'
    Then result is shown on screen
    """
    ----------------------
    |oooooooooooooxxxxxoo|
    |xxxxxxooooooox   xoo|
    |     xoooooooxxxxxoo|
    |     xoooooooooooooo|
    ----------------------

    """

