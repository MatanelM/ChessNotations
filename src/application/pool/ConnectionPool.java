package application.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

import application.util.Util;

public class ConnectionPool {

	private static ConnectionPool instance = null;
	private int size;
	private Stack<Connection> pool;

	private ConnectionPool() {
		size = 60;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		pool = new Stack<Connection>();
		for (int i = 0; i < size; i++) {
			Connection conn = null;

			try {
				conn = DriverManager.getConnection(Util.dbUrl, Util.sqlUser, Util.sqlPass);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			pool.push(conn);
		}
	}

	public static ConnectionPool getInstance() {
		if (instance == null)
			instance = new ConnectionPool();
		return instance;
	}

	public Connection getConnection() {
		if (this.pool.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Connection conn = this.pool.pop();
		return conn;
	}

	public void returnConnection(Connection conn) {
		this.pool.push(conn);
		if (this.pool.size() == 1)
			notify();
	}

	public void closeAllConnections() {
		for (int i = 0; i < size; i++) {
			if (this.pool.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				this.pool.pop().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
