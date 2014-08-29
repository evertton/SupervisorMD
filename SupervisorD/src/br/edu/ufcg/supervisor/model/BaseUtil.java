/**
 * Copyright 2013-2014 Marcos Ferreira and Elthon Oliveira
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
package br.edu.ufcg.supervisor.model;
//TODO: apagar em algum momento
/**
 * Classe contendo métodos auxiliares básicos utilizados pela aplicação. 
 * @author Marcos José
 *
 */
public class BaseUtil {
	private static final String c0 = "'0¨¨";
	private static final String d0 = "°";
	
	private static final String c1 = "{3=[{5=[{3=";
	private static final String d1 = "''";

	private static final String c2 = ",2=";
	private static final String d2 = "¨¨";

	private static final String c3 = ".0,1=";
	private static final String d3 = "'¨";

	private static final String c4 = ".0,0=";
	private static final String d4 = "¨'";

	private static final String c5_0 = ",4=[";
	private static final String d5_0 = "/";

	private static final String c5 = ",4=";
	private static final String d5 = "´";


	private static final String c6 = "},{3=";
	private static final String d6 = "'";

	private static final String c7 = "}]";
	private static final String d7 = "-";

	private static final String c8 = "},{5=[{3=";
	private static final String d8 = "`";

	private static final String c9 = "]}";
	private static final String d9 = "|";

	private static final String c10 = ",0=";
	private static final String d10 = " ";
	
	private static final String c11 = "[{3=";
			private static final String d11 = "^";

	public static String complicar(String json){

		String jsonCodi = json.replaceAll(" ","");
		//				.replace("=", " ")//1
		//				.replace(",","-")//2
		//				.replace("{", "'.")//3 erro
		//				.replace("}", "´")//4
		//				.replace("[", ".'")//5 erro
		//				.replace("]", "`");//6
		return jsonCodi;
	}

	public static String descomplicar(String json){
		String jsonDesc = json;
		//				.replace("`","]")//6
		//				.replace( ".'","[")//5 erro
		//				.replace("´","}" )//4
		//				.replace("'.", "{")//3 erro
		//				.replace("-",",")//2
		//				.replace(" ","=");

		return jsonDesc;
	}


	//-----------------------------------------------
	public static String complica1(String json){

		String jsonCodi = json.replace(" ","")
				.replace(c1, d1)//1
				.replace(c2, d2)//2
				.replace(c3, d3)//3 erro
				.replace(c4, d4)//4
				.replace(c5_0, d5_0)
				.replace(c5, d5)//5 erro
				.replace(c6, d6)//6
				.replace(c7, d7)
				.replace(c8, d8)
				.replace(c9, d9)
				.replace(c10, d10)
				.replace(c11, d11)
				.replace(c0, d0);

		return jsonCodi;
	}

	public static String descomplica1(String json){
		String jsonDesc = json
				.replace(d0, c0)
				.replace(d1, c1)
				.replace(d2, c2)
				.replace(d3, c3)
				.replace(d4, c4)
				.replace(d5, c5)
				.replace(d5_0, c5_0)
				.replace(d6, c6)
				.replace(d7, c7)
				.replace(d8, c8)
				.replace(d9, c9)
				.replace(d10, c10)
				.replace(d11, c11);

		return jsonDesc;
	}
	
	public static void main(String[] args) {
		String s = descomplica1("'°60'¨100¨'1´0°0'¨5¨'11´0°19'¨28¨'12´0-´1`0¨¨100'¨120¨'1´1°0'¨5¨'11´0°19'¨28¨'12´0-´3`0¨¨120'¨140¨'1´1°0'¨5¨'11´0°19'¨28¨'12´0-´1`0¨¨60'¨100¨'1´0°5'¨9¨'11´1°19'¨28¨'12´0-´1`0¨¨100'¨120¨'1´1°5'¨9¨'11´1°19'¨28¨'12´0-´3`0¨¨120'¨140¨'1´1°5'¨9¨'11´1°19'¨28¨'12´0-´1`0¨¨60'¨100¨'1´0°9'¨14¨'11´1°19'¨28¨'12´0-´1`0¨¨100'¨120¨'1´1°9'¨14¨'11´1°19'¨28¨'12´0-´3`0¨¨120'¨140¨'1´1°9'¨14¨'11´1°19'¨28¨'12´0-´1`0¨¨60'¨100¨'1´0°0'¨5¨'11´0°28'¨30¨'12´1-´1`0¨¨100'¨120¨'1´1°0'¨5¨'11´0°28'¨30¨'12´1-´2`0¨¨120'¨140¨'1´1°0'¨5¨'11´0°28'¨30¨'12´1-´2`0¨¨60'¨100¨'1´0°5'¨9¨'11´1°28'¨30¨'12´1-´1`0¨¨100'¨120¨'1´1°5'¨9¨'11´1°28'¨30¨'12´1-´2`0¨¨120'¨140¨'1´1°5'¨9¨'11´1°28'¨30¨'12´1-´2`0¨¨60'¨100¨'1´0°9'¨14¨'11´1°28'¨30¨'12´1-´1`0¨¨100'¨120¨'1´1°9'¨14¨'11´1°28'¨30¨'12´1-´2`0¨¨120'¨140¨'1´1°9'¨14¨'11´1°28'¨30¨'12´1-´2`0¨¨60'¨100¨'1´0°0'¨5¨'11´0°30'¨40¨'12´1-´2`0¨¨100'¨120¨'1´1°0'¨5¨'11´0°30'¨40¨'12´1-´2`0¨¨120'¨140¨'1´1°0'¨5¨'11´0°30'¨40¨'12´1-´2`0¨¨60'¨100¨'1´0°5'¨9¨'11´1°30'¨40¨'12´1-´2`0¨¨100'¨120¨'1´1°5'¨9¨'11´1°30'¨40¨'12´1-´2`0¨¨120'¨140¨'1´1°5'¨9¨'11´1°30'¨40¨'12´1-´2`0¨¨60'¨100¨'1´0°9'¨14¨'11´1°30'¨40¨'12´1-´2`0¨¨100'¨120¨'1´1°9'¨14¨'11´1°30'¨40¨'12´1-´2`0¨¨120'¨140¨'1´1°9'¨14¨'11´1°30'¨40¨'12´1-´2-¨¨^4¨¨0 HurryUp'1¨¨0 IncreaseHeartRate'9¨¨0 IncreaseTemperature'5¨¨1 HurryUp°1 DecreaseHeartRate'2¨¨1 IncreaseHeartRate'10¨¨1 IncreaseTemperature'1¨¨2 DecreaseHeartRate'11¨¨2 IncreaseTemperature'7¨¨3 HurryUp'4¨¨3 IncreaseHeartRate'12¨¨3 IncreaseTemperature°4 SlowDown'8¨¨4 HurryUp'3¨¨4 DecreaseHeartRate'5¨¨4 IncreaseHeartRate'13¨¨4 IncreaseTemperature'1¨¨5 SlowDown'4¨¨5 DecreaseHeartRate'14¨¨5 IncreaseTemperature'7¨¨6 IncreaseHeartRate'15¨¨6 IncreaseTemperature'3¨¨7 SlowDown'6¨¨7 DecreaseHeartRate'8¨¨7 IncreaseHeartRate'16¨¨7 IncreaseTemperature'4¨¨8 SlowDown'7¨¨8 DecreaseHeartRate'17¨¨8 IncreaseTemperature'13¨¨9 HurryUp'10¨¨9 IncreaseHeartRate°9 DecreaseTemperature'18¨¨9 IncreaseTemperature'14¨¨10 HurryUp'9¨¨10 DecreaseHeartRate'11¨¨10 IncreaseHeartRate'1¨¨10 DecreaseTemperature'19¨¨10 IncreaseTemperature'10¨¨11 DecreaseHeartRate'2¨¨11 DecreaseTemperature'20¨¨11 IncreaseTemperature'16¨¨12 HurryUp'13¨¨12 IncreaseHeartRate'3¨¨12 DecreaseTemperature'21¨¨12 IncreaseTemperature'9¨¨13 SlowDown'17¨¨13 HurryUp'12¨¨13 DecreaseHeartRate'14¨¨13 IncreaseHeartRate'4¨¨13 DecreaseTemperature'22¨¨13 IncreaseTemperature'10¨¨14 SlowDown'13¨¨14 DecreaseHeartRate'5¨¨14 DecreaseTemperature'23¨¨14 IncreaseTemperature'16¨¨15 IncreaseHeartRate'6¨¨15 DecreaseTemperature'24¨¨15 IncreaseTemperature'12¨¨16 SlowDown'15¨¨16 DecreaseHeartRate'17¨¨16 IncreaseHeartRate'7¨¨16 DecreaseTemperature'25¨¨16 IncreaseTemperature'13¨¨17 SlowDown'16¨¨17 DecreaseHeartRate'8¨¨17 DecreaseTemperature'26¨¨17 IncreaseTemperature'22¨¨18 HurryUp'19¨¨18 IncreaseHeartRate'9¨¨18 DecreaseTemperature'23¨¨19 HurryUp'18¨¨19 DecreaseHeartRate'20¨¨19 IncreaseHeartRate'10¨¨19 DecreaseTemperature'19¨¨20 DecreaseHeartRate'11¨¨20 DecreaseTemperature'25¨¨21 HurryUp'22¨¨21 IncreaseHeartRate'12¨¨21 DecreaseTemperature'18¨¨22 SlowDown'26¨¨22 HurryUp'21¨¨22 DecreaseHeartRate'23¨¨22 IncreaseHeartRate'13¨¨22 DecreaseTemperature'19¨¨23 SlowDown'22¨¨23 DecreaseHeartRate'14¨¨23 DecreaseTemperature'25¨¨24 IncreaseHeartRate'15¨¨24 DecreaseTemperature'21¨¨25 SlowDown'24¨¨25 DecreaseHeartRate'26¨¨25 IncreaseHeartRate'16¨¨25 DecreaseTemperature'22¨¨26 SlowDown'25¨¨26 DecreaseHeartRate'17¨¨26 DecreaseTemperature-,1=1_11_12/1,4,7|");
		System.out.println(s);
	}

}
