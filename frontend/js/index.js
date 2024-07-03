function getTodos() {
    axios.get("http://localhost:8081/api/todo")
        .then(response => {
            console.log("Todos fetched successfully:", response.data);
            let todoRows = response.data.map(todo => `
                <tr>
                    <td>${todo.id}</td>
                    <td>${todo.title}</td>
                    <td>${todo.description}</td>
                    <td><input type="checkbox" data-todo-id="${todo.id}" ${todo.completed ? 'checked' : ''}></td>
                </tr>
            `).join('');

            document.getElementById("tbody").innerHTML = todoRows;

            attachCheckboxEventListener();
        })
        .catch(error => {
            console.error('Error fetching todos:', error);
        });
}

function attachCheckboxEventListener() {
    let checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function() {
            let todoId = this.getAttribute('data-todo-id');
            let newStatus = this.checked;

            updateTodoStatus(todoId, newStatus);
        });
    });
}

function updateTodoStatus(todoId, newStatus) {
    axios.put(`http://localhost:8081/api/todo/${todoId}`, { completed: newStatus })
        .then(response => {
            console.log("Todo status updated successfully:", response.data);
        })
        .catch(error => {
            console.error('Error updating todo status:', error);
        });
}

document.getElementById("addTodoButton").addEventListener('click', function(event) {
    event.preventDefault();
    document.getElementById("todoForm").classList.toggle('d-none');
});

document.getElementById("newTodoForm").addEventListener('submit', function(event) {
    event.preventDefault();
    console.log("hi")
    let title = document.getElementById("title").value;
    let description = document.getElementById("description").value;

    axios.post("http://localhost:8081/api/todo", { title, description })
        .then(response => {
            console.log("Todo added successfully:", response.data);
            document.getElementById("newTodoForm").reset();
            document.getElementById("todoForm").classList.add('d-none');
            getTodos();
        })
        .catch(error => {
            console.error('Error adding todo:', error);
        });
});




document.addEventListener('DOMContentLoaded', function() {
    getTodos();
});
