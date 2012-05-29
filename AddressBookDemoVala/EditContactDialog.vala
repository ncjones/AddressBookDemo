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

using Gtk;

namespace AddressBookDemo {
	
	public class EditContactDialog : Gtk.Dialog {
		
		private int contactId;
		
		private Entry nameEntry;
		
		private Entry phoneEntry;
		
		private Entry emailEntry;
		
		public EditContactDialog(Contact contact) {
			this.title = "Edit Contact";
			this.contactId = contact.Id;
			this.nameEntry = new Entry();
			this.phoneEntry = new Entry();
			this.emailEntry = new Entry();
			this.vbox.pack_start(createField(this.nameEntry, "Name", contact.Name), false, true, 0);
			this.vbox.pack_start(createField(this.phoneEntry, "Phone", contact.Phone), false, true, 0);
			this.vbox.pack_start(createField(this.emailEntry, "Email", contact.Email), false, true, 0);
			this.add_button(Stock.OK, ResponseType.OK);
			this.show_all();
		}
		
		private HBox createField(Entry entry, string fieldName, string fieldValue) {
			var hbox = new HBox(false, 20);
			entry.text = fieldValue;
			hbox.pack_start(new Label(fieldName), false, true, 0);
			hbox.pack_start(entry, false, true, 0);
			return hbox;
		}
		
		public Contact getContact() {
			var contact = new Contact();
			contact.Id = this.contactId;
			contact.Name = this.nameEntry.text;
			contact.Phone = this.phoneEntry.get_text();
			contact.Email = this.emailEntry.get_text();
			return contact;
		}
	}
}