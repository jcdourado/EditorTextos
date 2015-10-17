import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
		ServerSocket server; 
		File caminhoServer;
		Socket cliente;
	public Server() throws IOException{
		server = new ServerSocket(16001);
		System.out.println("Servidor subiu");
		caminhoServer = new File("C:/Users/Julio Cezar");
		cliente = server.accept();
		InputStream ent = cliente.getInputStream();
		InputStreamReader reader = new InputStreamReader(ent);
		BufferedReader buffer = new BufferedReader(reader);
		StringBuffer txtBuffer = new StringBuffer();
		String ca = buffer.readLine();
		while(buffer.ready()){
			txtBuffer.append(buffer.readLine() +
					"\n\r");
		}
		reader.close();
		buffer.close();
		File camin = new File(caminhoServer + "/" +ca);
		FileWriter writer = new FileWriter(camin);
		BufferedWriter escreve = new BufferedWriter(writer);
		escreve.append(txtBuffer.toString());
		escreve.flush();
		writer.close();
		escreve.close();
		cliente.close();
	}
	public static void main(String[] args) throws IOException {
		new Server();
	}
}