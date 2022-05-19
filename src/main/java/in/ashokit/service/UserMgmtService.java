package in.ashokit.service;

import java.util.Map;

import in.ashokit.bindings.LoginForm;
import in.ashokit.bindings.UnlockAccForm;
import in.ashokit.bindings.UserRegForm;

public interface UserMgmtService {
	// Login functionality method
	public String login(LoginForm loginForm);

	// Registration functionality method
	public String emailCheck(String emailId);

	// loading all countries
	public Map<Integer, String> getCountryist();

	// loading state based on country id
	public Map<Integer, String> getStateList(Integer countryId);

	// loading cities based on city
	public Map<Integer, String> getCityList(Integer stateId);

	// Register User functionality
	public String resgisterUser(UserRegForm regForm);

	// Unclock Account functionality
	public String unlock(UnlockAccForm unlockAccForm);

	// forgot password functionality method
	public String forgotPassword(String email);

}
