package Java01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Java02 클래스는 간단한 계산기 GUI 애플리케이션을 구현합니다.
 * 이 클래스는 JFrame을 상속받아 계산기 UI를 구성하고,
 * 버튼 클릭 이벤트를 처리하여 기본적인 산술 연산을 수행합니다.
 */
public class Java02 extends JFrame {

    JButton[] buttons = new JButton[24]; // 계산기 버튼들을 저장할 JButton 객체 배열입니다.
    JLabel display; // 계산 결과를 표시할 JLabel입니다.
    String operator = ""; // 현재 연산자를 저장합니다.
    double currentResult = 0; // 누적된 결과값 저장합니다.
    boolean isDecimalUsed = false; // 소수점 사용 여부 확인
    boolean isOperatorUsed = false; // 연산자가 눌렸는지 여부 확인

    /**
     * Java02 클래스의 생성자입니다.
     * 프레임의 초기 설정을 하고, 버튼 및 디스플레이를 구성합니다.
     */
    public Java02() {
        setSize(350, 600);
        setTitle("계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        display = new JLabel("0", SwingConstants.RIGHT); // 결과 표시를 합니다.
        display.setFont(new Font("Arial", Font.PLAIN, 36)); //폰트 크기 설정을 위해 사용했습니다.
        display.setOpaque(true); // JLabel을 불투명하게 설정해서 배경색이 제대로 보이도록 설정합니다.
        display.setBackground(Color.white);
        display.setPreferredSize(new java.awt.Dimension(500, 100));
        add(panel, BorderLayout.CENTER);
        add(display, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(6, 4, 3, 3));

        Color buttonColor = Color.WHITE;
        String[] buttonLabels = {
            "%", "CE", "C", "←",
            "1/x", "x²", "²√x", "÷",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ".", "="
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setBackground(buttonColor);
            buttons[i].setPreferredSize(new java.awt.Dimension(100, 30));

            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { // 버튼 클릭을 할 때 호출되는 메서드입니다.
                    String command = e.getActionCommand(); // 클릭된 버튼의 명령어를 가져오는 것입니다.
                    new Thread(() -> handleButtonPress(command)).start(); // 버튼 클릭을 할 때 처리하는 메서드입니다.
                }
            });

            panel.add(buttons[i]);
        }

        setVisible(true);
    }

    /**
     * 버튼 클릭 시 수행되는 메서드입니다.
     * @param command 클릭된 버튼의 명령어
     */
    private void handleButtonPress(String command) {
        switch (command) { // 버튼의 명령어에 따라 분기
            case "CE":
                display.setText("0"); // 디스플레이를 0으로 초기화합니다.
                break;
            case "C":
                resetCalculator(); // 계산기를 초기화하는 메서드를 호출합니다.
                break;
            case "←":
                String currentText = display.getText(); // 현재 디스플레이 텍스트 가져옵니다.
                if (currentText.length() > 1) {
                    display.setText(currentText.substring(0, currentText.length() - 1)); // 마지막 글자를 제거합니다
                } else {
                    display.setText("0"); // 한 자리가 남았을 때 누르면 0으로 초기화합니다.
                }
                break;
            case "=":
                if (!isOperatorUsed) { // 연산자가 아직 안 눌렸을 경우입니다.
                    double secondNum; // 두 번째 숫자
                    try {
                        secondNum = Double.parseDouble(display.getText()); // 디스플레이에서 숫자를 가져옵니다.
                    } catch (NumberFormatException e) {
                        display.setText("Error"); // 숫자 변환 오류 시 "Error" 표시가 납니다.
                        return; // 메서드를 종료합니다.
                    }
                    currentResult = calculate(currentResult, secondNum, operator); // 계산을 수행합니다.
                    display.setText(formatResult(currentResult)); // 결과를 디스플레이에 표시합니다.
                    operator = ""; // 연산자를 초기화합니다.
                    isDecimalUsed = false; // 소수점 사용 여부 초기화합니다.
                    isOperatorUsed = false; // 연산자 눌림 상태 초기화합니다.
                } 
                break;
            case "+":
            case "-":
            case "x":
            case "÷":
                if (!isOperatorUsed) { // 아직 눌리지 않았을 경우입니다.
                    if (!operator.isEmpty()) { // 이전 연산자가 있을 경우입니다.
                        currentResult = calculate(currentResult, Double.parseDouble(display.getText()), operator);
                    } else { // 첫 번째 입력값
                        currentResult = Double.parseDouble(display.getText()); // 첫 번째 입력값 저장합니다.
                    }
                    operator = command.equals("x") ? "*" : command;  // 연산자 설정 (x는 *로 변환)
                    display.setText(formatResult(currentResult)); // 결과를 표시합니다.
                    isOperatorUsed = true; // 연산자 눌림 상태 설정합니다.
                    isDecimalUsed = false; // 소수점 사용 여부 초기화합니다.
                }
                break;
            case "x²": 
                double baseNum = Double.parseDouble(display.getText()); // 현재 숫자를 가져옵니다.
                currentResult = Math.pow(baseNum, 2); // 제곱 계산
                display.setText(formatResult(currentResult)); // 결과를 디스플레이에 표시합니다.
                isOperatorUsed = false; // 연산자 눌림 상태 초기화합니다.
                break;
            case "%":
                double num = Double.parseDouble(display.getText()); 
                currentResult = currentResult + (currentResult * (num / 100)); // 현재 결과의 퍼센트 계산 후 더하기
                display.setText(formatResult(currentResult)); // 결과 표시합니다.
                operator = ""; // 연산자 초기화합니다.
                isDecimalUsed = false; // 소수점 사용 여부를 초기화합니다.
                break;
            case "²√x":  
                num = Double.parseDouble(display.getText()); // 현재 숫자를 가져옵니다.
                display.setText(formatResult(Math.sqrt(num))); // 결과를 디스플레이에 표시합니다.
                isDecimalUsed = false; // 소수점 사용 여부 초기화합니다.
                break;
            case "1/x": 
                num = Double.parseDouble(display.getText()); // 현재 숫자를 가져옵니다.
                display.setText(formatResult(1 / num)); // 결과를 디스플레이에 표시합니다.
                isDecimalUsed = false; // 소수점 사용 여부 초기화합니다.
                break;
            case "+/-": 
                double value = Double.parseDouble(display.getText()); // 현재 숫자를 가져옵니다.
                display.setText(formatResult(value * -1)); // 부호 반전 결과를 표시합니다.
                break;
            case ".": 
                if (!isDecimalUsed) {  // 소수점이 사용되지 않았을 경우입니다.
                    display.setText(display.getText() + "."); // 현재 숫자에 소수점을 추가합니다.
                    isDecimalUsed = true; // 소수점 사용 여부를 설정합니다.
                }
                break;
            default: // 숫자 입력 처리
                if (isOperatorUsed) { 
                    display.setText(command); // 연산자 후 첫 숫자 입력합니다.
                    isOperatorUsed = false;   // 연산자 눌림 상태 초기화합니다.
                } else if (display.getText().equals("0")) {
                    display.setText(command); // 0이 있을 때 숫자를 입력합니다.
                } else {
                    display.setText(display.getText() + command); // 기존 숫자에 추가합니다.
                } 
                break;
        }
    }

    /**
     * 두 숫자와 연산자를 받아  해당 연산을을 수행하는 메서드입니다.
     * 
     * @param firstNum 첫 번째 숫자
     * @param secondNum 두 번째 숫자
     * @param operator 수행할 연산자
     * @return 계산 결과
     */
    private double calculate(double firstNum, double secondNum, String operator) {
        switch (operator) {
            case "+":
                return firstNum + secondNum;
            case "-":
                return firstNum - secondNum;
            case "*":
                return firstNum * secondNum;
            case "÷":
                return firstNum / secondNum;
            default:
                return secondNum; // 기본적으로 두 번째 숫자를 반환합니다.
        }
    }

    /**
     * 주어진 계산 결과를 포맷하여 문자열로 반환하는 메서드입니다.
     * 
     * @param result 계산 결과
     * @return 포맷된 문자열
     */
    private String formatResult(double result) {
        if (result % 1 == 0) {
            return String.valueOf((int) result); // 정수일 경우 소수점 없이 반환합니다.
        } else {
            return String.valueOf(result); // 그대로 반환합니다.
        }
    }

    /**
     * 계산기를 초기화하는 메서드입니다.
     * 디스플레이와 내부 상태를 초기 상태로 설정합니다.
     */
    private void resetCalculator() {
        display.setText("0"); // 디스플레이를 "0"으로 초기화합니다.
        currentResult = 0; // 누적 결과 초기화합니다.
        isDecimalUsed = false; // 소수점 사용 여부 초기화합니다.
        operator = "";  // 연산자 초기화합니다.
        isOperatorUsed = false; // 연산자 눌림 상태 초기화합니다.
      }

      /**
       * 프로그램의 진입점입니다.
       * Java02 클래스의 인스턴스를 생성하여 계산기 GUI를 실행합니다.
       * 
       * @param args 커맨드라인 인수
       */
      public static void main(String[] args) {
          new Java02();
      }
  }