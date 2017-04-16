package edu.zju.reservation.interceptor;

import edu.zju.reservation.domain.ResManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    Logger log = Logger.getLogger(AdminInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // log.debug("=============AdminInterceptor==============");

        String flag = request.getParameter("flag");
        if ("forlogin".equals(flag) || "login".equals(flag)) { // 请求登录，允许通过
            return true;
        } else {
            // 获取session对象
            ResManager manager = (ResManager) request.getSession()
                    .getAttribute("admin");
            if (manager != null) { // 用户已经登录
                return true;
            } else { // 用户未登录
                request.setAttribute("err", "您未登录，无权操作！");
                request.setAttribute("type", "loginadmin");

                request.getRequestDispatcher("jsps/error.jsp").forward(request,
                        response);
                return false;
            }
        }
    }
}
