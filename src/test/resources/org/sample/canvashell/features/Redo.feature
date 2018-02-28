Feature: redo

  Background:
    Given Reset shell

  Scenario: Redo canvas fill
    Given Enter command 'C 20 4'
    And Enter command 'B 2 2 a'
    And Enter command 'U'
    When Enter command 'D'
    Then result is shown on screen
    """
    ----------------------
    |aaaaaaaaaaaaaaaaaaaa|
    |aaaaaaaaaaaaaaaaaaaa|
    |aaaaaaaaaaaaaaaaaaaa|
    |aaaaaaaaaaaaaaaaaaaa|
    ----------------------

    """

  Scenario: Undo line
    Given Enter command 'C 20 4'
    And Enter command 'L 1 2 6 2'
    And Enter command 'L 6 3 6 4'
    And Enter command 'U'
    When Enter command 'D'
    Then result is shown on screen
    """
    ----------------------
    |                    |
    |xxxxxx              |
    |     x              |
    |     x              |
    ----------------------

    """

  Scenario: Redo multiple
    Given Enter command 'C 20 4'
    And Enter command 'L 1 2 6 2'
    And Enter command 'L 6 3 6 4'
    And Enter command 'B 1 1 *'
    And Enter command 'U'
    And Enter command 'U'
    When Enter command 'D'
    And Enter command 'D'
    Then result is shown on screen
    """
    ----------------------
    |********************|
    |xxxxxx**************|
    |     x**************|
    |     x**************|
    ----------------------

    """

  Scenario: Missing canvas
    When Enter command 'D'
    Then result is shown on screen
    """
    Canvas has to be initialized first
    """

  Scenario: Nothing to redo
    Given Enter command 'C 20 4'
    When Enter command 'D'
    Then result is shown on screen
    """
    ----------------------
    |                    |
    |                    |
    |                    |
    |                    |
    ----------------------

    """
