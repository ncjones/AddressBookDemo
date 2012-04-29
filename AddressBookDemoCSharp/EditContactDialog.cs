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
using Gtk;

namespace AddressBookDemoCSharp
{
	class EditContactDialog : Dialog
	{
		Entry nameEntry = new Entry();
		Entry phoneEntry = new Entry();
		Entry emailEntry = new Entry();

		public EditContactDialog()
		{
			this.Title = "Edit Contact";
			this.InitDialogUI();
		}
		
		public Contact GetContact() { 
			var contact = new Contact();
			contact.Name = this.nameEntry.Text;
			contact.Phone = this.phoneEntry.Text;
			contact.Email = this.emailEntry.Text;
			return contact;
		}

		void InitDialogUI()
		{
			var table = new Table(3, 2, false);
			table.RowSpacing = 4;
			table.ColumnSpacing = 4;
			var nameLabel = new Label("_Name");
			table.Attach(nameLabel, 0, 1, 0, 1);
			table.Attach(this.nameEntry, 1, 2, 0, 1);
			nameLabel.MnemonicWidget = this.nameEntry;
			var phoneLabel = new Label("_Phone");
			table.Attach(phoneLabel, 0, 1, 1, 2);
			table.Attach(this.phoneEntry, 1, 2, 1, 2);
			phoneLabel.MnemonicWidget = this.phoneEntry;
			var emailLabel = new Label("_Email");
			table.Attach(emailLabel, 0, 1, 2, 3);
			table.Attach(this.emailEntry, 1, 2, 2, 3);
			emailLabel.MnemonicWidget = this.emailEntry;
			this.VBox.PackStart(table, false, false, 0);
			table.ShowAll();
			this.AddButton(Stock.Ok, ResponseType.Ok);
			this.AddButton(Stock.Cancel, ResponseType.Cancel);
		}
	}

}