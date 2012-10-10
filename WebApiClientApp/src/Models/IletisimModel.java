package Models;
import org.json.JSONException;
import org.json.JSONObject;
public class IletisimModel {

	public String Isim;
	public String Mail;
	public String Mesaj;
	
	public JSONObject Form() throws JSONException
	{
		JSONObject Bilgiler = new JSONObject();
		Bilgiler.put("Isim", Isim);
		Bilgiler.put("Mail", Mail);
		Bilgiler.put("Mesaj", Mesaj);
		
		return Bilgiler;
		
	}
}
