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


$(document).ready(function () {
    var table = $('#activityTable').DataTable({
        "paging": true,
        "ordering": true,
        "info": true
    });

    $('#searchBox').on('keyup', function () {
        table.search(this.value).draw();
    });
});

$(document).ready(function () {
    var table = $('#adminPanel').DataTable({
        "paging": true,
        "ordering": true,
        "info": true
    });

    $('#searchBox').on('keyup', function () {
        table.search(this.value).draw();
    });
});
//
// document.addEventListener('DOMContentLoaded', function () {
//     const content = document.querySelector('.create-table');
//     const itemsPerPage = 5;
//     let currentPage = 0;
//     const items = Array.from(content.getElementsByTagName('tr')).slice(1);
//
//     function showPage(page) {
//         const startIndex = page * itemsPerPage;
//         const endIndex = startIndex + itemsPerPage;
//         items.forEach((item, index) => {
//             item.classList.toggle('hidden', index < startIndex || index >= endIndex);
//         });
//         updateActiveButtonStates();
//     }
//
//     function createPageButtons() {
//         const totalPages = Math.ceil(items.length / itemsPerPage);
//         const paginationContainer = document.createElement('div');
//         const paginationDiv = document.body.appendChild(paginationContainer);
//         paginationContainer.classList.add('pagination');
//
//         // Add page buttons
//         for (let i = 0; i < totalPages; i++) {
//             const pageButton = document.createElement('button');
//             pageButton.textContent = i + 1;
//             pageButton.addEventListener('click', () => {
//                 currentPage = i;
//                 showPage(currentPage);
//                 updateActiveButtonStates();
//             });
//
//             content.appendChild(paginationContainer);
//             paginationDiv.appendChild(pageButton);
//         }
//     }
//
//     function updateActiveButtonStates() {
//         const pageButtons = document.querySelectorAll('.pagination button');
//         pageButtons.forEach((button, index) => {
//             if (index === currentPage) {
//                 button.classList.add('active');
//             } else {
//                 button.classList.remove('active');
//             }
//         });
//     }
//
//     createPageButtons(); // Call this function to create the page buttons initially
//     showPage(currentPage);
// });