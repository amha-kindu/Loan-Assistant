import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;



class SwitchMode implements ActionListener{
  LoanAssistant la = null;

  SwitchMode(LoanAssistant la){ this.la = la;}
  
  @Override
  public void actionPerformed(ActionEvent e) {
    Color lightYellow = new Color(255,255,102);
    if (!la.computePayment)
    {
      la.secondMode.setVisible(false);
      la.firstMode.setVisible(true);
      la.computePayment = true;
      la.monthlyPayment.setText("");
      la.monthlyPayment.setEditable(false);
      la.monthlyPayment.setBackground(lightYellow);

      la.numOfPayments.setBackground(Color.WHITE);
      la.numOfPayments.setEditable(true);
      la.numOfPayments.requestFocus();;
      la.compute.setLabel("Compute Monthly Payment");
    }
    else{
      la.computePayment = false;
      la.secondMode.setVisible(true);
      la.firstMode.setVisible(false);
      la.monthlyPayment.setEditable(true);
      la.monthlyPayment.requestFocus();;
      la.monthlyPayment.setBackground(Color.WHITE);

      la.numOfPayments.setText("");
      la.numOfPayments.setEditable(false);
      la.numOfPayments.setBackground(lightYellow);  
      la.compute.setLabel("Compute Number of Payments");

    }
    
  }
}