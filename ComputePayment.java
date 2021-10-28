import java.awt.event.*;
import java.lang.Math;
import java.text.DecimalFormat;
import java.awt.TextField;
import javax.swing.JOptionPane;



class ComputePayment implements ActionListener{
    LoanAssistant la = null;
    ComputePayment(LoanAssistant la){
        this.la = la;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      double balance, payment, interest, monthlyInterest, multiplier;
      int months;
      DecimalFormat df = new DecimalFormat("###.##");

      if(!validateUserInput(la.interestRate))
      {
        JOptionPane.showConfirmDialog(null, "Invalid or Empty input on interest rate entry.\nPlease correct it!","Interest input Error", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        return;
      }
      if(!validateUserInput(la.LoanBalance))
      {
        JOptionPane.showConfirmDialog(null, "Invalid or Empty input on balance entry.\nPlease correct it!","Balance Input Error", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        return;
      }
      interest = Double.parseDouble(la.interestRate.getText());
      balance = Double.parseDouble(la.LoanBalance.getText());
      
      monthlyInterest = interest / 1200;
      
      if (!la.computePayment)
      {
        double minimumPayment = balance * monthlyInterest + 1.0;
        if(!(validateUserInput(la.monthlyPayment)))
        {
          JOptionPane.showConfirmDialog(null, "Invalid or Empty input on payment entry.\nPlease correct it!","Payment input Error", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
          return;
        }
        payment = Double.parseDouble(la.monthlyPayment.getText());
        if (payment < minimumPayment)
        {
          int UserResponse = JOptionPane.showConfirmDialog(null, "The Minimum payment is $"+ df.format(minimumPayment) +"\nWould you like to use this value?","Below Minimum Payment",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
          if(UserResponse == JOptionPane.YES_OPTION){
            payment = minimumPayment;
            la.monthlyPayment.setText(""+df.format(payment));
          }
          else{
            la.monthlyPayment.requestFocus();
            return;
          }
        }
        if (interest == 0)
        {
          months = (int)(balance / payment);
        }
        else{
          double temp = payment / (monthlyInterest * balance);
          multiplier = temp / (temp - 1);
          months = (int)(Math.log(multiplier) / Math.log(1 + monthlyInterest));
        }
        la.numOfPayments.setText(""+ df.format(months));
      }else{
          if(!(validateUserInput(la.numOfPayments)))
          {
            JOptionPane.showConfirmDialog(null, "Invalid or Empty input on number of Payments entry.\nPlease correct it!","Number of Payments input Error", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
            return;
          }
          months = Integer.parseInt(la.numOfPayments.getText());

          if (interest == 0)
          {
            payment = balance / months;
          }
          else
          {
            multiplier = Math.pow(1 + monthlyInterest, months);
            payment = balance * monthlyInterest * multiplier / (multiplier - 1);
          }
          
          la.monthlyPayment.setText("" + df.format(payment));
      }
      la.newLoanAnalysis.setEnabled(true);
      la.compute.setEnabled(false);

      double loanBalance = balance;
      //Calculate all but last payment
      for(int paymentNumber = 1; paymentNumber <= months - 1; paymentNumber++)
      {
        loanBalance += loanBalance  * monthlyInterest - payment;
      }
      //final payment
      double finalPayment = loanBalance;
      if(finalPayment > payment)
      {
        loanBalance += loanBalance * monthlyInterest - payment;
        finalPayment = loanBalance;
        months++;
        la.numOfPayments.setText(""+df.format(months));
      }
      double totalPayment = payment * (months - 1) + finalPayment;
      String analysis = "Loan Balance: $"+df.format(balance)+"\nInterest Rate: "+df.format(interest)+"% \n\n"+(months-1)+" payments of $"+df.format(payment)+"\nFinal Payment of: $"+df.format(finalPayment) +"\nTotal payment of: $"+df.format(totalPayment)+"\nInterest paid: $"+df.format(totalPayment - balance);
      
      la.loanAnalysis.setText(analysis);

    }
  private boolean validateUserInput(TextField tf)
    {
      String input = tf.getText().trim();
      if (input.length() == 0){
        tf.requestFocus();
        return false;
      }
      boolean hasDecimal = false;
      for(int index = 0; index < input.length();index++)
      {
        char ch = input.charAt(index);
        if (ch == '.')
        {
          if(hasDecimal)
          {
            tf.requestFocus();
            return false;
          }
          hasDecimal = true;
        }
        else if (!(ch <= '9' && ch >= '0')){
          tf.requestFocus();
          return false;
        }
          
      }
      return true;
    }
       
}