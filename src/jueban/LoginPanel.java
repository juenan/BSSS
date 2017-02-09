package jueban;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class LoginPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -597983528139505108L;
	private JTextField ip;
	private JList<String> identity;
	
	
	public LoginPanel(){
		ip = new JTextField("127.0.0.1");
		String iden[] = {"库存管理员","销售人员","财务人员","库存管理人员"};
		identity = new JList<String>(iden);
		setLayout(new GridLayout(2,2));
		add(new JLabel("服务器IP:"));
		add(ip);
		add(new JLabel("身份:"));
		identity.setSelectedIndex(0);
		identity.setVisibleRowCount(1);
		JScrollPane spane = new JScrollPane(identity);
		
		add(spane);
		
	}
	
	public String getIp(){
		String ip = this.ip.getText();
		this.ip.setText("");
		return ip;
	}
	
	
	public String getIdentity(){
		String iden = this.identity.getSelectedValue();
		return iden;
	}

}
