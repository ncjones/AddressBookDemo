package com.ncjones.addrbook
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap

class InMemoryContactService extends ContactService {

  val contacts:Map[Int,Contact] = new HashMap();
  
  def getContacts(): Iterable[Contact] = {
    return contacts.values;
  }
  
  def saveContact(contact: Contact): Unit = {
    contacts.put(contact.id, contact)
  }

}