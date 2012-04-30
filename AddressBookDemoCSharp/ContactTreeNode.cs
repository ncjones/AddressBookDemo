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
	[Gtk.TreeNode (ListOnly=true)]
	public class ContactTreeNode : TreeNode
	{
		private Contact contact;
		
		public ContactTreeNode(Contact contact)
		{
			this.contact = contact;
		}
		
		public Contact Contact {
			get {
				return this.contact;
			}
			set {
				this.contact = value;
				this.OnChanged();
			}
		}
		
		[Gtk.TreeNodeValue (Column=0)]
        public string Name {get { return this.contact.Name; } }
		
		[Gtk.TreeNodeValue (Column=1)]
        public string Phone {get { return this.contact.Phone; } }
		
		[Gtk.TreeNodeValue (Column=2)]
        public string Email {get { return this.contact.Email; } }
	}
}

