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

import java.util.regex.Pattern;

/**
 * Validates strings are non-empty and match a regular expression.
 */
public class RegexStringValidator implements Validator<String> {

	private final Pattern regex;

	private final String emptyMessage;

	private final String unmatchedMessage;

	/**
	 * Create a new {@link RegexStringValidator}.
	 * 
	 * @param regex the regular expression pattern that non-empty values should
	 *        match.
	 * @param emptyMessage the validation message to use when a value is empty.
	 * @param unmatchedMessage the validation message to use when a value does
	 *        not match the regular expression.
	 */
	public RegexStringValidator(final String regex, final String emptyMessage, final String unmatchedMessage) {
		this.regex = Pattern.compile(regex);
		this.emptyMessage = emptyMessage;
		this.unmatchedMessage = unmatchedMessage;
	}

	@Override
	public ValidationState validate(final String input) {
		if (input == null || input.isEmpty()) {
			return new ValidationState(false, this.emptyMessage);
		}
		if (!this.regex.matcher(input).matches()) {
			return new ValidationState(false, this.unmatchedMessage);
		}
		return new ValidationState(true, "");
	}

}
