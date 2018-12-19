package com.member.model;

import java.sql.Timestamp;
import java.util.List;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(String memberFullname, String email, String phone, String idcard,
			String memberAccount, String memberPassword, Integer ewalletBalance, Timestamp creationDate,
			byte[] profilePicture, String memberStatus) {

		MemberVO member = new MemberVO();

		member.setMemberFullname(memberFullname);
		member.setEmail(email);
		member.setPhone(phone);
		member.setIdcard(idcard);
		member.setMemberAccount(memberAccount);
		member.setMemberPassword(memberPassword);
		member.setEwalletBalance(ewalletBalance);
		member.setCreationDate(creationDate);
		member.setProfilePicture(profilePicture);
		member.setMemberStatus(memberStatus);
		dao.insert(member);

		return member;
	}

	public MemberVO updateMember(String memberNo, String memberFullname, String email, String phone, String idcard,
			String memberAccount, String memberPassword, Integer ewalletBalance, Timestamp creationDate,
			byte[] profilePicture, String memberStatus) {

		MemberVO member = new MemberVO();

		member.setMemberNo(memberNo);
		member.setMemberFullname(memberFullname);
		member.setEmail(email);
		member.setPhone(phone);
		member.setIdcard(idcard);
		member.setMemberAccount(memberAccount);
		member.setMemberPassword(memberPassword);
		member.setEwalletBalance(ewalletBalance);
		member.setCreationDate(creationDate);
		member.setProfilePicture(profilePicture);
		member.setMemberStatus(memberStatus);
		dao.update(member);

		return member;
	}

	public void deleteMember(String memberNo) {
		dao.delete(memberNo);
	}

	public MemberVO getOneMember(String memberNo) {
		return dao.findByPrimaryKey(memberNo);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
}
