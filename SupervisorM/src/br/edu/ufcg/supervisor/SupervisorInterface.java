/**
 * Copyright 2013-2014 Elthon Oliveira and Marcos Ferreira
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
 *  
 *  Contact: el7hon at gmail dot com
 */

package br.edu.ufcg.supervisor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimeZone;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ufcg.supervisor.engine.LoadedModel;
import br.edu.ufcg.supervisor.engine.Simulation;
import br.edu.ufcg.supervisor.engine.TrainingLoader;
import br.edu.ufcg.supervisor.model.Automaton;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.provider.Settings;
import android.text.format.Time;

public class SupervisorInterface extends org.apache.cordova.api.CordovaPlugin {
    //public static final String TAG = "SupervisorInterface";
	
	private static String pathToFile = "";
	private static Automaton model = null;
	private static String logString = "";
	private static HashMap<String, Float> map = new HashMap<String, Float>(); //nome - valor
	@SuppressLint("UseSparseArrays")
	private static HashMap<Integer, Float> map2 = new HashMap<Integer, Float>();//mesmo que map, soh q com chave "integer"
	
    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
	public void initialize(CordovaInterface cordova, CordovaWebView webView) { super.initialize(cordova, webView); }
	
	/**
     * Executes the request and returns PluginResult.
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false if not.
     */
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("java_start")) {
			start(callbackContext);
			return true;
		} else if (action.equals("java_load_model_from_file")) {
			loadModelFromFile(args, callbackContext);
			return true;
		} else if (action.equals("java_load_pre_defined_model")) {
			loadPreDefinedModel(args, callbackContext);
			return true;
	    } else if(action.equals("java_execute_model")){
	    	executeModel(args, callbackContext);
	    	return true;  	
	    } else if(action.equals("java_save_log")){
	    	saveLogTraining(logString);
	    	return true;  	
	    } else { return false; }
    }
	
	private void saveLogTraining(String content){
    	logString.replaceAll("<br>","");
		try {
			Time time = new Time();
			//String fileName = "supervisorM-"+time.year+time.month+time.weekDay+time.hour+time.minute+time.second+".log";
			@SuppressWarnings("static-access")
			String fileName = "supervisorM-"+time.YEAR+time.MONTH+time.HOUR+time.MINUTE+time.SECOND+".log";
			String folder = Environment.getExternalStorageDirectory().toString() + "/Download/";
			BufferedWriter writer = new BufferedWriter(new FileWriter(folder+fileName));  
			writer.write(content);
			writer.flush();  
			writer.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	/**
	 * Executes the model with some data and extracts recommendations.
	 * @param args Passed from Javascript interface.
	 * @param callbackContext Used to return the result of processing.
	 * @throws JSONException
	 */
	private void executeModel(JSONArray args, CallbackContext callbackContext) throws JSONException{
		JSONObject r = new JSONObject();
		String name = args.get(0).toString();
		Float value = Float.valueOf((String)args.get(1));
		map.put(name, value);
		map2.put(Integer.valueOf((String)args.get(2)), value);
		String recommendation = "";
		String currentState = "";
		try {
			Simulation.executeModel(r,model,map,map2,currentState,recommendation,logString);
		} catch (Exception e) {
			recommendation = "Value not monitored.";
			logString = logString + "("+recommendation+")\n";
			r.put("error",recommendation);
			r.put("rec","Stop and verify your devices. If this appears again, call your healthcare professional.");
			callbackContext.error(r);
		}
		callbackContext.success(r);
	}
	
	/*
	private void executeModel(JSONArray args, CallbackContext callbackContext) throws JSONException{
		JSONObject r = new JSONObject();
		String name = args.get(0).toString();
		Float value = Float.valueOf((String)args.get(1));
		map.put(name, value);
		map2.put(Integer.valueOf((String)args.get(2)), value);
		ArrayList<String> names = LoadedModel.getNomesVariaveisMonitoradas();
		String recommendation = "";
		ArrayList<String> arrayMensagens = new ArrayList<String>();
		String currentState = "";
		for (int i = 0; i < map.size(); i++) { currentState = currentState+"- "+names.get(i)+": "+map.get(names.get(i))+".<br>"; }
		logString = logString + currentState + " - ";
		r.put("cur",currentState);
		State estado;
		try {		
			Automaton a = LoadedModel.getModel();
			estado = a.buscaEstadoCorrespondente(map2);
			if (!(estado.getClassificacao() == State.INT_CL_ACEITACAO)){
				Search alg = new Search(model);
				alg.execute(estado);
				for (State estadoAceito : model.getArrayEstadosAceitos()){
					LinkedList<State> caminho = alg.getPath(estadoAceito);
					if (caminho != null){
						for (int j=0; j<caminho.size()-1;j++) {
							recommendation += "."+model.getMensagemDasTransicoesEntreDoisEstadosQuaisquer(caminho.get(j),caminho.get(j+1));//elthon
						}
						arrayMensagens.add(recommendation);
					}
				}
				String x = getShortestPath(arrayMensagens);
				x = eliminateReplicatedRecommendations(x);
				if (x.equals(".")) x = "Some variable has not been measured!";
				logString = logString + x +"\n";
				r.put("rec",x);
				callbackContext.success(r);
			} else
				logString = logString + "(keep going)\n";
				r.put("rec","Keep going!");
				r.put("cur",currentState);
				callbackContext.success(r);
		} catch (Exception e) {
			logString = logString + "(state not covered)\n";
			r.put("error","Value not monitored.");
			r.put("rec","Stop and verify your devices. If this appears again, call your healthcare professional.");
			callbackContext.error(r);
		}
	}
	
	private static String getShortestPath(ArrayList<String> array){
		if (array.size() == 0){	return ""; }
		int minimo = array.get(0).split(".").length;
		int qtd;
		int indexMenorCaminho = 0;
		for(int i = 1; i < array.size(); i++){
			qtd = array.get(i).split(".").length;
			if (qtd < minimo){
				minimo = qtd;
				indexMenorCaminho = i;
			}
		}
		return array.get(indexMenorCaminho);
	}
	
	private static String eliminateReplicatedRecommendations(String rec){
		String result = "";
		rec = rec.replaceFirst(".", "");
		String[] temp = rec.split("\\.");
		for (int i = 0; i < temp.length; i++) {
			if (!result.contains(temp[i])) result = result + ", " + temp[i];
		}
		return result.replaceFirst(", ","") +".";		
	}
	*/
	
	/**
	 * Starts the process of executing the chosen model.
	 * @param callbackContext Used to return the result of processing.
	 * @throws JSONException
	 */
	private void start(CallbackContext callbackContext) throws JSONException{
		JSONObject r = new JSONObject();
		if (model==null){
			r.put("msg", "At first, you have to load a training.");
			callbackContext.error(r);
		} else {
			model.setNome(model.getNome());
			LoadedModel.setModel(model);
			int[] arrayIds = LoadedModel.getIdVariaveisMonitoradas();
			ArrayList<String> arrayNames = LoadedModel.getNomesVariaveisMonitoradas();
			String ids = "";
			String names = "";
			map.clear();//zera o map para limpar modelos carregados anteriormente.
			map2.clear();
			logString = "";
			for(int i = 0; i<arrayNames.size();i++){
				ids = ids + "," + arrayIds[i];
				names = names + "," + arrayNames.get(i);
				map.put(arrayNames.get(i), 0.f);
				//map2.put(Integer.valueOf(arrayIds[i]), 0.f);
			}
			r.put("ids", ids.replaceFirst(",",""));
			r.put("names", names.replaceFirst(",",""));
			r.put("msg", model.getNome());
			callbackContext.success(r);
		}		
	}
	
	/**
	 * Loads model from a given name of a file. If it does not exist, loads a pre-defined simple one.
	 * @param args Passed from Javascript interface.
	 * @param callbackContext Used to return the result of processing.
	 * @throws JSONException
	 */
	private void loadModelFromFile(JSONArray args, CallbackContext callbackContext) throws JSONException{
		JSONObject r = new JSONObject();
		String fileName = args.get(0).toString()+".sup";
		pathToFile = Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName;
		File file = new File(pathToFile);
		if (!file.exists()){
			model = null;
			//model = TrainingLoader.getDefaultModel();
			r.put("msg", "File not found.");
			callbackContext.error(r);
		} else {
			String automatoJson = "";
			try { automatoJson = TrainingLoader.readFile(pathToFile); }
			catch (IOException e) {
				r.put("msg", "erro ao ler arquivo1.");
				callbackContext.error(r);
			}
			model = new Automaton(automatoJson);
			r.put("msg", "File found. Model loaded.");
			callbackContext.success(r);
		}
	}
	
	/**
	 * Loads model from a pre-defined list.
	 * @param args Passed from Javascript interface.
	 * @param callbackContext Used to return the result of processing.
	 * @throws JSONException
	 */
	private void loadPreDefinedModel(JSONArray args, CallbackContext callbackContext) throws JSONException{
		String fileName = args.get(0).toString();
		if (fileName.equals("sm")){
			model = new Automaton(TrainingLoader.model1);
		} else if (fileName.equals("shm")){
			model = new Automaton(TrainingLoader.model2);
		} else if (fileName.equals("shtm")){
			model = new Automaton(TrainingLoader.model3);
		} else {
			model = null;
		}
		callbackContext.success();
	}
	//--------------------------------------------------------------------------
    // Cordova METHODS
    //--------------------------------------------------------------------------
    /**
     * Get the device's Universally Unique Identifier (UUID).
     *
     * @return
     */
    public String getUuid() {
        String uuid = Settings.Secure.getString(this.cordova.getActivity().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        return uuid;
    }
    public String getModel() { String model = android.os.Build.MODEL; return model; }
    public String getProductName() { String productname = android.os.Build.PRODUCT; return productname; }
    /**
     * Get the OS version.
     *
     * @return
     */
    public String getOSVersion() { String osversion = android.os.Build.VERSION.RELEASE; return osversion; }
    public String getTimeZoneID() { TimeZone tz = TimeZone.getDefault(); return (tz.getID()); }
}