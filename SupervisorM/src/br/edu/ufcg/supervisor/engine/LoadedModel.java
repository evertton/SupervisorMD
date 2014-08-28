/**
 * Copyright 2013-2014 Marcos Ferreira and Elthon Oliveira
 * 
 * This file is part of Supervisor for Healthcare Professional software.
 * 
 *  Supervisor for Healthcare Professional is free software: you can 
 *  redistribute it and/or modify it under the terms of the GNU General 
 *  Public License as published by the Free Software Foundation, either 
 *  version 3 of the License, or (at your option) any later version.
 *  
 *  Supervisor for Healthcare Professional is distributed in the hope that
 *  it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
 *  the GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with Supervisor for Healthcare Professional. 
 *  If not, see <http://www.gnu.org/licenses/>.
 *  Contact: el7hon at gmail dot com
 */
package br.edu.ufcg.supervisor.engine;

import java.util.ArrayList;
import br.edu.ufcg.supervisor.model.*;
import br.edu.ufcg.supervisor.desktop.util.*;

public class LoadedModel {
	private static Automaton modelo = null;
	
	public static Automaton getModelo() { return modelo; }

	public static void setModelo(Automaton _modelo) { modelo = _modelo; }
	
	public static ArrayList<String> getNomesVariaveisMonitoradas(){
		String[] sArray = modelo.getNome().split("_");
		ArrayList<String> array = new ArrayList<String>();
		for (String sId : sArray){
			array.add(AttributeList.getAtributoDoId(sId));
		}
		return array; 
	}
	
	public static int[] getIdVariaveisMonitoradas(){
		String[] sArray = modelo.getNome().split("_");
		int[] arrayIds = new int[sArray.length];
		int len = sArray.length;
		for (int i = 0; i < len; i++ ) arrayIds[i] = Integer.parseInt(sArray[i]);
		return arrayIds; 
	}
}