Feature: GNV_TIRR

@confrontoGNV_TIRR
Scenario: GNV_TIRR
	Given utente apre browser GNV GNV_TIRR
	When utente chiude popup GNV_TIRR
	And utente compila campi GNV_TIRR
	And utente seleziona sistemazione GNV_TIRR
	And recupero totale offerta GNV_TIRR
	And utente chiude browser GNV_TIRR
	And utente apre browser TIRR GNV_TIRR
	And utente bypassa frame GNV_TIRR
	And utente inserisce dati viaggio GNV_TIRR
	And recupera prezzo GNV_TIRR
	Then confronto prezzi GNV_TIRR