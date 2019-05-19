package ro.utcn.danf.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.danf.a1.exception.ObjectNotFoundException;
import ro.utcn.danf.a1.model.Post;
import ro.utcn.danf.a1.model.PostTag;
import ro.utcn.danf.a1.model.Tag;
import ro.utcn.danf.a1.persistence.api.RepositoryFactory;
import ro.utcn.danf.a1.persistence.api.TagRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagManagementService {

    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Tag> showTags(){
        return new ArrayList<>(repositoryFactory.createTagRepository().findAll());
    }


    public void addTagToQuestion(String tagTitle, int questionId){
       Tag tag = repositoryFactory.createTagRepository().findByTagTitle(tagTitle).orElseThrow(ObjectNotFoundException::new);
       Post question = repositoryFactory.createPostRepository().findById(questionId).orElseThrow(ObjectNotFoundException::new);
       repositoryFactory.createPostTagRepository().save(new PostTag(question.getPostid(), tag.getTagid()));
    }

    public void createTagsAndAttach(List<String> tagNameList, int postId){
        TagRepository tagRepository = repositoryFactory.createTagRepository();
        for(String tagName : tagNameList) {
            Optional<Tag> tagOptional = tagRepository.findByTagTitle(tagName);
            Tag tag = tagOptional.orElseGet(() -> tagRepository.save(new Tag(tagName)));
            addTagToQuestion(tag.getTitle(), postId);
        }
    }
}
