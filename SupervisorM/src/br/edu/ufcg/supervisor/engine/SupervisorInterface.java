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
 */

package br.edu.ufcg.supervisor.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.TimeZone;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ufcg.supervisor.desktop.util.AttributeList;
import br.edu.ufcg.supervisor.model.Automaton;

import android.os.Environment;
import android.provider.Settings;

public class SupervisorInterface extends org.apache.cordova.api.CordovaPlugin {
    //public static final String TAG = "SupervisorInterface";
	
	private static String pathToFile = "";
	private static Automaton model = null;
	
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
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false if not.
     */
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		// service
		if (action.equals("java_start")) {
			JSONObject r = new JSONObject();
			if (SupervisorInterface.model==null){
				r.put("msg", "At first, you have to load a training.");
				callbackContext.error(r);
				return true;
			} else { 
				r.put("msg", SupervisorInterface.model.getNome());
				callbackContext.success(r);
				return true;
			}
		// service
		} else if (action.equals("java_load")) {
			String fileName = args.get(0).toString();
			JSONObject r = new JSONObject();
			SupervisorInterface.pathToFile = Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName;
			r.put("msg2", Environment.getExternalStorageState());
			if (Environment.getExternalStorageState()==Environment.MEDIA_MOUNTED){ //significa que nao eh o emulador android
				File file = new File(SupervisorInterface.pathToFile);// /Teste.txt
				if (!file.exists()){
					SupervisorInterface.model = null;
					r.put("msg", "File not found.");
					callbackContext.error(r);
					return true;
				} else {
					String automatoJson = TrainingLoader.readFile(SupervisorInterface.pathToFile);
					Automaton aut = new Automaton(automatoJson);
					SupervisorInterface.model = aut;
					r.put("path", SupervisorInterface.pathToFile);
					r.put("model", SupervisorInterface.model);
					r.put("msg", "File found. Model loaded.");
					callbackContext.success(r);
					return true;
				}
			} else {  //significa que EH o emulador android
				r.put("msg", "SD card not mounted. Please, choose one of the pre-defined models available.");
				SupervisorInterface.model = null;
				callbackContext.error(r);
				return true;
			}
		// service
		} else if (action.equals("java_load2")) {
			String fileName = args.get(0).toString();
			if (fileName.equals("sm")){
				SupervisorInterface.model = new Automaton("{1=1_11_12, 2=[{0=HurryUp, 2=0, 3=4}, {0=IncreaseHeartRate, 2=0, 3=1}, {0=IncreaseTemperature, 2=0, 3=9}, {0=HurryUp, 2=1, 3=5}, {0=DecreaseHeartRate, 2=1, 3=0}, {0=IncreaseHeartRate, 2=1, 3=2}, {0=IncreaseTemperature, 2=1, 3=10}, {0=DecreaseHeartRate, 2=2, 3=1}, {0=IncreaseTemperature, 2=2, 3=11}, {0=HurryUp, 2=3, 3=7}, {0=IncreaseHeartRate, 2=3, 3=4}, {0=IncreaseTemperature, 2=3, 3=12}, {0=SlowDown, 2=4, 3=0}, {0=HurryUp, 2=4, 3=8}, {0=DecreaseHeartRate, 2=4, 3=3}, {0=IncreaseHeartRate, 2=4, 3=5}, {0=IncreaseTemperature, 2=4, 3=13}, {0=SlowDown, 2=5, 3=1}, {0=DecreaseHeartRate, 2=5, 3=4}, {0=IncreaseTemperature, 2=5, 3=14}, {0=IncreaseHeartRate, 2=6, 3=7}, {0=IncreaseTemperature, 2=6, 3=15}, {0=SlowDown, 2=7, 3=3}, {0=DecreaseHeartRate, 2=7, 3=6}, {0=IncreaseHeartRate, 2=7, 3=8}, {0=IncreaseTemperature, 2=7, 3=16}, {0=SlowDown, 2=8, 3=4}, {0=DecreaseHeartRate, 2=8, 3=7}, {0=IncreaseTemperature, 2=8, 3=17}, {0=HurryUp, 2=9, 3=13}, {0=IncreaseHeartRate, 2=9, 3=10}, {0=DecreaseTemperature, 2=9, 3=0}, {0=IncreaseTemperature, 2=9, 3=18}, {0=HurryUp, 2=10, 3=14}, {0=DecreaseHeartRate, 2=10, 3=9}, {0=IncreaseHeartRate, 2=10, 3=11}, {0=DecreaseTemperature, 2=10, 3=1}, {0=IncreaseTemperature, 2=10, 3=19}, {0=DecreaseHeartRate, 2=11, 3=10}, {0=DecreaseTemperature, 2=11, 3=2}, {0=IncreaseTemperature, 2=11, 3=20}, {0=HurryUp, 2=12, 3=16}, {0=IncreaseHeartRate, 2=12, 3=13}, {0=DecreaseTemperature, 2=12, 3=3}, {0=IncreaseTemperature, 2=12, 3=21}, {0=SlowDown, 2=13, 3=9}, {0=HurryUp, 2=13, 3=17}, {0=DecreaseHeartRate, 2=13, 3=12}, {0=IncreaseHeartRate, 2=13, 3=14}, {0=DecreaseTemperature, 2=13, 3=4}, {0=IncreaseTemperature, 2=13, 3=22}, {0=SlowDown, 2=14, 3=10}, {0=DecreaseHeartRate, 2=14, 3=13}, {0=DecreaseTemperature, 2=14, 3=5}, {0=IncreaseTemperature, 2=14, 3=23}, {0=IncreaseHeartRate, 2=15, 3=16}, {0=DecreaseTemperature, 2=15, 3=6}, {0=IncreaseTemperature, 2=15, 3=24}, {0=SlowDown, 2=16, 3=12}, {0=DecreaseHeartRate, 2=16, 3=15}, {0=IncreaseHeartRate, 2=16, 3=17}, {0=DecreaseTemperature, 2=16, 3=7}, {0=IncreaseTemperature, 2=16, 3=25}, {0=SlowDown, 2=17, 3=13}, {0=DecreaseHeartRate, 2=17, 3=16}, {0=DecreaseTemperature, 2=17, 3=8}, {0=IncreaseTemperature, 2=17, 3=26}, {0=HurryUp, 2=18, 3=22}, {0=IncreaseHeartRate, 2=18, 3=19}, {0=DecreaseTemperature, 2=18, 3=9}, {0=HurryUp, 2=19, 3=23}, {0=DecreaseHeartRate, 2=19, 3=18}, {0=IncreaseHeartRate, 2=19, 3=20}, {0=DecreaseTemperature, 2=19, 3=10}, {0=DecreaseHeartRate, 2=20, 3=19}, {0=DecreaseTemperature, 2=20, 3=11}, {0=HurryUp, 2=21, 3=25}, {0=IncreaseHeartRate, 2=21, 3=22}, {0=DecreaseTemperature, 2=21, 3=12}, {0=SlowDown, 2=22, 3=18}, {0=HurryUp, 2=22, 3=26}, {0=DecreaseHeartRate, 2=22, 3=21}, {0=IncreaseHeartRate, 2=22, 3=23}, {0=DecreaseTemperature, 2=22, 3=13}, {0=SlowDown, 2=23, 3=19}, {0=DecreaseHeartRate, 2=23, 3=22}, {0=DecreaseTemperature, 2=23, 3=14}, {0=IncreaseHeartRate, 2=24, 3=25}, {0=DecreaseTemperature, 2=24, 3=15}, {0=SlowDown, 2=25, 3=21}, {0=DecreaseHeartRate, 2=25, 3=24}, {0=IncreaseHeartRate, 2=25, 3=26}, {0=DecreaseTemperature, 2=25, 3=16}, {0=SlowDown, 2=26, 3=22}, {0=DecreaseHeartRate, 2=26, 3=25}, {0=DecreaseTemperature, 2=26, 3=17}], 3=[{1=safe.slow.good, 4=3, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=tole.slow.good, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=dang.slow.good, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=safe.mode.good, 4=3, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=tole.mode.good, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=dang.mode.good, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=safe.fast.good, 4=3, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=tole.fast.good, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=dang.fast.good, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=safe.slow.high, 4=1, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=tole.slow.high, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=dang.slow.high, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=safe.mode.high, 4=1, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=tole.mode.high, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=dang.mode.high, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=safe.fast.high, 4=1, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=tole.fast.high, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=dang.fast.high, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=safe.slow.vhig, 4=2, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=tole.slow.vhig, 4=2, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=dang.slow.vhig, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=safe.mode.vhig, 4=2, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=tole.mode.vhig, 4=2, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=dang.mode.vhig, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=safe.fast.vhig, 4=2, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=tole.fast.vhig, 4=2, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=dang.fast.vhig, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}], 4=[3]}");
			} else if (fileName.equals("shm")){
				SupervisorInterface.model = new Automaton("{1=1_11_12, 2=[{0=HurryUp, 2=0, 3=4}, {0=IncreaseHeartRate, 2=0, 3=1}, {0=IncreaseTemperature, 2=0, 3=9}, {0=HurryUp, 2=1, 3=5}, {0=DecreaseHeartRate, 2=1, 3=0}, {0=IncreaseHeartRate, 2=1, 3=2}, {0=IncreaseTemperature, 2=1, 3=10}, {0=DecreaseHeartRate, 2=2, 3=1}, {0=IncreaseTemperature, 2=2, 3=11}, {0=HurryUp, 2=3, 3=7}, {0=IncreaseHeartRate, 2=3, 3=4}, {0=IncreaseTemperature, 2=3, 3=12}, {0=SlowDown, 2=4, 3=0}, {0=HurryUp, 2=4, 3=8}, {0=DecreaseHeartRate, 2=4, 3=3}, {0=IncreaseHeartRate, 2=4, 3=5}, {0=IncreaseTemperature, 2=4, 3=13}, {0=SlowDown, 2=5, 3=1}, {0=DecreaseHeartRate, 2=5, 3=4}, {0=IncreaseTemperature, 2=5, 3=14}, {0=IncreaseHeartRate, 2=6, 3=7}, {0=IncreaseTemperature, 2=6, 3=15}, {0=SlowDown, 2=7, 3=3}, {0=DecreaseHeartRate, 2=7, 3=6}, {0=IncreaseHeartRate, 2=7, 3=8}, {0=IncreaseTemperature, 2=7, 3=16}, {0=SlowDown, 2=8, 3=4}, {0=DecreaseHeartRate, 2=8, 3=7}, {0=IncreaseTemperature, 2=8, 3=17}, {0=HurryUp, 2=9, 3=13}, {0=IncreaseHeartRate, 2=9, 3=10}, {0=DecreaseTemperature, 2=9, 3=0}, {0=IncreaseTemperature, 2=9, 3=18}, {0=HurryUp, 2=10, 3=14}, {0=DecreaseHeartRate, 2=10, 3=9}, {0=IncreaseHeartRate, 2=10, 3=11}, {0=DecreaseTemperature, 2=10, 3=1}, {0=IncreaseTemperature, 2=10, 3=19}, {0=DecreaseHeartRate, 2=11, 3=10}, {0=DecreaseTemperature, 2=11, 3=2}, {0=IncreaseTemperature, 2=11, 3=20}, {0=HurryUp, 2=12, 3=16}, {0=IncreaseHeartRate, 2=12, 3=13}, {0=DecreaseTemperature, 2=12, 3=3}, {0=IncreaseTemperature, 2=12, 3=21}, {0=SlowDown, 2=13, 3=9}, {0=HurryUp, 2=13, 3=17}, {0=DecreaseHeartRate, 2=13, 3=12}, {0=IncreaseHeartRate, 2=13, 3=14}, {0=DecreaseTemperature, 2=13, 3=4}, {0=IncreaseTemperature, 2=13, 3=22}, {0=SlowDown, 2=14, 3=10}, {0=DecreaseHeartRate, 2=14, 3=13}, {0=DecreaseTemperature, 2=14, 3=5}, {0=IncreaseTemperature, 2=14, 3=23}, {0=IncreaseHeartRate, 2=15, 3=16}, {0=DecreaseTemperature, 2=15, 3=6}, {0=IncreaseTemperature, 2=15, 3=24}, {0=SlowDown, 2=16, 3=12}, {0=DecreaseHeartRate, 2=16, 3=15}, {0=IncreaseHeartRate, 2=16, 3=17}, {0=DecreaseTemperature, 2=16, 3=7}, {0=IncreaseTemperature, 2=16, 3=25}, {0=SlowDown, 2=17, 3=13}, {0=DecreaseHeartRate, 2=17, 3=16}, {0=DecreaseTemperature, 2=17, 3=8}, {0=IncreaseTemperature, 2=17, 3=26}, {0=HurryUp, 2=18, 3=22}, {0=IncreaseHeartRate, 2=18, 3=19}, {0=DecreaseTemperature, 2=18, 3=9}, {0=HurryUp, 2=19, 3=23}, {0=DecreaseHeartRate, 2=19, 3=18}, {0=IncreaseHeartRate, 2=19, 3=20}, {0=DecreaseTemperature, 2=19, 3=10}, {0=DecreaseHeartRate, 2=20, 3=19}, {0=DecreaseTemperature, 2=20, 3=11}, {0=HurryUp, 2=21, 3=25}, {0=IncreaseHeartRate, 2=21, 3=22}, {0=DecreaseTemperature, 2=21, 3=12}, {0=SlowDown, 2=22, 3=18}, {0=HurryUp, 2=22, 3=26}, {0=DecreaseHeartRate, 2=22, 3=21}, {0=IncreaseHeartRate, 2=22, 3=23}, {0=DecreaseTemperature, 2=22, 3=13}, {0=SlowDown, 2=23, 3=19}, {0=DecreaseHeartRate, 2=23, 3=22}, {0=DecreaseTemperature, 2=23, 3=14}, {0=IncreaseHeartRate, 2=24, 3=25}, {0=DecreaseTemperature, 2=24, 3=15}, {0=SlowDown, 2=25, 3=21}, {0=DecreaseHeartRate, 2=25, 3=24}, {0=IncreaseHeartRate, 2=25, 3=26}, {0=DecreaseTemperature, 2=25, 3=16}, {0=SlowDown, 2=26, 3=22}, {0=DecreaseHeartRate, 2=26, 3=25}, {0=DecreaseTemperature, 2=26, 3=17}], 3=[{1=safe.slow.good, 4=3, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=tole.slow.good, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=dang.slow.good, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=safe.mode.good, 4=3, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=tole.mode.good, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=dang.mode.good, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=safe.fast.good, 4=3, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=tole.fast.good, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=dang.fast.good, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=safe.slow.high, 4=1, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=tole.slow.high, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=dang.slow.high, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=safe.mode.high, 4=1, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=tole.mode.high, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=dang.mode.high, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=safe.fast.high, 4=1, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=tole.fast.high, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=dang.fast.high, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=safe.slow.vhig, 4=2, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=tole.slow.vhig, 4=2, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=dang.slow.vhig, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=safe.mode.vhig, 4=2, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=tole.mode.vhig, 4=2, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=dang.mode.vhig, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=safe.fast.vhig, 4=2, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=tole.fast.vhig, 4=2, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=dang.fast.vhig, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}], 4=[3]}");
			} else if (fileName.equals("shtm")){
				SupervisorInterface.model = new Automaton("{1=1_11_12, 2=[{0=HurryUp, 2=0, 3=4}, {0=IncreaseHeartRate, 2=0, 3=1}, {0=IncreaseTemperature, 2=0, 3=9}, {0=HurryUp, 2=1, 3=5}, {0=DecreaseHeartRate, 2=1, 3=0}, {0=IncreaseHeartRate, 2=1, 3=2}, {0=IncreaseTemperature, 2=1, 3=10}, {0=DecreaseHeartRate, 2=2, 3=1}, {0=IncreaseTemperature, 2=2, 3=11}, {0=HurryUp, 2=3, 3=7}, {0=IncreaseHeartRate, 2=3, 3=4}, {0=IncreaseTemperature, 2=3, 3=12}, {0=SlowDown, 2=4, 3=0}, {0=HurryUp, 2=4, 3=8}, {0=DecreaseHeartRate, 2=4, 3=3}, {0=IncreaseHeartRate, 2=4, 3=5}, {0=IncreaseTemperature, 2=4, 3=13}, {0=SlowDown, 2=5, 3=1}, {0=DecreaseHeartRate, 2=5, 3=4}, {0=IncreaseTemperature, 2=5, 3=14}, {0=IncreaseHeartRate, 2=6, 3=7}, {0=IncreaseTemperature, 2=6, 3=15}, {0=SlowDown, 2=7, 3=3}, {0=DecreaseHeartRate, 2=7, 3=6}, {0=IncreaseHeartRate, 2=7, 3=8}, {0=IncreaseTemperature, 2=7, 3=16}, {0=SlowDown, 2=8, 3=4}, {0=DecreaseHeartRate, 2=8, 3=7}, {0=IncreaseTemperature, 2=8, 3=17}, {0=HurryUp, 2=9, 3=13}, {0=IncreaseHeartRate, 2=9, 3=10}, {0=DecreaseTemperature, 2=9, 3=0}, {0=IncreaseTemperature, 2=9, 3=18}, {0=HurryUp, 2=10, 3=14}, {0=DecreaseHeartRate, 2=10, 3=9}, {0=IncreaseHeartRate, 2=10, 3=11}, {0=DecreaseTemperature, 2=10, 3=1}, {0=IncreaseTemperature, 2=10, 3=19}, {0=DecreaseHeartRate, 2=11, 3=10}, {0=DecreaseTemperature, 2=11, 3=2}, {0=IncreaseTemperature, 2=11, 3=20}, {0=HurryUp, 2=12, 3=16}, {0=IncreaseHeartRate, 2=12, 3=13}, {0=DecreaseTemperature, 2=12, 3=3}, {0=IncreaseTemperature, 2=12, 3=21}, {0=SlowDown, 2=13, 3=9}, {0=HurryUp, 2=13, 3=17}, {0=DecreaseHeartRate, 2=13, 3=12}, {0=IncreaseHeartRate, 2=13, 3=14}, {0=DecreaseTemperature, 2=13, 3=4}, {0=IncreaseTemperature, 2=13, 3=22}, {0=SlowDown, 2=14, 3=10}, {0=DecreaseHeartRate, 2=14, 3=13}, {0=DecreaseTemperature, 2=14, 3=5}, {0=IncreaseTemperature, 2=14, 3=23}, {0=IncreaseHeartRate, 2=15, 3=16}, {0=DecreaseTemperature, 2=15, 3=6}, {0=IncreaseTemperature, 2=15, 3=24}, {0=SlowDown, 2=16, 3=12}, {0=DecreaseHeartRate, 2=16, 3=15}, {0=IncreaseHeartRate, 2=16, 3=17}, {0=DecreaseTemperature, 2=16, 3=7}, {0=IncreaseTemperature, 2=16, 3=25}, {0=SlowDown, 2=17, 3=13}, {0=DecreaseHeartRate, 2=17, 3=16}, {0=DecreaseTemperature, 2=17, 3=8}, {0=IncreaseTemperature, 2=17, 3=26}, {0=HurryUp, 2=18, 3=22}, {0=IncreaseHeartRate, 2=18, 3=19}, {0=DecreaseTemperature, 2=18, 3=9}, {0=HurryUp, 2=19, 3=23}, {0=DecreaseHeartRate, 2=19, 3=18}, {0=IncreaseHeartRate, 2=19, 3=20}, {0=DecreaseTemperature, 2=19, 3=10}, {0=DecreaseHeartRate, 2=20, 3=19}, {0=DecreaseTemperature, 2=20, 3=11}, {0=HurryUp, 2=21, 3=25}, {0=IncreaseHeartRate, 2=21, 3=22}, {0=DecreaseTemperature, 2=21, 3=12}, {0=SlowDown, 2=22, 3=18}, {0=HurryUp, 2=22, 3=26}, {0=DecreaseHeartRate, 2=22, 3=21}, {0=IncreaseHeartRate, 2=22, 3=23}, {0=DecreaseTemperature, 2=22, 3=13}, {0=SlowDown, 2=23, 3=19}, {0=DecreaseHeartRate, 2=23, 3=22}, {0=DecreaseTemperature, 2=23, 3=14}, {0=IncreaseHeartRate, 2=24, 3=25}, {0=DecreaseTemperature, 2=24, 3=15}, {0=SlowDown, 2=25, 3=21}, {0=DecreaseHeartRate, 2=25, 3=24}, {0=IncreaseHeartRate, 2=25, 3=26}, {0=DecreaseTemperature, 2=25, 3=16}, {0=SlowDown, 2=26, 3=22}, {0=DecreaseHeartRate, 2=26, 3=25}, {0=DecreaseTemperature, 2=26, 3=17}], 3=[{1=safe.slow.good, 4=3, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=tole.slow.good, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=dang.slow.good, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=safe.mode.good, 4=3, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=tole.mode.good, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=dang.mode.good, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=safe.fast.good, 4=3, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=tole.fast.good, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=dang.fast.good, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=28.0, 2=19.0, 3=0, 4=0}]}, {1=safe.slow.high, 4=1, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=tole.slow.high, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=dang.slow.high, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=safe.mode.high, 4=1, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=tole.mode.high, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=dang.mode.high, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=safe.fast.high, 4=1, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=tole.fast.high, 4=1, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=dang.fast.high, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=30.0, 2=28.0, 3=0, 4=1}]}, {1=safe.slow.vhig, 4=2, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=tole.slow.vhig, 4=2, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=dang.slow.vhig, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=5.0, 2=0.0, 3=0, 4=0}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=safe.mode.vhig, 4=2, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=tole.mode.vhig, 4=2, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=dang.mode.vhig, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=9.0, 2=5.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=safe.fast.vhig, 4=2, 5=[{0=1, 1=100.0, 2=60.0, 3=0, 4=0}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=tole.fast.vhig, 4=2, 5=[{0=1, 1=120.0, 2=100.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}, {1=dang.fast.vhig, 4=2, 5=[{0=1, 1=140.0, 2=120.0, 3=0, 4=1}, {0=11, 1=14.0, 2=9.0, 3=0, 4=1}, {0=12, 1=40.0, 2=30.0, 3=0, 4=1}]}], 4=[3]}");
			} else {
				SupervisorInterface.model = null;
			}
			callbackContext.success();
			return true;
		// service
		} else if (action.equals("get_model")) {
			JSONObject r = new JSONObject();
			LoadedModel.setModelo(SupervisorInterface.model);
			int[] arrayIds = LoadedModel.getIdVariaveisMonitoradas();
			ArrayList<String> names = LoadedModel.getNomesVariaveisMonitoradas();
			r.put("ids", arrayIds);
			r.put("names", names);
			callbackContext.success(r);
			return true;
	    } else { return false; }
    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
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

    public String getModel() {
        String model = android.os.Build.MODEL;
        return model;
    }

    public String getProductName() {
        String productname = android.os.Build.PRODUCT;
        return productname;
    }

    /**
     * Get the OS version.
     *
     * @return
     */
    public String getOSVersion() {
        String osversion = android.os.Build.VERSION.RELEASE;
        return osversion;
    }

    public String getSDKVersion() {
        @SuppressWarnings("deprecation")
        String sdkversion = android.os.Build.VERSION.SDK;
        return sdkversion;
    }

    public String getTimeZoneID() {
        TimeZone tz = TimeZone.getDefault();
        return (tz.getID());
    }
}
