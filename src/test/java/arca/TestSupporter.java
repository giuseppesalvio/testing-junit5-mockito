package arca;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public
class TestSupporter
{
	static
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

	public static
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
}
