const BASE_URL = "http://localhost:8080";

class RestClient {
    constructor() {

    }

    logout() {
        this.username = "";
        this.password = "";
        this.authorization = "";
    }

    authentificate(username, password) {
        this.username = username;
        this.password = password;
        this.authorization = "Basic " + btoa(username + ":" + password);
    }

    loadAllPosts() {
        return fetch(BASE_URL + "/posts", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    createQuestion(title, text, tags) {
        return fetch(BASE_URL + "/posts", {
            method: "POST",
            body: JSON.stringify({
                title: title,
                text: text,
                tags: tags
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        }).then(response => response.json());
    }

    filterQuestionsByTitle(title) {
        debugger;
        return fetch(BASE_URL + "/posts/title/" + title, {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    filterQuestionsByTag(tagTitle) {
        return fetch(BASE_URL + "/posts/tag/" + tagTitle, {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }
}

const client = new RestClient();
client.authentificate("u1", "p1");

export default client;