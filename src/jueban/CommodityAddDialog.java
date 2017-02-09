package jueban;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import jueban.model.Commodity;

public class CommodityAddDialog extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -232653004739463739L;
	private JTextField name;
	private JTextField size;
	private JTextField buyprice;
	private JTextField sellprice;
	private JDialog dialog;
	private JButton cancel;
	private JButton okbutton;
	private boolean ok = false;
	
	
	public CommodityAddDialog(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,4));
		panel.add(new JLabel("商品名称"));
		panel.add(name = new JTextField());
		panel.add(new JLabel("商品型号"));
		panel.add(size = new JTextField());
		panel.add(new JLabel("默认进价"));
		panel.add(buyprice = new JTextField("0"));
		panel.add(new JLabel("默认售价"));
		panel.add(sellprice = new JTextField("0"));
		JPanel buttonpanel = new JPanel();
		buttonpanel.add(okbutton = new JButton("确定"));
		buttonpanel.add(cancel = new JButton("取消"));
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
		
		
		
		
		add(panel,BorderLayout.NORTH);
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
		size.setText("");
		buyprice.setText("0");
		sellprice.setText("0");
		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
		
	}
	
	

	
	
	public Commodity getCommodity(){
		if(name.getText().equals("") || size.getText().equals("")){
			return null;
		}
		
			Commodity commodity = new Commodity(name.getText(),size.getText(),
					Integer.parseInt(buyprice.getText()),Integer.parseInt(sellprice.getText()));
			return commodity;
		

		
	}

}
