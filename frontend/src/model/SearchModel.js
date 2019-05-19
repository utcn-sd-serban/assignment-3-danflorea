import {EventEmitter} from "events";

const searchTypeTag = "TAG";
const searchTypeTitle = "TITLE";

class SearchModel extends EventEmitter{

    constructor(){
        super();
        this.state = {
            searchModels: [
                {
                    id:0,
                    title: "CENAN"
                }
            ],

            newSearchModel:{
                searchType: "",
                searchTerm: ""
            },
        };
    }
    addSearchModel(searchType, searchTerm){
        this.state = {
            ...this.state,
            searchModels: this.state.posts.concat([{
                searchType: searchType,
                searchTerm: searchTerm
            }])
        };
        this.emit("change", this.state);
    }

    changeNewPostProperty(property,value){
        this.state = {
            ...this.state,
            newSearchModel:{
                ...this.state.newPost,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }
}

const searchModel = new SearchModel();
export default searchModel;