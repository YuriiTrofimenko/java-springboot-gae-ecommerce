/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.springboot.gae.ecommerce.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import org.springframework.stereotype.Repository;
import org.tyaa.java.springboot.gae.ecommerce.entity.User;
import org.tyaa.java.springboot.gae.ecommerce.util.CopyHelper;

/**
 *
 * @author yurii
 */
@Repository
public class UserDAO extends AbstractDAO<User> {
    
    public User read(String _name) throws Exception {
            
        User userEntity = null;
        
            User finalUserEntity = new User();
            ObjectifyService.run(new VoidWork() {
                @Override
                public void vrun() {
                    User userEntityResult =
                        ofy().load().type(User.class)
                            .filter("name", _name)
                            .first()
                            .now();
                    if (userEntityResult != null) {
                        CopyHelper.copy(userEntityResult, finalUserEntity);
                    }
                }
            });
            /*if (true) {
                throw new Exception("test ex");
            }*/
            userEntity = finalUserEntity;
            
        return userEntity;
    }
}
