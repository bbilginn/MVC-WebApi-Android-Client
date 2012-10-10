package Ext;

import java.security.PublicKey;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class ThreadGet extends AsyncTask<String, Void, String> {
	DataDownloadListener dataDownloadListener;

	public void setDataDownloadListener(
			DataDownloadListener dataDownloadListener) {
		this.dataDownloadListener = dataDownloadListener;
	}

	public String Sonuc;

	protected String doInBackground(String... urls) {
		try {
			HttpClient client = new DefaultHttpClient();
			String getURL = urls[0];
			HttpGet get = new HttpGet(getURL);

			HttpResponse responseGet = client.execute(get);
			HttpEntity resEntityGet = responseGet.getEntity();
			Sonuc = EntityUtils.toString(responseGet.getEntity());

			if (resEntityGet != null) {

				// do something with the response

			}
		} catch (Exception e) {
			Sonuc = "Ýþlem Baþarýsýz";
			// TODO: handle exception
		}
		return Sonuc;

	}

	protected void onPostExecute(final String restore) {

		dataDownloadListener.dataDownloadedSuccessfully(Sonuc);

	}

	public static interface DataDownloadListener {
		void dataDownloadedSuccessfully(String data);

	}

}
