import React from "react"

const PostDetails = ({postid, userid, parentid, title, text, posttype, creationdate, tags}) => (
    <div>
        <h2>Post details</h2>

        <label>Post ID: </label>
        <span>{postid}</span>
        <br/>

        <label>User ID: </label>
        <span>{userid}</span>
        <br/>

        <label>Parent ID: </label>
        <span>{parentid}</span>
        <br/>

        <label>Title: </label>
        <span>{title}</span>
        <br/>

        <label>Text: </label>
        <span>{text}</span>
        <br/>

        <label>Post Type: </label>
        <span>{posttype}</span>
        <br/>

        <label>Creation Date: </label>
        <span>{creationdate}</span>
        <br/>

        <label>Tags </label><br/>
        <span>{ tags.length>0?tags.reduce((a,x)=>a+","+x):""}</span>
        <br/>

    </div>
);

export default PostDetails;