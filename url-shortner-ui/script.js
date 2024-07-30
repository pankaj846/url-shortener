document.getElementById('urlForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const originalUrl = document.getElementById('originalUrl').value;

    fetch('https://url-shortener-pankaj.onrender.com/shorten', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ originalUrl: originalUrl })
    })
    .then(response => response.json())
    .then(data => {
        const shortenedUrl = "https://url-shortener-pankaj.onrender.com/" + data.shortUrl;
        document.getElementById('result').classList.remove('hidden');
        const shortenedUrlElement = document.getElementById('shortenedUrl');
        shortenedUrlElement.textContent = shortenedUrl;
        shortenedUrlElement.href = shortenedUrl;
        shortenedUrlElement.target = "_blank"; // Open in a new tab
    })
    .catch(error => {
        console.error('Error:', error);
    });
});
