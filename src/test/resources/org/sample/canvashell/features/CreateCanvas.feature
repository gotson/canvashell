Feature: create blank canvas

  Background:
    Given Reset shell

  Scenario: Large canvas
    When Enter command 'C 20 4'
    Then result is shown on screen
    """
    ----------------------
    |                    |
    |                    |
    |                    |
    |                    |
    ----------------------

    """

  Scenario: Small canvas
    When Enter command 'C 1 1'
    Then result is shown on screen
    """
    ---
    | |
    ---

    """

  Scenario: Override canvas
    Given Enter command 'C 20 4'
    When Enter command 'C 1 1'
    Then result is shown on screen
    """
    ---
    | |
    ---

    """