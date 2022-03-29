package uz.elmurodov.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Narzullayev Husan, чт 23:22. 30.12.2021
 */
@Getter
@Setter
@NoArgsConstructor
public class User extends Auditable {
    private String fullName;
    private Integer age;
    private String gender;
    private String phoneNumber;
    private String language;
    private String role;
    private String userName;

    @Builder(builderMethodName = "childBuilder")
    public User(String id, Date createdAt, String fullName,
                Integer age, String gender, String phoneNumber,
                String language, String role, String userName) {
        super(id, createdAt);
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.language = language;
        this.role = role;
        this.userName = userName;
    }

}
