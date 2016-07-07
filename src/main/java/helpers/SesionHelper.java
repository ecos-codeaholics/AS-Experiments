package helpers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by snaphuman on 6/8/16.
 */
public class SesionHelper {

	@SerializedName("_id")
	private String _id;
	private String email;
	private String userProfile;

	public String getId() { return _id; }

	public void setId(String _id) { this._id = _id; }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

}
