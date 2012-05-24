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
		
		public TableView(TableModel<T> model) {
			var columnIndex = 0;
			foreach (var column in model.get_columns()) {
				this.insert_column_with_attributes(-1, column.get_title(), new CellRendererText(), "text", columnIndex);
				columnIndex += 1;
			}
			var listStore = new Gtk.ListStore(model.get_columns().length, typeof(T));
			foreach (T o in model.get_row_data()) {
				TreeIter iter;
				listStore.append(out iter);
				columnIndex = 0;
				foreach (var column in model.get_columns()) {
					listStore.set_value(iter, columnIndex, column.get_value(o));
					columnIndex += 1;
				}
			}
			this.set_model(listStore);
		}
		
	}

}