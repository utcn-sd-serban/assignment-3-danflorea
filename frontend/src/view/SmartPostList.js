import React, {Component} from "react";
import post from "../model/Post";
import PostList from "./PostList";
import PostListPresenter from "../presenter/PostListPresenter"

const mapModelStateToComponentState = modelState => ({
    posts : modelState.posts
});

export default class SmartPostList extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(post.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        post.addListener("change", this.listener);
        PostListPresenter.onInit();
    }

    componentWillUnmount() {
        post.removeListener("change", this.listener);
    }

    render(){
        return (
            <PostList
                posts={this.state.posts}
                onViewDetails={PostListPresenter.onViewDetails}
                onCreatePost={PostListPresenter.onCreatePost}
                onSearchTitleClicked={PostListPresenter.filterPostsTitle}
                onSearchTagClicked={PostListPresenter.filterPostsTag}
                onChange={PostListPresenter.updateToSearch}
            />
        );
    }
}