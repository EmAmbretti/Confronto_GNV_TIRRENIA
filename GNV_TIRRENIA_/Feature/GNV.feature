Feature: GNV

@apreGNV
Scenario: GNV
	Given utente apre browser GNV
	When utente seleziona viaggio
	And utente compila campi
	And utente seleziona sistemazione
	And recupero totale offerta
	And utente chiude browser
	And utente apre browser TIRRENIA
	And utente inserisce dati viaggio TIRRENIA
	And controllo prezzo TIRRENIA
	Then confronto prezzi