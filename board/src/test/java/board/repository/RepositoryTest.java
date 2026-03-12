package board.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.annotation.Rollback;

import board.entity.User;

@DisplayName("Repository 테스트")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({RepositoryTest.TestJPAConfig.class})
//@Import({RepositoryTest.TestJPAConfig.class, UserRoleTypeConverter.class})
class RepositoryTest {
	
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final PostCommentRepository postCommentRepository;
	
	RepositoryTest(
			@Autowired UserRepository userRepository,
			@Autowired PostRepository postRepository,
			@Autowired PostCommentRepository postCommentRepository
	) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
		this.postCommentRepository = postCommentRepository;
	}
	
	
	@DisplayName("Repository 객체 확인")
	@Disabled
	@Test
	void test() {
		System.out.println(userRepository);
		System.out.println(postRepository);
		System.out.println(postCommentRepository);
	}
	
	
	@DisplayName("Repository Dummy 데이터")
//	@Rollback(false)
	@Disabled
	@Test
	void setDummies() throws InterruptedException {
//		User admin = User.of("admin", 
//							 "admin", 
//							 "admin", 
//							 "admin@board.com", 
//							 UserRoleType.ROLE_ADMIN);
//		userRepository.save(admin);

//		List<User> users = IntStream.rangeClosed(1, 10)
//										.mapToObj(i -> User.of("user"+i, 
//																"user"+i, 
//																"user"+i, 
//																"user"+i + "@board.com", 
//																UserRoleType.ROLE_USER))
//										.collect(Collectors.toList());
//		userRepository.saveAllAndFlush(users);
//		List<CategoryType> categories = Arrays.asList((CategoryType.FRONT),
//														(CategoryType.WEB),
//														(CategoryType.BACKEND));
//
//		List<Post> posts = IntStream.rangeClosed(1, 200)
//										.mapToObj(i -> Post.of("title"+i, 
//															   "post content : "+i, 
//															   categories.get(i%categories.size()), 
//															   users.get((i-1)%users.size())))
//										.collect(Collectors.toList());
//		
//		postRepository.saveAllAndFlush(posts);		
//		List<PostComment> postComments = IntStream.rangeClosed(1, 600)
//													.mapToObj(i -> PostComment.of("post comment : "+i, 
//																				posts.get((i-1)%posts.size()), 
//																				users.get((i-1)%users.size())))
//													.collect(Collectors.toList());
//		
//		postCommentRepository.saveAllAndFlush(postComments);
		
	}
	
	@DisplayName("Repository Dummy Converted 데이터")
	@Rollback(false)
//	@Disabled
	@Test
	void setConvertedDummies() throws InterruptedException {

//		List<User> users = IntStream.rangeClosed(11, 15)
//										.mapToObj(i -> User.of("user"+i, 
//																"user"+i, 
//																"user"+i, 
//																"user"+i + "@board.com", 
//																UserRoleType.USER))
//										.collect(Collectors.toList());
//		userRepository.saveAllAndFlush(users);
		
		List<User> users = userRepository.findAll();
		System.out.println(users);
		
	}
	
	@EnableJpaAuditing
	@TestConfiguration
	static class TestJPAConfig {
		@Bean
		AuditorAware<String> auditorAware() {
			return () -> Optional.of("admin");
		}
		
//		@Bean
//		UserRoleTypeConverter userRoleTypeConverter() {
//			return new UserRoleTypeConverter();
//		}
		
	}
}