document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('a.confirm-delete').forEach(link => {
        link.addEventListener('click', event => {
            if (!confirm('Nie klikaj bo się Arkowi internet wywali!')) {
                event.preventDefault();
            }
        });
    });
});