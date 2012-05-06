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

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ValidatableTextField extends JTextField implements Validatable {

	private static final long serialVersionUID = 1L;

	private final List<ValidationStateChangeListener> validationStateChangeListeners = new ArrayList<ValidationStateChangeListener>();

	private final Validator<String> validator;

	private ValidationState validationState = new ValidationState(false, "");

	public ValidatableTextField(final Validator<String> validator) {
		this.getDocument().addDocumentListener(new ValidatableTextFieldDocumentListener());
		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(final FocusEvent e) {
				ValidatableTextField.this.valueChanged();
			}
		});
		this.validator = validator;
	}

	private class ValidatableTextFieldDocumentListener implements DocumentListener {

		@Override
		public void insertUpdate(final DocumentEvent e) {
			ValidatableTextField.this.valueChanged();
		}

		@Override
		public void removeUpdate(final DocumentEvent e) {
			ValidatableTextField.this.valueChanged();
		}

		@Override
		public void changedUpdate(final DocumentEvent e) {
			ValidatableTextField.this.valueChanged();
		}

	}

	private void valueChanged() {
		final ValidationState newValidationState = this.validator.validate(this.getText());
		if (!newValidationState.equals(this.validationState)) {
			this.validationState = newValidationState;
			this.fireValidationStateChanged();
		}
	}

	private void fireValidationStateChanged() {
		for (final ValidationStateChangeListener listener : this.validationStateChangeListeners) {
			listener.validationStateChanged(this);
		}
	}

	@Override
	public ValidationState getValidationState() {
		return this.validationState;
	}

	@Override
	public void addValidationStateChangeListener(final ValidationStateChangeListener listener) {
		this.validationStateChangeListeners.add(listener);
	}

	@Override
	public void removeValidationStateChangeListener(final ValidationStateChangeListener listener) {
		this.validationStateChangeListeners.remove(listener);
	}

}
