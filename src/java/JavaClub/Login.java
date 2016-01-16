package JavaClub;

/**
 * Created on : Jun 12, 2015, 1:44:19 PM
 *
 * @autor : Jin Hwan Oh
 */
import JavaClub.db.UserDb;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
    protected static boolean hasLoggedOut = false;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            hasLoggedOut = false;
            // Get id and password
            String id = request.getParameter("userId");
            String password = request.getParameter("password");
            UserDb db = new UserDb();

            if (db.isCorrectPassword(id, password)) {
                // Create session
                HttpSession session = request.getSession(true);
                
                // Create User object
                User user = db.getUser(id);

                //set the "user" atribute in the session for future request
                session.setAttribute("user", user);

                // Redirect to MainPage               
                RequestDispatcher rd = request.getRequestDispatcher("MainPage");
                rd.forward(request, response);
                return;
            }
            else {
                notFound(out, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void notFound(final PrintWriter out, HttpServletResponse response) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Page</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("</head>");
        out.println(" <body>");
        out.println("<div id='wrongUser'>You entered wrong User ID or password</div>");
        out.println("<form id='home_form' method='POST' action='/JavaClub_JinHwan_Oh/Login'>");
        out.println("<table align=\"center\">");
        out.println("<tr>");
        out.println("<td>User Id:</td>");
        out.println("<td><input type=\"text\" size=\"14\" name=\"userId\"/></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Password:</td>");
        out.println("<td><input type=\"password\" size=\"14\" name=\"password\" /></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<input type=\"submit\" value=\"Login\" id=\"login_btn\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
