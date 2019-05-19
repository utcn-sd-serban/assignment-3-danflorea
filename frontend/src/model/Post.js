import {EventEmitter} from "events";
import client from "../rest/RestClient"

class Post extends EventEmitter{

    constructor(){
        super();
        this.state = {
            posts: [],
            newPost:{
                title: "",
                text: "",
                tags: []
            },

            searchedPost:[],

            toSearch:""
        };
    }

    loadPosts(){
        return client.loadAllPosts().then(posts => {
            this.state = {
                ...this.state,
                posts: posts
            };
            this.emit("change", this.state);
        })
    }

    addPost(title, text, tags){
        client.createQuestion(title, text, tags).then(response=>{
            debugger;
            this.state = {
                ...this.state,
                posts: this.state.posts.concat([{
                    postid: response.postid,
                    userid: response.userid,
                    parentid: response.parentid,
                    title: response.title,
                    text: response.text,
                    posttype: response.posttype,
                    creationdate: response.creationdate,
                    tags: response.tags
                }])
            };
            this.emit("change", this.state);
        });
    }

    changeNewPostProperty(property,value){
        this.state = {
            ...this.state,
            newPost:{
                ...this.state.newPost,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }

    filterPostsTitle(){
        return client.filterQuestionsByTitle(this.state.toSearch).then(posts => {
            debugger;
            this.state = {
                ...this.state,
                searchedPost: posts
            };
            this.emit("change", this.state);
        });
    }

    filterPostsTag(){
        return client.filterQuestionsByTag(this.state.toSearch).then(posts => {

            this.state = {
                ...this.state,
                searchedPost: posts
            };
            this.emit("change", this.state);
        });
    }

    toSearchChange(property,value) {
        this.state = {
            ...this.state,
            [property]: value
        };
        this.emit("change", this.state);
    }

}

const post = new Post();


export default post;