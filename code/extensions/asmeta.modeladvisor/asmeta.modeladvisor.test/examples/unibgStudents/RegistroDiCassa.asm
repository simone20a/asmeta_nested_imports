// ALDO BIZIAK
asm RegistroDiCassa

import ../../../../../../asm_examples/STDL/StandardLibrary

signature:
	domain PrezzoPizza subsetof Integer
	domain Quantita subsetof Integer
	domain TotaleDomain subsetof Integer
	

	abstract domain Pizza
	enum domain Stati = { ATTENDI_ORDINAZIONI | SCEGLI_SE_AGGIUNGERE_PIZZA | CHIUSO | SCEGLI_TIPO_DI_PIZZA | PIZZASTANDARD_SELEZIONATA | ALTRAPIZZA_SELEZIONATA}
	enum domain Servizio = {NEWORDINE | EXIT}
	enum domain AggiungiPizza = {SI | NO}
	enum domain SelezioneTipoDiPizza = {STANDARD | OTHER}

	dynamic controlled pizzaCorrente: Pizza	
	dynamic controlled statoCassa: Stati
	//dynamic controlled outMess: Any			//messaggio stampato sul video
	//static 	getPrezzo: Pizza -> Integer
	static 	getPrezzo: Pizza -> PrezzoPizza  

	dynamic monitored servizioSelezionato: Servizio 
	dynamic monitored pizzaInserita: Pizza 
	dynamic monitored sceltaDiAggiuntaPizza: AggiungiPizza
	dynamic monitored sceltaDelTipoPizza: SelezioneTipoDiPizza
	//dynamic monitored insertQuantita: Integer
	dynamic monitored insertQuantita: Quantita
	//dynamic monitored insertPrezzo: Integer
	dynamic monitored insertPrezzo: PrezzoPizza
	
	static margherita: Pizza
	static marinara: Pizza
	static capricciosa: Pizza
	//dynamic controlled totale: Integer
	dynamic controlled totale: TotaleDomain
	
	
	
	dynamic controlled attendiOrdinazioni: Boolean

definitions:
	domain PrezzoPizza = {3:5}
	domain Quantita = {1:2}
	domain TotaleDomain = {0:36}
	
	function getPrezzo($c in Pizza) = 	//$c per ricevere come input unastringa
		switch($c)
			case margherita : 4
			case marinara : 3
			case capricciosa : 5	//ritorna un intero (1,2 o 3)
		endswitch

	macro rule r_aggiungiPizzaStandardAlTotale =
	//seq
	forall $x in Pizza with $x=pizzaCorrente do
		totale := totale+getPrezzo($x)*insertQuantita
	//totale := totale+getPrezzo(pizzaCorrente)*insertQuantita
	//outMess := "prezzo totale aggiornato"	
	//endseq
	
	macro rule r_aggiungiAlTotale =
	//seq
	totale := totale +  insertQuantita*insertPrezzo
	//outMess := "prezzo totale aggiornato"	
	//endseq
	

	//controlla che ci sia una quantita' minima di soldi
	macro rule r_attendiOrdinazioni =
	if(statoCassa=ATTENDI_ORDINAZIONI) then
	par
		if (servizioSelezionato=EXIT) then
			//par
				statoCassa := CHIUSO
				//outMess := "Registro di cassa chiuso!"
			//endpar
		endif
		
		if(servizioSelezionato=NEWORDINE) then
			par
				totale := 0
				statoCassa := SCEGLI_SE_AGGIUNGERE_PIZZA //l'utente deve selezionare una cifra standard o inserire la cifra
				//outMess := "Scegli se aggiungere una nuova pizza"
			endpar
		endif
		endpar
	endif
	
	macro rule r_scegliSeAggiungerePizza =
	if(statoCassa=SCEGLI_SE_AGGIUNGERE_PIZZA) then
		par
			if(sceltaDiAggiuntaPizza=SI) then
				//par
					statoCassa := SCEGLI_TIPO_DI_PIZZA
					//outMess := "Scegli il tipo di pizza desiderata:"
				//endpar
			endif
			if(sceltaDiAggiuntaPizza=NO) then
				//seq
				//outMess := "Il totale �:"
				//outMess := totale
				statoCassa := ATTENDI_ORDINAZIONI
				//endseq
			endif
			endpar
		endif

	macro rule r_scegliTipoDiPizza =
		if(statoCassa=SCEGLI_TIPO_DI_PIZZA)then
		par
			if(sceltaDelTipoPizza=STANDARD)then
			//par 
			statoCassa := PIZZASTANDARD_SELEZIONATA
			//outMess := "Inserisci il nome di una pizza dell'elenco:"
			//endpar
			endif
			
			if(sceltaDelTipoPizza=OTHER)then
			//par 
			statoCassa := ALTRAPIZZA_SELEZIONATA
			//outMess := "Inserisci il nome di una nuova pizza:"
			//endpar
			endif
			endpar
		endif
	
	
	macro rule r_pizzaStandardSelezionata =
	if(statoCassa=PIZZASTANDARD_SELEZIONATA) then

			if((exist $c in Pizza with $c=pizzaInserita) ) then	
				//seq
				par
					pizzaCorrente := pizzaInserita
					r_aggiungiPizzaStandardAlTotale[]			
					statoCassa := SCEGLI_SE_AGGIUNGERE_PIZZA
				endpar
				//endseq
			else 
				//seq
				//outMess := "Questa pizza non � presente in elenco!"
				statoCassa := SCEGLI_SE_AGGIUNGERE_PIZZA
				//endseq
			endif

		endif
	
	macro rule r_altraPizzaSelezionata =
		if(statoCassa=ALTRAPIZZA_SELEZIONATA) then
			//seq
			par
			r_aggiungiAlTotale[]
			statoCassa := SCEGLI_SE_AGGIUNGERE_PIZZA
			endpar
			//endseq
		endif

	main rule r_Main =
		par
			attendiOrdinazioni := not(attendiOrdinazioni)
			if(attendiOrdinazioni) then
				r_attendiOrdinazioni[]
			else
				par
					r_scegliSeAggiungerePizza[]
					r_scegliTipoDiPizza[]
					r_pizzaStandardSelezionata[]
					r_altraPizzaSelezionata[]
				endpar
			endif
		endpar
	/*seq
	r_attendiOrdinazioni[]
		par
				
				r_scegliSeAggiungerePizza[]
				r_scegliTipoDiPizza[]
				r_pizzaStandardSelezionata[]
				r_altraPizzaSelezionata[]
			endpar
	endseq*/

// define the initial states 
default init s0:
	function totale = 0
	function statoCassa = ATTENDI_ORDINAZIONI
	 
	function attendiOrdinazioni = true