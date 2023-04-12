import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calculator extends JPanel {
    JFrame frame;
    static JButton nine = new JButton("9");
    static JButton eight = new JButton("8");
    static JButton seven = new JButton("7");
    static JButton six = new JButton("6");
    static JButton five = new JButton("5");
    static JButton four = new JButton("4");
    static JButton three = new JButton("3");
    static JButton two = new JButton("2");
    static JButton one = new JButton("1");
    static JButton zero = new JButton("0");
    static JButton point = new JButton(".");
    static JButton minus = new JButton("-");
    static JButton plus = new JButton("+");
    static JButton delenie = new JButton("/");
    static JButton ymnojenie = new JButton("*");
    static JButton perevod = new JButton("x16");
    static JButton delete = new JButton("C");
    static JButton ravno = new JButton("=");
    static JButton cancel = new JButton("<-");
    static JTextField output;
    String textleft = "";
    String textright = "";
    String operation = "";
    String symbol = "";
    String sixteen = "";
    String el;
    double result;
    int delimoe;
    int ostatok;
    int chastnoe = 0;
    int desyatichnoeleft = 0;
    int desyatichnoe = 0;
    int desyatichnoeright = 0;
    int counter = 1;
    int green = (int) (Math.random() * 255);
    int blue = (int) (Math.random() * 255);
    int red = (int) (Math.random() * 255);
    Color colornum = new Color(green, blue, red);
    Color colorcancel = new Color(red, blue, green);
    Color coloroperation = new Color(blue, red, green);
    Color colorframe = new Color(red, green, blue);
    private static final Font fontnum = new Font("Times New Roman", Font.BOLD, 28);
    private final Font fontout = new Font("Times New Roman", Font.BOLD, 32);

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setSize(360, 540);
        frame.setLocationRelativeTo(null);              //Фрэйм в центре экрана
        frame.setResizable(false);                      //Фрэйм нельзя изменять размер
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(colorframe);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator calculator1 = new Calculator();
        calculator1.go();
    }

    public void go() {
        ArrayList<JButton> array = new ArrayList<>();
        array.add(nine);
        array.add(eight);
        array.add(seven);
        array.add(six);
        array.add(five);
        array.add(four);
        array.add(three);
        array.add(two);
        array.add(one);
        array.add(zero);
        array.add(point);
        array.add(minus);
        array.add(plus);
        array.add(delenie);
        array.add(ymnojenie);
        array.add(delete);
        array.add(perevod);
        array.add(ravno);
        array.add(cancel);
        for (JButton jButton : array) {
            jButton.setFont(fontnum);
            jButton.setForeground(Color.black);
            jButton.addActionListener(new Listener());
        }

        output = new JTextField();
        output.setFont(fontout);
        output.setBounds(20, 20, 300, 60);
        output.setBackground(Color.black);
        output.setForeground(Color.white);
        frame.add(output);
        output.requestFocus();                          //Помещаем курсор в поле, чтобы сразу печатать
        output.addActionListener(new Listener());

        cancel.setBounds(260, 100, 60, 60);
        cancel.setBackground(colorcancel);
        frame.add(cancel);

        nine.setBounds(20, 180, 60, 60);
        nine.setBackground(colornum);
        frame.add(nine);

        eight.setBounds(100, 180, 60, 60);
        eight.setBackground(colornum);
        frame.add(eight);

        seven.setBounds(180, 180, 60, 60);
        seven.setBackground(colornum);
        frame.add(seven);

        six.setBounds(20, 260, 60, 60);
        six.setBackground(colornum);
        frame.add(six);

        five.setBounds(100, 260, 60, 60);
        five.setBackground(colornum);
        frame.add(five);

        four.setBounds(180, 260, 60, 60);
        four.setBackground(colornum);
        frame.add(four);

        three.setBounds(20, 340, 60, 60);
        three.setBackground(colornum);
        frame.add(three);

        two.setBounds(100, 340, 60, 60);
        two.setBackground(colornum);
        frame.add(two);

        one.setBounds(180, 340, 60, 60);
        one.setBackground(colornum);
        frame.add(one);

        zero.setBounds(100, 420, 60, 60);
        zero.setBackground(colornum);
        frame.add(zero);

        point.setBounds(20, 420, 60, 60);
        point.setBackground(coloroperation);
        frame.add(point);

        ravno.setBounds(180, 420, 60, 60);
        ravno.setBackground(coloroperation);
        frame.add(ravno);

        delete.setBounds(180, 100, 60, 60);
        delete.setBackground(colorcancel);
        frame.add(delete);

        plus.setBounds(260, 420, 60, 60);
        plus.setBackground(coloroperation);
        frame.add(plus);

        minus.setBounds(260, 340, 60, 60);
        minus.setBackground(coloroperation);
        frame.add(minus);

        ymnojenie.setBounds(260, 260, 60, 60);
        ymnojenie.setBackground(coloroperation);
        frame.add(ymnojenie);

        delenie.setBounds(260, 180, 60, 60);
        delenie.setBackground(coloroperation);
        frame.add(delenie);

        perevod.setBounds(20, 100, 140, 60);
        perevod.setBackground(coloroperation);
        frame.add(perevod);

        frame.repaint();                                                //Обновляем фрэйм
    }

    class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                symbol = e.getActionCommand();
                if (symbol.charAt(0) >= '0' && symbol.charAt(0) <= '9') {
                    if (operation.equals(""))
                        textleft += symbol;
                    else textright += symbol;
                    output.setText(textleft + operation + textright);
                } else if (symbol.charAt(0) == '.' && operation.equals("")) {
                    textleft += symbol;
                    output.setText(textleft + operation + textright);
                } else if (symbol.charAt(0) == '.' && !operation.equals("")) {
                    textright += symbol;
                    output.setText(textleft + operation + textright);
                } else if (symbol.charAt(0) == '-' && textleft.equals("")) {
                    textleft += symbol;
                    output.setText(textleft + operation + textright);
                } else if (symbol.charAt(0) == '-' && !operation.equals("")) {
                    textright += symbol;
                    output.setText(textleft + operation + textright);
                } else if (symbol.charAt(0) == 'C') {
                    textleft = operation = textright = sixteen = "";
                    output.setText(textleft + operation + textright);
                } else if (e.getSource() == cancel && !operation.equals("") && textright.equals("")) {
                    operation = "";
                    output.setText(textleft + operation + textright);
                } else if (e.getSource() == cancel && operation.equals("")) {
                    textleft = output.getText().substring(0, output.getText().length() - 1);
                    output.setText(textleft + operation);
                } else if (e.getSource() == cancel && !textright.equals("")) {
                    textright = textright.substring(0, textright.length() - 1);
                    output.setText(textleft + operation + textright);
                } else if (operation.equals("/") && textright.equals("0")) {
                    output.setText("На '0' делить нельзя");
                } else if (e.getSource() == perevod) {
                    textleft = output.getText();
                    if (textleft.contains("+")) {
                        String[] words = textleft.split("\\+");
                        textleft = words[0];
                        textright = words[1];
                        desyatichnoeleft = perevodVdesyatichnyu(textleft);
                        desyatichnoeright = perevodVdesyatichnyu(textright);
                        delimoe = desyatichnoeleft + desyatichnoeright;
                    }else if (textleft.contains("-")){
                        String[] words = textleft.split("-");
                        textleft = words[0];
                        textright = words[1];
                        desyatichnoeleft = perevodVdesyatichnyu(textleft);
                        desyatichnoeright = perevodVdesyatichnyu(textright);
                        delimoe = desyatichnoeleft - desyatichnoeright;
                    }else if (textleft.contains("*")){
                        String[] words = textleft.split("\\*");
                        textleft = words[0];
                        textright = words[1];
                        desyatichnoeleft = perevodVdesyatichnyu(textleft);
                        desyatichnoeright = perevodVdesyatichnyu(textright);
                        delimoe = desyatichnoeleft * desyatichnoeright;
                    }else if(textleft.contains("/")) {
                        String[] words = textleft.split("/");
                        textleft = words[0];
                        textright = words[1];
                        desyatichnoeleft = perevodVdesyatichnyu(textleft);
                        desyatichnoeright = perevodVdesyatichnyu(textright);
                        delimoe = desyatichnoeleft / desyatichnoeright;
                    }
                    while (delimoe != 0) {
                        if (16 * chastnoe > delimoe) {
                            --chastnoe;
                            ostatok = delimoe - (chastnoe * 16);
                            delimoe = chastnoe;
                            chastnoe = 0;
                            sixteen += switch (ostatok) {
                                case 10 -> "A";
                                case 11 -> "B";
                                case 12 -> "C";
                                case 13 -> "D";
                                case 14 -> "E";
                                case 15 -> "F";
                                default -> String.valueOf(ostatok);
                            };
                        } else chastnoe++;
                    }
                    sixteen = new StringBuilder(sixteen).reverse().toString();
                    output.setText(sixteen);
                    sixteen = "";
                } else if (symbol.charAt(0) == '=') {
                    result = switch (operation) {
                        case "+" -> Double.parseDouble(textleft) + Double.parseDouble(textright);
                        case "-" -> Double.parseDouble(textleft) - Double.parseDouble(textright);
                        case "/" -> Double.parseDouble(textleft) / Double.parseDouble(textright);
                        case "*" -> Double.parseDouble(textleft) * Double.parseDouble(textright);
                        default -> Double.parseDouble(textleft);
                    };
                    textleft = String.valueOf(result);
                    output.setText(textleft);
                    operation = textright = "";
                } else {
                    operation = symbol;
                    textleft = output.getText();
                    output.setText(textleft + operation + textright);
                }
            }else {
                textleft = output.getText();
                if (textleft.contains("+")){
                    String[] words = textleft.split("\\+");
                    output.setText(String.valueOf(Double.parseDouble(words[0]) + Double.parseDouble(words[1])));
                    textleft = output.getText();
                }
                if (textleft.contains("-")) {
                    String[] words = textleft.split("-");
                    output.setText(String.valueOf(Double.parseDouble(words[0]) - Double.parseDouble(words[1])));
                    textleft = output.getText();
                }

                if (textleft.contains("*")) {
                    String[] words = textleft.split("\\*");
                    output.setText(String.valueOf(Double.parseDouble(words[0]) * Double.parseDouble(words[1])));
                    textleft = output.getText();
                }
                if (textleft.contains("/")) {
                    String[] words = textleft.split("/");
                    if (words[1].equals("0"))
                        output.setText("На '0' делить нельзя");
                    else {
                        output.setText(String.valueOf(Double.parseDouble(words[0]) / Double.parseDouble(words[1])));
                        textleft = output.getText();
                    }
                }
            }
        }
    }
    public int perevodVdesyatichnyu(String text){
        desyatichnoe = 0;
        counter = 1;
        for (int i = 0; i < text.length(); i++) {
            el = String.valueOf(text.charAt(i));
            switch (el) {
                case "A" -> {
                    el = "10";
                    desyatichnoe += Integer.parseInt(el) * Math.pow(16, text.length() - counter);
                }
                case "B" -> {
                    el = "11";
                    desyatichnoe += Integer.parseInt(el) * Math.pow(16, text.length() - counter);
                }
                case "C" -> {
                    el = "12";
                    desyatichnoe += Integer.parseInt(el) * Math.pow(16, text.length() - counter);
                }
                case "D" -> {
                    el = "13";
                    desyatichnoe += Integer.parseInt(el) * Math.pow(16, text.length() - counter);
                }
                case "E" -> {
                    el = "14";
                    desyatichnoe += Integer.parseInt(el) * Math.pow(16, text.length() - counter);
                }
                case "F" -> {
                    el = "15";
                    desyatichnoe += Integer.parseInt(el) * Math.pow(16, text.length() - counter);
                }
                default -> desyatichnoe += Integer.parseInt(el) * Math.pow(16, text.length() - counter);

            }
            ++counter;
        }
        return desyatichnoe;
    }
}

