package com.chattercraze.Server.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name="society_members")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@IdClass(SocietyMembersId.class)
public class SocietyMembers {
    @Id
    @Column(name="user_id")
    UUID userId;

    @Id
    @Column(name="society_id")
    UUID societyId;

    String role;

    @Column(name="joined_at")
    Time joinedAt;
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class SocietyMembersId implements Serializable {
    UUID userId;
    UUID societyId;
}
