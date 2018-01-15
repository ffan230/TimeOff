package application;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MyController implements Initializable {

	@FXML
	private Button XC_quxiaoguanji;
	@FXML
	private Button XC_queding;
	@FXML
	private Button XC_quxiao;
	@FXML
	private Button JT_quxiaoguanji;
	@FXML
	private Button JT_queding;
	@FXML
	private Button JT_quxiao;

	@FXML
	private TextField XC_shi;
	@FXML
	private TextField XC_fen;
	@FXML
	private TextField XC_miao;
	@FXML
	private TextField JT_shi;
	@FXML
	private TextField JT_fen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO (don't really need to do anything here).

	}

	public String needZero(int ob) {
		// ����һ��int����һλ����0���String�ͷ�һλ��ֱ�����String��
		String obs = ob + "";
		if (obs.length() == 1) {
			return "0" + obs;
		} else {
			return obs;
		}
	}

	/**
	 * �������ʱ�ͷ֣��趨�ػ�ʱ�䡣
	 * ������õ�ʱ�䳬������ķ�Χ�����Զ�����Ϊ���ո�ʱ��
	 * ���磺������14:30,���ùػ�ʱ��Ϊ14:20,��ʵ�ʹػ�ʱ��Ϊ����14:20
	 * @param smartHour
	 * @param smartMinute
	 * @throws Exception
	 */
	public void smartTimeToShutdown(ActionEvent event) throws Exception {

		int smartHour = Integer.parseInt(JT_shi.getText());
		int smartMinute = Integer.parseInt(JT_fen.getText());
		
		MyController ha = new MyController();
		ha.cancelShutdown(event);
		
		// ȡ�����ڵ�date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date������Ʒ ����һ��
		Calendar rightNow = Calendar.getInstance();
		int nowy = rightNow.get(Calendar.YEAR);
		int nowM = rightNow.get(Calendar.MONTH) + 1;
		int nowd = rightNow.get(Calendar.DATE);
		int nowh = rightNow.get(Calendar.HOUR_OF_DAY);
		int nowm = rightNow.get(Calendar.MINUTE);
		int nows = rightNow.get(Calendar.SECOND);
		// ����result�ļ���
		if (smartHour > nowh || smartHour == nowh && smartMinute > nowm) {
			// ͬ��
			// ƴ�ӵ��û�ѡ�����ڼ�ȥ�������ڵõ�������
			int result = ((int) (sdf.parse(nowy + "-" + ha.needZero(nowM) + "-" + ha.needZero(nowd) + " "
					+ ha.needZero(smartHour) + ":" + ha.needZero(smartMinute) + ":" + ha.needZero(nows)).getTime())
					- (int) (sdf.parse(nowy + "-" + ha.needZero(nowM) + "-" + ha.needZero(nowd) + " "
							+ ha.needZero(nowh) + ":" + ha.needZero(nowm) + ":" + ha.needZero(nows)).getTime()))
					/ 1000;

			Runtime.getRuntime().exec("shutdown -s -t " + result);
		} else if (smartHour < nowh || smartHour == nowh && smartMinute < nowm) {
			// ��ͬ��
			// ƴ�ӵ��û�ѡ�����ڼ�ȥ�������ڵõ�����������Ϊ���գ���Ҫ����86400000���룩
			int result = ((int) (sdf.parse(nowy + "-" + ha.needZero(nowM) + "-" + ha.needZero(nowd) + " "
					+ ha.needZero(smartHour) + ":" + ha.needZero(smartMinute) + ":" + ha.needZero(nows)).getTime()
					+ 86400000)
					- (int) (sdf.parse(nowy + "-" + ha.needZero(nowM) + "-" + ha.needZero(nowd) + " "
							+ ha.needZero(nowh) + ":" + ha.needZero(nowm) + ":" + ha.needZero(nows)).getTime()))
					/ 1000;

			Runtime.getRuntime().exec("shutdown -s -t " + result);
		} else {
			// Сʱ�����Ӷ���ͬ�����
			System.out.println("���ڣ�");
		}
	}

	// ����ʱ���룬��ʾ���֮��ػ�
	public void timeToShutdown(ActionEvent event) throws Exception {

		int hour = Integer.parseInt(XC_shi.getText());
		int minute = Integer.parseInt(XC_fen.getText());
		int second = Integer.parseInt(XC_miao.getText());
		
		MyController ha = new MyController();
		ha.cancelShutdown(event);

		int result = hour * 3600 + minute * 60 + second;
		// ��ʱ��ִ��
		Runtime.getRuntime().exec("shutdown -s -t " + result);
	}

	public void cancelShutdown(ActionEvent event) throws Exception {
		// ȡ��
		Runtime.getRuntime().exec("shutdown -a");
	}

	public void exit(ActionEvent event) throws Exception {
		// ȡ��
		System.exit(0);;
	}
	
//	// When user click on myButton
//	// this method will be called.
//	public void showDateTime(ActionEvent event) {
//		Date now = new Date();
//
//		DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
//		String dateTimeString = df.format(now);
//		// Show in VIEW
//		myTextField.setText(dateTimeString);
//
//	}

}