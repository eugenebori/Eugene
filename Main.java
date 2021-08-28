import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main extends JFrame { //메인 프레임. 
									 //이벤트에 따라 메인 프레임과 Day1, Day2, Day3 이 세 개의 프레임에 패널을 붙이며 진행할 것임.
	
	MainPane mainpane; 
	MainDescription maindescription; 
	HowToPlay howtoplay;              
	Day1Description day1description; 
	Day1 day1;						
	Day1Clear day1clear;
	Day1Fail day1fail;
	Day2Description day2description;
	Day2 day2;
	Day2Clear day2clear;
	Day2Fail day2fail;
	Day3Description day3description;
	Day3 day3;
	Day3Clear day3clear;
	Day3Fail day3fail;
	AfterDay3Description afterday3description;
	TheEnd theend;
	
	
	public Main(int i) { //메인 프레임의 생성자에선 정수를 입력받는데, 이 정수에 따라 나오는 패널들을 결정함.
		
		this.setSize(442, 600); //프레임 사이즈 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X버튼을 누르면 창이 꺼짐.
		this.setResizable(false); //띄어진 프레임의 크기를 줄였다 늘렸다 할 수 없음.
		this.setLocationRelativeTo(null); //화면 가운데에 창이 뜨도록 설정.
		
		if(i == 1) {
			mainpane = new MainPane(this); //i가 1이면, mainpane 패널 객체를 생성. 이 패널은 게임 시작시 맨 처음 뜨는 것임.
			this.add(mainpane); //프레임에 패널 추가.
		} else if(i == 2) { 
			day1clear = new Day1Clear(this); //i가 2이면, day1clear 패널 객체를 생성. 이 패널은 day1이라는 첫번째 스테이지가 끝난 후 띄워지는 클리어 화면임.
			this.add(day1clear); //프레임에 패널 추가.
		} else if(i == 3) {
			day2clear = new Day2Clear(this); //i가 3이면, day2clear 패널 객체를 생성. 이 패널은 day2라는 두번째 스테이지가 끝난 후 띄워지는 클리어 화면임.
			this.add(day2clear); //프레임에 패널 추가.
			
		} else if(i == 4) { 
			day3clear = new Day3Clear(this); //i가 4이면, day3clear 패널 객체를 생성. 이 패널은 day3라는 세번째 스테이지가 끝난 후 띄워지는 클리어 화면임.
			this.add(day3clear); //프레임에 패널 추가.
		}
		
		this.setVisible(true); //프레임이 보이도록 설정.
	}
	

	void MainPaneToNext() { //mainpane 패널에서 버튼 클릭시 실행되는 메소드.
		this.remove(mainpane); //프레임에서 mainpane 패널을 없앰.
		maindescription = new MainDescription(this); //maindescription 패널 객체를 생성.
		this.add(maindescription); //프레임에 maindescription 패널을 추가.
		this.repaint(); //프레임에 다시 그림.
		this.revalidate(); //패널을 지우고 재생성하는 과정에서 변화가 적용되지 않을 수도 있기 때문에 적음.
	}
	
	void MainDescriptionToNext() { //maindescription 패널에서 버튼 클릭시 실행되는 메소드.
		this.remove(maindescription); //프레임에서 maindescription 패널을 없앰.
		howtoplay = new HowToPlay(this); //howtoplay 패널 객체를 생성.
		this.add(howtoplay); //프레임에 howtoplay 패널을 추가.
		this.repaint(); //프레임에 다시 그림.
		this.revalidate(); //패널을 지우고 재생성하는 과정에서 변화가 적용되지 않을 수도 있기 때문에 적음.
	}
	
	void HowToPlayToNext(int i) { //howtoplay패널에서 버튼 클릭시 실행되는 메소드.
		this.remove(howtoplay); //프레임에서 howtoplay 패널을 없앰.
		
		if(i==1) {  //인수가 1이면, 즉 이전 버튼을 눌렀다면
			this.add(maindescription); //다시 maindescription 패널을 추가.
		} else if(i == 2) { //인수가 2이면, 즉 다음 버튼을 눌렀다면
			day1description = new Day1Description(this); //day1description 패널 객체 생성.
			this.add(day1description); //day1description 패널을 추가.
		}
		this.repaint(); //프레임에 다시 그림.
		this.revalidate(); //패널을 지우고 재생성하는 과정에서 변화가 적용되지 않을 수도 있기 때문에 적음.
	}
	
	
	void Day1DescriptionToNext() { //day1description 패널에서 버튼 클릭시 실행되는 메소드.
		this.remove(day1description); //프레임에서 day1description 패널을 없앰.
		this.setVisible(false); //프레임 자체를 안보이게 함. day1 프레임을 보이게 할 것이기 때문.
		day1 = new Day1();	//day1 클래스 객체 생성.
	}

	
	void Day1ClearToNext() { //day1clear 패널에서 버튼 클릭시 실행되는 메소드.
		this.remove(day1clear); //프레임에서 day1clear 패널을 없앰.
		day2description = new Day2Description(this); //day2description 패널 객체 생성.
		this.add(day2description); //day2description 패널을 추가.
		this.repaint(); //프레임에 다시 그림.
		this.revalidate(); //패널을 지우고 재생성하는 과정에서 변화가 적용되지 않을 수도 있기 때문에 적음.
	}
	
	void Day2DescriptionToNext() { //day2description 패널에서 버튼 클릭시 실행되는 메소드.
		this.remove(day2description); //프레임에서 day2description 패널을 없앰.
		this.setVisible(false); //프레임 자체를 안보이게 함. day2 프레임을 보이게 할 것이기 때문.
		day2 = new Day2(); //day2 클래스 객체 생성.
	}
	
	
	void Day2ClearToNext() { //day2clear 패널에서 버튼 클릭시 실행되는 메소드.
		this.remove(day2clear); //프레임에서 day2clear 패널을 없앰.
		day3description = new Day3Description(this); //day3description 패널 객체 생성.
		this.add(day3description); //day3decription 패널을 추가.
		this.repaint(); //프레임에 다시 그림.
		this.revalidate(); //패널을 지우고 재생성하는 과정에서 변화가 적용되지 않을 수도 있기 때문에 적음.
	}
	
	void Day3DescriptionToNext() { //day3description 패널에서 버튼 클릭시 실행되는 메소드.
		this.remove(day3description); //프레임에서 day3description 패널을 없앰.
		this.setVisible(false); //프레임 자체를 안보이게 함. day3 프레임을 보이게 할 것이기 때문.
		new Day3();	//day3 클래스 객체 생성.
	}
	
	void Day3ClearToNext() { //day3clear 패널에서 버튼 클릭시 실행되는 메소드.
		this.remove(day3clear); //프레임에서 day3clear 패널을 없앰.
		afterday3description = new AfterDay3Description(this); //afterday3description 패널 객체 생성.
		this.add(afterday3description); //afterday3description 패널을 추가.
		this.repaint(); //프레임에 다시 그림.
		this.revalidate(); //패널을 지우고 재생성하는 과정에서 변화가 적용되지 않을 수도 있기 때문에 적음.
	}
	
	void AfterDay3DescriptionToNext() { //AfterDay3Description 패널에서 버튼 클릭시 실행되는 메소드.
		this.remove(afterday3description); //프레임에서 afterday3description 패널을 없앰.
		theend = new TheEnd(this); //theend 패널 객체 생성.
		this.add(theend); //theend 패널을 추가.
		this.repaint(); //프레임에 다시 그림.
		this.revalidate(); //패널을 지우고 재생성하는 과정에서 변화가 적용되지 않을 수도 있기 때문에 적음.
	}
	
	void TheEndToNext() { //theend 패널에서 버튼 클릭시 실행되는 메소드.
		System.exit(0); //프레임이 닫히도록 함.
	}
	
	public static void main(String[] args) {
		new Main(1); //Main 프레임 객체 생성. 인수로 1을 주어 게임 시작시 맨 처음 뜨는 mainpane 패널을 띄움.
	}

}
