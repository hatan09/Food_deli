package main00;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


import java.util.*;

public class GUI_Working extends GUI implements ActionListener, MouseListener, ItemListener, KeyListener{
	private static boolean reg_pass_stat = true;
	private static boolean pass2nd_stat = true;
	private static boolean pass2nd_pass = false;
	private static boolean reg_name_stat = false;
	private static boolean reg_mail_stat = false;
	private static boolean reg_mail_valid = false;
	private static boolean reg_phone_stat = false;
	private static boolean reg_phone_exist = false;
	private static boolean reg_address_stat = false;
	private static boolean reg_district_stat = false;
	private static boolean at_login = true;
	
	private static String unit = "VND";
	
	ArrayList<Order> orders = new ArrayList<>();
	
	public GUI_Working() {
		help.setText(FAQs.printFAQs());
		//add listener: general
		btn_help.addActionListener(this);
		btn_x.addActionListener(this);
		btn_xx.addActionListener(this);
		btn_option.addActionListener(this);
		btn_sq_option.addActionListener(this);
		btn_check_updates.addActionListener(this);
		btn_about.addActionListener(this);
		btn_ok.addActionListener(this);
		btn_check_tran.addActionListener(this);
		btn_tran_cancel.addActionListener(this);
		btn_all_trans.addActionListener(this);
		btn_acc_detail.addActionListener(this);
		btn_change_name.addActionListener(this);
		btn_change_phone.addActionListener(this);
		btn_view_rating.addActionListener(this);
		btn_change_pass.addActionListener(this);
		btn_add_pid.addActionListener(this);
		btn_add_address.addActionListener(this);
		btn_remove_address.addActionListener(this);
		
		//add listener: login
		btn_show_pass2nd.addActionListener(this);
		btn_hide_pass2nd.addActionListener(this);
		btn_go.addActionListener(this);
		btn_log.addActionListener(this);
		
		//add listener: choose
		btn_add.addActionListener(this);
		btn_drop.addActionListener(this);
		btn_next.addActionListener(this);
		search.addKeyListener(this);
		
		//add listener: amount
		btn_plus_1.addActionListener(this); 
		btn_plus_5.addActionListener(this);
		btn_minus_1.addActionListener(this); 
		btn_minus_5.addActionListener(this);
		btn_done.addActionListener(this);
		btn_back.addActionListener(this);
		btn_unit.addActionListener(this);
		
		//add listener: both amount n choose
		btn_acc.addActionListener(this);
		btn_out.addActionListener(this);
		
		//add hover effects
		btn_check_tran.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btn_check_tran.setBackground(hovered_btn_option_cl);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btn_check_tran.setBackground(btn_option_cl);
		    }
		});
		//-------------------------------------
		btn_check_updates.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btn_check_updates.setBackground(hovered_btn_option_cl);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btn_check_updates.setBackground(btn_option_cl);
		    }
		});
		//-------------------------------------
		btn_about.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btn_about.setBackground(hovered_btn_option_cl);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btn_about.setBackground(btn_option_cl);
		    }
		});
		//-------------------------------------
		btn_all_trans.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btn_all_trans.setBackground(hovered_btn_option_cl);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btn_all_trans.setBackground(btn_option_cl);
		    }
		});
		//-------------------------------------
		btn_acc_detail.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btn_acc_detail.setBackground(hovered_btn_option_cl);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btn_acc_detail.setBackground(btn_option_cl);
		    }
		});
		//-------------------------------------
		
		
		//add update listener to textfields
		log_pass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	//catch enter
				btn_log.doClick();
			}
		});
		//-------------------------------------
		//following fields are added document listener that catch changes in text
		reg_pass.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				String temp_pass = reg_pass.getText();
				checkRegPass(temp_pass);
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				String temp_pass = reg_pass.getText();
				checkRegPass(temp_pass);
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				String temp_pass = reg_pass.getText();
				checkRegPass(temp_pass);
			}
			
		});
		//-------------------------------------
        reg_pass2nd_show.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				checkPass2nd_show();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkPass2nd_show();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkPass2nd_show();
			}
		});
        
     	//-------------------------------------
        reg_pass2nd_hide.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				checkPass2nd_hide();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkPass2nd_hide();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkPass2nd_hide();
			}
		});
        //------------------------------------- 
        reg_mail.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				checkRegMail();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkRegMail();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkRegMail();
			}
		});
        //-------------------------------------
        reg_phone.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkPhone();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				checkPhone();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkPhone();
			}
        	
        });
        //------------------------------------- 
        
	}
	//below is the place for adding methods to buttons
	@Override
	public void itemStateChanged(ItemEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton JButtonClicked = (JButton)e.getSource();
		//Button : general
		if(JButtonClicked == btn_help) {
			add(scr_help, new Integer(50));
			add(btn_x, new Integer(50));
			revalidate();
			repaint();
		}
		//-------------------------------------
		if(JButtonClicked == btn_x) {
			remove(scr_help);
			remove(btn_x);
			revalidate();
			repaint();
		}
		//-------------------------------------
		if(JButtonClicked == btn_option) {
			showOption();
		}
		//-------------------------------------
		if(JButtonClicked == btn_sq_option) {
			closeOption();
		}
		//-------------------------------------
		if(JButtonClicked == btn_check_updates) {
			closeOption();
			Version.checkForUpdates();
		}
		//-------------------------------------
		if(JButtonClicked == btn_about) {
			closeOption();
			showAbout();
		}
		//-------------------------------------
		if(JButtonClicked == btn_ok) {
			closeOption();
			closeAbout();
		}
		//-------------------------------------
		if(JButtonClicked == btn_check_tran) {
			closeOption();
			showView();
			showTran();
		}
		//-------------------------------------
		if(JButtonClicked == btn_tran_cancel) {
			cancelTran();
		}
		//-------------------------------------
		if(JButtonClicked == btn_all_trans) {
			closeOption();
			showView();
			showAllTrans();
		}
		//-------------------------------------
		if(JButtonClicked == btn_acc_detail) {
			closeOption();
			showView();
			showAcc();
		}
		//-------------------------------------
		if(JButtonClicked == btn_change_name) {
			changeName();
		}
		//-------------------------------------
		if(JButtonClicked == btn_change_phone) {
			changePhone();
		}
		//-------------------------------------
		if(JButtonClicked == btn_view_rating) {
			viewRating();
		}
		//-------------------------------------
		if(JButtonClicked == btn_change_pass) {
			changePass();
		}
		//-------------------------------------
		if(JButtonClicked == btn_add_pid) {
			addPid();
		}
		//-------------------------------------
		if(JButtonClicked == btn_add_address) {
			addAddress();
		}
		//-------------------------------------
		if(JButtonClicked == btn_remove_address) {
			removeAddress();
		}
		//-------------------------------------
		if(JButtonClicked == btn_xx) {
			closeView();
		}
		//-------------------------------------
		
		
		
		//Buttons : login
		if(JButtonClicked == btn_log) {
			if(checkLogin()) {
				toChoose();
			}
		}
		//-------------------------------------
		if(JButtonClicked == btn_show_pass2nd) { //unhide the comfirm password
			pass2nd_stat = false;
			String temp = reg_pass2nd_hide.getText();
			remove(btn_show_pass2nd);
			remove(reg_pass2nd_hide);
			add(btn_hide_pass2nd, new Integer(1));
			add(reg_pass2nd_show, new Integer(1));		//show pass2nd
			reg_pass2nd_show.setText(temp);
			revalidate();
			repaint();
		}
		//-------------------------------------
		if(JButtonClicked == btn_hide_pass2nd) { //hide the comfirm password
			pass2nd_stat = true;
			String temp = reg_pass2nd_show.getText();
			remove(btn_hide_pass2nd);
			remove(reg_pass2nd_show);
			add(btn_show_pass2nd, new Integer(1));
			add(reg_pass2nd_hide, new Integer(1));		//hide pass2nd
			reg_pass2nd_hide.setText(temp);
			revalidate();
			repaint();
		}
		//-------------------------------------
		if(JButtonClicked == btn_go) {
			register();
		}
		//-------------------------------------
		
		
		//Button : choose
		if(JButtonClicked == btn_add) {
			addNewDish();
		}
		//-------------------------------------
		if(JButtonClicked == btn_drop) {
			dropDish();
		}
		//-------------------------------------
		if(JButtonClicked == btn_next) {
			toAmount();
		}
		//-------------------------------------
		
		
		//Button : amount
		if(JButtonClicked == btn_plus_1) {
			plus1();
		}
		//-------------------------------------
		if(JButtonClicked == btn_plus_5) {
			plus5();
		}
		//-------------------------------------
		if(JButtonClicked == btn_minus_1) {
			minus1();
		}
		//-------------------------------------
		if(JButtonClicked == btn_minus_5) {
			minus5();
		}
		//-------------------------------------
		if(JButtonClicked == btn_done) {
			order();
		}
		//-------------------------------------
		if(JButtonClicked == btn_back) {
			toChoose();
		}
		//-------------------------------------
		
		
		//Button : both choose n amount screen
		if(JButtonClicked == btn_out) {
			logout();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		JTextField jtf = (JTextField) e.getSource();
		if(jtf == search) {
			String input = search.getText();
			searchFilter(input);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	;
	//methods
	public void checkRegPass(String pass) {
		if(pass.length() > 50) {
			label_error_reg_pass.setForeground(Color.RED);
			label_error_reg_pass.setText("Your password is TOO LONG");
			reg_pass_stat = false;
		}
		else {
			if(StringChecker.calculatePasswordStrength(pass) < 4) {
				label_error_reg_pass.setForeground(Color.RED);
				label_error_reg_pass.setText("WEAK");
			}
			else if(StringChecker.calculatePasswordStrength(pass) < 6) {
				label_error_reg_pass.setForeground(Color.ORANGE);
				label_error_reg_pass.setText("NOT GOOD");
			}
			else if(StringChecker.calculatePasswordStrength(pass) < 8) {
				label_error_reg_pass.setForeground(Color.GREEN);
				label_error_reg_pass.setText("GOOD");
			}
			else {
				label_error_reg_pass.setForeground(Color.BLUE);
				label_error_reg_pass.setText("STRONG");
			}
			reg_pass_stat = true;
		}
		add(label_error_reg_pass, new Integer(2));
		revalidate();
		repaint();
	}
	//-------------------------------------
	public void checkPass2nd_show() {
		String pass2nd = reg_pass2nd_show.getText();
		String pass = reg_pass.getText();
		if(!(pass.equals(pass2nd))) {
			pass2nd_pass = false;
			label_error_reg_pass2nd.setText("This doesn't match the Password");
			add(label_error_reg_pass2nd, new Integer(2));
			revalidate();
			repaint();
		}
		else {
			pass2nd_pass = true;
			remove(label_error_reg_pass2nd);
			revalidate();
			repaint();
		}
	}
	//-------------------------------------
	public void checkPass2nd_hide() {
		String pass2nd = reg_pass2nd_hide.getText();
		String pass = reg_pass.getText();
		if(!(pass.equals(pass2nd))) {
			pass2nd_pass = false;
			label_error_reg_pass2nd.setText("This doesn't match the Password");
			add(label_error_reg_pass2nd, new Integer(2));
			revalidate();
			repaint();
		}
		else {
			pass2nd_pass = true;
			remove(label_error_reg_pass2nd);
			revalidate();
			repaint();
		}
	}
	//-------------------------------------
	public void checkRegMail() {
		String mail = reg_mail.getText();
		mail.trim();
		String[] parts = mail.split("@");
		String temp = mail;
		int count = temp.length() - temp.replace("@", "").length();
		if(parts.length != 2 || count != 1) {
			reg_mail_stat = false;
			label_error_reg_mail.setText("Please use your ACCURATE GMAIL (@gmail.com)");
			add(label_error_reg_mail, new Integer(2));
			revalidate();
			repaint();
		}
		else if(StringChecker.containsWhiteSpace(mail)){
			reg_mail_stat = false;
			label_error_reg_mail.setText("There is whitespace(s) in your inputed email");
			add(label_error_reg_mail, new Integer(2));
			revalidate();
			repaint();
		}
		else if(!(parts[1].equals("gmail.com") || parts[1].equals("Gmail.com"))) {
			reg_mail_stat = false;
			label_error_reg_mail.setText("ONLY GMAIL IS ACCPETED");
			add(label_error_reg_mail, new Integer(2));
			revalidate();
			repaint();
		}
		else if (MySQLCommunication.checkRegEmail(temp)){
			reg_mail_stat = false;
			label_error_reg_mail.setText("Email is already IN USE");
			add(label_error_reg_mail, new Integer(2));
			revalidate();
			repaint();
		}
		else {
			reg_mail_stat = true;
			remove(label_error_reg_mail);
			revalidate();
			repaint();
		}
	}
	//-------------------------------------
	public void checkPhone() {
		String phone = reg_phone.getText();
		phone = phone.replaceAll("\\s", "");
		if(phone.length() < 9 || phone.length() > 12 || !(StringChecker.isLong(phone))) {
			reg_phone_stat = false;
			if(phone.length()==0) {
				label_error_reg_phone.setText("Please provide a phone number");
				add(label_error_reg_phone, new Integer(2));
				revalidate();
				repaint();
			}
			else {
				label_error_reg_phone.setText("INVALID PHONE NUMBER");
				add(label_error_reg_phone, new Integer(2));
				revalidate();
				repaint();
			}
		}
		else {
			reg_phone_exist = MySQLCommunication.checkRegPhone(Long.parseLong(phone));
			reg_phone_stat = true;
			remove(label_error_reg_phone);
			revalidate();
			repaint();
		}
	}
	//-------------------------------------
	public void checkNameAdressDistrict() {
		final String temp_district = reg_district.getText();
		final String temp_address = reg_address.getText();
		final String temp_name = reg_name.getText();
		if(temp_name.isEmpty()) reg_name_stat = false;		//check if name is provided
		else reg_name_stat = true;
		//-------------------------------------
		if(temp_address.isEmpty() || temp_district.isEmpty()) {		//check if address & district are provided
			reg_address_stat = false;
			reg_district_stat = false;
		}
		else {
			reg_address_stat = true;
			reg_district_stat = true;
		}
	}
	//-------------------------------------
	public boolean checkLogin() {
		final String temp_mail = log_mail.getText();
		final String temp_pass = log_pass.getText();
		if(temp_mail.isEmpty() || temp_pass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please INPUT Email AND Password");
			return true;
		}
		else if (temp_mail.length()<11) {
			JOptionPane.showMessageDialog(null, "Please INPUT the accurate Email - ONLY GMAIL IS ACCPETED!");
			return false;
		}
		else if (!MySQLCommunication.checkLoginEmail(temp_mail)) {
			JOptionPane.showMessageDialog(null, "Your email DOES NOT MATCH any account!");
			return false;
		}
		else if (!MySQLCommunication.checkLoginPass(temp_mail, temp_pass)) {
			JOptionPane.showMessageDialog(null, "Wrong PASSWORD");
			return false;
		}
		else {
			return true;
		}
	}
	//-------------------------------------
	public boolean checkRegister() {
		checkNameAdressDistrict();
		if(!reg_pass_stat || !pass2nd_pass || !reg_mail_stat || !reg_phone_stat || !reg_address_stat || !reg_district_stat) {
			//1st, check if pass & pass2nd are the same
			if(!pass2nd_pass) {
				JOptionPane.showMessageDialog(null, "Confirm Password: Please check the password!");
			}
			//2nd, check validity of email
			if(!reg_mail_stat || !reg_mail_valid) {
				JOptionPane.showMessageDialog(null, "Gmail: Please check the email!");
				}
			//3rd, check phone
			if(!reg_phone_stat) {
				JOptionPane.showMessageDialog(null, "Phone no.: Please check the phone number!");
			}
			//4th, check address
			if(!reg_address_stat) {
				JOptionPane.showMessageDialog(null, "Address: Please provide your address!");
			}
			//5th, check district
			if(!reg_district_stat) {
				JOptionPane.showMessageDialog(null, "District: Please provide your district!");
			}
			//6th, check name
			if(!reg_name_stat) {
				JOptionPane.showMessageDialog(null, "Name: Please provide your real name!");
			}
			//7th, check pass length
			if(!reg_pass_stat) {
				JOptionPane.showMessageDialog(null, "Password: Your password is TOO LONG!");
			}
			return false;
		}
		else {
			return true;
		}
	}
	//-------------------------------------
	public void register() {
		String temp_name = reg_name.getText();
		String temp_id = reg_id.getText();
		if (temp_id.isEmpty()) temp_id = "No data";		//prevent null_pointer
		String temp_phone = reg_phone.getText();
		String temp_mail = reg_mail.getText();
		temp_mail.trim();
		String temp_address = reg_address.getText();
		String temp_district = reg_district.getText();
		String temp_pass = reg_pass.getText();
		String temp_pass2nd;
		if(pass2nd_stat) temp_pass2nd = reg_pass2nd_hide.getText();
		else temp_pass2nd = reg_pass2nd_show.getText();
		
		//check and create new account
		if(checkRegister()) {
			log_pass.setText("");
			reg_pass.setText("");
			reg_pass2nd_show.setText("");
			reg_pass2nd_hide.setText("");
			if(reg_phone_exist) {	//if the phone already exist
				if(MySQLCommunication.checkPhoneHasEmail(Long.parseLong(temp_phone))) {	//cannot create: phone has email
					JOptionPane.showMessageDialog(null, "Failed >> Error: PHONE NUMBER has been binded to an account.\n"
							+ 							"                 			             Please login if it is yours!");
				}
				else {	//update email & password & pid if phone has no email
					String[] options = {"Yes, alright!", "No! I will use another."};
					int ans = JOptionPane.showOptionDialog(null, "Do you want to use it?", "Phone number found in database", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
					if (ans == 0) {		//if user agree to update
						if(MySQLCommunication.updateCustomerInfo(Long.parseLong(temp_phone),temp_id, temp_mail, temp_pass)) {
							JOptionPane.showMessageDialog(null, "Success");
						}	//if user refuses updating (reuse that phone) : nothing happens
					}
				}
			}
			else if(MySQLCommunication.createNewCustomer(temp_name, temp_id, Long.parseLong(temp_phone), temp_mail, temp_address, temp_district, temp_pass)) {	//phone number & email not duplicated in database
				JOptionPane.showMessageDialog(null, "Success");
			}
		}
	}
	//-------------------------------------
	public void addNewDish() {
		int rows[] = this.tbl_menu.getSelectedRows();
		for(int i=0; i<rows.length; i++) {

			String id = model_tbl_menu.getValueAt(tbl_menu.convertRowIndexToModel(rows[i]), 0).toString();
			String name = model_tbl_menu.getValueAt(tbl_menu.convertRowIndexToModel(rows[i]), 1).toString();
			String price = model_tbl_menu.getValueAt(tbl_menu.convertRowIndexToModel(rows[i]), 2).toString();
			int count = 0;
			if (rows.length > 0) {
				model_tbl_menu.removeRow(tbl_menu.convertRowIndexToModel(rows[i]));
				model_tbl_pick.addRow(new Object[] {id, name, price});
				for(Order o : orders) {
					if(name.equals(o.getName())) break;
					count++;
				}
				if(count==orders.size()) orders.add(new Order(Integer.parseInt(id),name,1,Integer.parseInt(price)));
			}
		}
	}
	//-------------------------------------
	public void dropDish() {
		for(int i = 0; tbl_pick.getSelectedRowCount() > 0; i++) {
			int index = tbl_pick.getSelectedRow();
			String id = tbl_pick.getValueAt(index, 0).toString();
			String name = tbl_pick.getValueAt(index, 1).toString();
			String price = tbl_pick.getValueAt(index, 2).toString();
			model_tbl_menu.addRow(new Object[] {id, name, price});
			for(Order o : orders) {
				if(name.equals(o.getName())) {
					orders.remove(o);
					break;
				}
			}
			model_tbl_pick.removeRow(index);
		}
	}
	//-------------------------------------
	public void plus1() {
		int row = tbl_cart.getSelectedRow();
		if(row != -1) {
			String temp_name = tbl_cart.getValueAt(row, 1).toString();
			int value = Integer.parseInt(tbl_cart.getValueAt(row, 3).toString());
			tbl_cart.setValueAt(value + 1, row, 3);
			for(Order o : orders) {
				if(o.getName().equals(temp_name)) {
					o.setAmount(o.getAmount() + 1);
					tbl_cart.setValueAt((o.getAmount()*o.getPrice()), row, 4);
					break;
				}
			}
			setTotalMoney();
		}
	}
	//-------------------------------------
	public void plus5() {
		int row = tbl_cart.getSelectedRow();
		if(row != -1) {
			String temp_name = tbl_cart.getValueAt(row, 1).toString();
			int value = Integer.parseInt(tbl_cart.getValueAt(row, 3).toString());
			tbl_cart.setValueAt(value + 5, row, 3);
			for(Order o : orders) {
				if(o.getName().equals(temp_name)) {
					o.setAmount(o.getAmount() + 5);
					tbl_cart.setValueAt((o.getAmount()*o.getPrice()), row, 4);
					break;
				}
			}
			setTotalMoney();
		}
	}
	//-------------------------------------
	public void minus1() {
		int row = tbl_cart.getSelectedRow();
		if(row != -1) {
			String temp_name = tbl_cart.getValueAt(row, 1).toString();
			int value = Integer.parseInt(tbl_cart.getValueAt(row, 3).toString());
			if(value > 1) {
				tbl_cart.setValueAt(value - 1, row, 3);
				for(Order o : orders) {
					if(o.getName().equals(temp_name)) {
						o.setAmount(o.getAmount() - 1);
						tbl_cart.setValueAt((o.getAmount()*o.getPrice()), row, 4);
						break;
					}
				}
			}
			else {
				tbl_cart.setValueAt(0, row, 3);
				for(Order o : orders) {
					if(o.getName().equals(temp_name)) {
						o.setAmount(0);
						tbl_cart.setValueAt((o.getAmount()*o.getPrice()), row, 4);
						break;
					}
				}
			}
		}
		setTotalMoney();
	}
	//-------------------------------------
	public void minus5() {
		int row = tbl_cart.getSelectedRow();
		if(row != -1) {
			String temp_name = tbl_cart.getValueAt(row, 1).toString();
			int value = Integer.parseInt(tbl_cart.getValueAt(row, 3).toString());
			if(value > 5) {
				tbl_cart.setValueAt(value - 5, row, 3);
				for(Order o : orders) {
					if(o.getName().equals(temp_name)) {
						o.setAmount(o.getAmount() - 5);
						tbl_cart.setValueAt((o.getAmount()*o.getPrice()), row, 4);
						break;
					}
				}
			}
			else {
				tbl_cart.setValueAt(0, row, 3);
				for(Order o : orders) {
					if(o.getName().equals(temp_name)) {
						o.setAmount(0);
						tbl_cart.setValueAt((o.getAmount()*o.getPrice()), row, 4);
						break;
					}
				}
			}
		}
		setTotalMoney();
	}
	//-------------------------------------
	public void setTotalMoney() {
		int total_money = 0;
		for(Order o : orders) {
			total_money += o.getAmount()*o.getPrice();
		}
		if(!unit.equals("VND")) {
			total_money = Convert_VND.fromVND(unit, total_money);
		}
		money.setText(Integer.toString(total_money));
	}
	//-------------------------------------
	public void logout() {
		int index = 0;
		//say thanks
		JOptionPane.showMessageDialog(null, "Thank you, " + MySQLCommunication.Name + " for being with us!\n"
				+ 							"Good bye and see you again!");
		
		//clear all data except login mail
		log_pass.setText("");
		reg_mail.setText("");
		reg_name.setText("");
		reg_id.setText("");
		reg_phone.setText("");
		reg_address.setText("");
		reg_district.setText("");
		reg_pass.setText("");
		reg_pass2nd_show.setText("");
		reg_pass2nd_hide.setText("");
		notes.setText("-Notes here-");
		search.setText("");
		MySQLCommunication.clearData();
		
		//reset all the lists
		orders.clear();
		while(true) {
			if (model_tbl_menu.getRowCount() == 0) break;
			else model_tbl_menu.removeRow(0);
		}
		while(true) {
			if (model_tbl_pick.getRowCount() == 0) break;
			else model_tbl_pick.removeRow(0);
		}
		while(true) {
			if (model_tbl_cart.getRowCount() == 0) break;
			else model_tbl_cart.removeRow(0);
		}
		//remove all
		removeAll();
		add(btn_help,  new Integer(50),  new Integer(0));
		add(btn_option, new Integer(50));
		
		//add --- login screen
		add(btn_log, new Integer(1)); add(btn_go, new Integer(1)); add(btn_show_pass2nd, new Integer(1));
		  
		  add(label_log_mail, new Integer(1)); add(label_log_pass, new Integer(1)); add(label_create, new Integer(1));
		  add(label_account, new Integer(1)); add(label_reg_name, new Integer(1)); add(label_reg_id, new Integer(1));
		  add(label_reg_phone, new Integer(1)); add(label_reg_mail, new Integer(1)); add(label_reg_address, new Integer(1));
		  add(label_reg_district, new Integer(1)); add(label_reg_pass, new Integer(1)); add(label_reg_pass2nd, new Integer(1));
		  add(label_sq_left_icon, new Integer(1)); add(label_ro_mid_icon, new Integer(0)); add(label_reg_icon, new Integer(1));
		  add(label_red_banner, new Integer(0));
		  
		  add(log_mail, new Integer(1)); add(log_pass, new Integer(1)); add(reg_name, new Integer(1)); add(reg_id, new Integer(1)); add(reg_mail, new Integer(1));
		  add(reg_phone, new Integer(1)); add(reg_address, new Integer(1)); add(reg_district, new Integer(1)); add(reg_pass, new Integer(1));
		  add(reg_pass2nd_hide, new Integer(1));
		  
		  //move input cursor to login password
		  log_pass.requestFocusInWindow();
		  revalidate();
		  repaint();
		  
		  //indicate at login screen
		  at_login = true;
	}
	//-------------------------------------
	public void toChoose() {
		String temp = log_mail.getText();
		label_acc.setText(temp);
		if (at_login) if(MySQLCommunication.storeCustomerData(temp)) {
			getMenu();
			refreshTbl_menu();
			JOptionPane.showMessageDialog(null, "Login success! Welcome " + MySQLCommunication.Name + " to KFC!");
		}
		
		//clear password textfields
		log_pass.setText("");
		reg_pass.setText("");
		reg_pass2nd_show.setText("");
		reg_pass2nd_hide.setText("");
		
		//remove all components of login screen
		removeAll();
		
		//reset menu
		
		//add general buttons
		add(btn_help,  new Integer(50),  new Integer(0));
		add(btn_option, new Integer(50));
		
		//add all components of both choose and amount screen
		add(label_back,  new Integer(0));
		add(btn_out, new Integer(1));
		add(label_acc, new Integer(1));
		
		//add all components of choose screen
		add(search, new Integer(1));
		
		add(scr_menu, new Integer(1)); add(scr_pick, new Integer(1));
		
		add(btn_add, new Integer(1)); add(btn_drop, new Integer(1)); add(btn_next, new Integer(1));
		  
		add(label_cont, new Integer(1)); add(label_menu, new Integer(1)); add(label_pick, new Integer(1));
		revalidate();
		repaint();

		//indicate not at login screen
		at_login = false;
	}
	//-------------------------------------
	public void toAmount() {
		while(true) {
			if (model_tbl_cart.getRowCount() == 0) break;
			else model_tbl_cart.removeRow(0);
		}
		if(orders.size()==1) {
			String[] options = {"Yes, I do!", "Quit!"};
			int ins = JOptionPane.showOptionDialog(null, "Don't you want to order anything?", "Nothing is picked!", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
			if (ins == 1) {
				System.exit(0);
			}
		}
		else {
			for(Order o : orders) {
				model_tbl_cart.addRow(new Object[] {o.getID(), o.getName(), o.getPrice(), o.getAmount(), o.getPrice()*o.getAmount()});
			}
		
			//remove all components of login screen
			removeAll();
			add(btn_help,  new Integer(50),  new Integer(0));
			add(btn_option, new Integer(50));
		
			//add all components of both choose and amount screen
			add(label_back,  new Integer(0));
			add(btn_out, new Integer(1));
			add(label_acc, new Integer(1));
		
			//add --- amount screen
			setTotalMoney();
			add(money,  new Integer(1));
		
			add(scr_notes, new Integer(1)); add(scr_tbl_cart, new Integer(1));
		  
			add(btn_done, new Integer(1)); add(btn_plus_1, new Integer(1)); add(btn_plus_5, new Integer(1));
			add(btn_minus_1, new Integer(1)); add(btn_minus_5, new Integer(1)); add(btn_unit,  new Integer(1));
			add(btn_back,  new Integer(1));
		  
			add(label_cart, new Integer(1)); add(label_triangle, new Integer(1)); add(label_total, new Integer(2));

		}
	}
	//-------------------------------------
	public void order() {
		int money = 0;	//total money
		String chosen_address = "";		//store and send to the create trans method
		String chosen_district = "";	//store and send to the create trans method
		Object obj;		//used to check if the customer cancels ==> prevent null pointer
		Object[] addresses = MySQLCommunication.StandardAddresses.toArray();	//store to display available addresses
		obj = JOptionPane.showInputDialog(null, "Address:", "Choose an address", JOptionPane.INFORMATION_MESSAGE, null, addresses, null);
		chosen_address = (obj == null)? "" : obj.toString();
		if(chosen_address.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Canceled.");	//if he cancel choosing address
		}
		else {
			String[] parts = chosen_address.split(" - district: ", 2);
			if(parts.length == 2) {	//check if the address is in the form: "<address> - district: <district>"
				chosen_address = parts[0];
				chosen_district = parts[1];
			}
			else chosen_district = "No district";
			
			for(Order o : orders) {
				if(o.getAmount() > 0) {	//if they pick some thing of the dish
					money = money + o.getAmount()*o.getPrice();
				}
			}
			if(money > 0) {		//check if there is anything to order, if at least 1
				if(MySQLCommunication.createTran(chosen_address, chosen_district, money)) {	//check if creating new trans successful
					for(Order o : orders) {
						if (o.getAmount() > 0) MySQLCommunication.createOrder(o.getID(), o.getAmount());
						}
					SendData.send();
					JOptionPane.showMessageDialog(null, "SUCESS!");
				}
				else {	//if fail to create new trans
					String[] options = {"Yes", "No"};
					int ans = JOptionPane.showOptionDialog(null, "ORDER FAILED!\n"
														   + "   Try Again?", "Order Status:", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
					if (ans == 0) { //if he chooses yes, re-order
						btn_done.doClick();
					}
					else JOptionPane.showMessageDialog(null, "Order canceled.");	//if he chooses no
				}
			}
			else JOptionPane.showMessageDialog(null, "Nothing ORDERED!");	//if he doesn't choose anything
		}
	}
	//-------------------------------------
	public void showOption() {
		add(btn_sq_option, new Integer(50));
		remove(btn_option);
		add(btn_check_tran, new Integer(50));
		add(btn_check_updates, new Integer(50));
		add(btn_about, new Integer(50));
		if(!at_login) {
			add(btn_all_trans, new Integer(50));
			add(btn_acc_detail, new Integer(50));
		}
		revalidate();
		repaint();
	}
	//-------------------------------------
	public void closeOption() {
		remove(btn_check_tran);
		remove(btn_check_updates);
		remove(btn_about);
		remove(btn_all_trans);
		remove(btn_acc_detail);
		remove(btn_sq_option);
		
		add(btn_option);
		revalidate();
		repaint();
	}
	//-------------------------------------
	public void showView() {
		add(label_view, new Integer(50));
		add(btn_xx, new Integer(51));
	}
	//-------------------------------------
	public void closeView() {
		//remove popup view
		remove(label_view);
		remove(btn_xx);
		
		//remove account detail
		remove(label_acc_mod);
		remove(label_acc_name);
		remove(label_acc_phone);
		remove(label_acc_rating);
		remove(btn_change_name);
		remove(btn_change_phone);
		remove(btn_view_rating);
		remove(btn_change_pass);
		remove(btn_add_address);
		remove(btn_remove_address);
		remove(btn_add_pid);
		remove(scr_address);
		remove(acc_name);
		remove(acc_phone);
		remove(acc_rating);
		
		//remove tran & trans view
		remove(scr_view);
		remove(btn_tran_cancel);
		
		revalidate();
		repaint();
	}
	//-------------------------------------
	public void showTran() {
		Object obj;
		String id = "";
		obj = JOptionPane.showInputDialog("Transaction ID");
		id = (obj == null)? "" : obj.toString();
		String temp = "";
		if(StringChecker.isInt(id) && !id.isEmpty()) {
			int id_int = Integer.parseInt(id);
			temp = MySQLCommunication.getTran(id_int);
			if(temp.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Could not find your transaction ID");
				showTran();
			}
			else {
				String[] parts = temp.split(",");
				if(parts.length > 0) {
					while(true) {
						if (model_tbl_tran.getRowCount() == 0) break;
						else model_tbl_tran.removeRow(0);
					}
					model_tbl_tran.addRow(new Object[] {parts[0], parts[1], parts[2], parts[3]});
				}
				tbl_trans.setModel(model_tbl_tran);
				scr_view.setViewportView(tbl_trans);
				add(scr_view, new Integer(100));
				add(btn_tran_cancel, new Integer(100));
				revalidate();
				repaint();
			}
		}
		else {
			closeView();
			JOptionPane.showMessageDialog(null, "Canceled.");
		}
	}
	//-------------------------------------
	public void cancelTran() {
		JTextField tran_id = new JTextField();
		JTextField email = new JTextField();
		JTextField pass = new JPasswordField();
		Object obj;
		Object[] reasons = {"Wait too long", "Wrong pick", "Troll", "Mistaken order", "Others..."};
		Object[] message = {
				"Transaction ID: ", tran_id,
				"Email: ", email,
				"Password: ", pass};
		int option = JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String temp_tran_id = tran_id.getText();
			String temp_email = email.getText();
			String temp_pass = pass.getText();
			if(temp_tran_id.isEmpty() || temp_email.isEmpty() || temp_pass.isEmpty()) {	//if one the field is empty
				cancelTran();	//recall this method
			}
			else {	//all fields are filled
				if(MySQLCommunication.checkAuth(Integer.parseInt(temp_tran_id), temp_email, temp_pass)) {	//if inputs are verified
					obj = JOptionPane.showInputDialog(null, "Please choose 1:", "Cancellation's Reason", JOptionPane.INFORMATION_MESSAGE, null, reasons, null);
					String chosen_reason = (obj == null)? "Others" : obj.toString();
					if(MySQLCommunication.cancelTran(Integer.parseInt(temp_tran_id), chosen_reason)) {	//cancel tran & orders, if success
						JOptionPane.showMessageDialog(null, "Success. For more details, please contact us. Soory for any inconvenience!");
						closeView();
					}
					else {	//cancellation fails
						JOptionPane.showMessageDialog(null, "Failed! Please try again!");
						closeView();
					}
				}
				else {	//if inputs do not fit any transaction
					JOptionPane.showMessageDialog(null, "You may use WRONG TRANSACTION ID or WRONG EMAIL or WRONG PASSWORD or TRANSACTION WAS COMPLETED!");
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Canceled.");
		}
	}
	//-------------------------------------
	public void showAbout() {
		JPanel pn_abt = new About();
		scr_abt = new JScrollPane(pn_abt);
		scr_abt.setBounds(100,100,800,300);
		add(scr_abt, new Integer(100), new Integer(1));
		add(btn_ok, new Integer(100), new Integer(0));
		revalidate();
		repaint();
	}
	//-------------------------------------
	public void closeAbout() {
		remove(scr_abt);
		remove(btn_ok);
		revalidate();
		repaint();
	}
	//-------------------------------------
	public void showAllTrans() {
		ArrayList<String> trans = new ArrayList(MySQLCommunication.getTrans(MySQLCommunication.Email));
		while(true) { 	//clear the table
			if (model_tbl_trans.getRowCount() == 0) break;
			else model_tbl_trans.removeRow(0);
		}
		if (!trans.isEmpty()) {
			for(String s : trans) {
				String[] parts = s.split(",");
				model_tbl_trans.addRow(new Object[] {parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]});
			}
			tbl_trans.setModel(model_tbl_trans);
			scr_view.setViewportView(tbl_trans);
			add(scr_view, new Integer(100));
			revalidate();
			repaint();
		}
		else {
			closeView();
			JOptionPane.showMessageDialog(null, "No data!");
		}
	}
	//-------------------------------------
	public void showAcc() {
		int count = 1;
		while(true) {
			if(model_tbl_address.getRowCount()==0)break;
			else model_tbl_address.removeRow(0);
		}
		for(String s : MySQLCommunication.StandardAddresses) {
			model_tbl_address.addRow(new Object[] {count, s});
			count++;
		}
		acc_name.setText(" " + MySQLCommunication.Name);
		acc_phone.setText(" (+84) " + Long.toString(MySQLCommunication.Phone));
		acc_rating.setText(" " + Integer.toString(MySQLCommunication.Rating));
		//add components
		add(label_acc_mod, new Integer(100));
		add(label_acc_name, new Integer(100));
		add(label_acc_phone, new Integer(100));
		add(label_acc_rating, new Integer(100));
		add(scr_address, new Integer(100));
		add(acc_name, new Integer(100));
		add(acc_phone, new Integer(100));
		add(acc_rating, new Integer(100));
		add(btn_change_name, new Integer(100));
		add(btn_change_phone, new Integer(100));
		add(btn_view_rating, new Integer(100));
		add(btn_change_pass, new Integer(100));
		add(btn_add_address, new Integer(100));
		add(btn_remove_address, new Integer(100));
		if(!MySQLCommunication.checkHasPid()) add(btn_add_pid, new Integer(100));
		revalidate();
		repaint();
	}
	//-------------------------------------
	public boolean verifyPass() {
		JTextField password = new JPasswordField();
		String pass = "";
		Object[] message = {"Password", password};
		int choice = JOptionPane.showConfirmDialog(null, message, "Input password to continue...", JOptionPane.OK_CANCEL_OPTION);
		if(choice == JOptionPane.OK_OPTION) pass = password.getText();
		if(!pass.isEmpty() && MySQLCommunication.checkInputPass(pass)) return true;
		else return false;
		}
	//-------------------------------------
	public void changeName() {
		if(verifyPass()) {	//verify password, if correct
			Object obj;
			String name = "";
			obj = JOptionPane.showInputDialog(null, "Name:", "Choose your new name", JOptionPane.INFORMATION_MESSAGE, null, null, null);
			name = (obj == null)? "" : obj.toString();
			if(!name.isEmpty()) {	//if name is not null
				//check if update successful
				if(MySQLCommunication.updateCustomerSingleInfo("Name", name)) JOptionPane.showMessageDialog(null, "Success!");
				else JOptionPane.showMessageDialog(null, "Failed!");
				//check if able to get new info
				if(!MySQLCommunication.storeCustomerData(MySQLCommunication.Email)) JOptionPane.showMessageDialog(null, "Error at getting new info. Please restart!"); //check if it can retrieve customer's info
			}
			else JOptionPane.showMessageDialog(null, "No name was chosen!");
		}
		else JOptionPane.showMessageDialog(null, "WRONG PASSWORD");
	}
	//-------------------------------------
	public void changePhone() {
		if(verifyPass()) {	//verify password, if correct
			Object obj;
			String phone = "";
			obj = JOptionPane.showInputDialog(null, "Phone number:", "Choose new phone number", JOptionPane.INFORMATION_MESSAGE, null, null, null);
			phone = (obj == null)? "" : obj.toString();
			if(!phone.isEmpty() && StringChecker.isLong(phone) && phone.length() > 8 && phone.length() < 13) {	//if phone is not null & is valid
				if(!MySQLCommunication.checkRegPhone(Long.parseLong(phone))) {	//check if there is any similar phone number in database, if no
					//check if update successful
					if(MySQLCommunication.updateCustomerSingleInfo("Phone", phone)) JOptionPane.showMessageDialog(null, "Success!"); 
					else JOptionPane.showMessageDialog(null, "Failed!");
					//check if able to get new info
					if(!MySQLCommunication.storeCustomerData(MySQLCommunication.Email)) JOptionPane.showMessageDialog(null, "Error at getting new info. Please restart!");
				}
				else JOptionPane.showMessageDialog(null, "Error: Phone number already exists");		//phone duplicates
			}
			else JOptionPane.showMessageDialog(null, "Invalid PHONE NUMBER!");
		}
		else JOptionPane.showMessageDialog(null, "WRONG PASSWORD");
	}
	//-------------------------------------
	public void viewRating() {
		verifyPass();
	}
	//-------------------------------------
	public void changePass() {
		if(verifyPass()) {	//verify password, if correct
			JTextField password = new JPasswordField();
			String pass = "";
			Object[] message = {"New password", password};
			int choice = JOptionPane.showConfirmDialog(null, message, "Choose new password", JOptionPane.OK_CANCEL_OPTION);
			if(choice == JOptionPane.OK_OPTION) pass = password.getText();
			if(!pass.isEmpty()) {	//if pass is not null
				//check if update successful
				if(MySQLCommunication.updateCustomerSingleInfo("Password", pass)) JOptionPane.showMessageDialog(null, "Success!"); 
				else JOptionPane.showMessageDialog(null, "Failed!");
				//check if able to get new info
				if(!MySQLCommunication.storeCustomerData(MySQLCommunication.Email)) JOptionPane.showMessageDialog(null, "Error at getting new info. Please restart!");
			}
			else JOptionPane.showMessageDialog(null, "No password was chosen!");
		}
		else JOptionPane.showMessageDialog(null, "WRONG PASSWORD");
	}
	//-------------------------------------
	public void addPid() {
		if(verifyPass()) {	//verify password, if correct
			Object obj;
			String pid = "";
			obj = JOptionPane.showInputDialog(null, "Personal ID", "Choose new personal ID", JOptionPane.INFORMATION_MESSAGE, null, null, null);
			pid = (obj == null)? "" : obj.toString();
			if(!pid.isEmpty()) {	//if pid is not null
				//check if update successful
				if(MySQLCommunication.updateCustomerSingleInfo("Personal_ID", pid)) JOptionPane.showMessageDialog(null, "Success!"); 
				else JOptionPane.showMessageDialog(null, "Failed!");
				//check if able to get new info
				if(!MySQLCommunication.storeCustomerData(MySQLCommunication.Email)) JOptionPane.showMessageDialog(null, "Error at getting new info. Please restart!");
			}
			else JOptionPane.showMessageDialog(null, "No ID was chosen!");
		}
		else JOptionPane.showMessageDialog(null, "WRONG PASSWORD");
		closeView();
	}
	//-------------------------------------
	public void addAddress() {
		if(verifyPass()) {
			JTextField address = new JTextField();
			JTextField district = new JTextField();
			Object obj;
			Object[] message = {
					"Address", address,
					"District", district};
			int option = JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {	//if he clicks ok
				String temp_address = address.getText();
				String temp_district = district.getText();
				if(temp_address.isEmpty() || temp_district.isEmpty()) {	//if one the field is empty
					JOptionPane.showMessageDialog(null, "Canceled.");
				}
				else {	//if all fields are filled
					if(MySQLCommunication.createAddress(MySQLCommunication.Cus_ID, temp_address, temp_district)) {	//if update successful
						JOptionPane.showMessageDialog(null, "Success!");
					}
					else JOptionPane.showMessageDialog(null, "Failed!");	//if update fails
					//check if able to get new info
					if(!MySQLCommunication.storeCustomerData(MySQLCommunication.Email)) JOptionPane.showMessageDialog(null, "Error at getting new info. Please restart!");
				}
			}
			else JOptionPane.showMessageDialog(null, "Canceled.");	//if he cancels
		}
	}
	//-------------------------------------
	public void removeAddress() {
		String chosen_address = "";
		String chosen_district = "";
		Object obj;
		if(verifyPass()) {	//verify password, if correct
			Object[] addresses = MySQLCommunication.StandardAddresses.toArray();
			obj = JOptionPane.showInputDialog(null, "Address:", "Choose address", JOptionPane.INFORMATION_MESSAGE, null, addresses, null);
			chosen_address = (obj == null)? "" : obj.toString();
			if(chosen_address.isEmpty()) JOptionPane.showMessageDialog(null, "Canceled.");	//if he cancels
			//if he presses ok
			else {
				String[] parts = chosen_address.split(" - district: ",2);
				if(parts.length == 2) {	//check if the address is in the form: "<address> - district: <district>"
					chosen_address = parts[0];
					chosen_district = parts[1];
				}
				String[] options = {"Yes", "No"};
				int ans = JOptionPane.showOptionDialog(null, "Are you sure to REMOVE this address?", "Confirm", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
				if (ans == 0) { //if he chooses yes
					if(MySQLCommunication.removeAddress(chosen_address, chosen_district)) {	//if remove successful
						JOptionPane.showMessageDialog(null, "Success!");
					}
					else JOptionPane.showMessageDialog(null, "Failed!");	//if remove fails
					//check if able to get new info
					if(!MySQLCommunication.storeCustomerData(MySQLCommunication.Email)) JOptionPane.showMessageDialog(null, "Error at getting new info. Please restart!");
				}
				else JOptionPane.showMessageDialog(null, "Canceled.");	//if he chooses no
			}
		}
		else JOptionPane.showMessageDialog(null, "WRONG PASSWORD");
	}
	//-------------------------------------
	public void searchFilter(String search) {
        if(search.length() == 0) {
           sorter.setRowFilter(null);
        } 
        else {
        	try {
        		sorter.setRowFilter(RowFilter.regexFilter(search));
        	} 
        	catch(Exception pse) {
                System.out.println("Bad regex pattern");
        	}
        }
    }
	//-------------------------------------
	public void refreshTbl_menu() {
		while(model_tbl_menu.getRowCount() > 0) model_tbl_menu.removeRow(0);
		for(Menu m : menu) {
			model_tbl_menu.addRow(new Object[] {m.getID(), m.getName(), m.getPrice()});
		}
	}
	//-------------------------------------
	public void changeCurrency(int amount, String to) {
		if(unit.equals("VND")) {
			amount = Convert_VND.fromVND(to, amount);
			money.setText(Integer.toString(amount));
		}
		else {
			amount = Convert_VND.toVND(unit, amount);
			amount = Convert_VND.fromVND(to, amount);
			money.setText(Integer.toString(amount));
		}
		unit = to;
	}
}

