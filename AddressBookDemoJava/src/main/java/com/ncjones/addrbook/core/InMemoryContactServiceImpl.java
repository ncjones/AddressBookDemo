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
package com.ncjones.addrbook.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class InMemoryContactServiceImpl implements ContactService {

	private static int nextId = 0;

	private final Map<Integer, Contact> contacts = new HashMap<Integer, Contact>();

	@Override
	public Set<Contact> getAllContacts() {
		return new HashSet<Contact>(this.contacts.values());
	}

	@Override
	public Contact saveContact(final Contact contact) {
		if (contact == null) {
			throw new IllegalArgumentException("Contact is null.");
		}
		if (contact.getId() != null) {
			throw new IllegalArgumentException("Contact already has an id: " + contact);
		}
		final Contact newContact = new Contact(this.getNextId(), contact.getName(), contact.getEmail(), contact.getPhone());
		this.contacts.put(newContact.getId(), newContact);
		return newContact;
	}

	@Override
	public void updateContact(final Contact contact) {
		if (contact == null) {
			throw new IllegalArgumentException("Contact is null");
		}
		if (!this.contacts.containsKey(contact.getId())) {
			throw new IllegalArgumentException("Contact has not been saved: " + contact);
		}
	}

	@Override
	public void removeContact(final Contact contact) {
		if (!this.contacts.containsValue(contact)) {
			throw new IllegalArgumentException("No such contact to remove: " + contact);
		}
		this.contacts.remove(contact);
	}

	private synchronized int getNextId() {
		return InMemoryContactServiceImpl.nextId++;
	}

}
