document.getElementById('downloadBtn').addEventListener('click', async () => {
  const status = document.getElementById('downloadStatus');
  status.textContent = 'Downloading...';

  try {
    const res = await fetch('/api/download', { method: 'POST' });
    const text = await res.text();
    status.textContent = text;

    await loadMetrics(); // reload table after download
  } catch (err) {
    status.textContent = 'Download failed: ' + err.message;
  }
});

async function loadMetrics() {
  let current = null;
  let previous = null;

  try { current = await fetch('/api/current').then(r => r.json()); } catch(e) {}
  try { previous = await fetch('/api/previous').then(r => r.json()); } catch(e) {}

  const tbody = document.querySelector('#metricsTable tbody');
  tbody.innerHTML = '';

  if (!current) return; // no current snapshot yet

  const deltaWords = previous ? current.wordCount - previous.wordCount : 0;

  tbody.innerHTML += `
    <tr>
      <td>Current</td>
      <td>${current.wordCount}</td>
      <td>${current.uniqueTerms}</td>
      <td>${current.avgSentenceLength.toFixed(2)}</td>
      <td>${current.checksum}</td>
      <td>${previous ? (deltaWords >= 0 ? '+' + deltaWords : deltaWords) : '-'}</td>
    </tr>
  `;

  if (previous) {
    tbody.innerHTML += `
      <tr>
        <td>Previous</td>
        <td>${previous.wordCount}</td>
        <td>${previous.uniqueTerms}</td>
        <td>${previous.avgSentenceLength.toFixed(2)}</td>
        <td>${previous.checksum}</td>
        <td>-</td>
      </tr>
    `;
  }
}

// Load metrics on page load
loadMetrics();
