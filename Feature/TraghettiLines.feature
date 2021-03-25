Feature: TRAGHETTI LINES

@traghettiLines
Scenario: TRAGHETTI LINES
	Given utente apre browser TRAGHETTILINES
	And utente inserisce dati viaggio TRAGHETTILINES
	And recupero importi TRAGHETTILINES
	Then confronto prezzi TRAGHETTILINES