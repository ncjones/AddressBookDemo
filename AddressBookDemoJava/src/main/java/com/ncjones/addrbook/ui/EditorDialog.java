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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class EditorDialog<T> extends JDialog implements ValidationStateChangeListener {

	private static final long serialVersionUID = 1L;

	private final List<EditorDialogCloseListener<T>> closeListeners = new ArrayList<EditorDialogCloseListener<T>>();

	private boolean confirmed;

	private final EditorComponent<T> editorComponent;

	private JButton okButton;

	public EditorDialog(final EditorComponent<T> editorComponent) {
		this.editorComponent = editorComponent;
		this.init();
	}

	protected void init() {
		this.setLayout(new BorderLayout());
		this.add(this.getEditorComponent());
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setModal(true);
		this.pack();
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(final ComponentEvent e) {
				EditorDialog.this.confirmed = false;
			}
			@Override
			public void componentHidden(final ComponentEvent e) {
				EditorDialog.this.fireClosed();
			}

		});
		this.editorComponent.addValidationStateChangeListener(this);
	}

	protected Component createButtonPanel() {
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.okButton = new JButton("Ok");
		this.okButton.setEnabled(this.editorComponent.getValidationState().isValid());
		this.okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				EditorDialog.this.confirmed = true;
				EditorDialog.this.hideDialog();
			}
		});
		buttonPanel.add(this.okButton);
		return buttonPanel;
	}

	protected EditorComponent<T> getEditorComponent() {
		return this.editorComponent;
	}

	public T getValue() {
		return this.getEditorComponent().getValue();
	}

	public void setValue(final T value) {
		this.getEditorComponent().setValue(value);
	}

	public void showDialog() {
		this.setVisible(true);
	}

	public void hideDialog() {
		this.setVisible(false);
	}

	public void addCloseListener(final EditorDialogCloseListener<T> closeListener) {
		this.closeListeners.add(closeListener);
	}

	private void fireClosed() {
		for (final EditorDialogCloseListener<T> listener : this.closeListeners) {
			listener.editorClosed(this.getValue(), this.confirmed);
		}
	}

	@Override
	public void validationStateChanged(final Validatable validatable) {
		this.okButton.setEnabled(this.editorComponent.getValidationState().isValid());
	}

}