import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
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
	private JTextField textoTamanho;
	private JButton alterarTamanho;
	private JButton rede;
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
		alterarTamanho = new JButton("Alterar Estilo");
		rede = new JButton("Trabalhar em Rede");
		novo.addActionListener(this);
		salvar.addActionListener(this);
		salvarComo.addActionListener(this);
		abrir.addActionListener(this);
		alterarTamanho.addActionListener(this);
		rede.addActionListener(this);
		botoes.add(novo);
		botoes.add(abrir);
		botoes.add(salvarComo);
		botoes.add(salvar);
		botoes.add(alterarTamanho);
		botoes.add(rede);
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
		else if(cmd.equals("Alterar Estilo")){
			alterarTamanho();
		}
		else if(cmd.equals("Alterar Fonte")){
			String[] txt = textoTamanho.getText().split(" ");
			areaTexto.setFont(new Font(txt[1], Font.PLAIN, Integer.parseInt(txt[0])));	
		}
		else if(cmd.equals("Italico")){
			String[] txt = textoTamanho.getText().split(" ");
			if(areaTexto.getFont().isBold()){
				areaTexto.setFont(new Font(txt[1], Font.ITALIC + Font.BOLD, Integer.parseInt(txt[0])));
			}
			else if(areaTexto.getFont().isItalic() && !areaTexto.getFont().isBold()){
				areaTexto.setFont(new Font(txt[1], Font.PLAIN, Integer.parseInt(txt[0])));				
			}
			else{
				areaTexto.setFont(new Font(txt[1], Font.ITALIC , Integer.parseInt(txt[0])));
			}
		}
		else if(cmd.equals("Negrito")){
			String[] txt = textoTamanho.getText().split(" ");
			if(areaTexto.getFont().isItalic()){
				areaTexto.setFont(new Font(txt[1], Font.BOLD + Font.ITALIC, Integer.parseInt(txt[0])));
			}
			else{
				areaTexto.setFont(new Font(txt[1], Font.BOLD, Integer.parseInt(txt[0])));
			}
		}
		else if(cmd.equals("Trabalhar em Rede")){
			rede();
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
	public void alterarCor(){
		Color cor = buscor();
		if(cor == null){
			JOptionPane.showMessageDialog(null, "Cor não encontrada");
		}
		else{
			areaTexto.setForeground(cor);
		}
	}
	
	public Color buscor(){
		String cor = JOptionPane.showInputDialog("Digite a cor desejada");

		if(cor.startsWith("verm") || cor.startsWith("Verm")){
			return Color.RED;		
		}
		else if(cor.startsWith("Viol") || cor.startsWith("viol")){
			return (new Color(173,234,234));						
		}
		else if(cor.startsWith("Turques") || cor.startsWith("turque")){
			return (new Color(219,112,147));								
		}
		else if(cor.startsWith("Preto") || cor.startsWith("preto")){
			return (Color.BLACK);
		}
		else if(cor.startsWith("sal") || cor.startsWith("Sal")){
			return (new Color(111,66,66));						
		}
		else if(cor.startsWith("Orqu") || cor.startsWith("orqu")){
			return (new Color(153,50,205));						
		}
		else if(cor.startsWith("Marro") || cor.startsWith("marro")){
			return (new Color(165,42,42));			
		}
		else if(cor.startsWith("Light") || cor.startsWith("light")){
			return(new Color(143,143,189));						
		}
		else if(cor.startsWith("Cyan") || cor.startsWith("cyan")){
			return(Color.cyan);					
		}
		else if(cor.startsWith("Coral") || cor.startsWith("coral")){
			return(new Color(255,127,0));						
		}
		else if(cor.startsWith("Bronz") || cor.startsWith("bronz")){
			return(new Color(219,147,112));						
		}
		else if(cor.startsWith("Branco") || cor.startsWith("branco")){
			return(Color.WHITE);			
		}
		else if(cor.startsWith("cinz") || cor.startsWith("Cinz")){
			return(Color.GRAY);			
		}
		else if(cor.startsWith("Laranj") || cor.startsWith("laran")){
			return(Color.ORANGE);			
		}
		else if(cor.startsWith("Amare") || cor.startsWith("amare")){
			return(Color.YELLOW);			
		}
		else if(cor.startsWith("rosa") || cor.startsWith("Rosa")){
			return(Color.PINK);			
		}
		else if(cor.startsWith("Azul") || cor.startsWith("azul")){
			return(Color.BLUE);			
		}
		else if(cor.startsWith("verd") || cor.startsWith("Verd")){
			return (Color.GREEN);
		}
		else if(cor.startsWith("Roxo") || cor.startsWith("roxo")){
			return (new Color(135,31,120));						
		}
		return null;
	}
	public void alterarFundo(){
		Color cor = buscor();
		if(cor == null){
			JOptionPane.showMessageDialog(null, "Cor não encontrada");
		}
		else{
			areaTexto.setBackground(cor);
		}
	}
	public void alterarTamanho(){
		JFrame janelinha = new JFrame("Alterar estilo");
		JLabel label = new JLabel("Digite o tamanho e a fonte");
		JPanel botoesJanelinha = new JPanel(new BorderLayout());
		textoTamanho = new JTextField();
		JButton negrito = new JButton("Negrito");
		JButton italico = new JButton("Italico");
		JButton alterarCor = new JButton("Alterar Cor");;
		JButton alterarFundo = new JButton("Alterar Cor de Fundo");
		JButton alterarFonte = new JButton("Alterar Fonte");
		JPanel botoesAbaixo = new JPanel(new FlowLayout());
		botoesAbaixo.add(negrito);
		botoesAbaixo.add(italico);
		botoesAbaixo.add(alterarCor);
		botoesAbaixo.add(alterarFundo);
		botoesAbaixo.add(alterarFonte);
		botoesJanelinha.add(label, BorderLayout.NORTH);
		botoesJanelinha.add(textoTamanho, BorderLayout.CENTER);
		botoesJanelinha.add(botoesAbaixo, BorderLayout.SOUTH);
		alterarFonte.addActionListener(this);
		negrito.addActionListener(this);
		italico.addActionListener(this);
		alterarCor.addActionListener(this);
		alterarFundo.addActionListener(this);
		janelinha.setSize(600, 150);
		janelinha.setContentPane(botoesJanelinha);
		janelinha.setVisible(true);
		janelinha.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void rede(){
		String server = JOptionPane.showInputDialog("Digite o caminho do servidor e sua porta");
		String[] split = server.split(" ");
		try {
			Socket cliente = new Socket(split[0], Integer.parseInt(split[1]));
			if(cliente.isConnected() && !cliente.isClosed()){
				@SuppressWarnings("unused")
				JanelaRede r = new JanelaRede(cliente,areaTexto.getText(),this);
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(null, "Servidor Invalido");
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Servidor Invalido");
			e.printStackTrace();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Servidor Invalido");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Servidor Invalido");
			e.printStackTrace();
		}		
	}
	public void alterarTxt(String txto){
		this.areaTexto.setText(txto);
	}
}
