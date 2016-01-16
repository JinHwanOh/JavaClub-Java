/**
    Created on : Jun 12, 2015, 1:44:19 PM
    @autor     : Jin Hwan Oh
*/
package JavaClub;

import JavaClub.db.UserDb;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Signup extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("userId");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // invalid input
            if (id.equals("")) {
                invalidInput(out, response, "ID cannot be null");
            }
            else if (password.equals("") || password2.equals("")) {
                invalidInput(out, response, "Password cannot be null");
            }
            else if (firstName.equals("")) {
                invalidInput(out, response, "First name cannot be null");
            }
            else if (lastName.equals("")) {
                invalidInput(out, response, "Last name cannot be null");
            }
            else if (email.equals("")) {
                invalidInput(out, response, "Email cannot be null");
            }
            else if (!isPasswordMatch(password, password2)) {
                invalidInput(out, response, "Password does not match");
            }
            else {
                // valid input
                UserDb db = new UserDb();
                User user = new User(id, password, firstName, lastName, email);
                if (db.getUser(id) != null) {
                    invalidInput(out, response, "Entered Id already exists");
                }
                else {
                    // Add to database
                    db.addUser(user);
                    signedUp(out, response);
                }
            }
        }
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
        processRequest(request, response);
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

    public boolean isPasswordMatch(String password, String password2) {
        return password.equals(password2);
    }

    public void invalidInput(final PrintWriter out, HttpServletResponse response, String message) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Registration Form</title>\n");
        out.println("<meta charset=\"UTF-8\">\n");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("</head>");
        out.println(" <body>");
        out.println("<div id='invalid_msg'>" + message + "</div>");
        out.println("<form id='registration' method='POST' action='/JavaClub_JinHwan_Oh/Signup'>");
        out.println("Chooose user ID:<input type='text' name='userId' size='20'><br/>");
        out.println("Choose your password:<input type='password' name='password' size='20'><br/>");
        out.println("Re-enter password:<input type='password' name='password2' size='20'><br/>");
        out.println("Enter your first name:<input type='text' name='firstName' size='20'><br/>");
        out.println("Enter your last name:<input type='text' name='lastName' size='20'><br/>");
        out.println("Enter your email address:<input type='email' name='email' size='20'><br/>");

        out.println("<input type='submit' value='Signup'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    public void signedUp(final PrintWriter out, HttpServletResponse response) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Signup Confirmation</title>\n");
        out.println("<meta charset=\"UTF-8\">\n");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("</head>");
        out.println(" <body>");
        out.println("Sign up successfully. Thank you for joining Java Club."
                + "<br/><br/>");
        out.println("<a href=/JavaClub_JinHwan_Oh/index.html>Go to Home page.</a>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
