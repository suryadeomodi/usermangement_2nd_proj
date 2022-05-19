package in.ashokit.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.bindings.LoginForm;
import in.ashokit.bindings.UnlockAccForm;
import in.ashokit.bindings.UserRegForm;
import in.ashokit.entity.CityMasterEnity;
import in.ashokit.entity.CountryMasterEnity;
import in.ashokit.entity.StateMasterEnity;
import in.ashokit.entity.UserAccountEnity;
import in.ashokit.repository.CityMasterRepo;
import in.ashokit.repository.CountryMasterRepo;
import in.ashokit.repository.StateMasterRepo;
import in.ashokit.repository.UserDtlsRepo;
import in.ashokit.util.EmailUtils;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {
	@Autowired
	private UserDtlsRepo repo;
	@Autowired
	private CountryMasterRepo countryrepo;
	@Autowired
	private StateMasterRepo stateRepo;
	@Autowired
	private CityMasterRepo cityMasterRepo;
	@Autowired
	private EmailUtils emailutils;

	@Override
	public String login(LoginForm loginForm) {
		UserAccountEnity entity = repo.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());

		if (entity == null) {
			return "Invalid Credentials";
		}

		if (entity != null && entity.getAccStatus() == "LOCKED") {
			return "Your Account Is Locked";
		}

		return "Welcome to suryaIT";
	}

	@Override
	public String emailCheck(String emailId) {
		UserAccountEnity enity = repo.findByEmail(emailId);
		if (enity != null) {
			return "Please Enter Unique EmailId";
		}

		return "";
	}

	@Override
	public Map<Integer, String> getCountryist() {
		List<CountryMasterEnity> countryMasterEnities = countryrepo.findAll();

		Map<Integer, String> countriesMap = new HashMap<Integer, String>();

		for (CountryMasterEnity enity : countryMasterEnities) {
			countriesMap.put(enity.getCountryId(), enity.getCountryName());
		}

		return countriesMap;
	}

	@Override
	public Map<Integer, String> getStateList(Integer countryId) {

		List<StateMasterEnity> enities = stateRepo.findBycountryid(countryId);

		Map<Integer, String> stateMap = new HashMap<>();

		for (StateMasterEnity enity : enities) {
			stateMap.put(enity.getStateId(), enity.getStateName());
		}

		return stateMap;
	}

	@Override
	public Map<Integer, String> getCityList(Integer stateId) {
		List<CityMasterEnity> masterRepos = cityMasterRepo.findByStateId(stateId);

		Map<Integer, String> citiesMap = new HashMap<>();

		for (CityMasterEnity entity : masterRepos) {
			citiesMap.put(entity.getCity_id(), entity.getCityName());
		}

		return citiesMap;
	}

	@Override
	public String resgisterUser(UserRegForm regForm) {

		UserAccountEnity accountEnity = new UserAccountEnity();

		BeanUtils.copyProperties(regForm, accountEnity);

		accountEnity.setAccStatus("LOCKED");

		accountEnity.setPassword(UserMgmtServiceImpl.generateRandomPassword());

		repo.save(accountEnity);

		// TODO : email send
		String fileName = "UNLOCK-ACCOUNT-EMAIL-BODY.txt";

		String body = UserMgmtServiceImpl.readDynamicTextEmailBody(fileName, accountEnity);

		boolean isSent = emailutils.sendEmail(accountEnity.getEmail(), "To Register the account", body);
		if (isSent) {
			return "SUCCESS";
		}
		return "FAILED";
	}

	@Override
	public String unlock(UnlockAccForm unlockAccForm) {
		// TODO Auto-generated method stub
		System.out.println("hiiiiiiiiiiiiiii");
		System.out.println("TTTTTTTTT==========================TTTTTTTTT"+unlockAccForm.toString());
		UserAccountEnity accountEnity = repo.findByEmailAndPassword(unlockAccForm.getEmail(),
				unlockAccForm.getTempPassword());
		
		if (!unlockAccForm.getNewpwd().equals(unlockAccForm.getConfirmPassword())) {
			return "New Password And Temperory Password should be same";
		}
		if (accountEnity == null) {
			return "Incorrect Temperory password";
		}
		accountEnity.setPassword(unlockAccForm.getNewpwd());
		accountEnity.setAccStatus("UNLOCKED");

		repo.save(accountEnity);

		return "Account unlocked, please proceed with login";
	}

	@Override
	public String forgotPassword(String email) {
		UserAccountEnity accountEnity = repo.findByEmail(email);

		if (accountEnity == null) {
			return "Email does not exist";
		}

		// TODO :send mail

		String fileName = "FORGOT-PASSWORD.txt";

		String body = UserMgmtServiceImpl.readDynamicTextEmailBody(fileName, accountEnity);
		boolean isSent = emailutils.sendEmail(email, "Reset Password", body);

		if (isSent == true) {
			return "Please Check Mail";
		}

		return null;
	}

	public static String generateRandomPassword() {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi" + "jklmnopqrstuvwxyz!@#$%&";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(6);
		for (int i = 0; i < 6; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

	public static String readDynamicTextEmailBody(String fileName, UserAccountEnity accountEnity) {
		String mailBody = null;
		try {

			StringBuffer stringBuffer = new StringBuffer();

			FileReader reader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine(); // reading first line data
			while (line != null) {
				stringBuffer.append(line);// appending data to stringBuffer
				line = bufferedReader.readLine();// read next line data
			}
			mailBody = stringBuffer.toString();
			 mailBody = mailBody.replace("{FNAME}", accountEnity.getFname());
			 mailBody = mailBody.replace("{LNAME}", accountEnity.getLname());
			 mailBody = mailBody.replace("{PWD}", accountEnity.getPassword());
			 mailBody = mailBody.replace("{EMAIL}", accountEnity.getEmail());
			 mailBody = mailBody.replace("{TEMP-PWD}", accountEnity.getPassword());
			bufferedReader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mailBody;
	}

}
