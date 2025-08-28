function fetchData(type) {
    if (type === 'alunos') {
        window.location.href = 'alunos.php';
        return;
    }
    if (type === 'cursos') {
        window.location.href = 'cursos.php';
        return;
    }
    if (type === 'disciplinas') {
        window.location.href = 'disciplinas.php';
        return;
    }
    if (type === 'professores') {
        window.location.href = 'professor.php';
        return;
    }
    if (type === 'turmas') {
        window.location.href = 'turmas.php';
        return;
    }
    
    fetch(`fetch_data.php?type=${type}`)
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById('data-container');
            container.innerHTML = generateTable(data);
        })
        .catch(error => {
            console.error('Erro ao buscar dados:', error);
        });
}

function generateTable(data) {
    if (data.length === 0) {
        return '<p>Nenhum dado encontrado.</p>';
    }

    let table = '<table><thead><tr>';
    Object.keys(data[0]).forEach(key => {
        table += `<th>${key}</th>`;
    });
    table += '</tr></thead><tbody>';

    data.forEach(row => {
        table += '<tr>';
        Object.values(row).forEach(value => {
            table += `<td>${value}</td>`;
        });
        table += '</tr>';
    });

    table += '</tbody></table>';
    return table;
}
