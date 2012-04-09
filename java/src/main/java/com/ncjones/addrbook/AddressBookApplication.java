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
package com.ncjones.addrbook;

import javax.swing.SwingUtilities;

import com.ncjones.addrbook.core.Contact;
import com.ncjones.addrbook.core.ContactService;
import com.ncjones.addrbook.core.InMemoryContactServiceImpl;
import com.ncjones.addrbook.ui.AddressBookFrame;
import com.ncjones.addrbook.ui.AddressBookTableModel;

public class AddressBookApplication {

	public static void main(final String[] args) {
		final ContactService contactService = new InMemoryContactServiceImpl();
		final AddressBookTableModel addressBook = new AddressBookTableModel(contactService);
		addressBook.addContact(new Contact("One Example", "example1@example.com", "123 456 789"));
		addressBook.addContact(new Contact("Two Example", "example2@example.com", "321 654 987"));
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final AddressBookFrame frame = new AddressBookFrame(addressBook);
				frame.setVisible(true);
			}
		});
	}

}
