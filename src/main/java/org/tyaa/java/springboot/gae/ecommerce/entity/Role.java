/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.springboot.gae.ecommerce.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 *
 * @author yurii
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Role {
    
    @Id
    private Long id;
    @Index
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
