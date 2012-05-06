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
 * Receives notifications of changes in validation state of {@link Validatable}
 * objects.
 */
public interface ValidationStateChangeListener {

	/**
	 * Signals that the validation state has changed on the given
	 * {@link Validatable} object.
	 * 
	 * @param validatable the {@link Validatable} of which the validation state
	 *        has change.
	 */
	void validationStateChanged(Validatable validatable);
}
