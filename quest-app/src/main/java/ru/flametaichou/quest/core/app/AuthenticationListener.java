package ru.flametaichou.quest.core.app;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.session.SessionFixationProtectionEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class AuthenticationListener extends SimpleUrlLogoutSuccessHandler
        implements ApplicationListener<AbstractAuthenticationEvent> {


    @Override
    @Transactional
    public void onApplicationEvent(AbstractAuthenticationEvent aEvent) {
        if (aEvent instanceof SessionFixationProtectionEvent) {

            WebAuthenticationDetails details = ((WebAuthenticationDetails) aEvent.getAuthentication().getDetails());


        } else if (aEvent instanceof AuthenticationSuccessEvent) {
            WebAuthenticationDetails details = ((WebAuthenticationDetails) aEvent.getAuthentication().getDetails());
        }
    }

    @Override
    @Transactional
    public void onLogoutSuccess(HttpServletRequest aRequest, HttpServletResponse aResponse,
                                Authentication aAuthentication) throws IOException, ServletException {
        super.onLogoutSuccess(aRequest, aResponse, aAuthentication);

        if (Objects.nonNull(aAuthentication) && Objects.nonNull(aAuthentication.getDetails())) {
            WebAuthenticationDetails details = ((WebAuthenticationDetails) aAuthentication.getDetails());
        }
        String userAgentOnLogout = aRequest.getHeader("user-agent");
    }

}
