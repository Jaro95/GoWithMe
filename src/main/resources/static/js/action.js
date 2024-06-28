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
    document.querySelectorAll('#delete-activity').forEach(link => {
        link.addEventListener('click', event => {
            if (!confirm('Czy na pewno chcesz usunąć aktywność?')) {
                event.preventDefault();
            }
        });
    });
});

document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('#delete-user-from-joined-list').forEach(link => {
        link.addEventListener('click', event => {
            if (!confirm('Czy na pewno chcesz usunąć uzytkownika z aktywności')) {
                event.preventDefault();
            }
        });
    });
});

document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('#delete-user-from-list').forEach(link => {
        link.addEventListener('click', event => {
            if (!confirm('Czy na pewno chcesz usunąć użytkownika z listy oczekujących?')) {
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
    document.getElementById('activities-navigation').addEventListener('click', function () {
        const dropdownMenu = document.getElementById('dropdown-activities');
        dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
    });

    document.addEventListener('click', function (event) {
        const dropdownMenu = document.getElementById('dropdown-activities');
        const userIcon = document.getElementById('activities-navigation');
        if (!userIcon.contains(event.target) && !dropdownMenu.contains(event.target)) {
            dropdownMenu.style.display = 'none';
        }
    });
});

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('notification').addEventListener('click', function () {
        const dropdownMenu = document.getElementById('dropdown-notification');
        dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
    });

    document.addEventListener('click', function (event) {
        const dropdownMenu = document.getElementById('dropdown-notification');
        const userIcon = document.getElementById('notification');
        if (!userIcon.contains(event.target) && !dropdownMenu.contains(event.target)) {
            dropdownMenu.style.display = 'none';
        }
    });
});
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('user-icon').addEventListener('click', function () {
        const dropdownMenu = document.getElementById('dropdown-menu');
        dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
    });

    document.addEventListener('click', function (event) {
        const dropdownMenu = document.getElementById('dropdown-menu');
        const userIcon = document.getElementById('user-icon');
        if (!userIcon.contains(event.target) && !dropdownMenu.contains(event.target)) {
            dropdownMenu.style.display = 'none';
        }
    });
});


document.addEventListener('DOMContentLoaded', () => {
    const inputCity = document.getElementById('cityInput');
    inputCity.addEventListener('input', function () {
        let prefix = this.value;

        if (prefix.length >= 1) {
            fetch(`http://api.geonames.org/searchJSON?name_startsWith=${prefix}&maxRows=1&country=PL&username=gohan`)
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    let suggestions = document.getElementById('suggestions');
                    suggestions.innerHTML = '';

                    data.geonames.forEach(city => {
                        let li = document.createElement('li');
                        li.textContent = city.name;

                        li.addEventListener('click', function () {
                            document.getElementById('cityInput').value = city.name;
                            suggestions.innerHTML = ''; // Clear suggestions after selection
                        });
                        suggestions.appendChild(li);
                    });
                })
                .catch(error => console.error('Error:', error));
        } else {
            document.getElementById('suggestions').innerHTML = '';
        }
    });
});


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
$(document).ready(function () {
    var table = $('#assignTable').DataTable({
        "paging": false,
        "ordering": false,
        "info": false,
        "dom": 't'
    });

    $('#searchBox').on('keyup', function () {
        table.search(this.value).draw();
    });
});

$(document).ready(function () {
    $(document).on('click', '.prev-button, .next-button', function (e) {
        e.preventDefault();
        var url = $(this).attr('href');
        $.get(url, function (data) {
            var newContent = $(data).find('.notification-list').html();
            var newNavigation = $(data).find('.navigation-buttons').html();
            $('.notification-list').html(newContent);
            $('.navigation-buttons').html(newNavigation);
        }).fail(function () {
            alert('Wystąpił błąd podczas ładowania danych.');
        });
    });
});

$(document).ready(function () {
    $(document).on('click', '.prev-user, .next-user', function (e) {
        e.preventDefault();
        var url = $(this).attr('href');
        $.get(url, function (data) {
            var newContent = $(data).find('.user-list').html();
            var newNavigation = $(data).find('.user-buttons').html();
            $('.user-list').html(newContent);
            $('.user-buttons').html(newNavigation);
        }).fail(function () {
            alert('Wystąpił błąd podczas ładowania danych.');
        });
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const chatInput = document.getElementById('chatInput');

    chatInput.addEventListener('input', function () {
        // Reset textarea height to auto to correctly calculate the new height
        chatInput.style.height = 'auto';
        // Set the height according to the scroll height
        chatInput.style.height = chatInput.scrollHeight + 'px';
    });
});
document.addEventListener("DOMContentLoaded", function () {

});

