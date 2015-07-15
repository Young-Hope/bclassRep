package danmu.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONException;

public class Main extends JFrame implements ActionListener {

	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;

	private JButton registerBtn;
	private JButton startBtn;
	private JButton pauseBtn;
	private JButton sendBtn;
	private JTextField inform;
	private JLabel showcode;
	private SpinnerListModel model;
	private JSpinner spinner;
	String code = "8SC28V";
    int limit = 2;

	private TransparentWindow transparentWindow = null;

	private void initUI() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		
		JLabel title = new JLabel("��ӭ����B����");
		showcode = new JLabel("���Ŀγ�IDΪ");
		JLabel courseIdText = new JLabel("�γ�ID:");
		registerBtn = new JButton("ע��γ�");
		startBtn = new JButton("������Ļ");
		pauseBtn = new JButton("��ͣ��Ļ");
		sendBtn = new JButton("����֪ͨ");
		inform = new JTextField("");
		inform.setFont(new Font("����", Font.PLAIN, 14));
		registerBtn.addActionListener(this);
		startBtn.addActionListener(this);
		pauseBtn.addActionListener(this);
		sendBtn.addActionListener(this);
		pauseBtn.setEnabled(false);
		
		ChangeListener listener = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				inform.setFont(new Font("����", Font.PLAIN, (int) spinner.getValue()));
			}
		};
		
		spinner = new JSpinner();
		spinner.setPreferredSize(new Dimension((screenWidth - WIDTH) / 4, 30));
		spinner.setValue(14);
		spinner.addChangeListener(listener);
		contentPanel.add(title);
		contentPanel.add(courseIdText);
		contentPanel.add(registerBtn);
		contentPanel.add(showcode);
		contentPanel.add(startBtn);
		contentPanel.add(pauseBtn);
		contentPanel.add(inform);
		contentPanel.add(spinner);
		contentPanel.add(sendBtn);
		
		this.setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2);

		this.setContentPane(contentPanel);
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);

	}

	public Main() {
		initUI();
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerBtn) {
			Engine engine;
			engine = Engine.getInstance();
			engine.setRun(true);
			RequestMessage request = new RequestMessage();
			code = request.requestCode();
			showcode.setText(code);
			
		} else if (e.getSource() == startBtn) {
			if (transparentWindow == null) {
				startBtn.setEnabled(false);
				pauseBtn.setEnabled(true);
				transparentWindow = new TransparentWindow(code,limit);
				transparentWindow.startDanmu();
			}
		} else if (e.getSource() == pauseBtn) {
			if (transparentWindow != null) {
				transparentWindow.stopDanmu();
				transparentWindow = null;
				startBtn.setEnabled(true);
				pauseBtn.setEnabled(false);
			}
		} else if (e.getSource() == sendBtn) {
			Engine engine;
			engine = Engine.getInstance();
			engine.setRun(true);
			RequestMessage request = new RequestMessage();
			try {
				request.sendInform(inform.getText(),code);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
	}

	public static void main(String[] args) {
		new Main();
	}

}
