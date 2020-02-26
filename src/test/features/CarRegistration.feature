Feature: To test the car registration functionality from auto company

  @success_test
  Scenario: As a customer, I want to select the type of car I want to sell, so that I can receive an offer from auto company
    Given feature is available to the user
    When user selects manufacturer "Chevrolet"
    And selects main-type "Cruze"
    And user retrieve built-dates by selected main-type
    Then the status code should be 200
    And response includes the following dates
      | 2009 | 2009 |
      | 2010 | 2010 |
      | 2011 | 2011 |
      | 2012 | 2012 |
      | 2013 | 2013 |
      | 2014 | 2014 |
      | 2015 | 2015 |
      | 2016 | 2016 |


  @error
  Scenario: User should not be authorised without wa_key
    Given feature is available to the user
    When user selects "Chevrolet" manufacturer without wa_key
    Then the status code should be 401

  @error
  Scenario: User calls web service to get manufacturer with incorrect wa_key
    Given feature is available to the user
    When user selects "Chevrolet" manufacturer with invalid wa_key
    Then the status code should be 403

  @error
  Scenario: As a customer, I wont be able to delete existing record
    Given feature is available to the user
    When user try to delete "Chevrolet" manufacturer
    Then the status code should be 403

  @error
  Scenario: As a customer, I wont be allowed to create new record
    Given feature is available to the user
    When user try to create new record for manufacturer
    Then the status code should be 405

  @error
  Scenario: As a user I want to make sure the bad request is handled
    Given feature is available to the user
    When user selects manufacturer "Chevrolet"
    And user sends bad request to fetch main-types
    Then the status code should be 400