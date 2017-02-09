package jueban;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import jueban.model.Commodity;
import jueban.model.Customer;

public class CustomerAddDialog extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2746316659871480231L;
	private JTextField name;
	private JTextField phone;
	private JDialog dialog;
	private JButton okbutton;
	private JButton cancel;
	private boolean ok = false;
	public CustomerAddDialog(){
		okbutton = new JButton("确定");
		cancel = new JButton("取消");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		name = new JTextField();
		phone = new JTextField();
		panel.add(new JLabel("客户名称:"));
		panel.add(name);
		panel.add(new JLabel("联系方式:"));
		panel.add(phone);
		JPanel buttonpanel = new JPanel();
		buttonpanel.add(okbutton);
		buttonpanel.add(cancel);
		setLayout(new BorderLayout());
		okbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ok = true;
				dialog.setVisible(false);
			}
			
	});
		
		
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				
			}
			
		});
		
		
		add(panel,BorderLayout.CENTER);
		add(buttonpanel,BorderLayout.SOUTH);
		
		
	}
	
	
	
	public boolean showDialog(Component parent,String title){
		ok = false;
		Frame owner = null;
		if(parent instanceof Frame) owner = (Frame) parent;
		else owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class,parent);
		
		if(dialog == null || dialog.getOwner() != owner){
			dialog = new JDialog(owner,true);
			dialog.add(this);
			dialog.getRootPane().setDefaultButton(okbutton);
			dialog.pack();
		}
		Point p = owner.getLocation();
		dialog.setLocation((int)p.getX()+owner.getWidth()/2-(dialog.getWidth()/2),(int)p.getY()+owner.getHeight()/2-(dialog.getHeight()/2));
		name.setText("");
		phone.setText("");
		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
		
	}
	
	
	
	
	public Customer getCustomer(){
		if(name.getText().equals("") || phone.getText().equals("")){
			return null;
		}
		Customer customer = new Customer(name.getText(),phone.getText());
			
			return customer;
		

		
	}

}
