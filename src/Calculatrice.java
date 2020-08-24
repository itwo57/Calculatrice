import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculatrice extends JFrame {
	private JPanel container = new JPanel();
	  private JLabel ecran = new JLabel();
	  private Dimension dim = new Dimension(50, 40);
	  private Dimension dim2 = new Dimension(50, 31);
	  private double chiffre1;
	  private boolean clicOperateur = false, update = false;
	  private String operateur = "";
	  
	  

	
	private void initCal() {
	    Font police = new Font("Arial", Font.BOLD, 20);
	    ecran = new JLabel("0");
	    ecran.setFont(police);
	    ecran.setHorizontalAlignment(JLabel.RIGHT);
	    ecran.setPreferredSize(new Dimension(220, 20));
	    JPanel panEcran = new JPanel();
	    panEcran.setPreferredSize(new Dimension(220, 30));
		
	    JPanel chiffre = new JPanel();
	    chiffre.setPreferredSize(new Dimension(165, 225));
	    
	    for (int i = 1 ; i<10 ; i++) {
	    	JButton Bouton = new JButton(String.valueOf(i));
	    	Bouton.setPreferredSize(dim);
	    	Bouton.addActionListener(new ChiffreListener());
	    	chiffre.add(Bouton);
	    }

	    JButton Bouton0 = new JButton("0");
	    Bouton0.setPreferredSize(dim);
	    Bouton0.addActionListener(new ChiffreListener());
	    chiffre.add(Bouton0);
	    JButton BoutonP = new JButton(".");
	    BoutonP.setPreferredSize(dim);
	    BoutonP.addActionListener(new PointListener());
	    chiffre.add(BoutonP);
	    JButton BoutonEgal = new JButton("=");
	    BoutonEgal.setPreferredSize(dim);
	    BoutonEgal.addActionListener(new EgalListener());
	    chiffre.add(BoutonEgal);

	    
	    JPanel operateur = new JPanel();      
	    operateur.setPreferredSize(new Dimension(55, 225));
	    JButton BoutonC = new JButton("C");
	    BoutonC.setPreferredSize(dim);
	    BoutonC.setForeground(Color.red);
	    BoutonC.addActionListener(new ResetListener());
	    operateur.add(BoutonC);
	    JButton BoutonPlus = new JButton("+");
	    BoutonPlus.setPreferredSize(dim2);
	    BoutonPlus.addActionListener(new PlusListener());
	    operateur.add(BoutonPlus);
	    JButton BoutonMoins = new JButton("-");
	    BoutonMoins.setPreferredSize(dim2);
	    BoutonMoins.addActionListener(new MoinsListener());
	    operateur.add(BoutonMoins);
	    JButton BoutonFois = new JButton("*");
	    BoutonFois.setPreferredSize(dim2);
	    BoutonFois.addActionListener(new MultiListener());
	    operateur.add(BoutonFois);
	    JButton BoutonDiv = new JButton("/");
	    BoutonDiv.setPreferredSize(dim2);
	    BoutonDiv.addActionListener(new DivListener());
	    operateur.add(BoutonDiv);
	    
	    panEcran.add(ecran);
	    panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
	    container.add(panEcran, BorderLayout.NORTH);
	    container.add(chiffre, BorderLayout.CENTER);
	    container.add(operateur, BorderLayout.EAST);    
	    
	}
	
	  private void calcul(){
		    if(operateur.equals("+")){
		      chiffre1 = chiffre1 + 
		            Double.valueOf(ecran.getText()).doubleValue();
		      ecran.setText(String.valueOf(chiffre1));
		    }
		    if(operateur.equals("-")){
		      chiffre1 = chiffre1 - 
		            Double.valueOf(ecran.getText()).doubleValue();
		      ecran.setText(String.valueOf(chiffre1));
		    }          
		    if(operateur.equals("*")){
		      chiffre1 = chiffre1 * 
		            Double.valueOf(ecran.getText()).doubleValue();
		      ecran.setText(String.valueOf(chiffre1));
		    }     
		    if(operateur.equals("/")){
		      try{
		        chiffre1 = chiffre1 / 
		              Double.valueOf(ecran.getText()).doubleValue();
		        ecran.setText(String.valueOf(chiffre1));
		      } catch(ArithmeticException e) {
		        ecran.setText("0");
		      }
		    }
		  }
	
	  class ChiffreListener implements ActionListener {
		    public void actionPerformed(ActionEvent e){
		      //On affiche le chiffre additionnel dans le label
		      String str = ((JButton)e.getSource()).getText();
		      if(update){
		        update = false;
		      }
		      else{
		        if(!ecran.getText().equals("0"))
		          str = ecran.getText() + str;
		      }
		      ecran.setText(str);
		    }
		  }

		  //Listener affecté au bouton =
		  class EgalListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		      calcul();
		      update = true;
		      clicOperateur = false;
		    }
		  }
	  
	  class PointListener implements ActionListener {
		    public void actionPerformed(ActionEvent e){
		      String str = ((JButton)e.getSource()).getText();
		      if(!ecran.getText().contentEquals("0"))
		    	  str = ecran.getText() + str;
		      if(ecran.getText().contentEquals("0"))
		    	  str = "0.";
		      if(ecran.getText().contains("."))
		    	  str =  ecran.getText();
		      ecran.setText(str);		  
		    }
	  }
	  class PlusListener implements ActionListener {
		  public void actionPerformed(ActionEvent e) {
			  if(clicOperateur){
			        calcul();
			        ecran.setText(String.valueOf(chiffre1));
			      }
			      else{
			        chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
			        clicOperateur = true;
			      }
			      operateur = "+";
			      update = true;
			    }
	  }
	  
	  //Listener affecté au bouton -
	  class MoinsListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0){
	      if(clicOperateur){
	        calcul();
	        ecran.setText(String.valueOf(chiffre1));
	      }
	      else{
	        chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
	        clicOperateur = true;
	      }
	      operateur = "-";
	      update = true;
	    }
	  }

	  //Listener affecté au bouton *
	  class MultiListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0){
	      if(clicOperateur){
	        calcul();
	        ecran.setText(String.valueOf(chiffre1));
	      }
	      else{
	        chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
	        clicOperateur = true;
	      }
	      operateur = "*";
	      update = true;
	    }
	  }

	  //Listener affecté au bouton /
	  class DivListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0){
	      if(clicOperateur){
	        calcul();
	        ecran.setText(String.valueOf(chiffre1));
	      }
	      else{
	        chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
	        clicOperateur = true;
	      }
	      operateur = "/";
	      update = true;
	    }
	  }

	  //Listener affecté au bouton de remise à zéro
	  class ResetListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0){
	      clicOperateur = false;
	      update = true;
	      chiffre1 = 0;
	      operateur = "";
	      ecran.setText("");
	    }
	  }  
	  
	public Calculatrice() {
		this.setSize(260, 280);
		this.setTitle("Calculette");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    initCal();
	    this.setContentPane(container);
	    this.setVisible(true); 
	}
}
