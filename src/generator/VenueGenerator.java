package generator;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import org.hibernate.HibernateException;
//import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class VenueGenerator implements IdentifierGenerator {

//	public Serializable generate(SessionImplementor session, Object object)
	public Serializable generate(SharedSessionContractImplementor session, Object object)
			throws HibernateException {

		String prefix = "V";
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//		String strTimeNow  = dateFormat.format(new Date(System.currentTimeMillis()));
//		
//		StringBuffer prefixBuffer = new StringBuffer("R_").append(strTimeNow).append("_");
//		String prefix = prefixBuffer.toString();
		String venue_no = null;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT VENUE_SEQ.NEXTVAL as nextval FROM DUAL");
			rs.next();
			int nextval = rs.getInt("nextval");
			String strLPAD = String.format("%03d", nextval);
			venue_no = prefix + strLPAD;
//			resale_ordno = prefix + nextval;
			
//			con.close();   //hibernate cant close it
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		System.out.println("VenueGenerator :output of generator="+venue_no);
		return venue_no;
	}
}