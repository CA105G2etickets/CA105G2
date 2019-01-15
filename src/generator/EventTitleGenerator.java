package generator;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import org.hibernate.HibernateException;
//import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class EventTitleGenerator implements IdentifierGenerator {

//	public Serializable generate(SessionImplementor session, Object object)
	public Serializable generate(SharedSessionContractImplementor session, Object object)
			throws HibernateException {

		String prefix = "E";
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//		String strTimeNow  = dateFormat.format(new Date(System.currentTimeMillis()));
//		
//		StringBuffer prefixBuffer = new StringBuffer("R_").append(strTimeNow).append("_");
//		String prefix = prefixBuffer.toString();
		String EVETIT_NO = null;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT EVETIT_SEQ.NEXTVAL as nextval FROM DUAL");
			rs.next();
			int nextval = rs.getInt("nextval");
			String strLPAD = String.format("%04d", nextval);
			EVETIT_NO = prefix + strLPAD;
//			resale_ordno = prefix + nextval;
			
//			con.close();   //hibernate cant close it
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		System.out.println("EventTitleGenerator :output of generator="+EVETIT_NO);
		return EVETIT_NO;
	}
}