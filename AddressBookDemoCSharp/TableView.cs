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

namespace AddressBookDemoCSharp
{
	public class TableView<T> : Gtk.TreeView
	{
		public TableView(TableModel<T> model)
		{
			foreach (var tableColumn in model.GetTableColumns()) {
				var treeViewColumn = new Gtk.TreeViewColumn();
				treeViewColumn.Title = tableColumn.Title;
				var cellRenderer = new Gtk.CellRendererText();
				treeViewColumn.PackStart(cellRenderer, true);
				treeViewColumn.SetCellDataFunc(cellRenderer, new Gtk.TreeCellDataFunc(new TableColumnTextRenderer(tableColumn).Render));
				this.AppendColumn(treeViewColumn);
			}
			var listStore = new Gtk.ListStore(typeof(T));
			foreach (T o in model.GetRowData()) {
				listStore.AppendValues(o);
			}
			this.Model = listStore;
		}
		
		private class TableColumnTextRenderer {
			
			private TableColumn<T> tableColumn;
			
			public TableColumnTextRenderer(TableColumn<T> tableColumn) {
				this.tableColumn = tableColumn;
			}
			
			public void Render(Gtk.TreeViewColumn column, Gtk.CellRenderer cell, Gtk.TreeModel model, Gtk.TreeIter iter) {
				T rowDatum = (T) model.GetValue (iter, 0);
				(cell as Gtk.CellRendererText).Text = this.tableColumn.GetValue(rowDatum);
			}
		}
		
	}
	
}

