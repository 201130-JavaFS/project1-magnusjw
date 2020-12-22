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

    document.getElementById("tbody").innerHTML = "";

    if(data == ""){
        document.getElementById("info").innerText = "No Entries";
    }
    
    for(let ticket of data){
        console.log(ticket);

        let row = document.createElement("tr");

        let cell = document.createElement("td");
        cell.innerHTML = ticket.id;
        row.appendChild(cell);

        let cell2 = document.createElement("td");
        cell2.innerHTML = ticket.amount;
        row.appendChild(cell2);

        let cell3 = document.createElement("td");
        cell3.innerHTML = ticket.submitted;
        row.appendChild(cell3);

        let cell4 = document.createElement("td");
        cell4.innerHTML = ticket.resolved;
        row.appendChild(cell4);

        let cell5 = document.createElement("td");
        cell5.innerHTML = ticket.description;
        row.appendChild(cell5);

        let cell6 = document.createElement("td");
        cell6.innerHTML = ticket.authorId;
        row.appendChild(cell6);

        let cell7 = document.createElement("td");
        if(ticket.resolverId == 0){
            cell7.innerHTML = "";
        } else{
            cell7.innerHTML = ticket.resolverId;
        }
        row.appendChild(cell7);

        let cell8 = document.createElement("td");
        if(ticket.statusId == 1){
            cell8.innerHTML = "Pending";
        } else if(ticket.statusId == 2){
            cell8.innerHTML = "Accepted";
        } else {
            cell8.innerHTML = "Rejected";
        }
        row.appendChild(cell8);

        let cell9 = document.createElement("td");
        if(ticket.typeId == 1){
            cell9.innerHTML = "Lodging";
        } else if(ticket.typeId == 2){
            cell9.innerHTML = "Travel";
        } else if(ticket.typeId == 3){
            cell9.innerHTML = "Food";
        } else {
            cell9.innerHTML = "Other";
        }
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

    document.getElementById("tbody").innerHTML = "";

    if(data == ""){
        document.getElementById("info").innerText = "No Entries";
    }

    for(let ticket of data){
        console.log(ticket);

        let row = document.createElement("tr");

        let cell = document.createElement("td");
        cell.innerHTML = ticket.id;
        row.appendChild(cell);

        let cell2 = document.createElement("td");
        cell2.innerHTML = ticket.amount;
        row.appendChild(cell2);

        let cell3 = document.createElement("td");
        cell3.innerHTML = ticket.submitted;
        row.appendChild(cell3);

        let cell4 = document.createElement("td");
        cell4.innerHTML = ticket.resolved;
        row.appendChild(cell4);

        let cell5 = document.createElement("td");
        cell5.innerHTML = ticket.description;
        row.appendChild(cell5);

        let cell6 = document.createElement("td");
        cell6.innerHTML = ticket.authorId;
        row.appendChild(cell6);

        let cell7 = document.createElement("td");
        if(ticket.resolverId == 0){
            cell7.innerHTML = "";
        } else{
            cell7.innerHTML = ticket.resolverId;
        }
        row.appendChild(cell7);

        let cell8 = document.createElement("td");
        if(ticket.statusId == 1){
            cell8.innerHTML = "Pending";
        } else if(ticket.statusId == 2){
            cell8.innerHTML = "Accepted";
        } else {
            cell8.innerHTML = "Rejected";
        }
        row.appendChild(cell8);

        let cell9 = document.createElement("td");
        if(ticket.typeId == 1){
            cell9.innerHTML = "Lodging";
        } else if(ticket.typeId == 2){
            cell9.innerHTML = "Travel";
        } else if(ticket.typeId == 3){
            cell9.innerHTML = "Food";
        } else {
            cell9.innerHTML = "Other";
        }
        row.appendChild(cell9);

        document.getElementById("tbody").appendChild(row);
    }
  }
}

async function approveFunc(){

    let id = document.getElementById("reimbId").value;

    let objId = {
        reimbId:id
    };

    console.log("approve");

    let response = await fetch(url+'approve', {
    method:"PUT",
    body: JSON.stringify(objId),
    credentials:"include"
    });

    if(response.status===200){
        document.getElementById("info").innerText = "Accepted";

    } else if(response.status===400){
        document.getElementById("info").innerText = "Unexpected Error";

    } else if(response.status===401){
        document.getElementById("info").innerText = "Invalid Ticket Id";

    } else if(response.status===406){
        document.getElementById("info").innerText = "Ticket must be Pending";
        
    } else if(response.status===404){
        document.getElementById("info").innerText = "Ticket Not Found";
    }
}

async function rejectFunc(){

    let id = document.getElementById("reimbId").value;

    let objId = {
        reimbId:id
    };

    console.log("reject");

    let response = await fetch(url+'reject', {
    method:"PUT",
    body: JSON.stringify(objId),
    credentials:"include"
    });

    if(response.status===200){
        document.getElementById("info").innerText = "Rejected";

    } else if(response.status===400){
        document.getElementById("info").innerText = "Unexpected Error";

    } else if(response.status===401){
        document.getElementById("info").innerText = "Invalid Ticket Id";

    } else if(response.status===406){
        document.getElementById("info").innerText = "Ticket must be Pending";
        
    } else if(response.status===404){
        document.getElementById("info").innerText = "Ticket Not Found";
    }
}