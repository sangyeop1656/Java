package test01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class test01 extends JFrame {
	
	 	JButton[] buttons = new JButton[25]; //배열로 버튼을 25개를 지정했습니다.

	    public test01() {
	        setSize(520, 220); //프레임 크기 설정을 했습니다.
	        setTitle("계산기"); // 프레임 타이틀 이름을 설정했습니다.
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X를 눌렀을 때 나갈 수 있게 설정했습니다.
	  
	        
	        JPanel panel = new JPanel();     //panel을 생성했습니다.
	        JTextField text = new JTextField("0."); //텍스트필드를 생성했고 텍스트 필드의 값을 과제와 같이 0.로 설정했습니다.
	        text.setEditable(false); //텍스트필드의 값을 수정할 수 없게 false를 이용하여 설정했습니다.
	        text.setBackground(Color.white); // 텍스트 배경색을 과제와 같이 하얀색으로 설정했습니다.	        
	        add(panel,BorderLayout.CENTER);// 패널들을 중앙으로 설정했습니다.
	        add(text, BorderLayout.NORTH); // 텍스트의 위치는 북쪽으로 설정했습니다.
	     
	        panel.setLayout(new GridLayout(5, 5, 3, 3)); // 패널의 레이아웃을 5행 5열로 설정했고 과제와 같이 간격을 3으로 설정했습니다.
	       
	        Color buttonColor = Color.yellow; // 버튼 배경색을 노란색으로 설정했습니다.
	        String[] buttonLabels = {
	            "Backspace", "", "", "CE", "C", 
	            "7", "8", "9", "/", "sqrt", 
	            "4", "5", "6", "x", "%", 
	            "1", "2", "3", "-", "1/x", 
	            "0", "+/-", ".", "+", "="
	        }; // 버튼에 표시될 레이블을 배열로 정리했습니다.
	        
	        for (int i = 0; i < buttons.length; i++) {
	            buttons[i] = new JButton(buttonLabels[i]);
	            buttons[i].setBackground(buttonColor);
	        
	            if(i<3)
	            buttons[i].setForeground(Color.BLUE);
	            else if(4<i&&i<8)
	            	buttons[i].setForeground(Color.BLUE);
	            else if(4<i&&i<8)
	            	buttons[i].setForeground(Color.BLUE);
	            else if(9<i&&i<13)
	            	buttons[i].setForeground(Color.BLUE);            
	            else if(14<i&&i<18)
	            	buttons[i].setForeground(Color.BLUE);
	            else if(19<i&&i<23)
	            	buttons[i].setForeground(Color.BLUE);
	            else 
	            	buttons[i].setForeground(Color.RED);
	            panel.add(buttons[i]); 
	        }
	      //반복문을 통해 계산기의 글자 색깔을 넣었습니다. else if문을 통해 파란색 글씨를 넣어줬고,나머지 조건의 해당되지 않은 조건을 빨간색으로 설정했습니다.
	        
	        
	        
	        setVisible(true); //프레임을 화면에 나오게 했습니다
	      
	    }

	public static void main(String[] args) {
	    new test01(); //GUI05 클래스의 새로운 인스턴스를 생성하여 GUI를 실행시키게 했습니다

	}

}



