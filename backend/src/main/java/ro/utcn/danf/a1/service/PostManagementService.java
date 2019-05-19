package ro.utcn.danf.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.danf.a1.dto.PostDTO;
import ro.utcn.danf.a1.exception.ObjectNotFoundException;
import ro.utcn.danf.a1.model.Post;
import ro.utcn.danf.a1.model.PostTag;
import ro.utcn.danf.a1.model.SiteUser;
import ro.utcn.danf.a1.model.Tag;
import ro.utcn.danf.a1.persistence.api.PostRepository;
import ro.utcn.danf.a1.persistence.api.PostTagRepository;
import ro.utcn.danf.a1.persistence.api.RepositoryFactory;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostManagementService {

    private final RepositoryFactory repositoryFactory;
    private static final String QUESTION = "QUESTION";
    private static final String ANSWER = "ANSWER";

    @Transactional
    public List<PostDTO> showQuestions() {
        List<Post> posts = repositoryFactory.createPostRepository().findAll().stream().
                filter(x -> x.getPosttype().equals(QUESTION)).
                sorted(Comparator.comparing(Post::getCreationdate, Comparator.nullsLast(Comparator.reverseOrder()))).
                collect(Collectors.toList());
        return convertPostListToPostDTOList(posts);
    }

    @Transactional
    public List<PostDTO> showAnswers(int questionId) {
        return repositoryFactory.createPostRepository().findAll().stream().
                filter(x -> x.getPosttype().equals(ANSWER)).
                filter(x -> x.getParentid().equals(questionId)).
                sorted(Comparator.comparing(Post::getCreationdate, Comparator.nullsLast(Comparator.reverseOrder())))
                .map(a -> PostDTO.ofEntity(a, new ArrayList<>()))
                .collect(Collectors.toList());
    }

    @Transactional
    public PostDTO askQuestion(SiteUser currentUser, String title, String text) {
        return convertPostToPostDTO(repositoryFactory.createPostRepository().save(
                new Post(currentUser.getUserid(), null, title, text, QUESTION, Calendar.getInstance().getTime())));
    }

    @Transactional
    public PostDTO getPostById(int postId){
        return convertPostToPostDTO(repositoryFactory.createPostRepository().findById(postId).orElseThrow(ObjectNotFoundException::new));
    }

    @Transactional
    public PostDTO postAnswer(SiteUser currentUser, int questionId, String text, String title)
            throws ObjectNotFoundException {
        PostRepository postRepository = repositoryFactory.createPostRepository();
        postRepository.findById(questionId).orElseThrow(ObjectNotFoundException::new);
        return convertPostToPostDTO(postRepository.save(
                new Post(currentUser.getUserid(), questionId, title, text, ANSWER, Calendar.getInstance().getTime())));
    }

    public List<PostDTO> filterQuestionsByTag(String tagTitle) throws ObjectNotFoundException {
        Tag tag = repositoryFactory.createTagRepository().findByTagTitle(tagTitle).orElseThrow(ObjectNotFoundException::new);
        List<Post> postList = repositoryFactory.createPostRepository().findAll();

        List<Post> questionList = postList.stream().filter(x -> x.getPosttype().equals(QUESTION)).collect(Collectors.toList());
        List<PostTag> postTagList = repositoryFactory.createPostTagRepository().findAll();

        List<Post> resultList = new ArrayList<>();
        for (Post question : questionList)
            for (PostTag postTag : postTagList)
                if (question.getPostid().equals(postTag.getPostid()) && postTag.getTagid().equals(tag.getTagid()))
                    resultList.add(question);

        return convertPostListToPostDTOList(resultList);
    }

    @Transactional
    public List<PostDTO> searchQuestionsByTitle(String title) {
        List<Post> posts = repositoryFactory.createPostRepository().findAll().stream().
                filter(x -> x.getPosttype().equals(QUESTION)).
                filter(x -> x.getTitle().toLowerCase().equals(title.toLowerCase())).collect(Collectors.toList());
        return convertPostListToPostDTOList(posts);
    }

    private List<Tag> getTagsForPostId(int postId) {
        List<Integer> tagIds = repositoryFactory.createPostTagRepository().findAll().stream().filter(x -> x.getPostid().
                equals(postId)).map(PostTag::getTagid).collect(Collectors.toList());

        List<Tag> tagList = new ArrayList<>();
        tagIds.forEach(id -> {
            tagList.add(getTagById(id));
        });
        return tagList;
    }

    private Tag getTagById(int id) {
        return repositoryFactory.createTagRepository().findAll().stream().filter(x -> x.getTagid().equals(id)).findFirst().orElseThrow(ObjectNotFoundException::new);
    }

    private List<PostDTO> convertPostListToPostDTOList(List<Post> posts) {
        List<PostDTO> postDTOList = new ArrayList<>();
        posts.forEach(p -> {
            postDTOList.add(convertPostToPostDTO(p));
        });

        return postDTOList;
    }

    private PostDTO convertPostToPostDTO(Post p){
        List<String> tagListName = getTagsForPostId(p.getPostid()).stream().map(Tag::getTitle).collect(Collectors.toList());
        return PostDTO.ofEntity(p, tagListName);
    }
}
