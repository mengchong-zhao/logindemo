package com.vdian.logindemo.web;

import com.vdian.logindemo.service.AccountService;
import com.vdian.logindemo.service.AccountServiceException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chaofeng.zcf on 2016/3/12.
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -1684001376187652541L;

    private ApplicationContext context;

    @Override
    public void init()
                    throws ServletException
    {
        super.init();
        context = WebApplicationContextUtils.getWebApplicationContext( getServletContext() );
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp )
                    throws ServletException, IOException
    {
        String id = req.getParameter( "id" );
        String password = req.getParameter( "password" );

        if ( id == null || id.length() == 0 || password == null || password.length() == 0 )
        {
            resp.sendError( 400, "incomplete parameter" );
            return;
        }

        AccountService service = (AccountService) context.getBean( "accountService" );

        try
        {
            service.login( id, password );
            resp.getWriter().print( "Login Successful!" );
        }
        catch ( AccountServiceException e )
        {
            resp.sendError( 400, e.getMessage() );
        }
    }
}
