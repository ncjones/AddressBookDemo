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

/**
 * An address book entry.
 */
public class Contact {

	private final Integer id;

	private final String name;

	private final String email;

	private final String phone;

	/**
	 * Create a new contact without an id.
	 * 
	 * @param name the name of the contact.
	 * @param email the email address of the contact.
	 * @param phone the phone number of the contact.
	 * @throws IllegalArgumentException if any arguments are <code>null</code>
	 *         or empty or contain only whitespace.
	 */
	public Contact(final String name, final String email, final String phone) {
		this(null, name, email, phone);
	}

	/**
	 * Create a new contact.
	 * 
	 * @param id the unique id of the contact or <code>null</code> if it is
	 *        unknown.
	 * @param name the name of the contact.
	 * @param email the email address of the contact.
	 * @param phone the phone number of the contact.
	 * @throws IllegalArgumentException if any arguments are <code>null</code>
	 *         or empty or contain only whitespace.
	 */
	public Contact(final Integer id, final String name, final String email, final String phone) {
		if (name == null || "".equals(name.trim())) {
			throw new IllegalArgumentException(String.format("Name must be valued but was: '%s'", name));
		}
		if (email == null || "".equals(email.trim())) {
			throw new IllegalArgumentException(String.format("Email must be valued but was: '%s'", email));
		}
		if (phone == null || "".equals(phone.trim())) {
			throw new IllegalArgumentException(String.format("Phone must be valued but was: '%s'",phone));
		}
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	/**
	 * @return the id of the contact, or <code>null</code> if has not be
	 *         specified.
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the contact's name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the contact's email address.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @return the contact's phone number.
	 */
	public String getPhone() {
		return this.phone;
	}

	@Override
	public String toString() {
		return "Contact [id=" + this.id + ", name=" + this.name + ", email=" + this.email + ", phone=" + this.phone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Contact other = (Contact) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
