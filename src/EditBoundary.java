import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


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
	private JButton alterarCor;
	private JButton alterarFundo;
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
		alterarCor = new JButton("Alterar Cor");
		alterarFundo = new JButton("Alterar Cor de Fundo");
		
		novo.addActionListener(this);
		salvar.addActionListener(this);
		salvarComo.addActionListener(this);
		abrir.addActionListener(this);
		alterarCor.addActionListener(this);
		alterarFundo.addActionListener(this);
		
		botoes.add(novo);
		botoes.add(abrir);
		botoes.add(salvarComo);
		botoes.add(salvar);
		botoes.add(alterarCor);
		botoes.add(alterarFundo);
		
		principal.add(botoes, BorderLayout.SOUTH);
		janela.setContentPane(principal);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(600, 600);
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
		else if(cmd.equals("Alterar Cor")){
			alterarCor();
		}
		else if(cmd.equals("Alterar Cor de Fundo")){
			alterarFundo();
		}
		else if(cmd.equals("Italico")){
			
		}
		else if(cmd.equals(suffix))
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
	
	public void alterarCor(){
		String cor = JOptionPane.showInputDialog("Digite a cor desejada");
		
		if(cor.startsWith("verm") || cor.startsWith("Verm")){
			areaTexto.setForeground(Color.RED);		
		}
		else if(cor.startsWith("Viol") || cor.startsWith("viol")){
			areaTexto.setForeground(new Color(173,234,234));						
		}
		else if(cor.startsWith("Turques") || cor.startsWith("turque")){
			areaTexto.setForeground(new Color(219,112,147));								
		}
		else if(cor.startsWith("Preto") || cor.startsWith("preto")){
			areaTexto.setForeground(Color.BLACK);
		}
		else if(cor.startsWith("sal") || cor.startsWith("Sal")){
			areaTexto.setForeground(new Color(111,66,66));						
		}
		else if(cor.startsWith("Orqu") || cor.startsWith("orqu")){
			areaTexto.setForeground(new Color(153,50,205));						
		}
		else if(cor.startsWith("Marro") || cor.startsWith("marro")){
			areaTexto.setForeground(new Color(165,42,42));			
		}
		else if(cor.startsWith("Light") || cor.startsWith("light")){
			areaTexto.setForeground(new Color(143,143,189));						
		}
		else if(cor.startsWith("Cyan") || cor.startsWith("cyan")){
			areaTexto.setForeground(Color.cyan);					
		}
		else if(cor.startsWith("Coral") || cor.startsWith("coral")){
			areaTexto.setForeground(new Color(255,127,0));						
		}
		else if(cor.startsWith("Bronz") || cor.startsWith("bronz")){
			areaTexto.setForeground(new Color(219,147,112));						
		}
		else if(cor.startsWith("Branco") || cor.startsWith("branco")){
			areaTexto.setForeground(Color.WHITE);			
		}
		else if(cor.startsWith("cinz") || cor.startsWith("Cinz")){
			areaTexto.setForeground(Color.GRAY);			
		}
		else if(cor.startsWith("Laranj") || cor.startsWith("laran")){
			areaTexto.setForeground(Color.ORANGE);			
		}
		else if(cor.startsWith("Amare") || cor.startsWith("amare")){
			areaTexto.setForeground(Color.YELLOW);			
		}
		else if(cor.startsWith("rosa") || cor.startsWith("Rosa")){
			areaTexto.setForeground(Color.PINK);			
		}
		else if(cor.startsWith("Azul") || cor.startsWith("azul")){
			areaTexto.setForeground(Color.BLUE);			
		}
		else if(cor.startsWith("verd") || cor.startsWith("Verd")){
			areaTexto.setForeground(Color.GREEN);
		}
		else if(cor.startsWith("Roxo") || cor.startsWith("roxo")){
			areaTexto.setForeground(new Color(135,31,120));						
		}
		else{
			JOptionPane.showMessageDialog(null, "Cor não encontrada");
		}
	}
	
	public void alterarFundo(){
	String cor = JOptionPane.showInputDialog("Digite a cor desejada");
		
		if(cor.startsWith("verm") || cor.startsWith("Verm")){
			areaTexto.setBackground(Color.RED);		
		}
		else if(cor.startsWith("Viol") || cor.startsWith("viol")){
			areaTexto.setBackground(new Color(173,234,234));						
		}
		else if(cor.startsWith("Turques") || cor.startsWith("turque")){
			areaTexto.setBackground(new Color(219,112,147));								
		}
		else if(cor.startsWith("Preto") || cor.startsWith("preto")){
			areaTexto.setBackground(Color.BLACK);
		}
		else if(cor.startsWith("sal") || cor.startsWith("Sal")){
			areaTexto.setBackground(new Color(111,66,66));						
		}
		else if(cor.startsWith("Orqu") || cor.startsWith("orqu")){
			areaTexto.setBackground(new Color(153,50,205));						
		}
		else if(cor.startsWith("Marro") || cor.startsWith("marro")){
			areaTexto.setBackground(new Color(165,42,42));			
		}
		else if(cor.startsWith("Light") || cor.startsWith("light")){
			areaTexto.setBackground(new Color(143,143,189));						
		}
		else if(cor.startsWith("Cyan") || cor.startsWith("cyan")){
			areaTexto.setBackground(Color.cyan);					
		}
		else if(cor.startsWith("Coral") || cor.startsWith("coral")){
			areaTexto.setBackground(new Color(255,127,0));						
		}
		else if(cor.startsWith("Bronz") || cor.startsWith("bronz")){
			areaTexto.setBackground(new Color(219,147,112));						
		}
		else if(cor.startsWith("Branco") || cor.startsWith("branco")){
			areaTexto.setBackground(Color.WHITE);			
		}
		else if(cor.startsWith("cinz") || cor.startsWith("Cinz")){
			areaTexto.setBackground(Color.GRAY);			
		}
		else if(cor.startsWith("Laranj") || cor.startsWith("laran")){
			areaTexto.setBackground(Color.ORANGE);			
		}
		else if(cor.startsWith("Amare") || cor.startsWith("amare")){
			areaTexto.setBackground(Color.YELLOW);			
		}
		else if(cor.startsWith("rosa") || cor.startsWith("Rosa")){
			areaTexto.setBackground(Color.PINK);			
		}
		else if(cor.startsWith("Azul") || cor.startsWith("azul")){
			areaTexto.setBackground(Color.BLUE);			
		}
		else if(cor.startsWith("verd") || cor.startsWith("Verd")){
			areaTexto.setBackground(Color.GREEN);
		}
		else if(cor.startsWith("Roxo") || cor.startsWith("roxo")){
			areaTexto.setBackground(new Color(135,31,120));						
		}
		else{
			JOptionPane.showMessageDialog(null, "Cor não encontrada");
		}
	}
	
	public void alterarTamanho(){
		JFrame janelinha = new JFrame("Alterar estilo");
		JLabel label = new JLabel("Digite o tamanho");
		JPanel botoesJanelinha = new JPanel(new BorderLayout());
		JTextField textoTamanho = new JTextField();
		JButton negrito = new JButton("Negrito");
		JButton italico = new JButton("Italico");
		JButton sublinhado = new JButton("Sublinhado");
		
		JPanel botoesAbaixo = new JPanel(new FlowLayout());
		
		botoesAbaixo.add(negrito);
		botoesAbaixo.add(italico);
		botoesAbaixo.add(sublinhado);
		
		botoesJanelinha.add(label, BorderLayout.NORTH);
		botoesJanelinha.add(textoTamanho, BorderLayout.CENTER);
		botoesJanelinha.add(botoesAbaixo, BorderLayout.SOUTH);
		
		negrito.addActionListener(this);
		italico.addActionListener(this);
		sublinhado.addActionListener(this);
		
		janelinha.setContentPane(botoesJanelinha);
		janelinha.setVisible(true);
		janelinha.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
}
