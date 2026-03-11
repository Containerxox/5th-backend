package board.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.dto.PostDto;
import board.dto.UserDto;
import board.dto.request.SearchRequest;
import board.dto.response.PostResponse;
import board.dto.response.PostWithCommentsResponse;
import board.entity.constant.UserRoleType;
import board.service.PagingService;
import board.service.PostService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostController {
	
	private final PostService postService;
	private final PagingService pagingService;
	
	@GetMapping
	public String getPosts(
			@PageableDefault(size = 10) Pageable pageable,
			SearchRequest searchRequest,
			ModelMap map) {
		
		// http://localhost:8080?size=10
		System.out.println("---------");
		System.out.println(searchRequest);
		System.out.println("searchType : " + searchRequest.getSearchType());
		System.out.println("searchValue : " + searchRequest.getSearchValue());
		
		
		// v4
		Page<PostResponse> posts = postService.getPostWithSearchAndPage(searchRequest, pageable)
												.map(PostResponse::from);
		
		
		
//		List<PostResponse> posts = postService.getPostsWithSearch(searchRequest)
//												.stream()
//												.map(PostResponse::from)
//												.toList(); 
		
		// v3
//		Page<PostResponse> posts = postService.getPostsWithPage(pageable)
//													.map(PostResponse::from);
		
		List<Integer> pagingNumbers = pagingService.getPagingNumbers(pageable.getPageNumber() + 1, 
																		posts.getTotalPages());
		
		map.addAttribute("posts", posts);
		map.addAttribute("pagingNumbers", pagingNumbers);
		map.addAttribute("currentPage", pageable.getPageNumber()+1);
		
//		System.out.println("---");
//		System.out.println(posts);
//		System.out.println(posts.hasPrevious());
//		System.out.println(posts.hasNext());
		
		
		// v2
//		List<PostResponse> posts = postService.getPosts().stream()
//														.map(PostResponse::from)
//														.toList();
		
		// v1
//		List<PostDto> posts = postService.getPosts();
//		map.addAttribute("posts", posts);
		
		return "/posts/index";
	}
	
	@GetMapping("/{pid}")
	public String getPost(@PathVariable Long pid,
							ModelMap map) {
		
		PostWithCommentsResponse post = PostWithCommentsResponse.from(postService.getPostWithComments(pid));
		map.addAttribute("post", post);
		map.addAttribute("comments", post.getPostCommentResponse());
		
		// v1
//		PostDto post = postService.getPost(pid);
//		map.addAttribute("post", post);
		
		return "/posts/post-detail";
	}
	
	@GetMapping("/form")
	public String postFormPage() {
		return "/posts/post-form";
	}
	
	@GetMapping("/{pid}/form")
	public String updatePostFormPage(@PathVariable Long pid, 
									 ModelMap model) {
		
		PostDto post = postService.getPost(pid);
		model.addAttribute("post", post);
		
		return "/posts/post-form";
	}
	
	@PostMapping
	public String registerPost(
			PostDto postDto
	) {
		// 로그인 가정
		UserDto userDto = UserDto.of("admin", "admin", "admin@board.com", "admin", UserRoleType.ROLE_ADMIN);
		postService.registerPost(PostDto.of(postDto.getTitle(), 
											postDto.getContent(), 
											postDto.getCategoryType(), 
											userDto));
		
		return "redirect:/posts";
	}
	
	@PostMapping("/{pid}/edit")
	public String updatePost(
			@PathVariable Long pid,
			PostDto postDto
	) {
		// 로그인 가정
		UserDto userDto = UserDto.of("admin", "admin", "admin@board.com", "admin", UserRoleType.ROLE_ADMIN);
		postService.updatePost(pid, PostDto.of(postDto.getTitle(), 
												postDto.getContent(), 
												postDto.getCategoryType(), 
												userDto));
		
		return "redirect:/posts/" + pid;
	}

	
	@PostMapping("/{pid}/delete")
	public String deletePost(
			@PathVariable Long pid
	) {
		// 로그인 가정
		UserDto userDto = UserDto.of("admin", "admin", "admin@board.com", "admin", UserRoleType.ROLE_ADMIN);
		postService.deletePost(pid, userDto.getUid());
		
		return "redirect:/posts";
	}
	
}