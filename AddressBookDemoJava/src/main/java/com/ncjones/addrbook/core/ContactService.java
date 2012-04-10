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

import java.util.Set;

/**
 * A service for accessing and modifying {@link Contact}s.
 */
public interface ContactService {

	/**
	 * Get all saved contacts.
	 *
	 * @return a set of {@link Contact}s.
	 */
	Set<Contact> getAllContacts();

	/**
	 * Save a new contact. The new contact must have a <code>null</code> id.
	 * 
	 * @param contact the contact to save.
	 * @return the saved {@link Contact} with its id populated.
	 * @throws IllegalArgumentException if the {@link Contact}'s id is not
	 *         <code>null</code>.
	 */
	Contact saveContact(Contact contact);

	/**
	 * Update a saved contact.
	 * 
	 * @param contact the contact to update.
	 * @throws IllegalArgumentException if a {@link Contact} with the id of the
	 *         provided contact does not exist.
	 */
	void updateContact(Contact contact);

	/**
	 * Remove a contact.
	 *
	 * @param contact the contact to remove.
	 */
	void removeContact(Contact contact);

}
