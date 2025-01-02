import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.text.DecimalFormat;
public class Main {
    public static void main(String[] args) {
        InvestmentCalculatorFrame frame = new InvestmentCalculatorFrame();
        frame.setVisible(true);
    }
}
class InvestmentCalculatorFrame extends JFrame {
    public InvestmentCalculatorFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Investment Calculator");
        setSize(700, 600);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 0;
        constraints.weighty = 0;
        add(new JLabel("Prior Savings ($):"), constraints, panel, 0, 0, 1, 1);
        add(new JLabel("Annual Contribution ($):"), constraints, panel, 2, 0, 1, 1);
        add(new JLabel("Time (years):"), constraints, panel, 0, 1, 1, 1);
        add(new JLabel("Interest Rate (%):"), constraints, panel, 2, 1, 1, 1);
        JButton calculate = new JButton("Calculate");
        add(calculate, constraints, panel, 2, 2, 1, 1);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        NumberListener listener = new NumberListener();
        JTextField savings = new JTextField(8);
        savings.addKeyListener(listener);
        JTextField contribution = new JTextField(8);
        contribution.addKeyListener(listener);
        JTextField time = new JTextField(8);
        time.addKeyListener(listener);
        JTextField rate = new JTextField(8);
        rate.addKeyListener(listener);
        add(savings, constraints, panel, 1, 0, 1, 1);
        add(contribution, constraints, panel, 3, 0, 1, 1);
        add(time, constraints, panel, 1, 1, 1, 1);
        add(rate, constraints, panel, 3, 1, 1, 1);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 100;
        constraints.weighty = 100;
        JTextArea calculation = new JTextArea();
        calculation.setEditable(false);
        calculation.setFont(new Font("Sanserif", Font.PLAIN, 18));
        add(new JScrollPane(calculation), constraints, panel, 4, 0, 1, 2);
        calculate.addActionListener(e -> {
            calculation.setText("");
            DecimalFormat df = new DecimalFormat("$#,##0.00");
            double amount = Integer.parseInt(savings.getText());
            for (int i = 1; i <= Integer.parseInt(time.getText()); i++) {
                if (i == 1)
                    calculation.append("Year 1: " + df.format(amount) + "\n");
                else {
                    amount += Integer.parseInt(contribution.getText());
                    amount *= (Double.parseDouble(rate.getText()) / 100) + 1;
                    calculation.append("Year " + i + ": " + df.format(amount) + " \n");
                }
            }
        });
        add(panel);
    }
    public void add(Component c, GridBagConstraints constraints, Container co, int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        co.add(c, constraints);
    }
}
class NumberListener extends KeyAdapter {
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if(c < '0' || c > '9')
            e.consume();
    }
}