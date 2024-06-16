package cn.izoro.model.vo.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @Author zoro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterReqVO {
    private static final long serialVersionUID = 3191241716373120793L;
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 5, max = 11, message = "用户名长度在5-11之间")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    private String checkPassword;
}
