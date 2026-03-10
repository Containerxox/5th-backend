package board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import board.dto.PostCommentDto;
import board.dto.PostDto;
import board.dto.UserDto;
import board.dto.request.PostCommentRequest;
import board.entity.constant.UserRoleType;
import board.service.PostCommentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class PostCommentController {
	
	private final PostCommentService postCommentService;
	
	
	@PostMapping
	public String registerNewPostComment(PostCommentRequest postCommentRequest) {

		System.out.println("-------");
		System.out.println(postCommentRequest);
		
		// 로그인 되었다고 가정하고 진행
		UserDto userDto = UserDto.of("admin", "admin", "admin@board.com", "admin", UserRoleType.ROLE_ADMIN);
		
		postCommentService.registerPostComment(postCommentRequest.toDto(userDto));
		
		return "redirect:/posts/" + postCommentRequest.getPid();
	}
	
	@DeleteMapping("/{pcid}")
	public String deletePostComment(@PathVariable Long pcid) {
		return "";
	}
}