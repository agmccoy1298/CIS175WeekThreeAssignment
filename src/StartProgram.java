import java.util.List;
import java.util.Scanner;

import controller.ContactsHelper;
import model.Contacts;
/**
 * @author andrewmccoy - agmccoy
 * CIS175 - Fall 2021
 * Jan 29, 2023
 */



public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static ContactsHelper ch = new ContactsHelper();

		private static void addAContact() {
			// TODO Auto-generated method stub
			System.out.print("Enter contact name: ");
			String name = in.nextLine();
			System.out.print("Enter contact phone number: ");
			String phoneNumber = in.nextLine();
			System.out.print("Enter contact address: ");
			String address = in.nextLine();
			Contacts toAdd = new Contacts(name, phoneNumber, address);
			ch.inserItem(toAdd);
		}

		private static void deleteAContact() {
			// TODO Auto-generated method stub
			System.out.print("Enter the contact name to delete: ");
			String name = in.nextLine();			
			Contacts toDelete = new Contacts();
			toDelete.setName(name);
			ch.deleteItem(toDelete);

		}

		private static void editAContact() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Name");
			System.out.println("2 : Search by Phone Number");
			int searchBy = in.nextInt();
			in.nextLine();
			List<Contacts> foundContacts;
			if (searchBy == 1) {
				System.out.print("Enter the Contacts Name: ");
				String contactName = in.nextLine();
				foundContacts = ch.searchForContactsByName(contactName);
			} else {
				System.out.print("Enter the Contacts Phone Number: ");
				String contactPhoneNumber = in.nextLine();
				foundContacts = ch.searchForContactsByPhoneNumber(contactPhoneNumber);
			}

			if (!foundContacts.isEmpty()) {
				System.out.println("Found Results.");
				for (Contacts l : foundContacts) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				Contacts toEdit = ch.searchForContactById(idToEdit);
				System.out.println("Retrieved " + toEdit.getName()+ " with phone number " + toEdit.getPhoneNumber());
				System.out.println("1 : Update Name");
				System.out.println("2 : Update Phone Number");
				System.out.println("3 : Update Phone Number");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Name: ");
					String newName = in.nextLine();
					toEdit.setName(newName);
				} else if (update == 2) {
					System.out.print("New Phone Number: ");
					String newPhoneNumber = in.nextLine();
					toEdit.setPhoneNumber(newPhoneNumber);
				}else if (update == 3) {
					System.out.print("New Address: ");
					String newAddress = in.nextLine();
					toEdit.setAddress(newAddress);
				}
				

				ch.updateContact(toEdit);

			} else {
				System.out.println("No Contact found. Please try again (use option 4 to view all contacts)");
			}

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("Welcome to the contacts portal!");
			while (goAgain) {
				System.out.println("*  Select an action:");
				System.out.println("*  1 -- Add a contact");
				System.out.println("*  2 -- Edit a contact");
				System.out.println("*  3 -- Delete a contact");
				System.out.println("*  4 -- View the contact(s) contact");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAContact();
				} else if (selection == 2) {
					editAContact();
				} else if (selection == 3) {
					deleteAContact();
				} else if (selection == 4) {
					viewTheList();
				} else {
					ch.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<Contacts> allContacts =  ch.showAllContacts();
			for(Contacts singleContacts : allContacts) {
				System.out.println(singleContacts.returnContactInfo());
			}
		}

	}
