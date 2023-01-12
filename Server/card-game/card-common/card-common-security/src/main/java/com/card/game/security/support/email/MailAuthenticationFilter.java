package com.card.game.security.support.email;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.card.game.common.result.Result;
import com.card.game.common.result.ResultCode;
import com.card.game.common.web.utils.ServletUtils;
import com.card.game.security.constant.SecurityConstants;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author tomyou
 * @version v1.0 2023-01-07-8:00 PM
 */
public class MailAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String SPRING_SECURITY_FORM_MAIL_ACCOUNT_KEY = "mailAccount";

    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

    private String mailAccountParameter = SPRING_SECURITY_FORM_MAIL_ACCOUNT_KEY;

    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(SecurityConstants.EMAIL_LOGIN_URL,
            HttpMethod.POST.name());

    public MailAuthenticationFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //从请求体中取出帐号和code
        String data = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Map<String, String> map = JSONObject.parseObject(data, new TypeReference<Map<String, String>>() {
        });

        if (Objects.isNull(map)){
            ServletUtils.writeToJson(response, Result.error(ResultCode.REQUIRE_MAIL_LOGIN_PARAM));
            return null;
        }

        String mailAccount = map.get(mailAccountParameter);
        mailAccount = mailAccount != null ? mailAccount.trim() : "";

        String password = map.get(passwordParameter);
        password = password != null ? password : "";

        //未认证状态
        MailAuthenticationToken mailAuthenticationToken = MailAuthenticationToken.unauthenticated(mailAccount,password);
        setDetails(request,mailAuthenticationToken);

        //执行认证
        return this.getAuthenticationManager().authenticate(mailAuthenticationToken);
    }

    protected void setDetails(HttpServletRequest request, MailAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }


    public void setUsernameParameter(String mailAccountParameter) {
        Assert.hasText(mailAccountParameter, "Username parameter must not be empty or null");
        this.mailAccountParameter = mailAccountParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

}
