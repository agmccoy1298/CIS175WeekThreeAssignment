package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Contacts;



/**
 * @author andrewmccoy - agmccoy
 * CIS175 - Fall 2021
 * Jan 29, 2023
 */
public class ContactsHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ContactsList");
	
	public void inserItem(Contacts c) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Contacts> showAllContacts(){
		EntityManager em = emfactory.createEntityManager();
		List<Contacts> allContacts = em.createQuery("SELECT i FROM Contacts i").getResultList();
		return allContacts;		
	}
	
	public void deleteItem(Contacts toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Contacts> typedQuery = em.createQuery
			("select c from Contacts c where c.name = :selectedName ", 
					Contacts.class);
		
		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedName", toDelete.getName());		
		
		//we only want one result 
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		Contacts result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public List<Contacts> searchForContactsByName(String contactName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Contacts> typedQuery = em.createQuery
				("select c from Contacts c where c.name = :selectedName",
					Contacts.class);
		typedQuery.setParameter("selectedName", contactName);
		List<Contacts> foundContacts = typedQuery.getResultList();
		em.close();
		return foundContacts;
	}
	public List<Contacts> searchForContactsByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Contacts> typedQuery = em.createQuery
			("select c from Contacts c where c.phoneNumber = :selectedPhoneNumber",
				Contacts.class);
		typedQuery.setParameter("selectedPhoneNumber", phoneNumber);
		List<Contacts> foundContacts = typedQuery.getResultList();
		em.close();
		return foundContacts;
	}
	public Contacts searchForContactById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Contacts found = em.find(Contacts.class, idToEdit);
		em.close();
		return found;
	}
	
	public void updateContact(Contacts toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
