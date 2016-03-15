function trocaTexto(){
	var entrada = document.getElementsByClassName("area-text--entrada")[0].value;
	document.getElementsByClassName("area-text--saida")[0].value = tiraMin(entrada);
}

var openFile = function(event) {
    var input = event.target;

    var reader = new FileReader();
    reader.onload = function(){
			document.getElementsByClassName("area-text--entrada")[0].value = reader.result;
			document.getElementsByClassName("area-text--saida")[0].value = tiraMin(reader.result);
    };
    reader.readAsText(input.files[0]);
  };


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
