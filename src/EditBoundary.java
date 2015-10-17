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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class EditBoundary implements ActionListener{
	private JFrame janela;
	private JPanel principal;
	private JTextArea areaTexto;
	private JScrollPane scroll;
	private JPanel botoes;
	private String caminho;
	private JTextField textoTamanho;
	private EditControl edit = new EditControl();
	private JMenuBar menu = new JMenuBar();
	private JMenu arquivo = new JMenu("Arquivo");
	private JMenu editar = new JMenu("Editar");
	private JMenu servidor = new JMenu("Servidor");
	private JMenu ajuda = new JMenu("Ajuda");
	private JMenuItem itemNovo = new JMenuItem("Novo");
	private JMenuItem itemAbrir = new JMenuItem("Abrir");
	private JMenuItem itemSalvar = new JMenuItem("Salvar");
	private JMenuItem itemSalvarComo = new JMenuItem("Salvar Como");
	private JMenuItem itemFonte = new JMenuItem("Fonte");
	private JMenuItem itemCopiar = new JMenuItem("Copiar");
	private JMenuItem itemRecortar = new JMenuItem("Recortar");
	private JMenuItem itemColar = new JMenuItem("Colar");
	private JMenuItem itemServidor = new JMenuItem("Iniciar Servidor");
	private JMenuItem itemSobre = new JMenuItem("Sobre");
	public EditBoundary(){
		janela = new JFrame("Editor de textos");
		janela.setJMenuBar(menu);
		principal = new JPanel(new BorderLayout());
		areaTexto = new JTextArea();
		scroll = new JScrollPane();
		scroll.getViewport().add(areaTexto);
		principal.add(scroll,BorderLayout.CENTER);
		botoes = new JPanel(new FlowLayout());
		menu.add(arquivo);
		menu.add(editar);
		menu.add(servidor);
		menu.add(ajuda);
		arquivo.add(itemNovo);
		arquivo.add(itemAbrir);
		arquivo.add(itemSalvar);
		arquivo.add(itemSalvarComo);
		editar.add(itemFonte);
		editar.add(itemCopiar);
		editar.add(itemRecortar);
		editar.add(itemColar);
		servidor.add(itemServidor);
		ajuda.add(itemSobre);
		itemNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caminho = null;
				areaTexto.setText("");
			}
		});
		itemAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caminho = JOptionPane.showInputDialog("Digite o caminho");
				setarTexto(edit.abrir(caminho));
			}
		});
		itemSalvarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caminho = JOptionPane.showInputDialog("Digite o caminho");
				if(!edit.salvar(areaTexto.getText(), caminho)){
					JOptionPane.showMessageDialog(null, "Não pode ser salvo");
				}				
			}
		});
		itemFonte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarTamanho();
			}
		});
		itemCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copiar();
			}
		});
		itemRecortar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recortar();
			}
		});
		itemColar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colar();
			}
		});
		itemServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rede();
			}
		});
		itemSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Feito com amor pelo Julio <3. \n Design feito pelo Fabio");
			}
		});
		principal.add(botoes, BorderLayout.SOUTH);
		janela.setContentPane(principal);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(900, 600);
		janela.setVisible(true);
	}
	public static void main(String[] args) {
		new EditBoundary();
	}
	public void actionPerformed(ActionEvent eve) {
		String cmd = eve.getActionCommand();
		if(cmd.equals("Alterar Cor")){
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
		String cor = JOptionPane.showInputDialog("Dig"
				+ ""
				+ "ite a cor desejada");
		if(cor.startsWith("verm") || cor.startsWith("Ve"
				+ "rm")){
			return Color.RED;		
		}
		else if(cor.startsWith("Viol") || cor.startsWith("v"
				+ "iol")){
			return (new Color(173,234,234));						
		}
		else if(cor.startsWith("Tur"
				+ "ques") || cor.startsWith("turq"
						+ "ue")){
			return (new Color(219,112,147));								
		}
		else if(cor.startsWith("Pre"
				+ "to") || cor.startsWith("pr"
						+ "eto")){
			return (Color.BLACK);
		}
		else if(cor.startsWith("s"
				+ "al") || cor.startsWith("S"
						+ "al")){
			return (new Color(111,66,66));						
		}
		else if(cor.startsWith("Or"
				+ "qu") || cor.startsWith("or"
						+ "qu")){
			return (new Color(153,50,205));						
		}
		else if(cor.startsWith("M"
				+ "arro") || cor.startsWith("mar"
						+ "ro")){
			return (new Color(165,42,42));			
		}
		else if(cor.startsWith("Lig"
				+ "ht") || cor.startsWith("li"
						+ "ght")){
			return(new Color(143,143,189));						
		}
		else if(cor.startsWith("Cy"
				+ ""
				+ "an") || cor.startsWith("cy"
						+ "an")){
			return(Color.cyan);					
		}
		else if(cor.startsWith("Cor"
				+ "al") || cor.startsWith("c"
						+ "oral")){
			return(new Color(255,127,0));						
		}
		else if(cor.startsWith("Bro"
				+ "nz") || cor.startsWith("bro"
						+ ""
						+ "nz")){
			return(new Color(219,147,112));						
		}
		else if(cor.startsWith("Br"
				+ "anco") || cor.startsWith("bra"
						+ "nco")){
			return(Color.WHITE);			
		}
		else if(cor.startsWith("ci"
				+ "nz") || cor.startsWith("Cin"
						+ "z")){
			return(Color.GRAY);			
		}
		else if(cor.startsWith("Lar"
				+ "anj") || cor.startsWith("lara"
						+ "n")){
			return(Color.ORANGE);			
		}
		else if(cor.startsWith("Am"
				+ "are") || cor.startsWith("a"
						+ "ma"
						+ "re")){
			return(Color.YELLOW);			
		}
		else if(cor.startsWith("ro"
				+ "sa") || cor.startsWith("Ros"
						+ "a")){
			return(Color.PINK);			
		}
		else if(cor.startsWith("A"
				+ "zul") || cor.startsWith("az"
						+ "ul")){
			return(Color.BLUE);			
		}
		else if(cor.startsWith("ve"
				+ "rd") || cor.startsWith("Ve"
						+ "rd")){
			return (Color.GREEN);
		}
		else if(cor.startsWith("Ro"
				+ "xo") || cor.startsWith("ro"
						+ "xo")){
			return (new Color(135,31,120));						
		}
		return null;
	}
	public void alterarFundo(){
		Color cor = buscor();
		if(cor == null){
			JOptionPane.showMessageDialog(null, "Cor"
					+ " não encontrada");
		}
		else{
			areaTexto.setBackground(cor);
		}
	}
	public void alterarTamanho(){
		JFrame janelinha = new JFrame("Alte"
				+ "rar estilo");
		JLabel label = new JLabel("Digite o"
				+ " tamanho e a fonte");
		JPanel botoesJanelinha = new JPanel(new BorderLayout());
		textoTamanho = new JTextField();
		JButton negrito = new JButton("Negri"
				+ "to");
		JButton italico = new JButton("Itali"
				+ "co");
		JButton alterarCor = new JButton("Alterar Cor");;
		JButton alterarFundo = new JButton("Alterar"
				+ " Cor de Fundo");
		JButton alterarFonte = new JButton("Alterar"
				+ " Fonte");
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
		String server = JOptionPane.showInputDialog("Digite "
				+ "o caminho do servidor "
				+ "e sua porta");
		String[] split = server.split(" ");
		try {
			Socket cliente = new Socket(split[0], Integer.parseInt(split[1]));
			if(cliente.isConnected() && !cliente.isClosed()){
				@SuppressWarnings("unused")
				JanelaRede r = new JanelaRede(cliente,areaTexto.getText(),this);
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(null, "Servidor "
					+ "Invalido");
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Servidor"
					+ " Invalido");
			e.printStackTrace();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Servidor"
					+ " Invalido");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Servidor"
					+ " Invalido");
			e.printStackTrace();
		}		
	}
	public void alterarTxt(String txto){
		this.areaTexto.setText(txto);
	}
	public void copiar(){
		areaTexto.copy();
	}
	public void recortar(){
		areaTexto.cut();
	}
	public void colar(){
		areaTexto.paste();
	}
}