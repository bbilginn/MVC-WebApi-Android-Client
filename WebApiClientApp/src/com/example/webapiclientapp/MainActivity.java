package com.example.webapiclientapp;

//import org.json.JSONArray;
//import org.json.JSONObject;

//import Ext.ThreadPost;
import Ext.ThreadPost;
import Ext.Dialogs;
import Ext.NetKontrol;
import Models.IletisimModel;
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
			// Get gönderisi
			Gonder.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					try {
						ThreadPost PostGonder = new ThreadPost();
						Dialogs.ToastGoster(
								"Formunuz iletiliyor, lütfen bekleyiniz.",
								MainActivity.this);
						IletisimModel ilet = new IletisimModel();
						ilet.Isim = Isim.getText().toString();
						ilet.Mail = Mail.getText().toString();
						ilet.Mesaj = Mesaj.getText().toString();

						PostGonder.execute(
								"http://webapiornek.azurewebsites.net/api/values", ilet
										.Form().toString());
						PostGonder
								.setDataDownloadListener(new ThreadPost.DataDownloadListener() {
									public void dataDownloadedSuccessfully(
											String data) {
										if (data.contains("200")) {
											Dialogs.ToastGoster(
													"Mail Gönderme Baþarýlý",
													MainActivity.this);
										} else {
											Dialogs.ToastGoster(
													"Mail Gönderme Baþarýsýz. Gelen Cevap:"
															+ data,
													MainActivity.this);
										}

									}
								});
					} catch (Exception e) {
						Dialogs.ToastGoster("Ýþlem Baþarýsýz",
								MainActivity.this);
					}

				}
			});

			// Post gönderisi

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
