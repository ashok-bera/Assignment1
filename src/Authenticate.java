import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class User
{
	String fname;
	String email;
	String password;
	String lname;
	//static int count=0;
	  Connection con = null;
	public void register()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("\n Enter Fname :");
		fname=sc.next();
		System.out.print("\n Enter lname :");
		lname=sc.next();
		System.out.print("\n Enter email :");
		email=sc.next();
		System.out.print("\n Enter password  :");
		password=sc.next();
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment", "root", "Pass@123");
            PreparedStatement ps = con.prepareStatement("insert into Auth values(?,?,?,?)");
         
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.execute();
            
            System.out.println("Registered successfully..!!");
            System.out.println("Now u have to login..!!");

        } catch (Exception e) {
            System.out.println("Couldnot register");
        }
		
    }
	
	public void login()
	{   
		Scanner sc = new Scanner(System.in);
		System.out.print("\n Enter email :");
		email=sc.next();
		System.out.print("\n Enter password  :");
		password=sc.next();
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment", "root", "Pass@123");
            String sql="select * from Auth";
            Statement st=con.createStatement();         
            ResultSet rs=null;
            rs=st.executeQuery(sql);
            
            while(rs.next())
            {
            if((email.equals(rs.getString(3))) && (password.equals(rs.getString(4))))
            {
            		System.out.println("You have loggedin");
            }
//            else
//            {
//            	System.out.println("credentials are wrong ... try again");
//            	System.exit(0);
//            }
            }
            
        } 
		catch (Exception e) {
            System.out.println("Email or password is wrong ..!");
        }

	}
}

class Bank
{   
	
	String name;
	String ifsc;
	String accno;
	String pno;
	int balance;
	int choice;
	public void createaccount()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("\n Enter name :");
		name=sc.next();
		System.out.print("\n Enter ifsc :");
		ifsc=sc.next();
		System.out.print("\n Enter accno :");
		accno=sc.next();
		System.out.print("\n Enter pno  :");
		pno=sc.next();
		System.out.print("\n Enter balance  :");
		balance=sc.nextInt();
		
		try
		{       
			    Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment", "root", "Pass@123");
				PreparedStatement ps = c.prepareStatement("insert into account values(?,?,?,?,?)");
				
				ps.setString(1, name);
				ps.setString(2,ifsc);
				ps.setString(3, accno);
				ps.setString(4, pno);
				ps.setInt(5, balance);
				//ps.setString(5, desig);
				
				ps.execute();
				//c.close();
				System.out.println("account successfully created ...!!");
				
			}
			catch(Exception e)
			{
				System.out.println("try once more ...!!");
			}
		
	}
	
	public void checkbalance()
	{   Scanner sc = new Scanner(System.in);
		System.out.print("\n Enter accno :");
		accno=sc.next();
		
		try
		{  
			//String accno = request.getParameter("accno");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment", "root", "Pass@123");
			PreparedStatement ps = c.prepareStatement("select * from account where accno = ?");
			ps.setString(1,accno);
			ResultSet rs = ps.executeQuery();
			//PrintWriter out = response.getWriter();
			while(rs.next())
			{
			 System.out.println("Your Balance is:");
             System.out.println(rs.getString(5) + ":" + rs.getString(1) );
			}
            ps.execute();
		}
		catch(Exception e)
		{
			System.out.println("Invalid credentials....Plzz enter right ones ..");
		}
	}
	
	public void Transfer()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("\n Enter the choice if user want to withdraw enter 2 otherwise enter 1 :");
		choice=sc.nextInt();
		System.out.print("\n Enter accno :");
		accno=sc.next();
		System.out.print("\n Enter the balance to be transferred :");
		balance=sc.nextInt();
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment", "root", "Pass@123");
			if(choice==1)
			{
			
			PreparedStatement ps = c.prepareStatement("update account set balance = balance + ? where accno = ?");
			ps.setInt(1,balance);
			ps.setString(2, accno);
			//ResultSet rs = ps.executeQuery();
			//PrintWriter out = response.getWriter();
			ps.execute();
			}
			else
			{
				PreparedStatement ps = c.prepareStatement("update account set balance = balance - ? where accno = ?");
				ps.setInt(1,balance);
				ps.setString(2,accno);
				//ResultSet rs = ps.executeQuery();
				//PrintWriter out = response.getWriter();
				ps.execute();
			}
			
			System.out.println("Tranfer of money is done successfully:");
            //ps.execute();
		}
		catch(Exception e)
		{
			System.out.println("enter the correct credentials:");
		}
		
	}
}
public class Authenticate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int c1;
		int c2;
		User u = new User();
		Bank b = new Bank();
		do{
			System.out.println("--------------------------------");
			System.out.println("1.REGISTER");
			System.out.println("2.LOGIN");
			System.out.println("Enter your Choice: ");
			Scanner sc1=new Scanner(System.in);
			c1=sc1.nextInt();
			if(c1==1)
			{
				
				u.register();
			}
			if(c1==2)
			{
				u.login();
				do {
				System.out.println("1.Create account");
				System.out.println("2.Check Balance");
				System.out.println("3.Transfer Money");
				System.out.println("4.Exit");
				System.out.println("--------------------------------");
				System.out.println("Enter your Choice: ");
				Scanner sc2=new Scanner(System.in);
				c2=sc2.nextInt();
				
				switch(c2){
				case 1:b.createaccount();
					   break;	
				case 2:b.checkbalance();
					   break;
				case 3:b.Transfer();	
					   break;
				}
		}while(c2!=4);
		}	
		}while(c1!=3);
		

	}

}

