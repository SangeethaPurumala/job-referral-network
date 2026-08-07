const BASE_URL = "http://localhost:8080/persons";


// ===============================
// Add Person
// ===============================
function addPerson() {

    const person = {
        id: document.getElementById("id").value,
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        location: document.getElementById("location").value
    };


    fetch(BASE_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(person)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        loadPersons();
    })
    .catch(error => {
        console.error(error);
        alert("Error while adding person");
    });
}



// ===============================
// Create Referral
// ===============================
function createReferral() {

    const referral = {
        fromId: document.getElementById("fromId").value,
        toId: document.getElementById("toId").value
    };


    fetch(BASE_URL + "/referral", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(referral)

    })

    .then(response => response.text())

    .then(data => {

        alert(data);

    })

    .catch(error => {

        console.error(error);

        alert("Error while creating referral");

    });

}



// ===============================
// Load All Persons
// ===============================
function loadPersons() {


    fetch(BASE_URL)

    .then(response => response.json())

    .then(data => {


        let output = `

        <table class="table table-bordered">

        <thead>

        <tr>

        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Location</th>

        </tr>

        </thead>

        <tbody>

        `;


        data.forEach(person => {


            output += `

            <tr>

            <td>${person.id}</td>

            <td>${person.name}</td>

            <td>${person.email}</td>

            <td>${person.phone}</td>

            <td>${person.location}</td>


            </tr>

            `;


        });


        output += `

        </tbody>

        </table>

        `;


        document.getElementById("persons").innerHTML = output;


    })


    .catch(error => {

        console.error(error);

        alert("Unable to load persons");

    });

}



// ===============================
// Find Referrals (1-2 hops)
// ===============================
function findReferrals() {


    const id = document.getElementById("searchId").value;


    fetch(BASE_URL + "/" + id + "/referrals")


    .then(response => response.json())


    .then(data => {


        let output = "<h5>Referral Results</h5>";


        if(data.length === 0){

            output += "<p>No referrals found</p>";

        }


        data.forEach(person => {


            output += `

            <div class="card mb-2 p-3">


            <h5>${person.name}</h5>


            ID: ${person.id}<br>

            Email: ${person.email}<br>

            Phone: ${person.phone}<br>

            Location: ${person.location}


            </div>


            `;


        });


        document.getElementById("persons").innerHTML = output;


    })


    .catch(error => {

        console.error(error);

        alert("Unable to find referrals");

    });


}