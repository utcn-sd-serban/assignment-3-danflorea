import {EventEmitter} from "events";

class Model extends EventEmitter{

    constructor(){
        super();
        this.state = {
            siteUsers: [{
                userid: 1,
                username: "epic",
                password: "epicbad",
                email: "yes@no.yes"
            }, {
                userid: 2,
                username: "geraldo",
                password: "eabad",
                email: "geraldo@ea.bad"
            }],
            newSiteUser:{
                userid: "",
                username: "",
                password: "",
                email: ""
            },
        };
    }

    addSiteUser(userid, username, password, email){
        this.state = {
            ...this.state,
            siteUsers: this.state.siteUsers.concat([{
                userid: userid,
                username: username,
                password: password,
                email: email
            }])
        };
        this.emit("change", this.state);
    }

    changeNewSiteUserProperty(property,value){
        this.state = {
            ...this.state,
            newSiteUser:{
                ...this.state.newSiteUser,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }
}

const model = new Model();
export default model;