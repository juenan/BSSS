package jueban;


import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import jueban.model.*;

public class AccountPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8897882175210653893L;
	private JTable table;
	private JButton addpay;
	private JButton addcollect;
	private JButton del;
	private JButton ini;
	private JPanel buttonpanel;
	private AccountModel amodel;
	private CustomerModel cusmodel;
	private DialogPanel dialog;
	private AccountAddPanel apanel;
	private Frame frame;
	
	public AccountPanel(AccountModel amodel,CustomerModel cusmodel,Frame frame){
		this.amodel = amodel;
		this.cusmodel = cusmodel;
		table = new JTable(amodel);
		JScrollPane spane = new JScrollPane(table);
		addpay = new JButton("���Ӹ��");
		addcollect = new JButton("�����տ");
		del = new JButton("ɾ������");
		ini = new JButton("��ʼ����Ŀ");
		buttonpanel = new JPanel();
		buttonpanel.add(addpay);
		buttonpanel.add(addcollect);
		buttonpanel.add(del);
		buttonpanel.add(ini);
		this.frame = frame;
		apanel = new AccountAddPanel(cusmodel);
		dialog = new DialogPanel(apanel);
		addpay.addActionListener(new AddAction());
		addcollect.addActionListener(new AddAction());
		del.addActionListener(new DelAction());
		ini.addActionListener(new IniAction());
		
		setLayout(new BorderLayout());
		add(spane,BorderLayout.CENTER);
		add(buttonpanel,BorderLayout.SOUTH);
	}
	
	
	public class AddAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("���Ӹ��")){
				dialog.showDialog(frame,"����:");
				try {
					if(!amodel.add(cusmodel.fin(apanel.getName()),apanel.getPrice(),Account.OUT)){
						JOptionPane.showMessageDialog(frame,"���ʧ��");
					}
				} catch (HeadlessException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}else if(e.getActionCommand().equals("�����տ")){
				dialog.showDialog(frame,"�տ�:");
				try {
					if(!amodel.add(cusmodel.fin(apanel.getName()),apanel.getPrice(),Account.IN)){
						JOptionPane.showMessageDialog(frame,"���ʧ��");
					}
				} catch (HeadlessException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	
	public class DelAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(table.getSelectedRowCount()>1){
				try {
					if(!amodel.del(table.getSelectedRows())){
						JOptionPane.showMessageDialog(frame,"ɾ��ʧ��");
					}
				} catch (HeadlessException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			} else
				try {
					if(!amodel.del(table.getRowCount())){
						JOptionPane.showMessageDialog(frame,"ɾ��ʧ��");
					}
				} catch (HeadlessException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			
		}
		
	}
	
	public class IniAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = new JPanel();
			JTextField price = new JTextField();
			panel.setLayout(new GridLayout(1,2));
			panel.add(new JLabel("���:"));
			panel.add(price);
			DialogPanel dialog = new DialogPanel(panel);
			dialog.showDialog(frame,"��ʼ����˾�˻�");
			
			Account.setInitial(Integer.parseInt(price.getText()));
			
			amodel.fireTableDataChanged();
			
			
			
		}
		
	}
	
	

}
