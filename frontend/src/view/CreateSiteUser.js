import React from "react";

const CreateSiteUser = ({ userid, username, password, email, onCreate, onChange }) => (
    <div>

        <h2 className="display-2"> Add a User </h2>
        <div className="jumbotron-fluid">
            <label> User ID </label>
            <input value={userid} onChange={e => onChange("userid",e.target.value)}/>
            <br/>

            <label> Username </label>
            <input value={username} onChange={e => onChange("username",e.target.value)}/>
            <br/>

            <label> Password </label>
            <input value={password} onChange={e => onChange("password",e.target.value)}/>
            <br/>

            <label> Email </label>
            <input value={email} onChange={e => onChange("email",e.target.value)}/>
            <br/>

            <button onClick={onCreate}>Add user</button>
        </div>
        <hr/>

    </div>
);


export default CreateSiteUser;

