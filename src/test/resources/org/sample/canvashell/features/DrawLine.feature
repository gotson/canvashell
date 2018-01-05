Feature: draw line

  Background:
    Given Reset shell

  Scenario: Long line
    Given Enter command 'C 20 4'
    When Enter command 'L 1 2 6 2'
    Then result is shown on screen
    """
    ----------------------
    |                    |
    |xxxxxx              |
    |                    |
    |                    |
    ----------------------

    """

  Scenario: Short line
    Given Enter command 'C 20 4'
    When Enter command 'L 6 3 6 4'
    Then result is shown on screen
    """
    ----------------------
    |                    |
    |                    |
    |     x              |
    |     x              |
    ----------------------

    """

  Scenario: Multiple lines
    Given Enter command 'C 20 4'
    And Enter command 'L 1 2 6 2'
    When Enter command 'L 6 3 6 4'
    Then result is shown on screen
    """
    ----------------------
    |                    |
    |xxxxxx              |
    |     x              |
    |     x              |
    ----------------------

    """

  Scenario: Missing canvas
    When Enter command 'L 6 3 6 4'
    Then result is shown on screen
    """
    Canvas has to be initialized first
    """

  Scenario: Diagonal line
    Given Enter command 'C 20 4'
    When Enter command 'L 1 1 3 3'
    Then result is shown on screen
    """
    Only vertical and horizontal lines are supported
    """

  Scenario: Point outside canvas
    Given Enter command 'C 20 4'
    When Enter command 'L 100 1 100 3'
    Then result is shown on screen
    """
    One or more points are outside the canvas
    """