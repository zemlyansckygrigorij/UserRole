package org.example.userrole.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class User for entity from table users
 */

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user_role")
@EqualsAndHashCode
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "user_role_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false , unique=true)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "role_id", nullable = false)
    private long roleId;
}
