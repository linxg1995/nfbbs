package bean;

public class PostBean {
	private int pId;
	private String pTitle;
	private String pType;
	private String pContent;
	private String pUname;
	private String pTime;
	private int pRepost;
	private String pRptime;

	public PostBean(int pId, String pTitle, String pType, String pContent, String pUname, String pTime, int pRepost,
			String pRptime) {
		super();
		this.pId = pId;
		this.pTitle = pTitle;
		this.pType = pType;
		this.pContent = pContent;
		this.pUname = pUname;
		this.pTime = pTime;
		this.pRepost = pRepost;
		this.pRptime = pRptime;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType;
	}

	public String getpContent() {
		return pContent;
	}

	public void setpContent(String pContent) {
		this.pContent = pContent;
	}

	public String getpUname() {
		return pUname;
	}

	public void setpUname(String pUname) {
		this.pUname = pUname;
	}

	public String getpTime() {
		return pTime;
	}

	public void setpTime(String pTime) {
		this.pTime = pTime;
	}

	public int getpRepost() {
		return pRepost;
	}

	public void setpRepost(int pRepost) {
		this.pRepost = pRepost;
	}

	public String getpRptime() {
		return pRptime;
	}

	public void setpRptime(String pRptime) {
		this.pRptime = pRptime;
	}

}
