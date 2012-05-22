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
