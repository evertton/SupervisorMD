/**
 * Copyright 2013-2014 Elthon Oliveira
 * 
 * This file is part of SupervisorM for Healthcare Professional software.
 * 
 *  SupervisorM for Healthcare Professional is free software: you can 
 *  redistribute it and/or modify it under the terms of the GNU General 
 *  Public License as published by the Free Software Foundation, either 
 *  version 3 of the License, or (at your option) any later version.
 *  
 *  SupervisorM for Healthcare Professional is distributed in the hope that
 *  it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
 *  the GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with SupervisorM for Healthcare Professional. 
 *  If not, see <http://www.gnu.org/licenses/>.
 */

var erroDeConexao = "conexao com codigo nativo falhou!!";

function _start() { 

	cordova.exec(
		function(p){ window.open("simulation.html"); },
		function(p){ alert(p.msg); },
		"SupervisorInterface","java_start",[]);
	
}
function _generateComboOfAttributes(){
	cordova.exec(
		function(p){ ids = p.ids; names = p.names; },
		function(p){ alert(p.msg); },
		"SupervisorInterface","get_model",[]);
	
	alert(ids + " " + names);
	document.getElementById("combo").innerHTML='<select id="attributes">';
		document.getElementById("combo").innerHTML='<option value="sm">Speed Model</option>';
		document.getElementById("combo").innerHTML='<option value="shm">Speed/Heart-rate Model</option>';
		document.getElementById("combo").innerHTML='<option value="shtm">Speed/Heart-rate/Temperature Model</option>';
	document.getElementById("combo").innerHTML='</select>';
}


function _load() { 
	name = document.getElementById("filename").value.trim();
	if (name != ""){
		cordova.exec(
			function(p){ alert("acerto: "+p.msg); window.open("index.html"); },
			function(p){ alert("erro: "+p.msg); },
			"SupervisorInterface","java_load",[name]);
	} else {
		alert("Enter the name of the model!");	
	}
}

function _load2() { 
	model = document.getElementById("models").value;
	cordova.exec(
			function(p){ window.open("index.html"); },
			function(p){ alert("Unknown error (elthon)"); },
			"SupervisorInterface","java_load2",[model]);
}

function _activate() { 
	
}

function _export() { 
	
}

//	document.getElementById("texto").innerHTML="Not developed.";	
