package common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import beans.MemberInfo;
import beans.MovieInfo;
import exceptions.CustomException;

/**
 * Cache class to store user sessions
 * 
 * @author Team 7
 */
public final class ServiceCache {
    private static Logger logger = Logger.getLogger(ServiceCache.class);
    private static ServiceCache instance;
    private static ConnectionManager connMgr;
    
    private Map<String, MemberInfo> userInfoMap = new ConcurrentHashMap<String, MemberInfo>(7);
    private Map<String, MovieInfo> movieInfoMap = new ConcurrentHashMap<String, MovieInfo>(7);
    private boolean bContinue;
    
    /**
     * Private constructor - Starts a cleanup thread to remove expired sessions
     */
    private ServiceCache(ConnectionManager connectionMgr){
        connMgr = connectionMgr;
        bContinue = true;
        
        // load objects from DB and cache them 
        // TODO
        
        // cleanup thread
        Thread cleanThread = new Thread(new Runnable() {
            public void run() {
                while (bContinue) {
                    try {
                        if(userInfoMap.size() > 0) {
                            Iterator<String> itr = userInfoMap.keySet().iterator();
                            while(itr.hasNext()) {
                                String uuid = itr.next();
                                MemberInfo userInfo = userInfoMap.get(uuid);
                                if(userInfo.isExpired()) {
                                    userInfoMap.remove(uuid);
                                }
                            }
                        }
                        Thread.sleep(ServiceConstants.SESSION_TIMEOUT); // sleep till next timeout check
                    } 
                    catch (Exception ex) {
                        logger.error("Error in cleanup thread: " + ex.getLocalizedMessage(), ex);
                    }
                }
            }
        });
        cleanThread.setDaemon(true);
        cleanThread.start();
    }
    
    public static synchronized void initialize(ConnectionManager connMgr) throws Exception {
        if (instance != null)
            throw new IllegalStateException("Service Cache is already initialized.");
        instance = new ServiceCache(connMgr);
    }
    /**
     * @return instance of Cache
     */
    public static final ServiceCache getInstance() {
        return instance;
    }
    
    public static final String generateMembershipNumber() {
        return UUID.randomUUID().toString();
    }
    /**
     * Add new User session to cache
     * @param userInfo user session object
     * @return key associated with user session
     */
    public synchronized void addMemberInfo(MemberInfo userInfo) throws CustomException {
        if(userInfo == null) {
            throw new CustomException("UserInfo cannot be NULL");
        }
        userInfoMap.put(userInfo.getEmail(), userInfo);
    }
    /**
     * Add new User session to cache
     * @param userInfo user session object
     * @return key associated with user session
     */
    public synchronized void removeMemberInfo(String email) throws CustomException {
        MemberInfo userInfo = userInfoMap.get(email);
        if(userInfo == null)
            throw new CustomException("Member cannot be found.");
        userInfoMap.remove(email);
    }    
    /**
     * Get user session 
     * @param uuid key associated with session
     * @return user session object
     */
    public MemberInfo getMemberInfo(String email) {
        MemberInfo userInfo = userInfoMap.get(email);
        if(userInfo != null)
            userInfo.setAccessTime(System.currentTimeMillis());
        return userInfo;
    }
    /**
     * @param memberId
     * @return
     */
    public MemberInfo getMemberInfo(int memberId) {
        MemberInfo memberInfo = null;
        Collection<MemberInfo> members = userInfoMap.values();
        if(members != null && members.size() > 0) {
            for(MemberInfo userInfo: members) {
                if(userInfo.getMemberId() == memberId) {
                    memberInfo = userInfo;
                    break;
                }
            }
        }
        return memberInfo;
    }
    /**
     * @return
     */
    public MemberInfo[] getMembers() {
        MemberInfo[] memberInfos = null;
        Collection<MemberInfo> members = userInfoMap.values();
        if(members != null && members.size() > 0) {
            memberInfos =  (MemberInfo[]) members.toArray(new MemberInfo[members.size()]);
        }
        return memberInfos;
    }
    /**
     * Add new movie
     * @param userInfo user session object
     * @return key associated with user session
     */
    public synchronized void addMovieInfo(MovieInfo movieInfo) throws CustomException {
        if(movieInfo == null) {
            throw new CustomException("MovieInfo cannot be NULL");
        }
        movieInfoMap.put(movieInfo.getMovieName(), movieInfo);
    }
    /**
     * Remove movie from cache
     * @param userInfo user session object
     * @return key associated with user session
     */
    public synchronized void removeMovieInfo(String name) throws CustomException {
        MovieInfo movieInfo = movieInfoMap.get(name);
        if(movieInfo == null)
            throw new CustomException("Movie cannot be found.");
        userInfoMap.remove(name);
    }     
    /**
     * @param memberId
     * @return
     */
    public MovieInfo getMovieInfo(String name) {
        MovieInfo movieInfo = null;
        Collection<MovieInfo> movies = movieInfoMap.values();
        if(movies != null && movies.size() > 0) {
            for(MovieInfo movie: movies) {
                if(movie.getMovieName().equalsIgnoreCase(name)) {
                    movieInfo = movie;
                    break;
                }
            }
        }
        return movieInfo;
    }
    /**
     * @param movieId
     * @return
     */
    public MovieInfo getMovieInfo(int movieId) {
        MovieInfo movieInfo = null;
        Collection<MovieInfo> movies = movieInfoMap.values();
        if(movies != null && movies.size() > 0) {
            for(MovieInfo movie: movies) {
                if(movie.getMovieId() == movieId) {
                    movieInfo = movie;
                    break;
                }
            }
        }
        return movieInfo;
    }    
    /**
     * @return
     */
    public MovieInfo[] getMovies() {
        MovieInfo[] movieInfos = null;
        Collection<MovieInfo> movies = movieInfoMap.values();
        if(movies != null && movies.size() > 0) {
            movieInfos =  (MovieInfo[]) movies.toArray(new MovieInfo[movies.size()]);
        }
        return movieInfos;
    }
}