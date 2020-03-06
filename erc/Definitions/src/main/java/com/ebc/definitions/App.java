package com.ebc.definitions;

import javax.swing.JFrame;

import com.ebc.definitions.test.TestDefinitionAppMain;
import com.ebc.definitions.test.TestDefinitionMain;



/**
 * Hello world!
 *
 */
public class App extends JFrame {
	public static void main(String[] args) {
		
		
		
		App app= new App();
		
		DefinitionsPanel panel = new  DefinitionsPanel();

		
		
		app.add(panel);
		app.setVisible(true);
		app.setSize(1500, 600);
		app.setLocationRelativeTo(null);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
       // System.out.println( "Hello World!" );

		//appPanel panel = new appPanel();
		
	}
}
