import java.awt.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.border.Border;

class VentanaPrinicipal extends JFrame{
	JTextArea txtA1, txtA2;
	JProgressBar PGRB1, PGRB2;
   public VentanaPrinicipal() {
	   crearComponentes();
   }
   public void crearComponentes(){
	   
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(600,300);
		setLocationRelativeTo(null);
		setTitle("Resultados");
		setForeground(Color.DARK_GRAY);
		setVisible(true);
		
		
		//text Area 1
		txtA1=new JTextArea();
		txtA1.setBounds(0, 0, 295, 300);
		txtA1.setLineWrap(true);
		txtA1.setWrapStyleWord(true);
		txtA1.setBackground(Color.gray);
		add(txtA1);
		
		//text Area 2
		txtA2=new JTextArea();
		txtA2.setBounds(300, 0, 300, 300);
		txtA2.setLineWrap(true);
		txtA2.setWrapStyleWord(true);
		txtA2.setBackground(Color.gray);
		add(txtA2);
		
		//Progress Bars
		PGRB1=new JProgressBar();
		PGRB1.setStringPainted(true);
		PGRB1.setBackground(Color.DARK_GRAY);
		PGRB1.setBounds(0, 310, 1, 1);
		add(PGRB1);
		PGRB2=new JProgressBar();
		PGRB2.setBackground(Color.DARK_GRAY);
		PGRB2.setBounds(0, 320, 1, 1);
		add(PGRB2);
   }

}

class Obtenciondatos{
	public String[] generacionDatos() {
		Random rnd = new Random();
		int x=0;
		String datos[]=new String[10000000];
		for (int i = 0; i < datos.length; i++) {
			x=rnd.nextInt(2);
			if (x==0) {
				datos[i]="SI";
			}
			else {
				datos[i]="NO";
			}
		}
		return datos;
	}
}

class InsertarDatos implements Runnable{
	VentanaPrinicipal VentPrinicpal = new VentanaPrinicipal();
	@Override
	public void run() {
		Obtenciondatos obj=new Obtenciondatos();
		String datos[]=obj.generacionDatos();
		
		for (int i = 0; i <datos.length ; i++) {
			if (datos[i]=="SI") {
				VentPrinicpal.txtA1.setText(VentPrinicpal.txtA1.getText()+datos[i]+",");
				
			}
			else {
				VentPrinicpal.txtA2.setText(VentPrinicpal.txtA2.getText()+datos[i]+",");
				
			}	
		}
		 
		
	}
	
}
class histograma implements Runnable{

	@Override
	public void run() {
		Obtenciondatos obj=new Obtenciondatos();
		String datos[]=obj.generacionDatos();
		int contSi=0;
		int contNo=0;
		for (int i = 0; i <datos.length ; i++) {
			if (datos[i]=="SI") {
				contSi+=1;
			}
			else {		
				contNo+=1;
			}	
		}
		JFrame grafica = new JFrame("HISTOGRAMA");
	  	grafica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container content = grafica.getContentPane();
	    grafica.setSize(400, 150);
	    grafica.setLocationRelativeTo(null);
	    grafica.setVisible(true); 
    
	    JProgressBar progressBar = new JProgressBar(0, datos.length);
	    progressBar.setValue(contSi);
	    progressBar.setStringPainted(true);
	    progressBar.setForeground(Color.gray);
	    Border border = BorderFactory.createTitledBorder("Registro de Respuestas con SI.");
	    progressBar.setBorder(border);
	    content.add(progressBar, BorderLayout.PAGE_END);
	    
	    JProgressBar progressBar2 = new JProgressBar(0, datos.length);
	    progressBar2.setValue(contNo);
	    progressBar2.setStringPainted(true);
	    progressBar2.setForeground(Color.gray);
	    Border border2 = BorderFactory.createTitledBorder("Registro de Respuestas con NO.");
	    progressBar2.setBorder(border2);
	    content.add(progressBar2, BorderLayout.PAGE_START);
		    
	} 
	
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Thread H1=new Thread(new InsertarDatos());
				Thread H2=new Thread(new histograma());
				H1.start();
				H2.start();
				
			}
		});
		
	}

}
