package com.ssafy.tink.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@IdClass(JarangLikes.class)
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JarangLikes implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Member member;

    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Board board;

}
