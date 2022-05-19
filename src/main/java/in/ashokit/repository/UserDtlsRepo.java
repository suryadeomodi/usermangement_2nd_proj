package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.UserAccountEnity;

@Repository
public interface UserDtlsRepo extends JpaRepository<UserAccountEnity, Integer> {

	public UserAccountEnity findByEmailAndPassword(String email, String password);

	public UserAccountEnity findByEmail(String email);

}
