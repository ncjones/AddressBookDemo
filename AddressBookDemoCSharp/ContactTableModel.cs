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

namespace AddressBookDemoCSharp
{
	public class ContactTableModel : TableModel<Contact>
	{
		private ContactService contactService;
		
		public ContactTableModel(ContactService contactService)
		{
			this.contactService = contactService;
		}
		
		public System.Collections.Generic.List<Contact> GetRowData ()
		{
			return this.contactService.getAllContacts();
		}

		public System.Collections.Generic.List<TableColumn<Contact>> GetTableColumns ()
		{
			var columns = new List<TableColumn<Contact>>();
			columns.Add(new TableColumn<Contact>("Name", c => c.Name));
			columns.Add(new TableColumn<Contact>("Phone", c => c.Phone));
			columns.Add(new TableColumn<Contact>("Email", c => c.Email));
			return columns;
		}
	}
}

