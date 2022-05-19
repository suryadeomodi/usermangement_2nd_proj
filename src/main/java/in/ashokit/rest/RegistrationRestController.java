package in.ashokit.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.UserRegForm;
import in.ashokit.service.UserMgmtService;

@RestController
public class RegistrationRestController {

	@Autowired
	private UserMgmtService mgmtService;

	@GetMapping("/checkEmail/{email}")
	public String checkEmail(@PathVariable("email") String email) {

		return mgmtService.emailCheck(email);
	}

	@GetMapping("/countries")
	public Map<Integer, String> getCountries() {
		return mgmtService.getCountryist();
	}

	@GetMapping("/getStates/{countryId}")
	public Map<Integer, String> getStates(@PathVariable("countryId") Integer countryId) {
		return mgmtService.getStateList(countryId);
	}

	@GetMapping("/getCities/{stateId}")
	public Map<Integer, String> getCities(@PathVariable("stateId") Integer stateId) {
		return mgmtService.getCityList(stateId);
	}

	@PostMapping("/register")
	public String register(@RequestBody UserRegForm regForm) {
		return mgmtService.resgisterUser(regForm);
	}

}
