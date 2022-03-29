package uz.elmurodov.entity;

import lombok.*;

import uz.elmurodov.enums.Language;


import java.util.Date;

/**
 * @author Elmurodov Javohir, Fri 5:19 PM. 12/17/2021
 */

@Getter
@Setter
@NoArgsConstructor
public class AuthUser extends Auditable {
    private String fullName;
    private Integer age;
    private String gender;
    private String phoneNumber;
    @Getter
    @Setter
    private Language language=Language.EN;
            //AppConfig.language;
    private String role;
    private String username;
    private String password;
    private Date lastUserTime;

    @Builder(builderMethodName = "childBuilder")
    public AuthUser(String id, Date createdAt, String fullName, Integer age, String gender,
                    String phoneNumber, Language language, String role,
                    String userName, String password, Date lastUserTime) {
        super(id, createdAt);
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.language = language;
        this.role = role;
        this.username = userName;
        this.password = password;
        this.lastUserTime = lastUserTime;
    }


}
