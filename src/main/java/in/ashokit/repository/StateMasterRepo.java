package in.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.StateMasterEnity;

@Repository
public interface StateMasterRepo extends JpaRepository<StateMasterEnity, Integer> {

	public List<StateMasterEnity> findBycountryid(Integer countryId);

}
