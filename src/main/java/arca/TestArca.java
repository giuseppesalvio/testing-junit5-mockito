package arca;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

@SpringBootApplication
public
class TestArca
{

	private static       Connection con;
	private static       Statement  stmt;
	private static final String     user                        = "homeinsurance_pp";
	private static final String     password                    = "homeinsurance!DMZ01";
	private static final String     url                         =
		"jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=as-ractest-scan)(PORT=1521))(LOAD_BALANCE=YES)(FAILOVER=YES)(CONNECT_DATA=(SERVICE_NAME=TEST11G.GRUPPOARCA.IT)))";
	private static final String     numeroRegistrazioni         =
		readAllBytesJava7("src/main/resources/numero_registrazioni.sql");
	private static final String     numeroAccessi               =
		readAllBytesJava7("src/main/resources/numero_accessi.sql");
	private static final String     numeroAccessiSsoGiornaliero =
		readAllBytesJava7("src/main/resources/numero_accessi_sso_giornaliero.sql");
	private static final String     numeroDocumentiScaricati    =
		readAllBytesJava7("src/main/resources/numero_tipologia_doc_scaricati_giornalmente.sql");

	public static
	void execute()
	throws SQLException, ClassNotFoundException
	{
		connectToDB();

		executeQueryAndGenerateExcel(numeroRegistrazioni, "numeroRegistrazioni.xlsx");

		executeQueryAndGenerateExcel(numeroAccessi, "numeroAccessi.xlsx");

		executeQueryAndGenerateExcel(numeroAccessiSsoGiornaliero, "numeroAccessiSsoGiornaliero.xlsx");

		executeQueryAndGenerateExcel(numeroDocumentiScaricati, "numeroDocumentiScaricati.xlsx");

		disconnectToDB();
	}

	private static
	void executeQueryAndGenerateExcel(String query, String sheetName)
	throws SQLException
	{
		ResultSet rs = executeQuery(stmt, query);
		Map<Integer, Object[]> stringMap = convertResultSetToMap(rs);
		ExcelHandler.writeExcel(stringMap, sheetName);
	}

	private static
	Map<Integer, Object[]> convertResultSetToMap(ResultSet rs)
	throws SQLException
	{
		Map<Integer, Object[]> stringMap = new TreeMap<Integer, Object[]>();

		ResultSetMetaData rsmd = rs.getMetaData();

		int columnsNumber = rsmd.getColumnCount();

		Object[] head = new Object[columnsNumber];
		int i = 1;

		for (int c = 1; c <= columnsNumber; ++c)
		{
			head[c - 1] = rsmd.getColumnName(c);
		}
		stringMap.put(i, head);
		i++;

		while (rs.next())
		{
			Object[] row = new Object[columnsNumber];
			for (int c = 1; c <= columnsNumber; ++c)
			{
				row[c - 1] = rs.getString(c);
			}

			stringMap.put(i, row);

			i++;
		}

		return stringMap;
	}

	private static
	ResultSet executeQuery(Statement stmt, String sql)
	throws SQLException
	{
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	private static
	Statement connectToDB()
	throws SQLException, ClassNotFoundException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, user, password);

		stmt = con.createStatement();
		return stmt;
	}

	private static
	void disconnectToDB()
	throws SQLException
	{
		con.close();
	}

	private static
	String readAllBytesJava7(String filePath)
	{
		String content = "";

		try
		{
			String absolutePath =
				FileSystems.getDefault().getPath(filePath).normalize().toAbsolutePath().toString().replace("\\", "/");
			content = new String(Files.readAllBytes(Paths.get(absolutePath)));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return content;
	}
}
