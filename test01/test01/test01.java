package test01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class test01 extends JFrame {
	
	 	JButton[] buttons = new JButton[24]; 

	    public test01() {
	        setSize(350, 550); 
	        setTitle("계산기"); 
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	  
	        
	        JPanel panel = new JPanel();    
	        JTextField text = new JTextField("0"); 
	        text.setFont(new Font("0",Font.PLAIN, 24));
	        text.setEditable(false); 
	        text.setBackground(Color.white);         
	        text.setHorizontalAlignment(SwingConstants.RIGHT);
	        text.setPreferredSize(new java.awt.Dimension(500, 100));
	        add(panel,BorderLayout.CENTER);
	        add(text, BorderLayout.NORTH); 
	     
	        panel.setLayout(new GridLayout(6, 4, 3, 3)); 
	       
	        Color buttonColor = Color.WHITE;
	        String[] buttonLabels = {
	            "%", "CE", "C", "지우기", 
	            "1/x", "x^", "2루트x", "÷",
	             "7", "8", "9", "x", 
	            "4", "5", "6", "-", 
	            "1", "2", "3", "+",
	            "+/-", "0", ".", "="
	        };
	        
	        for (int i = 0; i < buttons.length; i++) {
	            buttons[i] = new JButton(buttonLabels[i]);
	            buttons[i].setBackground(buttonColor);       
	            buttons[i].setPreferredSize(new java.awt.Dimension(100, 30));
	            
	            panel.add(buttons[i]); 
	        }
	      
	        
	        
	        
	        setVisible(true); 
	      
	    }

	public static void main(String[] args) {
	    new test01(); 

	}
	

}



