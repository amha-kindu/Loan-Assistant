import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class newLoan implements ActionListener{
  LoanAssistant la = null;
  newLoan(LoanAssistant la)
  {
    this.la = la;
  }
  @Override
  public void actionPerformed(ActionEvent e) {

    if(la.computePayment)
    {
      la.monthlyPayment.setText("");
    }
    else{
      la.numOfPayments.setText("");
    }
    la.loanAnalysis.setText("");
    la.compute.setEnabled(true);
    la.newLoanAnalysis.setEnabled(false);
    la.LoanBalance.requestFocus();
    
  }

}