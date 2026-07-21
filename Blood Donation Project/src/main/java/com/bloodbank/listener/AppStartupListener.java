package com.bloodbank.listener;

import com.bloodbank.dao.AdminDao;
import com.bloodbank.util.PasswordUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppStartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AdminDao adminDao = new AdminDao();
        if (adminDao.countAdmins() == 0) {
            adminDao.insert("admin", PasswordUtil.hash("admin123"));
        }
    }
}
