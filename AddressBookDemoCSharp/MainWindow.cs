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
using GLib;
	
delegate void ContactHandler(Contact contact);

public class MainWindow: Gtk.Window
{
	private ContactService contactService;
	
	private NodeView contactTable;

	public MainWindow (ContactService contactService): base (Gtk.WindowType.Toplevel)
	{
		this.contactService = contactService;
		this.contactTable = CreateTable();
		this.SetSizeRequest(400, 300);
		var vbox = new VBox();
		vbox.PackStart(this.contactTable, true, true, 0);
		vbox.PackStart(createActionButtonsContainer(), false, false, 5);
		this.Add(vbox);
		this.ShowAll();
		this.Name = "MainWindow";
		this.DeleteEvent += new global::Gtk.DeleteEventHandler(this.HandleWindowDeleted);
	}

	private NodeView CreateTable ()
	{
		NodeView contactTable = new NodeView(CreateContactNodeStore());
		contactTable.AppendColumn("Name", new CellRendererText(), "text", ContactTreeNode.NAME_COLUMN_INDEX);
		contactTable.AppendColumn("Phone", new CellRendererText(), "text", ContactTreeNode.PHONE_COLUMN_INDEX);
		contactTable.AppendColumn("Email", new CellRendererText(), "text", ContactTreeNode.EMAIL_COLUMN_INDEX);
		contactTable.RowActivated += delegate (object o, RowActivatedArgs args) {
			this.ShowEditContactDialog();
		};
		return contactTable;
	}
	
	private NodeStore CreateContactNodeStore() {
		var store = new Gtk.NodeStore(typeof(ContactTreeNode));
		foreach (var contact in this.contactService.getAllContacts()) {
			store.AddNode(new ContactTreeNode(contact));
		}
		return store;
	}

	void ShowNewContactDialog()
	{
		var dialog = new NewContactDialog();
		dialog.Response += NewContactDialogResponseHandler;
		dialog.Show();
	}

	void ShowEditContactDialog()
	{
		var dialog = new EditContactDialog(this.GetSelectedContactNode().Contact);
		dialog.Response += EditContactDialogResponseHandler;
		dialog.Show();
	}
	
	private Container createActionButtonsContainer() {
		var box = new HBox(false, 0);
		var editContactButton = new Button("Edit Contact");
		editContactButton.Clicked += delegate(object sender, EventArgs e) {
			ShowEditContactDialog ();
		};
		box.PackEnd(editContactButton, false, false, 0);
		var newContactButton = new Button("Add Contact");
		newContactButton.Clicked += delegate(object sender, EventArgs e) {
			ShowNewContactDialog ();
		};
		box.PackEnd(newContactButton, false, false, 0);
		return box;
	}

	void SaveContact(Contact contact)
	{
		this.contactService.saveContact(contact);
		this.contactTable.NodeStore.AddNode(new ContactTreeNode(contact));
	}

	void UpdateContact(Contact contact)
	{
		this.contactService.updateContact(contact);
		this.GetSelectedContactNode().Contact = contact;
	}

	void NewContactDialogResponseHandler(object o, ResponseArgs args)
	{
		ContactDialogResponseHandler(o, args, SaveContact);
	}

	void EditContactDialogResponseHandler(object o, ResponseArgs args)
	{
		ContactDialogResponseHandler(o, args, UpdateContact);
	}

	void ContactDialogResponseHandler(object o, ResponseArgs args, ContactHandler contactHandler)
	{
		var dialog = (ContactDialog)o;
		if (args.ResponseId.Equals(ResponseType.Ok)) {
			contactHandler(dialog.GetContact());
		}
		dialog.Hide();
	}
	
	private ContactTreeNode GetSelectedContactNode() {
		return (ContactTreeNode) this.contactTable.NodeSelection.SelectedNode;
	}
	
	private void HandleWindowDeleted(object sender, DeleteEventArgs a)
	{
		Application.Quit ();
		a.RetVal = true;
	}

}
