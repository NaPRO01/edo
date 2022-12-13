import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * Дублирующее DTO "AuthorDto" для сущности "Author"
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(value = "Автор обращения")
public class AuthorDto implements Serializable {

    @ApiModelProperty(value = "имя автора")
    private String firstName;

    @ApiModelProperty(value = "фамилия автора")
    private String lastName;

    @ApiModelProperty(value = "отчество автора")
    private String middleName;

    @ApiModelProperty(value = "адрес автора")
    private String address;

    @ApiModelProperty(value = "СНИЛС автора")
    private Long snils;

    @ApiModelProperty(value = "номер телефона автора (мобильный)")
    private int mobilePhone;

    @ApiModelProperty(value = "адрес электронной почты автора")
    private String email;

    @ApiModelProperty(value = "трудоустройство автора (Безработный, Работник, Учащийся)")
    private Employment employment;

    @ApiModelProperty(value = "ФИО автора в дательном падеже")
    private String fioDative;

    @ApiModelProperty(value = "ФИО автора в родительном падеже")
    private String fioGenitive;

    @ApiModelProperty(value = "ФИО автора в именительном падеже")
    private String fioNominative;

}