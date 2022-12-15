package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConnexionWindow implements ActionListener {
	
	JFrame connexionFrame;
	JPanel loginInfoPanel, connexionPanel;
    JTextField idField, mdpField;
	JLabel idLabel, mdpLabel, infoConnexion;
	JButton connexionButton;
	
	public ConnexionWindow() {
		
		  
        //Create and set up the window.
		connexionFrame = new JFrame("Clavardage Connexion");
		connexionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//connexionFrame.setMinimumSize(new Dimension(500, 200));
		
	    //Create and set up the panel.
		loginInfoPanel = new JPanel(new GridLayout(2,2));
		connexionPanel = new JPanel(new GridLayout(2,1));
		
		
        //Add the widgets.
        addWidgets();

        //Set the default button.
		connexionFrame.getRootPane().setDefaultButton(connexionButton);
        
        //Add the panel to the window.
		connexionFrame.getContentPane().add(loginInfoPanel, BorderLayout.CENTER);
        connexionFrame.getContentPane().add(connexionPanel, BorderLayout.SOUTH);
        
        //Display the window.
        connexionFrame.pack();
        connexionFrame.setVisible(true);
    }
	
	
	/**
     * Create and add the widgets.
     */
    private void addWidgets() {
        //Create widgets.
    	idField = new JTextField(2);
    	mdpField =  new JTextField(2);
    	idLabel = new JLabel("Id : ");
    	mdpLabel = new JLabel("Mdp : ");
    	infoConnexion = new JLabel("");
    	connexionButton = new JButton("Connexion");

        //Listen to events from the Convert button.
    	connexionButton.addActionListener(this);

        //Add the widgets to the container.
    	loginInfoPanel.add(idLabel);
    	loginInfoPanel.add(idField);
    	loginInfoPanel.add(mdpLabel);
    	loginInfoPanel.add(mdpField);    	
    	connexionPanel.add(connexionButton);
    	connexionPanel.add(infoConnexion);
        
    	//idLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    	//mdpLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }
	
		
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		infoConnexion.setText("Connexion en cours");
	}
	
	 /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
    	
    	//Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        ConnexionWindow connexion = new ConnexionWindow();
    }
     

	public static void main(String[] args) {
		 //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

	}


}
