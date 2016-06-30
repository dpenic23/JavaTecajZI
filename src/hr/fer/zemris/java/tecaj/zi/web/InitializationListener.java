package hr.fer.zemris.java.tecaj.zi.web;

import java.awt.Color;

import hr.fer.zemris.java.tecaj.zi.ModelCrteza;
import hr.fer.zemris.java.tecaj.zi.ModelCrtezaImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitializationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ModelCrteza model = new ModelCrtezaImpl();
        model.dodajKrug(20, 20, 10, Color.RED, Color.BLUE);
        model.dodajKrug(100, 100, 35, Color.RED, Color.BLUE);
        model.dodajKrug(250, 300, 41, Color.RED, Color.BLUE);
        sce.getServletContext().setAttribute("model", model);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("model");
    }
    
}
