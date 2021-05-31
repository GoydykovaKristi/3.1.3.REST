$(document).ready(function () {
    viewUserInfo();
});

async function viewUserInfo() {
    fetch("http://localhost:8080/user")
        .then((res) => res.json())
        .then((user) => {
            $(".nav-username").text(user.email);
            $(".nav-user-roles").text('with roles: ' + user.roles.map(r => r.role.replace('ROLE_', '')).join(', '));

            let tab = "";
            tab += `
            <tr>
            <td> ${user.id}</td>
            <td> ${user.username}</td>
            <td> ${user.name}</td>
            <td> ${user.surname}</td>
            <td> ${user.age}</td>
            <td> ${user.email}</td>
            <td> ${user.roles.map(r => r.role.replace('ROLE_', '')).join(', ')}</td>
            </tr>`;

            $('#data').append(tab);
        })

}

