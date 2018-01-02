function updateList() {
    var ul = document.getElementById("list");
    ul.innerHTML = '';
    var notes = JSON.parse(findAll());

    for (var i = 0; i < notes.length; i++) {
        var li = document.createElement("li");
        li.appendChild(document.createTextNode(notes[i].text));
        ul.appendChild(li);
    }
}

function removeLast() {
    var notes = JSON.parse(findAll());
    deleteById(notes[notes.length-1].id);
    updateList();
}

function addText() {
    var payload = new Object();
    payload.text = document.getElementById("text").value;
    var req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8080/note", false);
    req.setRequestHeader("Content-type", "application/json");
    req.send(JSON.stringify(payload));
    if (req.status === 201) {
        updateList();
    } else {
        document.getElementById("output").innerHTML = "Error llamada REST: http://localhost:8080/note";
    }
}

function findById(id) {
    var url = "http://localhost:8080/note?id=" + id;
    var req = new XMLHttpRequest();
    req.open('GET', url, false);
    req.setRequestHeader("Content-type", "application/json");
    req.send(null);
    if (req.status === 200) {
        var note = JSON.parse(req.responseText);
        return note.text;
    } else {
        return "Error llamada REST: " + url;
    }
}

function findAll() {
    var req = new XMLHttpRequest();
    req.open('GET', "http://localhost:8080/note/find", false);
    req.setRequestHeader("Content-type", "application/json");
    req.send(null);
    if (req.status === 200) {
        return req.responseText;
    } else {
        return "Error llamada REST: http://localhost:8080/note/find";
    }
}

function deleteById(id) {
    var url = "http://localhost:8080/note?id=" + id;
    var req = new XMLHttpRequest();
    req.open('DELETE', url, false);
    req.setRequestHeader("Content-type", "application/json");
    req.send(null);
}
