import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout; 
import java.awt.GridBagConstraints;  
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


class LoanAssistant {
  JFrame frame = null;
  GridBagConstraints gbc;
  TextField interestRate;
  TextField LoanBalance;
  TextField numOfPayments;
  TextField monthlyPayment;
  TextArea loanAnalysis;
  Button firstMode;
  Button secondMode;
  Button compute;
  Button newLoanAnalysis;
  boolean computePayment;
  
  public static void main(String[] args) {
    LoanAssistant la = new LoanAssistant();
  }
  LoanAssistant() {
    frame = new JFrame("Loan Assistant");
    frame.setSize(500, 300);
    frame.setLocation(5, 5);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new GridBagLayout());
    gbc = new GridBagConstraints();
    this.setup();
    frame.setVisible(true);
  }
  private void setup()
  {
    computePayment = true;
    Font myFont = new Font("Arial", Font.PLAIN, 16);
    Font myFont2 = new Font("Courier New", Font.PLAIN, 14);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10,10,0,0);
    JLabel loanBalanceLabel = new JLabel(" Loan Balance");
    loanBalanceLabel.setFont(myFont);
    frame.add(loanBalanceLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 0, 10);
    LoanBalance = new TextField();
    LoanBalance.setColumns(30);
    LoanBalance.setPreferredSize(new Dimension(100,25));
    LoanBalance.setFont(myFont);
    LoanBalance.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          interestRate.requestFocus();
        }
      }
    );
    frame.add(LoanBalance, gbc);


    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.insets = new Insets(10,10,0,0);
    JLabel interestRateLabel = new JLabel(" Interest Rate(%)");
    interestRateLabel.setFont(myFont);
    frame.add(interestRateLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.insets = new Insets(10, 10, 0, 10);
    interestRate = new TextField();
    interestRate.setColumns(30);
    interestRate.setFont(myFont);
    interestRate.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          if(computePayment)
          {
            numOfPayments.requestFocus();
          }
          else{
            monthlyPayment.requestFocus();
          }
        }
      }
    );
    frame.add(interestRate, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.insets = new Insets(10,10,0,0);
    JLabel numOfPaymentsLabel = new JLabel(" Number of payments");
    numOfPaymentsLabel.setFont(myFont);
    frame.add(numOfPaymentsLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.insets = new Insets(10, 10, 0, 10);
    numOfPayments = new TextField();
    numOfPayments.setColumns(30);
    numOfPayments.setFont(myFont);
    numOfPayments.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          compute.requestFocus();
        }
      }
    );
    frame.add(numOfPayments, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    JLabel monthlyPaymentsLabel = new JLabel(" Monthly payments");
    gbc.insets = new Insets(10,10,0,0);

    monthlyPaymentsLabel.setFont(myFont);
    gbc.insets = new Insets(10,10,0,0);
    frame.add(monthlyPaymentsLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.insets = new Insets(10, 10, 0, 10);
    monthlyPayment = new TextField();
    monthlyPayment.setBackground(new Color(255,255,102));
    monthlyPayment.setEditable(false);
    monthlyPayment.setColumns(30);
    monthlyPayment.setFont(myFont);
    monthlyPayment.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          compute.requestFocus();
        }
      }
    );
    frame.add(monthlyPayment, gbc);

    gbc.gridx = 3;
    gbc.gridy = 0;
    gbc.insets = new Insets(10,10,0,0);
    JLabel loanAnalysisLabel = new JLabel(" Loan analysis");
    loanAnalysisLabel.setFont(myFont);
    frame.add(new JLabel(" Loan Anaylsis"), gbc);

    gbc.gridx = 3;
    gbc.gridy = 1;
    gbc.gridheight = 4;
    gbc.insets = new Insets(0,10,0,10);
    loanAnalysis = new TextArea();
    loanAnalysis.setFont(myFont2);
    loanAnalysis.setEditable(false);
    frame.add(loanAnalysis, gbc);

    GridBagConstraints paymentConstraint = new GridBagConstraints();
    paymentConstraint.gridx = 0;
    paymentConstraint.gridy = 4;
    paymentConstraint.gridwidth = 2;
    paymentConstraint.insets = new Insets(10,0,0,0);
    paymentConstraint.anchor = paymentConstraint.CENTER;
    compute = new Button("Compute Monthly Payment");
    ComputePayment cp = new ComputePayment(this);
    compute.addActionListener(cp);
    frame.add(compute, paymentConstraint);
    
    GridBagConstraints analysisConstraint = new GridBagConstraints();
    analysisConstraint.gridx = 0;
    analysisConstraint.gridy = 5;
    analysisConstraint.gridwidth = 2;
    analysisConstraint.insets = new Insets(10,0,10,0);
    analysisConstraint.anchor = analysisConstraint.CENTER;
    newLoanAnalysis = new Button("New Loan Analysis");

    newLoanAnalysis.setEnabled(false);
    newLoan laz = new newLoan(this);
    newLoanAnalysis.addActionListener(laz);
    frame.add(newLoanAnalysis, analysisConstraint);

    GridBagConstraints exitConstraint = new GridBagConstraints();
    exitConstraint.gridx = 3;
    exitConstraint.gridy = 5;
    exitConstraint.gridwidth = 1;
    exitConstraint.anchor = exitConstraint.CENTER;
    Button exit = new Button("Exit");

    exit.addActionListener(
        new ActionListener(){
          public void actionPerformed(ActionEvent e){
            System.exit(0);
          }
        }
    );
    frame.add(exit, exitConstraint);

    GridBagConstraints gbfirstMode = new GridBagConstraints();
    gbfirstMode.gridx = 2;
    gbfirstMode.gridy = 2;
    gbfirstMode.insets = new Insets(10,0,0,0);
    firstMode = new Button("X");
    SwitchMode changeToSecond = new SwitchMode(this);
    firstMode.addActionListener(changeToSecond);
    frame.add(firstMode, gbfirstMode);

    GridBagConstraints gbsecondMode = new GridBagConstraints();
    gbsecondMode.gridx = 2;
    gbsecondMode.gridy = 3;
    gbsecondMode.insets = new Insets(10,0,0,0);
    secondMode = new Button("X");
    secondMode.setVisible(false);
    SwitchMode changeToFirst = new SwitchMode(this);
    secondMode.addActionListener(changeToFirst);
    frame.add(secondMode, gbsecondMode);

    

    



  }
}
