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
	
	public class MainWindow : Window {
		
		private ContactService contactService;
		
		private TableView<Contact> contactsTable;
		
		private ContactTableModel contactTableModel;
		
		public MainWindow(ContactService contactService) {
			this.contactService = contactService;
			this.title = "Address Book";
			this.contactTableModel = new ContactTableModel(contactService);
			this.contactsTable = new TableView<Contact>(this.contactTableModel);
			var vbox = new VBox(false, 0);
			vbox.add(contactsTable);
			vbox.add(createButtonBox());
			this.add(vbox);
			contactsTable.row_activated.connect(this.ShowEditContactDialog);
		}
		
		private ButtonBox createButtonBox() {
			ButtonBox buttonBox = new HButtonBox();
			buttonBox.set_layout(ButtonBoxStyle.END);
			var editButton = new Button.with_label("Edit Contact");
			editButton.clicked.connect(this.ShowEditContactDialog);
			buttonBox.pack_end(editButton);
			return buttonBox;
		}
	
		private Contact getSelectedContact() {
			return this.contactsTable.getSelectedObject();
		}

		void ShowEditContactDialog() {
			var dialog = new EditContactDialog(this.getSelectedContact());
			dialog.response.connect((responseId) => {
				var contact = dialog.getContact();
				dialog.destroy();
				if (responseId == ResponseType.OK) {
					this.contactsTable.replaceSelectedObject(contact);
					this.contactService.SaveContact(contact);
				}
			});
			dialog.show();
		}
	}
}