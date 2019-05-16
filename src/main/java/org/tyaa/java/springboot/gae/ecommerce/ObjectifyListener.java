/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.springboot.gae.ecommerce;

import com.google.cloud.datastore.DatastoreOptions;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.tyaa.java.springboot.gae.ecommerce.entity.Role;
import org.tyaa.java.springboot.gae.ecommerce.entity.User;
import org.tyaa.java.springboot.gae.ecommerce.util.ErrorsGetter;

/**
 *
 * @author yurii
 */
@WebListener
public class ObjectifyListener implements ServletContextListener {
    
    private static final Logger log =
        Logger.getLogger(ObjectifyListener.class.getName());
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            if (System.getenv("SPRING_PROFILES_ACTIVE") == null) {
                // local without memcache (gradle bootRun)
                log.info("ObjectifyService.init - 1");
                ObjectifyService.init(new ObjectifyFactory(
                    DatastoreOptions.newBuilder()
                        .setHost("http://localhost:8484")
                        .setProjectId("spring-gae-ecommerce")
                        .build()
                        .getService()
                ));
            }
            else {
                // on appengine
                ObjectifyService.init(new ObjectifyFactory(
                    DatastoreOptions.getDefaultInstance().getService()
                ));
                // ObjectifyService.init();
            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, ErrorsGetter.printException(ex));
        }
        ObjectifyService.register(Role.class);
        ObjectifyService.register(User.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
