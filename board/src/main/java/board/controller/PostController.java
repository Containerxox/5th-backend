package board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import board.dto.PostDto;
import board.dto.UserDto;
import board.entity.Post;
import board.entity.constant.CategoryType;
import board.entity.constant.UserRoleType;
import board.service.PostService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostController {
	
	private final PostService postService;
	
	@GetMapping
	public String getPosts(ModelMap map) {
		
		List<PostDto> posts = postService.getPosts();
		map.addAttribute("posts", posts);
		
		return "posts/index";
	}
	
	@GetMapping("/form")
	public String postFormPage() {
		return "posts/post-form";
	}
	
	
	
	@GetMapping("/{pid}")
	public String getPost(@PathVariable Long pid, ModelMap map) {
		
		PostDto post= postService.getPost(pid);
		map.addAttribute("post", post);
		
		return "posts/post-detail";
	}
	
	
	
	@PostMapping
	public String registerPost(
			PostDto postDto
	) {
//		System.out.println("-------");  // 확인용
//		System.out.println(postDto);
		
		// 로그인 되었다고 가정하고 진행
		UserDto userDto = UserDto.of("admin", "admin", "admin@board.com", "admin", UserRoleType.ROLE_ADMIN); //게시글 작성하는 작성자의 정보
		postService.registerPost(PostDto.of(postDto.getTitle(), 
											postDto.getContent(), 
											postDto.getCategoryType(), 
											userDto));
		
		return "redirect:/posts";
	}
	
	
	
	
}