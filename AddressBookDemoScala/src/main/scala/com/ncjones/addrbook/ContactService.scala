package com.ncjones.addrbook

trait ContactService {
  
  def getContacts() : Iterable[Contact]; 

  def saveContact(contact:Contact) : Unit;
}