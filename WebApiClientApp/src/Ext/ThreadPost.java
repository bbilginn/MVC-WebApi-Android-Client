package Ext;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class ThreadPost extends AsyncTask<String, Void, String> {
	DataDownloadListener dataDownloadListener;

	public void setDataDownloadListener(DataDownloadListener dataDownloadListener) {
		this.dataDownloadListener = dataDownloadListener;
	}

	public String Sonuc;

	protected String doInBackground(String... urls) {

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(urls[0]);
			StringEntity params = new StringEntity(urls[1].toString());

			params.setContentType("application/json;charset=UTF-8");
			params.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json;charset=UTF-8"));
			httppost.setHeader("Accept", "application/json");

			httppost.setEntity(params);
			HttpResponse response = httpclient.execute(httppost);

			Sonuc = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			Sonuc = "Ýþlem Baþarýsýz";
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
