Feature: bucket fill

  Background:
    Given Reset shell

  Scenario: Canvas fill
    Given Enter command 'C 20 4'
    When Enter command 'B 2 2 a'
    Then result is shown on screen
    """
    ----------------------
    |aaaaaaaaaaaaaaaaaaaa|
    |aaaaaaaaaaaaaaaaaaaa|
    |aaaaaaaaaaaaaaaaaaaa|
    |aaaaaaaaaaaaaaaaaaaa|
    ----------------------

    """

  Scenario: Canvas refill
    Given Enter command 'C 20 4'
    When Enter command 'B 2 2 a'
    And Enter command 'B 2 2 b'
    Then result is shown on screen
    """
    ----------------------
    |bbbbbbbbbbbbbbbbbbbb|
    |bbbbbbbbbbbbbbbbbbbb|
    |bbbbbbbbbbbbbbbbbbbb|
    |bbbbbbbbbbbbbbbbbbbb|
    ----------------------

    """

  Scenario: Fill around lines
    Given Enter command 'C 20 4'
    And Enter command 'L 6 2 6 4'
    When Enter command 'B 2 2 c'
    Then result is shown on screen
    """
    ----------------------
    |cccccccccccccccccccc|
    |cccccxcccccccccccccc|
    |cccccxcccccccccccccc|
    |cccccxcccccccccccccc|
    ----------------------

    """


  Scenario: Fill with line color then refill removes line
    Given Enter command 'C 20 4'
    And Enter command 'L 6 2 6 4'
    When Enter command 'B 1 1 x'
    When Enter command 'B 2 2 e'
    Then result is shown on screen
    """
    ----------------------
    |eeeeeeeeeeeeeeeeeeee|
    |eeeeeeeeeeeeeeeeeeee|
    |eeeeeeeeeeeeeeeeeeee|
    |eeeeeeeeeeeeeeeeeeee|
    ----------------------

    """

  Scenario: Fill inside rectangles
    Given Enter command 'C 20 8'
    And Enter command 'R 14 1 18 5'
    And Enter command 'R 9 3 16 7'
    When Enter command 'B 15 2 f'
    Then result is shown on screen
    """
    ----------------------
    |             xxxxx  |
    |             xfffx  |
    |        xxxxxxxxfx  |
    |        x    x xfx  |
    |        x    xxxxx  |
    |        x      x    |
    |        xxxxxxxx    |
    |                    |
    ----------------------

    """

  Scenario: Missing canvas
    When Enter command 'B 1 1 a'
    Then result is shown on screen
    """
    Canvas has to be initialized first
    """

  Scenario: Point outside canvas
    Given Enter command 'C 20 4'
    When Enter command 'B 100 1 z'
    Then result is shown on screen
    """
    The point is outside the canvas
    """
