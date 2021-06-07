$(document).ready(refreshCurrentUserTable());

function refreshCurrentUserTable() {
    $.get(`http://localhost:8080/user/infoUsername/`)
        .done((currentUser) => {
            console.log(currentUser)
            $("#currentUserTableBody")
                .empty()
                .append($('<tr>')
                    .append($('<td>').text(currentUser.id))
                    .append($('<td>').text(currentUser.username))
                    .append($('<td>').text(currentUser.name))
                    .append($('<td>').text(currentUser.surname))
                    .append($('<td>').text(currentUser.age))
                    .append($('<td>').text(currentUser.email))
                    .append($('<td>').text(currentUser.roles.map(item=>item.name.replace('ROLE_', '')).join(', ')))
                );
        })

}