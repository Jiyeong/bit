// 우리는 프레임을 만들고시프다
package java02.test11.exam04;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class BUChatClient  extends Frame implements ActionListener { // 윈도우가 사용하여 만듬!(OS함수를 통해서)
  TextField tfServerAddr = new TextField(20);
  TextField tfName = new TextField(10);
  Button btnConnect = new Button("연결");
  TextArea taContent = new TextArea();
  TextField tfInput = new TextField(30);
  Button btnSend = new Button("보내기");

  String username;
  String serverAddress;
  
  public BUChatClient() {
    // 윈도우 준비
    Panel toolbar = new Panel(new FlowLayout(FlowLayout.LEFT));
    toolbar.add(new Label("이름 : "));
    toolbar.add(tfName);
    toolbar.add(new Label("서버 : "));
    toolbar.add(tfServerAddr);
    toolbar.add(btnConnect); 
    
    this.add(toolbar, BorderLayout.NORTH);
    this.add(taContent, BorderLayout.CENTER);

    Panel bottom = new Panel();
    bottom.add(tfInput);
    bottom.add(btnSend);

    this.add(bottom, BorderLayout.SOUTH);

    // 리스너 등록
    // 1) 윈도우 이벹르를 처리할 리스너 객체 등록
    // WindowListener 인터페이스를 구현한 객체이어야 한다.
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    

    // ActionEvent는 버튼을 눌렀을 때 발생하는 이벤트이다.
    // connectBtn.addActionListener(new MyConnectListener());

    // 실무에서는 한 번 밖에 안 쓸 객체라면 익명 이너 클래스로 정의한다.
    // 바로 직관적 코드의 가독성이 좋아진다.
    btnConnect.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // 바깥 클래스의 인스턴스 변수를 사용할 때는
        // 정확하게 바깥 클래스의 this를 지정하거나
        // 아니면 차라리 생략하라
        // 단, 로컬 변수나 이너 클래스에 같은 이름을 가진 변수가 있다면
        // 생략 불가하다.
        /*ChatClient.this.*/username = tfName.getText();
        //this.username = tfName.getText(); //NOP!!!
        serverAddress = tfServerAddr.getText();
        System.out.println("사용자 이름 : " + username);
        System.out.println("서버 주소 : " + serverAddress);
        //System.out.println("연결 버튼 눌렀네...ㅋ");

      }
    });

    // 보내기 버튼을 눌렀을 때 발생하는 이벤트이다.
    // sendBtn.addActionListener(new MySendListener());
    btnSend.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent e) {
        System.out.println("보내기 버튼 눌렀네...ㅎ");
      }
    });
  }


  public static void main(String[] args) {
    ChatClient wnd = new ChatClient();
    wnd.setSize(400, 600);
    wnd.setVisible(true);

  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == btnConnect) { //연결 버튼을 눌렀다면
      serverAddress = tfServerAddr.getText();
      System.out.println("사용자 이름 : " + username);
      System.out.println("서버 주소 : " + serverAddress);
    } else { // 보내기 버튼을 눌렀다면
      
    }
    
  }

  /*  // WindowListener를 직접 구현하지 말고,
  // 미리 구현한 WindowAdapter를 상속 받아라!
  class MyWindowListener extends WindowAdapter {
    // 다음 메서드는 윈도우에서 close 버튼을 눌렀을 때 호출된다.
    @Override
    public void windowClosing(WindowEvent e) {
      System.exit(0);
    }
  }*/
}

