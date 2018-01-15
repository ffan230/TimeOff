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
		// 输入一个int数，一位数则补0输出String型非一位数直接输出String型
		String obs = ob + "";
		if (obs.length() == 1) {
			return "0" + obs;
		} else {
			return obs;
		}
	}

	/**
	 * 输入具体时和分，设定关机时间。
	 * 如果设置的时间超过今天的范围，将自动设置为次日该时。
	 * 例如：现在是14:30,设置关机时间为14:20,则实际关机时间为次日14:20
	 * @param smartHour
	 * @param smartMinute
	 * @throws Exception
	 */
	public void smartTimeToShutdown(ActionEvent event) throws Exception {

		int smartHour = Integer.parseInt(JT_shi.getText());
		int smartMinute = Integer.parseInt(JT_fen.getText());
		
		MyController ha = new MyController();
		ha.cancelShutdown(event);
		
		// 取得现在的date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date类的替代品 功能一样
		Calendar rightNow = Calendar.getInstance();
		int nowy = rightNow.get(Calendar.YEAR);
		int nowM = rightNow.get(Calendar.MONTH) + 1;
		int nowd = rightNow.get(Calendar.DATE);
		int nowh = rightNow.get(Calendar.HOUR_OF_DAY);
		int nowm = rightNow.get(Calendar.MINUTE);
		int nows = rightNow.get(Calendar.SECOND);
		// 进行result的计算
		if (smartHour > nowh || smartHour == nowh && smartMinute > nowm) {
			// 同日
			// 拼接的用户选择日期减去现在日期得到的秒数
			int result = ((int) (sdf.parse(nowy + "-" + ha.needZero(nowM) + "-" + ha.needZero(nowd) + " "
					+ ha.needZero(smartHour) + ":" + ha.needZero(smartMinute) + ":" + ha.needZero(nows)).getTime())
					- (int) (sdf.parse(nowy + "-" + ha.needZero(nowM) + "-" + ha.needZero(nowd) + " "
							+ ha.needZero(nowh) + ":" + ha.needZero(nowm) + ":" + ha.needZero(nows)).getTime()))
					/ 1000;

			Runtime.getRuntime().exec("shutdown -s -t " + result);
		} else if (smartHour < nowh || smartHour == nowh && smartMinute < nowm) {
			// 不同日
			// 拼接的用户选择日期减去现在日期得到的秒数（因为跨日，需要加上86400000毫秒）
			int result = ((int) (sdf.parse(nowy + "-" + ha.needZero(nowM) + "-" + ha.needZero(nowd) + " "
					+ ha.needZero(smartHour) + ":" + ha.needZero(smartMinute) + ":" + ha.needZero(nows)).getTime()
					+ 86400000)
					- (int) (sdf.parse(nowy + "-" + ha.needZero(nowM) + "-" + ha.needZero(nowd) + " "
							+ ha.needZero(nowh) + ":" + ha.needZero(nowm) + ":" + ha.needZero(nows)).getTime()))
					/ 1000;

			Runtime.getRuntime().exec("shutdown -s -t " + result);
		} else {
			// 小时、分钟都相同的情况
			System.out.println("现在？");
		}
	}

	// 输入时分秒，表示多久之后关机
	public void timeToShutdown(ActionEvent event) throws Exception {

		int hour = Integer.parseInt(XC_shi.getText());
		int minute = Integer.parseInt(XC_fen.getText());
		int second = Integer.parseInt(XC_miao.getText());
		
		MyController ha = new MyController();
		ha.cancelShutdown(event);

		int result = hour * 3600 + minute * 60 + second;
		// 按时间执行
		Runtime.getRuntime().exec("shutdown -s -t " + result);
	}

	public void cancelShutdown(ActionEvent event) throws Exception {
		// 取消
		Runtime.getRuntime().exec("shutdown -a");
	}

	public void exit(ActionEvent event) throws Exception {
		// 取消
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