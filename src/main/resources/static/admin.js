
//------------------ALL-USERS--------------------------------

function getUsers() {

    fetch("http://localhost:8080/admin/users")
        .then((res) => res.json())
        .then((data) => {
            let temp = "";
            data.forEach(function (user) {

                temp += `
                <tr>
                <td id="id${user.id}">${user.id}</td>
                <td id="username${user.id}">${user.username}</td> 
                <td id="name${user.id}">${user.name}</td> 
                <td id="surname${user.id}">${user.surname}</td>
                <td id="age${user.id}">${user.age}</td>
                <td id="email${user.id}">${user.email}</td>
                <td id="roles${user.id}">${user.roles.map(r => r.name.replace('ROLE_','')).join(', ')}</td>
                <td>
                <button class="btn btn-info btn-md" type="button"
                data-toggle="modal" data-target="#editModal" 
                onclick="fillModal(${user.id})">Edit</button></td>
                <td>
                <button class="btn btn-danger btn-md" type="button"
                data-toggle="modal" data-target="#deleteModal" 
                onclick="fillModal(${user.id})">Delete</button></td>
              </tr>`;
            });
            document.getElementById("usersTable").innerHTML = temp;
        })
}
getUsers()

//------------------fillModals--------------------------------

function fillModal(id) {
    fetch("http://localhost:8080/admin/users/" + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(res => {
        res.json().then(user => {
            document.getElementById('id').value = user.id;
            document.getElementById('editUsername').value = user.username;
            document.getElementById('editPassword').value = user.password;
            document.getElementById('editName').value = user.name;
            document.getElementById('editSurname').value = user.surname;
            document.getElementById('editAge').value = user.age;
            document.getElementById('editEmail').value = user.email;


            document.getElementById('delId').value = user.id;
            document.getElementById('delUsername').value = user.username;
            // document.getElementById('delPassword').value = user.password;
            document.getElementById('delName').value = user.name;
            document.getElementById('delSurname').value = user.surname;
            document.getElementById('delAge').value = user.age;
            document.getElementById('delEmail').value = user.email;

        })
    });
}

//------------------SHOW-User--------------------------------

function showUser() {
    //$("#topNav").css("display", "none");
    const showUserURL = 'http://localhost:8080/admin/infoUsername';
    fetch(showUserURL)
        .then((res) => res.json())
        .then((user) => {

            let temp = "<tr>";
            temp += `
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${user.roles.map(r => r.name.replace('ROLE_','')).join(', ')}</td>
            `;
            temp += "<tr>";
            document.getElementById("userTable").innerHTML = temp;
        })
}
showUser();

//------------------NEW-USER--------------------------------

document.getElementById("newUserForm")
    .addEventListener("submit", newUserForm);

async function newUserForm(e){
    e.preventDefault();

    let username = document.getElementById("addUsername").value;
    let password = document.getElementById("addPassword").value;
    let name = document.getElementById("addName").value;
    let surname = document.getElementById("addSurname").value;
    let age = document.getElementById("addAge").value;
    let email = document.getElementById("addEmail").value;
    let roles = selectRole(Array.from(document.getElementById("addRole").selectedOptions)
        .map(r => r.value));

    fetch("http://localhost:8080/admin/users", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password,
            name: name,
            surname: surname,
            age: age,
            email: email,
            roles: roles
        })
    })
        .then(() => {
            document.getElementById("usersTab").click();
            getUsers();
            document.getElementById("newUserForm").reset();
        })
}

//------------------EDIT--------------------------------

async function butEdit() {

    let user = {
        id: document.getElementById('id').value,
        username: document.getElementById('editUsername').value,
        password: document.getElementById('editPassword').value,
        name: document.getElementById('editName').value,
        surname: document.getElementById('editSurname').value,
        age: document.getElementById('editAge').value,
        email: document.getElementById('editEmail').value,
        roles: selectRole(Array.from(document.getElementById("editRole").selectedOptions)
            .map(r => r.value))
    }

    let response = await fetch("http://localhost:8080/admin/users", {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)

    });
    if (response.ok) {
        $('#editModal').modal('hide');
        reTable();
    }
}

//------------------select-ROLE--------------------------------
function selectRole(r) {
    let roles = [];
    if (r.indexOf("USER") >= 0) {
        roles.push({"id": 2});
    }
    if (r.indexOf("ADMIN") >= 0) {
        roles.push({"id": 1});
    }
    return roles;
}

//------------------DELETE--------------------------------

function butDelete() {
    fetch("http://localhost:8080/admin/users/" + document.getElementById('delId').value, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    })

    $("#deleteModal .close").click();
    reTable();
}

//------------------reTable--------------------------------

function reTable() {
    let table = document.getElementById('usersTable')
    if (table.rows.length > 1) {
        table.deleteRow(1);
    }
    setTimeout(getUsers, 140)
}




