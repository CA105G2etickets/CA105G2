package android.com.MEMBER.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class MemberVO {
	
	private String memberNo;
	private String memberFullname;
	private String email;
	private String phone;
	private String idcard;
	private String memberAccount;
	private String memberPassword;
	private Integer ewalletBalance;
	private Timestamp creationDate;
	private byte[] profilePicture;
	private String memberStatus;
	private String thirdUID;

	public String getThirdUID() {
		return thirdUID;
	}

	public void setThirdUID(String thirdUID) {
		this.thirdUID = thirdUID;
	}

	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String memberNo, String memberFullname, String email, String phone, String idcard,
			String memberAccount, String memberPassword, Integer ewalletBalance, Timestamp creationDate,
			byte[] profilePicture, String memberStatus) {
		super();
		this.memberNo = memberNo;
		this.memberFullname = memberFullname;
		this.email = email;
		this.phone = phone;
		this.idcard = idcard;
		this.memberAccount = memberAccount;
		this.memberPassword = memberPassword;
		this.ewalletBalance = ewalletBalance;
		this.creationDate = creationDate;
		this.profilePicture = profilePicture;
		this.memberStatus = memberStatus;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberFullname() {
		return memberFullname;
	}

	public void setMemberFullname(String memberFullname) {
		this.memberFullname = memberFullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public Integer getEwalletBalance() {
		return ewalletBalance;
	}

	public void setEwalletBalance(Integer ewalletBalance) {
		this.ewalletBalance = ewalletBalance;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	@Override
	public String toString() {
		return "MemberVO [memberNo=" + memberNo + ", memberFullname=" + memberFullname + ", email=" + email + ", phone="
				+ phone + ", idcard=" + idcard + ", memberAccount=" + memberAccount + ", memberPassword="
				+ memberPassword + ", ewalletBalance=" + ewalletBalance + ", creationDate=" + creationDate
				+ ", profilePicture=" + Arrays.toString(profilePicture) + ", memberStatus=" + memberStatus + "]";
	}

}
