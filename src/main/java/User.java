import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Id;

import lombok.Data;
//https://github.com/yookeun/springboot-jwt-example/blob/master/springboot-jwt-example-auth/src/main/java/com/example/auth/user/model/User.java
@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 20, nullable = false, unique = true)
	private String userName;
	
	@Column(length = 100, nullable = false)
	private String password;
	
	//1:수퍼관리자, 2:관리자, 3:사용자
	@Column(length = 1, nullable = false)
	private String userType;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate = new Date();
}