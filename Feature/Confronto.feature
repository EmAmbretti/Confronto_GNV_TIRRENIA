Feature: GNV

@confronto
Scenario: GNV
	Given utente apre browser GNV
	When utente chiude popup GNV
	And utente compila campi GNV
	And utente seleziona sistemazione GNV
	And recupero totale offerta GNV
	And utente chiude browser GNV
	And utente apre browser TIRRENIA
	And utente bypassa frame TIRRENIA
	And utente inserisce dati viaggio TIRRENIA
	And recupera prezzo TIRRENIA
	Then confronto prezzi