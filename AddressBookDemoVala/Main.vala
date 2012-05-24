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
		var window = new Gtk.Window();
		window.title = "Address Book";
		var listModel = new ListStore(2, typeof(string), typeof(string));
		ContactService contactService = new InMemoryContactService();
		TreeIter iter;
		foreach (var contact in contactService.GetAllContacts()) {
			listModel.append(out iter);
			listModel.set(iter, 0, contact.Name, 1, contact.Phone);
		}
		var treeView = new TreeView();
		treeView.set_model(listModel);
		treeView.insert_column_with_attributes(-1, "Name", new CellRendererText(), "text", 0);
		treeView.insert_column_with_attributes(-1, "Phone", new CellRendererText(), "text", 1);
		window.add(treeView);
		
		
		window.show_all();
		Gtk.main();
		return 0;
	}

}
