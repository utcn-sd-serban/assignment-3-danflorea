package ro.utcn.danf.a1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ro.utcn.danf.a1.command.AskQuestionCommand;
import ro.utcn.danf.a1.command.CommandFactory;
import ro.utcn.danf.a1.dto.PostDTO;
import ro.utcn.danf.a1.dto.SiteUserDTO;
import ro.utcn.danf.a1.model.SiteUser;
import ro.utcn.danf.a1.service.PostManagementService;
import ro.utcn.danf.a1.service.SiteUserDetailsService;
import ro.utcn.danf.a1.service.TagManagementService;
import ro.utcn.danf.a1.service.UserManagementService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostManagementService postManagementService;
    private final SiteUserDetailsService siteUserDetailsService;
    private final TagManagementService tagManagementService;
    private final CommandFactory commandFactory;

    @GetMapping("/posts")
    public List<PostDTO> readAll(){
        return postManagementService.showQuestions();
    }

    @PostMapping("/posts")
    public PostDTO create(@RequestBody PostDTO dto){
//        SiteUser currentUser = siteUserDetailsService.loadCurrentUser();
//        PostDTO postDTO = postManagementService.askQuestion(currentUser, dto.getTitle(), dto.getText());
//        tagManagementService.createTagsAndAttach(dto.getTags(), postDTO.getPostid());
//        return postManagementService.getPostById(postDTO.getPostid());

        return commandFactory.getAskQuestionCommand().execute(dto);
    }

    @GetMapping("/posts/{postId}")
    public PostDTO readPost(@PathVariable("postId") int postId){
        return postManagementService.getPostById(postId);
    }

    @GetMapping("/posts/{postId}/answers")
    public  List<PostDTO> readPostAnswers(@PathVariable("postId") int postId){

        return postManagementService.showAnswers(postId);
    }

    @PostMapping("/posts/{postId}/answers")
    public PostDTO createPostAnswer(@PathVariable("postId") int postId, @RequestBody PostDTO dto){
        SiteUser currentUser = siteUserDetailsService.loadCurrentUser();
        return postManagementService.postAnswer(currentUser, postId, dto.getText(), dto.getTitle());
    }

    @GetMapping("/posts/title/{postName}")
    public List<PostDTO> filterPostByTitle(@PathVariable("postName") String postName){
        return postManagementService.searchQuestionsByTitle(postName);
    }

    @GetMapping("/posts/tag/{tagName}")
    public List<PostDTO> filterPostByTag(@PathVariable("tagName") String tagName){
        return postManagementService.filterQuestionsByTag(tagName);
    }
}
