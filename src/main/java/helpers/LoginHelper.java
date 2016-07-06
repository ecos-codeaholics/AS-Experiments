package helpers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by snaphuman on 6/8/16.
 */
public class LoginHelper {

	@SerializedName("_id")
	private String _id;
	private String email;
	private String password;

	public String getId() { return _id; }

	public void setId(String _id) { this._id = _id; }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
