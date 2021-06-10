package main00;

public abstract class Convert_VND {
	static int USD = 23400, Yen = 230, Won = 20;
	
	public static int fromVND (String to, int money) {
		switch (to) {
			case "USD": {money = money*USD; break;}
			case "Yen": {money = money*Yen; break;}
			case "Won": {money = money*Won; break;}
			case "VND": break;
			default: {System.out.println("Not supported"); break;}
		}
		return money;
	}
	
	public static int toVND (String from, int money) {
		switch (from) {
			case "USD": {money = money/USD; break;}
			case "Yen": {money = money/Yen; break;}
			case "Won": {money = money/Won; break;}
			case "VND": break;
			default: {System.out.println("Not supported"); break;}
		}
		return money;
	}
	
	public void updateRate (String unit, int rate) {
		switch (unit) {
		case "USD": {USD = rate; break;}
		case "Yen": {Yen = rate; break;}
		case "Won": {Won = rate; break;}
		case "VND": break;
		default: {System.out.println("Not supported"); break;}
		}
	}
}
