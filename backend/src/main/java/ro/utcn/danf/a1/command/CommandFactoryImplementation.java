package ro.utcn.danf.a1.command;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.utcn.danf.a1.service.PostManagementService;
import ro.utcn.danf.a1.service.SiteUserDetailsService;
import ro.utcn.danf.a1.service.TagManagementService;

@Component
@RequiredArgsConstructor
public class CommandFactoryImplementation implements CommandFactory {
    private final PostManagementService postManagementService;
    private final SiteUserDetailsService siteUserDetailsService;
    private final TagManagementService tagManagementService;

    @Override
    public Command getAskQuestionCommand() {
        return new AskQuestionCommand(postManagementService, siteUserDetailsService, tagManagementService);
    }


}
