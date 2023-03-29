package com.ssafy.tink.db.entity;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@IdClass(JarangLikes.class)
@Getter
public class JarangLikes implements Serializable {

    @Id
    @ManyToOne
    private Member member;

    @Id
    @ManyToOne
    private Board board;

}
