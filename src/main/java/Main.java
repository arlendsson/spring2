import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {

	public static void main(String[] args) {
		System.out.println("########## encode pw : " + new BCryptPasswordEncoder().encode("1234"));
	}
}
