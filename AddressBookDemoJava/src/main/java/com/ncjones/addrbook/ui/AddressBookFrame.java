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

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ncjones.addrbook.core.Contact;

public class AddressBookFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private final AddressBookTableModel addressBook;

	private JTable contactsTable;

	private final AddContactAction addContactAction = new AddContactAction();

	private final EditContactAction editContactAction = new EditContactAction();

	private final DeleteContactAction deleteContactAction = new DeleteContactAction();

	public AddressBookFrame(final AddressBookTableModel addressBook) {
		super("Address Book");
		this.addressBook = addressBook;
		this.init();
	}

	protected void init() {
		this.setLayout(new BoxLayout(this.getContentPane(), SwingConstants.VERTICAL));
		this.setBounds(50, 50, 600, 400);
		this.contactsTable = this.createContactsTable();
		this.add(new JScrollPane(this.contactsTable));
		final JPanel buttonPanel = this.createButtonPanel();
		this.add(buttonPanel);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
	}

	private JTable createContactsTable() {
		final JTable contactsTable = new JTable(this.addressBook);
		contactsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contactsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(final ListSelectionEvent e) {
				final boolean tableHasSelection = AddressBookFrame.this.contactsTable.getSelectedRow() >= 0;
				AddressBookFrame.this.editContactAction.setEnabled(tableHasSelection);
				AddressBookFrame.this.deleteContactAction.setEnabled(tableHasSelection);
			}
		});
		contactsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				if (e.getClickCount() == 2) {
					AddressBookFrame.this.editContactAction.actionPerformed(null);
				}
			}
		});
		return contactsTable;
	}

	private JPanel createButtonPanel() {
		final JButton addContactButton = new JButton(this.addContactAction);
		final JButton deleteContactButton = new JButton(this.deleteContactAction);
		final JButton editContactButton = new JButton(this.editContactAction);
		final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(addContactButton);
		buttonPanel.add(editContactButton);
		buttonPanel.add(deleteContactButton);
		return buttonPanel;
	}

	protected void showEditContactDialog() {
		final EditContactDialog editContactDialog = new EditContactDialog("Edit Contact");
		editContactDialog.addCloseListener(new EditorDialogCloseListener<Contact>() {
			@Override
			public void editorClosed(final Contact dialogValue, final boolean editConfirmed) {
				if (editConfirmed) {
					AddressBookFrame.this.updateContact(dialogValue);
				}
			}
		});
		editContactDialog.setLocationRelativeTo(this);
		editContactDialog.setValue(this.getSelectedContact());
		editContactDialog.showDialog();
	}

	private void updateContact(final Contact contact) {
		this.addressBook.updateContact(contact);
	}

	private Contact getSelectedContact() {
		final int selectedRow = this.contactsTable.getSelectedRow();
		if (selectedRow >= 0) {
			return this.addressBook.getContact(selectedRow);
		}
		return null;
	}

	private void showAddContactDialog() {
		final EditContactDialog addContactDialog = new EditContactDialog("Add Contact");
		addContactDialog.addCloseListener(new EditorDialogCloseListener<Contact>() {
			@Override
			public void editorClosed(final Contact dialogValue, final boolean editConfirmed) {
				if (editConfirmed) {
					AddressBookFrame.this.addContact(dialogValue);
				}
			}
		});
		addContactDialog.setLocationRelativeTo(this);
		addContactDialog.setValue(null);
		addContactDialog.showDialog();
	}

	private void addContact(final Contact contact) {
		this.addressBook.addContact(contact);
	}

	private void showDeleteContactDialog() {
		final String message = String.format("Delete contact \"%s\"?", this.getSelectedContact().getName());
		final int response = JOptionPane.showConfirmDialog(this, message, "Confirm Delete", JOptionPane.YES_NO_OPTION);
		if (JOptionPane.OK_OPTION == response) {
			this.deleteSelectedContact();
		}
	}

	private void deleteSelectedContact() {
		final int selectedRow = this.contactsTable.getSelectedRow();
		if (selectedRow >= 0) {
			AddressBookFrame.this.addressBook.removeContact(selectedRow);
		}
	}

	private final class AddContactAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public AddContactAction() {
			super("Add Contact");
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			AddressBookFrame.this.showAddContactDialog();
		}
	}

	private final class EditContactAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public EditContactAction() {
			super("Edit Contact");
			this.setEnabled(false);
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			AddressBookFrame.this.showEditContactDialog();
		}
	}

	private final class DeleteContactAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public DeleteContactAction() {
			super("Delete Contact");
			this.setEnabled(false);
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			AddressBookFrame.this.showDeleteContactDialog();
		}
	}

}
