package board.service;

import java.util.List;
import java.util.NoSuchElementException;

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
	private final PostRepository postRepository;
	
    public PostDto getPost(Long pid) {
    	  return postRepository.findById(pid)
  	            				.map(PostDto::from)
  	            				.orElseThrow(() -> new NoSuchElementException("해당 게시글 존재 X"));
    }
    
	@Transactional
    public void registerPost(PostDto postDto) {
		// 로그인 되었다고 가정 : save는 처음 1번만!
//    	User user = userRepository.save(User.of(postDto.getUserDto().getUid(),
//    											postDto.getUserDto().getPassword(),
//    											postDto.getUserDto().getPassword(),
//    											postDto.getUserDto().getEmail(),
//    											postDto.getUserDto().getUserRoleType()));
    	User user = userRepository.getReferenceById(postDto.getUserDto().getUid());
		
    	Post post = postDto.toEntity(user);
    	postRepository.save(post);
    }
	
    public void updatePost(Long pid, PostDto postDto){
    	
    }
	
    public void deletePost(long pid, String uid) {

    }

	public List<PostDto> getPosts() {
		return postRepository.findAll().stream()
										.map(PostDto::from)
//										.map(post -> PostDto.from(post))
										.toList();
	}
	


	
	
}
