package Student.NRI.Repository;




import org.springframework.data.jpa.repository.JpaRepository;
import Student.NRI.model.SignUp;

public interface SignUpRepository extends JpaRepository<SignUp, Long> {
    SignUp findByEmail(String email);
}
