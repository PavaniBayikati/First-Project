import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/userController")
public class userController extends HttpServlet {
private static final long serialVersionUID = 1L;
protected void doPost(HttpServletRequest request,
HttpServletResponse response) throws ServletException,
IOException {
response.setContentType("text/html");
PrintWriter out = response.getWriter();
String name = request.getParameter("name");
String regID = request.getParameter("RegID");
String email = request.getParameter("email");
String phno = request.getParameter("phno");
String slot = request.getParameter("slot");
Connection conn = null;
PreparedStatement pstmt = null;
try {
Class.forName("com.mysql.jdbc.Driver");
conn =
DriverManager.getConnection("jdbc:mysql://localhost:3306/use
r"
,
"rudhra"
,
"rudhra");
String sql =
"INSERT INTO details (name, RegID, email,
phno, slot) VALUES (?, ?, ?, ?, ?)";
pstmt = conn.prepareStatement(sql);
pstmt.setString(1, name);
pstmt.setString(2, regID);
pstmt.setString(3, email);
pstmt.setString(4, phno);
pstmt.setString(5, slot);
int rowsAffected = pstmt.executeUpdate();
if (rowsAffected > 0) {
pstmt.close();
conn.close();
out.println("<html><head><script>alert('Your slot has
been CONFIRMED!');
window.location=
'index1.html';</script></head></html>");
} else {
out.println("<h2>Failed to save user details.</h2>");
}
} catch (ClassNotFoundException | SQLException e) {
e.printStackTrace();
out.println("<h2>Error occurred: " + e.getMessage() +
"</h2>");
} finally {
try {
if (pstmt != null) {
pstmt.close();
}
if (conn != null) {
conn.close();
}
} catch (SQLException e) {
e.printStackTrace();
}
}
}
