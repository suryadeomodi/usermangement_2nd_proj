package in.ashokit.bindings;

import lombok.Data;

@Data
public class UnlockAccForm {

	private String email;
	private String tempPassword;
	private String newpwd;
	private String confirmPassword;

}
