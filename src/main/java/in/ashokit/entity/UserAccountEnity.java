package in.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "USER_ACCOUNT")
@Data
public class UserAccountEnity {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "FIRST_NAME")
	private String fname;

	@Column(name = "LAST_NAME")
	private String lname;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "PHONE_NUMBER")
	private Long phno;

	@Column(name = "DATE_OF_BIRTH")
	private LocalDate dob;

	@Column(name = "gender")
	private String gender;

	@Column(name = "COUNTRY_ID")
	private Integer countryId;

	@Column(name = "STATE_ID")
	private Integer stateId;

	@Column(name = "CITY_ID")
	private Integer cityId;

	@Column(name = "ACCOUNT_STATUS")
	private String accStatus;

	@Column(name = "CREATED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;

	@Column(name = "UPDATED_DATE", updatable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;

}
