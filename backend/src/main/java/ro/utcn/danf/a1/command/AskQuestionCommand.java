package ro.utcn.danf.a1.command;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import ro.utcn.danf.a1.dto.PostDTO;
import ro.utcn.danf.a1.model.Post;
import ro.utcn.danf.a1.model.PostTag;
import ro.utcn.danf.a1.model.SiteUser;
import ro.utcn.danf.a1.model.Tag;
import ro.utcn.danf.a1.persistence.api.RepositoryFactory;
import ro.utcn.danf.a1.service.PostManagementService;
import ro.utcn.danf.a1.service.SiteUserDetailsService;
import ro.utcn.danf.a1.service.TagManagementService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class AskQuestionCommand implements Command{
    private final PostManagementService postManagementService;
    private final SiteUserDetailsService siteUserDetailsService;
    private final TagManagementService tagManagementService;

    @Override
    public PostDTO execute(PostDTO dto) {
        SiteUser currentUser = siteUserDetailsService.loadCurrentUser();
        PostDTO postDTO = postManagementService.askQuestion(currentUser, dto.getTitle(), dto.getText());
        tagManagementService.createTagsAndAttach(dto.getTags(), postDTO.getPostid());
        return postManagementService.getPostById(postDTO.getPostid());
    }
}
