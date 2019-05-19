import Model from "../model/Model"

class SiteUserListPresenter{
    onCreateSiteUser(){
        window.location.assign("#/create-siteUser")
    }

    onViewDetails(index){
        window.location.assign("#/siteUser-details/" + index);
    }
}



const siteUserListPresenter = new SiteUserListPresenter();
export default siteUserListPresenter;