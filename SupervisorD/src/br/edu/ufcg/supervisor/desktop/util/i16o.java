/**
 * Copyright 2013-2014 Elthon Oliveira
 * 
 * This file is part of SupervisorD for Healthcare Professional software.
 * 
 *  SupervisorD for Healthcare Professional is free software: you can 
 *  redistribute it and/or modify it under the terms of the GNU General 
 *  Public License as published by the Free Software Foundation, either 
 *  version 3 of the License, or (at your option) any later version.
 *  
 *  SupervisorD for Healthcare Professional is distributed in the hope that
 *  it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
 *  the GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with SupervisorD for Healthcare Professional. 
 *  If not, see <http://www.gnu.org/licenses/>.
 *  
 *  Contact: el7hon at gmail dot com
 */
package br.edu.ufcg.supervisor.desktop.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class i16o {
	private static final String BUNDLE_NAME = "message";
	private static Locale locale = null;
	
	public static void setLocale(Locale l){ locale = l; }
	
	public static String label(String s, Object... args ){
		ResourceBundle resourceBundle = null;
		if(locale!=null)
			resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
		else
			resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
		String message = null;
		if(resourceBundle!=null){
			message = resourceBundle.getString(s);
			if(message!=null && args.length > 0)
				message = MessageFormat.format(message, args);
		}           
		return message;
	}	
}
