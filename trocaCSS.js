function trocaTexto(){
	var entrada = document.getElementsByClassName("area-text--entrada")[0].value;
	document.getElementsByClassName("area-text--saida")[0].value = tiraMin(entrada);	
}


function tiraMin(texto){
	d = "";
	var a = texto.split("{");
	for(var i in a){
		a[i] = a[i].replace("}",";}\n\n");
		d = d + a[i] + "{\n";
	}
	texto = d.replace("}\n\n{","}");

	a = texto.split(",");
	for(var i in a){
		if(!(i==0)){
			a[i] = "\n" + a[i];
		}
	}

	texto = a.join(",");

	a = texto.split(";");
	for(var i in a){
		if(!(i==0)){
			a[i] = "\n" + a[i];
		}
	}

	texto = a.join(";");

	return texto;
}


