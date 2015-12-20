package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class SignUpServlet extends HttpServlet {
    static Logger log = LogManager.getLogger(SignUpServlet.class.getName());

    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"}) //todo: remove after module 2 home work
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //get public user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
        log.info("signup doGet with login:" + request.getParameter("login")  + " password:" + request.getParameter("password"));
    }

    //sign up
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        log.info("signup post with login:" + login + " password:" + pass);
        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Empty login or password");
            log.info("send response: Empty login or password");
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);
        if (profile != null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("User already registered");
            log.info("send response: User already registered");
            return;
        }

        response.setContentType("text/html;charset=utf-8");
        UserProfile newProfile = new UserProfile(login, pass);
        accountService.addNewUser(newProfile);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print("User created");
        log.info("send response: User created");
    }

    //change profile
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
        log.info("signup doPut with login:" + request.getParameter("login")  + " password:" + request.getParameter("password"));
    }

    //unregister
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
        log.info("signup doDelete with login:" + request.getParameter("login")  + " password:" + request.getParameter("password"));
    }
}
