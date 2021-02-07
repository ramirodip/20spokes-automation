@Login
Feature: Login Functionalities

  Scenario: Login With a Valid Account for Patient
    Given I want to login as "Patient" profile
    When I perform the login with valid credentials
    Then I should be able to see the "Dashboard" page

  Scenario: Login With a Valid Account for Care Manager
    Given I want to login as "Care Manager" profile
    When I perform the login with valid credentials
    Then I should be able to see the "Dashboard" page

  Scenario: Login With a Valid Account for Provider
    Given I want to login as "Provider" profile
    When I perform the login with valid credentials
    Then I should be able to see the "Dashboard" page
