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

import javax.swing.table.AbstractTableModel;

import com.ncjones.addrbook.core.Contact;
import com.ncjones.addrbook.core.ContactService;

/**
 * Table model for a list of address book {@link Contact}s.
 */
public class AddressBookTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private final List<Contact> contacts;

	private final ContactService contactService;

	public AddressBookTableModel(final ContactService contactService) {
		this.contactService = contactService;
		this.contacts = new ArrayList<Contact>(contactService.getAllContacts());
	}

	public Contact getContact(final int rowIndex) {
		return this.contacts.get(rowIndex);
	}

	public void addContact(final Contact contact) {
		final Contact savedContact = this.contactService.saveContact(contact);
		this.contacts.add(savedContact);
		final int rowIndex = this.contacts.indexOf(savedContact);
		this.fireTableRowsInserted(rowIndex, rowIndex);
	}

	public void removeContact(final Contact contact) {
		this.removeContact(this.contacts.indexOf(contact));
	}

	public void removeContact(final int rowIndex) {
		final Contact contact = this.contacts.get(rowIndex);
		this.contactService.removeContact(contact);
		this.contacts.remove(rowIndex);
		this.fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void updateContact(final Contact contact) {
		this.contactService.updateContact(contact);
		final int rowIndex = this.contacts.indexOf(contact);
		this.contacts.remove(rowIndex);
		this.contacts.add(rowIndex, contact);
		this.fireTableRowsUpdated(rowIndex, rowIndex);
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(final int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Name";
		case 1:
			return "Number";
		case 2:
			return "Email";
		}
		throw new IllegalArgumentException("Invalid column index: " + columnIndex);
	}

	@Override
	public Class<?> getColumnClass(final int columnIndex) {
		return String.class;
	}

	@Override
	public int getRowCount() {
		return this.contacts.size();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final Contact contact = this.contacts.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return contact.getName();
		case 1:
			return contact.getPhone();
		case 2:
			return contact.getEmail();
		}
		throw new IllegalArgumentException("Invalid column index: " + columnIndex);
	}

}
