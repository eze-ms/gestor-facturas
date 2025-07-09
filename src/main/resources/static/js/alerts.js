
document.addEventListener('DOMContentLoaded', () => {
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach((alert, index) => {
        setTimeout(() => {
            alert.classList.add('fade');
            alert.classList.remove('show');
            setTimeout(() => {
                alert.remove();
            }, 500);
        }, 4000);
    });
});
