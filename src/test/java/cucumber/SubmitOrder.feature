@tag
Feature: Purchase the order from ecommerce website

Background:
Given I landed on Ecommerce Page

@tag2
Scenario Outline: Positive test of submitting the order
Given Logged in with username <name> and password <password>
When I add product <productName> to Cart
And Checkout <productName> and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

Examples:
|	name					|	password		|	productName	|
|	er.mamta25@gmail.com	|	Iamqueen@00 	| 	ZARA COAT 3 |