package DTO;

public class QRDTO {
	private int qrnum;
	private int pnum;
	private int qnum;
	private String qrdate;

	public String getQrdate() {
		return qrdate;
	}

	public void setQrdate(String qrdate) {
		this.qrdate = qrdate;
	}

	private String content;

	public int getQrnum() {
		return qrnum;
	}

	public void setQrnum(int qrnum) {
		this.qrnum = qrnum;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getQnum() {
		return qnum;
	}

	public void setQnum(int qnum) {
		this.qnum = qnum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
