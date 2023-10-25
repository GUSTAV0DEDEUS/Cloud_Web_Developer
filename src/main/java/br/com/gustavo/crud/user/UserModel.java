package br.com.gustavo.crud.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String userAge;
    private double firstNote;

    private double secondNote;
    private String nameTeacher;
    private String numberClass;
}
