package business;

import java.util.*;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class Main {

	public static void main(String[] args) {
		System.out.println(allWhoseZipContains3());
		System.out.println(allHavingOverdueBook());
		System.out.println(allHavingMultipleAuthors());

	}
	//Returns a list of all ids of LibraryMembers whose zipcode contains the digit 3
	public static List<String> allWhoseZipContains3() {
		DataAccess da = new DataAccessFacade();
		Collection<LibraryMember> members = da.readMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		mems.addAll(members);
		List<String>	ids = new ArrayList<>();
		for (LibraryMember m : mems){
			if (m != null && m.getAddress().getZip().contains("3"))
				ids.add(m.getMemberId());
		}
		return ids;
		
	}
	//Returns a list of all ids of  LibraryMembers that have an overdue book
	public static List<String> allHavingOverdueBook() {
		DataAccess da = new DataAccessFacade();
		Collection<LibraryMember> members = da.readMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		mems.addAll(members);
		List<String> ids = new ArrayList<>();
		for (LibraryMember m: mems){
			if (m != null && m.getRecord() != null){
				for (CheckoutRecordEntry entry: CheckoutRecord.entries)
					if (entry.isOverdue())
						ids.add(m.getMemberId());
			}
		}
		return ids;
		
	}
	
	//Returns a list of all isbns of  Books that have multiple authors
	public static List<String> allHavingMultipleAuthors() {
		DataAccess da = new DataAccessFacade();
		Collection<Book> books = da.readBooksMap().values();
		List<Book> bs = new ArrayList<>();
		bs.addAll(books);
		List<String>	isbns = new ArrayList<>();
		for (Book b: bs){
			if (b != null && b.getAuthors().size() > 1)
				isbns.add(b.getIsbn());
		}
		return isbns;
		
		}

}
