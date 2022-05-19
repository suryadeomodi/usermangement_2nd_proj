package in.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.CityMasterEnity;

@Repository
public interface CityMasterRepo extends JpaRepository<CityMasterEnity, Integer> {

	public List<CityMasterEnity> findByStateId(Integer stateId);

}
