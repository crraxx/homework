package com.ff.servletJsp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ff.dao.loginDao;

@WebServlet(name = "login", urlPatterns = "/login")
public class login_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        //是否机主账户密码
        String cookie = req.getParameter("ck");
        try {
            if (loginDao.link(account, password)) {
                System.out.println("数据库连接成功");
                if (cookie != null) {
                    //创建cookie对象,封装数据
                    Cookie acccok = new Cookie("acccok", account);
                    Cookie pswck = new Cookie("pswck", password);
                    //设置cookie保存时间
                    acccok.setMaxAge(3600 * 24 * 7);
                    pswck.setMaxAge(3600 * 24 * 7);
                    resp.addCookie(acccok);
                    resp.addCookie(pswck);
                }
                //获取session,获取ip,登陆时间,账户,
                HttpSession session = req.getSession();
                session.setAttribute("account", account);
                session.setAttribute("password", password);
                String ip = req.getRemoteAddr();
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String login_time = dateFormat.format(date);
                //插入数据库中
                loginDao.insert(account, ip, login_time);
                System.out.println("插入数据成功");
                //跳转页面
                resp.sendRedirect("success.jsp");
                out.print("登陆成功");
                System.out.println("登陆成功");
            } else {
                out.print("账户或密码错误");
                resp.sendRedirect("login.jsp");
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            System.out.println("数据库异常");
            resp.sendRedirect("505.jsp");
        }
    }
}
