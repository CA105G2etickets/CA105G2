package generator;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import org.hibernate.HibernateException;
//import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class SeatingAreaGenerator implements IdentifierGenerator {

//	public Serializable generate(SessionImplementor session, Object object)
	public Serializable generate(SharedSessionContractImplementor session, Object object)
			throws HibernateException {

		String prefix = "ES";
		String ticarea_no = null;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TICAREA_SEQ.NEXTVAL as nextval FROM DUAL");
			rs.next();
			int nextval = rs.getInt("nextval");
			String strLPAD = String.format("%08d", nextval);
			ticarea_no = prefix + strLPAD;
			
//			con.close();   //hibernate 5 cant close it
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		System.out.println("SeatingAreaGenerator :output of generator="+ticarea_no);
		return ticarea_no;
	}
}