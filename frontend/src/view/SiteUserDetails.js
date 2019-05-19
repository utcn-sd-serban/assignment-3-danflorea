import React from "react"

const SiteUserDetails = ({userid,username,password,email}) => (
    <div>
        <h2>Site User details</h2>

        <label>ID: </label>
        <span>{userid}</span>
        <br/>

        <label>Username: </label>
        <span>{username}</span>
        <br/>

        <label>Password: </label>
        <span>{password}</span>
        <br/>

        <label>Email: </label>
        <span> {email} </span>
        <br/>


    </div>
);

export default SiteUserDetails;