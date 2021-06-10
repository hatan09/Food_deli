package main00;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.util.*;

public class GUI extends JLayeredPane{
	//create new things
	static Font reg_font = new Font("Times New Roman", Font.PLAIN, 28);
	static Font btn_log_font = new Font("Arial", Font.BOLD, 15);
	static Font reg_label_font = new Font("Freestyle Script", Font.BOLD, 50);
	static Font btn_done_font = new Font("Freestyle Script", Font.BOLD, 60);
	static Font label_reg_font = new Font("Ink Free", Font.BOLD, 26);
	static Font label_menu_font = new Font("Brush Script MT", Font.BOLD, 50);
	static Font notes_font = new Font("Times New Roman", Font.PLAIN, 24);
	static Font digit_font = new Font("Cambria Math", Font.PLAIN, 22);
	static Font btn_x_font = new Font("Arial", Font.PLAIN, 22);
	//-------------------------------------
	static Color btn_login_cl = new Color(255, 95, 95);
	static Color btn_option_cl = new Color(68,134,246);
	static Color hovered_btn_option_cl = new Color(104,182,255);
	//-------------------------------------
	final TableRowSorter<DefaultTableModel> sorter;
	//-------------------------------------
		
		
		//-------------------------------------
		static JTextArea help /*store help*/;		//general
		static JTextField acc_name, acc_rating, acc_phone;		//general
		
		static JTextField log_mail /*login email*/, reg_name /*register name*/, reg_id /*register pid*/, reg_phone, reg_mail /*register email*/, reg_address /*register address*/, 
							reg_district /*register district*/, reg_pass2nd_show /*confirm pass*/;	//login
		static JPasswordField log_pass /*login pass*/, reg_pass /*register pass*/ , reg_pass2nd_hide /*confirm pass*/;	//login
		
		static JTextField search	/*find dish*/;	//choose
		
		static JTextArea notes;		//amount
		static JTextField money;		//amount
		//-------------------------------------
		
		
		//-------------------------------------
		static JLabel label_acc_mod /*title*/, label_acc_name /*indicator*/, label_acc_phone /*indicator*/, label_acc_rating /*indicator*/, 
						label_view /*windows*/;	 //general
		
		static JLabel label_create /*title*/, label_account /*title*/, label_sq_left_icon, label_ro_mid_icon, label_reg_icon, label_red_banner, label_log_mail /*indicator*/, 
						label_log_pass /*indicator*/, label_reg_name /*indicator*/, label_reg_id /*indicator*/, label_reg_phone /*indicator*/, label_reg_mail /*indicator*/, 
						label_reg_address /*indicator*/, label_reg_district /*indicator*/, label_reg_pass /*indicator*/, label_reg_pass2nd /*indicator*/,
						label_error_log_mail /*sub*/, label_error_log_pass /*sub*/, label_error_reg_name /*sub*/, label_error_reg_mail /*sub*/, label_error_reg_pass /*sub*/, 
						label_error_reg_pass2nd /*sub*/, label_error_reg_phone /*sub*/;			//login
		
		static JLabel label_acc, label_menu, label_pick, label_back, label_cont; 			//choose
		
		static JLabel label_cart, label_triangle, label_total;		//amount
		//-------------------------------------
		
		
		//-------------------------------------
		static JButton btn_help, btn_x, btn_xx, btn_option, btn_sq_option, btn_check_updates , btn_about, btn_ok, btn_check_tran, btn_tran_cancel, btn_acc_detail, 
						btn_change_name, btn_change_phone, btn_view_rating, btn_add_address, btn_remove_address, btn_change_pass, btn_add_pid, btn_all_trans;	//general
		
		static JButton btn_log, btn_show_pass2nd, btn_hide_pass2nd, btn_go;		//login
		
		static JButton btn_next, btn_add, btn_drop, btn_out, btn_acc, btn_x_search;		//choose
		
		static JButton btn_done, btn_plus_5, btn_plus_1, btn_minus_5, btn_minus_1, btn_unit, btn_1, btn_2, btn_3, btn_back;		//amount
		//-------------------------------------
		
		
		//-------------------------------------
		static DefaultTableModel model_tbl_tran, model_tbl_trans, model_tbl_acc, model_tbl_address;		//general
		
		static DefaultTableModel model_tbl_menu, model_tbl_pick;		//choose
		
		static DefaultTableModel model_tbl_cart;		//amount
		//-------------------------------------
		
		
		//-------------------------------------
		static JScrollPane scr_help, scr_view, scr_abt, scr_address;		//general
		
		static JScrollPane scr_menu, scr_pick;		//choose
		
		static JScrollPane scr_cart, scr_notes, scr_tbl_cart;		//amount
		//-------------------------------------
		
		
		//-------------------------------------
		static JTable tbl_trans, tbl_acc, tbl_address;		//general
		
		static JTable tbl_menu, tbl_pick;		//choose
		
		static JTable tbl_cart;		//amount
		//-------------------------------------
		
		
		ArrayList<Menu> menu = new ArrayList();	//dishes
		String sp_char_d = " ▼";
		String sp_char_u = " ▲";
		final String[] sp_unit = {"VND","USD","Yen","Won"};
		int index = 0;
		
		public void getMenu() {
			try {
				//*****************************
				menu.clear();
				menu.addAll(MySQLCommunication.getMenu());
				}
				catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please check your Internet connection and try again later!", "Cannot connect to the database", JOptionPane.INFORMATION_MESSAGE);
				}
		}
		
		public GUI() {
			setLayout(null);
			setOpaque(true);
			setBackground(Color.white);
			getMenu();
			System.out.println(Pathway.path);
			
			
			//DefaultTableModel: choose
			model_tbl_menu = new DefaultTableModel();
			//-------------------------------------
			model_tbl_pick = new DefaultTableModel();
			//-------------------------------------
			
			
			//DefaultTableModel: general
			model_tbl_tran = new DefaultTableModel();
			//-------------------------------------
			model_tbl_trans = new DefaultTableModel();
			//-------------------------------------
			model_tbl_address = new DefaultTableModel();
			//-------------------------------------
			
			
			//DefaultTableModel: amount
			model_tbl_cart = new DefaultTableModel();
			//-------------------------------------
			
			
			//Tables: choose
			tbl_menu = new JTable(model_tbl_menu) {		//menu for choices
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tbl_menu.setFont(notes_font);
			tbl_menu.getTableHeader().setFont(label_reg_font);
			tbl_menu.setRowHeight(26);
			tbl_menu.setAutoCreateRowSorter(true);
			sorter = new TableRowSorter<DefaultTableModel>(model_tbl_menu);
			tbl_menu.setRowSorter(sorter);
			model_tbl_menu.addColumn("ID");
			model_tbl_menu.addColumn("Name");
			model_tbl_menu.addColumn("Price");
			//-------------------------------------
			tbl_pick = new JTable(model_tbl_pick) {		//chosen dishes
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tbl_pick.setFont(notes_font);
			tbl_pick.getTableHeader().setFont(label_reg_font);
			tbl_pick.setRowHeight(26);
			model_tbl_pick.addColumn("ID");
			model_tbl_pick.addColumn("Name");
			model_tbl_pick.addColumn("Price");
			//-------------------------------------
			
			
			//Tables: general
			tbl_trans = new JTable(model_tbl_tran) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tbl_trans.setFont(notes_font);
			tbl_trans.getTableHeader().setFont(label_reg_font);
			tbl_trans.setRowHeight(26);
	        model_tbl_tran.addColumn("Trans_ID");		//0
	        model_tbl_tran.addColumn("Date");			//1
	        model_tbl_tran.addColumn("Total money");	//2
	        model_tbl_tran.addColumn("Status");			//3
	        //-------------------------------------
	        model_tbl_trans.addColumn("Trans_ID");		//0
	        model_tbl_trans.addColumn("Name");			//1
	        model_tbl_trans.addColumn("Date");			//2
	        model_tbl_trans.addColumn("Total money");	//3
	        model_tbl_trans.addColumn("Status");		//4
	        model_tbl_trans.addColumn("Reason");		//5
			//-------------------------------------
	        tbl_address = new JTable(model_tbl_address) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tbl_address.setFont(notes_font);
			tbl_address.getTableHeader().setFont(label_reg_font);
			tbl_address.setRowHeight(26);
	        model_tbl_address.addColumn("No.");		//0
	        model_tbl_address.addColumn("Address");	//1
	        TableColumnModel tcm_address = tbl_address.getColumnModel();
	        tcm_address.getColumn(0).setMaxWidth(100);
	     	//-------------------------------------
	        
			
			//Tables: amount
	        tbl_cart = new JTable(model_tbl_cart) {
	        	@Override
	        	public boolean isCellEditable(int row, int column) {
	        		return column == 2 ? true : false;
	        	}
	        };
	        tbl_cart.setFont(notes_font);
	        tbl_cart.getTableHeader().setFont(label_reg_font);
	        tbl_cart.setRowHeight(26);
	        tbl_cart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        model_tbl_cart.addColumn("ID"); 	//0
	        model_tbl_cart.addColumn("Dish");	//1
	        model_tbl_cart.addColumn("Price");	//2
	        model_tbl_cart.addColumn("Amount");	//3
	        model_tbl_cart.addColumn("Total");	//4
	        TableColumnModel tcm_cart = tbl_cart.getColumnModel();
	        tcm_cart.getColumn(0).setMaxWidth(100);
	        tcm_cart.getColumn(1).setPreferredWidth(200);
	        tcm_cart.getColumn(3).setPreferredWidth(40);
	        //-------------------------------------
	        
	        
	        //TextArea: general
	        help = new JTextArea();
	        help.setFont(notes_font);
	        help.setBackground(Color.CYAN);
	        help.setEditable(false);
	        //-------------------------------------
	        acc_name = new JTextField();
	        acc_name.setFont(notes_font);
	        acc_name.setBounds(110, 300, 300, 50);
	        acc_name.setEditable(false);
	        //-------------------------------------
	        acc_phone= new JTextField();
	        acc_phone.setFont(notes_font);
	        acc_phone.setBounds(110, 400, 300, 50);
	        acc_phone.setEditable(false);
	        //-------------------------------------
	        acc_rating = new JTextField();
	        acc_rating.setFont(notes_font);
	        acc_rating.setBounds(110, 500, 300, 50);
	        acc_rating.setEditable(false);
	        //-------------------------------------
			
			
			//Textfields: login
	        log_mail = new HintTextField("(Email)");
	        log_mail.setBounds(640, 40, 185, 28);
	        log_mail.setRequestFocusEnabled(true);
	        //-------------------------------------
	        log_pass = new JPasswordField();
	        log_pass.setBounds(840, 40, 185, 28);
	        //-------------------------------------
	        reg_name = new HintTextField("(Your name)");
	        reg_name.setBounds(850, 220, 350, 42);
	        reg_name.setFont(reg_font);
	        //-------------------------------------
	        reg_id = new HintTextField("(Your personal ID)");
	        reg_id.setBounds(850, 280, 350, 42);
	        reg_id.setFont(reg_font);
	        //-------------------------------------
	        reg_phone = new HintTextField("(Your phone number)");
	        reg_phone.setBounds(850, 340, 350, 42);
	        reg_phone.setFont(reg_font);
	        //-------------------------------------
	        reg_mail = new HintTextField("(Your Gmail)");
	        reg_mail.setBounds(850, 400, 350, 42);
	        reg_mail.setFont(reg_font);
	        //-------------------------------------
	        reg_address = new HintTextField("(Address for shipping)");
	        reg_address.setBounds(850, 460, 350, 42);
	        reg_address.setFont(reg_font);
	        //-------------------------------------
	        reg_district = new HintTextField("(District for shipping)");
	        reg_district.setBounds(850, 520, 350, 42);
	        reg_district.setFont(reg_font);
	        //-------------------------------------
	        reg_pass = new JPasswordField();
	        reg_pass.setBounds(850, 580, 350, 42);
	        reg_pass.setFont(reg_font);
	        //-------------------------------------
	        reg_pass2nd_show = new JTextField();
	        reg_pass2nd_show.setBounds(850, 640, 350, 42);
	        reg_pass2nd_show.setFont(reg_font);
	        //-------------------------------------
	        reg_pass2nd_hide = new JPasswordField();
	        reg_pass2nd_hide.setBounds(850, 640, 350, 42);
	        reg_pass2nd_hide.setFont(reg_font);
	        //-------------------------------------
			
	        
	        //TextFields: choose
	        search = new HintTextField("What do you want?");
	        search.setBounds(150,75,370,40);
	        search.setFont(notes_font);
	        
			
			//TextFields: amount
	        notes = new JTextArea("-Notes here-");
	        notes.setFont(notes_font);
	        notes.setBounds(500,500,750,100);
	        //-------------------------------------
	        money = new JTextField("00.00", SwingConstants.RIGHT);
	        money.setFont(reg_font);
	        money.setEditable(false);
	        money.setBounds(70,75,200,30);
	        //-------------------------------------
	        
	        
	        //Scroll Panes: general
	        scr_help = new JScrollPane(help,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scr_help.setBackground(Color.WHITE);
	        scr_help.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        scr_help.setBounds(350,30,600,750);
	        //-------------------------------------
	        scr_view = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        scr_view.setBackground(Color.WHITE);
	        scr_view.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        scr_view.setBounds(150,70,1010,450);
	        //-------------------------------------
	        scr_address = new JScrollPane(tbl_address, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scr_address.setBackground(Color.WHITE);
	        scr_address.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        scr_address.setBounds(600,200,600,450);
	        //-------------------------------------
	        
			//Scroll Panes: choose
			scr_menu = new JScrollPane(tbl_menu,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scr_menu.setBackground(Color.WHITE);
	        scr_menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        scr_menu.setBounds(700,75,500,300);
	        //-------------------------------------
	        scr_pick = new JScrollPane(tbl_pick,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scr_pick.setBackground(Color.WHITE);
	        scr_pick.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        scr_pick.setBounds(700,450,500,300);
	        //-------------------------------------
	        
	        
	        //Scroll Panes: amount
	        scr_tbl_cart = new JScrollPane(tbl_cart,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        scr_tbl_cart.setBackground(Color.WHITE);
	        scr_tbl_cart.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        scr_tbl_cart.setBounds(500,75,750,400);
	        //-------------------------------------
	        scr_notes = new JScrollPane(notes,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        scr_notes.setBackground(Color.WHITE);
	        scr_notes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        scr_notes.setBounds(400,495,855,200);
	        //-------------------------------------
	        
	        
	        //Buttons: General
	        btn_help = new JButton();
	        btn_help.setIcon(new ImageIcon(Pathway.path + "\\Image\\help_img.png"));
	        btn_help.setBounds(1265,20,30,30);
	        btn_help.setRolloverEnabled(true);
	        btn_help.setRolloverIcon(new ImageIcon(Pathway.path + "\\Image\\hovered_help_img.png"));
	        btn_help.setBorderPainted(false);
			btn_help.setFocusPainted(false);
			btn_help.setContentAreaFilled(false);
	     	//-------------------------------------
			btn_x = new JButton("X");
			btn_x.setFont(btn_x_font);
			btn_x.setBounds(295,35,50,50);
			btn_x.setBackground(Color.WHITE);
			btn_x.setFocusPainted(false);
			//-------------------------------------
			btn_xx = new JButton("[X]");
			btn_xx.setFont(btn_x_font);
			btn_xx.setBounds(1160,50,80,60);
			btn_xx.setBorderPainted(false);
			btn_xx.setFocusPainted(false);
			btn_xx.setContentAreaFilled(false);
			//-------------------------------------
			btn_option = new JButton();
			btn_option.setIcon(new ImageIcon(Pathway.path + "\\Image\\option_img.png"));
			btn_option.setRolloverEnabled(true);
			btn_option.setRolloverIcon(new ImageIcon(Pathway.path + "\\Image\\hovered_option_img.png"));
			btn_option.setBounds(15,30,40,40);
			btn_option.setBorderPainted(false);
			btn_option.setFocusPainted(false);
			btn_option.setContentAreaFilled(false);
			//-------------------------------------
			btn_sq_option = new JButton();
			btn_sq_option.setIcon(new ImageIcon(Pathway.path + "\\Image\\sq_option_img.png"));
			btn_sq_option.setRolloverEnabled(true);
			btn_sq_option.setRolloverIcon(new ImageIcon(Pathway.path + "\\Image\\sq_hovered_option_img.png"));
			btn_sq_option.setBounds(15,30,40,40);
			btn_sq_option.setBorderPainted(false);
			btn_sq_option.setFocusPainted(false);
			btn_sq_option.setContentAreaFilled(false);
			//-------------------------------------
			btn_check_updates = new JButton("Check for updates");
			btn_check_updates.setFont(label_reg_font);
			btn_check_updates.setBounds(55,30,400,40);
			btn_check_updates.setBackground(btn_option_cl);
			btn_check_updates.setBorderPainted(false);
			btn_check_updates.setFocusPainted(false);
			//-------------------------------------
			btn_about = new JButton("About");
			btn_about.setFont(label_reg_font);
			btn_about.setBounds(55,70,400,40);
			btn_about.setBackground(btn_option_cl);
			btn_about.setBorderPainted(false);
			btn_about.setFocusPainted(false);
			//-------------------------------------
			btn_ok = new JButton("OK");
			btn_ok.setFont(label_reg_font);
			btn_ok.setBounds(200,300,100,70);
			btn_ok.setBackground(Color.WHITE);
			btn_ok.setFocusPainted(false);
			//-------------------------------------
			btn_check_tran = new JButton("View a transaction");
			btn_check_tran.setFont(label_reg_font);
			btn_check_tran.setBounds(55,110,400,40);
			btn_check_tran.setBackground(btn_option_cl);
			btn_check_tran.setBorderPainted(false);
			btn_check_tran.setFocusPainted(false);
			//-------------------------------------
			btn_tran_cancel = new JButton("Cancel");
			btn_tran_cancel.setFont(btn_done_font);
			btn_tran_cancel.setBounds(550,570,200,70);
			btn_tran_cancel.setBackground(Color.RED);
			btn_tran_cancel.setForeground(Color.BLACK);
			btn_tran_cancel.setFocusPainted(false);
			//-------------------------------------
			btn_all_trans = new JButton("View your transactions");
			btn_all_trans.setFont(label_reg_font);
			btn_all_trans.setBounds(55,150,400,40);
			btn_all_trans.setBackground(btn_option_cl);
			btn_all_trans.setBorderPainted(false);
			btn_all_trans.setFocusPainted(false);
			//-------------------------------------
			btn_acc_detail = new JButton("View account details");
			btn_acc_detail.setFont(label_reg_font);
			btn_acc_detail.setBounds(55,190,400,40);
			btn_acc_detail.setBackground(btn_option_cl);
			btn_acc_detail.setBorderPainted(false);
			btn_acc_detail.setFocusPainted(false);
			//-------------------------------------
			btn_change_name = new JButton("Change...");
			btn_change_name.setFont(btn_log_font);
			btn_change_name.setBounds(410,300,120,50);
			btn_change_name.setBackground(Color.WHITE);
			btn_change_name.setFocusPainted(false);
			//-------------------------------------
			btn_change_phone = new JButton("Change...");		//should customers change their phone number???
			btn_change_phone.setFont(btn_log_font);
			btn_change_phone.setBounds(410,400,120,50);
			btn_change_phone.setBackground(Color.WHITE);
			btn_change_phone.setFocusPainted(false);
			//-------------------------------------
			btn_view_rating = new JButton("Details...");
			btn_view_rating.setFont(btn_log_font);
			btn_view_rating.setBounds(410,500,120,50);
			btn_view_rating.setBackground(Color.WHITE);
			btn_view_rating.setFocusPainted(false);
			//-------------------------------------
			btn_add_address = new JButton("Add an address");
			btn_add_address.setFont(label_reg_font);
			btn_add_address.setBounds(600,700,300,50);
			btn_add_address.setBackground(Color.GREEN);
			btn_add_address.setFocusPainted(false);
			//-------------------------------------
			btn_remove_address = new JButton("Remove an address");
			btn_remove_address.setFont(label_reg_font);
			btn_remove_address.setBounds(900,700,300,50);
			btn_remove_address.setBackground(Color.RED);
			btn_remove_address.setFocusPainted(false);
			//-------------------------------------
			btn_change_pass = new JButton("Change your password");
			btn_change_pass.setFont(label_reg_font);
			btn_change_pass.setBounds(110,600,420,50);
			btn_change_pass.setBackground(Color.WHITE);
			btn_change_pass.setFocusPainted(false);
			//-------------------------------------
			btn_add_pid = new JButton("Attach your personal ID");
			btn_add_pid.setFont(label_reg_font);
			btn_add_pid.setBounds(110,700,420,50);
			btn_add_pid.setBackground(Color.WHITE);
			btn_add_pid.setFocusPainted(false);
			//-------------------------------------
			
			
			//Buttons: login
			btn_log = new JButton("Login");
			btn_log.setBackground(btn_login_cl);
			btn_log.setBounds(1040, 40, 80, 30);
			btn_log.setForeground(Color.WHITE);
			btn_log.setFont(btn_log_font);
			//-------------------------------------
			btn_go = new JButton("Let's Gooooo!");
			btn_go.setFont(btn_done_font);
			btn_go.setBounds(840, 715, 300, 70);
			btn_go.setBackground(btn_login_cl);
			btn_go.setForeground(Color.WHITE);
			btn_go.setFocusPainted(false);
			//-------------------------------------
			btn_show_pass2nd = new JButton();		//click to show, indicate hiding
			btn_show_pass2nd.setIcon(new ImageIcon(Pathway.path + "\\Image\\hide_img.png"));
			btn_show_pass2nd.setBounds(1200, 640, 60, 42);
			btn_show_pass2nd.setBorderPainted(false);
			btn_show_pass2nd.setFocusPainted(false);
			btn_show_pass2nd.setContentAreaFilled(false);
			//-------------------------------------
			btn_hide_pass2nd = new JButton();		//click to hide, indicate showing
			btn_hide_pass2nd.setIcon(new ImageIcon(Pathway.path + "\\Image\\show_img.png"));
			btn_hide_pass2nd.setBounds(1200, 640, 60, 42);
			btn_hide_pass2nd.setBorderPainted(false);
			btn_hide_pass2nd.setFocusPainted(false);
			btn_hide_pass2nd.setContentAreaFilled(false);
			//-------------------------------------
			
			
			//Buttons: choose
			btn_add = new JButton("ADD");
			btn_add.setBounds(600,75,80,80);
	        btn_add.setBackground(Color.WHITE);
	        //-------------------------------------
	        btn_drop = new JButton("DROP");
			btn_drop.setBounds(600,450,80,80);
	        btn_drop.setBackground(Color.WHITE);
	        //-------------------------------------
	        btn_next = new JButton();
	        btn_next.setIcon(new ImageIcon(Pathway.path + "\\Image\\next_img.png"));
	        btn_next.setRolloverEnabled(true);
	        btn_next.setRolloverIcon(new ImageIcon(Pathway.path + "\\Image\\hovered_next_img.png"));
			btn_next.setBounds(400,150,115,125);
			btn_next.setBorderPainted(false);
			btn_next.setFocusPainted(false);
			btn_next.setContentAreaFilled(false);
	        //-------------------------------------
			btn_out = new JButton("(Sign out)");
			btn_out.setBounds(330,5,100,30);
			btn_out.setBorderPainted(false);
			btn_out.setFocusPainted(false);
			btn_out.setContentAreaFilled(false);
			//-------------------------------------
			btn_acc = new JButton();	//click this to view account options
			btn_acc.setBounds(330,5,100,30);
			btn_acc.setBorderPainted(false);
			btn_acc.setFocusPainted(false);
			btn_acc.setContentAreaFilled(false);
			//-------------------------------------
			
			
			//Buttons: amount
			btn_done = new JButton("ORDER!");
			btn_done.setFont(btn_done_font);
			btn_done.setBounds(920,720,240,75);
			btn_done.setBackground(Color.WHITE);
			//-------------------------------------
			btn_plus_1 = new JButton("+ 1"); 
			btn_plus_1.setFont(digit_font);
			btn_plus_1.setBounds(400,75,80,80); 
			btn_plus_1.setBackground(Color.WHITE);
			//-------------------------------------
			btn_plus_5 = new JButton("+ 5"); 
			btn_plus_5.setFont(digit_font);
			btn_plus_5.setBounds(400,182,80,80); 
			btn_plus_5.setBackground(Color.WHITE);
			//-------------------------------------
			btn_minus_1 = new JButton("– 1"); 
			btn_minus_1.setFont(digit_font);
			btn_minus_1.setBounds(400,288,80,80);
			btn_minus_1.setBackground(Color.WHITE);
			//-------------------------------------
		  	btn_minus_5 = new JButton("– 5"); 
		  	btn_minus_5.setFont(digit_font);
		  	btn_minus_5.setBounds(400,395,80,80);
		  	btn_minus_5.setBackground(Color.WHITE);
		  	//-------------------------------------
		  	btn_unit = new JButton("VND" + sp_char_d);
		  	btn_unit.setFont(digit_font);
		  	btn_unit.setBounds(280,75,100,30); 
		  	btn_unit.setBackground(Color.WHITE);
		  	btn_unit.setBorderPainted(false);
		  	btn_unit.setFocusPainted(false);
		  	//-------------------------------------
		  	btn_back = new JButton("BACK"); 
		  	btn_back.setFont(btn_done_font);
		  	btn_back.setBounds(10,730,160,70);
		  	btn_back.setBackground(Color.WHITE);
		  	btn_back.setFocusPainted(false);
		  	//-------------------------------------
			 			
			
			//Labels: general
			label_view = new JLabel();
			label_view.setBackground(Color.WHITE);
			label_view.setOpaque(true);
			/* label_error_msg.setVerticalAlignment(SwingConstants.NORTH); */
			label_view.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK), // outer border
														 BorderFactory.createEmptyBorder(5, 5, 5, 5))); // inner invisible border as the margin 			
			label_view.setBounds(70,50,1170,750);
			//-------------------------------------
			label_acc_mod = new JLabel("Account's Information");
			label_acc_mod.setFont(reg_label_font);
			label_acc_mod.setBounds(500,100,500,80);
	        //-------------------------------------
	        label_acc_name = new JLabel("Name:", SwingConstants.LEFT);
	        label_acc_name.setFont(label_reg_font);
	        label_acc_name.setBounds(110, 255, 300, 50);
	        //-------------------------------------
	        label_acc_phone = new JLabel("Phone number:", SwingConstants.LEFT);
	        label_acc_phone.setFont(label_reg_font);
	        label_acc_phone.setBounds(110, 355, 300, 50);
	        //-------------------------------------
	        label_acc_rating = new JLabel("Rating:", SwingConstants.LEFT);
	        label_acc_rating.setFont(label_reg_font);
	        label_acc_rating.setBounds(110, 455, 300, 50);
	        //-------------------------------------
	        
	        
	        //Labels: login
	        label_red_banner = new JLabel();
	        label_red_banner.setBackground(Color.RED);
	        label_red_banner.setBounds(0,0,1920,100);
	        label_red_banner.setOpaque(true);
	        //-------------------------------------
	        label_log_mail = new JLabel("Login email");
	        label_log_mail.setForeground(Color.WHITE);
	        label_log_mail.setBounds(640,20,70,15);
	        //-------------------------------------
	        label_log_pass = new JLabel("Password");
	        label_log_pass.setForeground(Color.WHITE);
	        label_log_pass.setBounds(840,20,70,15);
	        //-------------------------------------
	        label_create = new JLabel("Create a");
	        label_create.setFont(reg_label_font);
	        label_create.setBounds(775,150,250,55);
	        //-------------------------------------
	        label_account = new JLabel(" account");
	        label_account.setBounds(1050,150,400,55);
	        label_account.setFont(reg_label_font);
	        //-------------------------------------
	        label_sq_left_icon = new JLabel();
	        label_sq_left_icon.setIcon(new ImageIcon(Pathway.path + "\\Image\\top_img.png"));
	        label_sq_left_icon.setBounds(150,15,158,66);
	        //-------------------------------------
	        label_ro_mid_icon = new JLabel();
	        label_ro_mid_icon.setIcon(new ImageIcon(Pathway.path + "\\Image\\mid_img.png"));
	        label_ro_mid_icon.setBounds(30,140,600,600);
	        //-------------------------------------
	        label_reg_icon = new JLabel();
	        label_reg_icon.setIcon(new ImageIcon(Pathway.path + "\\Image\\reg_img.png"));
	        label_reg_icon.setBounds(921,123,133,100);
	        //-------------------------------------
	        label_reg_name = new JLabel("Name:");
	        label_reg_name.setBounds(765,220,140,50);
	        label_reg_name.setFont(label_reg_font);
	        //-------------------------------------
	        label_reg_id = new JLabel("Personal ID:");
	        label_reg_id.setBounds(690,280,200,50);
	        label_reg_id.setFont(label_reg_font);
	        //-------------------------------------
	        label_reg_phone = new JLabel("Phone no.:");
	        label_reg_phone.setBounds(725,340,200,50);
	        label_reg_phone.setFont(label_reg_font);
	        //-------------------------------------
	        label_reg_mail = new JLabel("Gmail:");
	        label_reg_mail.setBounds(770,400,133,50);
	        label_reg_mail.setFont(label_reg_font);
	        //-------------------------------------
	        label_reg_address = new JLabel("Adress:");
	        label_reg_address.setBounds(755,460,133,50);
	        label_reg_address.setFont(label_reg_font);
	        //-------------------------------------
	        label_reg_district = new JLabel("District:");
	        label_reg_district.setBounds(740,520,133,50);
	        label_reg_district.setFont(label_reg_font);
	        //-------------------------------------
	        label_reg_pass = new JLabel("Password:");
	        label_reg_pass.setBounds(725,580,145,50);
	        label_reg_pass.setFont(label_reg_font);
	        //-------------------------------------
	        label_reg_pass2nd = new JLabel("Confirm password:");
	        label_reg_pass2nd.setBounds(625,640,250,50);
	        label_reg_pass2nd.setFont(label_reg_font);
	        //-------------------------------------
	        label_error_log_mail = new JLabel("", SwingConstants.LEFT);
	        label_error_log_mail.setFont(btn_log_font);
	        label_error_log_mail.setBounds(640,70,200,30);
	        label_error_log_mail.setForeground(Color.WHITE);
	        //-------------------------------------
	        label_error_log_pass = new JLabel("", SwingConstants.LEFT);
	        label_error_log_pass.setFont(btn_log_font);
	        label_error_log_pass.setBounds(840,70,200,30);
	        label_error_log_pass.setForeground(Color.WHITE);
	        //-------------------------------------
	        label_error_reg_mail = new JLabel("", SwingConstants.LEFT);
	        label_error_reg_mail.setFont(btn_log_font);
	        label_error_reg_mail.setBounds(850, 435, 500, 30);
	        label_error_reg_mail.setForeground(btn_login_cl);
	        //-------------------------------------
	        label_error_reg_pass = new JLabel("", SwingConstants.LEFT);
	        label_error_reg_pass.setFont(btn_log_font);
	        label_error_reg_pass.setBounds(850,615,500,30);
	        label_error_reg_pass.setForeground(btn_login_cl);
	        //-------------------------------------
	        label_error_reg_pass2nd = new JLabel("", SwingConstants.LEFT);
	        label_error_reg_pass2nd.setFont(btn_log_font);
	        label_error_reg_pass2nd.setBounds(850,675,500,30);
	        label_error_reg_pass2nd.setForeground(btn_login_cl);
	        //-------------------------------------
	        label_error_reg_phone = new JLabel("", SwingConstants.LEFT);
	        label_error_reg_phone.setFont(btn_log_font);
	        label_error_reg_phone.setBounds(850,375,500,30);
	        label_error_reg_phone.setForeground(btn_login_cl);
	        //-------------------------------------
	        
	        
	        //Labels: choose
	        label_acc = new JLabel("default_mail@gmail.com", SwingConstants.RIGHT);
	        label_acc.setBounds(110,5,225,30);
	        label_acc.setForeground(Color.WHITE);
	        //-------------------------------------
	        label_back = new JLabel();
	        label_back.setIcon(new ImageIcon(Pathway.path + "\\Image\\back_img.jpg"));
	        label_back.setBounds(0,0,1313,850);
	        //-------------------------------------
	        label_cont = new JLabel("After choosing, click this to continue ==>");
	        label_cont.setBounds(150,200,250,20);
	        //-------------------------------------
	        label_menu = new JLabel("Menu");
	        label_menu.setFont(label_menu_font);
	        label_menu.setBounds(880,25,150,55);
	        label_menu.setForeground(Color.BLACK);
	        //-------------------------------------
	        label_pick = new JLabel("Chosen");
	        label_pick.setFont(label_menu_font);
	        label_pick.setBounds(880,395,150,55);
	        label_pick.setForeground(Color.BLACK);
	        //-------------------------------------
	        
	        
	        //Labels: amount
	        label_triangle = new JLabel();
	        label_triangle.setBounds(1175,720,75,75);
	        label_triangle.setIcon(new ImageIcon(Pathway.path + "\\Image\\tri_img.png"));
	        //-------------------------------------
	        label_cart = new JLabel("Your cart");
	        label_cart.setBounds(700,5,200,70);
	        label_cart.setForeground(Color.BLACK);
	        label_cart.setFont(label_menu_font);
	        //-------------------------------------
	        label_total = new JLabel("Total: ");
	        label_total.setForeground(Color.BLACK);
	        label_total.setFont(label_reg_font);
	        label_total.setBounds(70,30,133,50);
	        //-------------------------------------
	        
	        
	        //add --- general
	        add(btn_help, new Integer(50), new Integer(0));
	        add(btn_option, new Integer(50), new Integer(0));
	        
	        
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
			  log_mail.requestFocusInWindow();
		}

}
//ctrl + shift + / : comment
//ctrl + shift + \ : uncomment
//tran = 1 transaction <==> trans = transactions
