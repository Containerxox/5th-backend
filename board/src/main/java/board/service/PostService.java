package board.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import board.dto.PostDto;
import board.dto.PostWithCommentsDto;
import board.dto.request.SearchRequest;
import board.dto.response.PostResponse;
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

	@Transactional
    public PostDto getPost(Long pid) {
    	return postRepository.findById(pid)
    							.map(PostDto::from)
    							.orElseThrow(() -> new NoSuchElementException("해당 게시글 존재 x"));
    }

	@Transactional
    public PostWithCommentsDto getPostWithComments(Long pid) {
    	return postRepository.findById(pid)
    							.map(PostWithCommentsDto::from)
    							.orElseThrow(() -> new NoSuchElementException("해당 게시글 존재 x"));
    }

	@Transactional
    public void registerPost(PostDto postDto) {
		// 로그인 가정 : save 처음 1번만!
//    	User user = userRepository.save(User.of(postDto.getUserDto().getUid(),
//    											postDto.getUserDto().getPassword(),
//    											postDto.getUserDto().getPassword(),
//    											postDto.getUserDto().getEmail(),
//    											postDto.getUserDto().getUserRoleType()));

		User user = userRepository.getReferenceById(postDto.getUserDto().getUid());

    	Post post = postDto.toEntity(user);
    	postRepository.save(post);
    }

	@Transactional
    public void updatePost(Long pid, PostDto postDto) {

		Post post = postRepository.getReferenceById(pid);

		post.updateTitleAndContentAndCategoryType(postDto.getTitle(), 
												  postDto.getContent(), 
												  postDto.getCategoryType());

    }

	@Transactional
    public void deletePost(long pid, String uid) {

		// post 존재 + post 작성한 사람만이 삭제
		postRepository.deleteByIdAndUser_Uid(pid, uid);

    }

    @Transactional
	public List<PostDto> getPosts() {
		return postRepository.findAll().stream()
										.map(PostDto::from)
//										.map(post -> PostDto.from(post))
										.toList();
	}
    
    @Transactional
	public Page<PostDto> getPostsWithPage(Pageable pageable) { // 게시글을 페이지 단위로 조회해서 PostDto로 변환 후 반환
    	return postRepository.findAll(pageable)
    							.map(PostDto::from);
	}

    
    @Transactional
	public List<PostDto> getPostsWithSearch(SearchRequest searchRequest) {
		
    	// searchType, searchValue
    	// isBlank() -공백/탭/개행 등의 의미없는 문자 체크
    	// isEmpty() - 길이가 0인지 체크
    	if(!searchRequest.hasSearch()) {
    	    		return postRepository.findAll().stream()
    	    									.map(PostDto::from)
    	    									.toList();
    	    	}

    	// 검색 타입, 값 일치하는 부분을 리턴
    	return switch (searchRequest.getSearchType()) {
				case "title" -> postRepository.findByTitleContains(searchRequest.getSearchValue()).stream().map(PostDto::from).toList();
				case "content" -> postRepository.findByContentContains(searchRequest.getSearchValue()).stream().map(PostDto::from).toList();
				case "uid" -> postRepository.findByUser_UidContains(searchRequest.getSearchValue()).stream().map(PostDto::from).toList();
													
				default -> throw new IllegalArgumentException("Unexpected value: " + searchRequest.getSearchType());
    	};
	}

	public Page<PostDto> getPostWithSearchAndPage(SearchRequest searchRequest, Pageable pageable) {
		
    	// searchType, searchValue
    	// isBlank() -공백/탭/개행 등의 의미없는 문자 체크
    	// isEmpty() - 길이가 0인지 체크
    	if(!searchRequest.hasSearch()) {
    	    		return postRepository.findAll(pageable)
    	    									.map(PostDto::from);
    	    	}

    	// 검색 타입, 값 일치하는 부분을 리턴
    	return switch (searchRequest.getSearchType()) {
				case "title" -> postRepository.findByTitleContains(searchRequest.getSearchValue(), pageable).map(PostDto::from);
				case "content" -> postRepository.findByContentContains(searchRequest.getSearchValue(), pageable).map(PostDto::from);
				case "uid" -> postRepository.findByUser_UidContains(searchRequest.getSearchValue(), pageable).map(PostDto::from);
													
				default -> throw new IllegalArgumentException("Unexpected value: " + searchRequest.getSearchType());
    	};
	}
    
    /*
     * ▶ Page<PostDto>
     * : Page 객체는 단순 List가 아니라 "페이징 정보"까지 포함된 객체 (DB조회결과 + 페이징 정보)
     * - 현재 페이지     // page.getNumber() 
     * - 전체 페이지 수   // page.getTotalPages()
     * - 전체 데이터 수   // page.getTotalElments()
     * - 다음 페이지 존재 여부   // page.hasNext()
     * - 실제 데이터 List  // page.getContent()
     * - 이전 페이지 존재 여부 // page.hasPrevious()
     * 
     * ▶ Pageable pageable
     * : 페이지 정보를 담는 객체 (어떻게 몇 페이지, 몇 개씩, 어떤 정렬로 조회할지 정의하는 객체)
     * - Pageable.getPageNumber() : 현재 요청한 페이지
     * - Pageable.getPageSize() : 한 페이지당 데이터 수
     * - Pageable.getOffset() : SQL OFFSET 값
     * - Pageable.getSort() : 정렬 정보
     * ▶ PageRequest는 Pageable 인터페이스의 구현체!
     * 즉, 우리가 실제로 만드는 것은 Pageable pageable = PageRequest.of(...);
     * ▶ PageRequest.of(page,size)   // page=페이지번호, size=한 페이지당 데이터 개수
     * 예시) PageRequest.of(0,10) -> 첫페이지(0) 페이징 당 10개씩 조회(10)
     * 예시) PageRequest.of(1,5) -> 두번재 페이지(1) 페이징 당 5개씩 조회(5)
     * 
     * 
     * (정리)
     * - Pageable : 조회 조건
     * - Page : 조회 결과
     */

}
