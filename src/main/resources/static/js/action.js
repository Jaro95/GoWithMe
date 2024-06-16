
// Modal Image Gallery
function onClick(element) {
    document.getElementById("img01").src = element.src;
    document.getElementById("modal01").style.display = "block";
    var captionText = document.getElementById("caption");
    captionText.innerHTML = element.alt;
}


// Toggle between showing and hiding the sidebar when clicking the menu icon
var mySidebar = document.getElementById("mySidebar");

function w3_open() {
    if (mySidebar.style.display === 'block') {
        mySidebar.style.display = 'none';
    } else {
        mySidebar.style.display = 'block';
    }
}

// Close the sidebar with the close button
function w3_close() {
    mySidebar.style.display = "none";
}

document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('a.confirm-delete-user').forEach(link => {
        link.addEventListener('click', event => {
            if (!confirm('Czy na pewno chcesz usunąć użytkownika?')) {
                event.preventDefault();
            }
        });
    });
});

// document.addEventListener('DOMContentLoaded', () => {
//     const image = document.querySelector("#icon-user");
//     image.addEventListener("click",function (e) {
//         const buttonLogout = document.querySelector("#logoutButton")
//         if(buttonLogout == null) {
//             const logoutButton = document.createElement("a")
//             logoutButton.id = "logoutButton"
//             logoutButton.innerText = "Wyloguj";
//             logoutButton.href = "/logout";
//             logoutButton.className = "btn btn-danger";
//             image.parentElement.parentElement.appendChild(logoutButton);
//         } else {
//             image.parentElement.parentElement.removeChild(buttonLogout)
//         }
//
//     })
// });

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('user-icon').addEventListener('click', function() {
        const dropdownMenu = document.getElementById('dropdown-menu');
        dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
    });

    document.addEventListener('click', function(event) {
        const dropdownMenu = document.getElementById('dropdown-menu');
        const userIcon = document.getElementById('user-icon');
        if (!userIcon.contains(event.target) && !dropdownMenu.contains(event.target)) {
            dropdownMenu.style.display = 'none';
        }
    });
});





// document.addEventListener('DOMContentLoaded', () => {
//     document.querySelectorAll('a.profile').addEventListener('click', event => {
//             const profile = document.querySelector(".profile")
//             const edit = document.createElement(<td>Edycja</td>)
//             const logout = document.createElement(<td>Wyloguj</td>)
//             profile.appendChild(edit);
//             profile.appendChild(edit);
//         });
//     });
// });


// $(document).ready(function () {
//     var table = $('#activityTable').DataTable({
//         "paging": true,
//         "ordering": true,
//         "info": true
//     });
//
//     $('#searchBox').on('keyup', function () {
//         table.search(this.value).draw();
//     });
// });

$(document).ready(function () {
    var table = $('#adminTable').DataTable({
        "paging": true,
        "ordering": true,
        "info": true
    });

    $('#searchBox').on('keyup', function () {
        table.search(this.value).draw();
    });
});

$(document).ready(function () {
    var table = $('#activitiesTable').DataTable({
        "paging": true,
        "ordering": true,
        "info": true
    });

    $('#searchBox').on('keyup', function () {
        table.search(this.value).draw();
    });
});
