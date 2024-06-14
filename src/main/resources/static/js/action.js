document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('a.confirm-delete').forEach(link => {
        link.addEventListener('click', event => {
            if (!confirm('Nie klikaj bo się Arkowi internet wywali!')) {
                event.preventDefault();
            }
        });
    });
});

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



$(document).ready(function() {
    var table = $('#activityTable').DataTable({
        "paging": true,
        "ordering": true,
        "info": true
    });

    $('#searchBox').on('keyup', function() {
        table.search(this.value).draw();
    });
});