import post from "../model/Post"

class PostListPresenter{
    onCreatePost(){
        window.location.assign("#/create-post")
    }

    onViewDetails(index){
        window.location.assign("#/post-details/" + index);
    }

    onSearchQuestions(){
        window.location.assign("#/")
    }

    updateToSearch(property,value){
        post.toSearchChange(property,value);
    }

    filterPostsTitle(){
        post.filterPostsTitle();
        window.location.assign("#/search-title-post/");
    }

    filterPostsTag(){
        post.filterPostsTag();
        window.location.assign("#/search-tag-post/");
    }

    onInit(){
        post.loadPosts();
    }
}



const postListPresenter = new PostListPresenter();
export default postListPresenter;