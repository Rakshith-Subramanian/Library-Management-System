import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
class P
{
	void print(String s)
	{
		System.out.println(s);
	}
	void print(int s)
	{
		System.out.println(s);
	}
	void print(String s,int s1)
	{
		System.out.println(s+" "+s1);
	}
	void print(int s1,String s)
	{
		System.out.println(s1+" "+s);
	}
	void print(String s,String s1)
	{
		System.out.println(s+" "+s1);
	}
	void print(String s,int s1,String s2)
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
    ArrayDeque<Integer> pqueue;
    int n_copies;
    int c_inhand;
    Book(String title,String author,int book_id,Date y_o_r,ArrayDeque<Integer> pqueue,int n_copies,int c_inhand)
    {
        this.title=title;
        this.author=author;
        this.book_id=book_id;
        this.y_o_r=y_o_r;
        this.pqueue=pqueue;
        this.n_copies=n_copies;
        this.c_inhand=c_inhand;
    }
    
}
class Book_in_Hand 
{
    String name_of_book;
    int book_id;
    Date issue_date;
    Date return_date;
    String[] reserved_books;
    Integer user_id;
}

class UserDetails 
{
    String name;
    Integer user_id;
    //book_in_hand class array
    static int LOAN_PERIOD = 14;
    Date cur_date=new Date();
    int max_books_checked_out=5;
    Book_in_Hand[] all_book_in_hand=new Book_in_Hand[5];
    int all_fines=0;
    Admin admin = new Admin();
    //Book[] all_books = admin.all_book_array;
    String user_username;
    String user_pwd;
    int fines;
    Scanner s=new Scanner(System.in);
    P p=new P();
    static int FINE_PER_DAY=10;
    int RENEWAL_PERIOD=14;
    public Boolean signin()
    {
        
        p.print("\nEnter your name");
        s.nextLine();
        String sign_name=s.nextLine();
        p.print("\nWELCOME,",sign_name);
        
        p.print("Enter username");
        s.nextLine();
        String sign_uname=s.nextLine();
        p.print("\n");
        
        p.print("Enter password");
        s.nextLine();
        String sign_pwd=s.nextLine();
        if(sign_uname.equals(user_username) && sign_pwd.equals(user_pwd))
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
        
        p.print("Enter your name");
        s.nextLine();
        name=s.nextLine();
        p.print("\n");
        
        p.print("Enter your user name");
        s.nextLine();
        //String user_name=s.nextLine();
        user_username=s.nextLine();
        p.print("\n");
       
        p.print("Enter your password");
        s.nextLine();
        user_pwd=s.nextLine();
        p.print("Enter your User-Id");
        user_id=s.nextInt();
        int i=0;
        p.print("You have registerred successfully");
    }
    static UserDetails findUserById(Integer userId)
    {
        for (UserDetails user : Admin.all_user_Details)
        {
            if (user.user_id == userId)
            {
                return user;
            }
        }
        return null;
    }

    void finedisplay()
    {
        // code to calculate and display fines for a user
        // iterate over the book_in_hand array and calculate fines for each book
        for (Book_in_Hand book : this.all_book_in_hand)
        {
            if (book.return_date.before(cur_date))
            {
                // book is overdue
                int days_overdue = (int)((cur_date.getTime() - book.return_date.getTime()) / (1000*60*60*24));
                int fine = days_overdue * FINE_PER_DAY;
                all_fines += fine;
                p.print("Book " + book.name_of_book + " is overdue by " + days_overdue + " days. Fine: " + fine);
            }
            p.print("You dont have any fines for this book "+book.book_id);
        }
        //p.print("Total fines: " + all_fines);
    }


    void renew(Integer user_id, int book_id)
    {
        // code to renew a book borrowed by a user
        // find the book in the book_in_hand array
        for(UserDetails user: Admin.all_user_Details)
        {
            if(user!=null)
            {
                for (Book_in_Hand book : user.all_book_in_hand)
                {
                    if(book!=null)
                    {
                        if(user.user_id==user_id)
                        {    
                            if (book.book_id == book_id)
                            {
                                // book found, check if it can be renewed
                                if (book.return_date.before(cur_date))
                                {
                                    // book is overdue, cannot be renewed
                                    p.print("Book cannot be renewed as it is overdue");
                                }
                                else
                                {
                                    // renew the book
                                    book.return_date = new Date(book.return_date.getTime() + RENEWAL_PERIOD);
                                    p.print("Book renewed successfully. New return date: " + book.return_date);
                                }
                                return;
                            }
                        }
                    }
                }
                // book not found in book_in_hand array
                p.print("Book not found");
            }
        }
    }


    public void search(String book_name) 
    {
        // iterate over the all_book_array
        for (Book book : Admin.all_book_array) 
        {
            // check if the book name matches the given book name
            if (book.title.equalsIgnoreCase(book_name)) 
            {
                // if the book is found, print the details and return true
                System.out.println("Book found: ");
                System.out.println("Title: " + book.title);
                System.out.println("Author: " + book.author);
                System.out.println("Book ID: " + book.book_id);
                System.out.println("Year of Release: " + book.y_o_r);
                System.out.println("Number of Copies: " + book.n_copies);
                System.out.println("Copies in Hand: " + book.c_inhand);
                return;
            }
        }
        // if the book is not found, print a message and return false
        System.out.println("Book not found.");
        //return false;
    }
    public void requestBook(int book_id) 
    {
        Admin admin=new Admin();
        admin.issueBook(user_id,book_id);
    }
    

    void return_book(int book_id)
    {
        
        admin.return_book(this.user_id,book_id);
    }
    void updateUser(Integer user_id,UserDetails user)
    {
        int i=0;
        for(UserDetails user1: Admin.all_user_Details)
        {
            if(user1.user_id==user_id)
            {
                user1=user;
            } 
        }
    }
}
class Admin 
{
	static Book[] all_book_array=new Book[1000];
    static UserDetails[] all_user_Details=new UserDetails[1000];
    Date cur_date=new Date();
    static Book_in_Hand[] all_book_in_hand; 
	LinkedList<Book> book_linked_list=new LinkedList<Book>();
	Scanner s=new Scanner(System.in);
	P p=new P();
	String admin_name;
	String admin_username;
	String admin_pwd;
	
	void addbook(String title,int book_id,String author,Date y_o_r,int n_copies,int copies_inhand) 
    {
        ArrayDeque<Integer> pqueue=new ArrayDeque<Integer>();
		Book book_obj=new Book(title,author,book_id,y_o_r,pqueue,n_copies,copies_inhand);
		book_linked_list.add(book_obj);
        for(int i=0;i<100;i++)
        {
            if(Admin.all_book_array[i]==null)
            {
                Admin.all_book_array[i]=book_obj;
                return;
            }
        }
        
	}
	
	void deletebook(int book_id) 
    {
		Book book = findBookById(book_id);
		if (book == null) 
        {
			p.print("Error: Invalid book ID.");
			return;
		}
		/* 
		// Get book back from users who have it checked out
		for (UserDetails user: all_user_Details) 
        {
            for(Book_in_Hand bookInHand: user.all_book_in_hand)
            {
                if (bookInHand.book_id == book_id) 
                {
                    return_book(user.user_id, book_id);
                }
            }
		}
		*/
		// Remove book from linked list
		book_linked_list.remove(book);
		
		p.print("Book successfully deleted.");
	}
	
	Book findBookById(int bookId) 
    {
		for (Book book : all_book_array) 
        {
			if (book.book_id == bookId) 
            {
				return book;
			}
		}
		return null;
	}
	
	void issueBook(Integer userId, int bookId) 
    {
		UserDetails user = UserDetails.findUserById(userId);
		if (user == null) {
			p.print("Error: Invalid user ID.");
			return;
		}
		
		Book book = findBookById(bookId);
	
        if (book == null) 
        {
            p.print("Error: Invalid book ID.");
            return;
        }
        
        if (book.c_inhand == 0) 
        {
            // Add user to reservation queue for this book
            book.pqueue.add(userId);
            p.print("No copies of this book are currently available. You have been added to the reservation queue.");
            return;
        }
        
        // Check if user has reached maximum number of books checked out
        
        
        // Check out book to user
        Book_in_Hand bookInHand;
        bookInHand=new Book_in_Hand();
        bookInHand.name_of_book = book.title;
        bookInHand.book_id = bookId;
        bookInHand.issue_date = user.cur_date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cur_date);
        calendar.add(Calendar.DATE, UserDetails.LOAN_PERIOD);
        bookInHand.return_date = calendar.getTime();
        //bookInHand.user_id = userId;
        for(int i=0;i<5;i++)
        {
            if(user.all_book_in_hand[i]==null)
            {
                user.all_book_in_hand[i]=new Book_in_Hand();
                user.all_book_in_hand[i]=bookInHand;
                break;
            }
        }
        book.c_inhand--;
        
        p.print("Book successfully checked out.");
    }
    
    void return_book(Integer userId,int bookId) 
    {
        UserDetails user = UserDetails.findUserById(userId);
        if (user == null) 
        {
            p.print("Error: Invalid user ID.");
            return;
        }
        
        Book book = findBookById(bookId);
        if (book == null) 
        {
            p.print("Error: Invalid book ID.");
            return;
        }
        
        // Check if book is checked out to this user
        Book_in_Hand bookInHand = null;
        for (Book_in_Hand b : user.all_book_in_hand) 
        {
            //p.print("iterating");
            if(b!=null)
            {
                if (b.book_id == bookId ) 
                {
                    bookInHand = b;
                    //p.print("breaking");
                    break;
                }
                else
                {
                    continue;
                }
                
            }
            else
            {
                //p.print("returning");
                return;
            }
        }
        if (bookInHand == null) 
        {
            p.print("Error: You do not have this book checked out.");
            return;
        }
        //p.print("before finr calc");
        // Calculate fine if book is overdue
        long millis = cur_date.getTime() - bookInHand.return_date.getTime();
        int numDaysOverdue = (int) (millis / (1000 * 60 * 60 * 24));
        if (numDaysOverdue > 0) 
        {
            user.fines += numDaysOverdue * UserDetails.FINE_PER_DAY;
        }
        //p.print("before iterating");
        // Remove book from user's checked out list and add it back to library
        int iter=0;
        for(int i=0;i<user.all_book_in_hand.length;i++)
        {
            if(user.all_book_in_hand[i].book_id==bookId)
            {
                iter=i;
                //p.print("breaking-2");
                break;
            }
        }
        //p.print("starting");
        for(int i=iter;i<user.all_book_in_hand.length-1;i++)
        {
            //p.print("entered");
            user.all_book_in_hand[i]=user.all_book_in_hand[i+1];
        }
        user.all_book_in_hand[user.all_book_in_hand.length-1]=null;
        book.c_inhand++;
        // Check if there are any users in the reservation queue for this book
        if (book.pqueue.size() > 0) 
        {
            int nextUserId = book.pqueue.poll();
            issueBook(nextUserId, bookId);
        }
        
        p.print("Book successfully returned.");
    }
    
    void fineTable() 
    {
        for (UserDetails user : Admin.all_user_Details) 
        {
            if (user == null) {
                continue;
            }
            
            user.finedisplay();
        }
    }
    void inHand() 
    {
        for (Book book : Admin.all_book_array) 
        {
            if (book == null) 
            {
                continue;
            }
            
            System.out.println(book.title+ ": "+ (book.n_copies - book.c_inhand)+" copies checked out, "+book.c_inhand+ " copies available.");
        }
    }

    void checkReservation(int bookId) 
    {
        Book book = findBookById(bookId);
        if (book == null) 
        {
            p.print("Error: Invalid book ID.");
            return;
        }
        
        p.print("Reservation queue for " + book.title + ":");
        for (Integer userId : book.pqueue) 
        {
            UserDetails user = UserDetails.findUserById(userId);
            p.print(user.name);
        }
    }

    void updateList(int bookId, String title, String author, int nCopies, int copiesInHand) 
    {
        Book book = findBookById(bookId);
        if (book == null) 
        {
            p.print("Error: Invalid book ID.");
            return;
        }
        
        // Return book to library and remove it from users' checked out lists
        for(UserDetails user: all_user_Details)
        {
            for(Book_in_Hand bookInHand: user.all_book_in_hand)
            {
                if(bookInHand.book_id==bookId)
                {
                    return_book(user.user_id, bookId);
                }
            }
        }
        
        
        // Update book details
        book.title = title;
        book.author = author;
        book.n_copies = nCopies;
        book.c_inhand = copiesInHand;
        book.pqueue.clear();
        
        p.print("Book details updated successfully.");
    }

    public Boolean signin() 
    {
        
        p.print("Enter username: ");
        s.nextLine();
        String username = s.nextLine();
        p.print("Enter password: ");
        String password = s.nextLine();
        
        if (username.equals(admin_username) && password.equals(admin_pwd)) 
        {
            return true;
        }
        
        return true;
    }

    public void register() 
    {
        p.print("\n");
        p.print("Enter name: ");
        admin_name = s.nextLine();

        s.nextLine();
        p.print("Enter username: ");
        s.nextLine();
        admin_username = s.nextLine();

        p.print("Enter password: ");
        s.nextLine();
        admin_pwd = s.nextLine();
        p.print("Admin registered successfully.");
    }
    void issued_books() 
    {
        p.print("Issued Books: ");
        for(UserDetails user: all_user_Details)
        {
            for(Book_in_Hand bookInHand: user.all_book_in_hand)
            {
                if(bookInHand!=null)
                {
                    p.print("Book Name: " + bookInHand.name_of_book);
                    p.print("Book ID: " + bookInHand.book_id);
                    p.print("Issue Date: " + bookInHand.issue_date);
                    p.print("Return Date: " + bookInHand.return_date);
                    
                    long daysOverdue = (new Date().getTime() - bookInHand.return_date.getTime()) / (24 * 60 * 60 * 1000);
                    
                    if (daysOverdue > 0) 
                    {
                        p.print("Fine: " + daysOverdue * 10);
                    } 
                    else 
                    {
                        p.print("No fine.");
                    }
                }
                else
                {
                    return;
                }
            }
        }
    }
    
    void view_details(Integer user_id) 
    {
        UserDetails user = UserDetails.findUserById(user_id);
        
        if (user == null) 
        {
            p.print("User not found.");
            return;
        }
        
        p.print("User Name: " + user.name);
        p.print("User ID: " + user.user_id);
        p.print("Books borrowed: ");
        
        for (Book_in_Hand bookInHand: user.all_book_in_hand) 
        {
            if(bookInHand!=null)
            {
                p.print("Book Name: " + bookInHand.name_of_book);
                p.print("Book ID: " + bookInHand.book_id);
                p.print("Borrow Date: " + bookInHand.issue_date);
                p.print("Return Date: " + bookInHand.return_date);
                
                long daysOverdue = (new Date().getTime() - bookInHand.return_date.getTime()) / (24 * 60 * 60 * 1000);
                
                if (daysOverdue > 0) 
                {
                    p.print("Fine: " + daysOverdue * 10);
                } 
                else 
                {
                    p.print("No fine.");
                }
            }
        }
    }
    
}

class LibraryManagementSystem
{
	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		P p=new P();
		UserDetails user_obj=new UserDetails();
		Admin admin=new Admin();
		Date d=new Date();
        System.out.println(d);
        for(int i=0;i<1000;i++)
        {
            Admin.all_user_Details[i]=null;
        }
        p.print("Hello Admin,please Register");
        admin.register();
        int admin_choice=0;
        p.print("Enter 1 to login as an admin");
        p.print("Enter 2 to login as as User");
        int choice=s.nextInt();
        while(choice==1 || choice==2)
        {
            if(choice==1)
            {
                
                Boolean signcheck=false;
                while(!signcheck)
                {
                    signcheck=admin.signin();
                    if(signcheck==false)
                    {
                        p.print("Wrong username or Pwd singnin again");
                    }
                }
                p.print("Hello Admin");
                p.print("Here are the choices available to you");
                p.print("Press 1 to add a new Book");
                p.print("Press 2 to delete a book");
                p.print("Press 3 to view details of all the books");
                p.print("Press 4 to Details of book issued");
                p.print("Press 5 to display the fine-Table");
                p.print("Press 6 to check Reservation table of a book");
                p.print("Press 7 to issue book to a user");
                p.print("Press 8 to get a book return");
                p.print("Press 9 to update a particular Book");
                admin_choice=s.nextInt();
                while(admin_choice>=1 && admin_choice<=9)
                {
                    if(admin_choice==1)
                    {
                        String title;
                        int book_id;
                        String author;
                        Date y_o_r=new Date();
                        int n_copies;
                        int copies_inhand;
                        p.print("Enter Book-Title");
                        s.nextLine();
                        title=s.nextLine();
                        p.print("Enter Book-Id");
                        book_id=s.nextInt();
                        p.print("Enter author");
                        s.nextLine();
                        author=s.nextLine();
                       /*  System.out.print("Enter a date in the format yyyy-MM-dd: ");
                        s.nextLine();
                        String dateInput = s.nextLine();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try
                        {
                            y_o_r = dateFormat.parse(dateInput);
                        }
                        catch(ParseException e)
                        {
                            System.out.println("INVALID DATE FORMAT");
                        }*/
                        p.print("Enter number of copies");
                        n_copies=s.nextInt();
                        copies_inhand=n_copies;
                        admin.addbook(title,book_id,author,y_o_r,n_copies,copies_inhand);
                        
                    }
                    if(admin_choice==2)
                    {
                        p.print("Enter Book-id");
                        int bookid=s.nextInt();
                        admin.deletebook(bookid);

                    }

                    if(admin_choice==3)
                    {
                        admin.inHand();
                    }
                    if(admin_choice==4)
                    {
                        admin.issued_books();
                    }
                    if(admin_choice==5)
                    {
                        admin.fineTable();
                    }
                    if(admin_choice==6)
                    {
                        p.print("Enter book_id for which reservation status has to be checked");
                        int b_id=s.nextInt();
                        admin.checkReservation(b_id);
                    }
                    if(admin_choice==7)
                    {
                        p.print("Enter the User-id");
                        int uid=s.nextInt();
                        p.print("Enter book-id");
                        int bok_id=s.nextInt();
                        admin.issueBook(uid,bok_id);
                    }
                    if(admin_choice==8)
                    {
                        p.print("Enter the User-id");
                        int uid1=s.nextInt();
                        p.print("Enter book-id to be returned");
                        int bok_id1=s.nextInt();
                        admin.return_book(uid1,bok_id1);
                    }
                    if(admin_choice==9)
                    {
                        p.print("To update the details of a particular book-id  ----->please enter the following.NOTE:BOOK-ID WILL NOT BE CHANGED!");
                        p.print("Enter Book-id");
                        int book_id=s.nextInt();
                        p.print("Enter new Title");
                        s.nextLine();
                        String title=s.nextLine();
                        p.print("Enter new author");
                        s.nextLine();
                        String author=s.nextLine();
                        p.print("Enter number of copies");
                        int copies=s.nextInt();
                        int c_inhand=copies;
                        admin.updateList(book_id,title,author,copies,c_inhand);
                    }
                    p.print("-------------------------------------------");
                    p.print("To continue as admin--->Here are the choices available to you");
                    p.print("Press 1 to add a new Book");
                    p.print("Press 2 to delete a book");
                    p.print("Press 3 to view details of all the books");
                    p.print("Press 4 to Details of book issued");
                    p.print("Press 5 to display the fine-Table");
                    p.print("Press 6 to check Reservation table of a book");
                    p.print("Press 7 to issue book to a user");
                    p.print("Press 8 to get a book return");
                    p.print("Press 9 to update a particular Book");
                    p.print("Press any other to signout");
                    admin_choice=s.nextInt();
                }

            }
            if(choice==2)
            {
                p.print("You have selected USER-MODE");
                p.print("IF you are new user, press 1, else 0");
                int iterator=0;
                int u_sign_choice=s.nextInt();
                if(u_sign_choice==1)
                {
                    p.print("Kindly Register now");
                    
                    for(int i=0;i<Admin.all_user_Details.length-1;i++)
                    {
                        //p.print("Inside iterator");
                        if(Admin.all_user_Details[i]==null)
                        {
                            //p.print("inside if loop");
                            
                            iterator=i;
                            break;
                        }
                        else
                        {
                            continue;
                        }
                    }
                    //p.print("outside");
                    Admin.all_user_Details[iterator]=new UserDetails();
                    UserDetails user=Admin.all_user_Details[iterator];
                    //p.print("just before register ");
                    user.register();
                    for(int i=0;i<5;i++)
                    {
                        user.all_book_in_hand[i]=null;
                    }
                    Admin.all_user_Details[iterator]=user;
                    
                }
                

                p.print("Now please sigin");
                p.print("Enter your User id");
                Integer u_id=s.nextInt();
                UserDetails user=UserDetails.findUserById(u_id);
                Boolean u_sign_check=false;
                while(!u_sign_check)
                {
                    u_sign_check=user.signin();
                }
                Admin.all_user_Details[iterator]=user;
                p.print("Hello User "+user.name+" "+user.user_username);
                p.print("Press 1 to Search a book");
                p.print("Press 2 to Request a book");
                p.print("Press 3 to Renew a book");
                p.print("Press 4 to Display your fine");
                p.print("Press 5 to return a Book");
                p.print("Press any other number to signout");
                int user_choice=s.nextInt();
                while(user_choice>=1 && user_choice<=5)
                {
                    if(user_choice==1)
                    {
                        
                        p.print("Enter Book Name");
                        s.nextLine();
                        String b_name=s.nextLine();
                        user.search(b_name);
                    }
                    if(user_choice==2)
                    {
                        p.print("Enter Book-id to Request");
                        int uid=s.nextInt();
                        user.requestBook(uid);
                    }
                    if(user_choice==3)
                    {
                        p.print("Enter Book-id to renew");
                        int bid1=s.nextInt();
                        user.renew(user.user_id,bid1);
                    }
                    if(user_choice==4)
                    {
                        user.finedisplay();
                    }
                    if(user_choice==5)
                    {
                        //p.print("Enter Book-id to return");
                        //int bid2=s.nextInt();
                        //user.return_book(bid2);
                        p.print("PLEASE CALL THE ADMIN TO RETURN A BOOK");
                    }
                    for(UserDetails usertemp: Admin.all_user_Details)
                    {
                        if(usertemp.user_id==user.user_id)
                        {
                            p.print("updated");
                            usertemp=user;
                            break;
                        }
                    }
                    p.print("-------------------------------------");
                    p.print("Press 1 to Search a book");
                    p.print("Press 2 to Request a book");
                    p.print("Press 3 to Renew a book");
                    p.print("Press 4 to Display your fine");
                    p.print("Press 5 to return a Book");
                    p.print("Press any other number to signout");
                    user_choice=s.nextInt();
                }
            }
            p.print("------------------------------------------------");
            p.print("Enter 1 to login as an admin");
            p.print("Enter 2 to login as as User");
            p.print("Press any other number to Exit program");
            choice=s.nextInt();
        }
		
    }
}

