// 
//  Copyright Nathan Jones 2012
// 
//  This file is part of AddressBookDemo.
// 
//  AddressBookDemo is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
// 
//  AddressBookDemo is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
// 
//  You should have received a copy of the GNU General Public License
//  along with AddressBookDemo.  If not, see <http://www.gnu.org/licenses/>.
// 
using System;
using System.Collections.Generic;
using System.Threading;

namespace AddressBookDemoCSharp
{
	public class InMemoryContactService : AddressBookDemoCSharp.ContactService
	{
		
		private static int nextId = 0;
			
		private Dictionary<int, Contact> contacts;
			
		public InMemoryContactService ()
		{
			this.contacts = new Dictionary<int, Contact> ();
		}
		
		public System.Collections.Generic.List<Contact> getAllContacts ()
		{
			return new List<Contact> (this.contacts.Values);
		}

		public Contact saveContact (Contact contact)
		{
			contact.Id = this.getNextId();
			this.contacts[contact.Id] = contact;
			return contact;
		}

		public void updateContact (Contact contact)
		{
			this.contacts[contact.Id] = contact;
		}

		public void deleteContact (int contactId)
		{
			this.contacts.Remove (contactId);
		}
		
		private int getNextId() {
			return Interlocked.Increment(ref nextId);
		}
	}
}

