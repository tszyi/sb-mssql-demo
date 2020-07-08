package com.tszyi.springbootdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** 提供操作 Inventory 表的 Helper. */
public class InventoryHelper {
  private String user;
  private String pw;
  private Connection conn;
  private Statement stat;
  private PreparedStatement ps;

  /**
   * 建構子.
   *
   * @param user 帳號
   * @param pw 密碼
   */
  public InventoryHelper(String user, String pw) {
    this.user = user;
    this.pw = pw;
  }

  /**
   * 連線到資料庫.
   *
   * @param ip server IP
   * @param port server 埠號
   * @throws SQLException 連線錯誤
   */
  public void connect(String ip, int port) throws SQLException {
    String dbName = "TestDB";
    String url = String.format("jdbc:sqlserver://%s:%d;databaseName=%s", ip, port, dbName);
    conn = DriverManager.getConnection(url, user, pw);
  }

  /**
   * 新增紀錄.
   * @param id id
   * @param name 名稱
   * @param quantity 數量
   * @throws SQLException 資料庫操作失敗
   */
  public void insertItem(int id, String name, int quantity) throws SQLException {
    String sql = "INSERT INTO Inventory (id, name, quantity) VALUES (?, ?, ?)";
    ps = conn.prepareStatement(sql);
    ps.setInt(1, id);
    ps.setString(2, name);
    ps.setInt(3, quantity);
    ps.executeUpdate();
  }

  /**
   * 獲取所有紀錄.
   *
   * @return 表中所有紀錄
   * @throws SQLException 資料庫操作失敗
   */
  public String getItems() throws SQLException {
    String sql = "SELECT * FROM Inventory";
    stat = conn.createStatement();
    ResultSet result = stat.executeQuery(sql);

    StringBuilder sb = new StringBuilder();
    while (result.next()) {
      int id = result.getInt(1);
      String name = result.getString(2);
      String quantity = result.getString(3);

      sb.append(String.format("%d | %s | %s<br/>", id, name, quantity));
    }
    return sb.toString();
  }

  /**
   * 更新紀錄.
   * @param id id
   * @param name 名稱
   * @param quantity 數量
   * @throws SQLException 資料庫操作失敗
   */
  public void updateItemById(int id, String name, int quantity) throws SQLException {
    String sql = "UPDATE Inventory SET name=?, quantity=? WHERE id=?";
    ps = conn.prepareStatement(sql);
    ps.setString(1, name);
    ps.setInt(2, quantity);
    ps.setInt(3, id);
    ps.executeUpdate();
  }

  /**
   * 刪除紀錄.
   * @param id id
   * @throws SQLException 資料庫操作失敗
   */
  public void removeItemById(int id) throws SQLException {
    String sql = "DELETE FROM Inventory WHERE id=?";
    ps = conn.prepareStatement(sql);
    ps.setInt(1, id);
    ps.executeUpdate();
  }

  /**
   * 是否已連上資料庫.
   *
   * @return true表示已連上；反之則否
   */
  public boolean isConnect() {
    boolean isClosed = false;
    try {
      isClosed = conn.isClosed();
    } catch (Exception e) {
      isClosed = true;
    }
    return conn != null && !isClosed;
  }

  /** 關閉資料庫連線. */
  public void close() {
    try {
      if (ps != null) {
        ps.close();
      }
      if (stat != null) {
        stat.close();
      }
      if (conn != null) {
        conn.close();
      }
    } catch (Exception ignore) {
    }
  }
}
