package Ext;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

public class Dialogs {

	public static void ToastGoster(String Mesajiniz, Activity ActivityName) {
		Toast.makeText(ActivityName, Mesajiniz, Toast.LENGTH_LONG).show();
	}

	public void MesajGoster(String Mesajiniz, Activity ActivityName,
			String ButonText, DialogInterface.OnClickListener Olay) {
		AlertDialog.Builder adb = new AlertDialog.Builder(ActivityName);
		adb.setMessage(Mesajiniz);
		adb.setPositiveButton(ButonText, Olay);
		adb.show();
	};

}
