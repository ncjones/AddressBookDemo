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
	public class ContactEditor : Gtk.Bin
	{
		Contact contact;
		Entry nameEntry = new Entry();
		Entry phoneEntry = new Entry();
		Entry emailEntry = new Entry();
		
		public ContactEditor(Contact contact)
		{
			this.contact = contact;
			this.InitUi();
		}
		
		private void InitUi()
		{
			var table = new Table(3, 2, false);
			table.RowSpacing = 4;
			table.ColumnSpacing = 4;
			var nameLabel = new Label("_Name");
			table.Attach(nameLabel, 0, 1, 0, 1);
			table.Attach(this.nameEntry, 1, 2, 0, 1);
			this.nameEntry.Text = this.contact.Name;
			nameLabel.MnemonicWidget = this.nameEntry;
			var phoneLabel = new Label("_Phone");
			table.Attach(phoneLabel, 0, 1, 1, 2);
			table.Attach(this.phoneEntry, 1, 2, 1, 2);
			this.phoneEntry.Text = this.contact.Phone;
			phoneLabel.MnemonicWidget = this.phoneEntry;
			var emailLabel = new Label("_Email");
			table.Attach(emailLabel, 0, 1, 2, 3);
			table.Attach(this.emailEntry, 1, 2, 2, 3);
			this.emailEntry.Text = this.contact.Email;
			emailLabel.MnemonicWidget = this.emailEntry;
			this.Add(table);
		}
		
		protected override void OnSizeAllocated(Gdk.Rectangle allocation)
		{
			if (this.Child != null) {
				this.Child.Allocation = allocation;
			}
		}

		protected override void OnSizeRequested(ref Requisition requisition)
		{
			if (this.Child != null) {
				requisition = this.Child.SizeRequest();
			}
		}
		
		public Contact GetContact()
		{
			var contact = new Contact();
			contact.Name = this.nameEntry.Text;
			contact.Phone = this.phoneEntry.Text;
			contact.Email = this.emailEntry.Text;
			return contact;
		}
	}
}

