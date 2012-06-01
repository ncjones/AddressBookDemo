package com.ncjones.addrbook

object AddressBookApp extends App {

  val contactService: ContactService = new InMemoryContactService()
  contactService.saveContact(new Contact(0, "Test One", "143323", "test1@example.com"))
  contactService.saveContact(new Contact(1, "Test Two", "621234", "test2@example.com"))
  val contacts = contactService.getContacts()
  contacts.foreach(c => System.out.println(c.name))
	  
}