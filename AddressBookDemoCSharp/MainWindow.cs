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
using AddressBookDemoCSharp;
using System.Collections.Generic;

public partial class MainWindow: Gtk.Window
{	 

	public MainWindow (ContactService contactService): base (Gtk.WindowType.Toplevel)
	{
		this.SetSizeRequest(400, 300);
		Gtk.TreeView tree = new TableView<Contact>(new ContactTableModel(contactService));
		var vbox = new VBox();
		vbox.PackStart(tree, true, true, 0);
		vbox.PackStart(createActionButtonsContainer(), false, false, 5);
		this.Add(vbox);
		this.ShowAll();
		this.Name = "MainWindow";
		this.DeleteEvent += new global::Gtk.DeleteEventHandler (this.OnDeleteEvent);
	}
	
	private Container createActionButtonsContainer() {
		var box = new HBox(false, 0);
		var editButton = new Button("Edit Contact");
		editButton.Clicked += delegate(object sender, EventArgs e) {
			var dialog = new EditContactDialog();
			dialog.Show();
		};
		box.PackEnd(editButton, false, false, 0);
		return box;
	}
	
	protected void OnDeleteEvent (object sender, DeleteEventArgs a)
	{
		Application.Quit ();
		a.RetVal = true;
	}

}
