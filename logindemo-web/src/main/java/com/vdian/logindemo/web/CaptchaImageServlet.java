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
import java.io.OutputStream;

/**
 * Created by chaofeng.zcf on 2016/3/12.
 */
public class CaptchaImageServlet extends HttpServlet {
    private static final long serialVersionUID = -1108808858066784043L;

    private ApplicationContext context;

    @Override
    public void init()
                    throws ServletException
    {
        super.init();
        context = WebApplicationContextUtils.getWebApplicationContext( getServletContext() );
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response )
                    throws ServletException, IOException
    {
        String key = request.getParameter( "key" );

        if ( key == null || key.length() == 0 )
        {
            response.sendError( 400, "No Captcha Key Found" );
        }
        else
        {
            AccountService service = (AccountService) context.getBean( "accountService" );

            try
            {
                response.setContentType( "image/jpeg" );
                OutputStream out = response.getOutputStream();
                out.write( service.generateCaptchaImage( key ) );
                out.close();
            }
            catch ( AccountServiceException e )
            {
                response.sendError( 400, e.getMessage() );
            }
        }
    }
}
