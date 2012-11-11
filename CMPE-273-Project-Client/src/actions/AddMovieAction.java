package actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DefaultNamespace.VideoLibrarySystemProxy;
import beans.MemberInfo;
import beans.MovieInfo;

import common.ClientConstants;

/**
 * Add Item action class
 * 
 * @author Team 7
 */
public class AddMovieAction extends BaseAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String destination = "../View/sellItems.jsp";
        try {
            String name = request.getParameter("name");
            String banner = request.getParameter("banner");
            long releaseTime = Long.parseLong(request.getParameter("releaseTime"));
            float rent = Float.parseFloat(request.getParameter("rent"));
            String category = request.getParameter("category");
            int copies = Integer.parseInt(request.getParameter("copies"));
            String summary = request.getParameter("summary");
            
            if(name == null || name.length() == 0 || banner == null || banner.length() == 0
                    || category == null || category.length() == 0 || summary == null || summary.length() == 0) {
                session.setAttribute("errMsg", "Invalid input parameters, please try again.");
            }
            else {
                
                VideoLibrarySystemProxy proxy = new VideoLibrarySystemProxy(ClientConstants.END_POINT);
                MemberInfo memberInfo = (MemberInfo) session.getAttribute(ClientConstants.MEMBER);
                MovieInfo item = proxy.addMovie(memberInfo.getEmail(), name, banner, releaseTime, rent, category, copies, summary);
                if(item != null) {
                    session.setAttribute("successMsg", "Movie added into the system.");
                    session.removeAttribute("errMsg");
                }
                else {
                    session.setAttribute("errMsg", "Failed to add Movie, please try again.");
                }
            }
        }
        catch(Exception ex) {
            logger.error("Failed to add item: " + ex.getLocalizedMessage(), ex);
            session.setAttribute("errMsg", "Failed to add item: " + ex.getLocalizedMessage());
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }
}