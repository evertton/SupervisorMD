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