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
using Gtk;

namespace AddressBookDemoCSharp
{
	class MainClass
	{
		
		public static void Main(string[] args)
		{
			var contactService = new InMemoryContactService();
			contactService.saveContact(CreateContact("Test One", "1234567", "test1@example.com"));
			contactService.saveContact(CreateContact("Test Two", "7654321", "test2@example.com"));
			Application.Init();
			MainWindow win = new MainWindow(contactService);
			win.Show();
			Application.Run();
		}
		
		static Contact CreateContact(string name, string phone, string email)
		{
			var contact = new Contact();
			contact.Name = name;
			contact.Phone = phone;
			contact.Email = email;
			return contact;
		}
		
	}
}
