package library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import library.dao.Database;
import library.model.Reader;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = JsonUtil.getJsonString(request);
		Connection connection = Database.getConnection();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		
		Reader newReader = new Gson().fromJson(json, Reader.class);
		System.out.println("�յ�ע�������˺ţ�" + newReader.getAccount() + " "
				+ "���룺" + newReader.getPassword());
		String repeat = "select * from reader where account='" + newReader.getAccount() + "'";
		PrintWriter writer = response.getWriter();
		try {
			PreparedStatement pst = (PreparedStatement) connection.prepareStatement(repeat);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				response.setStatus(403);
				writer.write("{\"state\":\"failed\","
	        			+ "\"message\":\"���˻��Ѵ���\""
	        			+ "}");
				System.out.println("�˻��Ѵ���");
			} else {
				String registerSQL = "insert into reader (account, password, rname, phone, email, admin, rstate)"
						+ "values(?,?,?,?,?,?,?)";
				pst = (PreparedStatement) connection.prepareStatement(registerSQL);
				pst.setString(1, newReader.getAccount());
				pst.setString(2, newReader.getPassword());
				pst.setString(3, newReader.getRname());
				pst.setString(4, newReader.getPhone());
				pst.setString(5, newReader.getEmail());
				pst.setBoolean(6, newReader.isAdmin());
				pst.setBoolean(7, true);
				synchronized (this) {
					int resultCode = pst.executeUpdate();
					if (resultCode > 0) {
						response.setStatus(201);
						writer.write("{\"state\":\"succeed\","
			        			+ "\"message\":\"ע��ɹ�\""
			        			+ "}");
						System.out.println("ע��ɹ�");
					} else {
						response.setStatus(403);
						writer.write("{\"state\":\"failed\","
			        			+ "\"message\":\"ע��ʧ��\""
			        			+ "}");
					}
				}
				Database.closeAll(rs, pst, connection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(500);
			writer.write("{\"state\":\"failed\","
        			+ "\"message\":\"����������\""
        			+ "}");
		}
		writer.close();
	}

}
