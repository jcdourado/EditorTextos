import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class JanelaRede extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private Socket cliente;
	private String texto;
	private JTextField txt;
	public JanelaRede(Socket c, String texto, EditBoundary edit){
		this.cliente = c;
		this.texto = texto;
		this.setTitle("Rede");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(200, 200);
		this.setVisible(true);
		JPanel painel = new JPanel(new BorderLayout());
		JLabel lbl = new JLabel("Digite o caminho");
		txt = new JTextField();
		JPanel painelButtons = new JPanel(new FlowLayout());
		JButton commit = new JButton("Commit");
		commit.addActionListener(this);
		painelButtons.add(commit);
		painel.add(painelButtons, BorderLayout.SOUTH);
		painel.add(lbl, BorderLayout.NORTH);
		painel.add(txt, BorderLayout.CENTER);
		this.setContentPane(painel);
	}
	@Override
	public void actionPerformed(ActionEvent eve) {
		String cmd = eve.getActionCommand();
		if(cmd.equals("Commit")){
			try {
				OutputStreamWriter mand = new OutputStreamWriter(cliente.getOutputStream());
				BufferedWriter writer = new BufferedWriter(mand);
				writer.append(txt.getText() + "\n");
				writer.append(texto);
				writer.flush();
				mand.close();
				writer.close();
				cliente.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}