import java.awt.*;
import java.applet.*;
import java.util.* ;

public class RuleApplet extends Applet {

	// user selected a rule base
	void choice1_Clicked() {

		String rbName = choice1.getSelectedItem() ;

		if (rbName.equals("Medicament"))
			currentRuleBase = Medicaments ;

		//if (rbName.equals("Bugs")) currentRuleBase = bugs ;
		//if (rbName.equals("Plants")) currentRuleBase = plants ;
		currentRuleBase.reset() ; // reset the rule base
		Enumeration vars = currentRuleBase.variableList.elements() ;
		while (vars.hasMoreElements())
			choice2.addItem(((RuleVariable)vars.nextElement()).name) ;

		currentRuleBase.displayVariables(textArea3) ;
	}

	// user selected a variable
	void choice2_Clicked(Event event) {

		String varName = choice2.getSelectedItem() ;
		choice3.removeAll() ;
		RuleVariable rvar =
		(RuleVariable)currentRuleBase.variableList.get(varName);
		Enumeration labels = rvar.labels.elements();

		while (labels.hasMoreElements())
			choice3.addItem(((String)labels.nextElement())) ;
	}

	// user selected a value for a variable
	//void choice3_Clicked(Event event)
	void choice3_Clicked(Event event) {

		String varName = choice2.getSelectedItem() ;
		String varValue = choice3.getSelectedItem() ;
		RuleVariable rvar = (RuleVariable)currentRuleBase.variableList.get(varName);
		rvar.setValue(varValue) ;
		textArea3.appendText("\n"+ rvar.name + " set to "+ varValue) ;
	}

	// user pressed Find button -- do an infernece cycle
	void button1_Clicked(Event event) {

		String goal = textField1.getText() ;
		textArea2.appendText("\n --- Starting Inferencing Cycle --- \n");
		currentRuleBase.displayVariables(textArea2) ;
		if (radioButton1.getState() == true)
			currentRuleBase.forwardChain();
		if (radioButton2.getState() == true)
			currentRuleBase.backwardChain(goal);
		currentRuleBase.displayVariables(textArea2) ;
		textArea2.appendText("\n --- Ending Inferencing Cycle --- \n");
	}

	// user pressed Demo button -- do inference with pre-set values
	void button2_Clicked(Event event) {

		String rbName = choice1.getSelectedItem() ;

		if (rbName.equals("Medicaments")) {

			if (radioButton1.getState() == true)
				demoMedicamentsFC(Medicaments);
			if (radioButton2.getState() == true)
				demoMedicamentsBC(Medicaments);
		}

		//else if (rbName.equals("Bugs")) {
		//if (radioButton1.getState() == true) demoBugsFC(bugs);
		//if (radioButton2.getState() == true) demoBugsBC(bugs);
		//} else {
		//if (radioButton1.getState() == true) demoPlantsFC(plants);
		//if (radioButton2.getState() == true) demoPlantsBC(plants);
		//}
	}

	// User press the Reset button
	void button3_Clicked(Event event) {

		//{{CONNECTION
		// Clear the text for TextArea
		textArea1.setText("");
		textArea2.setText("");
		textArea3.setText("");
		//}}
		currentRuleBase.reset() ;
		currentRuleBase.displayRules(textArea1);
		currentRuleBase.displayVariables(textArea3) ;
	}

	public void init() {

		super.init();
		// Note, this code is generated by Visual Cafe
		//{{INIT_CONTROLS
		setLayout(null);
		addNotify();
		resize(624,527);
		button1 = new java.awt.Button("Find Goal");
		button1.reshape(36,468,108,30);
		add(button1);
		button2 = new java.awt.Button("Run Demo");
		button2.reshape(228,468,108,30);
		add(button2);
		button3 = new java.awt.Button("Reset");
		button3.reshape(444,468,108,30);
		add(button3);
		textArea1 = new java.awt.TextArea();
		textArea1.reshape(12,48,312,144);
		add(textArea1);
		textArea2 = new java.awt.TextArea();
		textArea2.reshape(12,216,600,168);
		add(textArea2);
		label2 = new java.awt.Label("Trace Log");
		label2.reshape(24,192,168,24);
		add(label2);
		label1 = new java.awt.Label("Rule Base");
		label1.reshape(24,12,96,24);
		add(label1);
		choice1 = new java.awt.Choice();
		add(choice1);
		choice1.reshape(132,12,192,24);
		Group1 = new CheckboxGroup();
		radioButton1 = new java.awt.Checkbox("Forward Chain", Group1, false);
		radioButton1.reshape(36,396,156,21);
		add(radioButton1);
		choice3 = new java.awt.Choice();
		add(choice3);
		choice3.reshape(480,36,135,24);
		label5 = new java.awt.Label("Value");
		label5.reshape(480,12,95,24);
		add(label5);
		choice2 = new java.awt.Choice();
		add(choice2);
		choice2.reshape(336,36,137,24);
		textArea3 = new java.awt.TextArea();
		textArea3.reshape(336,72,276,122);
		add(textArea3);
		label4 = new java.awt.Label("Variable");
		label4.reshape(336,12,109,24);
		add(label4);
		radioButton2 = new java.awt.Checkbox("Backward Chain", Group1, false);
		radioButton2.reshape(36,420,156,24);
		add(radioButton2);
		textField1 = new java.awt.TextField();
		textField1.reshape(324,420,142,27);
		add(textField1);
		label3 = new java.awt.Label("Goal");
		label3.reshape(324,384,80,30);
		add(label3);
		//}}
		// initialize the rule applet
		frame = new Frame("Ask User") ;
		frame.resize(50,50) ;
		frame.setLocation(100,100) ;
		choice1.addItem("Medicaments") ;
		//choice1.addItem("Bugs"") ;
		//choice1.addItem("Plants") ;
		Medicaments = new RuleBase("Medicaments Rule Base" ) ;
		Medicaments.setDisplay(textArea2) ;
		initMedicamentsRuleBase(Medicaments) ;
		currentRuleBase = Medicaments ;
		//bugs = new RuleBase("Bugs Rule Base") ;
		//bugs.setDisplay(textArea2) ;
		//initBugsRuleBase(bugs) ;
		//plants = new RuleBase("Plants Rule Base") ;
		//plants.setDisplay(textArea2) ;
		//initPlantsRuleBase(plants) ;
		// initialize textAreas and list controls
		currentRuleBase.displayRules(textArea1) ;
		currentRuleBase.displayVariables(textArea3) ;
		radioButton1.setState(true) ;
		choice1_Clicked() ; // fill variable list
	}

	// Note: this is Java 1.0.2 event model
	public boolean handleEvent(Event event) {

		if (event.target == button1 && event.id == Event.ACTION_EVENT) {
			button1_Clicked(event);
			return true;
		}

		if (event.target == button2 && event.id == Event.ACTION_EVENT) {
			button2_Clicked(event);
			return true;
		}

		if (event.target == button3 && event.id == Event.ACTION_EVENT) {
			button3_Clicked(event);
			return true;
		}

		if (event.target == dlg && event.id == Event.ACTION_EVENT) {
			return dlg.handleEvent(event);
		}

		if (event.target == choice1 && event.id == Event.ACTION_EVENT) {
			choice1_Clicked();
			return true;
		}

		if (event.target == choice2 && event.id == Event.ACTION_EVENT) {
			choice2_Clicked(event);
			return true;
		}

		if (event.target == choice3 && event.id == Event.ACTION_EVENT) {
			choice3_Clicked(event);
			return true;
		}

		return super.handleEvent(event);
	}

	// Note this code is generated by Visual Cafe
	//{{DECLARE_CONTROLS
	java.awt.Button button1;
	java.awt.Button button2;
	java.awt.Button button3;
	java.awt.TextArea textArea1;
	java.awt.TextArea textArea2;
	java.awt.Label label2;
	java.awt.Label label1;
	java.awt.Choice choice1;
	java.awt.Checkbox radioButton1;
	CheckboxGroup Group1;
	java.awt.Choice choice3;
	java.awt.Label label5;
	java.awt.Choice choice2;
	java.awt.TextArea textArea3;
	java.awt.Label label4;
	java.awt.Checkbox radioButton2;
	java.awt.TextField textField1;
	java.awt.Label label3;
	//}}
	static Frame frame ;
	static RuleVarDialog dlg ;
	//static RuleBase bugs ;
	//static RuleBase plants ;
	static RuleBase Medicaments ;
	static RuleBase currentRuleBase ;
	//....
	// Rule base definitions

	//display dialog to get user value for a variable
	static public String waitForAnswer(String prompt, String labels) {

		// position dialog over parent dialog
		Point p = frame.getLocation() ;
		dlg = new RuleVarDialog(frame, true) ;
		dlg.label1.setText(" "  + prompt + "\n (" + labels + ")");
		dlg.setLocation(400, 250) ;
		dlg.show() ;
		String ans = dlg.getText() ;
		return ans ;
	}

	//initialize the Medicaments rule base
	public void initMedicamentsRuleBase(RuleBase rb) {

		rb.goalClauseStack = new Stack() ; // goals and subgoals
		rb.variableList = new Hashtable() ;
		
		RuleVariable malady = new RuleVariable("Malady") ;
		malady.setLabels("Gastritis Poisoning Migraine Angina Allergy") ;
		malady.setPromptText("What kind of vehicle is it?");
		rb.variableList.put(malady.name,malady) ;
		
		RuleVariable temperature = new RuleVariable("temperature") ;
		temperature.setLabels("high medium low") ;
		temperature.setPromptText("How your temperature is ?") ;
		rb.variableList.put(temperature.name, temperature) ;
		
		RuleVariable duration = new RuleVariable("duration") ;
		duration.setLabels("1day 2days +3days") ;
		duration.setPromptText("since when you have sickness?") ;
		rb.variableList.put(duration.name,duration) ;
		
		RuleVariable pain = new RuleVariable("pain") ;
		pain.setLabels("articular abdominal Toothache") ;
		pain.setPromptText("What kind of pain you have?") ;
		rb.variableList.put(pain.name,pain) ;
		
		RuleVariable other = new RuleVariable("other") ;
		other.setLabels("Vomiting Vertigo Diarrhea") ;
		other.setPromptText("Have you something to add?");
		rb.variableList.put(other.name,other) ;
		
		RuleVariable Bleeding = new RuleVariable("Bleeding") ;
		Bleeding.setLabels("no yes") ;
		Bleeding.setPromptText("How many doors does it have?") ;
		rb.variableList.put(Bleeding.name,Bleeding) ;
		
		// Note: at this point all variables values are NULL
		Condition cEquals = new Condition("=") ;
		Condition cNotEquals = new Condition("!=") ;
		Condition cLessThan = new Condition("<") ;

		// define rules
		rb.ruleList = new Vector() ;
		Rule Gastritis = new Rule(rb, "Gastritis",
		new Clause(temperature,cEquals, "low") ,
		new Clause(other,cEquals, "Vertigo"),
		new Clause(pain, cEquals, "Toothache"),
		new Clause(malady, cEquals, "Gastritis")) ;
		
		Rule Poisoning = new Rule(rb, "Poisoning",
		new Clause(temperature,cEquals, "high") ,
		new Clause(other,cEquals, "Vomiting"),
		new Clause(pain, cEquals, "abdominal"),
		new Clause(malady, cEquals, "Poisoning")) ;
		
		Rule Migraine = new Rule(rb, "Migraine",
		new Clause(temperature,cEquals, "low") ,
		new Clause(other,cEquals, "Diarrhea"),
		new Clause(pain,cEquals, "abdominal"),
		new Clause(malady,cEquals, "Migraine")) ;
		
		Rule Angina = new Rule(rb, "Angina",
		new Clause(temperature,cEquals, "medium") ,
		new Clause(duration,cEquals, "+3days"),
		new Clause(Bleeding,cEquals, "no"),
		new Clause(malady,cEquals, "Angina")) ;
		
		Rule Allergy = new Rule(rb, "Allergy",
		new Clause(temperature,cEquals, "high") ,
		new Clause(duration,cEquals, "2days"),
		new Clause(Bleeding,cEquals, "yes"),
		new Clause(malady,cEquals, "Allergy")) ;
		
		/*Rule MiniVan = new Rule(rb, "miniVan",
		new Clause(temperature,cEquals, "automobile") ,
		new Clause(duration,cEquals, "medium"),
		new Clause(Bleeding,cEquals, "3"),
		new Clause(malady,cEquals, "MiniVan")) ;
		
		Rule SUV = new Rule(rb, "SUV",
		new Clause(temperature,cEquals, "automobile") ,
		new Clause(duration,cEquals, "large"),
		new Clause(Bleeding,cEquals, "4"),
		new Clause(malady,cEquals, "Sports_Utility_Vehicle")) ;
		
		Rule Cycle = new Rule(rb, "Cycle",
		new Clause(other,cLessThan, "4") ,
		new Clause(temperature,cEquals, "cycle")) ;
		
		Rule Automobile = new Rule(rb, "Automobile",
		new Clause(other,cEquals, "4") ,
		new Clause(pain,cEquals, "yes"),
		new Clause(temperature,cEquals, "automobile")) ;*/
	}

	public void demoMedicamentsFC(RuleBase rb) {

		textArea2.appendText("\n --- Starting Demo ForwardChain ---\n ") ;
		// should be a Mini-Van
		((RuleVariable)rb.variableList.get("malady")).setValue(null) ;
		((RuleVariable)rb.variableList.get("temperature")).setValue(null) ;
		((RuleVariable)rb.variableList.get("duration")).setValue("1day") ;
		((RuleVariable)rb.variableList.get("pain")).setValue("articular") ;
		((RuleVariable)rb.variableList.get("other")).setValue("Vomiting") ;
		((RuleVariable)rb.variableList.get("Bleeding")).setValue("yes") ;
		rb.displayVariables(textArea2) ;
		rb.forwardChain() ; // chain until quiescence...
		textArea2.appendText("\n --- Stopping Demo ForwardChain! ---\n") ;
		rb.displayVariables(textArea2);
	}

	public void demoMedicamentsBC(RuleBase rb) {

		textArea2.appendText("\n --- Starting Demo BackwardChain ---\n ") ;
		// should be a minivan
		((RuleVariable)rb.variableList.get("malady")).setValue(null) ;
		((RuleVariable)rb.variableList.get("temperature")).setValue(null) ;
		((RuleVariable)rb.variableList.get("duration")).setValue("1day") ;
		((RuleVariable)rb.variableList.get("pain")).setValue("articular") ;
		((RuleVariable)rb.variableList.get("other")).setValue("Vomiting") ;
		((RuleVariable)rb.variableList.get("Bleeding")).setValue("yes") ;
		rb.displayVariables(textArea2) ;
		rb.backwardChain("vehicle") ; // chain until quiescence...
		textArea2.appendText("\n --- Stopping Demo BackwardChain! ---\n ") ;
		rb.displayVariables(textArea2) ;
	}
}
