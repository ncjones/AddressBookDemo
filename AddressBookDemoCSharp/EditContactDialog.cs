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
		ContactEditor contactEditor;

		public EditContactDialog(Contact contact)
		{
			this.Title = "Edit Contact";
			this.contactEditor = new ContactEditor(contact);
			this.InitDialogUI();
		}
		
		public Contact GetContact() {
			return this.contactEditor.GetContact();
		}

		void InitDialogUI()
		{
			this.VBox.PackStart(this.contactEditor, false, false, 0);
			this.ShowAll();
			this.AddButton(Stock.Ok, ResponseType.Ok);
			this.AddButton(Stock.Cancel, ResponseType.Cancel);
		}
	}

}