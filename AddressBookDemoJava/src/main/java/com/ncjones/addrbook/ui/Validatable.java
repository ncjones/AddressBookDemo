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
 * Contains a {@link ValidationState} and notifies subscribers when the
 * validation state changes.
 */
public interface Validatable {

	/**
	 * @return the current {@link ValidationState}.
	 */
	ValidationState getValidationState();

	/**
	 * Add a {@link ValidationStateChangeListener} so it receives notifications
	 * from this {@link Validatable} when ever its validation state changes.
	 * 
	 * @param listener the listener to subscribe.
	 */
	void addValidationStateChangeListener(ValidationStateChangeListener listener);

	/**
	 * Remove a {@link ValidationStateChangeListener} so it no longer receives
	 * notifications from this {@link Validatable}.
	 * 
	 * @param listener the listener to unsubscribe.
	 */
	void removeValidationStateChangeListener(ValidationStateChangeListener listener);

}
