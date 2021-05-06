package arca;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest(classes = StartApplication.class)
class TestArcaTest
	extends TestSupporter
{

	@Autowired
	JdbcTemplate jdbcTemplate;

	/*private static Statement  stmt;
	private static Connection con;
*/
	private static final String numeroAccessiSSoGiornaliero =
		readAllBytesJava7("src/main/resources/numero_accessi_sso_giornaliero.sql");

	public
	void createTableAndConnect()
	throws SQLException
	{
		//stmt = connectToH2();
/*
		stmt.executeUpdate("create schema HOMEINSURANCE");

		String sql = "create table STATISTICHE_ACCESSO ( " +
		             "    ID_ACCESSO      number(15, 0) primary key, " +
		             "    CF              varchar2(16 char), " +
		             "    USERNAME        varchar2(30 char), " +
		             "    STATO           varchar2(40 char), " +
		             "    FLG_ACCESSO_SSO varchar2(1 char), " +
		             "    BANCA_SSO       varchar2(10 char), " +
		             "    TMST_ACCESSO    timestamp(3), " +
		             "    DISPOSITIVO     varchar2(50 char) " +
		             ")";

		stmt.executeUpdate(sql);*/
	}

	public
	void dropTableAndDisconnect()
	throws SQLException
	{
		//	stmt.executeUpdate("drop table STATISTICHE_ACCESSO");
		//	con.close();
	}

	@Test
	public
	void provaTest()
	throws SQLException
	{

		jdbcTemplate.update(
			"insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16156','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('29-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop');");
		jdbcTemplate.update(
			"insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16157','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('30-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop');");

		jdbcTemplate.update(
			"insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16158','VSNLCA66B58L781P','aliceavesani','STATO_UTENTE_ATTIVO','S','Bper',TO_TIMESTAMP('29-APR-21 16:57:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop');");
		jdbcTemplate.update(
			"insert into STATISTICHE_ACCESSO (ID_ACCESSO,CF,USERNAME,STATO,FLG_ACCESSO_SSO,BANCA_SSO,TMST_ACCESSO,DISPOSITIVO) values ('16155','pippoplutopaperi','aliceavesani','STATO_UTENTE_ATTIVO','S','Sondrio',TO_TIMESTAMP('29-APR-21 16:58:29,000000000','DD-MON-RR HH24:MI:SSXFF'),'desktop');");

		List<ConteggioAccessiSSO> lista = jdbcTemplate.query(numeroAccessiSSoGiornaliero,
		                                                     (rs, rowNum) -> new ConteggioAccessiSSO(rs.getString("DATA"),
		                                                                                             rs.getString(
			                                                                                             "BANCA_SSO"),
		                                                                                             rs.getInt(
			                                                                                             "conteggioAccessi")));

		//ResultSet resultSet = jdbcTemplate.query(numeroAccessiSSoGiornaliero, rs -> rs);
		//Map<Integer, Object[]> stringMap = convertResultSetToMap(resultSet);



		jdbcTemplate.query(numeroAccessiSSoGiornaliero, (rs, rownum) -> stringMap[rownum] = convertResultSetToMap(rs));


/*
		Assertions.assertThat(stringMap.size()).isEqualTo(4);

		Assertions.assertThat("DATA").isEqualTo(stringMap.get(1)[0]);
		Assertions.assertThat("BANCA_SSO").isEqualTo(stringMap.get(1)[1]);
		Assertions.assertThat("CONTEGGIO").isEqualTo(stringMap.get(1)[2]);*/
/*
		assertEquals("30/04/2021", stringMap.get(2)[0]);
		assertEquals("Sondrio", stringMap.get(2)[1]);
		assertEquals("1", stringMap.get(2)[2]);

		assertEquals("29/04/2021", stringMap.get(3)[0]);
		assertEquals("Bper", stringMap.get(3)[1]);
		assertEquals("1", stringMap.get(3)[2]);

		assertEquals("29/04/2021", stringMap.get(4)[0]);
		assertEquals("Sondrio", stringMap.get(4)[1]);
		assertEquals("2", stringMap.get(4)[2]);*/
	}
/*
	private
	Statement connectToH2()
	throws SQLException
	{
		String url = "jdbc:h2:mem:testdb";
		con = DriverManager.getConnection(url, "sa", "password");
		con.setAutoCommit(true);
		return con.createStatement();
	}*/
}
