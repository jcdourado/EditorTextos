import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class EditControl {
	public String abrir(String caminho){
		File f = new File(caminho);
		StringBuffer buffer = new StringBuffer();
		if(f.canRead()){
			FileReader reader;
			try {
				reader = new FileReader(f);
				BufferedReader br = new BufferedReader(reader);
				while(br.ready()){
					buffer.append(br.readLine());
				}
				br.close();
				return buffer.toString();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	public boolean salvar(String texto, String caminho){
		File f = new File(caminho);
		try {
			FileWriter writer = new FileWriter(f);
			if(f.canWrite()){
				BufferedWriter bw = new BufferedWriter(writer);
				bw.append(texto);
				bw.flush();
				writer.close();
				bw.close();
				return true;
			}
			writer.close();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}