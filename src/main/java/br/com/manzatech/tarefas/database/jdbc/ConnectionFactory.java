package br.com.manzatech.tarefas.database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public static Connection getConnection() {
		StringBuilder builder = new StringBuilder("jdbc:");
		try {
			Class.forName(DatabaseSettings.DRIVER_CLASS);
			builder.append(DatabaseSettings.DRIVER).append("://").append(DatabaseSettings.HOST);
			if (!DatabaseSettings.DATABASE_PORT.isEmpty()) {
				builder.append(":").append(DatabaseSettings.DATABASE_PORT);
			}
			builder.append("/").append(DatabaseSettings.DATABASE_NAME);
			if (!DatabaseSettings.QUERY_STRING.isEmpty()) {
				builder.append(DatabaseSettings.QUERY_STRING);
			}
			return DriverManager.getConnection(builder.toString(), DatabaseSettings.USERNAME,
					DatabaseSettings.PASSWORD);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
