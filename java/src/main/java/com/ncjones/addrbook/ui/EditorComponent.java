/*
 * Copyright Nathan Jones 2012
 *
 * This file is part of AddressBookDemo.
 *
 * AddressBookDemo is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AddressBookDemo is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AddressBookDemo.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ncjones.addrbook.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public abstract class EditorComponent<T> extends JComponent {

	private static final long serialVersionUID = 1L;

	private final List<ValueChangeListener<T>> valueChangeListeners = new ArrayList<ValueChangeListener<T>>();

	public abstract T getValue();

	public abstract void setValue(T contact);

	public abstract boolean isValueValid();

	public void addValueChangeListener(final ValueChangeListener<T> listener) {
		this.valueChangeListeners.add(listener);
	}

	protected void fireValueChanged() {
		for (final ValueChangeListener<T> listener : this.valueChangeListeners) {
			listener.valueChanged(this.getValue());
		}
	}

}
