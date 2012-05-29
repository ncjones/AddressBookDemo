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
			this.set_model(new ListStore.newv(getColumnTypes()));
			this.insertColumns();
			this.appendRows();
		}
		
		private Type[] getColumnTypes() {
			Type[] columnTypes = new Type[this.tableModel.get_columns().length + 1];
			columnTypes[0] = typeof(string);
			var columnIndex = 1;
			foreach (var column in this.tableModel.get_columns()) {
				columnTypes[columnIndex] = typeof(string);
				columnIndex += 1;
			}
			return columnTypes;
		}
		
		private void insertColumns() {
			var columnIndex = 1;
			foreach (var column in this.tableModel.get_columns()) {
				this.insert_column_with_attributes(-1, column.get_title(), new CellRendererText(), "text", columnIndex);
				columnIndex += 1;
			}
		}
		
		private ListStore getListStore() {
			return ((ListStore)this.get_model());
		}
		
		private void appendRows() {
			foreach (T o in this.tableModel.get_row_data()) {
				this.appendRow(o);
			}
		}
		
		private void appendRow(T o) {
			TreeIter iter;
			this.getListStore().append(out iter);
			updateRow(iter, o);
		}
		
		private void updateRow(TreeIter iter, T o) {
			var listStore = getListStore();
			listStore.set_value(iter, 0, this.tableModel.get_object_id(o));
			var columnIndex = 1;
			foreach (var column in this.tableModel.get_columns()) {
				listStore.set_value(iter, columnIndex, column.get_value(o));
				columnIndex += 1;
			}
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