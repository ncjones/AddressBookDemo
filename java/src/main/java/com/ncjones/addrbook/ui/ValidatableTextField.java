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
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ValidatableTextField extends JTextField implements DocumentListener {

	private static final long serialVersionUID = 1L;

	private final Pattern regex;

	private final boolean nullable;

	private final List<ValueChangeListener<String>> valueChangeListeners = new ArrayList<ValueChangeListener<String>>();

	public ValidatableTextField(final String regex, final boolean nullable) {
		this.getDocument().addDocumentListener(this);
		this.regex = Pattern.compile(regex);
		this.nullable = nullable;
	}

	public boolean isValueValid() {
		return this.nullable && this.getText().isEmpty() || this.regex.matcher(this.getText()).matches();
	}

	@Override
	public void insertUpdate(final DocumentEvent e) {
		this.fireValueChanged();
	}

	@Override
	public void removeUpdate(final DocumentEvent e) {
		this.fireValueChanged();
	}

	@Override
	public void changedUpdate(final DocumentEvent e) {
		this.fireValueChanged();
	}

	private void fireValueChanged() {
		for (final ValueChangeListener<String> listener : this.valueChangeListeners) {
			listener.valueChanged(this.getText());
		}
	}

	public void addValueChangeListener(final ValueChangeListener<String> listener) {
		this.valueChangeListeners.add(listener);
	}

}
