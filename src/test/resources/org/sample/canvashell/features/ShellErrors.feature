Feature: shell input errors

  Scenario: Missing argument
    When Enter command 'C 20'
    Then result message contains "parameter"
    And result message contains "should be specified"

  Scenario: Too many arguments
    When Enter command 'B 1 1 2 2'
    Then result message contains "too many arguments"

  Scenario: Command not found
    When Enter command 'A'
    Then result message contains "no command found"

  Scenario: Incorrect argument type
    When Enter command 'R a b c d'
    Then result message contains "failed to convert from type"
