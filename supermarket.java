import java.util.*;
class product {
    int id;
    String name;
    double price;
    double stock_quantity;
    double purchased_price;
    static int count;

    public product(String name, double price, double stock_quantity, double purchased_price) {
        this.id = ++count;
        this.name = name;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.purchased_price = purchased_price;
    }

    public double getqty() {
        return this.stock_quantity;
    }

    public void setqty(double qty) {
        this.stock_quantity = qty;
    }

    public double getprice() {
        return this.price;
    }

    public void display() {
        System.out.println("Product id : " + id);
        System.out.println("Product name : " + name);
        System.out.println("Selling price: Rs." + price);
        System.out.println("Stock Quantity: " + stock_quantity + "kg");
        System.out.println("Purchased price: Rs." + purchased_price);
        System.out.println();
    }
}

class customer {
    static int count = 1000;
    int id ;
    String name;
    String mobile_no;

    public customer(String name, String mobile_no) {
        this.id = ++count;
        this.name = name;
        this.mobile_no = mobile_no;
    }
    public void display() {
        System.out.println("customer id : " + id);
        System.out.println("name : " + name);
        System.out.println("mobile number  : " + mobile_no);
    }
}

class sale {
    product p;
    double qty;
    double amount;

    sale(product p, double qty) {
        this.p = p;
        this.qty = qty;
    }

    public double payable_amt() {
        this.amount = (p.getprice() * this.qty);
        p.setqty(p.getqty() - this.qty);
        return this.amount;
    }

}
class premiumcustomer extends customer{
    int points;

	premiumcustomer(String name, String mobile_no) {
        super(name, mobile_no);
        this.points = 0;
    }
	public void setpoints(int points){
		this.points = points;
	}
	public int getpoints(){
		return this.points;
	}
    public void display(){
        super.display();
        System.out.println("Total available points :"+ this.points);
    }
}

class supermarket {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        List<product> l = new ArrayList<product>();
        List<customer> c = new ArrayList<customer>();
        List<premiumcustomer> pc = new ArrayList<>();
        while (true) {
            System.out.println("Enter the operation");
            System.out.println("Enter 1 to add a product .");
            System.out.println("Enter 2 to add a customer .");
            System.out.println("Enter 3 to add a Premium customer .");
            System.out.println("Enter 4 to display available products .");
            System.out.println("Enter 5 to go for billing .");
            int a = sc.nextInt();
            if (a == 1) {
                sc.nextLine();
                System.out.println("Enter the product name :");
                String s = sc.nextLine();
                System.out.println("Enter the product price per kg : ");
                double pri = sc.nextDouble();
                System.out.println("Enter the product stock quantity : ");
                double stk = sc.nextDouble();
                System.out.println("Enter the product purchased price : ");
                double pp = sc.nextDouble();
                product new_prod = new product(s, pri, stk, pp);
                l.add(new_prod);
            } else if (a == 2) {
                System.out.println("Enter the customer name :");
                String name = sc.nextLine();
                sc.nextLine();
                System.out.println("Enter the customer mobile number:");
                String mob = sc.nextLine();
                customer c1 = new customer(name,mob);
                c.add(c1);
                System.out.println("Customer Added successfully !!");
            } 
            else if(a==3){
                System.out.println("Enter the customer name :");
                String name = sc.nextLine();
                sc.nextLine();
                System.out.println("Enter the customer mobile number:");
                String mob = sc.nextLine();
                premiumcustomer pc1 = new premiumcustomer(name,mob);
                pc.add(pc1);
                System.out.println("Customer Added successfully !!");
            }
              else if(a==4){
                for (product p  : l) {
                    p.display();
                }
              }
            else if(a == 5) {
                List<product> bill = new ArrayList<>();
                double payableAmount = 0.0;
                while(true){
                    System.out.println("Enter 1 to continue shopping & 2 to exit ");
                    int num = sc.nextInt();
                    if(num==1){
                    System.out.println("Enter the product name: ");
                    sc.nextLine();
                    String productName = sc.nextLine();
                    for(product p : l){
                        if(p.name.equals(productName)){
                            System.out.println("Enter the  quantity: ");
                            double saleQuantity = sc.nextDouble();
                            sale sl = new sale(p,saleQuantity);
                            payableAmount += sl.payable_amt();
                            break;
                        }
                        else{
                            System.out.println("Product not found ");
                        }
                    }
                }
                else{
                    System.out.println("The total payable amount is : ");
                    System.out.println(payableAmount);
                    sc.nextLine();
                    System.out.println("If premium customer --> type yes");
                    String type = sc.nextLine();
                    System.out.println("Enter the customer mobile number :");  
                    String number = sc.nextLine();
                    boolean ans = false;
                    premiumcustomer pc1 = null;
                    if(type.equalsIgnoreCase("yes")){
                        for(premiumcustomer k : pc){
                            if(k.mobile_no.equals(number)){
                                pc1.setpoints((int)payableAmount/100);
                                ans=true;
                                break;
                            }
                        }
                            if(ans==false){
                                System.out.println("Enter customer name :");
                                String name = sc.nextLine();
                                premiumcustomer prem = new premiumcustomer(name, number);
                                pc.add(prem);
                                prem.setpoints((int)payableAmount/100);
                                System.out.println("Thankyou for Shopping!!");
                            }
                        }
                    else{
                        boolean ans2 = false;
                        for(customer k : c){
                            if(k.mobile_no.equals(number)){
                                ans2=true;
                                break;
                            }
                        }
                            if(ans2==false){
                                System.out.println("Enter customer name :");
                                String name = sc.nextLine();
                                customer prem = new customer(name, number);
                                c.add(prem);
                                System.out.println("Thankyou for Shopping!!");
                            }
                    }
                    break;
                }

                    }
            }
            else {
                System.out.println("Pleasee choose Valid operation !!");
            }

        }
    }
}


