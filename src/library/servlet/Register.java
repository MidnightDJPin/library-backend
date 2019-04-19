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
		String repeat = "select * from reader where account='" + newReader.getAccount() + "'";
		try {
			PreparedStatement pst = (PreparedStatement) connection.prepareStatement(repeat);
			ResultSet rs = pst.executeQuery();
			PrintWriter writer = response.getWriter();
			if (rs.next()) {
				response.setStatus(403);
				writer.write("{\"state\":\"failed\","
	        			+ "\"message\":\"该账户已存在\""
	        			+ "}");
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
				pst.setBoolean(7, newReader.isRstate());
				synchronized (this) {
					int resultCode = pst.executeUpdate();
					if (resultCode > 0) {
						response.setStatus(201);
						writer.write("{\"state\":\"succeed\","
			        			+ "\"message\":\"注册成功\""
			        			+ "}");
					} else {
						response.setStatus(403);
						writer.write("{\"state\":\"failed\","
			        			+ "\"message\":\"注册失败\""
			        			+ "}");
					}
				}
				writer.close();
				Database.closeAll(rs, pst, connection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			PrintWriter writer = response.getWriter();
			response.setStatus(500);
			writer.write("{\"state\":\"failed\","
        			+ "\"message\":\"服务器错误\""
        			+ "}");
			writer.close();
		}
	}

}
