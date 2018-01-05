Feature: draw rectangle

  Background:
    Given Reset shell

  Scenario: Large rectangle
    Given Enter command 'C 20 4'
    When Enter command 'R 14 1 18 3'
    Then result is shown on screen
    """
    ----------------------
    |             xxxxx  |
    |             x   x  |
    |             xxxxx  |
    |                    |
    ----------------------

    """

  Scenario: Smallest rectangle
    Given Enter command 'C 20 4'
    When Enter command 'L 6 3 6 3'
    Then result is shown on screen
    """
    ----------------------
    |                    |
    |                    |
    |     x              |
    |                    |
    ----------------------

    """

  Scenario: Line rectangle
    Given Enter command 'C 20 4'
    When Enter command 'L 6 2 6 4'
    Then result is shown on screen
    """
    ----------------------
    |                    |
    |     x              |
    |     x              |
    |     x              |
    ----------------------

    """


  Scenario: Multiple rectangles
    Given Enter command 'C 20 4'
    When Enter command 'R 14 1 18 3'
    And Enter command 'R 1 1 10 3'
    Then result is shown on screen
    """
    ----------------------
    |xxxxxxxxxx   xxxxx  |
    |x        x   x   x  |
    |xxxxxxxxxx   xxxxx  |
    |                    |
    ----------------------

    """

  Scenario: Overlapping rectangles
    Given Enter command 'C 20 8'
    When Enter command 'R 14 1 18 5'
    And Enter command 'R 9 3 16 7'
    Then result is shown on screen
    """
    ----------------------
    |             xxxxx  |
    |             x   x  |
    |        xxxxxxxx x  |
    |        x    x x x  |
    |        x    xxxxx  |
    |        x      x    |
    |        xxxxxxxx    |
    |                    |
    ----------------------

    """

  Scenario: Missing canvas
    When Enter command 'R 14 1 18 3'
    Then result is shown on screen
    """
    Canvas has to be initialized first
    """

  Scenario: Point outside canvas
    Given Enter command 'C 20 4'
    When Enter command 'R 1 1 100 3'
    Then result is shown on screen
    """
    One or more points are outside the canvas
    """
