package projectJava2.formIsen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static projectJava2.formIsen.daos.DataSourceFactory.getDataSource;

public class Launcher {

	public static void main(String[] args) throws SQLException {
		App.main(args);
	}
}
