import React, {Component} from "react";
import Post from "../model/Post";
import PostList from "./PostList";
import PostListPresenter from "../presenter/PostListPresenter"
import FilteredPosts from "./FilteredPosts";

const mapModelStateToComponentState = modelState => ({
    searchedPost : modelState.searchedPost
});

export default class SmartFilteredPosts extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(Post.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        Post.addListener("change", this.listener);
    }

    componentWillUnmount() {
        Post.removeListener("change", this.listener);
    }

    render(){
        return (
            <FilteredPosts
                posts={this.state.searchedPost}
                onViewDetails={PostListPresenter.onViewDetails}
            />
        );
    }
}