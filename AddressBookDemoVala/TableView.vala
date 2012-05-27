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

using Gtk;

namespace AddressBookDemo {
	
	public class TableView<T> : Gtk.TreeView {
		
		private TableModel<T> tableModel;
		
		public TableView(TableModel<T> model) {
			this.tableModel = model;
			var columnIndex = 1;
			Type[] columnTypes = new Type[model.get_columns().length + 1];
			columnTypes[0] = typeof(string);
			foreach (var column in model.get_columns()) {
				this.insert_column_with_attributes(-1, column.get_title(), new CellRendererText(), "text", columnIndex);
				columnTypes[columnIndex] = typeof(string);
				columnIndex += 1;
			}
			ListStore listStore = new ListStore.newv(columnTypes);
			foreach (T o in model.get_row_data()) {
				TreeIter iter;
				listStore.append(out iter);
				listStore.set_value(iter, 0, model.get_object_id(o));
				columnIndex = 1;
				foreach (var column in model.get_columns()) {
					listStore.set_value(iter, columnIndex, column.get_value(o));
					columnIndex += 1;
				}
			}
			this.set_model(listStore);
		}
		
		public T? getSelectedObject() {
			TreeIter iter;
			this.get_selection().get_selected(null, out iter);
			string selectedObjectId;
			this.get_model().get(iter, 0, out selectedObjectId, -1);
			return this.tableModel.get_object(selectedObjectId);
		}
		
	}

}