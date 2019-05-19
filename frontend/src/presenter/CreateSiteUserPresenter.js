import Model from "../model/Model"

class CreateSiteUserPresenter{

    onCreate(){
        Model.addSiteUser(Model.state.newSiteUser.userid, Model.state.newSiteUser.username,
            Model.state.newSiteUser.password, Model.state.newSiteUser.email);
        Model.changeNewSiteUserProperty("userid","");
        Model.changeNewSiteUserProperty("username","");
        Model.changeNewSiteUserProperty("password","");
        Model.changeNewSiteUserProperty("email","");
        window.location.assign("#/");
    }

    onChange(property, value){
        Model.changeNewSiteUserProperty(property,value);
    }
}

const createSiteUserPresenter = new CreateSiteUserPresenter();
export default createSiteUserPresenter;