const url = 'http://localhost:8080/project-1/';

//Manager Page JS
document.getElementById("pendingbtn").addEventListener('click', pendingTicketsFunc);
document.getElementById("everyBtn").addEventListener('click', everyTicketFunc);
document.getElementById("approveBtn").addEventListener('click', approveFunc);
document.getElementById("rejectBtn").addEventListener('click', rejectFunc);

async function onloadFunc(){
    document.getElementById("user-destination").innerText=localStorage["username"];
}

async function pendingTicketsFunc(){

  console.log("pending");
  let response = await fetch(url+'viewPending', {
  method:"GET",
  credentials:"include"
  });

  if(response.status===200){
    let data = await response.json(); //This is the res body

    for(let ticket of data){
        console.log(ticket);

        let row = document.createElement("tr");

        let cell = document.createElement("td");
        cell.innerHTML = ticket.reimbId;
        row.appendChild(cell);

        let cell2 = document.createElement("td");
        cell2.innerHTML = ticket.reimbAmount;
        row.appendChild(cell2);

        let cell3 = document.createElement("td");
        cell3.innerHTML = ticket.reimbSubmitted;
        row.appendChild(cell3);

        let cell4 = document.createElement("td");
        cell4.innerHTML = ticket.reimbResolved;
        row.appendChild(cell4);

        let cell5 = document.createElement("td");
        cell5.innerHTML = ticket.reimbDescription;
        row.appendChild(cell5);

        let cell6 = document.createElement("td");
        cell6.innerHTML = ticket.reimbAuthor;
        row.appendChild(cell6);

        let cell7 = document.createElement("td");
        cell7.innerHTML = ticket.reimbResolver;
        row.appendChild(cell7);

        let cell8 = document.createElement("td");
        cell8.innerHTML = ticket.reimbStatusId;
        row.appendChild(cell8);

        let cell9 = document.createElement("td");
        cell9.innerHTML = ticket.reimbTypeId;
        row.appendChild(cell9);

        document.getElementById("tbody").appendChild(row);

    }
  }
}

async function everyTicketFunc(){

  console.log("every");
  let response = await fetch(url+'viewAll', {
  method:"GET",
  credentials:"include"
  });

  if(response.status===200){
    let data = await response.json(); //This is the res body

    for(let ticket of data){
        console.log(ticket);

        let row = document.createElement("tr");

        let cell = document.createElement("td");
        cell.innerHTML = ticket.reimbId;
        row.appendChild(cell);

        let cell2 = document.createElement("td");
        cell2.innerHTML = ticket.reimbAmount;
        row.appendChild(cell2);

        let cell3 = document.createElement("td");
        cell3.innerHTML = ticket.reimbSubmitted;
        row.appendChild(cell3);

        let cell4 = document.createElement("td");
        cell4.innerHTML = ticket.reimbResolved;
        row.appendChild(cell4);

        let cell5 = document.createElement("td");
        cell5.innerHTML = ticket.reimbDescription;
        row.appendChild(cell5);

        let cell6 = document.createElement("td");
        cell6.innerHTML = ticket.reimbAuthor;
        row.appendChild(cell6);

        let cell7 = document.createElement("td");
        cell7.innerHTML = ticket.reimbResolver;
        row.appendChild(cell7);

        let cell8 = document.createElement("td");
        cell8.innerHTML = ticket.reimbStatusId;
        row.appendChild(cell8);

        let cell9 = document.createElement("td");
        cell9.innerHTML = ticket.reimbTypeId;
        row.appendChild(cell9);

        document.getElementById("tbody").appendChild(row);
    }
  }
}

async function approveFunc(){

    let id = document.getElementById("reimbId").value;

    console.log("approve");
    let response = await fetch(url+'approve', {
    method:"PUT",
    credentials:"include"
    });

    if(response.status===200){
    let data = await response.json(); //This is the res body

    }
    }
async function rejectFunc(){

    let id = document.getElementById("reimbId").value;

    console.log("reject");
    let response = await fetch(url+'reject', {
    method:"PUT",
    credentials:"include"
    });

    if(response.status===200){
    let data = await response.json(); //This is the res body

  }
}