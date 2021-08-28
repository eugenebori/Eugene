import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main extends JFrame { //���� ������. 
									 //�̺�Ʈ�� ���� ���� �����Ӱ� Day1, Day2, Day3 �� �� ���� �����ӿ� �г��� ���̸� ������ ����.
	
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
	
	
	public Main(int i) { //���� �������� �����ڿ��� ������ �Է¹޴µ�, �� ������ ���� ������ �гε��� ������.
		
		this.setSize(442, 600); //������ ������ 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X��ư�� ������ â�� ����.
		this.setResizable(false); //����� �������� ũ�⸦ �ٿ��� �÷ȴ� �� �� ����.
		this.setLocationRelativeTo(null); //ȭ�� ����� â�� �ߵ��� ����.
		
		if(i == 1) {
			mainpane = new MainPane(this); //i�� 1�̸�, mainpane �г� ��ü�� ����. �� �г��� ���� ���۽� �� ó�� �ߴ� ����.
			this.add(mainpane); //�����ӿ� �г� �߰�.
		} else if(i == 2) { 
			day1clear = new Day1Clear(this); //i�� 2�̸�, day1clear �г� ��ü�� ����. �� �г��� day1�̶�� ù��° ���������� ���� �� ������� Ŭ���� ȭ����.
			this.add(day1clear); //�����ӿ� �г� �߰�.
		} else if(i == 3) {
			day2clear = new Day2Clear(this); //i�� 3�̸�, day2clear �г� ��ü�� ����. �� �г��� day2��� �ι�° ���������� ���� �� ������� Ŭ���� ȭ����.
			this.add(day2clear); //�����ӿ� �г� �߰�.
			
		} else if(i == 4) { 
			day3clear = new Day3Clear(this); //i�� 4�̸�, day3clear �г� ��ü�� ����. �� �г��� day3��� ����° ���������� ���� �� ������� Ŭ���� ȭ����.
			this.add(day3clear); //�����ӿ� �г� �߰�.
		}
		
		this.setVisible(true); //�������� ���̵��� ����.
	}
	

	void MainPaneToNext() { //mainpane �гο��� ��ư Ŭ���� ����Ǵ� �޼ҵ�.
		this.remove(mainpane); //�����ӿ��� mainpane �г��� ����.
		maindescription = new MainDescription(this); //maindescription �г� ��ü�� ����.
		this.add(maindescription); //�����ӿ� maindescription �г��� �߰�.
		this.repaint(); //�����ӿ� �ٽ� �׸�.
		this.revalidate(); //�г��� ����� ������ϴ� �������� ��ȭ�� ������� ���� ���� �ֱ� ������ ����.
	}
	
	void MainDescriptionToNext() { //maindescription �гο��� ��ư Ŭ���� ����Ǵ� �޼ҵ�.
		this.remove(maindescription); //�����ӿ��� maindescription �г��� ����.
		howtoplay = new HowToPlay(this); //howtoplay �г� ��ü�� ����.
		this.add(howtoplay); //�����ӿ� howtoplay �г��� �߰�.
		this.repaint(); //�����ӿ� �ٽ� �׸�.
		this.revalidate(); //�г��� ����� ������ϴ� �������� ��ȭ�� ������� ���� ���� �ֱ� ������ ����.
	}
	
	void HowToPlayToNext(int i) { //howtoplay�гο��� ��ư Ŭ���� ����Ǵ� �޼ҵ�.
		this.remove(howtoplay); //�����ӿ��� howtoplay �г��� ����.
		
		if(i==1) {  //�μ��� 1�̸�, �� ���� ��ư�� �����ٸ�
			this.add(maindescription); //�ٽ� maindescription �г��� �߰�.
		} else if(i == 2) { //�μ��� 2�̸�, �� ���� ��ư�� �����ٸ�
			day1description = new Day1Description(this); //day1description �г� ��ü ����.
			this.add(day1description); //day1description �г��� �߰�.
		}
		this.repaint(); //�����ӿ� �ٽ� �׸�.
		this.revalidate(); //�г��� ����� ������ϴ� �������� ��ȭ�� ������� ���� ���� �ֱ� ������ ����.
	}
	
	
	void Day1DescriptionToNext() { //day1description �гο��� ��ư Ŭ���� ����Ǵ� �޼ҵ�.
		this.remove(day1description); //�����ӿ��� day1description �г��� ����.
		this.setVisible(false); //������ ��ü�� �Ⱥ��̰� ��. day1 �������� ���̰� �� ���̱� ����.
		day1 = new Day1();	//day1 Ŭ���� ��ü ����.
	}

	
	void Day1ClearToNext() { //day1clear �гο��� ��ư Ŭ���� ����Ǵ� �޼ҵ�.
		this.remove(day1clear); //�����ӿ��� day1clear �г��� ����.
		day2description = new Day2Description(this); //day2description �г� ��ü ����.
		this.add(day2description); //day2description �г��� �߰�.
		this.repaint(); //�����ӿ� �ٽ� �׸�.
		this.revalidate(); //�г��� ����� ������ϴ� �������� ��ȭ�� ������� ���� ���� �ֱ� ������ ����.
	}
	
	void Day2DescriptionToNext() { //day2description �гο��� ��ư Ŭ���� ����Ǵ� �޼ҵ�.
		this.remove(day2description); //�����ӿ��� day2description �г��� ����.
		this.setVisible(false); //������ ��ü�� �Ⱥ��̰� ��. day2 �������� ���̰� �� ���̱� ����.
		day2 = new Day2(); //day2 Ŭ���� ��ü ����.
	}
	
	
	void Day2ClearToNext() { //day2clear �гο��� ��ư Ŭ���� ����Ǵ� �޼ҵ�.
		this.remove(day2clear); //�����ӿ��� day2clear �г��� ����.
		day3description = new Day3Description(this); //day3description �г� ��ü ����.
		this.add(day3description); //day3decription �г��� �߰�.
		this.repaint(); //�����ӿ� �ٽ� �׸�.
		this.revalidate(); //�г��� ����� ������ϴ� �������� ��ȭ�� ������� ���� ���� �ֱ� ������ ����.
	}
	
	void Day3DescriptionToNext() { //day3description �гο��� ��ư Ŭ���� ����Ǵ� �޼ҵ�.
		this.remove(day3description); //�����ӿ��� day3description �г��� ����.
		this.setVisible(false); //������ ��ü�� �Ⱥ��̰� ��. day3 �������� ���̰� �� ���̱� ����.
		new Day3();	//day3 Ŭ���� ��ü ����.
	}
	
	void Day3ClearToNext() { //day3clear �гο��� ��ư Ŭ���� ����Ǵ� �޼ҵ�.
		this.remove(day3clear); //�����ӿ��� day3clear �г��� ����.
		afterday3description = new AfterDay3Description(this); //afterday3description �г� ��ü ����.
		this.add(afterday3description); //afterday3description �г��� �߰�.
		this.repaint(); //�����ӿ� �ٽ� �׸�.
		this.revalidate(); //�г��� ����� ������ϴ� �������� ��ȭ�� ������� ���� ���� �ֱ� ������ ����.
	}
	
	void AfterDay3DescriptionToNext() { //AfterDay3Description �гο��� ��ư Ŭ���� ����Ǵ� �޼ҵ�.
		this.remove(afterday3description); //�����ӿ��� afterday3description �г��� ����.
		theend = new TheEnd(this); //theend �г� ��ü ����.
		this.add(theend); //theend �г��� �߰�.
		this.repaint(); //�����ӿ� �ٽ� �׸�.
		this.revalidate(); //�г��� ����� ������ϴ� �������� ��ȭ�� ������� ���� ���� �ֱ� ������ ����.
	}
	
	void TheEndToNext() { //theend �гο��� ��ư Ŭ���� ����Ǵ� �޼ҵ�.
		System.exit(0); //�������� �������� ��.
	}
	
	public static void main(String[] args) {
		new Main(1); //Main ������ ��ü ����. �μ��� 1�� �־� ���� ���۽� �� ó�� �ߴ� mainpane �г��� ���.
	}

}
