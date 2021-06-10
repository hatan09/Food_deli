package main00;

public abstract class FAQs {
	static String[] list = {
			  "________________________________________________\n"
			, " * Can I cancel transaction?\n"
			, " ==> Yes you can, but you will receive some penalties on\n"
			+ "your rating score. The decrement of your rating depends on\n"
			+ "the current status of the transaction.\n"
			, "________________________________________________\n"
			, " * Can I use my credit card to pay?\n"
			, " ==> Our system and current version DO NOT accept\n"
			+ "credit/ATM/debit/VISA/Master cards or any cards.\n"
			+ "We will add this feature in the near future.\n"
			+ "So sorry for this inconvenience!\n"
			, "________________________________________________\n"
			, " * Can I use a phone number from another country?\n"
			, " ==> We only use phone numbers that are available in\n"
			+ "Vietnam (code : +84).\n"
			+ "If problems happen, we will not work with those ones\n"
			+ "which are not phone numbers from Vietnam.\n"
			, "________________________________________________\n"
			};
	static int size = list.length;
	public static String showHelp(int a) {
		if(a==size) return "\n-End-";
		else return list[a] + showHelp(a+1);
	}
	
	public static String printFAQs() {
		return "-FAQs-\n\n" + showHelp(0);
	}
}
