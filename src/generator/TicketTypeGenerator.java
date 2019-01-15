package generator;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import org.hibernate.HibernateException;
//import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TicketTypeGenerator implements IdentifierGenerator {

//	public Serializable generate(SessionImplementor session, Object object)
	public Serializable generate(SharedSessionContractImplementor session, Object object)
			throws HibernateException {

		String prefix = "ET";
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//		String strTimeNow  = dateFormat.format(new Date(System.currentTimeMillis()));
//		
//		StringBuffer prefixBuffer = new StringBuffer("R_").append(strTimeNow).append("_");
//		String prefix = prefixBuffer.toString();
		String TICTYPE_NO = null;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TICTYPE_SEQ.NEXTVAL as nextval FROM DUAL");
			rs.next();
			int nextval = rs.getInt("nextval");
			String strLPAD = String.format("%06d", nextval);
			TICTYPE_NO = prefix + strLPAD;
//			resale_ordno = prefix + nextval;
			
//			con.close();   //hibernate cant close it
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		System.out.println("TicketTypeGenerator :output of generator="+TICTYPE_NO);
		return TICTYPE_NO;
	}
}