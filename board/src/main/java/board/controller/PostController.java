package board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		List<Post> posts = postService.getPosts();
		map.addAttribute("posts", posts);
		
		return "posts/index";
	}
	
	@GetMapping("/form")
	public String postFormPage() {
		return "/posts/form";
	}
	
	@PostMapping
	public String registerPost(
			PostDto postDto
	) {
		UserDto userDto = UserDto.of("admin", "admin", "admin@board.com", "admin", UserRoleType.ROLE_ADMIN);
		postService.registerPost(PostDto.of(postDto.getTitle(), 
											postDto.getContent(), 
											postDto.getCategoryType(), 
											userDto));
		
		return "redirect:/posts";
	}
	
}