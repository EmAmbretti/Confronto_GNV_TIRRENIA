Feature: TIRRENIA

@apreTIRRENIA
Scenario: TIRRENIA
	Given utente apre browser
	When utente seleziona destinazioni
	And utente clicca scopri offerta
	And utente compila campi
	And utente seleziona data
	And utente seleziona sistemazione
	And utente sceglie servizi
	And utente sceglie assicurazione
	And utente compila dati
	Then recupero totale offerta