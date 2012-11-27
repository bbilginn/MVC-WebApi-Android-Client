package com.example.webapiclientapp;

//import org.json.JSONArray;
//import org.json.JSONObject;

//import Ext.ThreadPost;
import Ext.ThreadGet;
import Ext.Dialogs;
import Ext.NetKontrol;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText Isim, Mail, Mesaj;
	Button GetGonder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Page_Load gibi... :)

		Isim = (EditText) findViewById(R.id.txtIsim);
		Mail = (EditText) findViewById(R.id.txtMail);
		Mesaj = (EditText) findViewById(R.id.txtMesaj);

		final Button Gonder = (Button) findViewById(R.id.btnGetGonder);

		NetKontrol NetConn = new NetKontrol();
		if (!NetConn.isConn(this)) {
			Dialogs.ToastGoster("Ýnternete eriþilemiyor.", MainActivity.this);
		} else {
			//Get gönderisi
			Gonder.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					try {
						ThreadGet Get = new ThreadGet();
						Get.execute("http://webapiornek.azurewebsites.net/api/values");
						Dialogs.ToastGoster("Gönderiliyor.", MainActivity.this);
						Get.setDataDownloadListener(new ThreadGet.DataDownloadListener() {
							public void dataDownloadedSuccessfully(String data) {
								Dialogs.ToastGoster("Ýþlem Baþarýlý, " + data,
										MainActivity.this);
							}
						});
					} catch (Exception e) {
						Dialogs.ToastGoster("Ýþlem Baþarýsýz",
								MainActivity.this);
					}

				}
			});

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
