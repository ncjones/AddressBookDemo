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

/**
 * The validation state of a {@link Validatable}.
 * <p>
 * The validation state contains a "valid" flag and a human-readable message.
 */
public class ValidationState {

	private final String message;

	private final boolean valid;

	/**
	 * Create a new {@link ValidationState}.
	 * 
	 * @param valid whether the {@link Validatable}'s value is valid.
	 * @param message a localised human-readable message describing why the
	 *        validation failed, or an empty string if validation passed.
	 */
	public ValidationState(final boolean valid, final String message) {
		this.valid = valid;
		this.message = message;
	}

	/**
	 * @return <code>true</code> if the {@link Validatable}'s value is valid.
	 */
	public boolean isValid() {
		return this.valid;
	}

	/**
	 * @return a localised human-readable message describing why the validation
	 *         failed, or an empty string if validation passed.
	 */
	public String getMessage() {
		return this.message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.message == null) ? 0 : this.message.hashCode());
		result = prime * result + (this.valid ? 1231 : 1237);
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
		final ValidationState other = (ValidationState) obj;
		if (this.message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!this.message.equals(other.message)) {
			return false;
		}
		if (this.valid != other.valid) {
			return false;
		}
		return true;
	}

}
