package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import in.ashokit.bindings.UnlockAccForm;
import in.ashokit.service.UserMgmtService;

@RestController
public class UnlockAccRestController {
	@Autowired
	private UserMgmtService service;

	@PostMapping("/unlockacc")
	public ResponseEntity<String> unlockAcc(@RequestBody UnlockAccForm unlockAccForm) {

		String status = service.unlock(unlockAccForm);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}

}
