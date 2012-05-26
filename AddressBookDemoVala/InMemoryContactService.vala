// 
// Copyright Nathan Jones 2012
// 
// This file is part of AddressBookDemo.
// 
// AddressBookDemo is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// AddressBookDemo is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with AddressBookDemo.  If not, see <http://www.gnu.org/licenses/>.
// 

namespace AddressBookDemo {
	
	public class InMemoryContactService : Object, ContactService {
		
		public Contact[] GetAllContacts() {
			Contact contact1 = new Contact();
			contact1.Name = "Test One";
			contact1.Phone = "12345";
			contact1.Email = "test1@example.com";
			Contact contact2 = new Contact();
			contact2.Name = "Test Two";
			contact2.Phone = "53421";
			contact2.Email = "test2@example.com";
			Contact[] contacts = new Contact[]{
				contact1,
				contact2
			};
			return contacts;
		}
	}
}
