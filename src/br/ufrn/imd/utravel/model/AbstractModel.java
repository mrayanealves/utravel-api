package br.ufrn.imd.utravel.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractModel {
    public abstract void setId(long id);

    public abstract long getId();
}
