package actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MemberInfo;

import DefaultNamespace.VideoLibrarySystemProxy;

import common.ClientConstants;
/**
 * Sign-In action class
 * 
 * @author Team 7
 */
public class SignInAction extends BaseAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String destination = "../View/index.jsp";
        try {
            String user = request.getParameter("user");
            String pwd = request.getParameter("pwd");
            
            if(user == null || user.length() == 0 || pwd == null || pwd.length() == 0) {
                session.setAttribute("errMsg", "Invalid username/password, please try again.");
            }
            else {
                VideoLibrarySystemProxy proxy = new VideoLibrarySystemProxy(ClientConstants.END_POINT);
                MemberInfo memberInfo = proxy.signIn(user, pwd); 
                if(memberInfo != null) {
                    session.removeAttribute("errMsg");
                    session.setAttribute(ClientConstants.MEMBER,memberInfo);
                    destination = "../View/browseMovies.jsp";
                }
            }
        }
        catch(Exception ex) {
            logger.error("Failed to SignIn: " + ex.getLocalizedMessage(), ex);
            session.setAttribute("errMsg", ex.getLocalizedMessage());
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }
}