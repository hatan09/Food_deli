package main00;

public class Order {
    private String name;
    private int amount = 0;
    private int id;
    private int price = 0;

    public int getAmount() {
        return amount;
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

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPrice(int price){
    	if(price > 0) this.price = price;
    }

    public Order(int id, int amount){
    	if (amount>-1) {
    	setAmount(amount);
        setID(id);
    	}
    }
    public Order(String name, int amount){
    	if (amount>-1) {
    	setAmount(amount);
        setName(name);
    	}
    }
    
    public Order(int id, String name, int amount, int price){
    	if (amount>-1 && price > 0) {
        setAmount(amount);
        setName(name);
        setPrice(price);
        setID(id);
    	}
    }
}
