package board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import board.dto.PostDto;
import board.entity.Post;
import board.entity.User;
import board.repository.PostRepository;
import board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	
	private final UserRepository userRepository;
	private final PostRepository postReository;
	
    public PostDto getPost(Long pid) {
    	return null;
    }
    
	@Transactional
    public void registerPost(PostDto postDto) {
    	User user = userRepository.save(User.of(postDto.getUserDto().getUid(),
    											postDto.getUserDto().getPassword(),
    											postDto.getUserDto().getPassword(),
    											postDto.getUserDto().getEmail(),
    											postDto.getUserDto().getUserRoleType()));
    	
    	Post post = postDto.toEntity(user);
    	postReository.save(post);
    }
	
    public void updatePost(Long pid, PostDto postDto) {

    }
	
    public void deletePost(long pid, String uid) {

    }

	public List<Post> getPosts() {
		return postReository.findAll();
	}
	
}
