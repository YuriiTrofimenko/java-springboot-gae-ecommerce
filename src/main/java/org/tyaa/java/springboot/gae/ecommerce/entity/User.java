/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.springboot.gae.ecommerce.entity;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.VoidWork;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tyaa.java.springboot.gae.ecommerce.util.CopyHelper;

/**
 *
 * @author yurii
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class User {
    
    @Id
    private Long id;
    @Index
    private String name;
    private String password;
    @Load
    @Index
    Ref<Role> role;

    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        setRole(role);
    }

    public User(Long id, String name, String password, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        setRole(role);
    }
    
    // @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    public final Role getRole() {
        Role role = new Role();
        ObjectifyService.run(new VoidWork() {
            @Override
            public void vrun() {
                Role roleLocal = User.this.role.get();
                if (roleLocal != null) {
                    CopyHelper.copy(roleLocal, role);
                }
            }
        });
        return role;
    }

    // @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    public final void setRole(Role role) {
        ObjectifyService.run(new VoidWork() {
            @Override
            public void vrun() {
                User.this.role = Ref.create(role);
            }
        });
    }
}
