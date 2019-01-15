package generator;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import org.hibernate.HibernateException;
//import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TicketOrderGenerator implements IdentifierGenerator {

//	public Serializable generate(SessionImplementor session, Object object)
	public Serializable generate(SharedSessionContractImplementor session, Object object)
			throws HibernateException {

//		String prefix = "EE";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String strTimeNow  = dateFormat.format(new Date(System.currentTimeMillis()));
		
		StringBuffer prefixBuffer = new StringBuffer("TO_").append(strTimeNow).append("_");
		String prefix = prefixBuffer.toString();
		String ticket_order_no = null;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TICKET_ORDER_SEQ.NEXTVAL as nextval FROM DUAL");
			rs.next();
			int nextval = rs.getInt("nextval");
			String strLPAD = String.format("%06d", nextval);
			ticket_order_no = prefix + strLPAD;
//			con.close();   //hibernate cant close it
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		System.out.println("TicketOrderGenerator :output of generator="+ticket_order_no);
		return ticket_order_no;
	}
}