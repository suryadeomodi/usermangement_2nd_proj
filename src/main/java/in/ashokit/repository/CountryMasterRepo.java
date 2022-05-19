package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.CountryMasterEnity;

@Repository
public interface CountryMasterRepo extends JpaRepository<CountryMasterEnity, Integer>{

}
