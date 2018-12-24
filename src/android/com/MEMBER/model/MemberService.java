package android.com.MEMBER.model;

import java.util.List;

public class MemberService {
	private MemberDAO_interface memberDAO;
	
	public MemberService() {
		memberDAO = new MemberJNDIDAO();
	}
	
	public MemberVO addMember(String memberFullname, String email, String phone, String idcard,
			String memberAccount, String memberPassword, Integer ewalletBalance,
			String memberStatus, String thirduid) {
		MemberVO member = new MemberVO();
		member.setMemberFullname(memberFullname);
		member.setEmail(email);
		member.setPhone(phone);
		member.setIdcard(idcard);
		member.setMemberAccount(memberAccount);
		member.setMemberPassword(memberPassword);
		member.setEwalletBalance(ewalletBalance);
//		member.setCreationDate(creationDate);
//		member.setProfilePicture(profilePicture);
		member.setMemberStatus(memberStatus);
		member.setThirdUID(thirduid);
		memberDAO.insert(member);
		
		return member;
	}
	
	public MemberVO updateMember(String memberNo, String memberFullname, String email, String phone,
			String memberAccount, String memberPassword,
			String memberStatus) {
		MemberVO member = new MemberVO();
		member.setMemberNo(memberNo);
		member.setMemberFullname(memberFullname);
		member.setEmail(email);
		member.setPhone(phone);
//		member.setIdcard(idcard);
		member.setMemberAccount(memberAccount);
		member.setMemberPassword(memberPassword);
//		member.setEwalletBalance(ewalletBalance);
//		member.setCreationDate(creationDate);
//		member.setProfilePicture(profilePicture);
		member.setMemberStatus(memberStatus);
//		member.setThirduid(thirduid);
		memberDAO.update(member);
		return member;
	}
	
	public void deleteMember(String memberNo) {
		memberDAO.delete(memberNo);
	}
	
	public MemberVO findByPrimaryKey(String memberNo) {
		return	memberDAO.findByPrimaryKey(memberNo);
	}
	
	public List<MemberVO> getAll() {
		return memberDAO.getAll();
	}
	
	public String isMember(String userName, String userPassword,String thirdUID) {
		return memberDAO.isMember(userName, userPassword, thirdUID);
	}
	
	public byte[] getImage(String memberNo) {
		return memberDAO.getImage(memberNo);
	}
	
}
