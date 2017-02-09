package jueban;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Client {
	
	public static void main(String args[]){
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				JFrame frame = new BSSFrame();
				frame.setVisible(true);
				
			}
			
		});
	}
	

	
}
