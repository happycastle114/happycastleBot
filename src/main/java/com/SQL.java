package com;
import java.sql.*;

public class  SQL{
    Connection conn = null;
    Statement stmt = null;
    String table;

    public SQL(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
        try {
            this.stmt = conn.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 삽입
    public void insert(String id, String name) {
        StringBuilder sb = new StringBuilder();
        String sql = "INSERT into " + table + " values(" + "'" + id + "', " + "'" + name + "'" + ");";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 삭제
    public void delete(String id) {
        StringBuilder sb = new StringBuilder();
        String sql = "DELETE FROM " + table + " where user = " + "'" + id + "'" + ";";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 수정
    public void update(String name, String grade) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("update " + table + " set")
                .append(" name = ")
                .append("'" + name + "'")
                .append(" commet = ")
                .append("'" + grade + "'")
                .append(" where name = ")
                .append(name)
                .append(";").toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 모든 검색
    /*public void selectAll() {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from " + table)
                .append(";").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);

            System.out.print("id");
            System.out.print("\t");
            System.out.print("name");
            System.out.print("\t");
            System.out.print("grade");
            System.out.print("\n");
            System.out.println("────────────────────────");

            while(rs.next()){
                System.out.print(rs.getInt("id"));
                System.out.print("\t");
                System.out.print(rs.getString("name"));
                System.out.print("\t");
                System.out.print(rs.getString("grade"));
                System.out.print("\n");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

    // 검색
    public String select(String id) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from " + table + " where")
                .append(" name = ")
                .append("'" + id + "'")
                .append(";").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            return rs.getString("commet");



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized boolean isInDatabase(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/commet", "happycastle", "1234");
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) AS total FROM `commet` WHERE 'name'=" + "'" + id + "'" + " LIMIT 1;");
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println(rs.getInt("total"));
            if (rs.getInt("total") != 0) {

                rs.close();
                ps.close();
                c.close();
                return true;
            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }

}