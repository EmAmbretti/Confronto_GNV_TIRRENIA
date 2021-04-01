Feature: GNV_TIRR_GRM

@confrontoCSV_GNV_TIRR_GRM
Scenario: GNV_TIRR_GRM
	Given utente apre browser GNV GNV_TIRR_GRM
	When utente chiude popup GNV_TIRR_GRM
	And utente compila campi GNV_TIRR_GRM
	And utente seleziona sistemazione GNV_TIRR_GRM
	And recupero totale offerta GNV_TIRR_GRM
	And utente chiude browser GNV_TIRR_GRM
	#And utente apre browser TIRR GNV_TIRR_GRM
	#And utente bypassa frame TIRR GNV_TIRR_GRM
	#And utente inserisce dati viaggio TIRR GNV_TIRR_GRM
	#And recupera prezzo TIRR GNV_TIRR_GRM
	#And utente apre browser GRM GNV_TIRR_GRM
	#And utente bypassa frame GRM GNV_TIRR_GRM
	#And utente inserisce dati viaggio GRM GNV_TIRR_GRM
	#And recupera prezzo GRM GNV_TIRR_GRM
	Then confronto prezzi GNV_TIRR_GRM