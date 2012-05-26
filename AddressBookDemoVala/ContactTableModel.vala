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
	
	public class ContactTableModel : TableModel<Contact> {
		
		private ContactService contactService;
		
		private TableColumn<Contact>[] columns;
		
		public ContactTableModel(ContactService contactService) {
			this.contactService = contactService;
			this.columns = new TableColumn<Contact>[] {
				new ContactNameColumn(),
				new ContactPhoneColumn(),
				new ContactEmailColumn()
			};
		}
		
		public Contact[] get_row_data(){
			return this.contactService.GetAllContacts();
		}
		
		public TableColumn<Contact>[] get_columns(){
			return this.columns;
		}
	}
	
	private class ContactNameColumn : GLib.Object, TableColumn<Contact> {
		
		public string get_title() {
			return "Name";
		}
		
		public string get_value(Contact contact) {
			return contact.Name;
		}
		
	}
	
	private class ContactPhoneColumn : GLib.Object, TableColumn<Contact> {
		
		public string get_title() {
			return "Phone Number";
		}
		
		public string get_value(Contact contact) {
			return contact.Phone;
		}
		
	}
	
	private class ContactEmailColumn : GLib.Object, TableColumn<Contact> {
		
		public string get_title() {
			return "Email Address";
		}
		
		public string get_value(Contact contact) {
			return contact.Email;
		}
		
	}
}
