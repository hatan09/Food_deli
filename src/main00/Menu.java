package main00;

public class Menu {
	int id;
	String name;
	int price;
	
	public Menu(int id, String name, int price) {
		if(id > 0 && !name.isEmpty() && price > 0) {
			this.id = id;
			this.name = name;
			this.price = price;
		}
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
}
