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

using AddressBookDemo;
using Gtk;

public class AddressBookDemoMain : GLib.Object {
	
	public static int main (string[] args) {
		Gtk.init(ref args);
		var window = new MainWindow(GetContactService());
		window.destroy.connect(Gtk.main_quit);
		window.show_all();
		Gtk.main();
		return 0;
	}
	
	public static ContactService GetContactService() {
		var contactService = new InMemoryContactService();
		contactService.SaveContact(createContact(1, "Test One", "12345", "test1@example.com"));
		contactService.SaveContact(createContact(2, "Test Two", "53421", "test2@example.com"));
		return contactService;
	}
	
	private static Contact createContact(int id, string name, string phone, string email) {
		Contact contact = new Contact();
		contact.Id = id;
		contact.Name = name;
		contact.Phone = phone;
		contact.Email = email;
		return contact;
	}

}
