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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		Reader loginReader = new Gson().fromJson(json, Reader.class);
		System.out.println("收到登录请求，账号：" + loginReader.getAccount() + " "
				+ "密码：" + loginReader.getPassword());
		String sql = "select * from reader where "
				+ "account='" + loginReader.getAccount() + "' AND "
						+ "password='" + loginReader.getPassword() + "'";
		PreparedStatement pst;
		PrintWriter writer = response.getWriter();
		try {
			pst = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				Reader reader = new Reader(rs.getInt("rid"), rs.getString("account"), rs.getString("password"), 
						rs.getString("rname"), rs.getString("phone"), rs.getString("email"), 
						(rs.getInt("admin") == 1), (rs.getInt("rstate") == 1), 
						(rs.getTimestamp("bantime") == null)? null : (rs.getTimestamp("bantime").toString()));
				response.setStatus(200);
				Gson gson = new Gson();
				String responseJson = gson.toJson(reader);
				writer.write(responseJson);
				System.out.println("登陆成功");
			} else {
				response.setStatus(401);
				writer.write("{\"state\":\"failed\","
	        			+ "\"message\":\"账号或密码不正确\""
	        			+ "}");
				System.out.println("账号或密码不正确");
			}
			Database.closeAll(rs, pst, connection);
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(500);
			writer.write("{\"state\":\"failed\","
        			+ "\"message\":\"服务器错误\""
        			+ "}");
		}
		writer.close();
	}

}
