package bean;

public class UserBean {
	private int uId;
	private String uPassword;
	private String uName;
	private String uHead;
	private String uPost;
	private String uRepost;

	public UserBean(int uId, String uPassword, String uName, String uHead, String uPost, String uRepost) {
		super();
		this.uId = uId;
		this.uPassword = uPassword;
		this.uName = uName;
		this.uHead = uHead;
		this.uPost = uPost;
		this.uRepost = uRepost;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuHead() {
		return uHead;
	}

	public void setuHead(String uHead) {
		this.uHead = uHead;
	}

	public String getuPost() {
		return uPost;
	}

	public void setuPost(String uPost) {
		this.uPost = uPost;
	}

	public String getuRepost() {
		return uRepost;
	}

	public void setuRepost(String uRepost) {
		this.uRepost = uRepost;
	}

}
