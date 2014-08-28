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
		function(p){ saveData(p.ids,p.names); window.open("simulation.html"); },
		function(p){ alert(p.msg); },
		"SupervisorInterface","java_start",[]);
}

//salva dados javascript para carregar em outra tela
function saveData(ids,names){
   localStorage.setItem('ids', ids);
   localStorage.setItem('names', names);
   return;
}
//carrega dados javascript salvos
function loadData(){
   var ids = localStorage.getItem('ids');
   var names = localStorage.getItem('names');
   //Checks whether the stored data exists
   if(ids && names) {
		//alert(ids);alert(names);
     //Do what you need with the object
     //fillFields(_account.User, _account.Pass);
     //If you want to delete the object
     //localStorage.removeItem('id');
     _generateCombo(ids,names);
   } else { alert("vazio"); }
}

//gera combo de atributos dinamicamente a partir do modelo
var _atts_names;
var _atts_ids;
function _generateCombo(_ids,_names){
	var i, theContainer, theSelect, theOptions, numOptions, anOption;
	_atts_names = _names.split(",");//['pop','option 2','option 3'];
	// Create the container <div>
	theContainer = document.createElement('div');
	theContainer.id = 'my_new_div';
	// Create the <select>
	theSelect = document.createElement('select');
	// Give the <select> some attributes
	theSelect.name = 'name_of_select';
	theSelect.id = 'id_of_select';
	theSelect.className = 'class_of_select';
	// Define something to do onChange
	//theSelect.onchange = function () {
   // Do whatever you want to do when the select changes
   //alert('You selected option '+this.selectedIndex);
	//};
	// Add some <option>s
	currentState = "";
	_atts_ids = _ids.split(",");
	for (i = 0; i < _atts_names.length; i++) {
   	anOption = document.createElement('option');
   	anOption.id = i;
    	anOption.value = _atts_ids[i];
    	anOption.innerHTML = _atts_names[i];
    	theSelect.appendChild(anOption);
    	currentState = currentState + "- " + _atts_names[i]+ ": no value.<br>";
	}
	// Add the <div> to the DOM, then add the <select> to the <div>
	document.getElementById('combo').appendChild(theContainer);
	theContainer.appendChild(theSelect);
	document.getElementById("att").innerHTML = currentState;
}

//TODO: executa modelo.
function _simulate() {
	//alert("nomes: "+_atts_names);
	//alert("ids: "+_atts_ids);
	var e = document.getElementById("id_of_select");
	alert("id: "+e.value+", nome: "+e.options[e.selectedIndex].text
			+", valor: "+document.getElementById("_value").value);
}

//carega modelo do sdcard
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

//carrega modelo pre-definido.
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
