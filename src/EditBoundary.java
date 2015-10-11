import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class EditBoundary implements ActionListener{

	private JFrame janela;
	private JPanel principal;
	private JTextArea areaTexto;
	private JButton abrir;
	private JButton salvarComo;
	private JButton salvar;
	private JButton novo;
	private JScrollPane scroll;
	private JPanel botoes;
	private String caminho;
	private EditControl edit = new EditControl();
	
	public EditBoundary(){
		janela = new JFrame("Editor de textos");
		principal = new JPanel(new BorderLayout());
		areaTexto = new JTextArea();
		scroll = new JScrollPane();
		scroll.getViewport().add(areaTexto);
		principal.add(scroll,BorderLayout.CENTER);
		
		botoes = new JPanel(new FlowLayout());
		novo = new JButton("Novo");
		abrir = new JButton("Abrir");
		salvarComo = new JButton("Salvar Como");
		salvar = new JButton("Salvar");
		
		novo.addActionListener(this);
		salvar.addActionListener(this);
		salvarComo.addActionListener(this);
		abrir.addActionListener(this);
		
		botoes.add(novo);
		botoes.add(abrir);
		botoes.add(salvarComo);
		botoes.add(salvar);
		
		principal.add(botoes, BorderLayout.SOUTH);
		janela.setContentPane(principal);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(400, 400);
		janela.setVisible(true);
	}
	
	public static void main(String[] args) {
		new EditBoundary();
	}

	@Override
	public void actionPerformed(ActionEvent eve) {
		String cmd = eve.getActionCommand();
		if(cmd.equals("Novo")){
			caminho = null;
			this.areaTexto.setText("");
		}
		else if(cmd.equals("Abrir")){
			caminho = JOptionPane.showInputDialog("Digite o caminho");
			setarTexto(edit.abrir(caminho));
		}
		else if(cmd.equals("Salvar Como")){
			caminho = JOptionPane.showInputDialog("Digite o caminho");
			if(!edit.salvar(areaTexto.getText(), caminho)){
				JOptionPane.showMessageDialog(null, "Não pode ser salvo");
			}
		}
		else{
			if(caminho == null){
				caminho = JOptionPane.showInputDialog("Digite o caminho");
			}
			if(!edit.salvar(areaTexto.getText(), caminho)){
					JOptionPane.showMessageDialog(null, "Não pode ser salvo");
			}
		}
	}
	
	public void setarTexto(String txt){
		if(txt == null){
			JOptionPane.showMessageDialog(null, "Não pode ser exibido");
		}
		else{
			areaTexto.setText(txt);
		}
	}
	
}
