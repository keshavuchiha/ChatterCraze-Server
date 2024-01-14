package com.chattercraze.Server.Models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name="societies")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Society {
    @Id
    @GeneratedValue
    @Column(name = "society_id")
    private UUID societyId;
    private String name;
    private String description;
    private String privacy;
}
