document.getElementById('urlForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const originalUrl = document.getElementById('originalUrl').value;
    const loadingSpinner = document.getElementById('loadingSpinner');
    const result = document.getElementById('result');
    
    // Show loading spinner and hide result
    loadingSpinner.classList.remove('hidden');
    result.classList.add('hidden');

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
        result.classList.remove('hidden');
        const shortenedUrlElement = document.getElementById('shortenedUrl');
        shortenedUrlElement.textContent = shortenedUrl;
        shortenedUrlElement.href = shortenedUrl;
        shortenedUrlElement.target = "_blank"; // Open in a new tab
    })
    .catch(error => {
        console.error('Error:', error);
    })
    .finally(() => {
        // Hide loading spinner
        loadingSpinner.classList.add('hidden');
    });
});
