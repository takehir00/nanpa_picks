package client.forms;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserForm {
    public Long id;

    @NotEmpty
    @Size(max = 30, message = "30文字以下で入力してください")
    public String name;

    @NotEmpty
    @Email
    public String mail;

    @Size(max = 500)
    public String introduction;

    public String imageUrl;

    @Size(max = 20, min = 4)
    public String password;

    @Size(max = 20, min = 4)
    public String passwordConfirm;
}
