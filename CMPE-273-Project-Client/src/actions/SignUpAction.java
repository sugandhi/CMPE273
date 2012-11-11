package actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ClientConstants;

import DefaultNamespace.VideoLibrarySystemProxy;
/**
 * Sign-up action class
 * 
 * @author Team 7
 */
public class SignUpAction extends BaseAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String destination = "../View/index.jsp";
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("pwd");
            String firstname = request.getParameter("fname");
            String lastname = request.getParameter("lname");
            String street1 = request.getParameter("street1");
            String street2 = request.getParameter("street2");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zipCode = request.getParameter("zipCode");
            String phoneNo = request.getParameter("phoneNo");
            boolean premium = Boolean.getBoolean(request.getParameter("premium"));
            
            if(email == null || email.length() == 0 || password == null || password.length() == 0 
                    || firstname == null || firstname.length() == 0 || lastname == null || lastname.length() == 0 ) {
                session.setAttribute("errMsg", "Invalid input parameters, please try again.");
            }
            else {
                VideoLibrarySystemProxy proxy = new VideoLibrarySystemProxy(ClientConstants.END_POINT);
                proxy.addMember(firstname, lastname, street1, street2, city, state, 
                        zipCode, email, password, phoneNo, premium); 
                session.setAttribute("successMsg", "Account created, please sign-in using email/password.");
            }
        }
        catch(Exception ex) {
            logger.error("Failed to SignUp: " + ex.getLocalizedMessage(), ex);
            session.setAttribute("errMsg", "Failed to SignUp: " + ex.getLocalizedMessage());
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }
}