import java.util.*;
class P
{
	print(String s)
	{
		System.out.println(s);
	}
	print(int s)
	{
		System.out.println(s);
	}
	print(String s,int s1)
	{
		System.out.println(s+" "+s1);
	}
	print(int s1,String s)
	{
		System.out.println(s1+" "+s);
	}
	print(String s,String s1)
	{
		System.out.println(s+" "+s1);
	}
	print(String s,int s1,String s2)
	{
		System.out.println(s+" "+s1+" "+s2);
	}
}
class Book
{
	String title;
	String author;
	int book_id;
	Date y_o_r;
	ArrayDeque<String> pqueue;
	int n_copies;
	int c_inhand;
	
}
interface Login
{
	public Boolean  signin();
	public void resgister();
}
class UserDetails extends Book implements Login
{
	String name;
	int user_id;
	//book_in_hand class array
	Date cur_date;
	public Boolean signin()
	{
		s.nextLine();
		p.print("\nEnter your name");
		String sign_name=s.nextLine();
		p.print("\nWELCOME,",sign_name);
		s.nextLine();
		p.print("Enter username");
		String sign_uname=s.nextLine();
		p.print("\n");
		s.nextLine();
		p.print("Enter password");
		String sign_pwd=s.nextLine();
		if(user_username.equals(sign_uname) && user_pwd.equals(sign_pwd))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	}
	public void register()
	{
		s.nextLine();
		p.print("Enter your name");
		user_name=s.nextLine();
		p.print("\n");
		s.nextLine();
		p.print("Enter your user name");
		user_name=s.nextLine();
		user_username=s.nextLine();
		p.print("\n");
		s.nextLine();
		p.print("Enter your password");
		user_pwd=s.nextLine();
		p.print("You have registerred successfully");
		
	}

	void finedisplay()
	{
	}
	void renew(int user_id,int book_id)
	{
	}
	boolean search(String book_name)
	{
		return true;
	}
	void return_book(int book_id)
	{
	}
}
class Book_in_Hand
{
	String name_of_book;
	int book_id;
	Date issue_date;
	Date return_data;
	String[] reserved_books;
}
class Admin extends Book implements Login
{
	Book[] all_book_array=new Book[1000];
	//Book[] all_book_array=new Book[1000];
	LinkedList<Book> book_linked_list=new LinkedList<Book>();
	Scanner s=new Scanner(System.in);
	P p=new P();
	String admin_name;
	String admin_username;
	String admin_pwd;
	void addbook(String title,int book_id,String author,Date y_o_r,int n_copies,int copies_inhand)
	{
		Book book_obj=new Book(title,book_id,author,y_o_r,n_copies,copies_inhand);
		book_linked_list.add(book_obj);
	}
	void deletebook(int book_id) //check
	{
		int flag_check=0;
		for (Book temp : book_linked_list)
		{
                   if (temp.book_id=book_id)
                   {
						flag_check=1;
						book_linked_list.remove(temp);
						break;
					
                   }
        }
		if(flag_check==0)
		{
			p.print("Book-id Not Found\n");
		}
	}
	void view_details()
	{

	}
	void to_issue(int book_id)
	{
	}
	void finetable()
	{
	}
	void in_hand()
	{
	}
	void check_reservation(int book_id)
	{
	}
	void issued_books()
	{
	}
	void updatelist(int book_id)
	{
	}
	public Boolean signin()
	{
		s.nextLine();
		p.print("\nEnter your name");
		String sign_name=s.nextLine();
		p.print("\nWELCOME,",sign_name);
		s.nextLine();
		p.print("Enter username");
		String sign_uname=s.nextLine();
		p.print("\n");
		s.nextLine();
		p.print("Enter password");
		String sign_pwd=s.nextLine();
		if(admin_username.equals(sign_uname) && admin_pwd.equals(sign_pwd))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	public void register()
	{
		s.nextLine();
		p.print("Enter your name");
		admin_name=s.nextLine();
		p.print("\n");
		s.nextLine();
		p.print("Enter your user name");
		admin_name=s.nextLine();
		admin_username=s.nextLine();
		p.print("\n");
		s.nextLine();
		p.print("Enter your password");
		admin_pwd=s.nextLine();
		p.print("You have registerred successfully");
		
	}
}

class library
{
	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		P p=new P();
		p.print("WELCOME TO SSN LIBRARY MANAGEMENT");
		p.print("If you are an admin, select 1");
		p.print("If you are an user, select 2");
		int admin_user_select=s.nextInt();
		while((admin_user_select==1)||(admin_user_select==2))
		{
			if((admin_user_select==1))
			{
				p.print("Welcome to admin portal");
				Admin admin=new Admin();
				p.print("Enter 1 to register and 2 to signin");
				int reg_sign=s.nextInt();
				if(reg_sign==1)
				{
					admin.register();
					Boolean sign=admin.signin();
					if(sign)
					{
						p.print("SIGNED-IN SUCCESSFULLY");
					}
					else
					{
						while(sign==false)
						{
							p.print("\nLOGIN FAILED due to WRONG USERNAME OR WRONG PASSWORD");
							p.print("\nPLEASE TRY AGAIN");
							sign=admin.signin();
						}
						if(sign)
						{
							p.print("SIGNED-IN SUCCESSFULLY");
						}	
					}
				}
				if(reg_sign==2)
				{
					Boolean sign_dir=admin.signin();
					if(sign_dir)
					{
						p.print("SIGNED-IN SUCCESSFULLY");
					}
					else
					{
						while(sign_dir==false)
						{
							p.print("\nLOGIN FAILED due to WRONG USERNAME OR WRONG PASSWORD");
							p.print("\nPLEASE TRY AGAIN");
							sign_dir=admin.signin();
						}
						if(sign_dir)
						{
							p.print("SIGNED-IN SUCCESSFULLY");
						}	
					}
				}
				p.print("-------------------");
				p.print("Type 1 to add a new book in library");
				p.print("Type 2 to delete a  book record from the library");
				p.print("Type 3 to issue a  book to an user");
				p.print("Type 4 to display the finetable so far");
				p.print("Type 5 to display the list of books in library(IN HAND)");
				p.print("Type 6 to check reservation queue of a particular book");
				p.print("Type 7 to display the LIST OF ALL ISSUED BOOKS SO FAR");
				p.print("Type 8 to update the book details");
				p.print("Type 9 to sign out");
				int admin_choice=s.nextInt();
				while(admin_choice>=1 && admin_choice<=8)
				{
					if(admin_choice==1)
					{
						p.print("Enter Book Name");
						s.nextLine();
						String title=s.nextLine();

						p.print("Enter Book-id");
						int book_id=s.nextInt();

						p.print("Enter the author");
						s.nextLine();
						String author=s.nextLine();

						//Date format
						Date y_o_r;
						p.print("Enter number of copies");
						int n_copies=s.nextInt();

						p.print("Copies in hand?");
						int copies_inhand=s.nextInt();
						admin.addbook(title,book_id,author,y_o_r,n_copies,copies_inhand);
					}
					if(admin_choice==2)
					{
						p.print("Enter the Book-id");
						int book_id=s.nextInt();
						admin.deletebook(book_id);
					}
					if(admin_choice==3)
					{
						admin.view_details();
					}
					if(admin_choice==4)
					{
						admin.finetable();
					}
					if(admin_choice==5)
					{
						admin.in_hand();
					}
					if(admin_choice==6)
					{
						admin.check_reservation();
					}
					if(admin_choice==7)
					{
						admin.issued_books();
					}
					if(admin_choice==8)
					{
						admin.updatelist();
					}
					p.print("\n-------------------");
					p.print("Type 1 to add a new book in library");
					p.print("Type 2 to delete a  book record from the library");
					p.print("Type 3 to issue a  book to an user");
					p.print("Type 4 to display the finetable so far");
					p.print("Type 5 to display the list of books in library(IN HAND)");
					p.print("Type 6 to check reservation queue of a particular book");
					p.print("Type 7 to display the LIST OF ALL ISSUED BOOKS SO FAR");
					p.print("Type 8 to update the book details");
					p.print("Type 9 to sign out");
					admin_choice=s.nextInt();
				}
			}
			if(admin_user_select==2)
			{
				//type user login and sigin and implement user class functions
			}
		}
	}
}