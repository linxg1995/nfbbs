package bean;

public class RepostBean {
	private int rpId;
	private int rpPid;
	private String rpContent;
	private String rpUname;
	private String rpTime;

	public RepostBean(int rpId, int rpPid, String rpContent, String rpUname, String rpTime) {
		super();
		this.rpId = rpId;
		this.rpPid = rpPid;
		this.rpContent = rpContent;
		this.rpUname = rpUname;
		this.rpTime = rpTime;
	}

	public int getRpId() {
		return rpId;
	}

	public void setRpId(int rpId) {
		this.rpId = rpId;
	}

	public int getRpPid() {
		return rpPid;
	}

	public void setRpPid(int rpPid) {
		this.rpPid = rpPid;
	}

	public String getRpContent() {
		return rpContent;
	}

	public void setRpContent(String rpContent) {
		this.rpContent = rpContent;
	}

	public String getRpUname() {
		return rpUname;
	}

	public void setRpUname(String rpUname) {
		this.rpUname = rpUname;
	}

	public String getRpTime() {
		return rpTime;
	}

	public void setRpTime(String rpTime) {
		this.rpTime = rpTime;
	}

}
