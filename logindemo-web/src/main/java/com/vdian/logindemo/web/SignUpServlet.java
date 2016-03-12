package com.vdian.logindemo.web;

import com.vdian.logindemo.service.AccountService;
import com.vdian.logindemo.service.AccountServiceException;
import com.vdian.logindemo.service.SignUpRequest;
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
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = -4775342453382627230L;

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
        String email = req.getParameter( "email" );
        String name = req.getParameter( "name" );
        String password = req.getParameter( "password" );
        String confirmPassword = req.getParameter( "confirm_password" );
        String captchaKey = req.getParameter( "captcha_key" );
        String captchaValue = req.getParameter( "captcha_value" );

        if ( id == null || id.length() == 0 || email == null || email.length() == 0 || name == null
                        || name.length() == 0 || password == null || password.length() == 0 || confirmPassword == null
                        || confirmPassword.length() == 0 || captchaKey == null || captchaKey.length() == 0 || captchaValue == null
                        || captchaValue.length() == 0 )
        {
            resp.sendError( 400, "Parameter Incomplete." );
            return;
        }

        AccountService service = (AccountService) context.getBean( "accountService" );

        SignUpRequest request = new SignUpRequest();

        request.setId( id );
        request.setEmail( email );
        request.setName( name );
        request.setPassword( password );
        request.setConfirmPassword( confirmPassword );
        request.setCaptchaKey( captchaKey );
        request.setCaptchaValue( captchaValue );

        request.setActivateServiceUrl( getServletContext().getRealPath( "/" ) + "activate" );

        try
        {
            service.signUp( request );
            resp.getWriter().print( "Account is created, please check your mail box for activation link." );
        }
        catch ( AccountServiceException e )
        {
            resp.sendError( 400, e.getMessage() );
            return;
        }
    }
}
