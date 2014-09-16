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
 *  
 *  Contact: el7hon at gmail dot com
 */
package br.edu.ufcg.supervisor.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.edu.ufcg.supervisor.model.Automaton;
import android.os.Environment;
import android.util.Log;

/**
 *  
 * @author Elthon Oliveira
 *
 */
public class TrainingLoader {
	public static String model1 = "{1=11_, 2=[{0=Hurry-up., 2=0, 3=1}, {0=Slow_down., 2=1, 3=0}, {0=Hurry-up., 2=1, 3=2}, {0=Slow_down., 2=2, 3=1}], 3=[{1=slow, 4=0, 5=[{0=11, 1=6.0, 2=0.0, 3=0, 4=1}]}, {1=moderable, 4=2, 5=[{0=11, 1=13.0, 2=6.0, 3=0, 4=1}]}, {1=fast, 4=1, 5=[{0=11, 1=20.0, 2=13.0, 3=0, 4=1}]}], 4=[1]}";
	public static String model2 = "{1=1_12_, 2=[{0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=0, 3=1}, {0=Increase_temperature., 2=0, 3=3}, {0=Decrease_heart-rate.(by_slowing_down), 2=1, 3=0}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=1, 3=2}, {0=Increase_temperature., 2=1, 3=4}, {0=Decrease_heart-rate.(by_slowing_down), 2=2, 3=1}, {0=Increase_temperature., 2=2, 3=5}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=3, 3=4}, {0=Decrease_temperature.(or_hydrate!), 2=3, 3=0}, {0=Increase_temperature., 2=3, 3=6}, {0=Decrease_heart-rate.(by_slowing_down), 2=4, 3=3}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=4, 3=5}, {0=Decrease_temperature.(or_hydrate!), 2=4, 3=1}, {0=Increase_temperature., 2=4, 3=7}, {0=Decrease_heart-rate.(by_slowing_down), 2=5, 3=4}, {0=Decrease_temperature.(or_hydrate!), 2=5, 3=2}, {0=Increase_temperature., 2=5, 3=8}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=6, 3=7}, {0=Decrease_temperature.(or_hydrate!), 2=6, 3=3}, {0=Decrease_heart-rate.(by_slowing_down), 2=7, 3=6}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=7, 3=8}, {0=Decrease_temperature.(or_hydrate!), 2=7, 3=4}, {0=Decrease_heart-rate.(by_slowing_down), 2=8, 3=7}, {0=Decrease_temperature.(or_hydrate!), 2=8, 3=5}], 3=[{1=safe.good, 4=2, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=12, 1=29.0, 2=10.0, 3=0, 4=0}]}, {1=tolerable.good, 4=0, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=12, 1=29.0, 2=10.0, 3=0, 4=0}]}, {1=dangerous.good, 4=1, 5=[{0=1, 1=200.0, 2=130.0, 3=0, 4=1}, {0=12, 1=29.0, 2=10.0, 3=0, 4=0}]}, {1=safe.high, 4=1, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=12, 1=35.0, 2=29.0, 3=0, 4=1}]}, {1=tolerable.high, 4=1, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=12, 1=35.0, 2=29.0, 3=0, 4=1}]}, {1=dangerous.high, 4=1, 5=[{0=1, 1=200.0, 2=130.0, 3=0, 4=1}, {0=12, 1=35.0, 2=29.0, 3=0, 4=1}]}, {1=safe.vhig, 4=1, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=12, 1=50.0, 2=35.0, 3=0, 4=1}]}, {1=tolerable.vhig, 4=1, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=12, 1=50.0, 2=35.0, 3=0, 4=1}]}, {1=dangerous.vhig, 4=1, 5=[{0=1, 1=200.0, 2=130.0, 3=0, 4=1}, {0=12, 1=50.0, 2=35.0, 3=0, 4=1}]}], 4=[0]}";
	public static String model3 = "{1=1_11_12_, 2=[{0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=0, 3=1}, {0=Hurry-up., 2=0, 3=3}, {0=Increase_temperature., 2=0, 3=9}, {0=Decrease_heart-rate.(by_slowing_down), 2=1, 3=0}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=1, 3=2}, {0=Hurry-up., 2=1, 3=4}, {0=Increase_temperature., 2=1, 3=10}, {0=Decrease_heart-rate.(by_slowing_down), 2=2, 3=1}, {0=Hurry-up., 2=2, 3=5}, {0=Increase_temperature., 2=2, 3=11}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=3, 3=4}, {0=Slow_down., 2=3, 3=0}, {0=Hurry-up., 2=3, 3=6}, {0=Increase_temperature., 2=3, 3=12}, {0=Decrease_heart-rate.(by_slowing_down), 2=4, 3=3}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=4, 3=5}, {0=Slow_down., 2=4, 3=1}, {0=Hurry-up., 2=4, 3=7}, {0=Increase_temperature., 2=4, 3=13}, {0=Decrease_heart-rate.(by_slowing_down), 2=5, 3=4}, {0=Slow_down., 2=5, 3=2}, {0=Hurry-up., 2=5, 3=8}, {0=Increase_temperature., 2=5, 3=14}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=6, 3=7}, {0=Slow_down., 2=6, 3=3}, {0=Increase_temperature., 2=6, 3=15}, {0=Decrease_heart-rate.(by_slowing_down), 2=7, 3=6}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=7, 3=8}, {0=Slow_down., 2=7, 3=4}, {0=Increase_temperature., 2=7, 3=16}, {0=Decrease_heart-rate.(by_slowing_down), 2=8, 3=7}, {0=Slow_down., 2=8, 3=5}, {0=Increase_temperature., 2=8, 3=17}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=9, 3=10}, {0=Hurry-up., 2=9, 3=12}, {0=Decrease_temperature.(or_hydrate!), 2=9, 3=0}, {0=Increase_temperature., 2=9, 3=18}, {0=Decrease_heart-rate.(by_slowing_down), 2=10, 3=9}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=10, 3=11}, {0=Hurry-up., 2=10, 3=13}, {0=Decrease_temperature.(or_hydrate!), 2=10, 3=1}, {0=Increase_temperature., 2=10, 3=19}, {0=Decrease_heart-rate.(by_slowing_down), 2=11, 3=10}, {0=Hurry-up., 2=11, 3=14}, {0=Decrease_temperature.(or_hydrate!), 2=11, 3=2}, {0=Increase_temperature., 2=11, 3=20}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=12, 3=13}, {0=Slow_down., 2=12, 3=9}, {0=Hurry-up., 2=12, 3=15}, {0=Decrease_temperature.(or_hydrate!), 2=12, 3=3}, {0=Increase_temperature., 2=12, 3=21}, {0=Decrease_heart-rate.(by_slowing_down), 2=13, 3=12}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=13, 3=14}, {0=Slow_down., 2=13, 3=10}, {0=Hurry-up., 2=13, 3=16}, {0=Decrease_temperature.(or_hydrate!), 2=13, 3=4}, {0=Increase_temperature., 2=13, 3=22}, {0=Decrease_heart-rate.(by_slowing_down), 2=14, 3=13}, {0=Slow_down., 2=14, 3=11}, {0=Hurry-up., 2=14, 3=17}, {0=Decrease_temperature.(or_hydrate!), 2=14, 3=5}, {0=Increase_temperature., 2=14, 3=23}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=15, 3=16}, {0=Slow_down., 2=15, 3=12}, {0=Decrease_temperature.(or_hydrate!), 2=15, 3=6}, {0=Increase_temperature., 2=15, 3=24}, {0=Decrease_heart-rate.(by_slowing_down), 2=16, 3=15}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=16, 3=17}, {0=Slow_down., 2=16, 3=13}, {0=Decrease_temperature.(or_hydrate!), 2=16, 3=7}, {0=Increase_temperature., 2=16, 3=25}, {0=Decrease_heart-rate.(by_slowing_down), 2=17, 3=16}, {0=Slow_down., 2=17, 3=14}, {0=Decrease_temperature.(or_hydrate!), 2=17, 3=8}, {0=Increase_temperature., 2=17, 3=26}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=18, 3=19}, {0=Hurry-up., 2=18, 3=21}, {0=Decrease_temperature.(or_hydrate!), 2=18, 3=9}, {0=Decrease_heart-rate.(by_slowing_down), 2=19, 3=18}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=19, 3=20}, {0=Hurry-up., 2=19, 3=22}, {0=Decrease_temperature.(or_hydrate!), 2=19, 3=10}, {0=Decrease_heart-rate.(by_slowing_down), 2=20, 3=19}, {0=Hurry-up., 2=20, 3=23}, {0=Decrease_temperature.(or_hydrate!), 2=20, 3=11}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=21, 3=22}, {0=Slow_down., 2=21, 3=18}, {0=Hurry-up., 2=21, 3=24}, {0=Decrease_temperature.(or_hydrate!), 2=21, 3=12}, {0=Decrease_heart-rate.(by_slowing_down), 2=22, 3=21}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=22, 3=23}, {0=Slow_down., 2=22, 3=19}, {0=Hurry-up., 2=22, 3=25}, {0=Decrease_temperature.(or_hydrate!), 2=22, 3=13}, {0=Decrease_heart-rate.(by_slowing_down), 2=23, 3=22}, {0=Slow_down., 2=23, 3=20}, {0=Hurry-up., 2=23, 3=26}, {0=Decrease_temperature.(or_hydrate!), 2=23, 3=14}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=24, 3=25}, {0=Slow_down., 2=24, 3=21}, {0=Decrease_temperature.(or_hydrate!), 2=24, 3=15}, {0=Decrease_heart-rate.(by_slowing_down), 2=25, 3=24}, {0=Increase_heart-rate.(by_going_uphill_or_accelerating), 2=25, 3=26}, {0=Slow_down., 2=25, 3=22}, {0=Decrease_temperature.(or_hydrate!), 2=25, 3=16}, {0=Decrease_heart-rate.(by_slowing_down), 2=26, 3=25}, {0=Slow_down., 2=26, 3=23}, {0=Decrease_temperature.(or_hydrate!), 2=26, 3=17}], 3=[{1=safe.slow.good, 4=0, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=11, 1=6.0, 2=0.0, 3=0, 4=1}, {0=12, 1=29.0, 2=10.0, 3=0, 4=0}]}, {1=tolerable.slow.good, 4=0, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=11, 1=6.0, 2=0.0, 3=0, 4=1}, {0=12, 1=29.0, 2=10.0, 3=0, 4=0}]}, {1=dangerous.slow.good, 4=1, 5=[{0=1, 1=200.0, 2=130.0, 3=0, 4=1}, {0=11, 1=6.0, 2=0.0, 3=0, 4=1}, {0=12, 1=29.0, 2=10.0, 3=0, 4=0}]}, {1=safe.moderable.good, 4=2, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=11, 1=13.0, 2=6.0, 3=0, 4=1}, {0=12, 1=29.0, 2=10.0, 3=0, 4=0}]}, {1=tolerable.moderable.good, 4=0, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=11, 1=13.0, 2=6.0, 3=0, 4=1}, {0=12, 1=29.0, 2=10.0, 3=0, 4=0}]}, {1=dangerous.moderable.good, 4=1, 5=[{0=1, 1=200.0, 2=130.0, 3=0, 4=1}, {0=11, 1=13.0, 2=6.0, 3=0, 4=1}, {0=12, 1=29.0, 2=10.0, 3=0, 4=0}]}, {1=safe.fast.good, 4=1, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=11, 1=20.0, 2=13.0, 3=0, 4=1}, {0=12, 1=29.0, 2=10.0, 3=0, 4=0}]}, {1=tolerable.fast.good, 4=1, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=11, 1=20.0, 2=13.0, 3=0, 4=1}, {0=12, 1=29.0, 2=10.0, 3=0, 4=0}]}, {1=dangerous.fast.good, 4=1, 5=[{0=1, 1=200.0, 2=130.0, 3=0, 4=1}, {0=11, 1=20.0, 2=13.0, 3=0, 4=1}, {0=12, 1=29.0, 2=10.0, 3=0, 4=0}]}, {1=safe.slow.high, 4=1, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=11, 1=6.0, 2=0.0, 3=0, 4=1}, {0=12, 1=35.0, 2=29.0, 3=0, 4=1}]}, {1=tolerable.slow.high, 4=1, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=11, 1=6.0, 2=0.0, 3=0, 4=1}, {0=12, 1=35.0, 2=29.0, 3=0, 4=1}]}, {1=dangerous.slow.high, 4=1, 5=[{0=1, 1=200.0, 2=130.0, 3=0, 4=1}, {0=11, 1=6.0, 2=0.0, 3=0, 4=1}, {0=12, 1=35.0, 2=29.0, 3=0, 4=1}]}, {1=safe.moderable.high, 4=1, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=11, 1=13.0, 2=6.0, 3=0, 4=1}, {0=12, 1=35.0, 2=29.0, 3=0, 4=1}]}, {1=tolerable.moderable.high, 4=1, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=11, 1=13.0, 2=6.0, 3=0, 4=1}, {0=12, 1=35.0, 2=29.0, 3=0, 4=1}]}, {1=dangerous.moderable.high, 4=1, 5=[{0=1, 1=200.0, 2=130.0, 3=0, 4=1}, {0=11, 1=13.0, 2=6.0, 3=0, 4=1}, {0=12, 1=35.0, 2=29.0, 3=0, 4=1}]}, {1=safe.fast.high, 4=1, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=11, 1=20.0, 2=13.0, 3=0, 4=1}, {0=12, 1=35.0, 2=29.0, 3=0, 4=1}]}, {1=tolerable.fast.high, 4=1, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=11, 1=20.0, 2=13.0, 3=0, 4=1}, {0=12, 1=35.0, 2=29.0, 3=0, 4=1}]}, {1=dangerous.fast.high, 4=1, 5=[{0=1, 1=200.0, 2=130.0, 3=0, 4=1}, {0=11, 1=20.0, 2=13.0, 3=0, 4=1}, {0=12, 1=35.0, 2=29.0, 3=0, 4=1}]}, {1=safe.slow.vhig, 4=1, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=11, 1=6.0, 2=0.0, 3=0, 4=1}, {0=12, 1=50.0, 2=35.0, 3=0, 4=1}]}, {1=tolerable.slow.vhig, 4=1, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=11, 1=6.0, 2=0.0, 3=0, 4=1}, {0=12, 1=50.0, 2=35.0, 3=0, 4=1}]}, {1=dangerous.slow.vhig, 4=1, 5=[{0=1, 1=200.0, 2=130.0, 3=0, 4=1}, {0=11, 1=6.0, 2=0.0, 3=0, 4=1}, {0=12, 1=50.0, 2=35.0, 3=0, 4=1}]}, {1=safe.moderable.vhig, 4=1, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=11, 1=13.0, 2=6.0, 3=0, 4=1}, {0=12, 1=50.0, 2=35.0, 3=0, 4=1}]}, {1=tolerable.moderable.vhig, 4=1, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=11, 1=13.0, 2=6.0, 3=0, 4=1}, {0=12, 1=50.0, 2=35.0, 3=0, 4=1}]}, {1=dangerous.moderable.vhig, 4=1, 5=[{0=1, 1=200.0, 2=130.0, 3=0, 4=1}, {0=11, 1=13.0, 2=6.0, 3=0, 4=1}, {0=12, 1=50.0, 2=35.0, 3=0, 4=1}]}, {1=safe.fast.vhig, 4=1, 5=[{0=1, 1=100.0, 2=50.0, 3=0, 4=0}, {0=11, 1=20.0, 2=13.0, 3=0, 4=1}, {0=12, 1=50.0, 2=35.0, 3=0, 4=1}]}, {1=tolerable.fast.vhig, 4=1, 5=[{0=1, 1=130.0, 2=100.0, 3=0, 4=1}, {0=11, 1=20.0, 2=13.0, 3=0, 4=1}, {0=12, 1=50.0, 2=35.0, 3=0, 4=1}]}, {1=dangerous.fast.vhig, 4=1, 5=[{0=1, 1=200.0, 2=130.0, 3=0, 4=1}, {0=11, 1=20.0, 2=13.0, 3=0, 4=1}, {0=12, 1=50.0, 2=35.0, 3=0, 4=1}]}], 4=[3]}";
	
	public static void setDefaultModel() {
		String str = model3;
		Automaton modelo = new Automaton(str);
		LoadedModel.setModel(modelo);
	}
	
	public static Automaton getDefaultModel() {
		return new Automaton(model3);
	}
	
	public static String getFilePath(String arquivo) throws FileNotFoundException{
		File directory = Environment.getExternalStorageDirectory();
		File file = new File(directory + "/Download/"+arquivo);// /Teste.txt
		return file.getAbsolutePath();
	}
	
	/*Esse método lê um arquivo .sup da pasta Download*/
	public static String readFile(String absolutePath) throws IOException {
		File file = new File(absolutePath);
		if (!file.exists()) { throw new RuntimeException("File not found"); }
		Log.e("Testing", "Starting to read");
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) { builder.append(line); }
		} catch (Exception e) { e.printStackTrace();}
		finally {
			if (reader != null) {
				try { reader.close();} 
				catch (IOException e) { e.printStackTrace(); }
			}
		}
		return builder.toString();
	}
    public static String ReadFile(String absolutePath) throws FileNotFoundException, IOException{
        File textfile = new File(absolutePath);
        FileInputStream input = new FileInputStream(textfile);
        byte[] buffer = new byte[(int)textfile.length()];
        input.read(buffer);            
        input.close();
        return new String(buffer);
    }
}