package board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import board.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {

	private final UserRepository userRepository;
	
	@GetMapping({"/"})
	public String mainPage() {
		return "forward:/posts";
	}
	
}
