package actions;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MemberInfo;

import DefaultNamespace.VideoLibrarySystemProxy;

import common.ClientConstants;
/**
 * Sign-out action class
 * 
 * @author Team 7
 */
public class SignOutAction extends BaseAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String destination = "../View/index.jsp";
        try {
            VideoLibrarySystemProxy proxy = new VideoLibrarySystemProxy(ClientConstants.END_POINT);
            MemberInfo memberInfo = (MemberInfo) session.getAttribute(ClientConstants.MEMBER);
            proxy.signOut(memberInfo.getEmail()); 
            
            Enumeration<String> keys = session.getAttributeNames();
            while (keys.hasMoreElements()) {
                session.removeAttribute(keys.nextElement());
            }
            session.invalidate();
        }
        catch(Exception ex) {
            logger.error("Failed to SignOut: " + ex.getLocalizedMessage(), ex);
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }
}