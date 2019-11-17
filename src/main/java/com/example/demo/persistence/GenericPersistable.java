package com.example.demo.persistence;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

/**
 * @author idobre
 * @since 14/11/2019
 */
public class GenericPersistable extends AbstractPersistable<Long> implements Serializable {

}
