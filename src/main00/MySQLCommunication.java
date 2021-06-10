package main00;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Conector;

public abstract class MySQLCommunication {
	static Connection con = Conector.getJDBCConnection();
	static int Cus_ID = 0;
	static String Email = "";
	static String Name = "";
	static ArrayList<String> Addresses = new ArrayList();
	static ArrayList<String> StandardAddresses = new ArrayList();
	static Long Phone = 0l;
	static int Rating = 0;
	static int Trans_ID = 0;	//use this to temporarily store trans_id that just create so as to create orders
	
	public static boolean checkRegPhone(Long phone) {
		String query = "SELECT Phone FROM customers WHERE Phone = ?";
		boolean exist = false;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setLong(1, phone);
			ResultSet rs = pst.executeQuery();
			exist = rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return exist;
	}
	
	public static boolean checkRegEmail(String email) {
		String query = "SELECT Email FROM customers WHERE Email = ?";
		boolean exist = false;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			exist = rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return exist;
	}

	public static boolean checkPhoneHasEmail(Long phone) {
		String query = "SELECT Email FROM customers WHERE Phone = ? AND Email IS NOT NULL";
		boolean exist = false;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setLong(1, phone);
			ResultSet rs = pst.executeQuery();
			exist = rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return exist;
	}
	
	public static boolean checkAuth(int tran_id, String email, String pass) {
		String query1 = "SELECT Cus_ID FROM transactions WHERE Trans_ID = ?  AND Status NOT IN ('Failed', 'Done')";
		String query2 = "SELECT * FROM customers WHERE Cus_ID = ? AND Email = ? AND Password = ?";
		int cus_id = 0;
		boolean success = false;
		try {
			//get Cus_ID that owns input trans_id
			PreparedStatement pst1 = con.prepareStatement(query1);
			PreparedStatement pst2 = con.prepareStatement(query2);
			pst1.setInt(1, tran_id);
			ResultSet rs1 = pst1.executeQuery();
			if(rs1.next()) {	//check if there is any transaction of inputted trans_id and not done or failed, if yes, continue
				cus_id = rs1.getInt("Cus_ID");
				//check if he knows email & password
				pst2.setInt(1, cus_id);
				pst2.setString(2, email);
				pst2.setString(3, pass);
				ResultSet rs2 = pst2.executeQuery();
				success = (rs2.next())? true : false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return success;
	}

	public static boolean cancelTran(int tran_id, String reason) {
		String query = ("UPDATE Transactions SET Status = 'Failed', Reason = ? WHERE Trans_ID = ?");
		boolean success = false;
		try {
			PreparedStatement pst1 = con.prepareStatement(query);
			pst1.setString(1, reason);
			pst1.setInt(2, tran_id);
			int rs = pst1.executeUpdate();
			success = (rs != 0)? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return success;
	}
	
	public static boolean createNewCustomer(String name, String pid, Long phone, String email, String address, String district, String pass) {
		String query = "INSERT INTO Customers (Name, Personal_ID, Phone, Email, Password) VALUES (?, ?, ?, ?, ?)";
		String get_id = "SELECT Cus_ID from Customers WHERE Email = ?";
		boolean success = false;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, pid);
			pst.setLong(3, phone);
			pst.setString(4, email);
			pst.setString(5, pass);
			int rs = pst.executeUpdate();
			PreparedStatement get_id_query = con.prepareStatement(get_id);
			get_id_query.setString(1, email);
			ResultSet set = get_id_query.executeQuery();
			if(rs == 1) {	//check if the creation of new customer successful, if yes
				if(set.next()) {	//check if able to get Cus_ID of new customer, if yes
					Cus_ID = set.getInt("Cus_ID");
					if(createAddress(Cus_ID, address, district)) {	//check if creating new address successful, if yes
						success = true;
					}
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return success;
	}
	
	public static boolean createAddress(int id, String address, String district) {
		String query = "INSERT INTO Addresses (Cus_ID, Address, District) VALUES (?, ?, ?)";
		boolean success = false;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, Cus_ID);
			pst.setString(2, address);
			pst.setString(3, district);
			int rs = pst.executeUpdate();
			success = (rs==1)? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return success;
	}
	
	public static boolean removeAddress(String address, String district) {
		String query = "DELETE FROM Addresses WHERE Cus_ID = ? AND Address = ? AND District = ?";
		boolean success = false;
		try {
			//create new trans
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, Cus_ID);
			pst.setString(2, address);
			pst.setString(3, district);
			int output = pst.executeUpdate();
			success = (output==1)? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return success;
	}
	
	public static boolean createTran(String address, String district, int money) {
		String query1 = "INSERT INTO Transactions (Cus_ID, Address, District, Total_money) VALUES (?, ?, ?, ?)";
		String query2 = "SELECT Trans_ID FROM Transactions WHERE Cus_ID = ? ORDER BY Trans_ID DESC LIMIT 1";
		boolean success = false;
		try {
			//create new trans
			PreparedStatement pst = con.prepareStatement(query1);
			pst.setInt(1, Cus_ID);
			pst.setString(2, address);
			pst.setString(3, district);
			pst.setInt(4, money);
			int output = pst.executeUpdate();
			success = (output==1)? true : false;
			//get trans_id of the one that just created
			PreparedStatement pst2 = con.prepareStatement(query2);
			pst2.setInt(1, Cus_ID);
			ResultSet rs = pst2.executeQuery();
			if(rs.next()) {
				Trans_ID = rs.getInt("Trans_ID");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return success;
	}
	
	public static boolean createOrder(int menu_id, int amount) {
		String query = "INSERT INTO Orders (Trans_ID, Menu_ID, Amount) VALUES (?, ?, ?)";
		boolean success = false;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, Trans_ID);
			pst.setInt(2, menu_id);
			pst.setInt(3, amount);
			int output = pst.executeUpdate();
			success = (output==1)? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return success;
	}
	
	public static boolean updateCustomerInfo(Long phone, String pid, String email, String pass) {
		String query = "UPDATE Customers SET Personal_ID = ?, Email = ?, Password = ? WHERE Phone = ?";
		boolean success = false;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, pid);
			pst.setString(2, email);
			pst.setString(3, pass);
			pst.setLong(4, phone);
			int rs = pst.executeUpdate();
			success = (rs == 1)? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return success;
	}
	
	public static boolean updateCustomerSingleInfo(String attribute, String input) {
		String query = "UPDATE Customers SET " + attribute + " = ? WHERE Cus_ID = ?";
		boolean success = false;
		boolean cont = true;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			switch(attribute) {
			case("Name"):{pst.setString(1, input); break;}
			case("Phone"):{pst.setLong(1, Long.parseLong(input)); break;}
			case("Password"):{pst.setString(1, input); break;}
			case("Personal_ID"):{pst.setString(1, input); break;}
			default:{cont = false; break;}
			}
			pst.setInt(2, Cus_ID);
			int rs = 0;
			if(cont) rs = pst.executeUpdate();
			success = (rs == 1)? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return success;
	}
	
	public static boolean checkLoginEmail(String email) {
		String query = "SELECT Email FROM customers WHERE Email = ?";
		boolean exist = false;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			exist = rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return exist;
	}
	
	public static boolean checkLoginPass(String email, String pass) {
		String query = "SELECT Cus_ID FROM customers WHERE Email = ? AND BINARY Password = ?";
		boolean correct = false;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			correct = rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return correct;
	}
	
	public static boolean checkInputPass(String pass) {
		String query = "SELECT Cus_ID FROM customers WHERE Email = ? AND Password = ?";
		boolean correct = false;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, Email);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			correct = rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return correct;
	}
	
	public static boolean checkHasPid() {
		String query = "SELECT Cus_ID FROM customers WHERE Email = ? AND Personal_ID NOT IN ('No data')";
		boolean exist = false;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, Email);
			ResultSet rs = pst.executeQuery();
			exist = rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return exist;
	}
	
	public static boolean storeCustomerData(String email) {
		//clear
		clearData();
		
		String query = "SELECT * FROM customers WHERE Email = ?";
		boolean success = true;
		//try {
			//PreparedStatement pst = con.prepareStatement(query);
			//pst.setString(1, email);
			//ResultSet rs = pst.executeQuery();
			//success = rs.next();
			//if(success) {
				//Email = rs.getString("Email");
				//Cus_ID = rs.getInt("Cus_ID");
				//Name = rs.getString("Name");
				//Phone = rs.getLong("Phone");
				//Rating = rs.getInt("Rating");
				//storeAddresses(Cus_ID);
			//}
		//}
		//catch (SQLException e) {
		//	e.printStackTrace();
		//};
		return success;
	}

	public static void storeAddresses(int Cus_id) {
		String query = "SELECT Address, District FROM Addresses WHERE Cus_ID = ?";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, Cus_ID);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Addresses.add(rs.getString("Address"));
				String temp = rs.getString("Address") + " - district: " + rs.getString("District");
				StandardAddresses.add(temp);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
	}
	
	public static ArrayList<Menu> getMenu() {
		String query = "SELECT * FROM menu";
		ArrayList<Menu> list = new ArrayList();
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				list.add(new Menu(rs.getInt("Menu_ID"), rs.getString("Name"), rs.getInt("Price")));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return list;
	}

	public static String getTran(int tran_ID) {
		String query = "SELECT * FROM transactions WHERE Trans_ID = ?";
		String row = "";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, tran_ID);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				row = row + rs.getInt("Trans_ID");
				row = row + "," + rs.getTimestamp("Date");
				row = row + "," + rs.getInt("Total_money") + "VND";
				row = row + "," + rs.getString("Status");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		if (row.isEmpty()) {
			return "";
		}
		else return row;
	}
	
	public static ArrayList<String> getTrans(String email) {
		String query = "SELECT t.Trans_ID, c.Name, t.Date, t.Total_money, t.Status, t.Reason FROM transactions t, customers c WHERE c.Email = ? AND t.Cus_ID=c.Cus_ID";
		ArrayList<String> list = new ArrayList();
		int count = 0;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				list.add(Integer.toString(rs.getInt("Trans_ID")));
				list.set(count, list.get(count) + "," + rs.getString("Name"));
				list.set(count, list.get(count) + "," + rs.getTimestamp("Date"));
				list.set(count, list.get(count) + "," + Integer.toString(rs.getInt("Total_money")) + "VND");
				String temp = rs.getString("Status");
				list.set(count, list.get(count) + "," + temp);
				if(temp.equals("Failed")) list.set(count, list.get(count) + "," + rs.getString("Reason"));	//if failed, get reason
				else list.set(count, list.get(count) + ", ");
				count++;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		return list;
	}
	
	public static String getVersion() {
		String query = "SELECT Name FROM version";
		String row = "";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				row = rs.getString("Name");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		};
		if (row.isEmpty()) {
			return "";
		}
		else return row;
	}

	public static void clearData() {
		Cus_ID = 0;
		Email = "";
		Name = "";
		Addresses.clear();
		StandardAddresses.clear();
		Phone = 0l;
		Rating = 0;
		Trans_ID = 0;
	}
}


