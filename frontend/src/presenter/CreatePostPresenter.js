import Post from "../model/Post"

class CreatePostPresenter{

    onCreate(){
        Post.addPost(
            Post.state.newPost.title,
            Post.state.newPost.text,
            Post.state.newPost.tags.split(","));
        window.location.assign("#/");
    }

    onChange(property, value){
        Post.changeNewPostProperty(property,value);
    }
}

const createPostPresenter = new CreatePostPresenter();
export default createPostPresenter;