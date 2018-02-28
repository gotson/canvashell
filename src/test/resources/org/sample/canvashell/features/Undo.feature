Feature: undo

  Background:
    Given Reset shell

  Scenario: Undo canvas fill
    Given Enter command 'C 20 4'
    And Enter command 'B 2 2 a'
    When Enter command 'U'
    Then result is shown on screen
    """
    ----------------------
    |                    |
    |                    |
    |                    |
    |                    |
    ----------------------

    """

  Scenario: Undo line
    Given Enter command 'C 20 4'
    And Enter command 'L 1 2 6 2'
    And Enter command 'L 6 3 6 4'
    When Enter command 'U'
    Then result is shown on screen
    """
    ----------------------
    |                    |
    |xxxxxx              |
    |                    |
    |                    |
    ----------------------

    """

  Scenario: Undo multiple
    Given Enter command 'C 20 4'
    And Enter command 'L 1 2 6 2'
    And Enter command 'L 6 3 6 4'
    And Enter command 'B 1 1 *'
    When Enter command 'U'
    And Enter command 'U'
    Then result is shown on screen
    """
    ----------------------
    |                    |
    |xxxxxx              |
    |                    |
    |                    |
    ----------------------

    """

  Scenario: Missing canvas
    When Enter command 'U'
    Then result is shown on screen
    """
    Canvas has to be initialized first
    """

  Scenario: Nothing to undo
    Given Enter command 'C 20 4'
    When Enter command 'U'
    Then result is shown on screen
    """
    ----------------------
    |                    |
    |                    |
    |                    |
    |                    |
    ----------------------

    """
