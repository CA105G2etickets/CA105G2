package generator;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import org.hibernate.HibernateException;
//import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ResaleOrderGenerator implements IdentifierGenerator {

//	public Serializable generate(SessionImplementor session, Object object)
	public Serializable generate(SharedSessionContractImplementor session, Object object)
			throws HibernateException {

//		String prefix = "EE";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String strTimeNow  = dateFormat.format(new Date(System.currentTimeMillis()));
		
		StringBuffer prefixBuffer = new StringBuffer("R_").append(strTimeNow).append("_");
		String prefix = prefixBuffer.toString();
		String resale_ordno = null;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT RESALE_ORD_SEQ.NEXTVAL as nextval FROM DUAL");
			rs.next();
			int nextval = rs.getInt("nextval");
			String strLPAD = String.format("%06d", nextval);
			resale_ordno = prefix + strLPAD;
//			resale_ordno = prefix + nextval;
			
//			con.close();   //hibernate cant close it
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		System.out.println("ResaleOrderGenerator:output of generator="+resale_ordno);
		return resale_ordno;
	}
}