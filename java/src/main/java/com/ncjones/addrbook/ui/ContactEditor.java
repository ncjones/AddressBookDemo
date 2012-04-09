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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JLabel;

import com.ncjones.addrbook.core.Contact;

public class ContactEditor extends EditorComponent<Contact> implements ValueChangeListener<String> {

	private static final long serialVersionUID = 1L;

	private ValidatableTextField nameField;

	private ValidatableTextField emailField;

	private ValidatableTextField phoneField;

	private Integer contactId;

	public ContactEditor() {
		this.initComponentUi();
	}

	private void initComponentUi() {
		final JComponent panel = this;
		this.nameField = new ValidatableTextField(".+", false);
		this.nameField.setColumns(22);
		this.nameField.addValueChangeListener(this);
		final JLabel nameLabel = new JLabel("Name");
		nameLabel.setLabelFor(this.nameField);
		this.emailField = new ValidatableTextField(".+@.+", false);
		this.emailField.setColumns(22);
		this.emailField.addValueChangeListener(this);
		final JLabel emailLabel = new JLabel("Email");
		emailLabel.setLabelFor(this.emailField);
		this.phoneField = new ValidatableTextField("\\+?[0-9]([0-9]| )*", false);
		this.phoneField.setColumns(22);
		this.phoneField.addValueChangeListener(this);
		final JLabel phoneLabel = new JLabel("Phone");
		phoneLabel.setLabelFor(this.phoneField);
		final GroupLayout groupLayout = new GroupLayout(panel);
		panel.setLayout(groupLayout);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(nameLabel).addComponent(emailLabel).addComponent(phoneLabel))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(this.nameField).addComponent(this.emailField).addComponent(this.phoneField)));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER, false).addComponent(nameLabel).addComponent(this.nameField))
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER, false).addComponent(emailLabel).addComponent(this.emailField))
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER, false).addComponent(phoneLabel).addComponent(this.phoneField)));
	}

	@Override
	public void setValue(final Contact contact) {
		if (contact == null) {
			this.contactId = null;
			this.nameField.setText("");
			this.emailField.setText("");
			this.phoneField.setText("");
		} else {
			this.contactId = contact.getId();
			this.nameField.setText(contact.getName());
			this.emailField.setText(contact.getEmail());
			this.phoneField.setText(contact.getPhone());
		}
	}

	@Override
	public boolean isValueValid() {
		return this.nameField.isValueValid() && this.emailField.isValueValid() && this.phoneField.isValueValid();
	}

	@Override
	public Contact getValue() {
		if (!this.isValueValid()) {
			return null;
		}
		final String name = this.nameField.getText();
		final String email = this.emailField.getText();
		final String phone = this.phoneField.getText();
		return new Contact(this.contactId, name, email, phone);
	}

	@Override
	public void valueChanged(final String value) {
		this.fireValueChanged();
	}

}
