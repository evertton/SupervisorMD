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
import java.util.LinkedList;
import java.util.TimeZone;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ufcg.supervisor.engine.LoadedModel;
import br.edu.ufcg.supervisor.engine.Search;
import br.edu.ufcg.supervisor.engine.TrainingLoader;
import br.edu.ufcg.supervisor.model.Automaton;
import br.edu.ufcg.supervisor.model.State;

import android.os.Environment;
import android.provider.Settings;
import android.text.format.Time;

public class SupervisorInterface extends org.apache.cordova.api.CordovaPlugin {
    //public static final String TAG = "SupervisorInterface";
	
	private static String pathToFile = "";
	private static Automaton model = null;
	private static String logString = "";
	private static HashMap<String, Float> map = new HashMap<String, Float>(); //nome - valor
	HashMap<Integer, Float> map2 = new HashMap<Integer, Float>();//mesmo que map, soh q com chave "integer"
	
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
		ArrayList<String> names = LoadedModel.getNomesVariaveisMonitoradas();
		String recommendation = "";
		ArrayList<String> arrayMensagens = new ArrayList<String>();
		String currentState = "";
		for (int i = 0; i < map.size(); i++) { currentState = currentState+"- "+names.get(i)+": "+map.get(names.get(i))+".<br>"; }
		logString = logString + currentState + " - ";
		r.put("cur",currentState);
		State estado;
		try {
			Automaton a = LoadedModel.getModelo();
			estado = a.buscaEstadoCorrespondente(map2);
			if (!(estado.getClassificacao() == State.INT_CL_ACEITACAO) ) {//verifica se E Qm, caso não chama o algoritmo
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
	
	private String getShortestPath(ArrayList<String> array){
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
	private String eliminateReplicatedRecommendations(String rec){
		String result = "";
		rec = rec.replaceFirst(".", "");
		String[] temp = rec.split("\\.");
		for (int i = 0; i < temp.length; i++) {
			if (!result.contains(temp[i])) result = result + ", " + temp[i];
		}
		return result.replaceFirst(", ","") +".";		
	}

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
			LoadedModel.setModelo(model);
			int[] arrayIds = LoadedModel.getIdVariaveisMonitoradas();
			ArrayList<String> arrayNames = LoadedModel.getNomesVariaveisMonitoradas();
			String ids = "";
			String names = "";
			map.clear();//zera o map para limpar modelos carregados anteriormente.
			map2.clear();
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
			model = new Automaton("{1=11_, 2=[{0=HurryUp, 2=0, 3=1}, {0=SlowDown, 2=1, 3=0}, {0=HurryUp, 2=1, 3=2}, {0=SlowDown, 2=2, 3=1}, {0=HurryUp, 2=2, 3=3}, {0=SlowDown, 2=3, 3=2}], 3=[{1=slow, 4=1, 5=[{0=11, 1=7.0, 2=0.0, 3=0, 4=1}]}, {1=mode, 4=3, 5=[{0=11, 1=10.0, 2=7.0, 3=0, 4=1}]}, {1=fast, 4=3, 5=[{0=11, 1=14.0, 2=10.0, 3=0, 4=1}]}, {1=vfast, 4=2, 5=[{0=11, 1=20.0, 2=14.0, 3=0, 4=1}]}], 4=[1, 2]}");
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
			model = new Automaton("{1=11_, 2=[{0=HurryUp, 2=0, 3=1}, {0=SlowDown, 2=1, 3=0}, {0=HurryUp, 2=1, 3=2}, {0=SlowDown, 2=2, 3=1}, {0=HurryUp, 2=2, 3=3}, {0=SlowDown, 2=3, 3=2}], 3=[{1=slow, 4=1, 5=[{0=11, 1=7.0, 2=0.0, 3=0, 4=1}]}, {1=mode, 4=3, 5=[{0=11, 1=10.0, 2=7.0, 3=0, 4=1}]}, {1=fast, 4=3, 5=[{0=11, 1=14.0, 2=10.0, 3=0, 4=1}]}, {1=vfast, 4=2, 5=[{0=11, 1=20.0, 2=14.0, 3=0, 4=1}]}], 4=[1, 2]}");
		} else if (fileName.equals("shm")){
			model = new Automaton("{1=1_12_, 2=[{0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=0, 3=1}, {0=Increase_temperature., 2=0, 3=3}, {0=Decrease_heart-rate.(by_slowing_down), 2=1, 3=0}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=1, 3=2}, {0=Increase_temperature., 2=1, 3=4}, {0=Decrease_heart-rate.(by_slowing_down), 2=2, 3=1}, {0=Increase_temperature., 2=2, 3=5}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=3, 3=4}, {0=Decrease_temperature.(or_hydrate!), 2=3, 3=0}, {0=Increase_temperature., 2=3, 3=6}, {0=Decrease_heart-rate.(by_slowing_down), 2=4, 3=3}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=4, 3=5}, {0=Decrease_temperature.(or_hydrate!), 2=4, 3=1}, {0=Increase_temperature., 2=4, 3=7}, {0=Decrease_heart-rate.(by_slowing_down), 2=5, 3=4}, {0=Decrease_temperature.(or_hydrate!), 2=5, 3=2}, {0=Increase_temperature., 2=5, 3=8}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=6, 3=7}, {0=Decrease_temperature.(or_hydrate!), 2=6, 3=3}, {0=Decrease_heart-rate.(by_slowing_down), 2=7, 3=6}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=7, 3=8}, {0=Decrease_temperature.(or_hydrate!), 2=7, 3=4}, {0=Decrease_heart-rate.(by_slowing_down), 2=8, 3=7}, {0=Decrease_temperature.(or_hydrate!), 2=8, 3=5}], 3=[{1=safe.good, 4=3, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=12, 1=29.0, 2=19.0, 3=0, 4=0}]}, {1=tolerable.good, 4=1, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=12, 1=29.0, 2=19.0, 3=0, 4=0}]}, {1=dangerous.good, 4=2, 5=[{0=1, 1=160.0, 2=130.0, 3=0, 4=1}, {0=12, 1=29.0, 2=19.0, 3=0, 4=0}]}, {1=safe.high, 4=2, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=12, 1=34.0, 2=29.0, 3=0, 4=1}]}, {1=tolerable.high, 4=2, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=12, 1=34.0, 2=29.0, 3=0, 4=1}]}, {1=dangerous.high, 4=2, 5=[{0=1, 1=160.0, 2=130.0, 3=0, 4=1}, {0=12, 1=34.0, 2=29.0, 3=0, 4=1}]}, {1=safe.vhig, 4=2, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=12, 1=50.0, 2=34.0, 3=0, 4=1}]}, {1=tolerable.vhig, 4=2, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=12, 1=50.0, 2=34.0, 3=0, 4=1}]}, {1=dangerous.vhig, 4=2, 5=[{0=1, 1=160.0, 2=130.0, 3=0, 4=1}, {0=12, 1=50.0, 2=34.0, 3=0, 4=1}]}], 4=[0]}");
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